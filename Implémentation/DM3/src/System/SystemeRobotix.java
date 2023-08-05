package System;

import java.util.ArrayList;
import java.util.Date;

import Activity.Activity;
import Activity.Interet;
import Machines.composantes.Composante;
import Users.Acteur;
import Users.Fournisseur;
import Users.Utilisateur;

/**
 * La classe SystemeRobotix est la classe principale du système de robots.
 * Elle utilise le singleton pour s'assurer qu'il n'y a qu'une seule instance du système.
 * @author Giovanni Belval
 * @version 1.0
 */
public class SystemeRobotix {
    
    /* Instance singleton */
    private static SystemeRobotix instance;

    /* Associations */
    private ArrayList<Acteur> acteurs;
    private ArrayList<Interet> interets;
    private ArrayList<Activity> activities;

    /**
     * Constructeur privé pour s'assurer qu'aucune autre instance de SystemeRobotix ne peut être créée.
     */
    private SystemeRobotix(){
        this.acteurs = new ArrayList<>();
        this.interets = new ArrayList<>();
        this.activities = new ArrayList<>();
    }

    /**
     * Méthode pour obtenir l'instance unique de SystemeRobotix.
     * @return L'instance unique de SystemeRobotix.
     */
    public static SystemeRobotix getInstance(){
        if(SystemeRobotix.instance == null){
            SystemeRobotix.instance = new SystemeRobotix();
        }
        return SystemeRobotix.instance;
    }

    /**
     * Méthode pour détruire l'instance unique de SystemeRobotix.
     */
    public static void destroy(){
        SystemeRobotix.instance = null;
    }

    /**
     * Méthode pour obtenir une liste de tous les utilisateurs dans le système.
     * @return Une ArrayList d'Utilisateur représentant tous les utilisateurs dans le système.
     */
    public ArrayList<Utilisateur> getUsers(){
        ArrayList<Utilisateur> filtered = new ArrayList<>();

        for(Acteur acteur : this.acteurs){
            if(acteur.getClass().getSimpleName().equals("Utilisateur")){
                filtered.add((Utilisateur)acteur);
            }
        }
        return filtered;
    }

    /**
     * Méthode pour obtenir une liste de tous les fournisseurs dans le système.
     * @return Une ArrayList de Fournisseur représentant tous les fournisseurs dans le système.
     */
    public ArrayList<Fournisseur> getSellers(){
            ArrayList<Fournisseur> filtered = new ArrayList<>();

            for(Acteur acteur : this.acteurs){
                if(acteur.getClass().getSimpleName().equals("Fournisseur")){
                    filtered.add((Fournisseur)acteur);
                }
            }
            return filtered;
        }

    /**
     * Méthode pour obtenir une liste de tous les acteurs dans le système.
     * @return Une ArrayList d'Acteur représentant tous les acteurs dans le système.
     */
    public ArrayList<Acteur> getActors(){
        return this.acteurs;
    }

    /**
     * Méthode pour se connecter en tant qu'utilisateur.
     * @param pseudo Le pseudo de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @return Un Utilisateur si les identifiants sont corrects, null sinon.
     */
    public Utilisateur loginUser(String pseudo, String password){
        for(Utilisateur user : this.getUsers()){
            if(user.getPseudo().toUpperCase().equals(pseudo.toUpperCase()) && user.checkPassword(password)){
                return user;
            }
        }
        return null;
    }


    /**
     * Méthode pour se connecter en tant que fournisseur.
     * @param name Le nom du fournisseur.
     * @param password Le mot de passe du fournisseur.
     * @return Un Fournisseur si les identifiants sont corrects, null sinon.
     */
    public Fournisseur loginSeller(String name, String password){
        for(Fournisseur user : this.getSellers()){
            if(user.getFirstName().toUpperCase().equals(name.toUpperCase()) && user.checkPassword(password)){
                return user;
            }
        }
        return null;
    }

    /**
     * Méthode pour inscrire un nouvel utilisateur.
     * @param companieName, firstName, secondName, email, pseudo, password, phoneNumber
     *                      Des informations sur le nouvel utilisateur.
     * @throws Exception   Si une exception se produit lors de la création d'un nouvel utilisateur.
     */
    public void signUpUser(String companieName, String firstName, String secondName, String email, String pseudo,
                           String password, String phoneNumber){
        try{
            this.acteurs.add(new Utilisateur(companieName, firstName, secondName, password, pseudo, email,
                    phoneNumber));
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Un nouveau profil utilisateur n'a pas été crée");
        }
    }

    /**
     * Méthode pour inscrire un nouveau fournisseur.
     * @param companieName, firstName, email, password, phoneNumber, location, capacity
     *                      Des informations sur le nouveau fournisseur.
     * @throws Exception   Si une erreur se produit lors de la création d'un nouveau fournisseur.
     */
    public void signUpSeller(String companieName, String firstName, String email, String password, String phoneNumber,
                             String location, int capacity){
        try{
            this.acteurs.add(new Fournisseur(companieName, firstName, password, email, phoneNumber, location,
                    capacity));
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Un nouveau profil fournisseur n'a pas été crée");
        }
    }

    /**
     * Méthode pour afficher tous les utilisateurs et fournisseurs.
     */
    public void showAllUsers(){
        for(Utilisateur user : this.getUsers()){
            System.out.println("•" + user.getPseudo());
        }
        for(Fournisseur seller : this.getSellers()){
            System.out.println("•" + seller.getFirstName());
        }
    }

