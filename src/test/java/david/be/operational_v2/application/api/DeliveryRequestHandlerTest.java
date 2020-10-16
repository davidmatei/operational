package david.be.operational_v2.application.api;

import david.be.operational_v2.application.domain.DeliveryRequest;
import david.be.operational_v2.application.domain.PriceSetting;
import david.be.operational_v2.application.infrastructure.DeliveryRequestRepositoryStub;
import david.be.operational_v2.application.port.secondary.DeliveryRequestRepository;
import david.be.operational_v2.application.port.secondary.PriceCalculatorRepository;
import david.be.operational_v2.infrastructure.DeliveryRequestEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryRequestHandlerTest {

    @Test
    void calculatePrice_ForPrivateCustomer_WithLowPriority() {
        //given
        PriceCalculatorRepository priceCalculatorRepository = new PriceCalculatorRepositoryStub();
        DeliveryRequestRepository deliveryRequestRepository = new DeliveryRequestRepositoryStub();
        DeliveryRequestHandler deliveryRequestHandler = new DeliveryRequestHandler(deliveryRequestRepository, priceCalculatorRepository);
        //when
        deliveryRequestHandler.handle(deliveryRequestEvent(2.0, lowPriority(), privateClient()));

        //then
        DeliveryRequest deliveryRequest = deliveryRequestRepository.find(1);
        assertEquals(20, deliveryRequest.getPrice());
    }

    @Test
    void calculatePrice_ForPrivateCustomer_WithMediumPriority() {
        //given
        PriceCalculatorRepository priceCalculatorRepository = new PriceCalculatorRepositoryStub();
        DeliveryRequestRepository deliveryRequestRepository = new DeliveryRequestRepositoryStub();
        DeliveryRequestHandler deliveryRequestHandler = new DeliveryRequestHandler(deliveryRequestRepository, priceCalculatorRepository);
        //when
        deliveryRequestHandler.handle(deliveryRequestEvent(2.0, mediumPriority(), privateClient()));

        //then
        DeliveryRequest deliveryRequest = deliveryRequestRepository.find(1);
        assertEquals(22, deliveryRequest.getPrice());
    }

    @Test
    void calculatePrice_ForPrivateCustomer_WithHighPriority() {
        //given
        PriceCalculatorRepository priceCalculatorRepository = new PriceCalculatorRepositoryStub();
        DeliveryRequestRepository deliveryRequestRepository = new DeliveryRequestRepositoryStub();
        DeliveryRequestHandler deliveryRequestHandler = new DeliveryRequestHandler(deliveryRequestRepository, priceCalculatorRepository);
        //when
        deliveryRequestHandler.handle(deliveryRequestEvent(2.0, highPriority(), privateClient()));

        //then
        DeliveryRequest deliveryRequest = deliveryRequestRepository.find(1);
        assertEquals(25, deliveryRequest.getPrice());
    }

    @Test
    void calculatePrice_ForProfessionalCustomer_WithLowPriority() {
        //given
        PriceCalculatorRepository priceCalculatorRepository = new PriceCalculatorRepositoryStub();
        DeliveryRequestRepository deliveryRequestRepository = new DeliveryRequestRepositoryStub();
        DeliveryRequestHandler deliveryRequestHandler = new DeliveryRequestHandler(deliveryRequestRepository, priceCalculatorRepository);
        //when
        deliveryRequestHandler.handle(deliveryRequestEvent(2.0, lowPriority(), professionalClient()));

        //then
        //(10*1.2) + 2 * 5 = 20 * 10% korting
        DeliveryRequest deliveryRequest = deliveryRequestRepository.find(1);
        assertEquals(18, deliveryRequest.getPrice());
    }

    private boolean professionalClient() {
        return true;
    }

    private boolean privateClient() {
        return false;
    }


    private DeliveryRequestEvent deliveryRequestEvent(double weight, int priority, boolean isProfessional) {
        return new DeliveryRequestEvent(2.0, 2.0, 2.0, weight,
                "Mercatorstraat", "124", "2", "2018", "Belgium", priority,isProfessional );
    }

    private int highPriority(){
        return 1;
    }
    private int mediumPriority() {
        return 2;
    }

    private int lowPriority() {
        return 3;
    }

    private class PriceCalculatorRepositoryStub implements PriceCalculatorRepository {
        final double BASE_PRICE = 10;
        final double WEIGHT_FACTOR = 5;
        final double PROFESSIONAL_DISCOUNT = 0.9;

        @Override
        public PriceSetting get() {
            return new PriceSetting(BASE_PRICE, WEIGHT_FACTOR,PROFESSIONAL_DISCOUNT);
        }
    }
}
