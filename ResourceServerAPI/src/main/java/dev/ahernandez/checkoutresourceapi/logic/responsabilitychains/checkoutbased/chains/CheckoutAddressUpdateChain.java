package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutAuthenticityCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutGetCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutInProgressVerificationHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutUpdateCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.useraddress.UserAddressAuthenticityHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.useraddress.UserAddressEnabledHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.useraddress.UserAddressGetHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Class that builds address checkout update chain of responsability.
 */
@Component
@AllArgsConstructor
public class CheckoutAddressUpdateChain {
  /**
   * Gets checkout information from DB with the checkoutUUID.
   */
  private CheckoutGetCbasedHandler checkoutGetHandler;
  /**
   * Validates if the checkout is inProgress to update it.
   * */
  private CheckoutInProgressVerificationHandler inProgressVerificationHandler;
  /**
   * Validates if the checkout was created by user authenticated.
   */
  private CheckoutAuthenticityCbasedHandler checkoutAuthenticityHandler;
  /**
   * Gets address by userAddressId.
   */
  private UserAddressGetHandler userAddressGetHandler;
  /**
   * Validates if address is enabled to be include in a checkout.
   */
  private UserAddressEnabledHandler userAddressEnabledHandler;
  /**
   * Validates if address found were persist by user authenticated.
   */
  private UserAddressAuthenticityHandler userAddressAuthenticityHandler;
  /**
   * Sets address to checkout.
   */
  private CheckoutUpdateCbasedHandler checkoutUpdateHandler;

  /**
   * Executes responsability chain to set address to checkout.
   *
   * @param checkout to be managed in chain
   * @return ChainResponse with results
   */
  public ChainResponse updateCheckoutAddress(Checkout checkout) {
    final AbstractCheckoutHandler handler1 = checkoutGetHandler;
    final AbstractCheckoutHandler handler2 = inProgressVerificationHandler;
    final AbstractCheckoutHandler handler3 = checkoutAuthenticityHandler;
    final AbstractCheckoutHandler handler4 = userAddressGetHandler;
    final AbstractCheckoutHandler handler5 = userAddressEnabledHandler;
    final AbstractCheckoutHandler handler6 = userAddressAuthenticityHandler;
    final AbstractCheckoutHandler handler7 = checkoutUpdateHandler;

    handler7.setNext(null);
    handler6.setNext(handler7);
    handler5.setNext(handler6);
    handler4.setNext(handler5);
    handler3.setNext(handler4);
    handler2.setNext(handler3);
    handler1.setNext(handler2);

    return handler1.handle(checkout);
  }
}
