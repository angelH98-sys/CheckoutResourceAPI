package dev.ahernandez.checkoutresourceapi.service.AddressService;

import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import dev.ahernandez.checkoutresourceapi.persistence.repository.UserAddressRepository;
import dev.ahernandez.checkoutresourceapi.service.imp.UserAddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("AddressService Persist Methods Test")
class AddressServicePersistMethodsTest {
    @Mock
    private UserAddressRepository addressRepository;
    @InjectMocks
    private UserAddressService addressService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void successSaveAddress(){
        UserAddress address = new UserAddress();
        Mockito.when(addressRepository.save(any(UserAddress.class))).thenReturn(address);

        UserAddress result = addressService.saveAddress(address);

        assertEquals(address, result);
    }
}
