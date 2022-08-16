package responsabilitychains.ProductOrderBased.handlers.ProductOrder;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderExistHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
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
import static org.mockito.ArgumentMatchers.any;

@DisplayName("ProductOrderExistHandler Test")
class ProductOrderExistHandlerTest {
    @Mock
    private ProductOrderService orderService;
    @InjectMocks
    private ProductOrderExistHandler existHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success ProductOrderExistHandler, product order exist in db")
    void successHandler(){

        Mockito.when(orderService
                .getProductOrderByCheckoutAndProduct(any(Checkout.class), any(Product.class)))
                .thenReturn(new ProductOrder());

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");

        ProductOrder order = new ProductOrder();
        order.setCheckout(checkout);
        order.setProduct(new Product());

        ChainResponse actual = existHandler.handle(order);

        ChainResponse expected = new ChainResponse(true, "", order);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail ProductOrderExistHandler, product order do not exist in db")
    void failHandler(){

        Mockito.when(orderService
                .getProductOrderByCheckoutAndProduct(any(Checkout.class), any(Product.class)))
                .thenReturn(null);

        ChainResponse actual = existHandler.handle(new ProductOrder());

        ChainResponse expected = new ChainResponse(false, "Product order not found");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
