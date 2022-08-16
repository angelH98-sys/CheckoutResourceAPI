package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.service.imp.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Persist Checkout from parameter in DB.
 */
@Component
@AllArgsConstructor
public class CheckoutUpdateCbasedHandler extends AbstractCheckoutHandler {

  private CheckoutService checkoutService;

  /**
   * Persist checkout from the parameter.
   * <b>Checkout parameter must be completely setted at this point</b>
   *
   * @param checkout to be handle
   * @return executes next handler
   */
  @Override
  public ChainResponse handle(Checkout checkout) {
    checkout = checkoutService.saveCheckout(checkout);
    return handleNext(checkout);
  }
}
