package Activity.action;

import Activity.Tache;

import java.util.Date;

public class TriggerAction extends Action {

    private Date executionDate;

    public TriggerAction(int duration , Date executionDate){
        super(duration, false);
        this.executionDate = executionDate;
        this.type = this.getClass().getSimpleName();
    }

    public TriggerAction(int duration , Tache task, Date executionDate){
        super(duration, false, task);
        this.executionDate = executionDate;
        this.type = this.getClass().getSimpleName();
    }
}

