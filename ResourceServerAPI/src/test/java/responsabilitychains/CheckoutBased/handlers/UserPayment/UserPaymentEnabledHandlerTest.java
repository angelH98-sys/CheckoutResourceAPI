package responsabilitychains.CheckoutBased.handlers.UserPayment;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.userpayment.UserPaymentEnabledHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@DisplayName("UserPaymentEnabledHandler Test")
class UserPaymentEnabledHandlerTest {

    private UserPaymentEnabledHandler enabledHandler;

    @BeforeEach
    void setup(){
        enabledHandler = new UserPaymentEnabledHandler();
    }

    @Test
    @DisplayName("Success UserPaymentEnabledHandler")
    void successHandler(){
        UserPayment payment = new UserPayment();
        payment.setEnabled(true);

        Checkout checkout = new Checkout();
        checkout.setUserPayment(payment);

        ChainResponse actual = enabledHandler.handle(checkout);

        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail UserPaymentEnabledHandler")
    void failHandler(){
        UserPayment payment = new UserPayment();
        payment.setEnabled(false);

        Checkout checkout = new Checkout();
        checkout.setUserPayment(payment);

        ChainResponse actual = enabledHandler.handle(checkout);

        ChainResponse expected = new ChainResponse(false, "Payment not enabled");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
