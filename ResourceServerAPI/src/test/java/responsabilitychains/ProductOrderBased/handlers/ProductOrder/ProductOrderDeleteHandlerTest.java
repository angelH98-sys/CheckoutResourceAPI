package responsabilitychains.ProductOrderBased.handlers.ProductOrder;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderDeleteHandler;
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

@DisplayName("ProductOrderDeleteHandler Test")
class ProductOrderDeleteHandlerTest {
    @Mock
    private ProductOrderService orderService;
    @InjectMocks
    private ProductOrderDeleteHandler orderDeleteHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void successHandler(){
        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");

        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrderId(1);
        productOrder.setCheckout(checkout);

        Mockito.doNothing().when(orderService).deleteProductOrderByOrderId(anyInt());

        ChainResponse actual = orderDeleteHandler.handle(productOrder);

        ChainResponse expected = new ChainResponse(true, "", productOrder);

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
