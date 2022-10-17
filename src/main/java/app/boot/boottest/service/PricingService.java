package app.boot.boottest.service;

import java.math.BigDecimal;

public class PricingService {

    private final ProductVerifier productVerifier;

    public PricingService(ProductVerifier productVerifier) {
        this.productVerifier = productVerifier;
    }

    public BigDecimal calculatePrice(String productName) {
        if (productVerifier.isCurrentlyInStockOfCompetitor(productName)) {
            return new BigDecimal("899.99");
        }

        return new BigDecimal("149.99");
    }
}
