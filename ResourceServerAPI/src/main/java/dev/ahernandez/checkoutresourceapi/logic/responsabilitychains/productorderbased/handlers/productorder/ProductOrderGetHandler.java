package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Get productOrder information and sets into another object.
 */
@Component
@AllArgsConstructor
public class ProductOrderGetHandler extends AbstractProductOrderHandler {

  /**
   * Access ProductOrder DB methods.
   */
  private ProductOrderService productOrderService;

  /**
   * <p>
   * Get productOrder information from db
   * and set it into productOrder parameter.
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


    if (productOrderInDb != null) {
      productOrder.setProductOrderDistinct(productOrderInDb);
    }

    return handleNext(productOrder);
  }
}
