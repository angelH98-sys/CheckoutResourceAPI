package dev.ahernandez.checkoutresourceapi.controllers.UserPayment;

import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import dev.ahernandez.checkoutresourceapi.service.imp.UserPaymentService;
import dev.ahernandez.checkoutresourceapi.web.controller.UserPaymentController;
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
import static org.mockito.ArgumentMatchers.anyString;

@DisplayName("GetAllPaymentByUser Test")
class GetAllPaymentByUserTest {
    @Mock
    private UserPaymentService paymentService;

    @InjectMocks
    private UserPaymentController controller;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success /api/user/payment/get/all")
    void successEndPoint(){
        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        List<UserPayment> paymentList = new ArrayList<>();

        Mockito.when(paymentService.getAllPaymentByUserId(anyString()))
                .thenReturn(paymentList);

        ResponseEntity actual = controller.getAllPaymentByUser();
        ResponseEntity expected = ResponseEntity.ok(paymentList);

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
