import Activity.*;
import Users.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import System.SystemeRobotix;
import org.junit.Test;

public class TestIntegration {

    private SystemeRobotix system;

    private Utilisateur user1;
    private Utilisateur user2;

    private Fournisseur seller1;
    private Fournisseur seller2;

    @Before
    public void setUp(){
        system = SystemeRobotix.getInstance();
        system.signUpUser(null, "Giovanni", "Belval", "gio@b.com", "belgio", "123456", "1234567890");
        system.signUpUser(null, "Tobias", "Lepoutre", "tob@g.com", "toblep", "123456", "1234567890");

        system.signUpSeller("Google", "Theo", "t@gmail.com", "123456","1234567890","1234 rue de Paris",100);
        system.signUpSeller("Google", "Victor", "v@gmail.com", "123456","1234567890","1234 rue de Paris",100);

        user1 = system.loginUser("belgio", "123456");
        user2 = system.loginUser("toblep", "123456");

        seller1 = system.loginSeller("Theo","123456");
        seller2 = system.loginSeller("Victor","123456");

    }

    @After
    public void reset(){
        SystemeRobotix.destroy();
    }

    @Test
    public void TestIntegrationActivite1(){

        Assert.assertTrue(user1.createActivity("CREATION", "physique", "fusée", new Date(), new Date(), 10));

        Assert.assertTrue(user1.getCreatedActivities().size() == 1);
        Assert.assertTrue(user2.getCreatedActivities().isEmpty());


        Assert.assertTrue(SystemeRobotix.getInstance().getActivities().size() == 1);
        Assert.assertTrue(system.getActivity("fusée") == system.getActivities().get(0));
        Activity a = system.getActivity(user1.getCreatedActivities().get(0).getName());
        Assert.assertTrue(a.getCreator() == user1);
        Assert.assertTrue(system.getInterets().size() == 1);
        Assert.assertTrue(system.getInterets().get(0).getName().equals("physique"));
        Interet i = system.getInterets().get(0);
        Assert.assertTrue(i.getInterestedUsers().size() == 1);
        Assert.assertTrue(i.getInterestedUsers().get(0) == user1);

        Assert.assertFalse(user1.createActivity("CREATION", "physique", "fusée", new Date(), new Date(), 10));
        Assert.assertFalse(system.getActivities().size() == 2);

        Assert.assertFalse(user2.createActivity("CREATION", "biologie", "fusée", new Date(), new Date(), 10));


        Assert.assertTrue(user2.createActivity("CREATION", "biologie", "molecule", new Date(), new Date(), 5));
        Assert.assertTrue(system.getInterets().size() == 2);
        Assert.assertTrue(system.getInterets().contains(user2.getInterets().get(0)));

    }

    @Test
    public void TestIntegrationActivite2(){

        Assert.assertTrue(user1.createActivity("CREATION", "physique", "fusée", new Date(), new Date(), 10));
        Assert.assertTrue(user2.createActivity("CREATION", "physique", "voiture", new Date(), new Date(), 10));

    }


}
