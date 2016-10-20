package dat255.chalmers.com.welcome;

import java.util.ArrayList;

public class WizardManager {
    private static WizardManager instance;
    private static ArrayList<String> stepsList = new ArrayList<>();

    public static WizardManager getInstance() {
        if (instance == null){
            instance = new WizardManager();
        }
        return instance;
    }

    private WizardManager(){
        stepsList.add("MentorChoice");
        stepsList.add("GenderAndBirth");
        stepsList.add("Job");
    }

    public int getIndexOf(String str){
        return stepsList.indexOf(str);
    }

    public int getPageCount(){
        return stepsList.size();
    }
}
