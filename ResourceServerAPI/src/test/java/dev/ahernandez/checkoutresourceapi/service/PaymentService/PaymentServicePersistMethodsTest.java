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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("PaymentService Persist Methods Test")
class PaymentServicePersistMethodsTest {
    @Mock
    private UserPaymentRepository paymentRepository;
    @InjectMocks
    private UserPaymentService paymentService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void successSavePayment(){
        UserPayment userPayment = new UserPayment();
        userPayment.setPaymentId(1);
        userPayment.setEnabled(true);
        userPayment.setUserId("");
        userPayment.setOwnerName("");
        userPayment.setCardNumber("4321432143214321");
        userPayment.setExpiration("03/24");
        userPayment.setCvv("123");
        userPayment.setPaymentName("");

        Mockito.when(paymentRepository.save(any(UserPayment.class)))
                .thenReturn(userPayment);

        UserPayment result = paymentService.savePayment(userPayment);

        assertEquals(userPayment, result);
    }
}
