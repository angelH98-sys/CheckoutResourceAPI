package responsabilitychains.CheckoutBased.handlers.Checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutPaymentVerificationHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@DisplayName("CheckoutPaymentVerificationHandler Test")
class CheckoutPaymentVerificationHandlerTest {

    @Test
    @DisplayName("Success CheckoutPaymentVerificationHandler")
    void successHandler(){
        Checkout checkout = new Checkout();
        checkout.setUserPayment(new UserPayment());

        CheckoutPaymentVerificationHandler handler = new CheckoutPaymentVerificationHandler();

        ChainResponse actual = handler.handle(checkout);

        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail CheckoutPaymentVerificationHandler")
    void failHandler(){
        Checkout checkout = new Checkout();

        CheckoutPaymentVerificationHandler handler = new CheckoutPaymentVerificationHandler();

        ChainResponse actual = handler.handle(checkout);

        ChainResponse expected = new ChainResponse(false,"Payment method not selected");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
