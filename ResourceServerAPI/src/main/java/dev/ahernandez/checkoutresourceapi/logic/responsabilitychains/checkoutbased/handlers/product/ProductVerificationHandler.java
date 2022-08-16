package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.product;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import java.util.Iterator;
import org.springframework.stereotype.Component;

/**
 * Validates if product in checkout.productOrder
 * has enough stock and are enabled to execute a purchase.
 */
@Component
public class ProductVerificationHandler extends AbstractCheckoutHandler {

  /**
   * <p>
   * Gets a productOrder list from checkout parameter.
   * Then, starts a loop to validate if product related
   * to each productOrder has enough stock and are
   * enable to proceed for purchase.
   * If a product has not enough stock or is unavailable,
   * return ChainResponse not succeeded and an error message
   * </p>
   *
   * @param checkout to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(Checkout checkout) {

    if (checkout.getProductOrders() == null
            || checkout.getProductOrders().isEmpty()) {
      return new ChainResponse(false, "Products to purchase not specified");
    }

    Iterator<ProductOrder> orderIterator = checkout.getProductOrders().iterator();
    ProductOrder order;
    Product product;
    int orderQuantity;

    while (orderIterator.hasNext()) {
      order = orderIterator.next();
      product = order.getProduct();
      orderQuantity = order.getQuantity();

      if (!product.isEnabled()) {
        return new ChainResponse(false,
                "Product with id: " + product.getProductId() + ", not enable for checkout");
      }

      if (orderQuantity > product.getStock()) {
        return new ChainResponse(false,
                "Product with id: " + product.getProductId() + ", out of stock for request");
      }
    }

    return handleNext(checkout);
  }
}
