package Users;

import java.util.ArrayList;
import System.SystemeRobotix;

public class Fournisseur extends Acteur {
    
    /* attributes */
    private String location;

    /* constructeur */
    public Fournisseur(){
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.systeme   = SystemeRobotix.getInstance();
    }
}
