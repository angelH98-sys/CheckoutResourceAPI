package dev.ahernandez.checkoutresourceapi.service;

import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import java.util.List;

/**
 * Interface to define all methods to request/persit info in Checkout entity.
 */
public interface CheckoutServiceMethods {

  Checkout getInProgressCheckoutByUserId(String userId);

  Checkout getCheckoutById(String checkoutId);

  Checkout saveCheckout(Checkout checkout);

  void deleteCheckoutByCheckoutUuid(String checkoutUuid);

  Checkout getByCheckoutIdAndUserId(String checkoutId, String userId);

  List<Checkout> getCheckoutByUserId(String userId);
}
