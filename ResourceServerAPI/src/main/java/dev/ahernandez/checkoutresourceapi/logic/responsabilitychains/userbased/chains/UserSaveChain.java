package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.chains;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.basehandler.AbstractUserHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.handlers.UserMailHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.handlers.UserPhoneHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.handlers.UserSaveHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Defines responsability chain to save user data in DB.
 */
@Component
@AllArgsConstructor
public class UserSaveChain {

  /**
   * Validates if an email is available.
   */
  private UserMailHandler userMailHandler;
  /**
   * Validates if a phone is available.
   */
  private UserPhoneHandler userPhoneHandler;
  /**
   * Persist user in DB.
   */
  private UserSaveHandler userSaveHandler;

  /**
   * Executes saveUser responsabilityHandler.
   *
   * @param user to persist
   * @return ChainResponse
   */
  public ChainResponse saveUser(User user) {

    final AbstractUserHandler handler1 = userMailHandler;
    final AbstractUserHandler handler2 = userPhoneHandler;
    final AbstractUserHandler handler3 = userSaveHandler;

    handler3.setNext(null);
    handler2.setNext(handler3);
    handler1.setNext(handler2);

    return handler1.handle(user);
  }
}
