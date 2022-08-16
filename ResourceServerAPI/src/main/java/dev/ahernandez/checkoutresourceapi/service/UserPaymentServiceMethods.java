package dev.ahernandez.checkoutresourceapi.service;

import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import java.util.List;

/**
 * Interface to define all methods to
 * request/persit info in UserPayment entity.
 */
public interface UserPaymentServiceMethods {

  UserPayment getFirstEnabledPaymentMethod(String userId);

  UserPayment getPaymentById(int paymentId);

  UserPayment savePayment(UserPayment payment);

  List<UserPayment> getAllPaymentByUserId(String userId);
}
