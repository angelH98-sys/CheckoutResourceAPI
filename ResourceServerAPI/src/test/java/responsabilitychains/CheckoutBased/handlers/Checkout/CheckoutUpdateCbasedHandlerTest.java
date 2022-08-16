package responsabilitychains.CheckoutBased.handlers.Checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutUpdateCbasedHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.service.imp.CheckoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("CheckoutUpdateCHandler Test")
class CheckoutUpdateCbasedHandlerTest {
    @Mock
    private CheckoutService checkoutService;
    @InjectMocks
    private CheckoutUpdateCbasedHandler updateCHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void successHandler(){
        Checkout checkout = new Checkout();
        Mockito.when(checkoutService.saveCheckout(any(Checkout.class)))
                .thenReturn(checkout);

        ChainResponse actual = updateCHandler.handle(checkout);
        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
