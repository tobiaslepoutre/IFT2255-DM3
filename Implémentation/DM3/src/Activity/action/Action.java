package Activity.action;

import Activity.Tache;

/**
 * La classe Action est une classe abstraite qui définit les actions à entreprendre.
 * @author Giovanni Belval
 * @version 1.0
 */
public abstract class Action {

    protected String type;
    private boolean transition;
    private int duration;

    // Association
    private Tache task;

    /**
     * Constructeur pour l'objet Action avec la durée et une indication si c'est une action de transition.
     *
     * @param duration La durée de l'action.
     * @param transition Indique si l'action est une transition.
     */
    public Action(int duration , boolean transition){
        this.duration = duration;
        this.transition = transition;
    }

    /**
     * Constructeur pour l'objet Action avec la durée, une indication si c'est une action de transition et la tâche associée.
     *
     * @param duration La durée de l'action.
     * @param transition Indique si l'action est une transition.
     * @param task La tâche associée à l'action.
     */
    public Action(int duration , boolean transition , Tache task){
        this.duration = duration;
        this.transition = transition;
        this.task = task;
    }

    /**
     * Renvoie le type de l'action.
     *
     * @return Le type de l'action.
     */
    public String getType(){
        return this.type;
    }

    /**
     * Indique si l'action est une transition.
     *
     * @return Vrai si l'action est une transition, faux sinon.
     */
    public boolean getTransition(){
        return this.transition;
    }
    
    /**
     * Renvoie la durée de l'action.
     *
     * @return La durée de l'action.
     */
    public int getDuration(){
        return this.duration;
    }

    /**
     * Attribue une tâche à l'action.
     *
     * @param t La tâche à attribuer à l'action.
     */
    public void assignToTask(Tache t){
        this.task = t;
    }

}
