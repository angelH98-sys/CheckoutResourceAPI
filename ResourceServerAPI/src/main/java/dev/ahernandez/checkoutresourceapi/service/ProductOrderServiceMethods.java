package dev.ahernandez.checkoutresourceapi.service;

import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import java.util.List;

/**
 * Interface to define all methods to
 * request/persit info in ProductOrder entity.
 */
public interface ProductOrderServiceMethods {

  ProductOrder saveProductOrder(ProductOrder productOrder);

  ProductOrder getProductOrderByCheckoutAndProduct(
          Checkout checkout, Product product);

  List<ProductOrder> getProductOrderByCheckout(Checkout checkout);

  void deleteProductOrderByOrderId(int orderId);

  ProductOrder getProductOrderById(int orderId);
}
