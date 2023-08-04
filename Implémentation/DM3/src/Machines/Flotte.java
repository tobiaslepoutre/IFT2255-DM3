package Machines;

import java.util.ArrayList;
import java.util.List;

public class Flotte {
    private List<Robot> robots;

    public Flotte() {
        this.robots = new ArrayList<>();
    }

    
    public void ajouterRobot(Robot robot) {
        if(!robots.contains(robot)){
            this.robots.add(robot);
        }
    }

    public void supprimerRobot(Robot robot) {
        if(this.robots.contains(robots)){
            this.robots.remove(robot);
        }
    }

    public List<Robot> getRobots() {
        return this.robots;
    }

    public void afficherEtat() {
        for (Robot robot : this.robots) {
            robot.afficherEtat();
        }
    }
}
