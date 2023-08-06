package Users;
import java.io.Serializable;
import java.util.*;

import System.SystemeRobotix;

/**
 * La classe Acteur est une classe abstraite qui représente un acteur dans le système.
 * Un acteur peut être un utilisateur ou un fournisseur.
 * Cette classe fournit des méthodes pour la gestion des informations personnelles et
 * des interactions avec d'autres acteurs (comme le suivi d'autres acteurs).
 * @author Giovanni Belval
 * @version 1.0
 */
public abstract class Acteur implements Serializable {

    /* Attributs */
    private String compagnieName;
    private String firstName;
    private String password;
    private String email;
    private String phoneNumber;
    private ArrayList<String> notifications;
    private float money = 500;

    /* Associations */
    ArrayList<Utilisateur> followingUser;
    ArrayList<Utilisateur> followersUser;
    ArrayList<Fournisseur> followingSeller;
    ArrayList<Fournisseur> followersSeller;

    /**
     * Constructeur pour créer un objet Acteur avec des détails spécifiés.
     *
     * @param compagnieName Le nom de la compagnie de l'acteur.
     * @param firstName Le prénom de l'acteur.
     * @param password Le mot de passe de l'acteur.
     * @param email L'email de l'acteur.
     * @param phoneNumber Le numéro de téléphone de l'acteur.
     * @throws Exception Si le numéro de téléphone ou l'email est invalide.
     */
    public Acteur(String compagnieName, String firstName, String password, String email, String phoneNumber) throws Exception{

        this.setCompagnieName(compagnieName);
        this.setFirstName(firstName);
        this.setPassword(password);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.notifications = new ArrayList<>();

        this.followersUser = new ArrayList<>();
        this.followingUser = new ArrayList<>();

        this.followersSeller = new ArrayList<>();
        this.followingSeller = new ArrayList<>();

    }

    /**
     * Méthode pour changer le mot de passe de l'acteur.
     *
     * @param oldPassword Le mot de passe actuel.
     * @param newPassword Le nouveau mot de passe.
     * @return true si le changement a réussi, false sinon.
     */
    public boolean setPassword(String oldPassword , String newPassword){
        if(oldPassword.equals(this.password)){
            this.setPassword(newPassword);
            return true;
        }
        return false;
    }

    /**
     * Method to change the password of the actor. First checks the current password before setting the new password.
     *
     * @param oldPassword The current password.
     * @param newPassword The new password to be set.
     * @return True if the password change was successful, false otherwise.
     */
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

    /**
     * Method to change the company name of the actor.
     *
     * @param newName The new company name to be set.
     */
    public void changeCompanieName(String newName){
        this.setCompagnieName(newName);
    }

    /**
     * Method to change the first name of the actor.
     *
     * @param newName The new first name to be set.
     * @return True as the operation will always be successful.
     */
    public boolean changeFirstName(String newName){
        this.setFirstName(newName);
        return true;
    }

