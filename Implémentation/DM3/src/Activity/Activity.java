package Activity;

import Users.Utilisateur;
import java.util.ArrayList;
import java.util.Date;

public class Activity {

    /* attributes*/
    private int reward;
    private Date startDate;
    private Date endDate;
    private String name;

    /* assosiations */
    private ArrayList<Utilisateur> participants;
    private Utilisateur createur;
    //TODO : Robot
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
        this.createur = createur;

        this.interets = new ArrayList<>();
        this.interets.add(interet);
        interet.addRelatedActivity(this);

        this.taches = new ArrayList<>();

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




}
