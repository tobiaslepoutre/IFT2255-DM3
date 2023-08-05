import Machines.composantes.Composante;
import Users.Fournisseur;
import Users.Utilisateur;
import System.SystemeRobotix;

import java.time.LocalDate;
import java.time.ZoneId;
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

            System.out.println("showProfile        -> voir un profil (pseudo)");
            System.out.println("showMyProfile      -> voir mon profil");
            System.out.println("showAll            -> voir tous les pseudo des profils");
            System.out.println("follow             -> suivre un autre utilisateur (pseudo)");
            System.out.println("findSeller         -> trouver un fournisseur (nom, adresse, Type de composant voulu)");
            System.out.println("buyComponent       -> Acheter un composant chez un fournisseur");
            System.out.println("createRobot        -> crée un robot en utilisant nos composant actuel");
            System.out.println("manageActivities   -> créer, participé, ou supprimer une activité");
            System.out.println("manageTasks        -> créer et assigner une tache à une activité");
            //TODO : buyMultipleCoponent pour se faciliter la vie

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

                case "CREATEROBOT":
                    Prototype.createRobot();
                    break;

                case "MANAGEACTIVITIES":
                    Prototype.manageActivities();
                    break;

                case "OUT":
                    Prototype.online = false;
                    break;

                default:
                    System.out.println("commande non reconnu par le systeme\n");
                    break;
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

    public static void createRobot(){

        // choisir un assembleur qui fournira le numéro de série
        // l'assemblage est payant

        System.out.println("voici la liste des fournisseur capable d'assembler vore robot, le prix d'assemblage est de 10CAD");
        for(Fournisseur seller : system.getSellers()){
            System.out.println("• "+ seller.getFirstName());
        }

        System.out.print("\nEntrez le nom du fournisseur que vous choisissez : ");
        String sellerName = Prototype.scanner.nextLine();
        Fournisseur seller = system.getSeller(sellerName);

        if(seller == null){
            System.out.println("Ce vendeurn'existe pas");
            return;
        }

        if(user.getMoney() < 10){
            System.out.println("Vousn'avez pas assez d'argent pour l'assemblage");
            return;
        }

        String serialNumber = ""+seller.hashCode();

        System.out.print("nom du robot : ");
        String nom = Prototype.scanner.nextLine();

        System.out.print("type du robot : ");
        String type = Prototype.scanner.nextLine();

        System.out.println("vous  devez maintenant selectionné les composantes que vos founissez au robot\n voici la liste de vos composantes : ");

        ArrayList<Composante> forBuild = new ArrayList<>();

        while(true){
            for(Composante c : user.getComposantes()){
                if(!forBuild.contains(c)){
                    System.out.println("• "+ c.getName());
                }
            }
            System.out.println("donner le nom de la prochaine composante que vous voulez include dans votre robot \nOu tapez exit pour annulez la creation du robot\nOu tapez pour terminer la creation du robot  : ");

            String text = Prototype.scanner.nextLine();
            if(text.toUpperCase().equals("EXIT")){
                return;
            }

            if(text.toUpperCase().equals("TERMINER")){
                break;
            }

            Composante c = user.getComposante(text);

            if(c == null){
                System.out.println("vous ne disposez pas de cette composante");
                continue;
            }
            else{
                forBuild.add(c);
            }

        }

        if(user.createRobot(nom, serialNumber, type, forBuild)){
            System.out.println("votre robot à parfaitement été crée");

            user.setMoney(user.getMoney() - 20);
            seller.setMoney(seller.getMoney() + 20);

            return;
        }
    }

    public static void manageActivities(){
        System.out.println("comment souhaitez vous gerer les activité ?");
        System.out.print("creation pour crée une nouvelle activité, remove pour en supprimer et attempt pour participer : ");
        String choice = Prototype.scanner.nextLine();

        if(choice.toUpperCase().equals("CREATION")){

            System.out.println("Veuillez s'il vous plait rentrer les informations concernant votre activité");

            System.out.print("nom de l'activité  : ");
            String name = Prototype.scanner.nextLine();

            System.out.print("date de départ dans le format yyyy-mm-jj : ");
            String sdate = Prototype.scanner.nextLine();
            LocalDate localStartDate = LocalDate.parse(sdate);
            Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            System.out.print("date de départ dans le format yyyy-mm-jj : ");
            String edate = Prototype.scanner.nextLine();
            LocalDate localEndDate = LocalDate.parse(edate);
            Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            System.out.print("le nombre de points en récompense   : ");
            int reward = Integer.parseInt(Prototype.scanner.nextLine());

            System.out.print("type d'interet pour cette activité : ");
            String type = Prototype.scanner.nextLine();

            System.out.print("nom de l'interet de cette activité : ");
            String interetName = Prototype.scanner.nextLine();


            if(user.createActivity(type, interetName, name, startDate, endDate, reward)){
                System.out.println("L'activité à été crée avec succès");
            }
        }

        else if(choice.toUpperCase().equals("REMOVE")){
            System.out.print("Nom de l'activité : ");
            String name = Prototype.scanner.nextLine();

            if(user.removeActivity(name)){
                System.out.println("L'activité à bien été supprimmé");
            }
            else{
                System.out.println("L'activité n'existe pas ou vous n'en êtes pas le créateur");
                return;
            }
        }

        else if(choice.toUpperCase().equals("ATTEMPT")){
            System.out.print("Nom de l'activité : ");
            String name = Prototype.scanner.nextLine();

            user.assignRobotToActivity(name);
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

        Utilisateur user1 = system.loginUser("belgio", "123456");

        user1.createActivity("creation", "robotique", "atelier" , new Date(2023,05,01), new Date(2023,05,05), 150);
        user1.createActivity("creation", "robotique", "programation" , new Date(2023,05,01), new Date(2023,04,05), 150);
        user1.createActivity("education", "automobile", "course" , new Date(2023,06,01), new Date(2023,06,05), 150);

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
