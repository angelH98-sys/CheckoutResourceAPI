package responsabilitychains.ProductOrderBased.handlers.Product;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.product.ProductGetHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;

@DisplayName("ProductGetHandler Test")
class ProductGetHandlerTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductGetHandler productGetHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success ProductGetHandler, product setted to productOrder")
    void successHandler(){
        Product product = new Product();
        product.setProductId(1);
        product.setUnitPrice(1.0);

        Mockito.when(productService.findById(anyInt())).thenReturn(product);

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("id");

        ProductOrder order = new ProductOrder();

        order.setProduct(product);
        order.setCheckout(checkout);

        ChainResponse result = productGetHandler.handle(order);

        ChainResponse expected = new ChainResponse(true, "id", order);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Fail ProductGetHandler, Product Not Found")
    void nullHandler(){
        Product product = new Product();
        product.setProductId(1);

        Mockito.when(productService.findById(anyInt())).thenReturn(null);

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("id");

        ProductOrder order = new ProductOrder();

        order.setProduct(product);
        order.setCheckout(checkout);

        ChainResponse result = productGetHandler.handle(order);

        ChainResponse expected = new ChainResponse(false, "Product not found");

        assertEquals(expected, result);
    }
}
