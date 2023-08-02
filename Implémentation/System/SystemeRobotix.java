package System;

import java.util.ArrayList;
import Users.Acteur;
import Users.Fournisseur;
import Users.Utilisateur;

public class SystemeRobotix {
   
    /* it implements the singleton pattern because I want it to be unique */
    
    /* singleton instance */
    private static SystemeRobotix instance;

    private SystemeRobotix(){
        this.acteurs = new ArrayList<>();
    }

    public static SystemeRobotix getInstance(){
        if(SystemeRobotix.instance == null){
            SystemeRobotix.instance = new SystemeRobotix();
        }
    
        return SystemeRobotix.instance;
    }

    /* assosiations */
    ArrayList<Acteur> acteurs;


    public ArrayList<Utilisateur> getUsers(){
        ArrayList<Utilisateur> filtered = new ArrayList<>();

        for(Acteur acteur : this.acteurs){
            if(acteur.getClass().getSimpleName().equals("Utilisateur")){
                filtered.add((Utilisateur)acteur);
            }
        }

        return filtered;
    }

    public ArrayList<Fournisseur> getSellers(){
            ArrayList<Fournisseur> filtered = new ArrayList<>();

            for(Acteur acteur : this.acteurs){
                if(acteur.getClass().getSimpleName().equals("Fournisseur")){
                    filtered.add((Fournisseur)acteur);
                }
            }

            return filtered;
        }

    public ArrayList<Acteur> getActors(){
        return this.acteurs;
    }
}
