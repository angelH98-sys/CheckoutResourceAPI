package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import org.springframework.stereotype.Component;

/**
 * Validates if parameter checkout has a user
 * setted and if this user is completly setted in db.
 */
@Component
public class CheckoutUserVerificationHandler extends AbstractCheckoutHandler {

  /**
   * Validates if checkout parameter has a user setted.
   * Then, validates if this user is completely setted.
   * If it doesn't, return ChainResponse not succeeded and a error message.
   * If it does, executes nextHandler
   *
   * @param checkout to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(Checkout checkout) {
    User user = checkout.getUser();

    if (user == null) {
      return new ChainResponse(false, "User not specified");
    }

    if (user.getUserId() == null
            || user.getFirstName() == null
            || user.getLastName() == null
            || user.getMail() == null
            || user.getPhone() == null) {
      return new ChainResponse(false, "User data not complete");
    }

    return handleNext(checkout);
  }
}
