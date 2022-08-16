package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.useraddress;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import org.springframework.stereotype.Component;

/**
 * Validates if address object in checkout
 * parameter is enabled to be included in an update.
 */
@Component
public class UserAddressEnabledHandler extends AbstractCheckoutHandler {

  /**
   * <p>
   * Gets userAddress object from checkout
   * parameter and validates if is enabled.
   * <b>UserAddress from checkout parameter must be setted at this point</b>
   * </p>
   *
   * @param checkout to be handle
   * @return executes next handler
   */
  @Override
  public ChainResponse handle(Checkout checkout) {

    UserAddress address = checkout.getUserAddress();

    if (!address.isEnabled()) {
      return new ChainResponse(false, "Address not enabled");
    }

    return handleNext(checkout);
  }
}
