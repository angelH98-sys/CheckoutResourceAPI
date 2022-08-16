package responsabilitychains.ProductOrderBased.handlers.Product;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.product.ProductIsEnabledHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ProductIsEnabledHandler Test")
class ProductIsEnabledHandlerTest {

    private ProductIsEnabledHandler productIsEnabledHandler;

    @BeforeEach
    void setup(){
        productIsEnabledHandler = new ProductIsEnabledHandler();
    }

    @Test
    @DisplayName("Success ProductIsEnabledHandler, product is enabled")
    void successHandler(){
        Product product = new Product();
        product.setEnabled(true);

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");

        ProductOrder order = new ProductOrder();
        order.setProduct(product);
        order.setCheckout(checkout);

        ChainResponse actual = productIsEnabledHandler.handle(order);

        ChainResponse expected = new ChainResponse(true, "", order);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Fail ProductIsEnabledHandler, product is not enabled")
    void failHandler(){
        Product product = new Product();
        product.setEnabled(false);

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");

        ProductOrder order = new ProductOrder();
        order.setProduct(product);
        order.setCheckout(checkout);

        ChainResponse actual = productIsEnabledHandler.handle(order);

        ChainResponse expected = new ChainResponse(false, "Product not available");

        assertEquals(expected, actual);
    }
}
