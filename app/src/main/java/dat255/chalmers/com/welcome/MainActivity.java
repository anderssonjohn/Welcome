package dat255.chalmers.com.welcome;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> matchList = new ArrayList<String>();
    ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Intent intent = new Intent(MainActivity.this, MainActivity.class);

                // Starts the intent
                startActivity(intent);

            }
        });
    }

    // When "Match" button is clicked this function starts
    public void showMatch(View view){
        itemsAdapter.add("Kakan");

    }
}
