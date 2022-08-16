package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains;

/**
 * Defines Chain handler methods.
 *
 * @param <T> specification of the BaseHandler class
 */
public interface BaseHandler<T> {
  /**
   * Set next handler.
   *
   * @param handler to be set next
   */
  void setNext(BaseHandler<T> handler);

  /**
   * handle current Handler.
   *
   * @param t object to be handle
   * @return ChainResponse
   */
  ChainResponse handle(T t);

  /**
   * handle nextHandler.
   *
   * @param t object to share with next handler
   * @return ChainResponse
   */
  ChainResponse handleNext(T t);
}
