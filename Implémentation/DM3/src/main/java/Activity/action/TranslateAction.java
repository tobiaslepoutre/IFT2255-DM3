package Activity.action;

import Activity.Tache;

/**
 * La classe TranslateAction étend la classe Action et représente une action de translation.
 * Cette classe a des attributs supplémentaires (x, y, z, speed) qui représentent respectivement
 * les déplacements en x, y, et z, et la vitesse de déplacement.
 * @author Giovanni Belval
 * @version 1.0
 */
public class TranslateAction extends Action {

    private float x;
    private float y;
    private float z;

    private float speed;

    /**
     * Constructeur pour créer un objet TranslateAction avec la durée, une indication si c'est une action de transition,
     * les déplacements en x, y, z et la vitesse de déplacement.
     * Le type d'action est automatiquement défini par le nom de la classe.
     *
     * @param duration La durée de l'action.
     * @param transition Indique si l'action est une transition.
     * @param x Déplacement en x.
     * @param y Déplacement en y.
     * @param z Déplacement en z.
     * @param speed La vitesse de déplacement.
     */
    public TranslateAction(int duration, boolean transition, float x , float y , float z , float speed){
        super(duration, transition);
        this.x = x;
        this.y = y;
        this.z = z;
        this.speed = speed;
        this.type = this.getClass().getSimpleName();
    }

    /**
     * Constructeur pour créer un objet TranslateAction avec la durée, une indication si c'est une action de transition, la tâche associée,
     * les déplacements en x, y, z et la vitesse de déplacement.
     * Le type d'action est automatiquement défini par le nom de la classe.
     *
     * @param duration La durée de l'action.
     * @param transition Indique si l'action est une transition.
     * @param task La tâche associée à l'action.
     * @param x Déplacement en x.
     * @param y Déplacement en y.
     * @param z Déplacement en z.
     * @param speed La vitesse de déplacement.
     */
    public TranslateAction(int duration, boolean transition, Tache task, float x , float y , float z , float speed){
        super(duration, transition, task);
        this.x = x;
        this.y = y;
        this.z = z;
        this.speed = speed;
        this.type = this.getClass().getSimpleName();
    }
}
