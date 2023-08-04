package Users;

import java.util.ArrayList;

import Machines.composantes.*;
import System.SystemeRobotix;

public class Fournisseur extends Acteur {
    
    /* attributes */
    private String location;
    private int capacity;

    /* assosiations */
    private ArrayList<Composante> composantes;

    /* constructeur */
    public Fournisseur(String companieName, String firstName, String password, String email, String phoneNumber, String location, int capacity) throws Exception{
        super(companieName, firstName, password, email, phoneNumber);
        this.setLocation(location);
        this.setCapacity(capacity);
        this.composantes = new ArrayList<>();
    }


    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

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

    public boolean followSeller(String name){
        if(name.equals(this.getFirstName())){
            return false;
        }
        return super.followSeller(name);
    }

    public boolean changeFirstName(String name){
        for(Fournisseur user : SystemeRobotix.getInstance().getSellers()){
            if(user.getFirstName().equals(name)){
                return false;
            }
        }
        this.setFirstName(name);
        return true;
    }

    private void setLocation(String location) {
        this.location = location;
    }

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String toString(){
        return "Fournisseur | Companie's name : " + this.getCompagnieName() + ", Name : " + this.getFirstName();
    }

    public ArrayList<Composante> getComposantes(){
        return this.composantes;
    }

    public void createComposante(String type , String name, String description , int price) {

        //TODO : check if the capacity is still enoutgh
        if (type.equals("CPU")) {
            this.composantes.add(new CPU(name, description, 10, this));
            return;
        }
        this.composantes.add(new Composante(name, type, description, 10, this));
    }

    public void deleteComposante(Composante c){
        this.composantes.remove(c);
    }

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
