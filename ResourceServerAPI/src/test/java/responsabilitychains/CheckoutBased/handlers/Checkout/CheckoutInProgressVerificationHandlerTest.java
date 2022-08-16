package responsabilitychains.CheckoutBased.handlers.Checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutInProgressVerificationHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@DisplayName("CheckoutInProgressVerificationHandler Test")
class CheckoutInProgressVerificationHandlerTest {

    @Test
    @DisplayName("Success CheckoutInProgressVerificationHandler")
    void successHandler(){
        Checkout checkout = new Checkout();
        checkout.setCheckoutStatus("InProgress");

        CheckoutInProgressVerificationHandler handler = new CheckoutInProgressVerificationHandler();

        ChainResponse actual = handler.handle(checkout);

        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail CheckoutInProgressVerificationHandler")
    void failHandler(){
        Checkout checkout = new Checkout();
        checkout.setCheckoutStatus("Completed");

        CheckoutInProgressVerificationHandler handler = new CheckoutInProgressVerificationHandler();

        ChainResponse actual = handler.handle(checkout);

        ChainResponse expected = new ChainResponse(false, "Checkout is not in progress");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
