package System;

import java.util.ArrayList;
import java.util.Date;

import Activity.Activity;
import Activity.Interet;
import Users.Acteur;
import Users.Fournisseur;
import Users.Utilisateur;

public class SystemeRobotix {
   
    /* it implements the singleton pattern because I want it to be unique */
    
    /* singleton instance */
    private static SystemeRobotix instance;

    private SystemeRobotix(){
        this.acteurs = new ArrayList<>();
        this.interets = new ArrayList<>();
        this.activities = new ArrayList<>();
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
    private ArrayList<Acteur> acteurs;
    private ArrayList<Interet> interets;
    private ArrayList<Activity> activities;


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

    public void showAllUsers(){
        for(Utilisateur user : this.getUsers()){
            System.out.println(user);
        }

        for(Fournisseur seller : this.getSellers()){
            System.out.println(seller);
        }
    }

    public void searchUser(String pseudo , String ByfollowersOf){
        for(Utilisateur user : this.getUsers()){
            if(user.getPseudo().equals(pseudo)){
                System.out.println(user);
            }

            // we don't use the showFollowers methodes because
            //we don't want to show doublons : case where the given pseudo is also a follower.
            if(user.getPseudo().equals(ByfollowersOf)){
                for(Utilisateur user_ : this.getUsers()){
                    if(user.isFollowedByUser(user_.getPseudo()) && user_.getPseudo() != pseudo){
                        System.out.println(user_);
                    }
                }
            }


            if(user.isFollowedBySeller(ByfollowersOf)){
                System.out.println(user);
            }
        }
    }

    public void searchSeller(String name, String location, String composante){
        for(Fournisseur seller : this.getSellers()){
            if(seller.getFirstName().equals(name)){
                System.out.println(seller);
            }
            if(seller.getLocation().equals(location)){
                System.out.println(seller);
            }

            //TODO : recherche par nom de composantes (Implémenter le package Machines)
        }
    }

    public void showProfile(String pseudo){
        for(Utilisateur user : this.getUsers()){
            if(user.getPseudo().equals(pseudo)){
                System.out.println("----- Profile of " + user.getPseudo() + " -----");
                System.out.println(user);
                System.out.println("Points : " + user.getPoints());

                System.out.println("• Followers : ");
                user.showFollowers();

                System.out.println("• Following : ");
                user.showFollowing();

                //TODO : afficher les activités et les interêts (implementer le package Activity)
            }

        }
    }

    public void showSeller(String name){
        for(Fournisseur seller : this.getSellers()){
            if(seller.getFirstName().equals(name)){
                System.out.println("----- Profile of " + seller.getFirstName() + " -----");
                System.out.println(seller);

                System.out.println("• Followers : ");
                seller.showFollowers();

                System.out.println("• Following : ");
                seller.showFollowing();

                //TODO : afficher les composantes vendu (Implementer le package machines)
            }

        }
    }

    public void showSellers(){
        for(Fournisseur seller : this.getSellers()){
            System.out.println("----- Profile of " + seller.getFirstName() + " -----");
            System.out.println(seller);

            System.out.println("• Followers : ");
            seller.showFollowers();

            System.out.println("• Following : ");
            seller.showFollowing();

            //TODO : afficher les composantes vendu (Implementer le package machines)

        }
    }

    public ArrayList<Interet> getInterets(){
        return this.interets;
    }

    public boolean createInteret(String type, String name){
        //crée un nouvel interet si il n'existe pas déja
        for(Interet interet : this.interets){
            if(interet.getName().equals(name) && interet.getType().equals(type)){
                return false;
            }
        }
        this.interets.add(new Interet(type, name));
        return true;
    }

    public Interet getInteret(String type, String name){
        //retourne un interet voulu si il existe sinon null
        for(Interet interet : this.interets){
            if(interet.getName().equals(name) && interet.getType().equals(type)){
                return interet;
            }
        }
        return null;
    }

    public void createActivity(int reward, Date startDate, Date endDate, String name, Utilisateur creator, Interet i){
        //TODO : check if the activity is unique
        i.addInterestedUser(creator.getPseudo());
        this.activities.add(new Activity(reward,startDate,endDate,name, creator,i));
    }

    public Activity getActivity(String name){
        for(Activity activity : this.activities){
            if(activity.getName().equals(name)){
                return activity;
            }
        }
        return null;
    }

    public void removeActivity(Activity activity){

        for(Interet interet : this.interets){
            if(interet.getActivities().contains(activity)){
                interet.removeRelatedActivity(activity);
            }
        }

        this.activities.remove(activity);

    }

    public ArrayList<Activity> getActivities(){
        return this.activities;
    }


}
