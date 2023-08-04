package Activity;

import Machines.Robot;
import Users.Utilisateur;
import java.util.ArrayList;
import java.util.Date;

public class Activity{

    /* attributes*/
    private int reward;
    private Date startDate;
    private Date endDate;
    private String name;

    /* assosiations */
    private ArrayList<Utilisateur> participants;
    private Utilisateur createur;
    private ArrayList<Robot> robots;
    private ArrayList<Interet> interets;
    private ArrayList<Tache> taches;

    /* Constructors */
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

    public void addParticipantRobot(Robot r){
        if(!this.robots.contains(r)){
            this.robots.add(r);
        }
    }

    public void addParticipant(Utilisateur user){
        if(!this.participants.contains(user)){
            this.participants.add(user);
        }
    }

    public void giveReward(){
        for(Utilisateur user : this.participants){
            user.addPoints(this.reward);
        }
    }

    public void notifierDateLimite(){
        for(Utilisateur user : this.participants){
            user.addNotification("the activity "+ this.name + "is ending soon : " + this.endDate);
        }
    }


    public String getName(){
        return this.name;
    }

    public Date getStartDate(){
        return this.startDate;
    }

    public Date getEndDate(){
        return this.endDate;
    }

    public ArrayList<Utilisateur> getParticipants(){
        return this.participants;
    }

    public ArrayList<Robot> getParticipantsRobot(){
        return this.robots;
    }

    public Utilisateur getCreator(){
        return this.createur;
    }




}
