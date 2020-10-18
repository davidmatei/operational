package david.be.operational_v2.application.infrastructure;

import david.be.operational_v2.application.domain.Parcel;
import david.be.operational_v2.application.port.secondary.ParcelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParcelRepositoryStub implements ParcelRepository {

    private List<Parcel> store = new ArrayList<>();

    public void persist(Parcel parcel){
        store.add(parcel);
    }

    public Parcel findById(Long id) {
        Optional<Parcel> first = store.stream().filter(parcel -> parcel.getId() == id).findFirst();
        return first.get();
    }

    @Override
    public List<Parcel> findAll() {
        return store;
    }
}
