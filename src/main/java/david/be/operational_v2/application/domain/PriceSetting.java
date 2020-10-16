package david.be.operational_v2.application.domain;

public class PriceSetting {
    private double basePrice;
    private double weightFactor;
    private double professionalDiscount;

    public PriceSetting(double basePrice, double weightFactor, double professionalDiscount) {
        this.basePrice = basePrice;
        this.weightFactor = weightFactor;
        this.professionalDiscount = professionalDiscount;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getWeightFactor() {
        return weightFactor;
    }

    public double getProfessionalDiscount() {
        return professionalDiscount;
    }
}
