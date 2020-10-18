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


public class TourHandlerTest {

    @Test
    public void assignParcelToRound() {

        //GIVEN
        double maxDeliveryCarCapacity = 12.3;

        ParcelRepository parcelRepository = new ParcelRepositoryStub();
        ParcelHandler parcelHandler = new ParcelHandler(parcelRepository);
        //toevoegen van Parcel aan Repo
        parcelHandler.handle(parcelEvent());
        parcelHandler.handle(parcelEvent2());
        //toevoegen van een Parcel met een andere postcode
        parcelHandler.handle(parcelEvent3());
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
        assertEquals(2, tour.getParcels().size());
    }


    private Tour tour2018tot2020(double maxCarCapacity) {
        List<Integer> postalCodes = new ArrayList<>();
        postalCodes.add(2018);
        postalCodes.add(2020);
        return new Tour(1, postalCodes, null, maxCarCapacity);
    }






    private ParcelEvent parcelEvent() {
        return new ParcelEvent(2, 2.0, 4.0, 3.0, 12, 2020, 1);
    }

    private ParcelEvent parcelEvent2() {
        return new ParcelEvent(2, 3.0, 5.0, 4.0, 13, 2018, 2);

    }

    private ParcelEvent parcelEvent3() {
        return new ParcelEvent(3, 3.0, 5.0, 4.0, 13, 2060, 1);

    }

}
