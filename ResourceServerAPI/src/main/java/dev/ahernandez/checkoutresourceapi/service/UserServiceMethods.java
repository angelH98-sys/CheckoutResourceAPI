package dev.ahernandez.checkoutresourceapi.service;

import dev.ahernandez.checkoutresourceapi.persistence.model.User;

/**
 * Interface to define all methods to
 * request/persit info in User entity.
 */
public interface UserServiceMethods {

  User saveUser(User user);

  User findUserByMail(String mail);

  User findUserByPhone(String phone);

  User getUserById(String userId);
}
