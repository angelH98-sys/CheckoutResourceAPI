package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.product;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Gets product from DB.
 */
@Component
@AllArgsConstructor
public class ProductGetHandler extends AbstractProductOrderHandler {

  /**
   * Product DB methods.
   */
  private ProductService productService;

  /**
   * Gets product by ID in DB.
   * If is not found, return a ChainResponse
   * not succeeded with an error message.
   * If is found, sets product state and unit price to ProductOrder.
   *
   * @param productOrder to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(ProductOrder productOrder) {
    Product product = productService
            .findById(productOrder.getProduct().getProductId());

    if (product == null) {
      return new ChainResponse(false, "Product not found");
    }

    productOrder.setProduct(product);
    productOrder.setUnitPrice(product.getUnitPrice());

    return handleNext(productOrder);
  }
}
