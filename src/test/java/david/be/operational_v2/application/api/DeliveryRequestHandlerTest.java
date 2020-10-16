package david.be.operational_v2.application.api;

import david.be.operational_v2.application.domain.DeliveryRequest;
import david.be.operational_v2.application.infrastructure.DeliveryRequestRepositoryStub;
import david.be.operational_v2.application.port.secondary.DeliveryRequestRepository;
import david.be.operational_v2.infrastructure.DeliveryRequestEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryRequestHandlerTest {

    @Test
    void calculatePriceForPrivateCustomer() {
        //given
        double basePrice = 10;
        double weightFactor = 5;

        DeliveryRequestRepository deliveryRequestRepository = new DeliveryRequestRepositoryStub();
        DeliveryRequestHandler deliveryRequestHandler = new DeliveryRequestHandler(deliveryRequestRepository);
        //when
        deliveryRequestHandler.handle(deliveryRequestEvent(2.0));

        //then
        // basisprijs + gewicht * gewichtsFactor
        //10 + 2 * 5 = 20

        DeliveryRequest deliveryRequest = deliveryRequestRepository.find(1);
        assertEquals(20, deliveryRequest.getPrice());
    }
    @Test
    void calculatePriceForPrivateCustomer2() {
        //given
        double basePrice = 10;
        double weightFactor = 5;

        DeliveryRequestRepository deliveryRequestRepository = new DeliveryRequestRepositoryStub();
        DeliveryRequestHandler deliveryRequestHandler = new DeliveryRequestHandler(deliveryRequestRepository);
        //when
        deliveryRequestHandler.handle(deliveryRequestEvent(4.0));

        //then
        // basisprijs + gewicht * gewichtsFactor
        //10 + 2 * 5 = 20

        DeliveryRequest deliveryRequest = deliveryRequestRepository.find(1);
        assertEquals(30, deliveryRequest.getPrice());
    }


    private DeliveryRequestEvent deliveryRequestEvent(double weight) {
        return new DeliveryRequestEvent(2.0, 2.0, 2.0, weight,
                "Mercatorstraat", "124", "2", "2018", "Belgium", 1);
    }
}