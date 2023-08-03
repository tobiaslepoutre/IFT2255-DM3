package Activity.action;

import Activity.Tache;

public class TranslateAction extends Action {

    private float x;
    private float y;
    private float z;

    private float speed;

    public TranslateAction(int duration, boolean transition, float x , float y , float z , float speed){
        super(duration, transition);
        this.x = x;
        this.y = y;
        this.z = z;
        this.speed = speed;
        this.type = this.getClass().getSimpleName();
    }

    public TranslateAction(int duration, boolean transition, Tache task, float x , float y , float z , float speed){
        super(duration, transition, task);
        this.x = x;
        this.y = y;
        this.z = z;
        this.speed = speed;
        this.type = this.getClass().getSimpleName();
    }
}
