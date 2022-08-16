package dev.ahernandez.checkoutresourceapi.service.imp;

import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import dev.ahernandez.checkoutresourceapi.persistence.repository.CheckoutRepository;
import dev.ahernandez.checkoutresourceapi.service.CheckoutServiceMethods;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Defines and implements methods to interact with Checkout DB entity.
 */
@Service
@AllArgsConstructor
public class CheckoutService implements CheckoutServiceMethods {

  /**
   * Access to Checkout DB methods.
   */
  private CheckoutRepository checkoutRepository;

  /**
   * <p>
   *   Gets checkout filter by.
   *    <ul>
   *        <li>userId</li>
   *        <li>inProgress state</li>
   *    </ul>
   * </p>
   *
   * @param userId to execute filter
   * @return Checkout
   */
  @Override
  public Checkout getInProgressCheckoutByUserId(String userId) {

    User user = new User();
    user.setUserId(userId);

    Optional<List<Checkout>> optional = checkoutRepository.findByUser(user);

    if (optional.isEmpty() || optional.get().isEmpty()) {
      return null;
    }

    Optional<Checkout> checkout = optional.get().stream()
        .filter(c -> c.getCheckoutStatus().equals("InProgress")).findFirst();

    if (checkout.isEmpty()) {
      return null;
    }

    return checkout.get();
  }

  /**
   * Get a checkout, filter by id.
   *
   * @param checkoutId to filter
   * @return Checkout
   */
  @Override
  public Checkout getCheckoutById(String checkoutId) {
    Optional<Checkout> optional = checkoutRepository
            .findByCheckoutUuid(checkoutId);

    if (optional.isEmpty()) {
      return null;
    }
    return optional.get();
  }

  /**
   * Persist a checkout in Db.
   *
   * @param checkout to save
   * @return Checkout
   */
  @Override
  public Checkout saveCheckout(Checkout checkout) {
    return checkoutRepository.save(checkout);
  }

  /**
   * Removes a checkout from db.
   *
   * @param checkoutUuid to delete
   */
  @Override
  public void deleteCheckoutByCheckoutUuid(String checkoutUuid) {
    checkoutRepository.deleteById(checkoutUuid);
  }

  /**
   * Gets a Checkout filtering by checkoutId and userId.
   *
   * @param checkoutId to filter
   * @param userId to filter
   * @return Checkout
   */
  @Override
  public Checkout getByCheckoutIdAndUserId(String checkoutId, String userId) {

    Optional<Checkout> optional = checkoutRepository
            .findByCheckoutUuid(checkoutId)
            .filter(c -> c.getUser().getUserId().equals(userId));

    if (optional.isEmpty()) {
      return null;
    }

    return optional.get();
  }

  /**
   * Gets a list of checkouts filter by userId.
   *
   * @param userId to filter
   * @return list of checkout
   */
  @Override
  public List<Checkout> getCheckoutByUserId(String userId) {
    User user = new User();
    user.setUserId(userId);
    Optional<List<Checkout>> optional = checkoutRepository.findByUser(user);

    if (optional.isEmpty() || optional.get().isEmpty()) {
      return null;
    }
    return optional.get();
  }

}
