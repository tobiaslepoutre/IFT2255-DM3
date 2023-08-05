package Activity.action;

import Activity.Tache;

/**
 * La classe ListenAction étend la classe Action et représente une action d'écoute.
 * Cette classe n'a pas d'attributs supplémentaires en dehors de ceux de la classe Action.
 * @author Giovanni Belval
 * @version 1.0
 */
public class ListenAction extends Action {

    /**
     * Constructeur pour créer un objet ListenAction avec la durée et une indication si c'est une action de transition.
     * Le type d'action est automatiquement défini par le nom de la classe.
     *
     * @param duration La durée de l'action.
     * @param transition Indique si l'action est une transition.
     */
    public ListenAction(int duration , boolean transition){
        super(duration, transition);
        this.type = this.getClass().getSimpleName();
    }

    /**
     * Constructeur pour créer un objet ListenAction avec la durée, une indication si c'est une action de transition et la tâche associée.
     * Le type d'action est automatiquement défini par le nom de la classe.
     *
     * @param duration La durée de l'action.
     * @param transition Indique si l'action est une transition.
     * @param task La tâche associée à l'action.
     */
    public ListenAction(int duration , boolean transition, Tache task){
        super(duration, transition, task);
        this.type = this.getClass().getSimpleName();
    }
}
