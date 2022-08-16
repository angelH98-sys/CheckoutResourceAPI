package dev.ahernandez.checkoutresourceapi.controllers.Checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutAddressUpdateChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutGetChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutPaymentUpdateChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutStatusUpdateChain;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.service.imp.CheckoutService;
import dev.ahernandez.checkoutresourceapi.web.controller.CheckoutController;
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

@DisplayName("GetAllCheckoutByUser Test")
class GetAllCheckoutByUserTest {
    @Mock
    private CheckoutAddressUpdateChain addressUpdateChain;
    @Mock
    private CheckoutPaymentUpdateChain paymentUpdateChain;
    @Mock
    private CheckoutGetChain checkoutGetChain;
    @Mock
    private CheckoutStatusUpdateChain checkoutStatusUpdateChain;
    @Mock
    private CheckoutService checkoutService;

    @InjectMocks
    private CheckoutController controller;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success api/checkout/get/all")
    void successEndpoint(){
        List<Checkout> checkoutList = new ArrayList<>();

        Mockito.when(checkoutService.getCheckoutByUserId(anyString()))
                .thenReturn(checkoutList);

        String userId = "";

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName())
                .thenReturn(userId);

        ResponseEntity actual = controller.getAllCheckoutByUser();

        ResponseEntity expected = ResponseEntity.ok(checkoutList);

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
