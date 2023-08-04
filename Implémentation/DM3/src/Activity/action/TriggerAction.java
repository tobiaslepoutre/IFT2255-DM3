package Activity.action;

import Activity.Tache;

import java.util.Date;

public class TriggerAction extends Action {

    private int executionDate;

    public TriggerAction(int duration , int executionDate){
        super(duration, false);
        this.executionDate = executionDate;
        this.type = this.getClass().getSimpleName();
    }

    public TriggerAction(int duration , Tache task, int executionDate){
        super(duration, false, task);
        this.executionDate = executionDate;
        this.type = this.getClass().getSimpleName();
    }
}

