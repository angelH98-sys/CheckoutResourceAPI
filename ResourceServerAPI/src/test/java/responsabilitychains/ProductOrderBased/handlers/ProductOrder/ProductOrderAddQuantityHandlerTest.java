package responsabilitychains.ProductOrderBased.handlers.ProductOrder;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderAddQuantityHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductOrderService;
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

@DisplayName("OrderAddQuantityHandler Test")
class ProductOrderAddQuantityHandlerTest {
    @Mock
    private ProductOrderService orderService;
    @InjectMocks
    private ProductOrderAddQuantityHandler addQuantityHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success ProductOrderAddQuantityHandler, quantity added")
    void successHandler(){

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");

        ProductOrder order = new ProductOrder();
        order.setOrderId(1);
        order.setQuantity(1);
        order.setCheckout(checkout);

        Mockito.when(orderService.getProductOrderById(anyInt()))
                .thenReturn(order);

        ChainResponse actual = addQuantityHandler.handle(order);

        order.setQuantity(2);

        ChainResponse expected = new ChainResponse(true, "", order);

        boolean asserts = false;

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Success ProductOrderAddQuantityHandler, no order in db")
    void successNoOrderInDDHandler(){

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");

        ProductOrder order = new ProductOrder();
        order.setOrderId(1);
        order.setQuantity(1);
        order.setCheckout(checkout);

        Mockito.when(orderService.getProductOrderById(anyInt()))
                .thenReturn(null);

        ChainResponse actual = addQuantityHandler.handle(order);

        ChainResponse expected = new ChainResponse(true, "", order);

        boolean asserts = false;

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
