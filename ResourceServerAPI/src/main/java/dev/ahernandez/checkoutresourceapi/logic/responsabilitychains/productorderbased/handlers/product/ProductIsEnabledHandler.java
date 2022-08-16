package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.product;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import org.springframework.stereotype.Component;

/**
 * Validates if product in ProductOrder is enabled.
 */
@Component
public class ProductIsEnabledHandler extends AbstractProductOrderHandler {

  /**
   * Gets product from productOrder and validates if is enabled.
   * If is not, return ChainResponse not succeeded with error message.
   *
   * @param productOrder to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(ProductOrder productOrder) {
    Product product = productOrder.getProduct();
    if (!product.isEnabled()) {
      return new ChainResponse(false, "Product not available");
    }

    return handleNext(productOrder);
  }
}
