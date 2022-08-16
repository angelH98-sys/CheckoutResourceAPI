package dev.ahernandez.checkoutresourceapi.service.imp;

import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.persistence.repository.ProductOrderRepository;
import dev.ahernandez.checkoutresourceapi.service.ProductOrderServiceMethods;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Access to productOrder Db methods.
 */
@Service
@AllArgsConstructor
public class ProductOrderService implements ProductOrderServiceMethods {

  /**
   * Access to Product Order DB methods.
   */
  private ProductOrderRepository productOrderRepository;

  /**
   * Persist productOrder in DB.
   *
   * @param productOrder to be saved in DB
   * @return ProductOrder
   */
  @Override
  public ProductOrder saveProductOrder(ProductOrder productOrder) {
    return productOrderRepository.save(productOrder);
  }

  /**
   * Gets a ProductOrder by checkoutId and productId.
   *
   * @param checkout to filter
   * @param product to filter
   * @return ProductOrder
   */
  @Override
  public ProductOrder getProductOrderByCheckoutAndProduct(Checkout checkout, Product product) {
    Optional<List<ProductOrder>> optional =
            productOrderRepository.findByCheckout(checkout);

    if (optional.isEmpty() || optional.get().isEmpty()) {
      return null;
    }
    List<ProductOrder> productOrders = optional.get();
    Optional<ProductOrder> productOrder = productOrders.stream()
            .filter(p -> p.getProduct().getProductId() == product.getProductId())
            .findFirst();

    if (productOrder.isEmpty()) {
      return null;
    }

    return productOrder.get();
  }

  /**
   * Get a list of productOrder, filter by checkoutId.
   *
   * @param checkout to be handle
   * @return list of productOrder
   */
  @Override
  public List<ProductOrder> getProductOrderByCheckout(Checkout checkout) {
    Optional<List<ProductOrder>> optional =
            productOrderRepository.findByCheckout(checkout);

    if (optional.isEmpty() || optional.get().isEmpty()) {
      return null;
    }

    return  optional.get();
  }

  /**
   * Deletes a productOrder in DB.
   *
   * @param orderId to be deleted
   */
  @Override
  public void deleteProductOrderByOrderId(int orderId) {
    productOrderRepository.deleteById(orderId);
  }

  /**
   * Gets a productorder, filtering by id.
   *
   * @param orderId to filter
   * @return ProductOrder
   */
  @Override
  public ProductOrder getProductOrderById(int orderId) {
    Optional<ProductOrder> optional =
            productOrderRepository.findById(orderId);
    if (optional.isEmpty()) {
      return null;
    }
    return optional.get();
  }
}
