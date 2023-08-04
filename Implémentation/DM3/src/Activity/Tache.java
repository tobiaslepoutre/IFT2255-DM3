package Activity;

import Activity.action.Action;
import Machines.Robot;

import java.util.ArrayList;
import java.util.Date;

public class Tache {
    private int executionDate;

    /* Assosiations */
    private ArrayList<Action> actions;
    private Activity activity;
    private Robot executionner;

    public Tache(int executionDate){
        this.executionDate = executionDate;
        this.actions = new ArrayList<>();
    }

    public Tache(int executionDate, Activity activity){
        this.executionDate = executionDate;
        this.actions = new ArrayList<>();
        this.activity = activity;
    }

    public void assignRobot(Robot r){
        this.executionner = r;
    }

    public Date getExecutionDate(){
        return this.getExecutionDate();
    }

}
