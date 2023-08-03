package Users;

import java.util.ArrayList;
import System.SystemeRobotix;

public class Fournisseur extends Acteur {
    
    /* attributes */
    private String location;
    private int capacity;

    /* constructeur */
    public Fournisseur(String companieName, String firstName, String password, String email, String phoneNumber, String location, int capacity) throws Exception{
        super(companieName, firstName, password, email, phoneNumber);
        this.setLocation(location);
        this.setCapacity(capacity);
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
}
