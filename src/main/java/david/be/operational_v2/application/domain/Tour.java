package david.be.operational_v2.application.domain;

import java.util.ArrayList;
import java.util.List;

public class Tour {

    private int id;

    private List<Integer> postalCodes;

    private List<Parcel> parcels;

    private double maxCarCapacity;

    private double takenCapacity;

    public double getTakenCapacity() {
        return takenCapacity;
    }

    public Tour(int id, List<Integer> postalCodes, List<Integer> parcels, double maxCarCapacity) {
        this.id = id;
        this.postalCodes = postalCodes;
        this.parcels = new ArrayList<>();
        this.maxCarCapacity = maxCarCapacity;
        this.takenCapacity = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Parcel> getParcels() {
        return parcels;
    }


    public void setParcels(List<Parcel> parcels) {
        this.parcels = parcels;
    }

    public void setMaxCarCapacity(double maxCarCapacity) {
        this.maxCarCapacity = maxCarCapacity;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getPostalCodes() {
        return postalCodes;
    }

    public void setPostalCodes(List<Integer> postalCodes) {
        this.postalCodes = postalCodes;
    }

    public void addParcel(Parcel parcel) {
        if (parcel.getCubicMeters() < maxCarCapacity
                && takenCapacity < maxCarCapacity
                && parcel.getPriority() == 1) {
            parcel.setAssignedToRoute(true);
            parcels.add(parcel);
            setTakenCapacity(parcel.getCubicMeters());
        }
    }

    private void setTakenCapacity(double cubicMeters) {
        takenCapacity += cubicMeters;
    }

    public double getMaxCarCapacity() {
        return maxCarCapacity;
    }
}
