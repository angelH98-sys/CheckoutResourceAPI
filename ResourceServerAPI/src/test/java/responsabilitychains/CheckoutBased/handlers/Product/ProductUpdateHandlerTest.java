package responsabilitychains.CheckoutBased.handlers.Product;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.product.ProductUpdateHandler;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("ProductUpdateHandler Test")
class ProductUpdateHandlerTest {
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductUpdateHandler updateHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success ProductUpdateHandler")
    void successHandler(){

        Product product = new Product();
        product.setStock(10);

        ProductOrder order = new ProductOrder();
        order.setQuantity(1);

        order.setProduct(product);

        List<ProductOrder> orderList = new ArrayList<>();
        orderList.add(order);

        Mockito.when(productService.saveAll(any()))
                .thenReturn(new ArrayList<>());

        Checkout checkout = new Checkout();
        checkout.setProductOrders(orderList);

        ChainResponse actual = updateHandler.handle(checkout);

        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
