package Machines.composantes;

import Machines.Robot;
import Users.Fournisseur;
import Users.Utilisateur;

import java.util.ArrayList;
import java.util.Arrays;

public class Composante {

    private String name;
    private String type;
    private String description;
    private float price;
    public static ArrayList<String> types = new ArrayList<>(Arrays.asList("roue","bras","helice","camera", "hautParleur","micro","ecran"));

    /* assosiations */
    private Utilisateur owner;
    private Fournisseur seller;
    private Robot robot;

    public Composante(String name, String type, String description, float price, Fournisseur seller) {
        // on initialize en précisant le vendeur
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;

        this.seller = seller;
    }


    public String getName() {
        return name;
    }

    public String getType(){
        return this.type;
    }

    private void setName(String name) {
        this.name = name;
    }

    protected void setType(String type) {
        if (types.contains(type)){
            this.type = type;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    private void setPrice(float price) {
        this.price = price;
    }

    public Fournisseur getSeller(){
        return this.seller;
    }

    public Utilisateur getOwner(){
        return this.owner;
    }

    public void setOwner(Utilisateur user){
        this.owner = user;
        this.notifierAchat(user);
    }

    private void notifierAchat(Utilisateur user){
        this.seller.addNotification("Le composant " + this.toString() + "à été acheté par " + user.getPseudo());
    }

    public void connectToRobot(Robot robot){
        //use when a user construc a robot
        //using that composante
        this.robot = robot;
    }

    public void deconstruct(){
        //use when you whant to dismantle that part
        this.robot = null;
    }
}
