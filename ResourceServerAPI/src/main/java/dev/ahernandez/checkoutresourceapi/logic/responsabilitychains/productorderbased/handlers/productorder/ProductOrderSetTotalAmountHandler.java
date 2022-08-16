package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import org.springframework.stereotype.Component;

/**
 * Calculates totalAmount to set it into productOrder.
 */
@Component
public class ProductOrderSetTotalAmountHandler extends AbstractProductOrderHandler {

  /**
   * Calculates total amount from quantity and unit price.
   * Then is setted into productOrder.totalAmount state.
   *
   * @param productOrder to be handle
   * @return ChainResponses
   */
  @Override
  public ChainResponse handle(ProductOrder productOrder) {
    double totalAmount = productOrder.getQuantity() * productOrder.getUnitPrice();
    productOrder.setTotalAmount(totalAmount);

    return handleNext(productOrder);
  }
}
