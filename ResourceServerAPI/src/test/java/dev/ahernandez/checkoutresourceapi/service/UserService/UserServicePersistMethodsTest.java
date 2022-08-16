package dev.ahernandez.checkoutresourceapi.service.UserService;

import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import dev.ahernandez.checkoutresourceapi.persistence.repository.UserRepository;
import dev.ahernandez.checkoutresourceapi.service.imp.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("UserService Persist Methods Test")
class UserServicePersistMethodsTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("saveUser user return")
    void successSaveUser(){
        User user = new User();
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.saveUser(user);

        assertEquals(user, result);
    }
}
