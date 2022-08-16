package responsabilitychains.ProductOrderBased.handlers.Checkout;

import dev.ahernandez.checkoutresourceapi.helpers.UuidGenerator;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutInProgressGetHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.*;
import dev.ahernandez.checkoutresourceapi.service.imp.CheckoutService;
import dev.ahernandez.checkoutresourceapi.service.imp.UserAddressService;
import dev.ahernandez.checkoutresourceapi.service.imp.UserPaymentService;
import dev.ahernandez.checkoutresourceapi.service.imp.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@DisplayName("CheckoutInProgressGetHandler Test")
class CheckoutInProgressGetHandlerTest {
    @Mock
    private CheckoutService checkoutService;
    @Mock
    private UserAddressService userAddressService;
    @Mock
    private UserPaymentService userPaymentService;
    @Mock
    private UserService userService;

    @InjectMocks
    private CheckoutInProgressGetHandler inProgressGetHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success CheckoutInProgressGetHandler, checkout created")
    void creationHandler(){
        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        User user = new User();
        user.setUserId(userId);

        String checkoutUUID = "";

        Mockito.mockStatic(UuidGenerator.class).when(UuidGenerator::generate).thenReturn(checkoutUUID);

        Mockito.when(checkoutService.getInProgressCheckoutByUserId(anyString()))
                        .thenReturn(null);

        Mockito.when(userService.getUserById(anyString())).thenReturn(user);

        UserAddress address = new UserAddress();

        Mockito.when(userAddressService.getFirstEnabledAddressByUserId(anyString()))
                .thenReturn(address);

        UserPayment payment = new UserPayment();

        Mockito.when(userPaymentService.getFirstEnabledPaymentMethod(anyString()))
                .thenReturn(payment);

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");
        checkout.setUser(user);
        checkout.setUserAddress(address);
        checkout.setUserPayment(payment);

        Mockito.when(checkoutService.saveCheckout(any(Checkout.class)))
                .thenReturn(checkout);

        ProductOrder order = new ProductOrder();

        ChainResponse actual = inProgressGetHandler.handle(order);

        order.setCheckout(checkout);

        ChainResponse expected = new ChainResponse(true, checkoutUUID, order);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Success CheckoutInProgressGetHandler, checkout founded and InProgress")
    void successValidateHandler(){
        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        User user = new User();
        user.setUserId(userId);

        Checkout checkoutInDB = new Checkout();
        checkoutInDB.setCheckoutUuid("");
        checkoutInDB.setCheckoutStatus("InProgress");
        checkoutInDB.setUser(user);

        Mockito.when(checkoutService.getCheckoutById(anyString()))
                .thenReturn(checkoutInDB);

        Checkout currentCheckout = new Checkout();
        currentCheckout.setCheckoutUuid("");

        ProductOrder order = new ProductOrder();
        order.setCheckout(currentCheckout);

        ChainResponse actual = inProgressGetHandler.handle(order);

        order.setCheckout(checkoutInDB);

        ChainResponse expected = new ChainResponse(true, "", order);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail CheckoutInProgressGetHandler, checkout founded it not InProgress")
    void failCheckoutNotInProgressHandler(){
        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        User user = new User();
        user.setUserId(userId);

        Checkout checkoutInDB = new Checkout();
        checkoutInDB.setCheckoutUuid("");
        checkoutInDB.setCheckoutStatus("Completed");
        checkoutInDB.setUser(user);

        Mockito.when(checkoutService.getCheckoutById(anyString()))
                .thenReturn(checkoutInDB);

        Checkout currentCheckout = new Checkout();
        currentCheckout.setCheckoutUuid("");

        ProductOrder order = new ProductOrder();
        order.setCheckout(currentCheckout);

        ChainResponse actual = inProgressGetHandler.handle(order);

        order.setCheckout(checkoutInDB);

        ChainResponse expected = new ChainResponse(false, "Checkout not available to update");

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail CheckoutInProgressGetHandler, checkout not founded")
    void failCheckoutNotFoundHandler(){
        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        Mockito.when(checkoutService.getCheckoutById(anyString()))
                .thenReturn(null);

        Checkout currentCheckout = new Checkout();
        currentCheckout.setCheckoutUuid("");

        ProductOrder order = new ProductOrder();
        order.setCheckout(currentCheckout);

        ChainResponse actual = inProgressGetHandler.handle(order);

        ChainResponse expected = new ChainResponse(false, "Checkout not found");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
