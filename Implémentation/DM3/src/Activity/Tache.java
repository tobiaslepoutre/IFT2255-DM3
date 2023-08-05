package Activity;

import Activity.action.Action;
import Machines.Robot;

import java.util.ArrayList;
import java.util.Date;

public class Tache {
    private Date executionDate;

    /* Assosiations */
    private ArrayList<Action> actions;
    private Activity activity;
    private Robot executionner;

    public Tache(Date executionDate){
        this.executionDate = executionDate;
        this.actions = new ArrayList<>();
    }

    public Tache(Date executionDate, Activity activity){
        this.executionDate = executionDate;
        this.actions = new ArrayList<>();
        this.activity = activity;
    }

    public void addAction(Action a){
        this.actions.add(a);
        a.assignToTask(this);
    }

    public void assignRobot(Robot r){
        this.executionner = r;
    }

    public Date getExecutionDate(){
        return this.getExecutionDate();
    }

}
