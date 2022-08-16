package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import org.springframework.stereotype.Component;

/**
 * Validates if the parameter checkout is
 * inProgress to execute an update operation.
 */
@Component
public class CheckoutInProgressVerificationHandler extends AbstractCheckoutHandler {

  /**
   * From the checkout parameter, it will validate if the status is inProgress.
   * <b>Its important that Checkout is completely set in this point</b>
   *
   * @param checkout to be handle
   * @return execute nextHandler
   */
  @Override
  public ChainResponse handle(Checkout checkout) {
    if (!checkout.getCheckoutStatus().equals("InProgress")) {
      return new ChainResponse(false,
              "Checkout is not in progress");
    }

    return handleNext(checkout);
  }
}
