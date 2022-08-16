package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutAddressVerificationHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutGetCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutInProgressVerificationHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutPaymentVerificationHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutSetCompletedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutUpdateCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutUserVerificationHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.product.ProductUpdateHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.product.ProductVerificationHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.productorder.ProductOrderGetCbasedHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Set Completed checkout status and then persist into DB.
 */
@Component
@AllArgsConstructor
public class CheckoutStatusUpdateChain {

  /**
   * Request checkout data to db.
   */
  private CheckoutGetCbasedHandler getHandler;
  /**
   * Validates if checkout is inProgress.
   */
  private CheckoutInProgressVerificationHandler inProgressHandler;
  /**
   * Validates if checkout user has setted complete his data.
   */
  private CheckoutUserVerificationHandler userVerificationHandler;
  /**
   * Validates if UserAddress is setted in checkout.
   */
  private CheckoutAddressVerificationHandler addressVerificationHandler;
  /**
   * Validates if UserPayment is setted in checkout.
   */
  private CheckoutPaymentVerificationHandler paymentVerificationHandler;
  /**
   * Request products registed with checkoutuuid.
   */
  private ProductOrderGetCbasedHandler productOrderGetHandler;
  /**
   * Validates if products has enough stock and are enable to update.
   */
  private ProductVerificationHandler productVerificationHandler;
  /**
   * Persist changes in checkout.produtOrder in DB.
   */
  private ProductUpdateHandler productUpdateHandler;
  /**
   * Set checkout.status to Completed.
   */
  private CheckoutSetCompletedHandler setCompletedHandler;
  /**
   * Persist checkout changes in DB.
   */
  private CheckoutUpdateCbasedHandler updateHandler;

  /**
   * Executes checkout status responsability chain.
   *
   * @param checkout to be handle
   * @return ChainResponse
   */
  public ChainResponse setCompleted(Checkout checkout) {
    final AbstractCheckoutHandler handler1 = getHandler;
    final AbstractCheckoutHandler handler2 = inProgressHandler;
    final AbstractCheckoutHandler handler3 = userVerificationHandler;
    final AbstractCheckoutHandler handler4 = addressVerificationHandler;
    final AbstractCheckoutHandler handler5 = paymentVerificationHandler;
    final AbstractCheckoutHandler handler6 = productOrderGetHandler;
    final AbstractCheckoutHandler handler7 = productVerificationHandler;
    final AbstractCheckoutHandler handler8 = productUpdateHandler;
    final AbstractCheckoutHandler handler9 = setCompletedHandler;
    final AbstractCheckoutHandler handler10 = updateHandler;

    handler10.setNext(null);
    handler9.setNext(handler10);
    handler8.setNext(handler9);
    handler7.setNext(handler8);
    handler6.setNext(handler7);
    handler5.setNext(handler6);
    handler4.setNext(handler5);
    handler3.setNext(handler4);
    handler2.setNext(handler3);
    handler1.setNext(handler2);

    return handler1.handle(checkout);
  }
}
