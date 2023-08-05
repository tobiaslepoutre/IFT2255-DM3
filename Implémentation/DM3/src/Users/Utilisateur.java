package Users;

import Activity.*;
import Activity.action.Action;
import Machines.*;
import Machines.composantes.Composante;
import System.SystemeRobotix;

import java.util.ArrayList;
import java.util.Date;

public class Utilisateur extends Acteur {
    
    /* attributes */
    private String secondName;
    private String pseudo;
    private int    points;

    /* Assosiations */
    private ArrayList<Interet> interets;
    private ArrayList<Activity> createdActivities;
    private ArrayList<Activity> activities;
    private Flotte flotte;
    private ArrayList<Composante> composantes;
    
    /* constructeur */
    public Utilisateur(String compagnieName,String firstName,String secondName, String password, String pseudo,String email ,String phoneNumber) throws Exception{
        super(compagnieName,firstName,password,email,phoneNumber);
        this.setPoints(0);
        this.setPseudo(pseudo);
        this.setSecondName(secondName);

        this.interets = new ArrayList<>();

        this.createdActivities = new ArrayList<>();
        this.activities = new ArrayList<>();
        this.composantes = new ArrayList<>();
        this.flotte = new Flotte();
    }

    /* Methods*/
    public boolean changePseudo(String pseudo){
        try {
            this.setPseudo(pseudo);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean followUser(String pseudo){
        if(pseudo.toUpperCase().equals(this.pseudo.toUpperCase())){
            return false;
        }
        return super.followUser(pseudo);
    }

    public void addPoints(int points){
        this.setPoints(this.points + points);
    }

    public int getPoints() {
        return points;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getSecondName() {
        return secondName;
    }

    private void setPoints(int points) {
        this.points = points;
    }

    private void setPseudo(String pseudo) throws Exception {

        for(Utilisateur user : SystemeRobotix.getInstance().getUsers()){
            if(user.getPseudo().equals(pseudo)){
                throw new Exception("ce pseudo existe déja");
            }
        }
        this.pseudo = pseudo;
    }

     private void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String toString() {
        return "Utilisateur | Pseudo : " + this.getPseudo() + ", Name : " + this.getFirstName();
    }

    public boolean ajouterInteret(String type, String name){
        if(this.interets.size() == 10){
            return false;
        }

        SystemeRobotix.getInstance().createInteret(type, name);
        this.interets.add(SystemeRobotix.getInstance().getInteret(type,name));
        return true;
    }

    public boolean suppripmerInteret(String name){
        for(Interet i : SystemeRobotix.getInstance().getInterets()){
            if(i.getName().toUpperCase().equals(name.toUpperCase()) && this.interets.contains(i)){
                this.interets.remove(i);
                i.removeInterestedUser(this.getPseudo());
                return true;
            }
        }

        return false;
    }

    public ArrayList<Interet> getInterets(){
        return this.interets;
    }

    public ArrayList<Activity> getCreatedActivities(){
        return this.createdActivities;
    }

    public boolean createActivity(String type, String interetName, String name, Date startDate, Date endDate, int reward){
        // crée une nouvelle activité

        for(Activity a : SystemeRobotix.getInstance().getActivities()){
            if(a.getName().toUpperCase().equals(name.toUpperCase())){
                System.out.println("cette activité de meme nom existe déja");
                return false;
            }
        }

        SystemeRobotix.getInstance().createInteret(type,interetName);
        Interet interet = SystemeRobotix.getInstance().getInteret(type, interetName);
        SystemeRobotix.getInstance().createActivity(reward, startDate, endDate, name , this , interet);
        Activity activity = SystemeRobotix.getInstance().getActivity(name);

        this.createdActivities.add(activity);

        if(!this.interets.contains(interet)){
            this.interets.add(interet);

        }
        return true;

    }

    public boolean removeActivity(String activityName){
        Activity toRemove = null;

        for(Activity act :this.createdActivities){
            if(act.getName().toUpperCase().equals(activityName.toUpperCase())){
                toRemove = act;
                break;
            }
        }

        if(toRemove != null){
            SystemeRobotix.getInstance().removeActivity(toRemove); //will remove the activity in teh correspondding interet
            this.createdActivities.remove(toRemove);

            return true;
        }

        return false;

    }

    public void showCreatedActivities(){
        for(Activity a : this.createdActivities){
            System.out.println("    • " + a.getName());
        }
    }

    public void showActivities(){
        for(Activity a : this.activities){
            System.out.println("    • " + a.getName());
        }
    }

    public void showInterets(){
        for(Interet i : this.interets){
            System.out.println("    • type d'interet : "+i.getType() + "  , nom : "+i.getName());
        }
    }

    public boolean createRobot(String name, String serialNumber, String type, ArrayList<Composante> composants){

        boolean hasCPU = false;

        if(composants.size() < 2){
            System.out.println("Vous avez moins de deux composantes pour la création de votre robot");
            return false;
        }

        if(!this.composantes.containsAll(composants)){
            System.out.println("the user does not have all the components");
            return false;
        }

        for(Composante c : composants){
            if(c.getType().toUpperCase().equals("CPU")){
                hasCPU = true;
            }
        }

        if(!hasCPU){
            System.out.println("vous n'avez pas utilisez de CPU");
            return false;
        }

        Robot r = new Robot(name, type, serialNumber, this);
        // we add the components in the robot
        for(Composante c : composants){
            r.addComposante(c);
        }
        //we remove the component from the list of available components
        for(Composante c : r.getComposants()){
            this.composantes.remove(c);
        }

        this.flotte.ajouterRobot(r);
        r.joinTheFlotte(this.flotte);
        return true;
    }

    public Flotte getFlotte(){
        return this.flotte;
    }

    public boolean createTask(String activityName, Date executionDate, ArrayList<Action> actionsOfTask){
        //crée une task et l'ajoute ou non à une activité

        if(actionsOfTask.isEmpty()){
            return false;
        }
        Activity activity = null;
        for(Activity a : this.createdActivities){
            if(a.getName().toUpperCase().equals(activityName.toUpperCase())){
                activity = a;
            }
        }

        if(activity == null){
            return false;
        }

        Tache tache = new Tache(executionDate, activity);
        for(Action a : actionsOfTask){

            tache.addAction(a);
            a.assignToTask(tache);

        }
        return true;

    }

    public boolean buyComposante(Fournisseur seller, String type){
        // remove c from the seller because
        // he no longer sells it

        Composante composante = null;
        for(Composante c : seller.getComposantes()){
            if(c.getType().toUpperCase().equals(type.toUpperCase())){
                c.setOwner(this);
                this.composantes.add(c);
                composante = c;
                break;
            }
        }

        if(composante != null){
            seller.deleteComposante(composante);
            return true;
        }
        return false;
    }

    public boolean buyComposante(Fournisseur seller, String type, String name){

        // remove c from the seller because
        // he no longer sells it

        Composante composante = null;
        for(Composante c : seller.getComposantes()){
            if(c.getType().toUpperCase().equals(type.toUpperCase()) && c.getName().toUpperCase().equals(name.toUpperCase())){
                // we check if the user has enougth money to buy the component

                if(this.getMoney() >= c.getPrice()){
                    // on met a jour les portefeuilles de l'acheteur et du vendeur.

                    this.setMoney(this.getMoney() - c.getPrice());
                    c.getSeller().setMoney(this.getMoney() + c.getPrice());

                    // on récupère la composante et on la supprime du catalogue du vendeur
                    c.setOwner(this);
                    this.composantes.add(c);
                    composante = c;
                    break;
                }

            }
        }

        if(composante != null){
            seller.deleteComposante(composante);
            return true;
        }
        return false;
    }

    public Composante getComposante(String name){
        for(Composante c : this.composantes){
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

    public ArrayList<Composante> getComposantes(){
        return this.composantes;
    }


    public boolean assignRobotToActivity(String activityName){
        Activity activity = null;

        for(Activity a : SystemeRobotix.getInstance().getActivities()){
            if(a.getName().toUpperCase().equals(activityName.toUpperCase())){
                activity = a;
            }
        }

        if(activity == null){
            System.out.println("Cette activité n'existe pas");
            return false;
        }

        for(Robot r : this.flotte.getRobots()){
            if(!r.isBusy(activity.getStartDate() , activity.getEndDate())){

                activity.addParticipantRobot(r);
                activity.addParticipant(this);
                r.participateToActivity(activity);

                if(!this.activities.contains(activity)){
                    this.activities.add(activity);
                }
                System.out.println("Vous avez inscrit votre robot :"+r.getName()+" `l'acivité "+ activity.getName());
                return true;
            }
        }

        System.out.println("vous n'avez pas de robot disponible durant la periode couverte par l'activité");
        return false;
    }

}
