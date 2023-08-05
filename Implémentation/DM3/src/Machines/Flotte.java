package Machines;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Flotte représente une collection de robots.
 * <p>
 * Elle permet d'ajouter et de supprimer des robots, ainsi que
 * d'afficher l'état de chaque robot dans la flotte.
 * </p>
 *
 * @author VotreNom
 * @version 1.0
 */
public class Flotte {
    private final List<Robot> robots;

    /**
     * Construit une nouvelle flotte vide de robots.
     */
    public Flotte() {
        this.robots = new ArrayList<>();
    }

    /**
     * Ajoute un robot à la flotte s'il n'est pas déjà présent.
     *
     * @param robot Le robot à ajouter.
     */
    public void ajouterRobot(Robot robot) {
        if(!robots.contains(robot)){
            this.robots.add(robot);
        }
    }

    /**
     * Supprime un robot de la flotte.
     *
     * @param robot Le robot à supprimer.
     */
    public void supprimerRobot(Robot robot) {
        this.robots.remove(robot);
    }

    /**
     * Retourne la liste des robots dans la flotte.
     *
     * @return La liste des robots.
     */
    public List<Robot> getRobots() {
        return this.robots;
    }

    /**
     * Affiche l'état de chaque robot dans la flotte.
     */
    public void afficherEtatFlotte() {
        for (Robot robot : this.robots) {
            robot.afficherEtat();
        }
    }
}
