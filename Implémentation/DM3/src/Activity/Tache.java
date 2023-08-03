package Activity;

import Activity.action.Action;
import java.util.ArrayList;
import java.util.Date;

public class Tache {
    private Date executionDate;

    /* Assosiations */
    private ArrayList<Action> actions;
    private Activity activity;
    //TODO association to robot (Implment Machines Module)

    public Tache(Date executionDate){
        this.executionDate = executionDate;
        this.actions = new ArrayList<>();
    }

    public Tache(Date executionDate, Activity activity){
        this.executionDate = executionDate;
        this.actions = new ArrayList<>();
        this.activity = activity;
    }

    public Date getExecutionDate(){
        return this.getExecutionDate();
    }

}
