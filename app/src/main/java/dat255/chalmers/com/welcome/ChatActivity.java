package dat255.chalmers.com.welcome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ChatActivity extends AppCompatActivity {


    ArrayList<Message> chatList = new ArrayList<>();
    ChatAdapter chatAdapter;
    ListView listView;
    boolean flipFlop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatAdapter = new ChatAdapter(chatList);

        listView = (ListView) findViewById(R.id.chatListView);
        listView.setAdapter(chatAdapter);

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
            }
        });
    }

    public void sendMessage(View view){
        EditText messageField = (EditText) findViewById(R.id.messageField);
        Message message = new Message(messageField.getText().toString(), flipFlop?0:1);
        chatList.add(message);
        chatAdapter.notifyDataSetChanged();
        flipFlop = !flipFlop;
    }

    private class Message{
        public String body;
        public int senderId;

        private Message(String body, int senderId){
            this.body = body;
            this.senderId = senderId;
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
            if (mess.senderId == 0){
                messageBubble = inflater.inflate(R.layout.bubble, parent, false);
            } else {
                messageBubble = inflater.inflate(R.layout.bubbleblue, parent, false);
            }


            TextView text = (TextView)messageBubble.findViewById(R.id.bubble);
            text.setText(mess.body);

            return messageBubble;
        }
    }
}
