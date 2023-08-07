package Activity.action;

import Activity.Tache;

/**
 * La classe PrintAction étend la classe Action et représente une action d'impression.
 * Cette classe a un attribut supplémentaire "text" qui représente le texte à imprimer.
 * @author Giovanni Belval
 * @version 1.0
 */
public class PrintAction extends Action {

    private String text;

    /**
     * Constructeur pour créer un objet PrintAction avec la durée, une indication si c'est une action de transition et le texte à imprimer.
     * Le type d'action est automatiquement défini par le nom de la classe.
     *
     * @param duration La durée de l'action.
     * @param transition Indique si l'action est une transition.
     * @param text Le texte à imprimer.
     */
    public PrintAction(int duration, boolean transition, String text){
        super(duration, transition);
        this.text = text;
        this.type = this.getClass().getSimpleName();
    }

    /**
     * Constructeur pour créer un objet PrintAction avec la durée, une indication si c'est une action de transition, le texte à imprimer et la tâche associée.
     * Le type d'action est automatiquement défini par le nom de la classe.
     *
     * @param duration La durée de l'action.
     * @param transition Indique si l'action est une transition.
     * @param text Le texte à imprimer.
     * @param task La tâche associée à l'action.
     */
    public PrintAction(int duration, boolean transition, String text, Tache task){
        super(duration, transition, task);
        this.text = text;
        this.type = this.getClass().getSimpleName();
    }
}

