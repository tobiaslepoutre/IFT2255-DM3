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

    public boolean setPassword(String oldPassword , String newPassword){

        if(oldPassword.equals(this.password)){
            this.setPassword(newPassword);
            return true;
        }

        return false;

    }

    public void chngeCompanieName(String newName){

    }

    public void changeFirstName(String newName){

    }

    public void changeEmail(String newEmail){

    }

    public void changePhone(String newPhone){

    }

    public void followUser(String pseudo){

    }

    public void followSeller(String compagnieName){

    }

    /* getters and setters  */
    public String getCompagnieName() {
        return compagnieName;
    }

    public String getEmail() {
        return email;
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
        this.email = email;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}