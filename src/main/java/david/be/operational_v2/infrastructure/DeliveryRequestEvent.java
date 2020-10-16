package david.be.operational_v2.infrastructure;

public class DeliveryRequestEvent {

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




    public DeliveryRequestEvent(double length, double width, double height, double weight, String address, String addressNumber, String bus, String postalCode, String country, int priority) {
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

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public String getBus() {
        return bus;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public int getPriority() {
        return priority;
    }
}
