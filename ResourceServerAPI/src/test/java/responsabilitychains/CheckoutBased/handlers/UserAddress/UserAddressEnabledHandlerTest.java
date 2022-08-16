package responsabilitychains.CheckoutBased.handlers.UserAddress;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.useraddress.UserAddressEnabledHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@DisplayName("UserAddressEnabledHandler Test")
class UserAddressEnabledHandlerTest {

    @Test
    @DisplayName("Success UserAddressEnabledHandler")
    void successHandler(){
        UserAddress address = new UserAddress();
        address.setEnabled(true);

        Checkout checkout = new Checkout();
        checkout.setUserAddress(address);

        UserAddressEnabledHandler handler = new UserAddressEnabledHandler();

        ChainResponse actual = handler.handle(checkout);

        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail UserAddressEnabledHandler")
    void failHandler(){
        UserAddress address = new UserAddress();
        address.setEnabled(false);

        Checkout checkout = new Checkout();
        checkout.setUserAddress(address);

        UserAddressEnabledHandler handler = new UserAddressEnabledHandler();

        ChainResponse actual = handler.handle(checkout);

        ChainResponse expected = new ChainResponse(false, "Address not enabled");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
