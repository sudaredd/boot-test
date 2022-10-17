package app.boot.boottest.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PricingServiceTest {

    @Mock
    private ProductVerifier productVerifier;

    @Test
    void calculatePrice() {
        Mockito.when(productVerifier.isCurrentlyInStockOfCompetitor("IPhone")).thenReturn(true);
        PricingService pricingService = new PricingService(productVerifier);
        assertEquals(new BigDecimal("899.99"), pricingService.calculatePrice("IPhone"));
    }
}