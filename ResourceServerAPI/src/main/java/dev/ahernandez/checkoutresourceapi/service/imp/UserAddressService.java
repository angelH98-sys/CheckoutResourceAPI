package dev.ahernandez.checkoutresourceapi.service.imp;

import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import dev.ahernandez.checkoutresourceapi.persistence.repository.UserAddressRepository;
import dev.ahernandez.checkoutresourceapi.service.UserAddressServiceMethods;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Defines and implements methods to interact with UserAddress DB entity.
 */
@Service
@AllArgsConstructor
public class UserAddressService implements UserAddressServiceMethods {

  /**
   * Access to userAddress DB methods.
   */
  private UserAddressRepository addressRespository;

  /**
   * Gets a UserAddress, filter by userId and enabled status.
   *
   * @param userId to filter
   * @return UserAddress
   */
  @Override
  public UserAddress getFirstEnabledAddressByUserId(String userId) {
    Optional<List<UserAddress>> optional = addressRespository
            .findByUserId(userId);

    if (optional.isEmpty() || optional.get().isEmpty()) {
      return null;
    }

    List<UserAddress> addresses = optional.get();
    Optional<UserAddress> address =
            addresses.stream().filter(UserAddress::isEnabled).findFirst();

    if (address.isEmpty()) {
      return null;
    }

    return address.get();
  }

  /**
   * Gets all addresses with userId.
   *
   * @param userId to filter
   * @return list of UserAddress
   */
  @Override
  public List<UserAddress> getAllAddressByUserId(String userId) {
    Optional<List<UserAddress>> optional = addressRespository
            .findByUserId(userId);

    if (optional.isEmpty() || optional.get().isEmpty()) {
      return null;
    }

    return optional.get();
  }

  /**
   * Gets an address by addressId.
   *
   * @param userAddressId to filter
   * @return UserAddress
   */
  @Override
  public UserAddress getAddressById(int userAddressId) {
    Optional<UserAddress> optional =
            addressRespository.findById(userAddressId);
    if (optional.isEmpty()) {
      return null;
    }
    return optional.get();
  }

  /**
   * Persist a UserAddress in DB.
   *
   * @param userAddress to persist
   * @return UserAddress
   */
  @Override
  public UserAddress saveAddress(UserAddress userAddress) {
    return addressRespository.save(userAddress);
  }

}
