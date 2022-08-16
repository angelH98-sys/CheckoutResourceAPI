package responsabilitychains.CheckoutBased.chains;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutPaymentUpdateChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutAuthenticityCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutGetCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutInProgressVerificationHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutUpdateCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.userpayment.UserPaymentAuthenticityHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.userpayment.UserPaymentEnabledHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.userpayment.UserPaymentGetHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.repository.CheckoutRepository;
import dev.ahernandez.checkoutresourceapi.service.imp.CheckoutService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("CheckoutPaymentUpdateChain Test")
class CheckoutPaymentUpdateChainTest {

    @Test
    @DisplayName("CheckoutPaymentUpdateChain execution test")
    void successHandler(){

        CheckoutRepository repository
                = Mockito.mock(CheckoutRepository.class);
        CheckoutService service
                = new CheckoutService(repository);
        CheckoutGetCbasedHandler checkoutGetCbasedHandler
                = new CheckoutGetCbasedHandler(service);
        CheckoutInProgressVerificationHandler inProgressVerificationHandler
                = Mockito.mock(CheckoutInProgressVerificationHandler.class);
        CheckoutAuthenticityCbasedHandler checkoutAuthenticityCbasedHandler
                = Mockito.mock(CheckoutAuthenticityCbasedHandler.class);
        UserPaymentGetHandler paymentGetHandler
                = Mockito.mock(UserPaymentGetHandler.class);
        UserPaymentEnabledHandler paymentEnabledHandler
                = Mockito.mock(UserPaymentEnabledHandler.class);
        UserPaymentAuthenticityHandler paymentAuthenticityHandler
                = Mockito.mock(UserPaymentAuthenticityHandler.class);
        CheckoutUpdateCbasedHandler checkoutUpdateCbasedHandler
                = Mockito.mock(CheckoutUpdateCbasedHandler.class);

        CheckoutPaymentUpdateChain updateChain = new CheckoutPaymentUpdateChain(
                checkoutGetCbasedHandler,
                inProgressVerificationHandler,
                checkoutAuthenticityCbasedHandler,
                paymentGetHandler,
                paymentEnabledHandler,
                paymentAuthenticityHandler,
                checkoutUpdateCbasedHandler);

        Checkout checkout = new Checkout();
        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        Mockito.when(repository.findByCheckoutUuid(any()))
                .thenReturn(Optional.empty());

        ChainResponse expected = new ChainResponse(false, "Checkout not found");

        ChainResponse actual = updateChain.paymentUpdate(checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
