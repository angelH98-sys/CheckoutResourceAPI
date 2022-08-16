package dev.ahernandez.checkoutresourceapi.controllers.UserAddress;

import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import dev.ahernandez.checkoutresourceapi.service.imp.UserAddressService;
import dev.ahernandez.checkoutresourceapi.web.controller.UserAddressController;
import dev.ahernandez.checkoutresourceapi.web.dto.useraddress.UserAddressDto;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("CreateAddressByUser Test")
class CreateAddressByUserTest {
    @Mock
    private UserAddressService addressService;

    @InjectMocks
    private UserAddressController controller;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success api/user/address/create")
    void successEndPoint(){
        UserAddressDto dto = new UserAddressDto();

        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        UserAddress address = new UserAddress();

        Mockito.when(addressService.saveAddress(any()))
                .thenReturn(address);

        ResponseEntity actual = controller.createAddressByUser(dto);
        ResponseEntity expected = ResponseEntity.ok(address);

        assertEquals(actual, expected);
    }
}
