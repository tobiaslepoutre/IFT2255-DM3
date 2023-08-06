package Activity;

import Activity.action.Action;
import Machines.Robot;
import Users.Utilisateur;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * La classe Activity représente une activité dans le système.
 * @author Giovanni Belval
 * @version 1.0
 */
public class Activity{

    /* Attributs*/
    private int reward;
    private LocalDate startDate;
    private LocalDate endDate;
    private String name;

    /* Associations */
    private ArrayList<Utilisateur> participants;
    private Utilisateur createur;
    private ArrayList<Robot> robots;
    private Interet interet;
    private ArrayList<Tache> taches;

    /**
     * Constructeur pour l'objet Activity.
     *
     * @param reward Le gain pour l'activité.
     * @param startDate La date de début de l'activité.
     * @param endDate La date de fin de l'activité.
     * @param name Le nom de l'activité.
     * @param createur Le créateur de l'activité.
     * @param interet L'interet lié à l'activité.
     */
    public Activity(int reward, LocalDate startDate , LocalDate endDate , String name, Utilisateur createur, Interet interet)  {

        this.reward = reward;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.createur = createur;

        this.participants = new ArrayList<>();
        this.robots = new ArrayList<>();
        this.createur = createur;

        this.interet = interet;
        interet.addRelatedActivity(this);

        this.taches = new ArrayList<>();

    }

    /**
     * Ajoute un robot participant à l'activité.
     *
     * @param r Le robot à ajouter.
     */
    public void addParticipantRobot(Robot r){
        if(!this.robots.contains(r)){
            this.robots.add(r);
        }
    }


    /**
     * Ajoute un utilisateur participant à l'activité.
     *
     * @param user L'utilisateur à ajouter.
     */
    public void addParticipant(Utilisateur user){
        if(!this.participants.contains(user)){
            this.participants.add(user);
        }
    }

    /**
     * Attribue les récompenses aux participants de l'activité.
     */
    public void giveReward(){
        for(Utilisateur user : this.participants){
            user.addPoints(this.reward);
        }
    }

    /**
     * Notifie tous les participants que l'activité se termine bientôt.
     */
    public void notifierDateLimite(){
        for(Utilisateur user : this.participants){
            user.addNotification("the activity "+ this.name + "is ending soon : " + this.endDate);
        }
    }

    /**
     * Renvoie le nom de l'activité.
     *
     * @return Le nom de l'activité.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Renvoie la date de début de l'activité.
     *
     * @return La date de début.
     */
    public LocalDate getStartDate(){
        return this.startDate;
    }

    /**
     * Renvoi le nombre de point offert pour la complétion de l'activité
     *
     * @Return reward (int)
     */
    public int getReward(){
        return this.reward;
    }

    /**
     * Renvoie la date de fin de l'activité.
     *
     * @return La date de fin.
     */
    public LocalDate getEndDate(){
        return this.endDate;
    }

    /**
     * Renvoie la liste des utilisateurs participants à l'activité.
     *
     * @return La liste des utilisateurs participants.
     */
    public ArrayList<Utilisateur> getParticipants(){
        return this.participants;
    }

    /**
     * Renvoie la liste des robots participants à l'activité.
     *
     * @return La liste des robots participants.
     */
    public ArrayList<Robot> getParticipantsRobot(){
        return this.robots;
    }

    /**
     * Renvoie le créateur de l'activité.
     *
     * @return Le créateur de l'activité.
     */
    public Utilisateur getCreator(){
        return this.createur;
    }

    /**
     * Ajoute une action a la liste des actins
     *
     * @Retun void
     */
    public void addTache(Tache t){
        this.taches.add(t);
    }

    /**
     * Imprime les le nom de l'activité et toutes les informations associées
     *
     * @Return void
     */
    public void showActivityDetails(){
        System.out.println("nom de l'activité : " + this.getName() + " , date de début : "+ this.getStartDate() + " , date de fin : "+ this.getEndDate());

        System.out.println("participants : ");
        for(Robot r : this.robots){
            System.out.print("• pseudo : " + r.getOwner().getPseudo() + " , nom du robot : " + r.getName());
        }
        System.out.println("");

        System.out.println("Taches associées : ");
        int c = 0;
        for(Tache t : this.taches){
            System.out.print("• tache "+ (++c) + " : ");

            for(Action a : t.getActions()){
                System.out.print(a.getType() + ", ");
            }
        }
        System.out.println("");

    }

    /**
     *
     *
     *
     */
    public ArrayList<Tache> getTasks(){
        return this.taches;
    }

    /**
     *
     *
     *
     */
    public Interet getInteret(){
        return this.interet;
    }

}
