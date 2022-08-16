package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Validates if checkout parameter belongs to authenticated user.
 */
@Component
public class CheckoutAuthenticityCbasedHandler extends AbstractCheckoutHandler {

  /**
   * Gets userId from the securityContext and then compares it with checkout userid.
   * <b>Checkout parameter must be setted at this point</b>
   *
   * @param checkout to be handle
   * @return executes nexthandler
   */
  @Override
  public ChainResponse handle(Checkout checkout) {

    User user = checkout.getUser();

    String userId = SecurityContextHolder.getContext().getAuthentication().getName();

    if (!user.getUserId().equals(userId)) {
      return new ChainResponse(false, "Checkout do not belongs to authenticated user");
    }

    return handleNext(checkout);
  }
}
