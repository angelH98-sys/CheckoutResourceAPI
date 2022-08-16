package dev.ahernandez.checkoutresourceapi.persistence.repository;

import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * User implementation for CrudRepository.
 */
public interface UserRepository extends CrudRepository<User, String> {

  Optional<User> findByMail(String mail);

  Optional<User> findByPhone(String mail);
}
