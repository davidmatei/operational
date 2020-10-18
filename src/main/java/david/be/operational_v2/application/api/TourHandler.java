package david.be.operational_v2.application.api;

import david.be.operational_v2.application.domain.Parcel;
import david.be.operational_v2.application.domain.Tour;
import david.be.operational_v2.application.port.secondary.TourRepository;

import java.util.List;

public class TourHandler {

    private TourRepository repository;

    public TourHandler(TourRepository tourRepository) {
        this.repository = tourRepository;
    }


    public void addTour(Tour tour){
        repository.persist(tour);
    }

    public void addParcelToTour(int tourId, List<Parcel> parcels) {
        repository.addParcelToTour(tourId, parcels);
    }

}
