package Activity;

import Users.Utilisateur;
import java.util.ArrayList;
import System.SystemeRobotix;

/**
 * La classe Interet représente un intérêt que les utilisateurs peuvent avoir dans le système.
 * @author Giovanni Belval
 * @version 1.0
 */
public class Interet {

    /* Attributs*/
    public String type;
    public String name;

    /* Associations */
    private ArrayList<Activity> activities;
    private ArrayList<Utilisateur> interestedUsers;

    /**
     * Constructeur pour l'objet Interet.
     *
     * @param type Le type de l'intérêt. Peut-être "CREATION", "EDUCATION", "GAME", ou tout autre string (classé comme "OTHER").
     * @param name Le nom de l'intérêt.
     */
    public Interet(String type, String name){
        if(type.toUpperCase().equals("CREATION") || type.toUpperCase().equals("EDUCATION") || type.toUpperCase().equals("GAME")){
            this.type = type.toUpperCase();
        }
        else{
            this.type = "OTHER";
        }
        this.name = name;
        this.interestedUsers = new ArrayList<>();
        this.activities = new ArrayList<>();
    }

    /**
     * Ajoute un utilisateur intéressé par l'intérêt.
     *
     * @param pseudo Le pseudo de l'utilisateur à ajouter.
     */
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

    /**
     * Supprime un utilisateur intéressé par l'intérêt.
     *
     * @param pseudo Le pseudo de l'utilisateur à supprimer.
     */
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

    /**
     * Renvoie la liste des activités associées à l'intérêt.
     *
     * @return La liste des activités.
     */
    public ArrayList<Activity> getActivities(){
        return this.activities;
    }

    /**
     * Supprime une activité associée à l'intérêt.
     *
     * @param activity L'activité à supprimer.
     */
    public void removeRelatedActivity(Activity activity){
        this.activities.remove(activity);
    }

    /**
     * Ajoute une activité associée à l'intérêt.
     *
     * @param activity L'activité à ajouter.
     */
    public void addRelatedActivity(Activity activity){
        this.activities.add(activity);
    }

    /**
     * Renvoie le nom de l'intérêt.
     *
     * @return Le nom de l'intérêt.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Renvoie le type de l'intérêt.
     *
     * @return Le type de l'intérêt.
     */
    public String getType(){
        return this.type;
    }

    /**
     * Renvoie la liste des utilisateurs intéressés par l'intérêt.
     *
     * @return La liste des utilisateurs.
     */
    public ArrayList<Utilisateur> getInterestedUsers(){
        return this.interestedUsers;
    }
}
