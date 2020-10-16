package david.be.operational_v2.application.api;

import david.be.operational_v2.application.domain.DeliveryRequest;
import david.be.operational_v2.application.port.secondary.DeliveryRequestRepository;
import david.be.operational_v2.application.port.secondary.PriceCalculatorRepository;
import david.be.operational_v2.infrastructure.DeliveryRequestEvent;

public class DeliveryRequestHandler {

    private DeliveryRequestRepository repository;
    private final PriceCalculatorRepository priceCalculatorRepository;

    public DeliveryRequestHandler(DeliveryRequestRepository deliveryRequestRepository,
                                  PriceCalculatorRepository priceCalculatorRepository) {
        this.repository = deliveryRequestRepository;
        this.priceCalculatorRepository = priceCalculatorRepository;
    }

    public void handle(DeliveryRequestEvent event) {
        DeliveryRequest deliveryRequest = mapDeliveryRequest(event);
        deliveryRequest.calculatePrice(priceCalculatorRepository.get());
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
                event.getPriority(),
                event.isProfessional());
    }


}
