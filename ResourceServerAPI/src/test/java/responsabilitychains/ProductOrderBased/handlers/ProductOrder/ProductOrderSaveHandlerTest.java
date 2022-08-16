package responsabilitychains.ProductOrderBased.handlers.ProductOrder;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderSaveHandler;
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

@DisplayName("ProductOrderSaveHandler Test")
class ProductOrderSaveHandlerTest {
    @Mock
    private ProductOrderService orderService;
    @InjectMocks
    private ProductOrderSaveHandler saveHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success ProductOrderSaveHandler, product added")
    void successHandler(){
        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");

        Product product = new Product();
        product.setProductId(1);

        ProductOrder orderInDB = new ProductOrder();
        orderInDB.setOrderId(1);
        orderInDB.setCheckout(checkout);
        orderInDB.setProduct(product);
        orderInDB.setQuantity(1);
        orderInDB.setUnitPrice(1.0);
        orderInDB.setTotalAmount(1.0);

        Mockito.when(orderService.saveProductOrder(any(ProductOrder.class)))
                .thenReturn(orderInDB);

        ProductOrder currentOrder = new ProductOrder();
        currentOrder.setCheckout(checkout);
        currentOrder.setProduct(product);

        ChainResponse actual = saveHandler.handle(currentOrder);

        ChainResponse expected = new ChainResponse(true, checkout.getCheckoutUuid(), orderInDB);

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
