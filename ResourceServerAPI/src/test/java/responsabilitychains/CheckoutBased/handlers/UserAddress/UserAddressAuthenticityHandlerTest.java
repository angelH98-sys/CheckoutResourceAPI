package responsabilitychains.CheckoutBased.handlers.UserAddress;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.useraddress.UserAddressAuthenticityHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@DisplayName("UserAddressAuthenticityHandler Test")
class UserAddressAuthenticityHandlerTest {

    @Test
    @DisplayName("Success UserAddressAuthenticityHandler")
    void successHandler(){
        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        UserAddress address = new UserAddress();
        address.setUserId(userId);

        Checkout checkout = new Checkout();
        checkout.setUserAddress(address);

        UserAddressAuthenticityHandler handler = new UserAddressAuthenticityHandler();

        ChainResponse actual = handler.handle(checkout);
        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail UserAddressAuthenticityHandler")
    void failHandler(){
        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        UserAddress address = new UserAddress();
        address.setUserId("diff");

        Checkout checkout = new Checkout();
        checkout.setUserAddress(address);

        UserAddressAuthenticityHandler handler = new UserAddressAuthenticityHandler();

        ChainResponse actual = handler.handle(checkout);
        ChainResponse expected = new ChainResponse(false, "User Address do not belongs to user authenticated");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
