package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.userpayment;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import org.springframework.stereotype.Component;

/**
 * Validates if payment object inside checkout
 * parameter is enabled to be used in a checkout update.
 */
@Component
public class UserPaymentEnabledHandler extends AbstractCheckoutHandler {

  /**
   * Gets payment object from checkout parameter and validates if its enabled.
   * <b>Payment from checkout parameter must be setted at this point</b>
   *
   * @param checkout to be handle
   * @return executes next handler
   */
  @Override
  public ChainResponse handle(Checkout checkout) {
    if (!checkout.getUserPayment().isEnabled()) {
      return new ChainResponse(false, "Payment not enabled");
    }
    return handleNext(checkout);
  }
}
