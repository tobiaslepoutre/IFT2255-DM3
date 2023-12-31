package Users;

import org.junit.*;
import java.util.Calendar;
import java.time.LocalDate;

import System.SystemeRobotix;
import Users.Utilisateur;

public class TestUtilisateur {

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
    public void TestChangeEmail(){

        Assert.assertEquals("gio@b.com", user1.getEmail());
        Assert.assertEquals("tob@g.com", user2.getEmail());

        user1.changeEmail("new@gmail.com");
        Assert.assertEquals(user1.getEmail(),"new@gmail.com");

        Assert.assertFalse(user2.changeEmail(user1.getEmail()));
        Assert.assertTrue(user2.changeEmail("tob2@g.com"));

    }

    @Test
    public void TestChangePseudo(){
        Assert.assertEquals("belgio",user1.getPseudo());
        Assert.assertTrue(user1.changePseudo("belgio2"));

        Assert.assertEquals("toblep", user2.getPseudo());
        Assert.assertFalse(user1.changePseudo(user2.getPseudo()));
    }

    @Test
    public void TestChangePassword(){
        Assert.assertEquals("belgio",user1.getPseudo());
        Assert.assertEquals("toblep", user2.getPseudo());

        Assert.assertFalse(user1.changePassword("12345","12345"));
        Assert.assertFalse(user1.changePassword("123457","12345"));
        Assert.assertFalse(user1.changePassword("wrongPassword","12345"));

        Assert.assertTrue(user1.changePassword("123456","newPassword"));


    }

    @Test
    public void TestFollowUser(){
        Assert.assertEquals("belgio",user1.getPseudo());
        Assert.assertEquals("toblep", user2.getPseudo());

        Assert.assertFalse(user1.followUser(user1.getPseudo()));

        Assert.assertTrue(user1.followUser(user2.getPseudo()));
        Assert.assertFalse(user1.followUser(user2.getPseudo()));
        Assert.assertTrue(user2.isFollowedByUser(user1.getPseudo()));

        Assert.assertTrue(user2.followUser(user1.getPseudo()));
        Assert.assertTrue(user1.isFollowedByUser(user2.getPseudo()));

    }

    @Test
    public void TestFollowSeller(){
        Assert.assertEquals("Theo",seller1.getFirstName());
        Assert.assertEquals("Victor",seller2.getFirstName());

        Assert.assertTrue(user1.followSeller(seller1.getFirstName()));
        Assert.assertTrue(seller1.isFollowedByUser(user1.getPseudo()));

        Assert.assertTrue(user2.followSeller(seller1.getFirstName()));
        Assert.assertTrue(user2.followSeller(seller2.getFirstName()));

        Assert.assertTrue(seller1.isFollowedByUser(user2.getPseudo()));
        Assert.assertTrue(seller2.isFollowedByUser(user2.getPseudo()));


    }

    @Test
    public void TestBuyComposante(){
        Assert.assertTrue(seller1.getComposantes().isEmpty());

        seller1.createComposante("CPU", "RX900" , "the fastest CPU", 20);
        Assert.assertTrue(seller1.getComposantes().size() == 1);

        seller1.createComposante("CPU", "RX899" , "the second fastest CPU", 20);
        Assert.assertTrue(seller1.getComposantes().size() == 2);

        Assert.assertTrue(user1.buyComposante(seller1 , "CPU"));
        Assert.assertTrue(seller1.getComposantes().size() == 1);

        Assert.assertTrue(user1.getComposantes().size() == 1);
        Assert.assertTrue(user1.getComposantes().get(0).getSeller() == seller1);
        Assert.assertTrue(user1.getComposantes().get(0).getName().equals("RX900"));

        Assert.assertTrue(user1.buyComposante(seller1 , "CPU"));
        Assert.assertTrue(seller1.getComposantes().isEmpty());
        Assert.assertTrue(user1.getComposantes().get(1).getSeller() == seller1);
        Assert.assertTrue(user1.getComposantes().get(1).getName().equals("RX899"));

    }

    @Test
    public void TestCreateRobot(){
        seller1.createComposante("CPU", "RX900" , "the fastest CPU", 20);
        seller1.createComposante("bras", "mechaArm" , "", 20);
        seller1.createComposante("helice", "Ihelice" , "", 20);
        seller1.createComposante("ecran", "Iscreen" , "", 20);
        seller1.createComposante("bras", "mechaArm" , "", 20);
        Assert.assertTrue(seller1.getComposantes().size() == 5);

        Assert.assertTrue(user1.buyComposante(seller1 , "CPU"));
        Assert.assertTrue(user1.buyComposante(seller1 , "bras"));
        Assert.assertTrue(user1.buyComposante(seller1 , "bras"));
        Assert.assertTrue(user1.buyComposante(seller1 , "ecran"));
        Assert.assertTrue(user1.buyComposante(seller1 , "helice"));

        Assert.assertTrue(seller1.getComposantes().isEmpty());

        Assert.assertTrue(user1.createRobot("GPT","1234", "mover",user1.getComposantes()));
        Assert.assertTrue(seller1.getComposantes().size() == 0);
        Assert.assertTrue(user1.getFlotte().getRobots().size() == 1);

    }

