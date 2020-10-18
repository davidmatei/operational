package david.be.operational_v2.application.domain;

public class Parcel {
    private int id;

    private double length;
    private double width;
    private double height;
    private double weight;

    private int postalCode;

    private int priority;

    public Parcel(int id, double length, double width, double height, double weight, int postalCode, int priority) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.postalCode = postalCode;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
