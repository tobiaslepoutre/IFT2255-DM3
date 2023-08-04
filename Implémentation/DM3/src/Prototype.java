import Users.Fournisseur;
import Users.Utilisateur;
import System.SystemeRobotix;

import java.util.Date;

public class Prototype {

    private static SystemeRobotix system;
    private static  Utilisateur user1;
    private static Utilisateur user2;
    private static Utilisateur user3;

    private static  Fournisseur seller1;
    private static  Fournisseur seller2;

    public static void main(String[] args) {
        system = SystemeRobotix.getInstance();
        system.signUpUser(null, "Giovanni", "Belval", "gio@b.com", "belgio", "123456", "1234567890");
        system.signUpUser(null, "Tobias", "Lepoutre", "tob@g.com", "toblep", "123456", "1234567890");
        system.signUpUser(null, "test1", "Lepoutre", "tob2@g.com", "test1", "123456", "1234567890");

        system.signUpSeller("Google", "Theo", "t@gmail.com", "123456","1234567890","1234 rue de Paris",100);
        system.signUpSeller("Google", "Victor", "v@gmail.com", "123456","1234567890","1234 rue de Paris",100);

        user1 = system.loginUser("belgio", "123456");
        user2 = system.loginUser("toblep", "123456");
        user3 = system.loginUser("test1", "123456");

        seller1 = system.loginSeller("Theo","123456");
        seller2 = system.loginSeller("Victor","123456");


        /* ------ */
        //system.showAllUsers();

        user1.createActivity("CREATION","voiture","course",0 , 10, 50);
        user1.createActivity("CREATION","voiture","navigation",2 , 11, 100);
        user2.ajouterInteret("CREATION", "avion");
        user1.removeActivity("course");
        user2.removeActivity("navigation");
    }
}
