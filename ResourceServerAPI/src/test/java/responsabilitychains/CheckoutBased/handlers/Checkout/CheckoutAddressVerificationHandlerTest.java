package responsabilitychains.CheckoutBased.handlers.Checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutAddressVerificationHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@DisplayName("CheckoutAddressVerificationHandler Test")
class CheckoutAddressVerificationHandlerTest {

    @Test
    @DisplayName("Success CheckoutAddressVerificationHandler")
    void successHandler(){
        UserAddress address = new UserAddress();
        Checkout checkout = new Checkout();
        checkout.setUserAddress(address);

        CheckoutAddressVerificationHandler handler = new CheckoutAddressVerificationHandler();

        ChainResponse actual = handler.handle(checkout);

        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail CheckoutAddressVerificationHandler")
    void failHandler(){
        Checkout checkout = new Checkout();

        CheckoutAddressVerificationHandler handler = new CheckoutAddressVerificationHandler();

        ChainResponse actual = handler.handle(checkout);

        ChainResponse expected = new ChainResponse(false, "Address not specified");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
