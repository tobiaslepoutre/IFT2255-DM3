package Activity.action;

import Activity.Tache;

import java.time.LocalDate;

/**
 * La classe TriggerAction étend la classe Action et représente une action déclenchée.
 * Cette classe a un attribut supplémentaire (executionDate) qui représente la date d'exécution de l'action.
 * @author Giovanni Belval
 * @version 1.0
 */
public class TriggerAction extends Action {

    private LocalDate executionDate;


    /**
     * Constructeur pour créer un objet TriggerAction avec la durée et la date d'exécution.
     * Le type d'action est automatiquement défini par le nom de la classe.
     * Notez que l'action n'est pas une transition (false est passé comme argument au super-constructeur).
     *
     * @param duration La durée de l'action.
     * @param executionDate La date d'exécution de l'action.
     */
    public TriggerAction(int duration , LocalDate executionDate){
        super(duration, false);
        this.executionDate = executionDate;
        this.type = this.getClass().getSimpleName();
    }

    /**
     * Constructeur pour créer un objet TriggerAction avec la durée, la tâche associée et la date d'exécution.
     * Le type d'action est automatiquement défini par le nom de la classe.
     * Notez que l'action n'est pas une transition (false est passé comme argument au super-constructeur).
     *
     * @param duration La durée de l'action.
     * @param task La tâche associée à l'action.
     * @param executionDate La date d'exécution de l'action.
     */
    public TriggerAction(int duration , Tache task, LocalDate executionDate){
        super(duration, false, task);
        this.executionDate = executionDate;
        this.type = this.getClass().getSimpleName();
    }
}

