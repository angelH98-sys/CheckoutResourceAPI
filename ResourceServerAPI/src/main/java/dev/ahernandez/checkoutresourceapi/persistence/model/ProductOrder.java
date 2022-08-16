package dev.ahernandez.checkoutresourceapi.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * ProductOrder entity.
 */
@Entity
@Table(name = "productorder")
public class ProductOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "orderid", nullable = false)
  @Getter
  @Setter
  private int orderId;
  @Getter
  @Setter
  @OneToOne
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name = "checkoutuuid", nullable = false)
  private Checkout checkout;
  @Getter
  @Setter
  @OneToOne
  @JoinColumn(name = "productid", nullable = false)
  private Product product;
  @Positive
  @Min(message = "Quantity must be at least 1", value = 1)
  @Getter
  @Setter
  @Column(name = "quantity", nullable = false)
  private int quantity;
  @Getter
  @Setter
  @Column(name = "unitprice")
  private Double unitPrice;
  @Getter
  @Setter
  @Column(name = "totalamount")
  private Double totalAmount;

  /**
   * Set current productOrder object states with parameter order states.
   *
   * @param order has the external states
   */
  public void setProductOrderDistinct(ProductOrder order) {
    if (orderId == 0) {
      orderId = order.getOrderId();
    }
    if (checkout == null) {
      checkout = order.getCheckout();
    }
    if (product == null) {
      product = order.getProduct();
    }
    if (quantity == 0) {
      quantity = order.getQuantity();
    }
    if (unitPrice == null) {
      unitPrice = order.getUnitPrice();
    }
    if (totalAmount == null) {
      totalAmount = order.getTotalAmount();
    }
  }
}
