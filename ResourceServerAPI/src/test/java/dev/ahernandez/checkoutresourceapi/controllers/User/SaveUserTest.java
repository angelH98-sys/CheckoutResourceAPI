package dev.ahernandez.checkoutresourceapi.controllers.User;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.chains.UserSaveChain;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import dev.ahernandez.checkoutresourceapi.web.controller.UserController;
import dev.ahernandez.checkoutresourceapi.web.dto.user.UserSaveDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("SaveUser Test")
class SaveUserTest {

    @Mock
    private UserSaveChain userSaveChain;

    @InjectMocks
    private UserController controller;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success /api/user/save")
    void successEndPoint(){
        UserSaveDto dto = new UserSaveDto("","", "", "");

        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        Mockito.when(userSaveChain.saveUser(any(User.class)))
                .thenReturn(new ChainResponse(true, ""));

        ResponseEntity actual = controller.saveUser(dto);
        ResponseEntity expected = ResponseEntity.ok("");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
