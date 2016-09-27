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


    ArrayList<String> chatList = new ArrayList<String>();
    ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chatList);
        ListView listView = (ListView) findViewById(R.id.chatListView);
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
            }
        });
    }

    public void sendMessage(View view){
        EditText messageField = (EditText) findViewById(R.id.messageField);
        itemsAdapter.add(messageField.getText().toString());
    }
}
