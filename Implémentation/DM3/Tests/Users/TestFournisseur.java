package Users;

import org.junit.*;
import System.SystemeRobotix;

public class TestFournisseur {

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

        Assert.assertEquals("t@gmail.com", seller1.getEmail());
        Assert.assertEquals("v@gmail.com", seller2.getEmail());

        seller1.changeEmail("new@gmail.com");
        Assert.assertEquals(seller1.getEmail(),"new@gmail.com");

        Assert.assertFalse(seller2.changeEmail(seller1.getEmail()));
        Assert.assertTrue(seller2.changeEmail("tob2@g.com"));

    }

    @Test
    public void TestChangeFirstName(){
        Assert.assertEquals("Theo",seller1.getFirstName());
        Assert.assertTrue(seller1.changeFirstName("belgio2"));

        Assert.assertEquals("Victor", seller2.getFirstName());
        Assert.assertFalse(seller1.changeFirstName(seller2.getFirstName()));
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

        Assert.assertTrue(seller1.followUser(user2.getPseudo()));
        Assert.assertTrue(seller2.followUser(user2.getPseudo()));

        Assert.assertTrue(user2.isFollowedBySeller(seller1.getFirstName()));
        Assert.assertTrue(user2.isFollowedBySeller(seller2.getFirstName()));

        Assert.assertTrue(user1.followSeller(seller1.getFirstName()));
        Assert.assertTrue(seller1.isFollowedByUser(user1.getPseudo()));

    }

    @Test
    public void TestFollowSeller(){
        Assert.assertEquals("Theo",seller1.getFirstName());
        Assert.assertEquals("Victor",seller2.getFirstName());

        Assert.assertTrue(seller1.followSeller(seller2.getFirstName()));
        Assert.assertTrue(seller2.isFollowedBySeller(seller1.getFirstName()));

        Assert.assertTrue(user2.followSeller(seller1.getFirstName()));
        Assert.assertTrue(user2.followSeller(seller2.getFirstName()));

        Assert.assertTrue(seller1.isFollowedByUser(user2.getPseudo()));
        Assert.assertTrue(seller2.isFollowedByUser(user2.getPseudo()));


    }
}
