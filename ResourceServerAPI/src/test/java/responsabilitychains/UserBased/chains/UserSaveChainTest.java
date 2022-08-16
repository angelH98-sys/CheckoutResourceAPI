package responsabilitychains.UserBased.chains;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.chains.UserSaveChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.handlers.UserMailHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.handlers.UserPhoneHandler;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.handlers.UserSaveHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
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

@DisplayName("UserSaveChain Test")
class UserSaveChainTest {
    @Mock
    private UserMailHandler userMailHandler;
    @Mock
    private UserPhoneHandler userPhoneHandler;
    @Mock
    private UserSaveHandler userSaveHandler;
    @InjectMocks
    private UserSaveChain saveChain;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void successChain(){
        User user = new User();
        user.setUserId("");

        ChainResponse expected = new ChainResponse(true, user.getUserId());

        Mockito.when(userMailHandler.handle(any(User.class)))
                .thenReturn(expected);

        ChainResponse actual = saveChain.saveUser(user);


        assertThat(actual, samePropertyValuesAs(expected));
    }
}
