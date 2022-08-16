package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.chains;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutInProgressGetHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout.CheckoutUpdateHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.product.ProductGetHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.product.ProductIsEnabledHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.product.ProductStockHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderAddQuantityHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderGetHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderSaveHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.productorder.ProductOrderSetTotalAmountHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Class that builds responsability chain to add product to order.
 */
@Component
@AllArgsConstructor
public class ProductOrderCreateChain {

  /**
   * Gets product information from DB.
   */
  private ProductGetHandler productGetHandler;
  /**
   * Validates if product is enabled.
   */
  private ProductIsEnabledHandler productIsEnabledHandler;
  /**
   * Validates if product has enough stock for request.
   */
  private ProductStockHandler productStockHandler;
  /**
   * Gets/creates user checkout with status InProgress.
   */
  private CheckoutInProgressGetHandler checkoutInProgressGetHandler;
  /**
   * Gets productOrder value from DB, if creates same product.
   */
  private ProductOrderGetHandler productOrderGetHandler;
  /**
   * Gets productOrder from db, if its found,
   * quantity is added to current request quantity.
   */
  private ProductOrderAddQuantityHandler productOrderAddQuantityHandler;
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
   * Executes productOrder creation responsabilityChain.
   *
   * @param productOrder to be handle
   * @return ChainResponse
   */
  public ChainResponse addProductToCheckout(ProductOrder productOrder) {

    final AbstractProductOrderHandler handler1 = productGetHandler;
    final AbstractProductOrderHandler handler2 = productIsEnabledHandler;
    final AbstractProductOrderHandler handler3 = checkoutInProgressGetHandler;
    final AbstractProductOrderHandler handler4 = productOrderGetHandler;
    final AbstractProductOrderHandler handler5 = productOrderAddQuantityHandler;
    final AbstractProductOrderHandler handler6 = productOrderSetTotalAmountHandler;
    final AbstractProductOrderHandler handler7 = productStockHandler;
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
