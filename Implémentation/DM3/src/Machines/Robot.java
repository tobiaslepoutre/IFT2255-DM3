package Machines;

import Activity.Activity;
import Activity.Tache;
import Machines.composantes.Composante;
import Users.Utilisateur;

import java.util.ArrayList;
import java.util.Date;

public class Robot {

    /* attributes */
    private String name;
    private String type;
    private String serialNumber;
    private float positionX;
    private float positionY;
    private float positionZ;
    private float speed;
    private int powerLevel;

    /* assosiations */
    private Utilisateur owner;
    private Flotte flotte;
    private ArrayList<Tache> taches;
    private ArrayList<Activity> activities;
    private ArrayList<Composante> composants;


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
    }

    public void addTache(Tache t){
        //on définit que le robot doit faire une tache
        if(!this.taches.contains(t)){
            this.taches.add(t);
            t.assignRobot(this);
        }
    }


    public boolean isBusy(Date startDate, Date endDate) {

        // on parcours toutes les activité et on check que la date n'empiete pas sur
        //la plage des dates d'activité
        for(Activity a : this.activities){
            if(endDate.after(a.getStartDate()) || startDate.before(a.getEndDate())){
                return true;
            }
        }
        return false;
    }

    public void afficherEtat() {
        System.out.println("Name: " + this.name);
        System.out.println("Type: " + this.type);
        System.out.println("Serial number: " + this.serialNumber);
        System.out.println("Position: (" + this.positionX + ", " + this.positionY + ", " + this.positionZ + ")");
        System.out.println("Speed: " + this.speed);
        System.out.println("Power level: " + this.powerLevel);
    }



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public float getPositionX() {
        return this.positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return this.positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public float getPositionZ() {
        return this.positionZ;
    }

    public void setPositionZ(float positionZ) {
        this.positionZ = positionZ;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getPowerLevel() {
        return this.powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    public ArrayList<Activity> getActivities(){
        return this.activities;
    }

    public void participateToActivity(Activity a){
        if(!this.activities.contains(a)){
            a.addParticipantRobot(this);
            this.activities.add(a);
        }
    }

    public void addComposante(Composante c){
        this.composants.add(c);
        c.connectToRobot(this);
    }

    public ArrayList<Composante> getComposants(){
        return this.composants;
    }

    public void joinTheFlotte(Flotte f){
        this.flotte = f;
    }
}
