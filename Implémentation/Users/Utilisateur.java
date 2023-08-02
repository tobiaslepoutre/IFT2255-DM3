package Users;

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
    public Utilisateur(String compagnieName,String firstName,String secondName, String password, String pseudo,String email ,String phoneNumber){
        super(compagnieName,firstName,password,email,phoneNumber);
        this.setPoints(0);
        this.setPseudo(pseudo);
        this.setSecondName(secondName);
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

    private void setPseudo(String pseudo) {
        //TODO : check if the pseudo is unique.
        this.pseudo = pseudo;
    }

     private void setSecondName(String secondName) {
        this.secondName = secondName;
    }

}