    /**
     * Méthode pour rechercher un utilisateur par pseudo et par followers.
     * @param pseudo Le pseudo de l'utilisateur recherché.
     * @param ByfollowersOf Le pseudo des followers de l'utilisateur recherché.
     */
    public void searchUser(String pseudo , String ByfollowersOf){
        for(Utilisateur user : this.getUsers()){
            if(user.getPseudo().toUpperCase().equals(pseudo.toUpperCase())){
                System.out.println(user);
            }
            // on n'utilise pas la méthode showFollowers parce qu'on ne veut pas
            // avoir de doublons : cas où le pseudo donné est aussi un follower.
            if(user.getPseudo().toUpperCase().equals(ByfollowersOf.toUpperCase())){
                for(Utilisateur user_ : this.getUsers()){
                    if(user.isFollowedByUser(user_.getPseudo()) &&
                            !user_.getPseudo().toUpperCase().equals(pseudo.toUpperCase())){
                        System.out.println(user_);
                    }
                }
            }
            if(user.isFollowedBySeller(ByfollowersOf)){
                System.out.println(user);
            }
        }
    }

    /**
     * Méthode pour rechercher un fournisseur par nom, lieu et type de composant.
     * @param name Le nom du fournisseur.
     * @param location L'emplacement du fournisseur.
     * @param composanteType Le type de composant que le fournisseur vend.
     */
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

    /**
     * Affiche le profil d'un utilisateur en utilisant le pseudo.
     * @param pseudo Le pseudo de l'utilisateur dont on veut afficher le profil.
     * @return boolean Indique si le profil de l'utilisateur a été trouvé et affiché.
     */
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

    /**
     * Affiche le profil d'un vendeur en utilisant le prénom.
     * @param name Le prénom du vendeur dont on veut afficher le profil.
     * @return boolean Indique si le profil du vendeur a été trouvé et affiché.
     */
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
                    System.out.println("•" + c.getName() + " , description : "+ c.getDescription() +
                            " , prix : " + c.getPrice());
                }

                return true;
            }
        }
        return false;
    }

    /**
     * Affiche les profils de tous les vendeurs présents dans le système.
     */
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

    /**
     * Obtient un vendeur en utilisant son prénom.
     * @param name Le prénom du vendeur que l'on veut obtenir.
     * @return Fournisseur Le vendeur trouvé, ou null si aucun vendeur n'a été trouvé.
     */
    public Fournisseur getSeller(String name){
        for(Fournisseur seller : this.getSellers()) {
            if(seller.getFirstName().toUpperCase().equals(name.toUpperCase())){
                return seller;
            }
        }
        return null;
    }

    /**
     * Obtient tous les intérêts présents dans le système.
     * @return ArrayList<Interet> La liste de tous les intérêts.
     */
    public ArrayList<Interet> getInterets(){
        return this.interets;
    }

    /**
     * Crée un nouvel intérêt, s'il n'existe pas, et l'ajouter au système.
     * @param type Le type d'intérêt à créer.
     * @param name Le nom de l'intérêt à créer.
     * @return boolean Indique si l'intérêt a été créé et ajouté avec succès.
     */
    public boolean createInteret(String type, String name){
        for(Interet interet : this.interets){
            if(interet.getName().toUpperCase().equals(name.toUpperCase()) &&
                    interet.getType().toUpperCase().equals(type.toUpperCase())){
                return false;
            }
        }
        this.interets.add(new Interet(type, name));
        return true;
    }

    /**
     * Obtient un intérêt spécifique en utilisant son type et son nom.
     * @param type Le type de l'intérêt recherché.
     * @param name Le nom de l'intérêt recherché.
     * @return Interet L'intérêt trouvé, ou null si aucun intérêt correspondant n'a été trouvé.
     */
    public Interet getInteret(String type, String name){
        if(!(type.toUpperCase().equals("CREATION") || type.toUpperCase().equals("EDUCATION") ||
                type.toUpperCase().equals("GAME"))){
            type = "OTHER";
        }

        type = type.toUpperCase();
        for(Interet interet : this.interets){
            if(interet.getName().toUpperCase().equals(name.toUpperCase()) &&
                    interet.getType().toUpperCase().equals(type.toUpperCase())){
                return interet;
            }
        }
        return null;
    }

    /**
     * Crée une nouvelle activité et l'ajoute au système.
     * @param reward La récompense de l'activité à créer.
     * @param startDate La date de début de l'activité à créer.
     * @param endDate La date de fin de l'activité à créer.
     * @param name Le nom de l'activité à créer.
     * @param creator L'utilisateur qui crée l'activité.
     * @param i L'intérêt lié à l'activité à créer.
     * @return boolean Indique si l'activité a été créée et ajoutée avec succès.
     */
    public boolean createActivity(int reward, Date startDate, Date endDate, String name, Utilisateur creator,
                                  Interet i) {
        for(Activity a : this.activities){
            if(a.getName().toUpperCase().equals(name.toUpperCase())){
                return false;
            }
        }
        i.addInterestedUser(creator.getPseudo());
        this.activities.add(new Activity(reward,startDate,endDate,name, creator,i));
        return true;
    }

    /**
     * Obtient une activité spécifique en utilisant son nom.
     * @param name Le nom de l'activité recherchée.
     * @return Activity L'activité trouvée, ou null si aucune activité correspondante n'a été trouvée.
     */
    public Activity getActivity(String name){
        for(Activity activity : this.activities){
            if(activity.getName().equals(name)){
                return activity;
            }
        }
        return null;
    }

    /**
     * Supprime une activité spécifique du système.
     * @param activity L'activité à supprimer du système.
     */
    public void removeActivity(Activity activity){
        for(Interet interet : this.interets){
            if(interet.getActivities().contains(activity)){
                interet.removeRelatedActivity(activity);
            }
        }
        this.activities.remove(activity);
    }

    /**
     * Obtient toutes les activités présentes dans le système.
     * @return ArrayList<Activity> La liste de toutes les activités.
     */
    public ArrayList<Activity> getActivities(){
        return this.activities;
    }
}
