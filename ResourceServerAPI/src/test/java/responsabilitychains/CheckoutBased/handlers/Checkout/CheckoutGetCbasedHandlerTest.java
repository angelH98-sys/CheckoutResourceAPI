package responsabilitychains.CheckoutBased.handlers.Checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutGetCbasedHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
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

@DisplayName("CheckoutGetCHandlerTest")
class CheckoutGetCbasedHandlerTest {
    @Mock
    private CheckoutService checkoutService;
    @InjectMocks
    private CheckoutGetCbasedHandler getCHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Succcess CheckoutGetCHandler")
    void successHandler(){

        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");

        Mockito.when(checkoutService.getByCheckoutIdAndUserId(anyString(), anyString()))
                .thenReturn(checkout);

        ChainResponse actual = getCHandler.handle(checkout);

        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail CheckoutGetCHandler, checkout not found")
    void failHandler(){

        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");

        Mockito.when(checkoutService.getByCheckoutIdAndUserId(anyString(), anyString()))
                .thenReturn(null);

        ChainResponse actual = getCHandler.handle(checkout);

        ChainResponse expected = new ChainResponse(false, "Checkout not found");

        assertThat(actual, samePropertyValuesAs(expected));
    }

}
