package responsabilitychains.ProductOrderBased.chains;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.chains.ProductOrderDeleteChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutAuthenticityHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutDeleteHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutInProgressGetHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutUpdateHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderDeleteHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderExistHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderGetHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
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

@DisplayName("ProductOrderDeleteChain Test")
class ProductOrderDeleteChainTest {
    @Mock
    private ProductOrderGetHandler productOrderGetHandler;
    @Mock
    private ProductOrderExistHandler productOrderExistHandler;
    @Mock
    private CheckoutInProgressGetHandler checkoutInProgressGetHandler;
    @Mock
    private CheckoutAuthenticityHandler checkoutAuthenticityHandler;
    @Mock
    private ProductOrderDeleteHandler productOrderDeleteHandler;
    @Mock
    private CheckoutUpdateHandler checkoutUpdateHandler;
    @Mock
    private CheckoutDeleteHandler checkoutDeleteHandler;

    @InjectMocks
    private ProductOrderDeleteChain deleteChain;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success ProductOrderDeleteChain")
    void successChain(){
        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");
        ProductOrder order = new ProductOrder();
        order.setCheckout(checkout);

        ChainResponse expected = new ChainResponse(true, checkout.getCheckoutUuid(), order);

        Mockito.when(productOrderGetHandler.handle(any(ProductOrder.class)))
                .thenReturn(expected);

        ChainResponse actual = deleteChain.deleteProductOrder(order);


        assertThat(actual, samePropertyValuesAs(expected));
    }
}
