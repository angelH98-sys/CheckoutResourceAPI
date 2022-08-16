package dev.ahernandez.checkoutresourceapi.service.imp;

import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import dev.ahernandez.checkoutresourceapi.persistence.repository.UserPaymentRepository;
import dev.ahernandez.checkoutresourceapi.service.UserPaymentServiceMethods;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * Defines and implements methods to interact with UserPayment DB entity.
 */
@Component
@AllArgsConstructor
public class UserPaymentService implements UserPaymentServiceMethods {

  /**
   * Access to UserPayment DB methods.
   */
  private UserPaymentRepository userPaymentRepository;

  /**
   * Get payment, filter by userId and enabled status.
   *
   * @param userId to filter
   * @return UserPayment
   */
  @Override
  public UserPayment getFirstEnabledPaymentMethod(String userId) {
    Optional<List<UserPayment>> optional = userPaymentRepository
            .findByUserId(userId);

    if (optional.isEmpty() || optional.get().isEmpty()) {
      return null;
    }

    List<UserPayment> payments = optional.get();
    Optional<UserPayment> payment =
            payments.stream().filter(UserPayment::isEnabled).findFirst();

    if (payment.isEmpty()) {
      return null;
    }

    return payment.get();
  }

  /**
   * Get payment by paymentId.
   *
   * @param paymentId to filter
   * @return UserPayment
   */
  @Override
  public UserPayment getPaymentById(int paymentId) {
    Optional<UserPayment> optional = userPaymentRepository.findById(paymentId);

    if (optional.isEmpty()) {
      return null;
    }

    return optional.get();
  }

  /**
   * Persist a userPayment in Db.
   *
   * @param payment to persist
   * @return UserPayment
   */
  @Override
  public UserPayment savePayment(UserPayment payment) {

    String card = payment.getCardNumber();
    String cvv = payment.getCvv();
    String exp = payment.getExpiration();

    card = DigestUtils.sha256Hex(card);
    cvv = DigestUtils.sha256Hex(cvv);
    exp = DigestUtils.sha256Hex(exp);

    payment.setCardNumber(card);
    payment.setCvv(cvv);
    payment.setExpiration(exp);

    return userPaymentRepository.save(payment);
  }

  /**
   * Get all payments by userID.
   *
   * @param userId to filter
   * @return list of userPayment
   */
  @Override
  public List<UserPayment> getAllPaymentByUserId(String userId) {
    Optional<List<UserPayment>> optional = userPaymentRepository.findByUserId(userId);

    if (optional.isEmpty() || optional.get().isEmpty()) {
      return null;
    }

    return optional.get();
  }
}
