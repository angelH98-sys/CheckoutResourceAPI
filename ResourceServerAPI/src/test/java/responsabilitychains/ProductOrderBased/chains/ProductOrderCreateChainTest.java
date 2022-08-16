package responsabilitychains.ProductOrderBased.chains;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.chains.ProductOrderCreateChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutInProgressGetHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutUpdateHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.product.ProductGetHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.product.ProductIsEnabledHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.product.ProductStockHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderAddQuantityHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderGetHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderSaveHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderSetTotalAmountHandler;
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

@DisplayName("ProductOrderCreateChain Test")
class ProductOrderCreateChainTest {
    @Mock
    private ProductGetHandler productGetHandler;
    @Mock
    private ProductIsEnabledHandler productIsEnabledHandler;
    @Mock
    private ProductStockHandler productStockHandler;
    @Mock
    private CheckoutInProgressGetHandler checkoutInProgressGetHandler;
    @Mock
    private ProductOrderGetHandler productOrderGetHandler;
    @Mock
    private ProductOrderAddQuantityHandler productOrderAddQuantityHandler;
    @Mock
    private ProductOrderSetTotalAmountHandler productOrderSetTotalAmountHandler;
    @Mock
    private ProductOrderSaveHandler productOrderSaveHandler;
    @Mock
    private CheckoutUpdateHandler checkoutUpdateHandler;

    @InjectMocks
    private ProductOrderCreateChain createChain;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success ProductOrderCreateChain")
    void successChain(){
        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");
        ProductOrder order = new ProductOrder();
        order.setCheckout(checkout);

        ChainResponse expected = new ChainResponse(true, checkout.getCheckoutUuid(), order);

        Mockito.when(productGetHandler.handle(any(ProductOrder.class)))
                .thenReturn(expected);

        ChainResponse actual = createChain.addProductToCheckout(order);


        assertThat(actual, samePropertyValuesAs(expected));
    }
}
