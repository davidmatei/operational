package david.be.operational_v2.application.domain;

public class DeliveryRequest {

    private int id;

    private double length;
    private double width;
    private double height;
    private double weight;

    private String address;
    private String addressNumber;
    private String bus;
    private String postalCode;
    private String country;

    private int priority;
    private double price;

    public DeliveryRequest() {
    }

    public DeliveryRequest(double length, double width, double height, double weight, String address, String addressNumber, String bus, String postalCode, String country, int priority) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.address = address;
        this.addressNumber = addressNumber;
        this.bus = bus;
        this.postalCode = postalCode;
        this.country = country;
        this.priority = priority;
    }

    public double getPrice() {
        return price;
    }


    public int getId() {
        return 1;
    }

    public void calculatePrice(int basePrice, int weightFactor) {
        price = basePrice + weight * weightFactor;
    }
}
