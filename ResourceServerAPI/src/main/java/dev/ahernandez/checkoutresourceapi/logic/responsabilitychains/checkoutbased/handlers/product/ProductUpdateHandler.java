package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.product;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Get products from checkout.productOrder list,
 * updates each stock and persist changes to DB.
 */
@Component
@AllArgsConstructor
public class ProductUpdateHandler extends AbstractCheckoutHandler {

  /**
   * Access to DB methods.
   */
  private ProductService productService;

  /**
   * <p>
   * Gets ProductOrders's from parameter checkout,
   * then iterates each one getting the product.
   * Product stock is updated and feeds a productList.
   * When this loop finnish, persist the list in DB.
   * </p>
   *
   * @param checkout to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(Checkout checkout) {

    Iterator<ProductOrder> orderIterator =
            checkout.getProductOrders().iterator();
    ProductOrder order;
    Product product;
    int stock;

    List<Product> productList = new ArrayList<>();
    while (orderIterator.hasNext()) {
      order = orderIterator.next();
      product = order.getProduct();
      stock = product.getStock() - order.getQuantity();
      product.setStock(stock);
      productList.add(product);
    }

    productService.saveAll(productList);

    return handleNext(checkout);
  }
}
