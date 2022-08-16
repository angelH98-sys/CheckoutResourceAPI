package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.BaseHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;

/**
 * Abstract class where chackout base handlers extends.
 */
public abstract class AbstractCheckoutHandler implements BaseHandler<Checkout> {

  /**
   * Saves next handler to execute.
   */
  private BaseHandler<Checkout> handler;

  /**
   * Sets BaseHandler state.
   * */
  @Override
  public void setNext(final BaseHandler<Checkout> baseHandler) {
    this.handler = baseHandler;
  }

  /**
   * Executes nextHandler.
   * If its null returns a ChainResponse to end responsabilityChain.
   *
   * @param checkout to send to next handler
   * @return Chain response
   */
  @Override
  public ChainResponse handleNext(final Checkout checkout) {
    if (handler == null) {
      return new ChainResponse(true, "", checkout);
    } else {
      return handler.handle(checkout);
    }
  }
}
