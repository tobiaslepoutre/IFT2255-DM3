package Users;

import java.util.ArrayList;
import System.SystemeRobotix;

public class Fournisseur extends Acteur {
    
    /* attributes */
    private String location;
    private int capacity;

    /* constructeur */
    public Fournisseur(String companieName, String firstName, String password, String email, String phoneNumber, String location, int capacity){
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

    private void setLocation(String location) {
        this.location = location;
    }

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
