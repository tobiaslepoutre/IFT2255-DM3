import Activity.Activity;
import Activity.Tache;
import Activity.action.*;
import Machines.Robot;
import Machines.composantes.Composante;
import Users.Fournisseur;
import Users.Utilisateur;
import System.SystemeRobotix;

import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe Prototype est le point d'entrée du système Robotix,
 * c'est elle qui contien main.
 * @author Giovanni Belval
 * @version 1.0
 */
public class Prototype {

    private static SystemeRobotix system;
    private static Fournisseur seller = null;
    private static Utilisateur user = null;
    private static Scanner scanner = new Scanner(System.in);  // Create a Scanner object
    private static boolean online = true;
    private static LocalDate currentDate = LocalDate.of(2023,01,01);

    public static void main(String[] args) throws IOException {
        ObjectInputStream obj;

        try{
            // we load the previous state of the system

             obj = new ObjectInputStream(new FileInputStream("system.txt"));
             system = (SystemeRobotix)obj.readObject();

        }catch(Exception e){

            System.out.println(e.getMessage());
            system = SystemeRobotix.getInstance();
            Prototype.setup();

        }

        System.out.println("Bienvenue dans Robotix");

        while(Prototype.online){

            //Cas Fournisseur


            //Cas Utilisateur
            if(user == null){
                connexion();
            }

            System.out.println("----------------------------------------------------");
            System.out.println("--------------" + Prototype.currentDate.getYear() + "-" + Prototype.currentDate.getMonth() + "-" + Prototype.currentDate.getDayOfMonth() + "----------------");
            System.out.println("\nVous avez actuellement "+ user.getMoney() + "CAD");
            System.out.println("Que voulez vous faire ? tapez un des choix correspondant ou out pour quittez le système : \n");

            System.out.println("showProfile               -> voir un profil (pseudo)");
            System.out.println("showMyProfile             -> voir mon profil");
            System.out.println("showAll                   -> voir tous les pseudo des profils");
            System.out.println("follow                    -> suivre un autre utilisateur (pseudo)");
            System.out.println("findSeller                -> trouver un fournisseur (nom, adresse, Type de composant voulu)");
            System.out.println("buyComponent              -> Acheter un composant chez un fournisseur");
            System.out.println("createRobot               -> crée un robot en utilisant nos composant actuel");
            System.out.println("showMetrics               -> affiche les métrique de ma flotte");
            System.out.println("manageActivities          -> créer, participé, ou supprimer une activité");
            System.out.println("manageTasks               -> créer et assigner une tache à une activité");
            System.out.println("showActivityDetails       -> montrer les detais d'une activity");
            System.out.println("continue                  -> avancer dans le temps");


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

                case "SHOWMETRICS":
                    //Prototype.showMyMetrics();
                    break;

                case "MANAGEACTIVITIES":
                    Prototype.manageActivities();
                    break;

                case "MANAGETASKS":
                    Prototype.manageTasks();
                    break;

                case "SHOWACTIVITYDETAILS":
                    Prototype.showActivityDetails();
                    break;

                case "CONTINUE":
                    Prototype.moveInTime();
                    break;

                case "OUT":
                    Prototype.online = false;
                    break;

                default:
                    System.out.println("commande non reconnu par le systeme\n");
                    break;
            }

        }

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("system.txt"));
        out.writeObject(system);
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
        System.out.println("Liste des fournisseurs et leurs composantes disponibles :  ");

        for(Fournisseur seller : system.getSellers()){
            System.out.print("• "+seller.getFirstName() + " : ");

            for(Composante c : seller.getComposantes()){
                System.out.print(c.getType() + ", ");
            }
            System.out.println("");
        }

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
            System.out.println("\ndonner le nom de la prochaine composante que vous voulez include dans votre robot \nOu tapez exit pour annulez la creation du robot\nOu tapez pour terminer la creation du robot  : ");

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
            LocalDate startDate = LocalDate.now();

            try{
                startDate = LocalDate.parse(sdate);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

            System.out.print("date de départ dans le format yyyy-mm-jj : ");
            String edate = Prototype.scanner.nextLine();
            LocalDate endDate = LocalDate.now();

            try{
                endDate = LocalDate.parse(edate);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

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

    public static void showActivityDetails(){
        System.out.print("\nDonnez le nom de l'activité : ");
        String name = Prototype.scanner.nextLine();

        for(Activity a : system.getActivities()){
            if(a.getName().toUpperCase().equals(name.toUpperCase())){
                a.showActivityDetails();
                return;
            }
        }

        System.out.println("Cette activité n'existe pas");

    }

    public static void manageTasks(){

        System.out.print("Veuillez entrez le nom de l'activité auqeulle vous voulez assignez des taches : ");
        String activityName = Prototype.scanner.nextLine();

        System.out.print("Veuillez entrez la date de l'activité dans le format yyyy-mm-jj : ");
        String date = Prototype.scanner.nextLine();
        LocalDate localStartDate = LocalDate.parse(date);
        LocalDate executionDate = LocalDate.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        ArrayList<Action> actions = new ArrayList<>();

        System.out.println("cette activité se déroule du ");
        while(true){

            System.out.println("Veuillez entrer le type d'action que vous voulez ajouter à cette tache (Tapez terminer pour conclure) : ");
            System.out.println("translate");
            System.out.println("rotate");
            System.out.println("listening");
            System.out.println("print");
            System.out.println("trigger");
            System.out.println("sound");

            String choice = Prototype.scanner.nextLine();
            
            if(choice.toUpperCase().equals("TRANSLATE")){
                System.out.println("donner les coordonnées et la vitese");

                float x;
                float y;
                float z;
                float v;
                int duration;
                boolean transition;

                System.out.print("x : " );
                x = Float.parseFloat(Prototype.scanner.nextLine());

                System.out.print("y : " );
                y = Float.parseFloat(Prototype.scanner.nextLine());

                System.out.print("z : " );
                z = Float.parseFloat(Prototype.scanner.nextLine());

                System.out.print("speed : " );
                v = Float.parseFloat(Prototype.scanner.nextLine());

                System.out.print("duration : " );
                duration = Integer.parseInt(Prototype.scanner.nextLine());

                System.out.print("transition : (true/false) " );
                transition = Boolean.parseBoolean(Prototype.scanner.nextLine());

                try {
                    actions.add(new TranslateAction(duration, transition, x, y , z, v));
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return;
                }

            }
            
            if(choice.toUpperCase().equals("ROTATE")){
                System.out.println("donner les caractéristique : ");

                float x;
                float y;
                float z;
                float v;
                int duration;
                boolean transition;

                System.out.print("rotation en x : " );
                x = Float.parseFloat(Prototype.scanner.nextLine());

                System.out.print("rotation en y : " );
                y = Float.parseFloat(Prototype.scanner.nextLine());

                System.out.print("rotation en z : " );
                z = Float.parseFloat(Prototype.scanner.nextLine());

                System.out.print("vitese de rotation : " );
                v = Float.parseFloat(Prototype.scanner.nextLine());

                System.out.print("duration : " );
                duration = Integer.parseInt(Prototype.scanner.nextLine());

                System.out.print("transition : (true/false) " );
                transition = Boolean.parseBoolean(Prototype.scanner.nextLine());

                try{
                    actions.add(new RotateAction(duration, transition, x, y , z, v));
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return;
                }
            }
            
            if(choice.toUpperCase().equals("LISTENING")){

                int duration;
                boolean transition;

                System.out.print("duration : " );
                duration = Integer.parseInt(Prototype.scanner.nextLine());

                System.out.print("transition : (true/false) " );
                transition = Boolean.parseBoolean(Prototype.scanner.nextLine());

                try{
                    actions.add(new ListenAction(duration, transition));
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return;
                }
            }

            if(choice.toUpperCase().equals("PRINT")){
                int duration;
                boolean transition;
                String text;

                System.out.print("duration : " );
                duration = Integer.parseInt(Prototype.scanner.nextLine());

                System.out.print("transition : (true/false) " );
                transition = Boolean.parseBoolean(Prototype.scanner.nextLine());

                System.out.print("texte à imprimé : ");
                text = Prototype.scanner.nextLine();

                try{
                    actions.add(new PrintAction(duration, transition, text));
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return;
                }
            }

            if(choice.toUpperCase().equals("TRIGGER")){
                int duration;
                boolean transition;
                String text;

                System.out.print("duration : " );
                duration = Integer.parseInt(Prototype.scanner.nextLine());

                System.out.print("date de déclanchement dans le format yyyy-mm-jj : ");
                String date_ = Prototype.scanner.nextLine();
                LocalDate localStartDate_ = LocalDate.parse(date_);
                LocalDate executionDate_ = LocalDate.from(localStartDate_.atStartOfDay(ZoneId.systemDefault()).toInstant());

                try{
                    actions.add(new TriggerAction(duration, executionDate_));
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return;
                }
            }

            if(choice.toUpperCase().equals("SOUND")){
                int duration;
                boolean transition;
                String text;

                System.out.print("duration : " );
                duration = Integer.parseInt(Prototype.scanner.nextLine());

                System.out.print("transition : (true/false) " );
                transition = Boolean.parseBoolean(Prototype.scanner.nextLine());

                System.out.print("type de son : ");
                String sound = Prototype.scanner.nextLine();

                try{
                    actions.add(new EmitSoundAction(duration, transition, sound));
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return;
                }

            }

            if(choice.toUpperCase().equals("TERMINER")){
                break;
            }
        }

        user.createTask(activityName, executionDate, actions);
    }

    public static void moveInTime(){

        LocalDate last = LocalDate.of(1990,01,01);

        for(Activity activity : system.getActivities()){

            if(activity.getStartDate().isBefore(Prototype.currentDate)){
                return;
            }

            if(last.isBefore(activity.getEndDate())){
                last = activity.getEndDate();
            }

            for(Robot robot : activity.getParticipantsRobot()){
                int powerLevelEnd   = Math.max(0, (int)ChronoUnit.DAYS.between(activity.getEndDate(), activity.getStartDate()));
                int cpuConsommation = 0;

                for(Tache tache : activity.getTasks()){
                    cpuConsommation += Math.max(2,tache.getActions().size() / robot.getComposants().size());
                }

                robot.getOwner().addPoints(activity.getReward());

                robot.statistiques.get("consommationCPU").add(Math.min(cpuConsommation,100));
                robot.statistiques.get("powerLevelUsed").add(Math.min(100, powerLevelEnd));

            }

        }

        currentDate = LocalDate.of(last.getYear() , last.getMonth() , last.getDayOfMonth() + 1);

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

        system.signUpUser(null, "Giovanni", "Belval"  , "gio@b.com", "belgio", "123456", "1234567890");
        system.signUpUser(null, "Tobias"  , "Lepoutre", "tob@g.com", "toblep", "123456", "1234567890");
        system.signUpUser(null, "Raphael" , "Ana"     , "rap@b.com", "rahana", "123456", "1234567890");
        system.signUpUser(null, "Kael"    , "Biaritz" , "kae@g.com", "kaebia", "123456", "1234567890");
        system.signUpUser(null, "Vincent" , "Pascal"  , "vin@b.com", "vinpas", "123456", "1234567890");
        system.signUpUser(null, "Eloise"  , "Lepoutre", "elo@g.com", "elolep", "123456", "1234567890");
        system.signUpUser(null, "Partale" , "Brother" , "par@b.com", "parbro", "123456", "1234567890");
        system.signUpUser(null, "Rémis"   , "Amir"    , "rem@g.com", "remami", "123456", "1234567890");
        system.signUpUser(null, "Auguste" , "Amir"    , "aug@b.com", "augami", "123456", "1234567890");
        system.signUpUser(null, "Mario"   , "Amir"    , "mar@g.com", "marami", "123456", "1234567890");

        system.signUpSeller("Google"  , "Theo", "t@gmail.com", "123456","1234567890","1234 rue de Paris",100);
        system.signUpSeller("Google"  , "Victor", "v@gmail.com", "123456","1234567890","1234 rue de genève",100);
        system.signUpSeller("Facebook", "francis", "ter@gmail.com", "123456","1234567890","1234 rue de Paris",100);
        system.signUpSeller("Netflix" , "edouard", "vit@gmail.com", "123456","1234567890","1234 rue de Paris",100);
        system.signUpSeller("Google"  , "valentin", "ol@gmail.com", "123456","1234567890","1234 rue de Lyon",100);
        system.signUpSeller("Google"  , "grégory", "at@gmail.com", "123456","1234567890","1234 rue de Montreal",100);


        //following

        Utilisateur user1 = system.loginUser("belgio", "123456");
        Utilisateur user2 = system.loginUser("toblep", "123456");
        Utilisateur user3 = system.loginUser("rahana", "123456");
        Utilisateur user4 = system.loginUser("elolep", "123456");
        Utilisateur user5 = system.loginUser("marami", "123456");

        user1.createActivity("creation", "robotique"  , "atelier"      , LocalDate.of(2023,2,1) , LocalDate.of(2023,2,6) , 200);
        user2.createActivity("creation", "robotique"  , "programation" , LocalDate.of(2023,2,1) , LocalDate.of(2023,2,5) , 150);
        user2.createActivity("education", "automobile", "course"       , LocalDate.of(2023,2,5) , LocalDate.of(2023,2,8) , 150);
        user3.createActivity("creation", "robotique"  , "photography"  , LocalDate.of(2023,2,4) , LocalDate.of(2023,2,3) , 150);
        user3.createActivity("creation", "algebre"    , "aviation"     , LocalDate.of(2023,5,2) , LocalDate.of(2023,6,1) , 200);
        user4.createActivity("education", "automobile", "sprint"       , LocalDate.of(2023,6,1) , LocalDate.of(2023,6,5) , 150);
        user4.createActivity("creation", "robotique"  , "devoirs"      , LocalDate.of(2023,5,1) , LocalDate.of(2023,7,5) , 200);
        user5.createActivity("creation", "informatif" , "seminaire"    , LocalDate.of(2023,5,12), LocalDate.of(2023,5,15), 150);
        user5.createActivity("education", "automobile", "formule1"     , LocalDate.of(2023,8,1), LocalDate.of(2023 ,8,5) , 150);
        user1.createActivity("creation", "robotique"  , "assemblage"   , LocalDate.of(2023,6,1), LocalDate.of(2023 ,6,2) , 150);
        user1.createActivity("creation", "robotique"  , "spaceX"       , LocalDate.of(2023,2,1), LocalDate.of(2023 ,6,1) , 200);
        user1.createActivity("education", "automobile", "karting"      , LocalDate.of(2023,6,1), LocalDate.of(2023 ,6,5) , 150);


        Fournisseur seller1 = system.loginSeller("Theo"    ,"123456");
        Fournisseur seller2 = system.loginSeller("Victor"  ,"123456");
        Fournisseur seller3 = system.loginSeller("francis" ,"123456");
        Fournisseur seller4 = system.loginSeller("edouard" ,"123456");
        Fournisseur seller5 = system.loginSeller("valentin","123456");
        Fournisseur seller6 = system.loginSeller("grégory" ,"123456");

        seller1.createComposante("HautParleur", "BooseX100" , "", 30);
        seller1.createComposante("bras", "mechaArm" , "", 25);
        seller1.createComposante("bras", "mechaArm" , "", 25);
        seller1.createComposante("ecran", "Iscreen" , "", 50);
        seller1.createComposante("bras", "mechaArm" , "", 25);

        seller2.createComposante("CPU", "RX900" , "the fastest CPU", 150);
        seller2.createComposante("CPU", "FX1000" , "", 95);
        seller2.createComposante("helice", "Ihelice" , "", 110);
        seller2.createComposante("CPU", "RX900" , "the fastest CPU", 150);
        seller2.createComposante("CPU", "FX1000" , "", 95);
        seller2.createComposante("helice", "Ihelice" , "", 110);

        seller3.createComposante("micro", "MRX18" , "slowest HP", 10);
        seller3.createComposante("hautParleur", "HP360" , "", 95);
        seller3.createComposante("helice", "Ihelice" , "", 110);
        seller3.createComposante("CPU", "RX901" , "the fastest CPU for IA", 250);
        seller3.createComposante("ecran", "Iscreen" , "", 95);
        seller3.createComposante("helice", "Ihelice" , "", 110);

        seller4.createComposante("CPU", "FX1000" , "", 95);
        seller4.createComposante("helice", "Ihelice" , "", 110);
        seller4.createComposante("bras", "mechaArm" , "", 25);
        seller4.createComposante("ecran", "Iscreen" , "", 50);
        seller4.createComposante("hautParleur", "HP360" , "", 95);

    }
}
