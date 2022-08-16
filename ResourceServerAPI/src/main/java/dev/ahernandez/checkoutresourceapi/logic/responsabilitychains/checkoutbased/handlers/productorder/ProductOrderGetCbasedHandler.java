package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.productorder;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductOrderService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Gets productOrders data from DB, filtering by checkoutuuid.
 */
@Component
@AllArgsConstructor
public class ProductOrderGetCbasedHandler extends AbstractCheckoutHandler {

  /**
   * Has access to productOrder DB methods.
   */
  private ProductOrderService productOrderService;

  /**
   * Gets ProductOrder related to checkout and setted to parameter.
   * Then continues with nextHandler.
   *
   * @param checkout to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(Checkout checkout) {
    List<ProductOrder> orderList = productOrderService
            .getProductOrderByCheckout(checkout);
    checkout.setProductOrders(orderList);
    return handleNext(checkout);
  }
}
