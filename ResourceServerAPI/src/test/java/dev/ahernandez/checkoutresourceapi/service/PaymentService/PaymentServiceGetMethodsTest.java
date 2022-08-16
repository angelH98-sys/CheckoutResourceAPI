package dev.ahernandez.checkoutresourceapi.service.PaymentService;

import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import dev.ahernandez.checkoutresourceapi.persistence.repository.UserPaymentRepository;
import dev.ahernandez.checkoutresourceapi.service.imp.UserPaymentService;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@DisplayName("PaymentService Get Methods Test")
class PaymentServiceGetMethodsTest {
    @Mock
    private UserPaymentRepository paymentRepository;
    @InjectMocks
    private UserPaymentService paymentService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getFirstEnabledPaymentMethod UserPayment return")
    void successGetFirstPayment(){
        List<UserPayment> list = new ArrayList<>();
        UserPayment payment = new UserPayment();
        payment.setPaymentId(1);
        payment.setPaymentName("");
        payment.setUserId("");
        payment.setOwnerName("");
        payment.setCardNumber("");
        payment.setExpiration("");
        payment.setCvv("");
        payment.setEnabled(true);
        list.add(payment);

        Mockito.when(paymentRepository.findByUserId(anyString())).thenReturn(Optional.of(list));

        UserPayment result = paymentService.getFirstEnabledPaymentMethod("");

        assertEquals(payment, result);
    }

    @Test
    @DisplayName("getFirstEnabledPaymentMethod empty return")
    void emptyGetFirstPayment(){

        Mockito.when(paymentRepository.findByUserId(anyString())).thenReturn(Optional.empty());

        UserPayment result = paymentService.getFirstEnabledPaymentMethod("");

        assertEquals(null, result);
    }

    @Test
    @DisplayName("getFirstEnabledPaymentMethod empty user return")
    void emptyUserGetFirstPayment(){
        List<UserPayment> list = new ArrayList<>();
        UserPayment payment = new UserPayment();
        payment.setEnabled(false);
        list.add(payment);

        Mockito.when(paymentRepository.findByUserId(anyString())).thenReturn(Optional.of(list));

        UserPayment result = paymentService.getFirstEnabledPaymentMethod("");

        assertEquals(null, result);
    }

    @Test
    @DisplayName("getPaymentById UserPayment return")
    void successGetPaymentById(){
        UserPayment payment = new UserPayment();
        payment.setPaymentId(1);
        payment.setPaymentName("");
        payment.setUserId("");
        payment.setOwnerName("");
        payment.setCardNumber("");
        payment.setExpiration("");
        payment.setCvv("");
        payment.setEnabled(true);

        Mockito.when(paymentRepository.findById(anyInt())).thenReturn(Optional.of(payment));

        UserPayment result = paymentService.getPaymentById(0);

        assertEquals(payment, result);
    }

    @Test
    @DisplayName("getPaymentById empty return")
    void emptyGetPaymentById(){

        Mockito.when(paymentRepository.findById(anyInt())).thenReturn(Optional.empty());

        UserPayment result = paymentService.getPaymentById(0);

        assertEquals(null, result);
    }

    @Test
    @DisplayName("getAllPaymentByUserId list return")
    void successGetAllPaymentByUserId(){
        List<UserPayment> list = new ArrayList<>();
        list.add(new UserPayment());

        Mockito.when(paymentRepository.findByUserId(anyString()))
                .thenReturn(Optional.of(list));

        List<UserPayment> result = paymentService.getAllPaymentByUserId("");

        assertEquals(list, result);
    }

    @Test
    @DisplayName("getAllPaymentByUserId empty return")
    void emptyGetAllPaymentByUserId(){

        Mockito.when(paymentRepository.findByUserId(anyString()))
                .thenReturn(Optional.empty());

        List<UserPayment> result = paymentService.getAllPaymentByUserId("");

        assertEquals(null, result);
    }

    @Test
    @DisplayName("getAllPaymentByUserId empty list return")
    void emptyListGetAllPaymentByUserId(){

        Mockito.when(paymentRepository.findByUserId(anyString()))
                .thenReturn(Optional.of(new ArrayList<>()));

        List<UserPayment> result = paymentService.getAllPaymentByUserId("");

        assertEquals(null, result);
    }

}
