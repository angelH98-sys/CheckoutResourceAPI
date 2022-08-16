package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.handlers;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.basehandler.AbstractUserHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import dev.ahernandez.checkoutresourceapi.service.imp.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Persist a user in DB.
 */
@Component
@AllArgsConstructor
public class UserSaveHandler extends AbstractUserHandler {

  /**
   * Access to User DB methods.
   */
  private UserService userService;

  /**
   * Executes save method to persist user in DB.
   *
   * @param user to be handle
   * @return ChainResponse
   */
  @Override
  public ChainResponse handle(User user) {
    user = userService.saveUser(user);
    return handleNext(user);
  }
}
