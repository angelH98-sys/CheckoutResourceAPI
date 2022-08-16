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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

@DisplayName("AddressService Get Methods Test")
class AddressServiceGetMethodsTest {
    @Mock
    private UserAddressRepository addressRepository;
    @InjectMocks
    private UserAddressService addressService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getFirstEnabledAddressByUserId UserAddress return")
    void successGetEnabledAddressByUser(){
        List<UserAddress> list = new ArrayList<>();
        UserAddress address = new UserAddress();
        address.setEnabled(true);
        list.add(address);

        Mockito.when(addressRepository.findByUserId(anyString())).thenReturn(Optional.of(list));

        UserAddress result = addressService.getFirstEnabledAddressByUserId("");

        assertEquals(address, result);
    }

    @Test
    @DisplayName("getFirstEnabledAddressByUserId empty return")
    void emptyGetEnabledAddressByUser(){
        Mockito.when(addressRepository.findByUserId(anyString())).thenReturn(Optional.empty());

        UserAddress result = addressService.getFirstEnabledAddressByUserId("");

        assertEquals(null, result);
    }

    @Test
    @DisplayName("getFirstEnabledAddressByUserId user empty return")
    void emptyUserGetEnabledAddressByUser(){
        List<UserAddress> list = new ArrayList<>();
        UserAddress address = new UserAddress();
        address.setEnabled(false);
        list.add(address);

        Mockito.when(addressRepository.findByUserId(anyString())).thenReturn(Optional.of(list));

        UserAddress result = addressService.getFirstEnabledAddressByUserId("");

        assertEquals(null, result);
    }

    @Test
    @DisplayName("getAllAddressByUserId list return")
    void successGetAllAddressByUserId(){
        List<UserAddress> list = new ArrayList<>();
        list.add(new UserAddress());

        Mockito.when(addressRepository.findByUserId(anyString())).thenReturn(Optional.of(list));

        List<UserAddress> result = addressService.getAllAddressByUserId("");

        assertEquals(list, result);
    }

    @Test
    @DisplayName("getAllAddressByUserId empty return")
    void emptyGetAllAddressByUserId(){

        Mockito.when(addressRepository.findByUserId(anyString())).thenReturn(Optional.empty());

        List<UserAddress> result = addressService.getAllAddressByUserId("");

        assertEquals(null, result);
    }

    @Test
    @DisplayName("getAllAddressByUserId empty list return")
    void emptyListGetAllAddressByUserId(){

        Mockito.when(addressRepository.findByUserId(anyString())).thenReturn(Optional.of(new ArrayList<>()));

        List<UserAddress> result = addressService.getAllAddressByUserId("");

        assertEquals(null, result);
    }

    @Test
    @DisplayName("getAddressById UserAddress return")
    void successGetAddressById(){

        UserAddress address = new UserAddress();

        Mockito.when(addressRepository.findById(anyInt())).thenReturn(Optional.of(address));

        UserAddress result = addressService.getAddressById(0);

        assertEquals(address, result);
    }

    @Test
    @DisplayName("getAddressById empty return")
    void emptyGetAddressById(){

        Mockito.when(addressRepository.findById(anyInt())).thenReturn(Optional.empty());

        UserAddress result = addressService.getAddressById(0);

        assertEquals(null, result);
    }
}
