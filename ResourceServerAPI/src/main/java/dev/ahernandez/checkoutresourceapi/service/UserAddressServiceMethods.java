package dev.ahernandez.checkoutresourceapi.service;

import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import java.util.List;

/**
 * Interface to define all methods to
 * request/persit info in UserAddress entity.
 */
public interface UserAddressServiceMethods {

  UserAddress getFirstEnabledAddressByUserId(String userId);

  List<UserAddress> getAllAddressByUserId(String userId);

  UserAddress getAddressById(int userAddressId);

  UserAddress saveAddress(UserAddress userAddress);
}
