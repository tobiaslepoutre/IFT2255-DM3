package Activity.action;

import Activity.Tache;

/**
 * La classe EmitSoundAction étend la classe Action et représente une action qui émet un son.
 * Cette classe contient des informations sur le type de son à émettre.
 * @author Giovanni Belval
 * @version 1.0
 */
public class EmitSoundAction extends Action {
    private String soundType;

    /**
     * Constructeur pour créer un objet EmitSoundAction avec la durée, une indication si c'est une action de transition et le type de son.
     * Le type d'action est automatiquement défini par le nom de la classe.
     *
     * @param duration La durée de l'action.
     * @param transition Indique si l'action est une transition.
     * @param soundType Le type de son à émettre.
     */
    public EmitSoundAction(int duration, boolean transition, String soundType){
        super(duration, transition);
        this.soundType = soundType;
        this.type = this.getClass().getSimpleName();
    }

    /**
     * Constructeur pour créer un objet EmitSoundAction avec la durée, une indication si c'est une action de transition, la tâche associée et le type de son.
     * Le type d'action est automatiquement défini par le nom de la classe.
     *
     * @param duration La durée de l'action.
     * @param transition Indique si l'action est une transition.
     * @param task La tâche associée à l'action.
     * @param soundType Le type de son à émettre.
     */
    public EmitSoundAction(int duration, boolean transition, Tache task, String soundType){
        super(duration, transition, task);
        this.soundType = soundType;
        this.type = this.getClass().getSimpleName();
    }
}
