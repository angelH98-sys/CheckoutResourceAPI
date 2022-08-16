package responsabilitychains.ProductOrderBased.handlers.Checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutAuthenticityHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import dev.ahernandez.checkoutresourceapi.service.imp.CheckoutService;
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
import static org.mockito.ArgumentMatchers.anyString;

@DisplayName("CheckoutAuthenticityHandler Test")
class CheckoutAuthenticityHandlerTest {
    @Mock
    private CheckoutService checkoutService;
    @InjectMocks
    private CheckoutAuthenticityHandler authenticityHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success CheckoutAuthenticityHandler")
    void successHandler(){

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");
        User user = new User();
        String userId = "";
        user.setUserId(userId);
        checkout.setUser(user);

        Mockito.when(checkoutService.getCheckoutById(anyString())).thenReturn(checkout);

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        ProductOrder order = new ProductOrder();
        order.setCheckout(checkout);

        ChainResponse actual = authenticityHandler.handle(order);

        ChainResponse expected = new ChainResponse(true, checkout.getCheckoutUuid(), order);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail CheckoutAuthenticityHandler, checkout not from user")
    void failHandler(){

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");

        Mockito.when(checkoutService.getCheckoutById(anyString())).thenReturn(null);

        ProductOrder order = new ProductOrder();
        order.setCheckout(checkout);

        ChainResponse actual = authenticityHandler.handle(order);

        ChainResponse expected = new ChainResponse(false, "Checkout not found");

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail CheckoutAuthenticityHandler, checkout not found")
    void failAuthHandler(){

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");
        User user = new User();
        String userId = "";
        user.setUserId(userId);
        checkout.setUser(user);

        Mockito.when(checkoutService.getCheckoutById(anyString())).thenReturn(checkout);

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn("another");

        ProductOrder order = new ProductOrder();
        order.setCheckout(checkout);

        ChainResponse actual = authenticityHandler.handle(order);

        ChainResponse expected = new ChainResponse(false, "Checkout do not belongs to authenticated user");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
