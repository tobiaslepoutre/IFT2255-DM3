import Users.Fournisseur;
import Users.Utilisateur;
import System.SystemeRobotix;

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
        user1.followUser(user2.getPseudo());
        user3.followUser(user2.getPseudo());

        user1.followSeller(seller1.getFirstName());
        user2.followSeller(seller1.getFirstName());
        seller1.followUser(user1.getPseudo());

        system.showSeller(seller1.getFirstName());
    }
}
