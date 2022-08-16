package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Gets productOrder from db, if is found,
 * its quantity is added to current quantity from productOrder parameter.
 */
@Component
@AllArgsConstructor
public class ProductOrderAddQuantityHandler extends AbstractProductOrderHandler {

  /**
   * ProductOrder DB methods.
   */
  private ProductOrderService productOrderService;

  /**
   * Gets productOrder from DB and executes the add.
   *
   * @param productOrder to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(ProductOrder productOrder) {
    ProductOrder productOrderInDb = productOrderService
            .getProductOrderById(productOrder.getOrderId());

    if (productOrderInDb != null) {
      productOrder.setQuantity(
              productOrder.getQuantity() + productOrderInDb.getQuantity());
    }

    return handleNext(productOrder);
  }
}
