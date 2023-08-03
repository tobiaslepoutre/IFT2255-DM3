package Machines;

import java.util.Date;

public class Robot {

    
    private String name;
    private String type;
    private String serialNumber;
    private float positionX;
    private float positionY;
    private float positionZ;
    private float speed;
    private int powerLevel;

    
    public Robot(String name, String type, String serialNumber, float positionX, float positionY, float positionZ, float speed, int powerLevel) {
        this.name = name;
        this.type = type;
        this.serialNumber = serialNumber;
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionZ = positionZ;
        this.speed = speed;
        this.powerLevel = powerLevel;
    }


    public boolean isBusy(Date date) {
        //??? Je ne sais pas comment implémenter cette méthode, sur quels élément se base t on pour déterminer si le robot est busy?
        return false;
    }

    public void afficherEtat() {
        System.out.println("Name: " + this.name);
        System.out.println("Type: " + this.type);
        System.out.println("Serial number: " + this.serialNumber);
        System.out.println("Position: (" + this.positionX + ", " + this.positionY + ", " + this.positionZ + ")");
        System.out.println("Speed: " + this.speed);
        System.out.println("Power level: " + this.powerLevel);
    }



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public float getPositionX() {
        return this.positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return this.positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public float getPositionZ() {
        return this.positionZ;
    }

    public void setPositionZ(float positionZ) {
        this.positionZ = positionZ;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getPowerLevel() {
        return this.powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }
}
