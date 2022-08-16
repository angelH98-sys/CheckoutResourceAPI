package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Deletes a productOrder object from the parameter information.
 */
@Component
@AllArgsConstructor
public class ProductOrderDeleteHandler extends AbstractProductOrderHandler {

  /**
   * Access to ProductOrder DB methods.
   */
  private ProductOrderService productOrderService;

  /**
   * Executes a productOder delete in DB.
   *
   * @param productOrder to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(ProductOrder productOrder) {

    productOrderService
            .deleteProductOrderByOrderId(productOrder.getOrderId());

    return handleNext(productOrder);
  }
}
