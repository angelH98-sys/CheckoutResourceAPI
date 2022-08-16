package responsabilitychains.CheckoutBased.chains;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutAddressUpdateChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutAuthenticityCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutGetCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutInProgressVerificationHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutUpdateCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.useraddress.UserAddressAuthenticityHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.useraddress.UserAddressEnabledHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.useraddress.UserAddressGetHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
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

@DisplayName("CheckoutAddressUpdateChain Test")
class CheckoutAddressUpdateChainTest {
    @Mock
    private CheckoutGetCbasedHandler checkoutGetCbasedHandler;
    @Mock
    private CheckoutInProgressVerificationHandler inProgressVerificationHandler;
    @Mock
    private CheckoutAuthenticityCbasedHandler checkoutAuthenticityCbasedHandler;
    @Mock
    private UserAddressGetHandler userAddressGetHandler;
    @Mock
    private UserAddressEnabledHandler userAddressEnabledHandler;
    @Mock
    private UserAddressAuthenticityHandler userAddressAuthenticityHandler;
    @Mock
    private CheckoutUpdateCbasedHandler checkoutUpdateCbasedHandler;

    @InjectMocks
    private CheckoutAddressUpdateChain updateChain;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success CheckoutAddressUpdateChain")
    void successChain(){
        Checkout checkout = new Checkout();

        ChainResponse expected = new ChainResponse(true, "", checkout);

        Mockito.when(checkoutGetCbasedHandler.handle(any(Checkout.class)))
                .thenReturn(expected);

        ChainResponse actual = updateChain.updateCheckoutAddress(checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
