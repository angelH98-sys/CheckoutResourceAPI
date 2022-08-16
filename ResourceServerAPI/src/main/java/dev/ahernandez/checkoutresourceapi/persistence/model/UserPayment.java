package dev.ahernandez.checkoutresourceapi.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * UserPayment entity.
 */
@Entity
@Table(name = "userpayment")
public class UserPayment {

  @Getter
  @Setter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "paymentid", nullable = false)
  private int paymentId;
  @Getter
  @Setter
  @Column(name = "userid")
  private String userId;
  @Getter
  @Setter
  @Column(name = "paymentname")
  private String paymentName;
  @Getter
  @Setter
  @Column(name = "ownername")
  private String ownerName;
  @Getter
  @Setter
  @Column(name = "cardnumber")
  private String cardNumber;
  @Getter
  @Setter
  @Column(name = "expiration")
  private String expiration;
  @Getter
  @Setter
  @Column(name = "cvv")
  private String cvv;
  @Getter
  @Setter
  @Column(name = "enabled", nullable = false)
  private boolean enabled;

  /**
   * Equals implementation for UserPayment objects.
   *
   * @param obj to be compared
   * @return boolean
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }

    UserPayment payment = (UserPayment) obj;

    if (payment.getPaymentId() != paymentId) {
      return false;
    }
    if (!payment.getUserId().equals(userId)) {
      return false;
    }
    if (!payment.getPaymentName().equals(paymentName)) {
      return false;
    }
    if (!payment.getOwnerName().equals(ownerName)) {
      return false;
    }
    if (!payment.getCardNumber().equals(cardNumber)) {
      return false;
    }
    if (!payment.getExpiration().equals(expiration)) {
      return false;
    }
    if (!payment.getCvv().equals(cvv)) {
      return false;
    }

    return true;

  }

  /**
   * Ser current object with parameter payment states.
   *
   * @param payment to be setted
   */
  public void setUserPaymentDistinct(UserPayment payment) {
    if (paymentId == 0 || paymentId == 1) {
      paymentId = payment.getPaymentId();
    }
    if (userId == null) {
      userId = payment.getUserId();
    }
    if (paymentName == null) {
      paymentName = payment.getPaymentName();
    }
    if (ownerName == null) {
      ownerName = payment.getOwnerName();
    }
    if (cardNumber == null) {
      cardNumber = payment.getCardNumber();
    }
    if (expiration == null) {
      expiration = payment.getExpiration();
    }
    if (cvv == null) {
      cvv = payment.getCvv();
    }
    if (enabled == false) {
      enabled = payment.isEnabled();
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
