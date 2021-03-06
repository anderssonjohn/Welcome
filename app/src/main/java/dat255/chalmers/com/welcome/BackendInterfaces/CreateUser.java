package dat255.chalmers.com.welcome.BackendInterfaces;


import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.AUTH_TOKEN;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_DAY;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_MONTH;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_YEAR;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.GENDER;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.INTEREST_ID;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.JOB_ID;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.NAME;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.SWEDISH_SPEAKER;

public class CreateUser {

    private Context context;

    public CreateUser (Context context){
        this.context = context;
    }

    public void createUser() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);

        String gender = prefs.getString(GENDER, "null");
        String name = prefs.getString(NAME, "null");
        Boolean swedish_speaker = prefs.getBoolean(SWEDISH_SPEAKER, true);
        String profession = prefs.getString(JOB_ID,"null");
        String interest = prefs.getString(INTEREST_ID,"null");

        int DateOfBirth = prefs.getInt(DOB_DAY,0);
        int MonthOfBirth = prefs.getInt(DOB_MONTH,0);
        int YearOfBirth = prefs.getInt(DOB_YEAR, 0);

        String dataUrlParameters = "name=" + name
                + "&gender=" + gender
                +"&profession=" + profession
                + "&interest=" + interest
                + "&date=" + YearOfBirth + "-" + (MonthOfBirth + 1) // + 1 since the month is zeroindexed
                + "-" + DateOfBirth
                + "&swedish_speaker=" + swedish_speaker;

        String subPath = "user";
        SharedPreferences.Editor editor = prefs.edit();
        try {
            JSONObject json = new JSONObject(BackendConnection.sendPost(subPath, dataUrlParameters, ""));

            editor.putString(AUTH_TOKEN, json.getString("auth_token"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        editor.commit();
    }
}

