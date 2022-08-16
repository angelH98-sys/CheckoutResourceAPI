package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.product;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import org.springframework.stereotype.Component;

/**
 * Validates if product from productOrder has enough stock for request.
 */
@Component
public class ProductStockHandler extends AbstractProductOrderHandler {

  /**
   * <p>
   * Gets product from productOrder and validates if
   * order quantity is less than product stock.
   * If is not, returns a ChainResponse not suceeded and an error message.
   * </p>
   *
   * @param productOrder to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(ProductOrder productOrder) {
    Product product = productOrder.getProduct();
    if (productOrder.getQuantity() > product.getStock()) {
      return new ChainResponse(false, "Product stock not enough for request");
    }

    return handleNext(productOrder);
  }
}
