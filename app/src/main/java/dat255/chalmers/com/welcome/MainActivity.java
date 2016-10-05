package dat255.chalmers.com.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.FIRST_RUN;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> matchList = new ArrayList<String>();
    ArrayList<String> idList = new ArrayList<>();
    ArrayAdapter<String> itemsAdapter;
    public static String CHAT_BUDDY_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check if the app has been run before, and display the first time setup if it hasn't
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        boolean firstRun = prefs.getBoolean(FIRST_RUN, true);

        if (firstRun) {
            Intent intent = new Intent(MainActivity.this, LanguageActivity.class);
            startActivity(intent);
        }
        //Otherwise, just keep on going with the main activity...

       itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, matchList);
       ListView listView = (ListView) findViewById(R.id.listView);
       listView.setAdapter(itemsAdapter);

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
                System.out.println(idList.get(i));
                // Starts the intent
                startActivity(intent);

            }
        });
    }

    // When "Match" button is clicked this function starts
    public void showMatch(View view){
        itemsAdapter.add("Kakan");
        new GetMatches().execute();
        new GetAllMatches().execute();
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
