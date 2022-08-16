package responsabilitychains.CheckoutBased.handlers.Product;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.product.ProductVerificationHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@DisplayName("ProductVerificationHandler Test")
class ProductVerificationHandlerTest {

    @Test
    @DisplayName("Success ProductVerificationHandler")
    void successHandler(){

        Product product = new Product();
        product.setStock(10);
        product.setEnabled(true);

        ProductOrder order = new ProductOrder();
        order.setQuantity(1);

        order.setProduct(product);

        List<ProductOrder> orderList = new ArrayList<>();
        orderList.add(order);

        Checkout checkout = new Checkout();
        checkout.setProductOrders(orderList);

        ProductVerificationHandler handler = new ProductVerificationHandler();

        ChainResponse actual = handler.handle(checkout);

        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail ProductVerificationHandler, null productOrder")
    void nullOrdersHandler(){
        Checkout checkout = new Checkout();

        ProductVerificationHandler handler = new ProductVerificationHandler();

        ChainResponse actual = handler.handle(checkout);

        ChainResponse expected = new ChainResponse(false, "Products to purchase not specified");

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail ProductVerificationHandler, product not enabled")
    void productNotEnabledHandler(){

        Product product = new Product();
        product.setProductId(1);
        product.setStock(10);
        product.setEnabled(false);

        ProductOrder order = new ProductOrder();
        order.setQuantity(1);

        order.setProduct(product);

        List<ProductOrder> orderList = new ArrayList<>();
        orderList.add(order);

        Checkout checkout = new Checkout();
        checkout.setProductOrders(orderList);

        ProductVerificationHandler handler = new ProductVerificationHandler();

        ChainResponse actual = handler.handle(checkout);

        ChainResponse expected = new ChainResponse(false, "Product with id: 1, not enable for checkout");

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail ProductVerificationHandler, stock not enough")
    void stockNotEnoughHandler(){

        Product product = new Product();
        product.setProductId(1);
        product.setStock(5);
        product.setEnabled(true);

        ProductOrder order = new ProductOrder();
        order.setQuantity(10);

        order.setProduct(product);

        List<ProductOrder> orderList = new ArrayList<>();
        orderList.add(order);

        Checkout checkout = new Checkout();
        checkout.setProductOrders(orderList);

        ProductVerificationHandler handler = new ProductVerificationHandler();

        ChainResponse actual = handler.handle(checkout);

        ChainResponse expected = new ChainResponse(false, "Product with id: 1, out of stock for request");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
