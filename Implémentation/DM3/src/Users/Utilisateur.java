package Users;

import System.SystemeRobotix;

public class Utilisateur extends Acteur {
    
    /* attributes */
    private String secondName;
    private String pseudo;
    private int    points;

    /* Assosiations */
    //interet
    //activity - crée
    //activity - participe
    //flotte
    //composante
    
    /* constructeur */
    public Utilisateur(String compagnieName,String firstName,String secondName, String password, String pseudo,String email ,String phoneNumber) throws Exception{
        super(compagnieName,firstName,password,email,phoneNumber);
        this.setPoints(0);
        this.setPseudo(pseudo);
        this.setSecondName(secondName);
    }

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

}
