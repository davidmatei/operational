package david.be.operational_v2.application.api;

import david.be.operational_v2.application.domain.Parcel;
import david.be.operational_v2.application.port.secondary.ParcelRepository;
import david.be.operational_v2.infrastructure.ParcelEvent;

import java.util.List;

public class ParcelHandler {

    private ParcelRepository parcelRepository;

    public ParcelHandler(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }


    public void save(ParcelEvent event) {
        Parcel parcel = mapParcel(event);
        parcelRepository.persist(parcel);
    }


    public List<Parcel> getAllParcelsReadyForDelivery(){

        return parcelRepository.findAll();
    }

    private Parcel mapParcel(ParcelEvent event) {
        return new Parcel(
                event.getId(),
                event.getLength(),
                event.getWidth(),
                event.getHeight(),
                event.getWeight(),
                event.getPostalCode(),
                event.getPriority());
    }


}
