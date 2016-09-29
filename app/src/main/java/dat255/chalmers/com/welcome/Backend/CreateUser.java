package dat255.chalmers.com.welcome.Backend;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class CreateUser extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... strings) {

        String dataUrl = "http://johnandersson.me:3030/user";
        String dataUrlParameters = "";

        for (int i = 0; i < strings.length; i++) {
            if (i == 0) {
                dataUrlParameters += strings[i];
            } else {
                dataUrlParameters += "&" + strings[i];
            }

        }

        URL url;
        HttpURLConnection connection = null;
        try {
            // Create connection
            url = new URL(dataUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
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

