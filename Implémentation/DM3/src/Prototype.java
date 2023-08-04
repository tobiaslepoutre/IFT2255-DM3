import Machines.composantes.Composante;
import Users.Fournisseur;
import Users.Utilisateur;
import System.SystemeRobotix;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Prototype {

    private static SystemeRobotix system;
    private static Fournisseur seller = null;
    private static Utilisateur user = null;
    private static Scanner scanner = new Scanner(System.in);  // Create a Scanner object
    private static boolean online = true;

    public static void main(String[] args) {
        //TODO : sauvegarder les information de l'object system et le reload a chaque fois comme demandé par la prof

        // initialisation générale
        system = SystemeRobotix.getInstance();
        Prototype.setup();

        System.out.println("Bienvenue dans Robotix");

        while(Prototype.online){
            //Cas Fournisseur


            //Cas Utilisateur
            if(user == null){
                connexion();
            }

            System.out.println("----------------------------------------------------"); // TODO : gerer la date ici
            System.out.println("\nVous avez actuellement "+ user.getMoney() + "CAD");
            System.out.println("Que voulez vous faire ? tapez un des choix correspondant ou out pour quittez le système : \n");

            System.out.println("showProfile     -> voir un profil (pseudo)");
            System.out.println("showMyProfile   -> voir mon profil");
            System.out.println("showAll         -> voir tous les pseudo des profils");
            System.out.println("follow          -> suivre un autre utilisateur (pseudo)");
            System.out.println("findSeller      -> trouver un fournisseur (nom, adresse, Type de composant voulu)");
            System.out.println("buyComponent    -> Acheter un composant chez un fournisseur");

            System.out.println("\n");

            String choice = Prototype.scanner.nextLine();

            switch(choice.toUpperCase()){

                case "SHOWPROFILE":
                    Prototype.showProfile();
                    break;

                case "SHOWMYPROFILE":
                    Prototype.showMyProfile();
                    break;

                case "SHOWALL":
                    Prototype.showAll();
                    break;

                case "FOLLOW":
                    Prototype.follow();
                    break;

                case "FINDSELLER":
                    Prototype.findSeller();
                    break;

                case "BUYCOMPONENT":
                    Prototype.buyComponent();
                    break;

                case "OUT":
                    Prototype.online = false;

                default:break;
            }

        }

    }

    public static void showProfile(){
        System.out.print("Veuillez rentrer le pseudo du profil qui vous interesse : ");
        String pseudo = Prototype.scanner.nextLine();

        if(!system.showProfile(pseudo)){
            if(!system.showSeller(pseudo)){
                System.out.println("Aucun pseudo ni nom de fournisseur ne corresponde a votre recherche.");
            }
        }
    }

    public static void showMyProfile(){
        system.showProfile(Prototype.user.getPseudo());
    }

    public static void showAll(){
        system.showAllUsers();
    }

    public static void follow(){
        System.out.print("Veuillez rentrer le pseudo/nom du profil que vous voulez suivre : ");

        String pseudo = Prototype.scanner.nextLine();

        if(!user.followUser(pseudo)){
            if(!user.followSeller(pseudo)){
                System.out.println("Aucun pseudo ni nom de fournisseur ne corresponde a votre recherche.");
            }
            else{
                System.out.println("vous suivez désormais le fournisseur  " + pseudo);
            }
        }

        else{
            System.out.println("vous suivez désormais l'utilisateur " + pseudo);
        }
    }

    public static void findSeller(){
        System.out.print("Veuillez rentrer le nom du fournisseur que vous voulez suivre : ");
        String name = Prototype.scanner.nextLine();

        System.out.print("Veuillez rentrer le nom du type de composante qui vous intéresse chez le fournisseur : ");
        String composanteType = Prototype.scanner.nextLine();

        System.out.println("Voici la liste des différents Fournisseur demandé dans notre base de données : ");
        system.searchSeller(name, "", composanteType);
    }

    public static void buyComponent(){
        System.out.print("donner est le nom du fournisseur chez lequel vous voulez achetez la composante : ");
        String sellerName = Prototype.scanner.nextLine();

        Fournisseur seller = null;
        for(Fournisseur s : system.getSellers()){
            if(s.getFirstName().toUpperCase().equals(sellerName.toUpperCase())){
                seller = s;
                break;
            }
        }

        if(seller == null){
            System.out.println("ce fournisseur n'existe pas");
            return;
        }

        // acheter la composante au près du vendeur
        System.out.print("donner le type de la composante que vous recherchez : ");
        String type = Prototype.scanner.nextLine();

        ArrayList<Composante> composantes = seller.getCorrespondingComponents(type);
        if(composantes.isEmpty()){
            System.out.println("le fournisseur " + seller.getFirstName() + " ne détient pas de composantes du type "+ type);
            return;
        }

        System.out.println("Les différentes composante du type "+ type + " vendu par " + seller.getFirstName() + "sont : \n");
        for(Composante c : composantes){
            System.out.println("•" + c.getName() + " , description : "+ c.getDescription() + " , prix : " + c.getPrice());
        }


        System.out.print("\nVeuillez indiqué le nom de la composante que vous voulez acheter ou exit pour annuler l'achat : ");
        String name = Prototype.scanner.nextLine();

        if(name.equals("exit")){
            return;
        }

        if(Prototype.user.buyComposante(seller, type,name)){
            System.out.println("Bravo pour votre achat du "+ name);
        }else{
            System.out.println("Aucune composante ne correspond au nom : "+ name);
        }

    }

    public static void connexion(){
        while(true){
            System.out.println("\nTAPEZ out pour sortir du systeme,  connexion pour vous connecter ou inscription pour vous inscrire : ");
            String choice = Prototype.scanner.nextLine().toUpperCase();

            if(choice.equals("CONNEXION")){

                String pseudo = "";
                String password = "";

                System.out.print("Veuillez rentrer votre pseudo : ");
                pseudo = Prototype.scanner.nextLine();

                System.out.print("Veuillez rentrer votre mot de passe : ");
                password = Prototype.scanner.nextLine();

                Prototype.user = system.loginUser(pseudo, password);

                if(Prototype.user == null){
                    System.out.println("votre pseudo ou mot de passe est inccorect");
                    continue;
                }

                System.out.println("Vous avez été correctement authentifié");
                return;
            }

            else if(choice.equals("INSCRIPTION")){

                String pseudo = "";
                String password = "";
                String firstName = "";
                String secondName = "";
                String email = "";
                String phoneNumber = "";
                String companyName = "";

                System.out.print("Veuillez rentrer votre pseudo : ");
                pseudo = Prototype.scanner.nextLine();

                System.out.print("Veuillez rentrer votre mot de passe : ");
                password = Prototype.scanner.nextLine();

                System.out.print("veuillez rentrer votre nom : ");
                secondName = Prototype.scanner.nextLine();

                System.out.print("Veuillez rentrer votre prénom : ");
                firstName = Prototype.scanner.nextLine();

                System.out.print("veuillez rentrer votre email : ");
                email = Prototype.scanner.nextLine();

                System.out.print("Veuillez rentrer le nom de votre companie : ");
                companyName = Prototype.scanner.nextLine();

                System.out.print("Veuillez enter votre numéro de téléphone a 10 chiffres : ");
                phoneNumber = Prototype.scanner.nextLine();

                system.signUpUser(companyName, firstName, secondName, email, pseudo, password, phoneNumber);
                Prototype.user = system.loginUser(pseudo, password);

                if(Prototype.user == null){
                    continue;
                }

                System.out.println("Votre compte à correctement été crée");
                return;
            }

            else if(choice.toUpperCase().equals("OUT")){
                Prototype.online = false;
                return;
            }

            System.out.println("Votre choix n'a pas été reconnu par notre systeme");
        }
    }

    public static void setup(){

        //inscription de user et seller

        system.signUpUser(null, "Giovanni", "Belval", "gio@b.com", "belgio", "123456", "1234567890");
        system.signUpUser(null, "Tobias", "Lepoutre", "tob@g.com", "toblep", "123456", "1234567890");

        system.signUpSeller("Google", "Theo", "t@gmail.com", "123456","1234567890","1234 rue de Paris",100);
        system.signUpSeller("Google", "Victor", "v@gmail.com", "123456","1234567890","1234 rue de Paris",100);

        //following

        system.loginUser("belgio","123456").followUser("toblep");
        system.loginUser("belgio","123456").followSeller(("Victor"));

        // create composante
        Fournisseur seller1 = system.loginSeller("Theo","123456");
        Fournisseur seller2 = system.loginSeller("Victor","123456");

        seller1.createComposante("HautParleur", "BooseX100" , "", 30);
        seller1.createComposante("bras", "mechaArm" , "", 25);
        seller1.createComposante("bras", "mechaArm" , "", 25);
        seller1.createComposante("ecran", "Iscreen" , "", 50);
        seller1.createComposante("bras", "mechaArm" , "", 25);

        seller2.createComposante("CPU", "RX900" , "the fastest CPU", 150);
        seller2.createComposante("CPU", "FX1000" , "", 95);
        seller2.createComposante("helice", "Ihelice" , "", 110);
    }
}
