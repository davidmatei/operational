package david.be.operational_v2.application.port.secondary;

import david.be.operational_v2.application.domain.DeliveryRequest;

public interface DeliveryRequestRepository {
    DeliveryRequest find(int id);

    void persist(DeliveryRequest deliveryRequest);
}
