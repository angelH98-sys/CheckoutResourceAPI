package dev.ahernandez.checkoutresourceapi.web.dto.productorder;

import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import lombok.Getter;
import lombok.Setter;

/**
 * ProductOrderLiteDto.
 */
public class ProductOrderLiteDto {
  @Getter
  @Setter
  private int orderId;
  @Getter
  @Setter
  private int productId;
  @Getter
  @Setter
  private String productName;
  @Getter
  @Setter
  private int quantity;
  @Getter
  @Setter
  private Double unitPrice;
  @Getter
  @Setter
  private Double totalAmount;

  /**
   * Constructor from a ProductOrder object.
   *
   * @param productOrder has the base states
   */
  public ProductOrderLiteDto(ProductOrder productOrder) {
    orderId = productOrder.getOrderId();
    productId = productOrder.getProduct().getProductId();
    productName = productOrder.getProduct().getProductName();
    quantity = productOrder.getQuantity();
    unitPrice = productOrder.getUnitPrice();
    totalAmount = productOrder.getTotalAmount();
  }
}
