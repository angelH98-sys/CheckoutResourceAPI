package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutAuthenticityCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutGetCbasedHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.productorder.ProductOrderGetCbasedHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.web.dto.checkout.CheckoutDetailedDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Builds responsability chain of checkout get endpoint.
 */
@Component
@AllArgsConstructor
public class CheckoutGetChain {
  /**
   * Get checkout in DB, filtering by checkoutUUID and userid.
   */
  private CheckoutGetCbasedHandler checkoutGetHandler;
  /**
   * Validates if checkout found was persisted by user authenticated.
   * */
  private CheckoutAuthenticityCbasedHandler checkoutAuthenticityCbasedHandler;
  /**
   * Gets a List of ProductOrders and includes it into checkout parameter.
   */
  private ProductOrderGetCbasedHandler productOrderGetHandler;

  /**
   * Executes responsability chain to request checkout data.
   *
   * @param checkout to be handle in chain
   * @return ChainResponse
   */
  public ChainResponse getCheckout(Checkout checkout) {
    AbstractCheckoutHandler handler1 = checkoutGetHandler;
    AbstractCheckoutHandler handler2 = checkoutAuthenticityCbasedHandler;
    AbstractCheckoutHandler handler3 = productOrderGetHandler;

    handler3.setNext(null);
    handler2.setNext(handler3);
    handler1.setNext(handler2);

    ChainResponse response = handler1.handle(checkout);

    if (response.isItSucceed()) {
      CheckoutDetailedDto checkoutDetailedDto =
                      new CheckoutDetailedDto((Checkout) response.getObject());
      response.setObject(checkoutDetailedDto);
    }

    return response;
  }
}
