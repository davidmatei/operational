package david.be.operational_v2.application.port.secondary;

import david.be.operational_v2.application.domain.Parcel;
import david.be.operational_v2.application.domain.Tour;

import java.util.List;

public interface TourRepository {


    Tour findById(int id);

    void addParcelToTour(int tourId, List<Parcel> parcels);

    void persist(Tour tour);
}
