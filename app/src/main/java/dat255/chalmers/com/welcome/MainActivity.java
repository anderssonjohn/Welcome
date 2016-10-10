package dat255.chalmers.com.welcome;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dat255.chalmers.com.welcome.BackendInterfaces.BackendConnection;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.AUTH_TOKEN;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.FIRST_RUN;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.SWEDISH_SPEAKER;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.VIEWED_INFO;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> matchList = new ArrayList<>();
    ArrayList<String> idList = new ArrayList<>();
    ArrayAdapter<String> itemsAdapter;
    public static String CHAT_BUDDY_ID = "";
    private static boolean isMentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check if the app has been run before, and display the first time setup if it hasn't
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        isMentor = prefs.getBoolean(SWEDISH_SPEAKER, true);
        boolean firstRun = prefs.getBoolean(FIRST_RUN, true);

        if (firstRun) {
            Intent intent = new Intent(MainActivity.this, LanguageActivity.class);
            startActivity(intent);
        } else {
            new GetAllMatches().execute();
        }


        //Add an adapter to our listview
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, matchList);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(itemsAdapter);

        //Set a listener to our listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * This is called when a listitem in the list is clicked.
             * @param adapterView
             * @param view
             * @param i
             * @param l
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = (String) adapterView.getItemAtPosition(i);
                // Creates a new intent which indicates which activity you're in and also which
                // activity we intend to go to
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                intent.putExtra(CHAT_BUDDY_ID, idList.get(i));
                // Starts the intent
                startActivity(intent);
            }
        });

        //Here we notify the activity tyhat the listview should have a context menu.
        registerForContextMenu(listView);
    }

    //Here we specify what should happen when the different buttons in the context menu of the
    //contact list is pressed. (Right now we only have a delete function)
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.deleteItem:
                deleteContact((int)info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    public void deleteContact(int id){
        String contactName = matchList.get(id);
        new RemoveContact().execute(idList.get(id));
        itemsAdapter.remove(contactName);
    }

    //This simply tells the activity what context menu xml it should be using.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_context_menu, menu);
    }

    //A dialog that will be displayed the first time the user finds a match
    public static class FirstMatchDialog extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.first_match_title);
            if(isMentor) {
                builder.setMessage(R.string.first_match_info_sv);
            } else {
                builder.setMessage(R.string.first_match_info_as);

            }
            builder.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Back out
                        }
                    });

            return builder.create();
        }
    }

    // When "Match" button is clicked this function starts
    public void showMatch(View view){
        new GetMatches().execute();
        new GetAllMatches().execute();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        boolean viewedBefore = prefs.getBoolean(VIEWED_INFO, false);
        System.out.println(matchList);
        if((matchList.isEmpty()) && !(viewedBefore)) {
            FirstMatchDialog dialog = new FirstMatchDialog();
            dialog.show(getFragmentManager(), "");
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(VIEWED_INFO, true);
            editor.commit();
        }
    }

    public void showSettings(MenuItem item) {
        Intent intent = new Intent(this, PreferencesActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //Do nothing
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    private class GetMatches extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);

            String token= sharedPreferences.getString(AUTH_TOKEN,"");

            BackendConnection.sendGet("match", token);
            return null;
        }
    }

    private class RemoveContact extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
            String token= sharedPreferences.getString(AUTH_TOKEN,"");

            BackendConnection.sendGet("delete/" + params[0], token);
            return null;
        }
    }

    private class GetAllMatches extends AsyncTask<Void, Void, JSONArray> {

        @Override
        protected void onPostExecute(JSONArray json) {
            itemsAdapter.clear();
            try {
                for (int i = 0; i < json.length(); i++) {
                    JSONObject object = json.getJSONObject(i);
                    itemsAdapter.add(object.getString("name"));
                    idList.add(object.getString("recipient_id"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected JSONArray doInBackground(Void... params) {
            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
            String token = sharedPreferences.getString(AUTH_TOKEN, "");
            try {
                return new JSONArray(BackendConnection.sendGet("conversations", token));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
