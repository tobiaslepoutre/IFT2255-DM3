package Machines.composantes;

import Users.Fournisseur;

public class CPU extends Composante {
    private int consomation;

    public CPU(String name, String description, int price, Fournisseur seller) {
        super("CPU", name, description, price, seller);

        //no consomation when a CPU is created
        this.consomation = 0;
    }

    public int getConsomation() {
        return this.consomation;
    }

    public void setConsomation(int consomation) {
        this.consomation = consomation;
    }

}
