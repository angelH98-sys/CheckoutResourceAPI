package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.userpayment;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Validates if payment inside checkout
 * parameter were persisted by authenticated user.
 */
@Component
public class UserPaymentAuthenticityHandler extends AbstractCheckoutHandler {

  /**
   * <p>
   * Gets userId from SecurityContext,
   * then gets payment object from checkout parameter and then
   * validates if are equal.
   * <b>Payment object from checkout parameter must be setted at this point</b>
   * </p>
   *
   * @param checkout to be handle
   * @return executes next handler
   */
  @Override
  public ChainResponse handle(Checkout checkout) {
    String userId = SecurityContextHolder.getContext().getAuthentication().getName();
    UserPayment payment = checkout.getUserPayment();

    if (!payment.getUserId().equals(userId)) {
      return new ChainResponse(false, "Payment do not belongs to authenticated user");
    }

    return handleNext(checkout);
  }
}
