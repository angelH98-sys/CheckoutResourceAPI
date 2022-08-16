package responsabilitychains.CheckoutBased.handlers.ProductOrder;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.productorder.ProductOrderGetCbasedHandler;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("ProductOrderGetCHandler Test")
class ProductOrderGetCbasedHandlerTest {
    @Mock
    private ProductOrderService productOrderService;

    @InjectMocks
    private ProductOrderGetCbasedHandler getCHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success ProductOrderGetCHandler")
    void successHandler(){
        List<ProductOrder> orderList = new ArrayList<>();
        Mockito.when(productOrderService.getProductOrderByCheckout(any(Checkout.class)))
                .thenReturn(orderList);

        Checkout checkout = new Checkout();

        ChainResponse actual = getCHandler.handle(checkout);

        checkout.setProductOrders(orderList);

        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
