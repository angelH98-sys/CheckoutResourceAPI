package responsabilitychains.UserBased.handlers;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.handlers.UserPhoneHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import dev.ahernandez.checkoutresourceapi.service.imp.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.anyString;

@DisplayName("UserPhoneHandler Test")
class UserPhoneHandlerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserPhoneHandler phoneHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success UserPhoneHandler")
    void successHandler(){
        User user = new User();
        user.setUserId("");
        user.setPhone("");

        Mockito.when(userService.findUserByPhone(anyString()))
                .thenReturn(user);

        ChainResponse actual = phoneHandler.handle(user);

        ChainResponse expected = new ChainResponse(true, user.getUserId());

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail UserPhoneHandler")
    void failHandler(){
        User user = new User();
        user.setUserId("");
        user.setPhone("");

        Mockito.when(userService.findUserByPhone(anyString()))
                .thenReturn(user);

        User user2 = new User();
        user2.setUserId("diff");
        user2.setPhone("");

        ChainResponse actual = phoneHandler.handle(user2);

        ChainResponse expected = new ChainResponse(false, "Phone not available", null);

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
