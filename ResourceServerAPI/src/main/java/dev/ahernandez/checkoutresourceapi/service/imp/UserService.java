package dev.ahernandez.checkoutresourceapi.service.imp;

import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import dev.ahernandez.checkoutresourceapi.persistence.repository.UserRepository;
import dev.ahernandez.checkoutresourceapi.service.UserServiceMethods;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Defines and implements methods to interact with User DB entity.
 */
@Service
@AllArgsConstructor
public class UserService implements UserServiceMethods {

  /**
   * Access to user DB methods.
   */
  private UserRepository userRepository;

  /**
   * Persist user in DB.
   *
   * @param user to be persisted
   * @return User
   */
  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  /**
   * Find a user by mail.
   *
   * @param mail to filter
   * @return User
   */
  @Override
  public User findUserByMail(String mail) {
    Optional<User> optional = userRepository.findByMail(mail);
    if (optional.isEmpty()) {
      return null;
    }
    return optional.get();
  }

  /**
   * Gets a User filtering by phone.
   *
   * @param phone to filter
   * @return User
   */
  @Override
  public User findUserByPhone(String phone) {
    Optional<User> optional = userRepository.findByPhone(phone);
    if (optional.isEmpty()) {
      return null;
    }
    return optional.get();
  }

  /**
   * Gets a user, filtering by userId.
   *
   * @param userId to filter
   * @return User
   */
  @Override
  public User getUserById(String userId) {
    User user = new User();
    user.setUserId(userId);

    Optional<User> optional = userRepository.findById(userId);
    if (optional.isEmpty()) {
      user = userRepository.save(user);
      return user;
    }

    return optional.get();
  }
}
