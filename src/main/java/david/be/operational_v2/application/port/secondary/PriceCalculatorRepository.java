package david.be.operational_v2.application.port.secondary;

import david.be.operational_v2.application.domain.PriceSetting;

public interface PriceCalculatorRepository {
    PriceSetting get();
}
