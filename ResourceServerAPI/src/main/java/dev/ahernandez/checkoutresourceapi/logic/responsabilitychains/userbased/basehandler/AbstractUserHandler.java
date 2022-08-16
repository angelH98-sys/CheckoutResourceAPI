package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.basehandler;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.BaseHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;

/**
 * Abstract class that set default behaviors to BaseHandler methods,
 * based on User.
 */
public abstract class AbstractUserHandler implements BaseHandler<User> {

  /**
   * Stores next handler.
   */
  private BaseHandler<User> handler;

  /**
   * Set next handler.
   *
   * @param baseHandler handler that proceeds the current one
   */
  @Override
  public void setNext(final BaseHandler<User> baseHandler) {
    this.handler = baseHandler;
  }

  /**
   * Executes next handler handle method.
   *
   * @param user user to be managed with handler
   * @return ChainResponse with the result of the handler/chain
   */
  @Override
  public ChainResponse handleNext(final User user) {
    if (handler == null) {
      return new ChainResponse(true, user.getUserId());
    } else {
      return handler.handle(user);
    }
  }
}
