package dev.ahernandez.checkoutresourceapi.service.imp;

import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.repository.CheckoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@DisplayName("CheckoutService Persists Methods Test")
class CheckoutServicePersistMethodsTest {

    @Mock
    private CheckoutRepository checkoutRepository;
    @InjectMocks
    private CheckoutService checkoutService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("saveCheckout checkout return")
    void saveCheckout(){

        Checkout checkout = new Checkout();

        Mockito.when(checkoutRepository.save(any(Checkout.class)))
                .thenReturn(checkout);

        Checkout result = checkoutService.saveCheckout(checkout);

        assertEquals(checkout, result);
    }

    @Test
    @DisplayName("deleteCheckoutByCheckoutUUID execution")
    void deleteCheckout(){
        Mockito.doNothing().when(checkoutRepository).deleteById(anyString());

        checkoutService.deleteCheckoutByCheckoutUuid("checkoutId");

        assertTrue(true);
    }
}
