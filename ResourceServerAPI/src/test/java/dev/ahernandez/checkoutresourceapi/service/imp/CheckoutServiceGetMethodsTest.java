package dev.ahernandez.checkoutresourceapi.service.imp;

import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import dev.ahernandez.checkoutresourceapi.persistence.repository.CheckoutRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@DisplayName("CheckoutService Get Methods Test")
class CheckoutServiceGetMethodsTest {

    @Mock
    private CheckoutRepository checkoutRepository;
    @InjectMocks
    private CheckoutService checkoutService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getInProgressCheckoutByUserId empty return")
    void emptyFindByUser() {

        Mockito.when(checkoutRepository.findByUser(any(User.class)))
                .thenReturn(Optional.empty());

        Checkout checkout = checkoutService.getInProgressCheckoutByUserId(anyString());

        assertEquals(null, checkout);
    }

    @Test
    @DisplayName("getInProgressCheckoutByUserId empty checkout return")
    void emptyCheckoutFindByUser(){

        List<Checkout> list = new ArrayList<>();
        Checkout checkout = new Checkout();
        checkout.setCheckoutStatus("Completed");

        list.add(checkout);

        Mockito.when(checkoutRepository.findByUser(any(User.class)))
                .thenReturn(Optional.of(list));

        Checkout actual = checkoutService.getInProgressCheckoutByUserId(anyString());

        assertEquals(null, actual);
    }

    @Test
    @DisplayName("getInProgressCheckoutByUserId checkout return")
    void successFindByUser(){

        List<Checkout> list = new ArrayList<>();
        Checkout checkout = new Checkout();
        checkout.setCheckoutStatus("InProgress");

        list.add(checkout);

        Mockito.when(checkoutRepository.findByUser(any(User.class)))
                .thenReturn(Optional.of(list));

        Checkout actual = checkoutService.getInProgressCheckoutByUserId(anyString());

        assertEquals(checkout, actual);
    }

    @Test
    @DisplayName("getCheckoutById empty return")
    void emptyFindById(){

        Mockito.when(checkoutRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        Checkout result = checkoutService.getCheckoutById(anyString());

        assertEquals(null, result);
    }

    @Test
    @DisplayName("getCheckoutById checkout return")
    void successFindById(){

        Checkout checkout = new Checkout();

        Mockito.when(checkoutRepository.findByCheckoutUuid(anyString()))
                .thenReturn(Optional.of(checkout));

        Checkout result = checkoutService.getCheckoutById(anyString());

        assertEquals(checkout, result);
    }

    @Test
    @DisplayName("getByCheckoutIdAndUserId empty return")
    void emptyFindByCheckoutAndUserWithUser(){

        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("checkoutId");
        checkout.setUser(new User());

        Mockito.when(checkoutRepository.findByUser(any(User.class)))
                .thenReturn(Optional.empty());

        Checkout result = checkoutService.getByCheckoutIdAndUserId("checkoutId", "userId");

        assertEquals(null, result);
    }

    @Test
    @DisplayName("getByCheckoutIdAndUserId checkout (with user) return")
    void successFindByCheckoutAndUserWithUser(){
        Checkout checkout = new Checkout();
        checkout.setCheckoutUuid("checkoutId");
        User user = new User();
        user.setUserId("userId");
        checkout.setUser(user);

        Mockito.when(checkoutRepository.findByCheckoutUuid(anyString()))
                .thenReturn(Optional.of(checkout));

        Checkout result = checkoutService.getByCheckoutIdAndUserId("checkoutId", "userId");

        assertEquals(checkout, result);
    }

    @Test
    @DisplayName("Success getCheckoutByUserId")
    void successGetCheckoutByUser(){
        List<Checkout> expected = new ArrayList<>();
        expected.add(new Checkout());

        Mockito.when(checkoutRepository.findByUser(any(User.class)))
                .thenReturn((Optional<List<Checkout>>) Optional.of(expected));

        List<Checkout> actual = checkoutService.getCheckoutByUserId("");

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Empty getCheckoutByUserId")
    void emptyGetCheckoutByUser(){
        List<Checkout> expected = new ArrayList<>();

        Mockito.when(checkoutRepository.findByUser(any(User.class)))
                .thenReturn(Optional.empty());

        List<Checkout> actual = checkoutService.getCheckoutByUserId("");

        assertEquals(null,actual);
    }

}