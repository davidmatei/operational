package david.be.operational_v2.application.infrastructure;

import david.be.operational_v2.application.domain.DeliveryRequest;
import david.be.operational_v2.application.port.secondary.DeliveryRequestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class DeliveryRequestRepositoryStub implements DeliveryRequestRepository {

    private List<DeliveryRequest> store = new ArrayList<>();

    @Override
    public DeliveryRequest find(int id) {

        Optional<DeliveryRequest> first = store.stream().filter(deliveryRequest -> deliveryRequest.getId() == id).findFirst();
        return first.get();
    }

    @Override
    public void persist(DeliveryRequest deliveryRequest) {
        deliveryRequest.setId(1);
        store.add(deliveryRequest);
    }
}
