package dev.ahernandez.checkoutresourceapi.persistence.repository;

import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * UserAddress implementation fo CrudRepository.
 */
@Repository
public interface UserAddressRepository
        extends CrudRepository<UserAddress, Integer> {

  Optional<List<UserAddress>> findByUserId(String userId);
}
