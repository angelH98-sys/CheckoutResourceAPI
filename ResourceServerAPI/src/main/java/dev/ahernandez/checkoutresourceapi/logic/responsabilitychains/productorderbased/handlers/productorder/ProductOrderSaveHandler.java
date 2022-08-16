package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Persist a productOrder in DB.
 */
@Component
@AllArgsConstructor
public class ProductOrderSaveHandler extends AbstractProductOrderHandler {

  /**
   * Access to ProductOrder DB methods.
   */
  private ProductOrderService productOrderService;

  /**
   * Saves productOrder parameter in DB.
   *
   * @param productOrder to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(ProductOrder productOrder) {
    productOrder = productOrderService.saveProductOrder(productOrder);
    return handleNext(productOrder);
  }
}
