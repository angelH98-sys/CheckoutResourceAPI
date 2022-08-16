package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.BaseHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;

/**
 * BaseHandler default implementations based on ProductOrder.
 */
public abstract class AbstractProductOrderHandler
        implements BaseHandler<ProductOrder> {

  /**
   * Stores next handler.
   */
  private BaseHandler<ProductOrder> handler;

  /**
   * Sets next handler.
   *
   * @param baseHandler to be set as next handler
   */
  @Override
  public void setNext(final BaseHandler baseHandler) {
    this.handler = baseHandler;
  }

  /**
   * Executes next handler handle method.
   *
   * @param productOrder to send to next parameter
   * @return Chain response
   */
  @Override
  public ChainResponse handleNext(final ProductOrder productOrder) {
    if (handler == null) {
      return new ChainResponse(true,
              productOrder.getCheckout().getCheckoutUuid(), productOrder);
    } else {
      return handler.handle(productOrder);
    }
  }
}
