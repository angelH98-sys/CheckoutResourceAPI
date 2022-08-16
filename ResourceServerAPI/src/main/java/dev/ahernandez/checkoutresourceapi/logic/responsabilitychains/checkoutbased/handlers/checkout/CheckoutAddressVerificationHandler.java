package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import org.springframework.stereotype.Component;

/**
 * Validates if checkout parameter has UserAddress setted.
 */
@Component
public class CheckoutAddressVerificationHandler extends AbstractCheckoutHandler {

  /**
   * Executes a validation to know if UserAddress is setted in checkout parameter.
   * If is not set, ChainResponse dont succeed and send an error message.
   * If is set, executes nextHandler()
   *
   * @param checkout to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(Checkout checkout) {
    UserAddress address = checkout.getUserAddress();
    if (address == null) {
      return new ChainResponse(false, "Address not specified");
    }
    return handleNext(checkout);
  }
}
