package Activity;

import Users.Utilisateur;
import java.util.ArrayList;
import System.SystemeRobotix;

public class Interet {

    /* attributes*/
    public String type;
    public String name;

    /* assosiations */
    private ArrayList<Activity> activities;
    private ArrayList<Utilisateur> interestedUsers;


    public Interet(String type, String name){
        if(type.equals("CREATION") || type.equals("EDUCATION") || type.equals("GAME")){
            this.type = type;
        }
        else{
            this.type = "OTHER";
        }

        this.name = name;
        this.interestedUsers = new ArrayList<>();
        this.activities = new ArrayList<>();

    }

    public void addInterestedUser(String pseudo){
        for(Utilisateur user : SystemeRobotix.getInstance().getUsers()){
            if(!user.getPseudo().equals(pseudo)){
                continue;
            }

            if(!this.interestedUsers.contains(user)){
                this.interestedUsers.add(user);
                return;
            }
        }
    }

    public void removeInterestedUser(String pseudo){
        for(Utilisateur user : SystemeRobotix.getInstance().getUsers()){
            if(!user.getPseudo().equals(pseudo)){
                continue;
            }

            if(this.interestedUsers.contains(user)){
                this.interestedUsers.remove(user);
                return;
            }
        }
    }

    public ArrayList<Activity> getActivities(){
        return this.activities;
    }

    public void removeRelatedActivity(Activity activity){
        this.activities.remove(activity);
    }

    public void addRelatedActivity(Activity activity){
        this.activities.add(activity);
    }

    public String getName(){
        return this.name;
    }

    public String getType(){
        return this.type;
    }
}
