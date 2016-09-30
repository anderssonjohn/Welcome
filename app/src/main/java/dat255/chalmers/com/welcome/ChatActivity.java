package dat255.chalmers.com.welcome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class ChatActivity extends AppCompatActivity {


    ArrayList<String> chatList = new ArrayList<>();
    ArrayAdapter<String> receivedTextAdapter;
    ArrayAdapter<String> sentTextAdapter;
    boolean flipflop = true;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        receivedTextAdapter = new ArrayAdapter<>(this, R.layout.bubble, chatList);
        sentTextAdapter = new ArrayAdapter<>(this, R.layout.bubbleblue, chatList);

        listView = (ListView) findViewById(R.id.chatListView);
        listView.setAdapter(receivedTextAdapter);

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
        if (flipflop){
            listView.setAdapter(receivedTextAdapter);
        } else {
            listView.setAdapter(sentTextAdapter);
        }
        flipflop = !flipflop;
        System.out.println(flipflop);
        EditText messageField = (EditText) findViewById(R.id.messageField);
        ((ArrayAdapter<String>)listView.getAdapter()).add(messageField.getText().toString());
    }
}
