package responsabilitychains.ProductOrderBased.handlers.Checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutDeleteHandler;
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
import static org.mockito.ArgumentMatchers.anyString;

@DisplayName("CheckoutDeleteHandler Test")
class CheckoutDeleteHandlerTest {
    @Mock
    private CheckoutService checkoutService;
    @Mock
    private ProductOrderService orderService;
    @InjectMocks
    private CheckoutDeleteHandler deleteHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success CheckoutDeleteHandler, Checkout deleted")
    void successHandler(){
        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");

        ProductOrder order = new ProductOrder();
        order.setCheckout(checkout);

        Mockito.when(orderService.getProductOrderByCheckout(any(Checkout.class)))
                .thenReturn(null);

        Mockito.doNothing().when(checkoutService).deleteCheckoutByCheckoutUuid(anyString());

        ChainResponse actual = deleteHandler.handle(order);

        ChainResponse expected = new ChainResponse(true, checkout.getCheckoutUuid(), order);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Success CheckoutDeleteHandler, Checkout not deleted")
    void successNotDeletedHandler(){
        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");

        ProductOrder order = new ProductOrder();
        order.setCheckout(checkout);

        List<ProductOrder> orderList = new ArrayList<>();
        orderList.add(new ProductOrder());

        Mockito.when(orderService.getProductOrderByCheckout(any(Checkout.class)))
                .thenReturn(orderList);

        ChainResponse actual = deleteHandler.handle(order);

        ChainResponse expected = new ChainResponse(true, checkout.getCheckoutUuid(), order);

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
