package david.be.operational_v2.application.api;

import david.be.operational_v2.application.domain.DeliveryRequest;
import david.be.operational_v2.application.port.secondary.DeliveryRequestRepository;
import david.be.operational_v2.infrastructure.DeliveryRequestEvent;

public class DeliveryRequestHandler {

    private DeliveryRequestRepository repository;

    public DeliveryRequestHandler(DeliveryRequestRepository deliveryRequestRepository) {
        this.repository = deliveryRequestRepository;
    }

    public void handle(DeliveryRequestEvent event) {
        DeliveryRequest deliveryRequest = mapDeliveryRequest(event);
        deliveryRequest.calculatePrice(10, 5);
        repository.persist(deliveryRequest);
    }

    private DeliveryRequest mapDeliveryRequest(DeliveryRequestEvent event) {
        return new DeliveryRequest(event.getLength(),
                event.getWidth(),
                event.getHeight(),
                event.getWeight(),
                event.getAddress(),
                event.getAddressNumber(),
                event.getBus(),
                event.getPostalCode(),
                event.getCountry(),
                event.getPriority());
    }


}
