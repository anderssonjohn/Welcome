package dat255.chalmers.com.welcome.BackendInterfaces;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.AUTH_TOKEN;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_DAY;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_MONTH;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_YEAR;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.GENDER;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.INTEREST_ID;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.JOB_ID;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;

public class CreateUser extends AsyncTask<Void, Void, Void> {

    private Context context;

    public CreateUser (Context context){
        this.context = context;
    }


    @Override
    protected Void doInBackground(Void... fuckAndroid) {

        String dataUrl = "http://johnandersson.me:3030/user";
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);

        String gender = prefs.getString(GENDER, "null");
        Long profession = prefs.getLong(JOB_ID,0);
        Long interest = prefs.getLong(INTEREST_ID,0);

        int DateOfBirth = prefs.getInt(DOB_DAY,0);
        int MonthOfBirth = prefs.getInt(DOB_MONTH,0);
        int YearOfBirth = prefs.getInt(DOB_YEAR, 0);

        JSONObject json;


        String dataUrlParameters = "name=" + "Johny Awesome"
                + "&gender=" + gender
                +"&profession=" + profession
                + "&interest=" + interest
                + "&date=" + YearOfBirth + "-" + MonthOfBirth + "-" + DateOfBirth
                + "&swedish_speaker=" + "true";


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
            // use jason object to save the auth_token locally
            json = new JSONObject(responseStr);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(AUTH_TOKEN, json.getString("auth_token")) ;
            editor.commit();

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

