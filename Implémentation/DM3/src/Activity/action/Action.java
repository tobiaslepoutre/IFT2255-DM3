package Activity.action;

import Activity.Tache;

public abstract class Action {

    protected String type;
    private boolean transition;
    private int duration;

    //assosiations
    private Tache task;

    public Action(int duration , boolean transition){
        this.duration = duration;
        this.transition = transition;
    }

    public Action(int duration , boolean transition , Tache task){
        this.duration = duration;
        this.transition = transition;
        this.task = task;
    }

    public String getType(){
        return this.type;
    }

    public boolean getTransition(){
        return this.transition;
    }

    public int getDuration(){
        return this.duration;
    }

    public void assignToTask(Tache t){
        this.task = t;
    }

}
