package Users;

import java.util.ArrayList;

import Machines.composantes.*;
import System.SystemeRobotix;

/**
 * La classe "Fournisseur" hérite de la classe "Acteur". Un "Fournisseur" est un type d'"Acteur"
 * qui fournit des composantes.
 * @author Giovanni Belval
 * @version 1.0
 */
public class Fournisseur extends Acteur {
    
    /* Attributs */
    private String location;
    private int capacity;

    /* Association */
    private ArrayList<Composante> composantes;

    /**
     * Constructeur de la classe "Fournisseur".
     *
     * @param companieName The company name of the provider.
     * @param firstName The first name of the provider.
     * @param password The password of the provider.
     * @param email The email of the provider.
     * @param phoneNumber The phone number of the provider.
     * @param location The location of the provider.
     * @param capacity The capacity of the provider.
     */
    public Fournisseur(String companieName, String firstName, String password, String email, String phoneNumber, String location, int capacity) throws Exception{
        super(companieName, firstName, password, email, phoneNumber);
        this.setLocation(location);
        this.setCapacity(capacity);
        this.composantes = new ArrayList<>();
    }

    /**
     * Récupère l'emplacement du fournisseur.
     *
     * @return L'emplacement du fournisseur.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Récupère la capacité du fournisseur.
     *
     * @return La capacité du fournisseur.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Définit le prénom du fournisseur.
     *
     * @param firstName Le prénom à définir.
     */
    private void setFirstName(String firstName){
        //overiding because in that case the name should be unique.
        for(Fournisseur user : SystemeRobotix.getInstance().getSellers()){
            if(user.getFirstName().equals(firstName)){
                System.out.println("the name is already taken, please try again.");
                return;
            }
        }
        super.changeFirstName(firstName);
    }

    /**
     * Suit un vendeur donné un nom de vendeur.
     *
     * @param name Le nom du vendeur à suivre.
     * @return True si le vendeur a été suivi avec succès, False sinon.
     */
    public boolean followSeller(String name){
        if(name.equals(this.getFirstName())){
            return false;
        }
        return super.followSeller(name);
    }

    /**
     * Modifie le prénom du fournisseur.
     *
     * @param name Le nouveau prénom à définir.
     * @return True si le prénom a été modifié avec succès, False sinon.
     */
    public boolean changeFirstName(String name){
        for(Fournisseur user : SystemeRobotix.getInstance().getSellers()){
            if(user.getFirstName().equals(name)){
                return false;
            }
        }
        this.setFirstName(name);
        return true;
    }

    /**
     * Définit l'emplacement du fournisseur.
     *
     * @param location L'emplacement à définir.
     */
    private void setLocation(String location) {
        this.location = location;
    }


    /**
     * Définit la capacité du fournisseur.
     *
     * @param capacity La capacité à définir.
     */
    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Fournit une représentation en chaîne de caractères du fournisseur.
     *
     * @return Une représentation en chaîne de caractères du fournisseur.
     */
    public String toString(){
        return "Fournisseur | Companie's name : " + this.getCompagnieName() + ", Name : " + this.getFirstName();
    }

    /**
     * Récupère les composantes fournies par le fournisseur.
     *
     * @return Les composantes fournies par le fournisseur.
     */
    public ArrayList<Composante> getComposantes(){
        return this.composantes;
    }

    /**
     * Crée une composante.
     *
     * @param type Le type de la composante.
     * @param name Le nom de la composante.
     * @param description La description de la composante.
     * @param price Le prix de la composante.
     */
    public void createComposante(String type , String name, String description , int price) {

        //TODO : check if the capacity is still enoutgh
        if (type.equals("CPU")) {
            this.composantes.add(new CPU(name, description, price, this));
            return;
        }
        this.composantes.add(new Composante(name, type, description, price, this));
    }

    /**
     * Supprime une composante.
     *
     * @param c La composante à supprimer.
     */
    public void deleteComposante(Composante c){
        this.composantes.remove(c);
    }


    /**
     * Récupère les composantes d'un type spécifique.
     *
     * @param type Le type des composantes à récupérer.
     * @return Une liste des composantes du type spécifique.
     */
    public ArrayList<Composante> getCorrespondingComponents(String type){
        ArrayList<Composante> liste = new ArrayList<>();

        for(Composante c : this.composantes){
            if(c.getType().toUpperCase().equals(type.toUpperCase())){
                liste.add(c);
            }
        }
        return liste;
    }
}

