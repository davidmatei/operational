package david.be.operational_v2.application.api;

import david.be.operational_v2.application.domain.Parcel;
import david.be.operational_v2.application.domain.Tour;
import david.be.operational_v2.application.infrastructure.ParcelRepositoryStub;
import david.be.operational_v2.application.infrastructure.TourRepositoryStub;
import david.be.operational_v2.application.port.secondary.TourRepository;
import david.be.operational_v2.application.port.secondary.ParcelRepository;
import david.be.operational_v2.infrastructure.ParcelEvent;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TourHandlerTest {

    @Test
    public void assignParcelToRound() {
        //GIVEN
        double maxDeliveryCarCapacity = 12.3;

        ParcelRepository parcelRepository = new ParcelRepositoryStub();
        ParcelHandler parcelHandler = new ParcelHandler(parcelRepository);
        //toevoegen van Parcel aan Repo
        parcelHandler.save(parcelEvent());
        parcelHandler.save(parcelEvent2());
        //toevoegen van een Parcel met een andere postcode
        parcelHandler.save(parcelEvent3());
        //alle Parcels ophalen die klaar zijn voor levering
        List<Parcel> allParcelsReadyForShipment = parcelHandler.getAllParcelsReadyForDelivery();
        TourRepository tourRepository = new TourRepositoryStub();
        TourHandler tourHandler = new TourHandler(tourRepository);
        tourHandler.addTour(tour2018tot2020(maxDeliveryCarCapacity));
        //WHEN
        //toewijzen van Paket aan Ronde (parcelEvent3 heeft een andere postCode dan Ronde met ID 1
        //dus parcelEvent3 zal niet worden toegevoegd aan de tour.
        tourHandler.addParcelToTour(1, allParcelsReadyForShipment);
        //THEN
        Tour tour = tourRepository.findById(1);
        Parcel parcel1 = tour.getParcels().get(1);
        assertTrue(parcel1.isAssignedToRoute());
        assertEquals(2, tour.getParcels().size());
    }

    @Test
    public void checkIfMaxCapacityIsNotOverwritten() {
        //GIVEN
        ParcelRepository parcelRepository = new ParcelRepositoryStub();
        ParcelHandler parcelHandler = new ParcelHandler(parcelRepository);
        TourRepository tourRepository = new TourRepositoryStub();
        TourHandler tourHandler = new TourHandler(tourRepository);
        parcelHandler.save(parcelEvent());
        parcelHandler.save(parcelEvent2());
        parcelHandler.save(parcelEvent3());
        List<Parcel> allParcelsReadyForShipment = parcelHandler.getAllParcelsReadyForDelivery();
        //WHEN
        Tour tour = tour2018tot2020(12.5);
        tourHandler.addTour(tour);
        tourHandler.addParcelToTour(1, allParcelsReadyForShipment);
        //THEN
        assertTrue(tour.getMaxCarCapacity() >= tour.getTakenCapacity());
    }

    @Test
    public void checkIfParcelPriorityIsCorrect() {
        //GIVEN                                                                                 
        ParcelRepository parcelRepository = new ParcelRepositoryStub();
        ParcelHandler parcelHandler = new ParcelHandler(parcelRepository);
        TourRepository tourRepository = new TourRepositoryStub();
        TourHandler tourHandler = new TourHandler(tourRepository);
        parcelHandler.save(parcelEvent());
        parcelHandler.save(parcelEvent2());
        parcelHandler.save(parcelEvent3());
        List<Parcel> allParcelsReadyForShipment = parcelHandler.getAllParcelsReadyForDelivery();
        //WHEN                                                                                  
        Tour tour = tour2018tot2020(12.5);
        tourHandler.addTour(tour);
        tourHandler.addParcelToTour(1, allParcelsReadyForShipment);
        //THEN
        assertEquals(tour.getParcels().get(0).getPriority(), 1);
    }

    private Tour tour2018tot2020(double maxCarCapacity) {
        List<Integer> postalCodes = new ArrayList<>();
        postalCodes.add(2018);
        postalCodes.add(2020);
        return new Tour(1, postalCodes, null, maxCarCapacity);
    }

    private ParcelEvent parcelEvent() {
        return new ParcelEvent(1, 1.0, 2.0, 1.0, 12, 2020, 1);
    }

    private ParcelEvent parcelEvent2() {
        return new ParcelEvent(2, 4.0, 2.0, 1.0, 13, 2018, 2);

    }

    private ParcelEvent parcelEvent3() {
        return new ParcelEvent(3, 4.0, 2.0, 1.0, 13, 2060, 1);

    }

}
