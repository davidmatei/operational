package david.be.operational_v2.application.domain;

import java.util.ArrayList;
import java.util.List;

public class Tour {

    private int id;

    private List<Integer> postalCodes;

    private List<Integer> parcels;

    private double maxCarCapacity;


    public Tour(int id, List<Integer> postalCodes, List<Integer> parcels, double maxCarCapacity) {
        this.id = id;
        this.postalCodes = postalCodes;
        this.parcels = new ArrayList<>();
        this.maxCarCapacity = maxCarCapacity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getParcels() {
        return parcels;
    }

    public void setParcels(List<Integer> parcels) {
        this.parcels = parcels;
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
        parcels.add(parcel.getId());
    }
}
