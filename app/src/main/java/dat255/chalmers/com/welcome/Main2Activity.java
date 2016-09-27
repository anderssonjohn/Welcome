package dat255.chalmers.com.welcome;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void createUser(View view) {
        try {

            URL url = new URL("http", "95.80.8.206", 3030, "abc/def/ghj");
            new CreateUser().execute(url);
        } catch (Exception e) {
            System.out.println("ERROR FÖR FAN");
        }


//        String dataUrl = "http://95.80.8.206:3030/abc/def/ghj";
//        String dataUrlParameters = "abc/def/ghj";// + "def" + "ghj";
//        URL url;
//        HttpURLConnection connection = null;
//        try {
//        // Create connection
////            url = new URL(dataUrl);
//            url = new URL("http", "95.80.8.206", 3030, "abc/def/ghj");
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
////            connection.setRequestProperty("Content-Type", "text/html");
////            connection.setRequestProperty("Content-Length", "" + Integer.toString(dataUrlParameters.getBytes().length));
////            connection.setRequestProperty("Content-Language", "en-US");
//            connection.setUseCaches(false);
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//            // Send request
//            DataOutputStream wr = new DataOutputStream(
//                    connection.getOutputStream());
//            wr.writeBytes(dataUrlParameters);
//            wr.flush();
//            wr.close();
//            // Get Response
//            InputStream is = connection.getInputStream();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//            String line;
//            StringBuffer response = new StringBuffer();
//            while ((line = rd.readLine()) != null) {
//                response.append(line);
//                response.append('\r');
//            }
//            rd.close();
//            String responseStr = response.toString();
//            Log.d("Server response", responseStr);
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        } finally {
//
//            if (connection != null) {
//                connection.disconnect();
//            }
//        }


//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://www.google.com";
//
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        mTextView.setText("Response is: " + response.substring(0, 500));
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
//            }
//        });
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);



    }

    private class CreateUser extends AsyncTask<URL, Void, Integer> {
        @Override
        protected Integer doInBackground(URL... params) {

            String dataUrl = "http://johnandersson.me:3030/user";
            String dataUrlParameters = "first_name=ab&last_name=bc&proffesion=dc";
            URL url;
            HttpURLConnection connection = null;
            try {
                // Create connection
                url = new URL(dataUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestProperty("Accept","*/*");

                // Send request
                DataOutputStream wr = new DataOutputStream(
                        connection.getOutputStream());
                wr.writeBytes(dataUrlParameters);
                wr.flush();
                wr.close();
                // Get Response
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                String responseStr = response.toString();
                Log.d("Server response", responseStr);

            } catch (Exception e) {

                e.printStackTrace();

            } finally {

                if (connection != null) {
                    connection.disconnect();
                }
            }

            return null;
        }
    }
}
