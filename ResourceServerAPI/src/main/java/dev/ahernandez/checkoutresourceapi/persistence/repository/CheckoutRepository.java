package dev.ahernandez.checkoutresourceapi.persistence.repository;

import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Checkout repository to define custom methods.
 */
@Repository
public interface CheckoutRepository extends CrudRepository<Checkout, String> {

  Optional<List<Checkout>> findByUser(User user);

  Optional<Checkout> findByCheckoutUuid(String checkoutUuid);
}
