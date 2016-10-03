package dat255.chalmers.com.welcome.BackendInterfaces;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.AUTH_TOKEN;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;


public class BackendConnection extends AsyncTask<String, Void, Void> {

    private Context context;
    private String dataUrl = "http://95.80.8.206:3030/";

    public BackendConnection (Context context) {
        super();
        this.context = context;
    }

    private void saveAuthToken(JSONObject json) {

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);

        SharedPreferences.Editor editor = prefs.edit();

        System.out.println(json);
        try {
            editor.putString(AUTH_TOKEN, json.getString("auth_token"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        editor.commit();
    }

    @Override
    protected Void doInBackground(String... parameters) {


        if (parameters[2].equals("GET")) {
            sendGet(parameters);
        } else if (parameters[2].equals("POST")) {
            sendPost(parameters);
        }
        return null;
    }

    private void sendGet(String... parameters) {
        JSONObject json = null;

        // subPath parameter
        dataUrl += parameters[0];
        URL url;
        HttpURLConnection connection = null;
        try {
            // Create connection
            url = new URL(dataUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("authorization", "Token token=" + parameters[3]);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setRequestProperty("Accept","*/*");

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
            // use jason object to save the auth_token locally
            json = new JSONObject(responseStr);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }

    }



    private JSONObject sendPost(String... parameters) {
        JSONObject json = null;

        // subPath parameter
        dataUrl += parameters[0];
        URL url;
        HttpURLConnection connection = null;
        try {
            // Create connection
            url = new URL(dataUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("authorization", "Token token=" + parameters[3]);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept","*/*");

            // Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(parameters[1]);
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
            // use json object to save the auth_token locally
            json = new JSONObject(responseStr);
            if (parameters[0].equals("user")) {
                saveAuthToken(json);
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }

        return json;
    }



}
