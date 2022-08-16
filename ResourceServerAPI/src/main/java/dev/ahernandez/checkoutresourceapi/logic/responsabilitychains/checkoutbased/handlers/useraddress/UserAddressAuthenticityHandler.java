package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.useraddress;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Validates if address object inside
 * checkout paramater were persisted by user authenticated.
 */
@Component
public class UserAddressAuthenticityHandler extends AbstractCheckoutHandler {

  /**
   * <p>
   * Gets userId from the securityContext, then gets userAddress
   * from checkout parameter, then validates
   * if address has the same userId as authenticated user.
   * <b>Address from checkout parameter must be setted at this point</b>
   * </p>
   *
   * @param checkout to be handle
   * @return executes next handler
   */
  @Override
  public ChainResponse handle(Checkout checkout) {

    String userId = SecurityContextHolder.getContext().getAuthentication().getName();

    UserAddress address = checkout.getUserAddress();

    if (!address.getUserId().equals(userId)) {
      return new ChainResponse(false, "User Address do not belongs to user authenticated");
    }

    return handleNext(checkout);
  }
}
