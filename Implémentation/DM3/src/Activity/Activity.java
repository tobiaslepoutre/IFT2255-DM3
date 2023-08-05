package Activity;

import Machines.Robot;
import Users.Utilisateur;
import java.util.ArrayList;
import java.util.Date;

/**
 * La classe Activity représente une activité dans le système.
 * @author Giovanni Belval
 * @version 1.0
 */
public class Activity{

    /* Attributs*/
    private int reward;
    private Date startDate;
    private Date endDate;
    private String name;

    /* Associations */
    private ArrayList<Utilisateur> participants;
    private Utilisateur createur;
    private ArrayList<Robot> robots;
    private ArrayList<Interet> interets;
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
    public Activity(int reward, Date startDate , Date endDate , String name, Utilisateur createur, Interet interet)  {

        this.reward = reward;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.createur = createur;

        this.participants = new ArrayList<>();
        this.robots = new ArrayList<>();
        this.createur = createur;

        this.interets = new ArrayList<>();
        this.interets.add(interet);
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
    public Date getStartDate(){
        return this.startDate;
    }

    /**
     * Renvoie la date de fin de l'activité.
     *
     * @return La date de fin.
     */
    public Date getEndDate(){
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
    
}
