package responsabilitychains.CheckoutBased.handlers.UserPayment;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.userpayment.UserPaymentAuthenticityHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@DisplayName("UserPaymentAuthenticityHandler Test")
class UserPaymentAuthenticityHandlerTest {

    @Test
    @DisplayName("Success UserPaymentAuthenticityHandler")
    void successHandler(){
        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        UserPayment payment = new UserPayment();
        payment.setUserId(userId);

        Checkout checkout = new Checkout();
        checkout.setUserPayment(payment);

        UserPaymentAuthenticityHandler handler = new UserPaymentAuthenticityHandler();

        ChainResponse actual = handler.handle(checkout);

        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail UserPaymentAuthenticityHandler")
    void failHandler(){
        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        UserPayment payment = new UserPayment();
        payment.setUserId("diff");

        Checkout checkout = new Checkout();
        checkout.setUserPayment(payment);

        UserPaymentAuthenticityHandler handler = new UserPaymentAuthenticityHandler();

        ChainResponse actual = handler.handle(checkout);

        ChainResponse expected = new ChainResponse(false, "Payment do not belongs to authenticated user");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
