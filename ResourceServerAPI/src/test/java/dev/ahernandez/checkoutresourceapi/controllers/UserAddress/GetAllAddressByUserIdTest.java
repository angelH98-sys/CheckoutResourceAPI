package dev.ahernandez.checkoutresourceapi.controllers.UserAddress;

import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import dev.ahernandez.checkoutresourceapi.service.imp.UserAddressService;
import dev.ahernandez.checkoutresourceapi.web.controller.UserAddressController;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

@DisplayName("GetAllAddressByUserId Test")
class GetAllAddressByUserIdTest {
    @Mock
    private UserAddressService addressService;

    @InjectMocks
    private UserAddressController controller;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success api/user/address/get")
    void successEndPoint(){
        List<UserAddress> addressList = new ArrayList<>();

        Mockito.when(addressService.getAllAddressByUserId(anyString()))
                .thenReturn(addressList);

        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        ResponseEntity actual = controller.getAllAddressByUserId();
        ResponseEntity expected = ResponseEntity.ok(addressList);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail api/user/address/get")
    void failEndPoint(){

        Mockito.when(addressService.getAllAddressByUserId(anyString()))
                .thenReturn(null);

        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        ResponseEntity actual = controller.getAllAddressByUserId();

        assertEquals(200, actual.getStatusCodeValue());
    }
}
