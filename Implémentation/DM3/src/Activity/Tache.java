package Activity;

import Activity.action.Action;
import Machines.Robot;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 * La classe Tache représente une tâche à exécuter dans le cadre d'une activité.
 * @author Giovanni Belval
 * @version 1.0
 */
public class Tache {
    private LocalDate executionDate;

    /* Associations */
    private ArrayList<Action> actions;
    private Activity activity;
    private Robot executionner;

    /**
     * Constructeur pour l'objet Tache avec seulement la date d'exécution.
     *
     * @param executionDate La date à laquelle la tâche doit être exécutée.
     */
    public Tache(LocalDate executionDate){
        this.executionDate = executionDate;
        this.actions = new ArrayList<>();
    }

    /**
     * Constructeur pour l'objet Tache avec la date d'exécution et l'activité associée.
     *
     * @param executionDate La date à laquelle la tâche doit être exécutée.
     * @param activity L'activité à laquelle la tâche est associée.
     */
    public Tache(LocalDate executionDate, Activity activity){
        this.executionDate = executionDate;
        this.actions = new ArrayList<>();
        this.activity = activity;
    }

    /**
     * Ajoute une action à la tâche.
     *
     * @param a L'action à ajouter à la tâche.
     */
    public void addAction(Action a){
        this.actions.add(a);
        a.assignToTask(this);
    }

    /**
     * Attribue un robot pour exécuter la tâche.
     *
     * @param r Le robot à affecter à l'exécution de la tâche.
     */
    public void assignRobot(Robot r){
        this.executionner = r;
    }

    /**
     * Renvoie la date d'exécution de la tâche.
     *
     * @return La date d'exécution de la tâche.
     */
    public LocalDate getExecutionDate(){
        return this.getExecutionDate();
    }

    /**
     * Renvoie la liste des actiosn dans une taches
     *
     * @return La listes des actions d'une taches ArrayList<Action>()
     */
    public ArrayList<Action> getActions(){
        return this.actions;
    }

}
