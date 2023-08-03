package Activity;

import Activity.action.Action;
import java.util.ArrayList;
import java.util.Date;

public class Tache {
    private Date executionDate;

    /* Assosiations */
    private ArrayList<Action> actions;
    //TODO association to Activity
    //TODO association to robot (Implment Machines Module)

    public Tache(Date executionDate){
        this.executionDate = executionDate;
        this.actions = new ArrayList<>();
    }

    public Date getExecutionDate(){
        return this.getExecutionDate();
    }

}
