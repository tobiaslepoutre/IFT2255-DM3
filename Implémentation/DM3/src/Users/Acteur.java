package Users;
import java.util.*;

import System.SystemeRobotix;

public abstract class Acteur{

    /* attributes */
    private String compagnieName;
    private String firstName;
    private String password;
    private String email;
    private String phoneNumber;

    /* associations */ 
    ArrayList<Utilisateur> followingUser;
    ArrayList<Utilisateur> followersUser;

    ArrayList<Fournisseur> followingSeller;
    ArrayList<Fournisseur> followersSeller;

    public Acteur(String compagnieName, String firstName, String password, String email, String phoneNumber) throws Exception{

        this.setCompagnieName(compagnieName);
        this.setFirstName(firstName);
        this.setPassword(password);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);

        this.followersUser = new ArrayList<>();
        this.followingUser = new ArrayList<>();

        this.followersSeller = new ArrayList<>();
        this.followingSeller = new ArrayList<>();

    }
    /* methods */
    public boolean setPassword(String oldPassword , String newPassword){

        if(oldPassword.equals(this.password)){
            this.setPassword(newPassword);
            return true;
        }

        return false;

    }

    public boolean changePassword(String oldPassword, String newPassword) {
        try {
            if (this.checkPassword(oldPassword)) {
                this.setPassword(newPassword);
                return true;
            }
            return false;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void changeCompanieName(String newName){
        this.setCompagnieName(newName);
    }

    public boolean changeFirstName(String newName){
        this.setFirstName(newName);
        return true;
    }

    public boolean changeEmail(String newEmail){
        try{
            this.setEmail(newEmail);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void changePhone(String newPhone){
        try {
            this.setPhoneNumber(newPhone);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean followUser(String pseudo){
        for(Utilisateur user : SystemeRobotix.getInstance().getUsers()){
            if(user.getPseudo().equals(pseudo) && !this.followingUser.contains(user)){
                this.followingUser.add(user);
                try{
                    user.addFollowerUser((Utilisateur)this);
                }catch(Exception e){
                    user.addFollowerSeller((Fournisseur)this);
                }
                return true;
            }
        }
        return false;
    }

    protected void addFollowerUser(Utilisateur user){
        this.followersUser.add(user);
    }

    protected void addFollowerSeller(Fournisseur user){
        this.followersSeller.add(user);
    }

    public boolean isFollowedByUser(String pseudo){
        for(Utilisateur user : this.followersUser){
            if(user.getPseudo().equals(pseudo)){
                return true;
            }
        }
        return false;
    }

    public boolean isFollowedBySeller(String name){
        for(Fournisseur user : this.followersSeller){
            if(user.getFirstName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean followSeller(String name){
        for(Fournisseur user : SystemeRobotix.getInstance().getSellers()){
            if(user.getFirstName().equals(name) && !this.followingSeller.contains(user)){
                this.followingSeller.add(user);
                try{
                    user.addFollowerSeller((Fournisseur)this);
                }catch(Exception e){
                    user.addFollowerUser((Utilisateur)this);
                }
                return true;
            }
        }
        return false;
    }


    /* getters and setters  */
    public String getCompagnieName() {
        return compagnieName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private void setCompagnieName(String compagnieName) {
        this.compagnieName = compagnieName;
    }

    private void setEmail(String email) throws Exception {
        if(email.contains("@")){
            for(Acteur acteur : SystemeRobotix.getInstance().getActors()){
                if(acteur.getEmail().equals(email)){
                    throw new Exception("This email already exist in the database, try to log in instead");
                }
            }
            this.email = email;
        }
        else{
            throw new Exception("The new email has an invalid format");
        }
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setPhoneNumber(String phoneNumber) throws Exception {
        if(phoneNumber.length() == 10 && phoneNumber.matches("[0-9]+")){
            this.phoneNumber = phoneNumber;
        }
        else{
            throw new Exception("The new phone number has not a valid format, it shoud be 10 numbers.");
        }
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

}