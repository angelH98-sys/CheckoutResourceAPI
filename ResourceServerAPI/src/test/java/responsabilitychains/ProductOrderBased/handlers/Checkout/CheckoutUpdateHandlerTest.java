package responsabilitychains.ProductOrderBased.handlers.Checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutUpdateHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.service.imp.CheckoutService;
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

@DisplayName("CheckoutUpdateHandler Test")
class CheckoutUpdateHandlerTest {
    @Mock
    private CheckoutService checkoutService;
    @Mock
    private ProductOrderService productOrderService;

    @InjectMocks
    private CheckoutUpdateHandler updateHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success CheckoutUpdateHandler")
    void successHandler(){
        List<ProductOrder> orderList = new ArrayList<>();
        ProductOrder order = new ProductOrder();
        order.setTotalAmount(1.0);

        Mockito.when(productOrderService.getProductOrderByCheckout(any()))
                .thenReturn(orderList);

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");
        checkout.setTotalAmount(1.0);

        Mockito.when(checkoutService.saveCheckout(any(Checkout.class))).thenReturn(checkout);

        ProductOrder currentOrder = new ProductOrder();
        checkout.setTotalAmount(0.0);
        currentOrder.setCheckout(checkout);

        ChainResponse actual = updateHandler.handle(currentOrder);

        ChainResponse expected = new ChainResponse(true, checkout.getCheckoutUuid(), currentOrder);

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
