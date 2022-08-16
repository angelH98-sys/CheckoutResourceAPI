package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutAuthenticityCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutGetCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutInProgressVerificationHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutUpdateCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.userpayment.UserPaymentAuthenticityHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.userpayment.UserPaymentEnabledHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.userpayment.UserPaymentGetHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Manages payment update chain of responsability.
 */
@Component
@AllArgsConstructor
public class CheckoutPaymentUpdateChain {

  /**
   * Gets checkout from DB, filter by checkoutUUID.
   */
  private CheckoutGetCbasedHandler checkoutGetHandler;
  /**
   * Validates if checkout found is inProgress to execute an update.
   */
  private CheckoutInProgressVerificationHandler inProgressVerificationHandler;
  /**
   * Validates if checkout parameter belongs to authenticated user.
   */
  private CheckoutAuthenticityCbasedHandler checkoutAuthenticityHandler;
  /**
   * Gets userPayment object from DB, filter by paymentId.
   */
  private UserPaymentGetHandler paymentGetHandler;
  /**
   * Validates if payment found is enabled to be used in a checkout update.
   */
  private UserPaymentEnabledHandler paymentEnabledHandler;
  /**
   * Validates if payment were persisted by authenticated user.
   */
  private UserPaymentAuthenticityHandler paymentAuthenticityHandler;
  /**
   * Execute a checkout update in DB.
   */
  private CheckoutUpdateCbasedHandler checkoutUpdateHandler;

  /**
   * Executes responsability chain to update checkoutpayment state.
   *
   * @param checkout to be handle in chain
   * @return ChainResponse
   */
  public ChainResponse paymentUpdate(Checkout checkout) {
    final AbstractCheckoutHandler handler1 = checkoutGetHandler;
    final AbstractCheckoutHandler handler2 = inProgressVerificationHandler;
    final AbstractCheckoutHandler handler3 = checkoutAuthenticityHandler;
    final AbstractCheckoutHandler handler4 = paymentGetHandler;
    final AbstractCheckoutHandler handler5 = paymentEnabledHandler;
    final AbstractCheckoutHandler handler6 = paymentAuthenticityHandler;
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
