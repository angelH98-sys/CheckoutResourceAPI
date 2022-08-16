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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@DisplayName("UserService Get Methods Test")
class UserServiceGetMethodsTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("successFindUserByMail user return")
    void successFindUserByMail(){
        User user = new User();
        Mockito.when(userRepository.findByMail(anyString())).thenReturn(Optional.of(user));

        User result = userService.findUserByMail("");

        assertEquals(user,result);
    }

    @Test
    @DisplayName("successFindUserByMail empty return")
    void emptyFindUserByMail(){
        Mockito.when(userRepository.findByMail(anyString())).thenReturn(Optional.empty());

        User result = userService.findUserByMail("");

        assertEquals(null,result);
    }

    @Test
    @DisplayName("successFindUserByPhone user return")
    void successFindUserByPhone(){
        User user = new User();
        Mockito.when(userRepository.findByPhone(anyString())).thenReturn(Optional.of(user));

        User result = userService.findUserByPhone("");

        assertEquals(user,result);
    }

    @Test
    @DisplayName("successFindUserByPhone empty return")
    void emptyFindUserByPhone(){
        Mockito.when(userRepository.findByPhone(anyString())).thenReturn(Optional.empty());

        User result = userService.findUserByPhone("");

        assertEquals(null,result);
    }

    @Test
    @DisplayName("No empty user getUserByIdgetUserById")
    void notEmptyGetUserById(){
        User user = new User();

        Mockito.when(userRepository.findById(anyString()))
                .thenReturn(Optional.of(user));

        User actual = userService.getUserById("");

        assertEquals(user, actual);
    }

    @Test
    @DisplayName("Empty getUserById")
    void emptyGetUserById(){
        User user = new User();

        Mockito.when(userRepository.findById(anyString()))
                .thenReturn(Optional.of(user));

        User actual = userService.getUserById("");

        assertEquals(user, actual);
    }
}
