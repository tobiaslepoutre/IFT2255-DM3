package Machines.composantes;

import Users.Fournisseur;

/**
 * La classe CPU représente un processeur qui est une composante d'un robot.
 * @author Tobias Lepoutre
 * @version 1.0
 */
public class CPU extends Composante {

    /**
     * La consommation en énergie du CPU.
     */
    private int consomation;

    /**
     * Crée un nouveau CPU avec un nom, une description, un prix et un fournisseur.
     *
     * @param name Le nom du CPU.
     * @param description La description du CPU.
     * @param price Le prix du CPU.
     * @param seller Le fournisseur du CPU.
     */
    public CPU(String name, String description, int price, Fournisseur seller) {
        super(name, "CPU", description, price, seller);
        // pas de consommation lorsque le CPU vient d'être créé.
        this.consomation = 0;
    }

    /**
     * Obtient la consommation du CPU.
     *
     * @return La consommation du CPU.
     */
    public int getConsomation() {
        return this.consomation;
    }

    /**
     * Définit la consommation du CPU.
     *
     * @param consomation La nouvelle consommation du CPU.
     */
    public void setConsomation(int consomation) {
        this.consomation = consomation;
    }
}
