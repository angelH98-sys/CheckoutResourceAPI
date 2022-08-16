package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.handlers;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.basehandler.AbstractUserHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import dev.ahernandez.checkoutresourceapi.service.imp.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Validates if phone from user parameter is available to be persisted in DB.
 */
@Component
@AllArgsConstructor
public class UserPhoneHandler extends AbstractUserHandler {

  /**
   * Access to user DB methods.
   */
  private UserService userService;

  /**
   * Gets a user with phone, if its founded returns a
   * ChainResponse not succeeded with an error message.
   *
   * @param user to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(User user) {
    User userInDb = userService.findUserByPhone(user.getPhone());

    if (userInDb != null && !userInDb.getUserId().equals(user.getUserId())) {
      return new ChainResponse(false, "Phone not available");
    }

    return handleNext(user);
  }
}
