package Machines.composantes;

public class CPU extends Composante {
    private int consomation;

    public CPU(String name, String type, String description, float price, int consomation) {
        super(name, type, description, price); 
        this.consomation = consomation;
    }

    public int getConsomation() {
        return this.consomation;
    }

    public void setConsomation(int consomation) {
        this.consomation = consomation;
    }

}
