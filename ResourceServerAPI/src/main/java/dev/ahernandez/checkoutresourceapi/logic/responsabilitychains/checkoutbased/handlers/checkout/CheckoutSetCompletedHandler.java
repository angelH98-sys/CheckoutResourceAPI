package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import org.springframework.stereotype.Component;

/**
 * Changes checkout parameter status to Completed.
 */
@Component
public class CheckoutSetCompletedHandler extends AbstractCheckoutHandler {

  /**
   * Change parameter checkout status to Completed.
   *
   * @param checkout to handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(Checkout checkout) {
    checkout.setCheckoutStatus("Completed");
    return handleNext(checkout);
  }
}
