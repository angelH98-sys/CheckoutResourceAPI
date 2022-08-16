package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.service.imp.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Gets checkout persist in DB by checkoutUUID from checkout parameter.
 */
@Component
@AllArgsConstructor
public class CheckoutGetCbasedHandler extends AbstractCheckoutHandler {

  /**
   * Access to checkout DB methods.
   */
  private CheckoutService checkoutService;

  /**
   * Use the checkoutService to find a checkout by checkoutUUID
   * provided in the parameter.
   * <b>Is important that checkout parameter must
   * be setted before include this handler in a particular chain</b>
   * Once found it, its sett it into checkout of the chain
   *
   * @param checkout to be handle
   * @return executes nextHandler
   */
  @Override
  public ChainResponse handle(Checkout checkout) {

    String userId =
            SecurityContextHolder.getContext().getAuthentication().getName();

    Checkout checkoutInDb = checkoutService
            .getByCheckoutIdAndUserId(checkout.getCheckoutUuid(), userId);

    if (checkoutInDb == null) {
      return new ChainResponse(false, "Checkout not found");
    }

    checkout.setCheckoutDistinct(checkoutInDb);

    return handleNext(checkout);
  }
}
