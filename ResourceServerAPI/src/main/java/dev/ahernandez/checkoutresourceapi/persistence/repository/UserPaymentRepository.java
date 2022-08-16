package dev.ahernandez.checkoutresourceapi.persistence.repository;

import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * UserPayment implementation of CrudRepository.
 */
@Repository
public interface UserPaymentRepository
        extends CrudRepository<UserPayment, Integer> {

  Optional<List<UserPayment>> findByUserId(String userId);
}
