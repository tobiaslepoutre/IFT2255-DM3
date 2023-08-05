package Machines.composantes;

import Machines.Robot;
import Users.Fournisseur;
import Users.Utilisateur;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * La classe Composante représente une composante d'un robot.
 * @author Tobias Lepoutre
 * @version 1.0
 */
public class Composante {

    /* attributs */
    private String name;
    private String type;
    private String description;
    private float price;
    public static ArrayList<String> types = new ArrayList<>(Arrays.asList("roue","bras","helice","camera", 
            "hautParleur","micro","ecran"));

    /* associations */
    private Utilisateur owner;
    private Fournisseur seller;
    private Robot robot;

    /**
     * Constructeur pour créer une nouvelle composante avec un nom, un type, une description, un prix et un vendeur.
     * @param name Le nom de la composante.
     * @param type Le type de la composante.
     * @param description La description de la composante.
     * @param price Le prix de la composante.
     * @param seller Le vendeur de la composante.
     */
    public Composante(String name, String type, String description, float price, Fournisseur seller) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.seller = seller;
    }

    /**
     * Méthode pour obtenir le nom de la composante.
     * @return Le nom de la composante.
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode pour définir le nom de la composante.
     * @param name Le nouveau nom de la composante.
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Méthode pour obtenir le type de la composante.
     * @return Le type de la composante.
     */
    public String getType(){
        return this.type;
    }

    /**
     * Méthode pour définir le type de la composante, si le type est dans la liste de types valides.
     * @param type Le nouveau type de la composante.
     */
    protected void setType(String type) {
        if (types.contains(type)){
            this.type = type;
        }
    }

    /**
     * Méthode pour obtenir la description de la composante.
     * @return La description de la composante.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Méthode pour définir la description de la composante.
     * @param description La nouvelle description de la composante.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Méthode pour obtenir le prix de la composante.
     * @return Le prix de la composante.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Méthode pour définir le prix de la composante.
     * @param price Le nouveau prix de la composante.
     */
    private void setPrice(float price) {
        this.price = price;
    }

    /**
     * Méthode pour obtenir le vendeur de la composante.
     * @return Le vendeur de la composante.
     */
    public Fournisseur getSeller(){
        return this.seller;
    }

    /**
     * Méthode pour obtenir le propriétaire de la composante.
     * @return Le propriétaire de la composante.
     */
    public Utilisateur getOwner(){
        return this.owner;
    }

    /**
     * Méthode pour définir le propriétaire de la composante.
     * @param user Le nouveau propriétaire de la composante.
     */
    public void setOwner(Utilisateur user){
        this.owner = user;
        this.notifierAchat(user);
    }

    /**
     * Méthode pour notifier le vendeur de l'achat de la composante.
     * @param user L'utilisateur qui a acheté la composante.
     */
    private void notifierAchat(Utilisateur user){
        this.seller.addNotification("Le composant " + this.toString() + "à été acheté par " + user.getPseudo());
    }

    /**
     * Méthode pour connecter la composante à un robot.
     * @param robot Le robot auquel la composante doit être connectée.
     */
    public void connectToRobot(Robot robot){
        this.robot = robot;
    }

    /**
     * Méthode pour déconnecter la composante du robot.
     */
    public void deconstruct(){
        this.robot = null;
    }
}
