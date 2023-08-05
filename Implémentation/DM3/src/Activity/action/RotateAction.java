package Activity.action;

import Activity.Tache;

/**
 * La classe RotateAction étend la classe Action et représente une action de rotation.
 * Cette classe a des attributs supplémentaires (rx, ry, rz, speed) qui représentent respectivement les
 * degrés de rotation
 * autour des axes x, y, et z, et la vitesse de rotation.
 * @author Giovanni Belval
 * @version 1.0
 */
public class RotateAction extends Action {
    private float rx;
    private float ry;
    private float rz;
    private float speed;

    /**
     * Constructeur pour créer un objet RotateAction avec la durée, une indication si c'est une action de transition,
     * les degrés de rotation autour des axes x, y, z et la vitesse de rotation.
     * Le type d'action est automatiquement défini par le nom de la classe.
     *
     * @param duration La durée de l'action.
     * @param transition Indique si l'action est une transition.
     * @param rx Degré de rotation autour de l'axe x.
     * @param ry Degré de rotation autour de l'axe y.
     * @param rz Degré de rotation autour de l'axe z.
     * @param speed La vitesse de rotation.
     */
    public RotateAction(int duration, boolean transition, float rx , float ry , float rz , float speed){
        super(duration, transition);
        this.rx = rx;
        this.ry = ry;
        this.rz = rz;
        this.speed = speed;
        this.type = this.getClass().getSimpleName();
    }

    /**
     * Constructeur pour créer un objet RotateAction avec la durée, une indication si c'est une action de transition, la tâche associée,
     * les degrés de rotation autour des axes x, y, z et la vitesse de rotation.
     * Le type d'action est automatiquement défini par le nom de la classe.
     *
     * @param duration La durée de l'action.
     * @param transition Indique si l'action est une transition.
     * @param task La tâche associée à l'action.
     * @param rx Degré de rotation autour de l'axe x.
     * @param ry Degré de rotation autour de l'axe y.
     * @param rz Degré de rotation autour de l'axe z.
     * @param speed La vitesse de rotation.
     */
    public RotateAction(int duration, boolean transition, Tache task, float rx , float ry , float rz , float speed){
        super(duration, transition, task);
        this.rx = rx;
        this.ry = ry;
        this.rz = rz;
        this.speed = speed;
        this.type = this.getClass().getSimpleName();
    }
}
