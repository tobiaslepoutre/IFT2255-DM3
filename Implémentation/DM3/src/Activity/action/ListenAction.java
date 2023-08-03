package Activity.action;

import Activity.Tache;

public class ListenAction extends Action {

    public ListenAction(int duration , boolean transition){
        super(duration, transition);
        this.type = this.getClass().getSimpleName();
    }

    public ListenAction(int duration , boolean transition, Tache task){
        super(duration, transition, task);
        this.type = this.getClass().getSimpleName();
    }
}
