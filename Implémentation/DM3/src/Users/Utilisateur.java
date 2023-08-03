package Users;

import Activity.Interet;
import System.SystemeRobotix;

import java.util.ArrayList;

public class Utilisateur extends Acteur {
    
    /* attributes */
    private String secondName;
    private String pseudo;
    private int    points;

    /* Assosiations */
    private ArrayList<Interet> interets;
    //TODO : activity - crée
    //TODO : activity - participe
    //TODO : flotte
    //TODO : composante
    
    /* constructeur */
    public Utilisateur(String compagnieName,String firstName,String secondName, String password, String pseudo,String email ,String phoneNumber) throws Exception{
        super(compagnieName,firstName,password,email,phoneNumber);
        this.setPoints(0);
        this.setPseudo(pseudo);
        this.setSecondName(secondName);

        this.interets = new ArrayList<>();
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

    public boolean ajouterInteret(String name){
        if(this.interets.size() == 10){
            return false;
        }

        for(Interet i : SystemeRobotix.getInstance().getInterets()){
            if(i.getName().equals(name) && !this.interets.contains(i)){
                this.interets.add(i);
                i.addInterestedUser(this.getPseudo());
                return true;
            }
        }

        Interet i = SystemeRobotix.getInstance().createNewInterest(name);
        i.addInterestedUser(this.getPseudo());
        this.interets.add(i);
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

}
