package Activity.action;

import Activity.Tache;

public class RotateAction extends Action {
    private float rx;
    private float ry;
    private float rz;
    private float speed;

    public RotateAction(int duration, boolean transition, float rx , float ry , float rz , float speed){
        super(duration, transition);
        this.rx = rx;
        this.ry = ry;
        this.rz = rz;
        this.speed = speed;
        this.type = this.getClass().getSimpleName();
    }

    public RotateAction(int duration, boolean transition, Tache task, float rx , float ry , float rz , float speed){
        super(duration, transition, task);
        this.rx = rx;
        this.ry = ry;
        this.rz = rz;
        this.speed = speed;
        this.type = this.getClass().getSimpleName();
    }
}
