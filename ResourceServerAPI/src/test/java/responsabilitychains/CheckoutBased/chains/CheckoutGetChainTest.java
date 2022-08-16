package responsabilitychains.CheckoutBased.chains;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutGetChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutAuthenticityCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutGetCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.productorder.ProductOrderGetCbasedHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.*;
import dev.ahernandez.checkoutresourceapi.web.dto.checkout.CheckoutDetailedDto;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("CheckoutGetChain Test")
class CheckoutGetChainTest {
    @Mock
    private CheckoutGetCbasedHandler checkoutGetCbasedHandler;
    @Mock
    private CheckoutAuthenticityCbasedHandler checkoutAuthenticityCbasedHandler;
    @Mock
    private ProductOrderGetCbasedHandler productOrderGetCbasedHandler;

    @InjectMocks
    private CheckoutGetChain getChain;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void successChain(){
        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");
        checkout.setUser(new User());
        checkout.setUserAddress(new UserAddress());
        checkout.setUserPayment(new UserPayment());
        checkout.setTotalAmount(1.0);
        checkout.setCheckoutStatus("InProgress");

        List<ProductOrder> productOrders = new ArrayList<>();
        Product product = new Product();
        product.setProductId(1);
        product.setProductName("");

        ProductOrder order = new ProductOrder();
        order.setOrderId(1);
        order.setQuantity(1);
        order.setUnitPrice(1.0);
        order.setTotalAmount(1.0);

        order.setProduct(product);
        productOrders.add(order);
        checkout.setProductOrders(productOrders);

        Mockito.when(checkoutGetCbasedHandler.handle(any(Checkout.class)))
                .thenReturn(new ChainResponse(true, "", checkout));

        ChainResponse actual = getChain.getCheckout(checkout);

        CheckoutDetailedDto checkoutDetailedDto = new CheckoutDetailedDto(checkout);

        ChainResponse expected = new ChainResponse(true, "", checkoutDetailedDto);

        assertEquals(expected.isItSucceed(), actual.isItSucceed());
    }
}
