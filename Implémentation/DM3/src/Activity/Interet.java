package Activity;

import Users.Utilisateur;
import java.util.ArrayList;
import System.SystemeRobotix;

public class Interet {

    /* attributes*/
    public String type;
    public String name;

    /* assosiations */
    //TODO : assosiation to activity (implement activity)
    private ArrayList<Utilisateur> interestedUsers;


    public Interet(String type, String name){
        if(!type.equals("CREATION") && !type.equals("EDUCATION") && !type.equals("GAME")){
            this.type = "OTHER";
        }

        this.name = name;
        this.interestedUsers = new ArrayList<>();
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

    public String getName(){
        return this.name;
    }
}
