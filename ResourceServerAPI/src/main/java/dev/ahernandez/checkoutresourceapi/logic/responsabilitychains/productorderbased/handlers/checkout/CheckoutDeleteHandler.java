package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.service.imp.CheckoutService;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductOrderService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Deletes a checkout in db.
 */
@Component
@AllArgsConstructor
public class CheckoutDeleteHandler extends AbstractProductOrderHandler {

  private CheckoutService checkoutService;
  private ProductOrderService productOrderService;

  /**
   * Gets productOrder, filter by checkoutUUID from DB.
   * If any data is send, checkout is deleted.
   *
   * @param productOrder to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(ProductOrder productOrder) {

    List<ProductOrder> productOrderList = productOrderService
            .getProductOrderByCheckout(productOrder.getCheckout());

    if (productOrderList == null) {

      checkoutService.deleteCheckoutByCheckoutUuid(
              productOrder.getCheckout().getCheckoutUuid());
    }

    return handleNext(productOrder);
  }
}
