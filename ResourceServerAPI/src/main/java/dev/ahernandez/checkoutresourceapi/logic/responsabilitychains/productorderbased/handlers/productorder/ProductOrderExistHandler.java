package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Validates if a productOrder exist in DB.
 */
@Component
@AllArgsConstructor
public class ProductOrderExistHandler extends AbstractProductOrderHandler {

  /**
   * Access to ProductOrder DB methods.
   */
  private ProductOrderService productOrderService;

  /**
   * <p>
   * Gets productOrder from DB,
   * if is not founded returns a
   * ChainResponse not succeeded with an error message.
   * </p>
   *
   * @param productOrder to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(ProductOrder productOrder) {
    ProductOrder productOrderInDb = productOrderService
            .getProductOrderByCheckoutAndProduct(
                    productOrder.getCheckout(), productOrder.getProduct());

    if (productOrderInDb == null) {
      return new ChainResponse(false, "Product order not found");
    }

    return handleNext(productOrder);
  }
}
