package dat255.chalmers.com.welcome;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Gustav on 2016-10-08.
 */

public class WizardManager {
    private static WizardManager instance;
    private ArrayList<String> stepsList = new ArrayList<>();

    public static WizardManager getInstance() {
        if (instance == null){
            instance = new WizardManager();
        }
        return instance;
    }

    private WizardManager(){
        stepsList.add("Language");
        stepsList.add("MentorChoice");
        stepsList.add("Info");
        stepsList.add("GenderAndBirth");
        stepsList.add("Job");
    }

    public int getPosInList(String str){
        
    }
}
