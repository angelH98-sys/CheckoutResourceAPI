package responsabilitychains.ProductOrderBased.handlers.ProductOrder;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderSetTotalAmountHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ProductOrderSetTotalAmountHandler Test")
class ProductOrderSetTotalAmountHandlerTest {

    @Test
    void successHandler(){
        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("");

        ProductOrder order = new ProductOrder();
        order.setCheckout(checkout);

        int quantity = 1;
        double unitPrice = 1.0;

        order.setQuantity(quantity);
        order.setUnitPrice(unitPrice);

        ProductOrderSetTotalAmountHandler amountHandler = new ProductOrderSetTotalAmountHandler();

        ChainResponse response = amountHandler.handle(order);

        ProductOrder orderResponse = (ProductOrder) response.getObject();
        double actual = orderResponse.getTotalAmount();
        double expected = quantity * unitPrice;

        assertEquals(expected, actual);
    }
}
