package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.userpayment;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import dev.ahernandez.checkoutresourceapi.service.imp.UserPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Get a userPayment object from DB, filter
 * by paymentID provided from checkout parameter.
 */
@Component
@AllArgsConstructor
public class UserPaymentGetHandler extends AbstractCheckoutHandler {

  /**
   * Access to UserPayment DB methods.
   */
  private UserPaymentService paymentService;

  /**
   * Uses paymentService to get a payment object filter by paymentId.
   * <b>Checkout parameter must have setted paymentId</b>
   *
   * @param checkout to be handle
   * @return executes next handler
   */
  @Override
  public ChainResponse handle(Checkout checkout) {

    UserPayment payment = checkout.getUserPayment();

    UserPayment paymentInDb =
            paymentService.getPaymentById(payment.getPaymentId());

    if (paymentInDb == null) {
      return new ChainResponse(false, "Payment not found");
    }

    payment.setUserPaymentDistinct(paymentInDb);

    checkout.setUserPayment(payment);

    return handleNext(checkout);
  }
}
