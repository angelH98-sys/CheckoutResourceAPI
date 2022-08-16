package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.service.imp.CheckoutService;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductOrderService;
import java.util.Iterator;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * Updates totalAmount on InProgress checkout
 * (adding ProductOrder totalAmount with checkout totalAmount).
 */
@Component
@AllArgsConstructor
public class CheckoutUpdateHandler extends AbstractProductOrderHandler {
  private CheckoutService checkoutService;
  private ProductOrderService productOrderService;

  /**
   * Gets inProgress Checkout and all ProductOrder persisted with checkoutId.
   * Loop every ProductOrder and sums totalAmount.
   * Then will set totalAmount to checkout.
   * Once did everything, checkout will be persisted.
   *
   * @param productOrder to be handle
   * @return executes next handler
   */
  @Override
  public ChainResponse handle(ProductOrder productOrder) {

    Checkout checkout = productOrder.getCheckout();

    List<ProductOrder> productOrderList = productOrderService
            .getProductOrderByCheckout(checkout);

    double totalAmount = 0.0;

    if (productOrderList != null) {
      Iterator<ProductOrder> productOrderIterator = productOrderList.iterator();

      while (productOrderIterator.hasNext()) {
        totalAmount += productOrderIterator.next().getTotalAmount();
      }
    }

    checkout.setTotalAmount(totalAmount);

    checkoutService.saveCheckout(checkout);

    productOrder.setCheckout(checkout);

    return handleNext(productOrder);
  }
}
