package responsabilitychains.ProductOrderBased.handlers.Product;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.product.ProductStockHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ProductStockHandler Test")
class ProductStockHandlerTest {

    private ProductStockHandler productStockHandler;

    @BeforeEach
    void setup(){
        productStockHandler = new ProductStockHandler();
    }

    @Test
    @DisplayName("Success ProductStockHandler, product has enough stock for request")
    void successHandler(){
        Product product = new Product();
        product.setStock(10);

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");

        ProductOrder order = new ProductOrder();
        order.setQuantity(5);
        order.setProduct(product);
        order.setCheckout(checkout);

        ChainResponse actual = productStockHandler.handle(order);

        ChainResponse expected = new ChainResponse(true, "", order);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Fail ProductStockHandler, product has not enough stock for request")
    void failHandler(){
        Product product = new Product();
        product.setStock(5);

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");

        ProductOrder order = new ProductOrder();
        order.setQuantity(15);
        order.setProduct(product);
        order.setCheckout(checkout);

        ChainResponse actual = productStockHandler.handle(order);

        ChainResponse expected = new ChainResponse(false, "Product stock not enough for request");


        assertEquals(expected, actual);
    }
}
