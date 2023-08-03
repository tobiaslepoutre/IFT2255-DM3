package Activity.action;

import Activity.Tache;

public class PrintAction extends Action {

    private String text;

    public PrintAction(int duration, boolean transition, String text){
        super(duration, transition);
        this.text = text;
        this.type = this.getClass().getSimpleName();
    }

    public PrintAction(int duration, boolean transition, String text, Tache task){
        super(duration, transition, task);
        this.text = text;
        this.type = this.getClass().getSimpleName();
    }
}
