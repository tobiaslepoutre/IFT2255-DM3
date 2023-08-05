package System;

import java.util.ArrayList;
import java.util.Date;

import Activity.Activity;
import Activity.Interet;
import Machines.composantes.Composante;
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
            if(user.getPseudo().toUpperCase().equals(pseudo.toUpperCase()) && user.checkPassword(password)){
                return user;
            }
        }

        return null;
    }

    public Fournisseur loginSeller(String name, String password){
        for(Fournisseur user : this.getSellers()){
            if(user.getFirstName().toUpperCase().equals(name.toUpperCase()) && user.checkPassword(password)){
                return user;
            }
        }

        return null;
    }



    public void signUpUser(String companieName, String firstName, String secondName, String email, String pseudo, String password, String phoneNumber){
        try{
            this.acteurs.add(new Utilisateur(companieName, firstName, secondName, password, pseudo, email, phoneNumber));
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Un nouveau profil utilisateur n'a pas été crée");
        }
    }

    public void signUpSeller(String companieName, String firstName, String email, String password, String phoneNumber, String location, int capacity){
        try{
            this.acteurs.add(new Fournisseur(companieName, firstName, password, email, phoneNumber, location, capacity));
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Un nouveau profil fournisseur n'a pas été crée");
        }
    }

    public void showAllUsers(){
        for(Utilisateur user : this.getUsers()){
            System.out.println("•" + user.getPseudo());
        }

        for(Fournisseur seller : this.getSellers()){
            System.out.println("•" + seller.getFirstName());
        }
    }

    public void searchUser(String pseudo , String ByfollowersOf){
        for(Utilisateur user : this.getUsers()){
            if(user.getPseudo().toUpperCase().equals(pseudo.toUpperCase())){
                System.out.println(user);
            }

            // we don't use the showFollowers methodes because
            //we don't want to show doublons : case where the given pseudo is also a follower.
            if(user.getPseudo().toUpperCase().equals(ByfollowersOf.toUpperCase())){
                for(Utilisateur user_ : this.getUsers()){
                    if(user.isFollowedByUser(user_.getPseudo()) && !user_.getPseudo().toUpperCase().equals(pseudo.toUpperCase())){
                        System.out.println(user_);
                    }
                }
            }


            if(user.isFollowedBySeller(ByfollowersOf)){
                System.out.println(user);
            }
        }
    }

    public void searchSeller(String name, String location, String composanteType){
        for(Fournisseur seller : this.getSellers()){
            if(seller.getFirstName().equals(name)){
                System.out.println("•" + seller.getFirstName());
                continue;
            }
            if(seller.getLocation().equals(location)){
                System.out.println("•" + seller.getFirstName());
                continue;
            }

            for(Composante c : seller.getComposantes()){
                if(c.getType().equals(composanteType)){
                    System.out.println("•" + seller.getFirstName());
                    break;
                }

            }

        }
    }

    public boolean showProfile(String pseudo){
        for(Utilisateur user : this.getUsers()){
            if(user.getPseudo().equals(pseudo)){
                System.out.println("----- Profile of " + user.getPseudo() + " -----");
                System.out.println(user);
                System.out.println("Points : " + user.getPoints());

                System.out.println("• Followers : ");
                user.showFollowers();

                System.out.println("• Following : ");
                user.showFollowing();

                System.out.println("Activité crée :");
                user.showCreatedActivities();

                System.out.println("Activité inscrite :");
                user.showActivities();

                System.out.println("Centre d'interet :");
                user.showInterets();

                return true;
            }

        }
        return false;
    }

    public boolean showSeller(String name){
        for(Fournisseur seller : this.getSellers()){
            if(seller.getFirstName().equals(name)){
                System.out.println("----- Profile of " + seller.getFirstName() + " -----");
                System.out.println(seller);

                System.out.println("• Followers : ");
                seller.showFollowers();

                System.out.println("• Following : ");
                seller.showFollowing();

                System.out.println("Composantes en vente : ");

                for(Composante c : seller.getComposantes()){
                    System.out.println("•" + c.getName() + " , description : "+ c.getDescription() + " , prix : " + c.getPrice());
                }

                return true;
            }

        }
        return false;
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

    public Fournisseur getSeller(String name){
        for(Fournisseur seller : this.getSellers()) {
            if(seller.getFirstName().toUpperCase().equals(name.toUpperCase())){
                return seller;
            }
        }
        return null;
    }

    public ArrayList<Interet> getInterets(){
        return this.interets;
    }

    public boolean createInteret(String type, String name){
        //crée un nouvel interet si il n'existe pas déja
        for(Interet interet : this.interets){
            if(interet.getName().toUpperCase().equals(name.toUpperCase()) && interet.getType().toUpperCase().equals(type.toUpperCase())){
                return false;
            }
        }
        this.interets.add(new Interet(type, name));
        return true;
    }

    public Interet getInteret(String type, String name){
        //retourne un interet voulu si il existe sinon null

        if(!(type.toUpperCase().equals("CREATION") || type.toUpperCase().equals("EDUCATION") || type.toUpperCase().equals("GAME"))){
            type = "OTHER";
        }

        type = type.toUpperCase();

        for(Interet interet : this.interets){
            if(interet.getName().toUpperCase().equals(name.toUpperCase()) && interet.getType().toUpperCase().equals(type.toUpperCase())){
                return interet;
            }
        }
        return null;
    }

    public boolean createActivity(int reward, Date startDate, Date endDate, String name, Utilisateur creator, Interet i){
        for(Activity a : this.activities){
            if(a.getName().toUpperCase().equals(name.toUpperCase())){
                return false;
            }
        }
        i.addInterestedUser(creator.getPseudo());
        this.activities.add(new Activity(reward,startDate,endDate,name, creator,i));
        return true;
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
