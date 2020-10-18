package david.be.operational_v2.application.port.secondary;

import david.be.operational_v2.application.domain.Parcel;

import java.util.List;

public interface ParcelRepository {

    void persist(Parcel parcel);

    Parcel findById(Long id);


    List<Parcel> findAll();
}
