package Users;

import Activity.Activity;
import Activity.Interet;
import Machines.Flotte;
import Machines.Robot;
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
        if(pseudo.equals(this.pseudo)){
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
            if(i.getName().equals(name) && this.interets.contains(i)){
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
            if(a.getName().equals(name)){
                System.out.println("cette activité de meme nom existe déja");
                return false;
            }
        }

        SystemeRobotix.getInstance().createInteret(type,interetName);
        Interet interet = SystemeRobotix.getInstance().getInteret(type, interetName);
        SystemeRobotix.getInstance().createActivity(reward, startDate, endDate, name , this , interet);
        Activity activity = SystemeRobotix.getInstance().getActivity(name);

        this.createdActivities.add(activity);
        this.interets.add(interet);
        return true;

    }

    public void removeActivity(String activityName){
        Activity toRemove = null;

        for(Activity act :this.createdActivities){
            if(act.getName().equals(activityName)){
                toRemove = act;
                break;
            }
        }

        if(toRemove != null){
            SystemeRobotix.getInstance().removeActivity(toRemove);
            this.createdActivities.remove(toRemove);
        }

    }

    public boolean createRobot(String name, String serialNumber, String type, ArrayList<Composante> composants){

        boolean hasCPU = false;

        if(composants.size() < 2){
            System.out.println("the user used less than 2 components");
            return false;
        }

        if(!this.composantes.containsAll(composants)){
            System.out.println("the user does not have all the components");
            return false;
        }

        for(Composante c : composants){
            if(c.getType().equals("CPU")){
                hasCPU = true;
            }
        }

        if(!hasCPU){
            System.out.println("the user did not use any CPU");
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

    public void createTask(){
        //crée une task et l'ajoute ou non à une activité

    }

    public boolean buyComposante(Fournisseur seller, String type){
        // remove c from the seller because
        // he no longer sells it

        Composante composante = null;
        for(Composante c : seller.getComposantes()){
            if(c.getType().equals(type)){
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
            if(c.getType().equals(type) && c.getName().equals(name)){
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

    public ArrayList<Composante> getComposantes(){
        return this.composantes;
    }


    public boolean assignRobotToActivity(String activityName){
        Activity activity = null;

        for(Activity a : SystemeRobotix.getInstance().getActivities()){
            if(a.getName().equals(activityName)){
                activity = a;
            }
        }

        if(activity == null){
            System.out.println("this activity does not exist");
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
                return true;
            }
        }

        return false;
    }

}
