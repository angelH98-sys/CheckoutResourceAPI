package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import org.springframework.stereotype.Component;

/**
 * Validates if checkout parameter has setted a UserPayment.
 */
@Component
public class CheckoutPaymentVerificationHandler extends AbstractCheckoutHandler {

  /**
   * Validates if parameter checkout has a UserPayment setted.
   * If it doesn't, return ChainResponse not succeded with an error message.
   * If it does, executes nexthandler
   *
   * @param checkout to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(Checkout checkout) {
    UserPayment payment = checkout.getUserPayment();

    if (payment == null) {
      return new ChainResponse(false,
              "Payment method not selected");
    }

    return handleNext(checkout);
  }
}
