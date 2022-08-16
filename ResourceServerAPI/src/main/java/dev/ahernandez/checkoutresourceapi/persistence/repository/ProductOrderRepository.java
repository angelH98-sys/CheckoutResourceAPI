package dev.ahernandez.checkoutresourceapi.persistence.repository;

import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductOrder implementation of CrudRepository.
 */
@Repository
public interface ProductOrderRepository extends CrudRepository<ProductOrder, Integer> {
  Optional<List<ProductOrder>> findByCheckout(Checkout checkout);
}
