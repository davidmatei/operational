package david.be.operational_v2.application.infrastructure;

import david.be.operational_v2.application.domain.Parcel;
import david.be.operational_v2.application.domain.Tour;
import david.be.operational_v2.application.port.secondary.TourRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourRepositoryStub implements TourRepository {


    private List<Tour> store = new ArrayList<>();

    public void persist(Tour tour) {
        store.add(tour);
    }

    public Tour findById(int id) {
        Optional<Tour> first = store.stream().filter(tour -> tour.getId() == id).findFirst();
        return first.get();
    }

    @Override
    public void addParcelToTour(int tourId, List<Parcel> parcels) {
        Tour tour = findById(tourId);

        for (Parcel parcel : parcels
        ) {
            //controle op postCodes
            if (tour.getPostalCodes().contains(parcel.getPostalCode())) {
                tour.addParcel(parcel);
            }
        }
        store.set(store.indexOf(tour), tour);
    }
}
