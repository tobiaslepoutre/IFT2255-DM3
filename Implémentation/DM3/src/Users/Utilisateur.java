package Users;

import Activity.*;
import Activity.action.Action;
import Machines.*;
import Machines.composantes.Composante;
import System.SystemeRobotix;

import java.util.ArrayList;
import java.util.Date;

/**
 * La classe Utilisateur représente un utilisateur du système Robotix.
 * C'est une sous-classe de la classe Acteur.
 *  * @author Giovanni Belval
 *  * @version 1.0
 */
public class Utilisateur extends Acteur {

    /* Attributs */
    private String secondName;
    private String pseudo;
    private int    points;

    /* Associations */
    private ArrayList<Interet> interets;
    private ArrayList<Activity> createdActivities;
    private ArrayList<Activity> activities;
    private Flotte flotte;
    private ArrayList<Composante> composantes;

    /**
     * Constructeur de la classe Utilisateur
     * @param compagnieName Le nom de la compagnie de l'utilisateur
     * @param firstName Le prénom de l'utilisateur
     * @param secondName Le second nom de l'utilisateur
     * @param password Le mot de passe de l'utilisateur
     * @param pseudo Le pseudo de l'utilisateur
     * @param email L'adresse email de l'utilisateur
     * @param phoneNumber Le numéro de téléphone de l'utilisateur
     * @throws Exception Lance une exception si un problème survient lors de la création de l'utilisateur
     */
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