    /**
     * Method to change the email of the actor. Checks if the new email is in a valid format before setting it.
     *
     * @param newEmail The new email to be set.
     * @return True if the email change was successful, false otherwise.
     */
    public boolean changeEmail(String newEmail){
        try{
            this.setEmail(newEmail);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Method to change the phone number of the actor. Checks if the new phone number is in a valid format before setting it.
     *
     * @param newPhone The new phone number to be set.
     */
    public void changePhone(String newPhone){
        try {
            this.setPhoneNumber(newPhone);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Follows a user given a username.
     *
     * @param pseudo The username of the user to follow.
     * @return True if the user was successfully followed, false otherwise.
     */
    public boolean followUser(String pseudo){
        for(Utilisateur user : SystemeRobotix.getInstance().getUsers()){
            if(user.getPseudo().toUpperCase().equals(pseudo.toUpperCase()) && !this.followingUser.contains(user)){
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

    /**
     * Adds a user to the list of user followers.
     *
     * @param user The user to be added to the followers list.
     */
    protected void addFollowerUser(Utilisateur user){
        this.followersUser.add(user);
    }

    /**
     * Adds a seller to the list of seller followers.
     *
     * @param user The seller to be added to the followers list.
     */
    protected void addFollowerSeller(Fournisseur user){
        this.followersSeller.add(user);
    }

    /**
     * Checks if the actor is followed by a user.
     *
     * @param pseudo The username of the user.
     * @return True if the actor is followed by the user, false otherwise.
     */
    public boolean isFollowedByUser(String pseudo){
        for(Utilisateur user : this.followersUser){
            if(user.getPseudo().toUpperCase().equals(pseudo.toUpperCase())){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the actor is followed by a seller.
     *
     * @param name The name of the seller.
     * @return True if the actor is followed by the seller, false otherwise.
     */
    public boolean isFollowedBySeller(String name){
        for(Fournisseur user : this.followersSeller){
            if(user.getFirstName().toUpperCase().equals(name.toUpperCase())){
                return true;
            }
        }
        return false;
    }

    /**
     * Follows a seller given a seller's name.
     *
     * @param name The name of the seller to follow.
     * @return True if the seller was successfully followed, false otherwise.
     */
    public boolean followSeller(String name){
        for(Fournisseur user : SystemeRobotix.getInstance().getSellers()){
            if(user.getFirstName().toUpperCase().equals(name.toUpperCase()) && !this.followingSeller.contains(user)){
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


    /* Getters et Setters */

    /**
     * Retrieves the company name of the actor.
     *
     * @return The company name of the actor.
     */
    public String getCompagnieName() {
        return compagnieName;
    }

    /**
     * Retrieves the email of the actor.
     *
     * @return The email of the actor.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Retrieves the first name of the actor.
     *
     * @return The first name of the actor.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the phone number of the actor.
     *
     * @return The phone number of the actor.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Retrieves the money of the actor.
     *
     * @return The money of the actor.
     */
    public float getMoney(){
        return this.money;
    }

    /**
     * Sets the money of the actor.
     *
     * @param money The amount to set as the actor's money.
     */
    public void setMoney(float money){
        this.money = money;
    }

    /**
     * Sets the company name of the actor.
     *
     * @param compagnieName The company name to be set.
     */
    private void setCompagnieName(String compagnieName) {
        this.compagnieName = compagnieName;
    }

    /**
     * Sets the email of the actor.
     *
     * @param email The email to be set.
     */
    private void setEmail(String email) throws Exception {
        if(email.contains("@")){
            for(Acteur acteur : SystemeRobotix.getInstance().getActors()){
                if(acteur.getEmail().toUpperCase().equals(email.toUpperCase())){
                    throw new Exception("cet email existe déja dans notre systeme, essayer de vous connecter.");
                }
            }
            this.email = email;
        }
        else{
            throw new Exception("l'email n'est pas dans un format correct");
        }
    }

    /**
     * Sets the first name of the actor.
     *
     * @param firstName The first name to be set.
     */
    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the password of the actor.
     *
     * @param password The password to be set.
     */
    private void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the phone number of the actor.
     *
     * @param phoneNumber The phone number to be set.
     */
    private void setPhoneNumber(String phoneNumber) throws Exception {
        if(phoneNumber.length() == 10 && phoneNumber.matches("[0-9]+")){
            this.phoneNumber = phoneNumber;
        }
        else{
            throw new Exception("Ce numéro de téléphone n'est pas dans un format valide, il doit contenir 10 chiffres..");
        }
    }

    /**
     * Checks if the given password matches the actor's password.
     *
     * @param password The password to be checked.
     * @return True if the password matches, false otherwise.
     */
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    /**
     * Displays the users and sellers the actor is following.
     */
    public void showFollowing(){
        for(Utilisateur user : this.followingUser){
            System.out.println("             -"+ user);
        }

        for(Fournisseur seller : this.followingSeller){
            System.out.println("             -"+ seller);
        }
    }

    /**
     * Displays the users and sellers following the actor.
     */
    public void showFollowers(){
        for(Utilisateur user : this.followersUser){
            System.out.println("             -"+ user);
        }

        for(Fournisseur seller : this.followersSeller){
            System.out.println("             -"+seller);
        }
    }

    /**
     * Adds a notification to the actor's list of notifications.
     *
     * @param notif The notification to be added.
     */
    public void addNotification(String notif){
        this.notifications.add(notif);
    }

}
