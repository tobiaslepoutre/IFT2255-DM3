package Machines;

import java.util.ArrayList;
import java.util.List;

public class Flotte {
    private final List<Robot> robots;

    public Flotte() {
        this.robots = new ArrayList<>();
    }

    
    public void ajouterRobot(Robot robot) {
        if(!robots.contains(robot)){
            this.robots.add(robot);
        }
    }

    public void supprimerRobot(Robot robot) {
        this.robots.remove(robot);
    }

    public List<Robot> getRobots() {
        return this.robots;
    }

    public void afficherEtatFlotte() {
        for (Robot robot : this.robots) {
            robot.afficherEtat();
        }
    }
}