    /**
     * Méthode pour changer le pseudo de l'utilisateur
     * @param pseudo Le nouveau pseudo de l'utilisateur
     * @return Retourne true si le pseudo a été changé avec succès, false sinon
     */
    public boolean changePseudo(String pseudo){
        try {
            this.setPseudo(pseudo);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Méthode pour suivre un autre utilisateur
     * @param pseudo Le pseudo de l'utilisateur à suivre
     * @return Retourne true si l'utilisateur est suivi avec succès, false sinon
     */
    public boolean followUser(String pseudo){
        if(pseudo.toUpperCase().equals(this.pseudo.toUpperCase())){
            return false;
        }
        return super.followUser(pseudo);
    }

    /**
     * Méthode pour ajouter des points à l'utilisateur
     * @param points Le nombre de points à ajouter
     */
    public void addPoints(int points){
        this.setPoints(this.points + points);
    }

    /**
     * Méthode pour obtenir le nombre de points de l'utilisateur
     * @return Le nombre de points de l'utilisateur
     */
    public int getPoints() {
        return points;
    }

    /**
     * Méthode pour obtenir le pseudo de l'utilisateur
     * @return Le pseudo de l'utilisateur
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Méthode pour obtenir le second nom de l'utilisateur
     * @return Le second nom de l'utilisateur
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * Méthode privée pour définir le nombre de points de l'utilisateur
     * @param points Le nombre de points à définir
     */
    private void setPoints(int points) {
        this.points = points;
    }

    /**
     * Méthode privée pour définir le pseudo de l'utilisateur
     * @param pseudo Le pseudo à définir
     * @throws Exception Lance une exception si le pseudo est déjà utilisé
     */
    private void setPseudo(String pseudo) throws Exception {

        for(Utilisateur user : SystemeRobotix.getInstance().getUsers()){
            if(user.getPseudo().equals(pseudo)){
                throw new Exception("ce pseudo existe déja");
            }
        }
        this.pseudo = pseudo;
    }


    /**
     * Méthode privée pour définir le second nom de l'utilisateur
     * @param secondName Le second nom à définir
     */
    private void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * Méthode pour représenter l'utilisateur sous forme de chaîne de caractères
     * @return Une représentation textuelle de l'utilisateur
     */
    public String toString() {
        return "Utilisateur | Pseudo : " + this.getPseudo() + ", Name : " + this.getFirstName();
    }

    /**
     * Méthode pour ajouter un intérêt à l'utilisateur
     * @param type Le type de l'intérêt
     * @param name Le nom de l'intérêt
     * @return Retourne true si l'intérêt a été ajouté avec succès, false sinon
     */
    public boolean ajouterInteret(String type, String name){
        if(this.interets.size() == 10){
            return false;
        }

        SystemeRobotix.getInstance().createInteret(type, name);
        this.interets.add(SystemeRobotix.getInstance().getInteret(type,name));
        return true;
    }

    /**
     * Méthode pour supprimer un intérêt de l'utilisateur
     * @param name Le nom de l'intérêt à supprimer
     * @return Retourne true si l'intérêt a été supprimé avec succès, false sinon
     */
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

    /**
     * Méthode pour obtenir la liste des intérêts de l'utilisateur
     * @return La liste des intérêts de l'utilisateur
     */
    public ArrayList<Interet> getInterets(){
        return this.interets;
    }

    /**
     * Méthode pour obtenir la liste des activités créées par l'utilisateur
     * @return La liste des activités créées par l'utilisateur
     */
    public ArrayList<Activity> getCreatedActivities(){
        return this.createdActivities;
    }

    /**
     * Crée une nouvelle activité et l'ajoute à la liste des activités créées.
     * Si l'activité est créée avec succès, elle est ajoutée à la liste des intérêts de l'utilisateur.
     *
     * @param type Le type de l'activité à créer.
     * @param interetName Le nom de l'intérêt associé à l'activité.
     * @param name Le nom de l'activité.
     * @param startDate La date de début de l'activité.
     * @param endDate La date de fin de l'activité.
     * @param reward La récompense pour l'activité.
     * @return true si l'activité est créée avec succès, false sinon.
     */
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

    /**
     * Supprime une activité de la liste des activités créées.
     *
     * @param activityName Le nom de l'activité à supprimer.
     * @return true si l'activité est supprimée avec succès, false sinon.
     */
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

    /**
     * Affiche toutes les activités créées par l'utilisateur.
     */
    public void showCreatedActivities(){
        for(Activity a : this.createdActivities){
            System.out.println("    • " + a.getName());
        }
    }

    /**
     * Affiche toutes les activités auxquelles l'utilisateur participe.
     */
    public void showActivities(){
        for(Activity a : this.activities){
            System.out.println("    • " + a.getName());
        }
    }

    /**
     * Affiche tous les intérêts de l'utilisateur.
     */
    public void showInterets(){
        for(Interet i : this.interets){
            System.out.println("    • type d'interet : "+i.getType() + "  , nom : "+i.getName());
        }
    }

    /**
     * Crée un robot et l'ajoute à la flotte de l'utilisateur.
     *
     * @param name Le nom du robot.
     * @param serialNumber Le numéro de série du robot.
     * @param type Le type du robot.
     * @param composants La liste des composants à ajouter au robot.
     * @return true si le robot est créé avec succès, false sinon.
     */
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

    /**
     * Retourne la flotte de l'utilisateur.
     *
     * @return La flotte de l'utilisateur.
     */
    public Flotte getFlotte(){
        return this.flotte;
    }

    /**
     * Crée une tâche et l'associe à une activité.
     *
     * @param activityName Le nom de l'activité à laquelle la tâche sera associée.
     * @param executionDate La date d'exécution de la tâche.
     * @param actionsOfTask La liste des actions à ajouter à la tâche.
     * @return true si la tâche est créée avec succès, false sinon.
     */
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

    /**
     * Achète une composante auprès d'un fournisseur.
     *
     * @param seller Le fournisseur auprès duquel la composante sera achetée.
     * @param type Le type de la composante à acheter.
     * @return true si l'achat est effectué avec succès, false sinon.
     */
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

    /**
     * Achète une composante spécifique auprès d'un fournisseur.
     *
     * @param seller Le fournisseur auprès duquel la composante sera achetée.
     * @param type Le type de la composante à acheter.
     * @param name Le nom de la composante à acheter.
     * @return true si l'achat est effectué avec succès, false sinon.
     */
    public boolean buyComposante(Fournisseur seller, String type, String name){

        // remove c from the seller because
        // he no longer sells it

        Composante composante = null;
        for(Composante c : seller.getComposantes()){
            if(c.getType().toUpperCase().equals(type.toUpperCase()) && c.getName().toUpperCase().equals(name.toUpperCase())){
                // we check if the user has enougth money to buy the component

                if(this.getMoney() >= c.getPrice()){
                    // on met à jour les portefeuilles de l'acheteur et du vendeur.

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

    /**
     * Récupère une composante à partir de son nom.
     *
     * @param name Le nom de la composante à récupérer.
     * @return La composante si elle est trouvée, null sinon.
     */
    public Composante getComposante(String name){
        for(Composante c : this.composantes){
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

    /**
     * Retourne la liste des composantes de l'utilisateur.
     *
     * @return La liste des composantes de l'utilisateur.
     */
    public ArrayList<Composante> getComposantes(){
        return this.composantes;
    }

    /**
     * Affecte un robot à une activité.
     *
     * @param activityName Le nom de l'activité à laquelle le robot sera affecté.
     * @return true si le robot est affecté avec succès, false sinon.
     */
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
