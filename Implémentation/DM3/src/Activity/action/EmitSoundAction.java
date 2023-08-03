package Activity.action;

import Activity.Tache;

public class EmitSoundAction extends Action {
    private String soundType;

    public EmitSoundAction(int duration, boolean transition, String soundType){
        super(duration, transition);
        this.soundType = soundType;
        this.type = this.getClass().getSimpleName();
    }

    public EmitSoundAction(int duration, boolean transition, Tache task, String soundType){
        super(duration, transition, task);
        this.soundType = soundType;
        this.type = this.getClass().getSimpleName();
    }
}
