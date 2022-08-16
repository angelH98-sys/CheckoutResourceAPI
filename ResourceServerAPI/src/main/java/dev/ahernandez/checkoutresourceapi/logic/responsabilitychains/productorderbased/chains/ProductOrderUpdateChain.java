package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.chains;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutAuthenticityHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutInProgressGetHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutUpdateHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.product.ProductGetHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.product.ProductIsEnabledHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.product.ProductStockHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderGetHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderSaveHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderSetTotalAmountHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Class that manages responsability chain of ProductOrder update.
 */
@Component
@AllArgsConstructor
public class ProductOrderUpdateChain {

  /**
   * Gets productOrder information from DB.
   */
  private ProductOrderGetHandler productOrderGetHandler;
  /**
   * Gets product information from DB.
   */
  private ProductGetHandler productGetHandler;
  /**
   * Gets/creates user checkout with status InProgress.
   */
  private CheckoutInProgressGetHandler checkoutInProgressGetHandler;
  /**
   * Validates if checkout were persisted from user authenticated.
   */
  private CheckoutAuthenticityHandler checkoutAuthenticityHandler;
  /**
   * Validates if product is enabled.
   */
  private ProductIsEnabledHandler productIsEnabledHandler;
  /**
   * Validates if product has enough stock for request.
   */
  private ProductStockHandler productStockHandler;
  /**
   * Calculates total amount for ProductOrder.
   */
  private ProductOrderSetTotalAmountHandler productOrderSetTotalAmountHandler;
  /**
   * Persist ProductOrder in DB.
   */
  private ProductOrderSaveHandler productOrderSaveHandler;
  /**
   * Updates checkout.
   */
  private CheckoutUpdateHandler checkoutUpdateHandler;

  /**
   * Executes productOrder update responsabilityChain.
   *
   * @param productOrder to be handle
   * @return ChainResponse
   */
  public ChainResponse updateProductOrder(ProductOrder productOrder) {
    final AbstractProductOrderHandler handler1 = productOrderGetHandler;
    final AbstractProductOrderHandler handler2 = productGetHandler;
    final AbstractProductOrderHandler handler3 = checkoutInProgressGetHandler;
    final AbstractProductOrderHandler handler4 = checkoutAuthenticityHandler;
    final AbstractProductOrderHandler handler5 = productIsEnabledHandler;
    final AbstractProductOrderHandler handler6 = productStockHandler;
    final AbstractProductOrderHandler handler7 = productOrderSetTotalAmountHandler;
    final AbstractProductOrderHandler handler8 = productOrderSaveHandler;
    final AbstractProductOrderHandler handler9 = checkoutUpdateHandler;

    handler9.setNext(null);
    handler8.setNext(handler9);
    handler7.setNext(handler8);
    handler6.setNext(handler7);
    handler5.setNext(handler6);
    handler4.setNext(handler5);
    handler3.setNext(handler4);
    handler2.setNext(handler3);
    handler1.setNext(handler2);

    return handler1.handle(productOrder);
  }
}
