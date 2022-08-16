package responsabilitychains.UserBased.handlers;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.handlers.UserSaveHandler;
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
import static org.mockito.ArgumentMatchers.any;

@DisplayName("UserSaveHandler Test")
class UserSaveHandlerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserSaveHandler saveHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success UserSaveHandler")
    void successHandler(){
        User user = new User();
        user.setUserId("");

        Mockito.when(userService.saveUser(any(User.class)))
                .thenReturn(user);

        ChainResponse actual = saveHandler.handle(user);

        ChainResponse expected = new ChainResponse(true, user.getUserId());

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
