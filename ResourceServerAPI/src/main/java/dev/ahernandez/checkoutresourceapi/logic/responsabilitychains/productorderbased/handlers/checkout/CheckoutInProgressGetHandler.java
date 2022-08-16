package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.handlers.checkout;

import dev.ahernandez.checkoutresourceapi.helpers.UuidGenerator;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.basehandler.AbstractProductOrderHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import dev.ahernandez.checkoutresourceapi.service.imp.CheckoutService;
import dev.ahernandez.checkoutresourceapi.service.imp.UserAddressService;
import dev.ahernandez.checkoutresourceapi.service.imp.UserPaymentService;
import dev.ahernandez.checkoutresourceapi.service.imp.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * <p>Gets InProgress Checkout by the userId provided by user authenticated.
 * If it doesnt exist, creates one.
 *
 * checkoutId is set it into productOrder object</p>
 */
@Component
@AllArgsConstructor
public class CheckoutInProgressGetHandler extends AbstractProductOrderHandler {

  private CheckoutService checkoutService;
  private UserAddressService userAddressService;
  private UserPaymentService userPaymentService;
  private UserService userService;
  /**
   * Checks if exist an InProgress Checkout by userId.
   * If it doesnt exist, creates one:
   * <ul>
   *     <li>Generates a random UUID</li>
   *     <li>Set userId from user authenticated</li>
   *     <li>Get a Address, if its found then will set into checkout</li>
   *     <li>Get a Payment, if its found then will set into checkout</li>
   *     <li>Set a 0.0 total amount</li>
   *     <li>Set InProgress status</li>
   * </ul>
   *
   * @param productOrder to be handle
   * @return next handler execution, with productOrder updated with checkoutId
   */

  @Override
  public ChainResponse handle(ProductOrder productOrder) {

    String userId =
            SecurityContextHolder.getContext().getAuthentication().getName();
    User user = new User();
    user.setUserId(userId);

    Checkout checkout;

    if (productOrder.getCheckout() == null) {

      checkout = checkoutService.getInProgressCheckoutByUserId(userId);

      if (checkout == null) {

        checkout = new Checkout();
        checkout.setCheckoutUuid(UuidGenerator.generate());

        User userInDb = userService.getUserById(userId);
        checkout.setUser(userInDb);

        UserAddress userAddress = userAddressService
                .getFirstEnabledAddressByUserId(userId);
        checkout.setUserAddress(userAddress);


        UserPayment userPayment = userPaymentService
                .getFirstEnabledPaymentMethod(userId);
        checkout.setUserPayment(userPayment);

        checkout.setTotalAmount(0.0);
        checkout.setCheckoutStatus("InProgress");

        checkout = checkoutService.saveCheckout(checkout);
      }

    } else {
      checkout = checkoutService
              .getCheckoutById(productOrder.getCheckout().getCheckoutUuid());

      if (checkout == null) {
        return new ChainResponse(false,
                "Checkout not found");
      }

      if (!checkout.getCheckoutStatus().equals("InProgress")) {
        return new ChainResponse(false,
                "Checkout not available to update");
      }

      if (checkout.getUser() == null) {

        checkout.setUser(user);
      }

    }

    productOrder.setCheckout(checkout);

    return handleNext(productOrder);
  }
}
