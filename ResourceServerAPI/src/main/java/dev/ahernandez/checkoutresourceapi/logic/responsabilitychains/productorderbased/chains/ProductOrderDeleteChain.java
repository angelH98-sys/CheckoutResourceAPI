package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.chains;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutAuthenticityHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutDeleteHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutInProgressGetHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutUpdateHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderDeleteHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderExistHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderGetHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Defines handlers to execute a ProductOrder delete.
 */
@Component
@AllArgsConstructor
public class ProductOrderDeleteChain {

  /**
   * Gets product information from DB.
   */
  private ProductOrderGetHandler productOrderGetHandler;
  /**
   * Validates if Productorder exist in DB.
   */
  private ProductOrderExistHandler productOrderExistHandler;
  /**
   * Gets/creates user checkout with status InProgress.
   */
  private CheckoutInProgressGetHandler checkoutInProgressGetHandler;
  /**
   * Validates if checkout were persisted from user authenticated.
   */
  private CheckoutAuthenticityHandler checkoutAuthenticityHandler;
  /**
   * Deletes ProductOrder from DB.
   */
  private ProductOrderDeleteHandler productOrderDeleteHandler;
  /**
   * updates checkout.
   */
  private CheckoutUpdateHandler checkoutUpdateHandler;
  /**
   * Validates if is no more productOrder with checkoutUUID to delete.
   */
  private CheckoutDeleteHandler checkoutDeleteHandler;

  /**
   * Executes deleteProductOrder responsability chain.
   *
   * @param productOrder to be handle
   * @return ChainResponse
   */
  public ChainResponse deleteProductOrder(ProductOrder productOrder) {

    final AbstractProductOrderHandler handler1 = productOrderGetHandler;
    final AbstractProductOrderHandler handler2 = productOrderExistHandler;
    final AbstractProductOrderHandler handler3 = checkoutInProgressGetHandler;
    final AbstractProductOrderHandler handler4 = checkoutAuthenticityHandler;
    final AbstractProductOrderHandler handler5 = productOrderDeleteHandler;
    final AbstractProductOrderHandler handler6 = checkoutUpdateHandler;
    final AbstractProductOrderHandler handler7 = checkoutDeleteHandler;

    handler7.setNext(null);
    handler6.setNext(handler7);
    handler5.setNext(handler6);
    handler4.setNext(handler5);
    handler3.setNext(handler4);
    handler2.setNext(handler3);
    handler1.setNext(handler2);

    return handler1.handle(productOrder);
  }
}
