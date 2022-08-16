package responsabilitychains.CheckoutBased.chains;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutStatusUpdateChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.*;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.product.ProductUpdateHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.product.ProductVerificationHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.productorder.ProductOrderGetCbasedHandler;
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

@DisplayName("CheckoutStatusUpdateChain Test")
class CheckoutStatusUpdateChainTest {
    @Mock
    private CheckoutGetCbasedHandler getCHandler;
    @Mock
    private CheckoutInProgressVerificationHandler inProgressHandler;
    @Mock
    private CheckoutUserVerificationHandler userVerificationHandler;
    @Mock
    private CheckoutAddressVerificationHandler addressVerificationHandler;
    @Mock
    private CheckoutPaymentVerificationHandler paymentVerificationHandler;
    @Mock
    private ProductOrderGetCbasedHandler productOrderGetCbasedHandler;
    @Mock
    private ProductVerificationHandler productVerificationHandler;
    @Mock
    private ProductUpdateHandler productUpdateHandler;
    @Mock
    private CheckoutSetCompletedHandler setCompletedHandler;
    @Mock
    private CheckoutUpdateCbasedHandler updateCHandler;

    @InjectMocks
    private CheckoutStatusUpdateChain updateChain;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void successChain(){
        Checkout checkout = new Checkout();

        ChainResponse expected = new ChainResponse(true, "", checkout);

        Mockito.when(getCHandler.handle(any(Checkout.class)))
                .thenReturn(expected);

        ChainResponse actual = updateChain.setCompleted(checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
