package Machines.composantes;

public abstract class Composante {

    private String name;
    private String type;
    private String description;
    private float price;

    public Composante(String name, String type, String description, float price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    public String getNameComp() {
        return name;
    }

    public void setNameComp(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
