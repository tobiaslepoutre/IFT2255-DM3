package System;

import java.util.ArrayList;
import Users.Acteur;
import Users.Fournisseur;
import Users.Utilisateur;

public class SystemeRobotix {
   
    /* it implements the singleton pattern because I want it to be unique */
    
    /* singleton instance */
    private static SystemeRobotix instance;

    private SystemeRobotix(){
        this.acteurs = new ArrayList<>();
    }

    public static SystemeRobotix getInstance(){
        if(SystemeRobotix.instance == null){
            SystemeRobotix.instance = new SystemeRobotix();
        }
    
        return SystemeRobotix.instance;
    }

    public static void destroy(){
        SystemeRobotix.instance = null;
    }

    /* assosiations */
    ArrayList<Acteur> acteurs;


    public ArrayList<Utilisateur> getUsers(){
        ArrayList<Utilisateur> filtered = new ArrayList<>();

        for(Acteur acteur : this.acteurs){
            if(acteur.getClass().getSimpleName().equals("Utilisateur")){
                filtered.add((Utilisateur)acteur);
            }
        }

        return filtered;
    }

    public ArrayList<Fournisseur> getSellers(){
            ArrayList<Fournisseur> filtered = new ArrayList<>();

            for(Acteur acteur : this.acteurs){
                if(acteur.getClass().getSimpleName().equals("Fournisseur")){
                    filtered.add((Fournisseur)acteur);
                }
            }

            return filtered;
        }

    public ArrayList<Acteur> getActors(){
        return this.acteurs;
    }

    public Utilisateur loginUser(String pseudo, String password){
        for(Utilisateur user : this.getUsers()){
            if(user.getPseudo().equals(pseudo) && user.checkPassword(password)){
                return user;
            }
        }

        return null;
    }

    public Fournisseur loginSeller(String name, String password){
        for(Fournisseur user : this.getSellers()){
            if(user.getFirstName().equals(name) && user.checkPassword(password)){
                return user;
            }
        }

        return null;
    }



    public void signUpUser(String companieName, String firstName, String secondName, String email, String pseudo, String password, String phoneNumber){
        try{
            this.acteurs.add(new Utilisateur(companieName, firstName, secondName, password, pseudo, email, phoneNumber));
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("The new user has not been created");
        }
    }

    public void signUpSeller(String companieName, String firstName, String email, String password, String phoneNumber, String location, int capacity){
        try{
            this.acteurs.add(new Fournisseur(companieName, firstName, password, email, phoneNumber, location, capacity));
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("The new user has not been created");
        }
    }
}
