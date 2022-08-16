package responsabilitychains.UserBased.handlers;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.handlers.UserMailHandler;
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

@DisplayName("UserMailHandler Test")
class UserMailHandlerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserMailHandler handler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success UserMailHandler")
    void successHandler(){
        User user = new User();
        user.setUserId("");
        user.setMail("");

        Mockito.when(userService.findUserByMail(anyString()))
                .thenReturn(user);

        ChainResponse actual = handler.handle(user);

        ChainResponse expected = new ChainResponse(true, user.getUserId());

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail UserMailHandler")
    void failHandler(){
        User user = new User();
        user.setUserId("");
        user.setMail("");

        Mockito.when(userService.findUserByMail(anyString()))
                .thenReturn(user);

        User user2 = new User();
        user2.setUserId("diff");
        user2.setMail("");

        ChainResponse actual = handler.handle(user2);

        ChainResponse expected = new ChainResponse(false, "Mail not available");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
