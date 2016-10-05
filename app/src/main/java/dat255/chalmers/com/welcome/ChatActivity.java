package dat255.chalmers.com.welcome;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dat255.chalmers.com.welcome.BackendInterfaces.BackendConnection;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.AUTH_TOKEN;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;


public class ChatActivity extends AppCompatActivity {


    ArrayList<Message> chatList = new ArrayList<>();
    ChatAdapter chatAdapter;
    ListView listView;
    boolean flipFlop = true;

    String userAuthToken;
    int buddyId;

    SharedPreferences convoPrefs;
    SharedPreferences.Editor convoEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        buddyId = Integer.parseInt( getIntent().getStringExtra(MainActivity.CHAT_BUDDY_ID));
        SharedPreferences profilePrefs = getSharedPreferences(SharedPreferencesKeys.PREFS_NAME, 0);
        userAuthToken = profilePrefs.getString(SharedPreferencesKeys.AUTH_TOKEN, "NoProfile");

        convoPrefs = getSharedPreferences("Conversations", 0);
        convoEditor = convoPrefs.edit();

        chatAdapter = new ChatAdapter(chatList);

        listView = (ListView) findViewById(R.id.chatListView);
        listView.setAdapter(chatAdapter);

        //loadAllMessages();

        new GetMessageDatabase().execute();

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * This is called when a listitem in the list is clicked.
             * @param adapterView
             * @param view
             * @param i
             * @param l
             *//*
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });*/
    }

    public void loadAllMessages(){
        int counter = 0;
        while (true){
            Message message = new Message("", true);
            message.body = convoPrefs.getString("conversation" + Integer.toString(buddyId) + "message" + counter + "body", "noMessageFoundError");
            message.fromMe = convoPrefs.getBoolean("conversation" + Integer.toString(buddyId) + "message" + counter + "fromMe", true);
            if ((message.body).equals("noMessageFoundError")){
                break;
            }
            counter++;
            chatList.add(message);
        }
        chatAdapter.notifyDataSetChanged();
    }

    public void sendMessage(View view){
        EditText messageField = (EditText) findViewById(R.id.messageField);
        if (!messageField.getText().toString().isEmpty()){
            Message message = new Message(messageField.getText().toString(), flipFlop);
            flipFlop = !flipFlop;
            messageField.setText("");
            showMessage(message);
            saveMessageLocal(message);
            new SaveMessageDatabase(message).execute();
        }
    }

    public void showMessage(Message message){
        
        chatList.add(message);
        chatAdapter.notifyDataSetChanged();
    }

    public void saveMessageLocal(Message message){
        convoEditor.putString("conversation" + buddyId + "message" + chatList.indexOf(message) + "body", message.body);
        convoEditor.putBoolean("conversation" + buddyId + "message" + chatList.indexOf(message) + "fromMe", message.fromMe);
        convoEditor.apply();
    }

    private class Message{
        public String body;
        public boolean fromMe;

        private Message(String body, boolean fromMe){
            this.body = body;
            this.fromMe = fromMe;
        }
    }

    class ChatAdapter extends BaseAdapter {

        ArrayList<Message> messageList;

        public ChatAdapter(ArrayList<Message> messages){
            messageList = messages;
        }

        @Override
        public int getCount() {
            return messageList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View messageBubble;
            Message mess = messageList.get(position);
            if (mess.fromMe){
                messageBubble = inflater.inflate(R.layout.bubbleblue, parent, false);
            } else {
                messageBubble = inflater.inflate(R.layout.bubble, parent, false);
            }


            TextView text = (TextView)messageBubble.findViewById(R.id.bubble);
            text.setText(mess.body);

            return messageBubble;
        }


    }


    private class SaveMessageDatabase extends AsyncTask<Void, Void, Void>{

        Message message = new Message("", true);

        public SaveMessageDatabase(Message message){
            this.message = message;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String urlParameters = "recipient=" + Integer.toString(buddyId) + "&body=" + message.body;
            BackendConnection.sendPost("messages", urlParameters, userAuthToken);

            return null;
        }
    }

    private class GetMessageDatabase extends AsyncTask<Void, Void, JSONArray>{


        @Override
        protected void onPostExecute(JSONArray jsonArray){

            JSONObject object;

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    object = jsonArray.getJSONObject(i);

                    Boolean fromMe = !(object.getInt("user_id") == buddyId);

                    Message message = new Message(object.getString("body"), fromMe);
                    chatList.add(message);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            chatAdapter.notifyDataSetChanged();

        }


        @Override
        protected JSONArray doInBackground(Void... voids) {

            String messages = BackendConnection.sendGet("conversations/" + Integer.toString(buddyId) + "/messages", userAuthToken);

            try {
               return new JSONArray(messages);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
