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
    ArrayList<Acteur> following;
    ArrayList<Acteur> followers;
    SystemeRobotix systeme;

    public Acteur(String compagnieName, String firstName, String password, String email, String phoneNumber){

        this.setCompagnieName(compagnieName);
        this.setFirstName(firstName);
        this.setPassword(password);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);

    }
    /* methods */
    public boolean setPassword(String oldPassword , String newPassword){

        if(oldPassword.equals(this.password)){
            this.setPassword(newPassword);
            return true;
        }

        return false;

    }

    public void changeCompanieName(String newName){
        this.setCompagnieName(newName);
    }

    public void changeFirstName(String newName){
        this.setFirstName(newName);
    }

    public void changeEmail(String newEmail){
        this.setEmail(newEmail);
    }

    public void changePhone(String newPhone){
        this.setPhoneNumber(newPhone);
    }

    public void followUser(String pseudo){
        for(Utilisateur user : SystemeRobotix.getInstance().getUsers()){
            if(user.getPseudo().equals(pseudo)){
                this.following.add(user);
            }
        }
    }

    public void followSeller(String name){
        for(Fournisseur user : SystemeRobotix.getInstance().getSellers()){
            if(user.getFirstName().equals(name)){
                this.following.add(user);
            }
        } 
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

    private void setEmail(String email) {
        if(email.contains("@")){
            for(Acteur acteur : SystemeRobotix.getInstance().getActors()){
                if(acteur.getEmail().equals(email)){
                    System.out.println("this email is already taken, please provide another email.");
                    return;
                }
            }
            this.email = email;
        }
        else{
            System.out.println("the new email is not valid, please try again.");
        }
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setPhoneNumber(String phoneNumber) {
        if(phoneNumber.length() == 10 && phoneNumber.matches("[0-9]")){
            this.phoneNumber = phoneNumber;
        }
        else{
            System.out.println("the new number is invalid, please try again.");
        }
    }

}