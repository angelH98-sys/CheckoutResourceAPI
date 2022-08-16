package responsabilitychains.CheckoutBased.handlers.UserPayment;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.userpayment.UserPaymentGetHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import dev.ahernandez.checkoutresourceapi.service.imp.UserPaymentService;
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

@DisplayName("UserPaymentGetHandler Test")
class UserPaymentGetHandlerTest {
    @Mock
    private UserPaymentService paymentService;
    @InjectMocks
    private UserPaymentGetHandler getHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success UserPaymentGetHandler")
    void successHandler(){
        UserPayment payment = new UserPayment();
        payment.setPaymentId(1);

        Checkout checkout = new Checkout();
        checkout.setUserPayment(payment);

        Mockito.when(paymentService.getPaymentById(anyInt()))
                .thenReturn(payment);

        ChainResponse actual = getHandler.handle(checkout);

        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail UserPaymentGetHandler")
    void failHandler(){
        UserPayment payment = new UserPayment();
        payment.setPaymentId(1);

        Checkout checkout = new Checkout();
        checkout.setUserPayment(payment);

        Mockito.when(paymentService.getPaymentById(anyInt()))
                .thenReturn(null);

        ChainResponse actual = getHandler.handle(checkout);

        ChainResponse expected = new ChainResponse(false, "Payment not found");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
