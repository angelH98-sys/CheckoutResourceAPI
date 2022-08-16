package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.service.imp.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Validates if productOrder checkoutUUID state exist
 * in db and if it belongs to authenticated user.
 */
@Component
@AllArgsConstructor
public class CheckoutAuthenticityHandler extends AbstractProductOrderHandler {

  /**
   * Access to checkout DB methods.
   */
  private CheckoutService checkoutService;

  /**
   * Gets checkout from productOrder parameter and
   * gets userId from securityContext.
   * If checkout is not set or userId are not equal,
   * returns ChainResponse not succeeded with error message
   *
   * @param productOrder to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(ProductOrder productOrder) {
    Checkout checkout = checkoutService
            .getCheckoutById(productOrder.getCheckout().getCheckoutUuid());

    if (checkout == null) {
      return new ChainResponse(false, "Checkout not found");
    }

    String userId = SecurityContextHolder.getContext().getAuthentication().getName();

    if (!checkout.getUser().getUserId().equals(userId)) {
      return new ChainResponse(false,
              "Checkout do not belongs to authenticated user");
    }

    return handleNext(productOrder);
  }
}
