package Machines;

import Activity.Activity;
import Activity.Tache;
import Machines.composantes.Composante;
import Users.Utilisateur;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * La classe Robot représente un robot individuel.
 * <p>
 * Un robot a un nom, un type, un numéro de série, une position, une vitesse, et un niveau de puissance.
 * Un robot a aussi une liste de tâches à accomplir, une liste d'activités auxquelles il participe,
 * et une liste de composants qui le composent.
 * </p>
 *
 * @author Tobias Lepoutre
 * @version 1.0
 */
public class Robot implements Serializable {

    /* attributs */
    private String name;
    private String type;
    private String serialNumber;
    private float positionX;
    private float positionY;
    private float positionZ;
    private float speed;
    private int powerLevel;
    public HashMap<String, ArrayList<Integer>> statistiques;

    /* associations */
    private Utilisateur owner;
    private Flotte flotte;
    private ArrayList<Tache> taches;
    private ArrayList<Activity> activities;
    private ArrayList<Composante> composants;

    /**
     * Construit un nouveau robot avec un nom, un type, un numéro de série, et un propriétaire spécifié.
     *
     * @param name Le nom du robot.
     * @param type Le type du robot.
     * @param serialNumber Le numéro de série du robot.
     * @param owner Le propriétaire du robot.
     */
    public Robot(String name, String type, String serialNumber, Utilisateur owner) {

        this.name = name;
        this.type = type;

        this.serialNumber = serialNumber;
        this.positionX = 0;
        this.positionY = 0;
        this.positionZ = 0;
        this.speed = 0;
        this.powerLevel = 100;

        this.taches = new ArrayList<>();
        this.activities = new ArrayList<>();
        this.composants = new ArrayList<>();
        this.owner = owner;

        this.statistiques = new HashMap<>();
        this.statistiques.put("consommationCPU" , new ArrayList<>());
        this.statistiques.put("powerLevelUsed" , new ArrayList<>());
    }

    /**
     * Ajoute une tâche à la liste des tâches du robot si elle n'est pas déjà présente.
     *
     * @param t La tâche à ajouter.
     */
    public void addTache(Tache t){
        if(!this.taches.contains(t)){
            this.taches.add(t);
            t.assignRobot(this);
        }
    }

    /**
     * Vérifie si le robot est occupé pendant une période spécifiée.
     *
     * @param startDate La date de début de la période.
     * @param endDate La date de fin de la période.
     * @return true si le robot est occupé, false sinon.
     */
    public boolean isBusy(LocalDate startDate, LocalDate endDate) {
        // on parcourt toutes les activités et on vérifie que la date n'empiète pas sur
        // la plage horaire des autres activités.
        for(Activity a : this.activities){
            if(!(endDate.isBefore(a.getStartDate()) || startDate.isAfter(a.getEndDate()))){
                return true;
            }
        }
        return false;
    }

    /**
     * Affiche l'état actuel du robot, y compris son nom, son type, son numéro de série, sa position,
     * sa vitesse et son niveau de puissance.
     */
    public void afficherEtat() {
        System.out.println("Name: " + this.name);
        System.out.println("Type: " + this.type);
        System.out.println("Serial number: " + this.serialNumber);
        System.out.println("Position: (" + this.positionX + ", " + this.positionY + ", " + this.positionZ + ")");
        System.out.println("Speed: " + this.speed);
        System.out.println("Power level: " + this.powerLevel);
    }

    /**
     * Obtient le nom du robot.
     *
     * @return Le nom du robot.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Définit le nom du robot.
     *
     * @param name Le nouveau nom du robot.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtient le type du robot.
     *
     * @return Le type du robot.
     */
    public String getType() {
        return this.type;
    }


    /**
     * Définit le type du robot.
     *
     * @param type Le nouveau type du robot.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Obtient le numéro de série du robot.
     *
     * @return Le numéro de série du robot.
     */
    public String getSerialNumber() {
        return this.serialNumber;
    }

    /**
     * Définit le numéro de série du robot.
     *
     * @param serialNumber Le nouveau numéro de série du robot.
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Obtient la position X du robot.
     *
     * @return La position X du robot.
     */
    public float getPositionX() {
        return this.positionX;
    }

    /**
     * Définit la position X du robot.
     *
     * @param positionX La nouvelle position X du robot.
     */
    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    /**
     * Obtient la position Y du robot.
     *
     * @return La position Y du robot.
     */
    public float getPositionY() {
        return this.positionY;
    }

    /**
     * Définit la position Y du robot.
     *
     * @param positionY La nouvelle position Y du robot.
     */
    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    /**
     * Obtient la position Z du robot.
     *
     * @return La position Z du robot.
     */
    public float getPositionZ() {
        return this.positionZ;
    }


    /**
     * Définit la position Z du robot.
     *
     * @param positionZ La nouvelle position Z du robot.
     */
    public void setPositionZ(float positionZ) {
        this.positionZ = positionZ;
    }

    /**
     * Obtient la vitesse du robot.
     *
     * @return La vitesse du robot.
     */
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Définit la vitesse du robot.
     *
     * @param speed La nouvelle vitesse du robot.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Obtient le niveau de puissance du robot.
     *
     * @return Le niveau de puissance du robot.
     */
    public int getPowerLevel() {
        return this.powerLevel;
    }

    /**
     * Définit le niveau de puissance du robot.
     *
     * @param powerLevel Le nouveau niveau de puissance du robot.
     */
    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    /**
     * Obtient la liste des activités du robot.
     *
     * @return La liste des activités du robot.
     */
    public ArrayList<Activity> getActivities(){
        return this.activities;
    }

    /**
     * Ajoute le robot à une activité s'il n'y participe pas déjà.
     *
     * @param a L'activité à laquelle le robot doit participer.
     */
    public void participateToActivity(Activity a){
        if(!this.activities.contains(a)){
            a.addParticipantRobot(this);
            this.activities.add(a);
        }
    }

    /**
     * Ajoute une composante au robot.
     *
     * @param c La composante à ajouter.
     */
    public void addComposante(Composante c){
        this.composants.add(c);
        c.connectToRobot(this);
    }

    /**
     * Obtient la liste des composants du robot.
     *
     * @return La liste des composants du robot.
     */
    public ArrayList<Composante> getComposants(){
        return this.composants;
    }

    /**
     * Fait rejoindre le robot à une flotte.
     *
     * @param f La flotte que le robot doit rejoindre.
     */
    public void joinTheFlotte(Flotte f){
        this.flotte = f;
    }

    /**
     *
     *
     */
    public Utilisateur getOwner(){
        return this.owner;
    }
}
