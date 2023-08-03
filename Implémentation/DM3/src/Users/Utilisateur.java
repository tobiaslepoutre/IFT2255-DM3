package Users;

import Activity.Activity;
import Activity.Interet;
import System.SystemeRobotix;

import java.util.ArrayList;
import java.util.Date;

public class Utilisateur extends Acteur {
    
    /* attributes */
    private String secondName;
    private String pseudo;
    private int    points;
    private ArrayList<String> notifications;

    /* Assosiations */
    private ArrayList<Interet> interets;
    private ArrayList<Activity> createdActivities;
    private ArrayList<Activity> activities;
    //TODO : flotte
    //TODO : composante
    
    /* constructeur */
    public Utilisateur(String compagnieName,String firstName,String secondName, String password, String pseudo,String email ,String phoneNumber) throws Exception{
        super(compagnieName,firstName,password,email,phoneNumber);
        this.setPoints(0);
        this.setPseudo(pseudo);
        this.setSecondName(secondName);

        this.interets = new ArrayList<>();
        this.notifications = new ArrayList<>();

        this.createdActivities = new ArrayList<>();
        this.activities = new ArrayList<>();
    }

    /* Methods*/
    public boolean changePseudo(String pseudo){
        try {
            this.setPseudo(pseudo);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean followUser(String pseudo){
        if(pseudo.equals(this.pseudo)){
            return false;
        }
        return super.followUser(pseudo);
    }

    public void addPoints(int points){
        this.setPoints(this.points + points);
    }

    public int getPoints() {
        return points;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getSecondName() {
        return secondName;
    }

    private void setPoints(int points) {
        this.points = points;
    }

    private void setPseudo(String pseudo) throws Exception {
        for(Utilisateur user : SystemeRobotix.getInstance().getUsers()){
            if(user.getPseudo().equals(pseudo)){
                throw new Exception("This pseudo is already taken");
            }
        }
        this.pseudo = pseudo;
    }

     private void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String toString() {
        return "Utilisateur | Pseudo : " + this.getPseudo() + ", Name : " + this.getFirstName();
    }

    public boolean ajouterInteret(String type, String name){
        if(this.interets.size() == 10){
            return false;
        }

        SystemeRobotix.getInstance().createInteret(type, name);
        this.interets.add(SystemeRobotix.getInstance().getInteret(type,name));
        return true;
    }

    public boolean suppripmerInteret(String name){
        for(Interet i : SystemeRobotix.getInstance().getInterets()){
            if(i.getName().equals(name) && this.interets.contains(i)){
                this.interets.remove(i);
                i.removeInterestedUser(this.getPseudo());
                return true;
            }
        }

        return false;
    }

    public ArrayList<Activity> getCreatedActivities(){
        return this.createdActivities;
    }

    public void addNotification(String notif){
        this.notifications.add(notif);
    }

    public void createActivity(String type, String interetName, String name, Date startDate, Date endDate, int reward){
        // crée une nouvelle activité

        SystemeRobotix.getInstance().createInteret(type,interetName);
        Interet interet = SystemeRobotix.getInstance().getInteret(type, interetName);
        SystemeRobotix.getInstance().createActivity(reward, startDate, endDate, name , this , interet);
        Activity activity = SystemeRobotix.getInstance().getActivity(name);

        this.createdActivities.add(activity);
        this.interets.add(interet);

    }

    public void removeActivity(String activityName){
        Activity toRemove = null;

        for(Activity act :this.createdActivities){
            if(act.getName().equals(activityName)){
                toRemove = act;
                break;
            }
        }

        if(toRemove != null){
            SystemeRobotix.getInstance().removeActivity(toRemove);
            this.createdActivities.remove(toRemove);
        }

    }

    public void createTask(){
        //crée une task et l'ajoute ou non à une activité

    }

}
