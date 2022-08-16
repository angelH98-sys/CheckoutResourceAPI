package dev.ahernandez.checkoutresourceapi.persistence.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Checkout entity class.
 */
@Entity
@Table(name = "checkout")
public class Checkout {

  @Getter
  @Setter
  @Id
  @Column(name = "checkoutuuid", nullable = false)
  private String checkoutUuid;

  @Getter
  @Setter
  @OneToOne()
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name = "userid", nullable = false)
  private User user;

  @Getter
  @Setter
  @OneToOne
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name = "addressid", nullable = false)
  private UserAddress userAddress;

  @Getter
  @Setter
  @OneToOne
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name = "paymentid", nullable = false)
  private UserPayment userPayment;
  @Getter
  @Setter
  @Column(name = "totalamount")
  private Double totalAmount;
  @Getter
  @Setter
  @Column(name = "checkoutstatus")
  private String checkoutStatus;
  @Getter
  @Setter
  @Transient
  private List<ProductOrder> productOrders;

  /**
   * Set to current object, all states comparing parameter checkout.
   *
   * @param checkout to be compared with current one
   */
  public void setCheckoutDistinct(Checkout checkout) {
    if (checkoutUuid == null) {
      checkoutUuid = checkout.getCheckoutUuid();
    }
    if (user ==  null) {
      user = checkout.getUser();
    }
    if (userAddress == null) {
      userAddress = checkout.getUserAddress();
    }
    if (userPayment == null) {
      userPayment = checkout.userPayment;
    }
    if (totalAmount == null) {
      totalAmount = checkout.getTotalAmount();
    }
    if (checkoutStatus == null) {
      checkoutStatus = checkout.getCheckoutStatus();
    }
    if (productOrders == null) {
      productOrders = checkout.getProductOrders();
    }
  }
}
