package responsabilitychains.CheckoutBased.handlers.Checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutSetCompletedHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@DisplayName("CheckoutSetCompletedHandler Test")
class CheckoutSetCompletedHandlerTest {

    @Test
    @DisplayName("Success CheckoutSetCompletedHandler")
    void successHandler(){
        Checkout checkout = new Checkout();

        CheckoutSetCompletedHandler handler = new CheckoutSetCompletedHandler();

        ChainResponse actual = handler.handle(checkout);

        checkout.setCheckoutStatus("Completed");

        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }

}