    @Test
    public void TestCreateActivity(){
        Assert.assertTrue(user1.createActivity("CREATION", "physique", "fusée", LocalDate.now(), LocalDate.now(), 10));
        Assert.assertTrue(user2.createActivity("CREATION", "physique", "voiture", LocalDate.now(), LocalDate.now(), 10));

        Assert.assertTrue(system.getActivities().size() == 2);
        Assert.assertTrue(system.getInterets().size() == 1);
        Assert.assertTrue(system.getInterets().contains(user1.getInterets().get(0)));
        Assert.assertTrue(system.getInterets().contains(user2.getInterets().get(0)));
        Assert.assertTrue(user1.getInterets().get(0) == user2.getInterets().get(0));

        Assert.assertFalse(user1.createActivity("EDUCATION", "physique", "voiture", LocalDate.now(), LocalDate.now(), 10));

        Assert.assertTrue(user1.createActivity("CREATION", "afro", "music", LocalDate.now(), LocalDate.now(), 10));
        Assert.assertTrue(system.getActivities().size() == 3);
        Assert.assertTrue(system.getInterets().size() == 2);
        Assert.assertTrue(system.getInteret("CREATION", "afro") == user1.getInterets().get(1));

        Assert.assertTrue(user1.createActivity("EDUCATION", "physique", "avion", LocalDate.now(), LocalDate.now(), 10));
        Assert.assertTrue(system.getActivities().size() == 4);
        Assert.assertTrue(system.getInterets().size() == 3);
    }

    @Test
    public void TestAssignRobotToActivity(){

                //Ce code sert à simuler les paramètres startDate et endDate utilisés par createActivity() et isBusy()
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(10);
        
        Assert.assertTrue(user1.createActivity("CREATION", "physique", "fusée", startDate, endDate, 10));
        Assert.assertTrue(user2.createActivity("CREATION", "physique", "voiture", startDate, endDate, 10));

        seller1.createComposante("CPU", "RX900" , "the fastest CPU", 20);
        seller2.createComposante("bras", "mechaArm" , "", 20);
        seller1.createComposante("helice", "Ihelice" , "", 20);
        seller2.createComposante("ecran", "Iscreen" , "", 20);
        seller1.createComposante("bras", "mechaArm" , "", 20);
        Assert.assertTrue(seller1.getComposantes().size() == 3);
        Assert.assertTrue(seller2.getComposantes().size() == 2);

        Assert.assertTrue(user1.buyComposante(seller1 , "CPU"));
        Assert.assertTrue(user1.buyComposante(seller2 , "bras"));
        Assert.assertTrue(user1.buyComposante(seller1 , "helice"));
        Assert.assertTrue(user1.buyComposante(seller1 , "bras"));
        Assert.assertTrue(user1.buyComposante(seller2 , "ecran"));

        Assert.assertFalse(user1.buyComposante(seller2 , "bras"));
        Assert.assertFalse(user1.buyComposante(seller1 , "ecran"));
        Assert.assertFalse(user1.buyComposante(seller1 , "CPU"));
        Assert.assertFalse(user1.buyComposante(seller2 , "bras"));
        Assert.assertFalse(user1.buyComposante(seller2 , "helice"));

        Assert.assertTrue(seller1.getComposantes().isEmpty());
        Assert.assertTrue(seller2.getComposantes().isEmpty());

        Assert.assertTrue(user1.createRobot("GPT","1234", "mover",user1.getComposantes()));
        Assert.assertTrue(user1.getFlotte().getRobots().size() == 1);

        Assert.assertTrue(user1.assignRobotToActivity("voiture"));
        Assert.assertTrue(system.getActivity("voiture").getParticipants().size() == 1);
        Assert.assertTrue(system.getActivity("voiture").getParticipants().contains(user1));
        Assert.assertFalse(system.getActivity("voiture").getParticipants().contains(user2));
        Assert.assertTrue(system.getInteret("CREATION", "physique").getInterestedUsers().contains(user1));
        Assert.assertFalse(user1.assignRobotToActivity("fusée")); //the robot is busy


    }

    //TODO : do activity
}
