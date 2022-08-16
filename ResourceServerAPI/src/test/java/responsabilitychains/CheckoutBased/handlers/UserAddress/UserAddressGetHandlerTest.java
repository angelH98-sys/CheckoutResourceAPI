package responsabilitychains.CheckoutBased.handlers.UserAddress;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.useraddress.UserAddressGetHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import dev.ahernandez.checkoutresourceapi.service.imp.UserAddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.anyInt;

@DisplayName("UserAddressGetHandler Test")
class UserAddressGetHandlerTest {
    @Mock
    private UserAddressService addressService;
    @InjectMocks
    private UserAddressGetHandler addressGetHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success UserAddressGetHandler")
    void successHandler(){
        UserAddress address = new UserAddress();
        address.setAddressId(1);
        address.setUserId("");

        User user = new User();
        user.setUserId("");

        Checkout checkout = new Checkout();
        checkout.setUserAddress(address);
        checkout.setUser(user);

        Mockito.when(addressService.getAddressById(anyInt()))
                .thenReturn(address);

        ChainResponse actual = addressGetHandler.handle(checkout);

        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail UserAddressGetHandler, address not found")
    void addressNotFoundHandler(){
        UserAddress address = new UserAddress();
        address.setAddressId(1);
        address.setUserId("");

        User user = new User();
        user.setUserId("");

        Checkout checkout = new Checkout();
        checkout.setUserAddress(address);
        checkout.setUser(user);

        Mockito.when(addressService.getAddressById(anyInt()))
                .thenReturn(null);

        ChainResponse actual = addressGetHandler.handle(checkout);

        ChainResponse expected = new ChainResponse(false, "Address not found");

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail UserAddressGetHandler, address not from user")
    void addressNotFromUserHandler(){
        UserAddress address = new UserAddress();
        address.setAddressId(1);
        address.setUserId("");

        User user = new User();
        user.setUserId("diff");

        Checkout checkout = new Checkout();
        checkout.setUserAddress(address);
        checkout.setUser(user);

        Mockito.when(addressService.getAddressById(anyInt()))
                .thenReturn(address);

        ChainResponse actual = addressGetHandler.handle(checkout);

        ChainResponse expected = new ChainResponse(false, "Address not belongs to user authenticated");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
