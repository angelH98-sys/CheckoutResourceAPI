package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.useraddress;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.basehandler.AbstractCheckoutHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import dev.ahernandez.checkoutresourceapi.service.imp.UserAddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Gets UserAddress from DB filter by addressID provided in checkout parameter.
 */
@Component
@AllArgsConstructor
public class UserAddressGetHandler extends AbstractCheckoutHandler {

  /**
   * Access to UserAddress DB methods.
   */
  private UserAddressService userAddressService;

  /**
   * <p>
   * Use userAddressService to find an UserAddress
   * object filter by addressId from checkout parameter.
   * Once found it, checkout parameter will include this address object.
   * </p>
   *
   * @param checkout to be handle
   * @return executes nextHandler
   */
  @Override
  public ChainResponse handle(Checkout checkout) {
    UserAddress address = userAddressService
            .getAddressById(checkout.getUserAddress().getAddressId());

    if (address == null) {
      return new ChainResponse(false, "Address not found");
    }

    if (!address.getUserId().equals(checkout.getUser().getUserId())) {
      return new ChainResponse(false, "Address not belongs to user authenticated");
    }

    checkout.setUserAddress(address);

    return handleNext(checkout);
  }
}
