package dev.ahernandez.checkoutresourceapi.logic.responsabilitychains;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Custom object dto to comunicate between handler and client.
 */
@Data
@AllArgsConstructor
public class ChainResponse {

  /**
   * Stores if a handler succeed or not.
   */
  private boolean itSucceed;
  /**
   * Stores an optional message.
   */
  private String message;
  /**
   * Stores an object relevant to handler/chain.
   */
  private Object object;

  /**
   * Default chain response.
   */
  public ChainResponse() {
    itSucceed = true;
  }

  /**
   * Chain response constructor.
   *
   * @param itSucceed to set if itSuceed
   * @param message to set a custom message
   */
  public ChainResponse(final boolean itSucceed, final String message) {
    this.itSucceed = itSucceed;
    this.message = message;
  }

  /**
   * Validates of an object is a ChainResponse and if has
   * same attributes.
   *
   * @param obj object to be compared
   * @return boolean with method result
   */
  @Override
  public boolean equals(final Object obj) {

    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }

    ChainResponse response = (ChainResponse) obj;
    if (response.itSucceed !=  itSucceed) {
      return false;
    }
    if (!response.message.equals(message)) {
      return false;
    }

    boolean areEquals = object != null;
    areEquals = areEquals && response.object != null;
    areEquals = areEquals && !response.object.equals(object);

    if (areEquals) {
      return false;
    }

    return true;

  }

  /**
   * Default hashCode implementation.
   *
   * @return int
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
