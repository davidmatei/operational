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
    private boolean professional;
    private double price;

    public DeliveryRequest(double length, double width, double height, double weight, String address, String addressNumber, String bus, String postalCode, String country, int priority,
                           boolean professional) {
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
        this.professional = professional;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return 1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void calculatePrice(PriceSetting priceSetting) {
        if (professional) professionalClientPrice(priceSetting);
        else privateClientPrice(priceSetting);
    }

    private void professionalClientPrice(PriceSetting priceSetting) {
        double basePrice = priceSetting.getBasePrice();
        switch(priority) {
            case 1:
                basePrice *= 1.5;
                break;
            case 2:
                basePrice *= 1.2;
                break;
            default:
                basePrice *= 1.0;

        }
        price = (basePrice + (weight * priceSetting.getWeightFactor()));
        price = price*priceSetting.getProfessionalDiscount();

    }

    private void privateClientPrice(PriceSetting priceSetting) {
        double basePrice = priceSetting.getBasePrice();
        switch(priority) {
            case 1:
                basePrice *= 1.5;
                break;
            case 2:
                basePrice *= 1.2;
                break;
            default:
                basePrice *= 1.0;

        }
        price = basePrice + (weight * priceSetting.getWeightFactor());
    }
}
