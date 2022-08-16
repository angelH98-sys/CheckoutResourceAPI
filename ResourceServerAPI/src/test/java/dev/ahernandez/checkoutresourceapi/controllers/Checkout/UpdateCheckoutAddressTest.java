package dev.ahernandez.checkoutresourceapi.controllers.Checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutAddressUpdateChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutGetChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutPaymentUpdateChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutStatusUpdateChain;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.service.imp.CheckoutService;
import dev.ahernandez.checkoutresourceapi.web.controller.CheckoutController;
import dev.ahernandez.checkoutresourceapi.web.dto.checkout.CheckoutAddressUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("UpdateCheckoutAddress Test")
class UpdateCheckoutAddressTest {
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
    @DisplayName("Success api/checkout/update/address")
    void successEndpoint(){
        CheckoutAddressUpdateDto dto = new CheckoutAddressUpdateDto("", 1);

        Mockito.when(addressUpdateChain.updateCheckoutAddress(any(Checkout.class)))
                .thenReturn(new ChainResponse(true, ""));

        ResponseEntity actual = controller.updateCheckoutAddress(dto);

        ResponseEntity expected = ResponseEntity.ok("");

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail api/checkout/update/address")
    void failEndpoint(){
        CheckoutAddressUpdateDto dto = new CheckoutAddressUpdateDto("", 1);

        Mockito.when(addressUpdateChain.updateCheckoutAddress(any(Checkout.class)))
                .thenReturn(new ChainResponse(false, ""));

        ResponseEntity actual = controller.updateCheckoutAddress(dto);

        ResponseEntity expected = ResponseEntity.badRequest().body("");

        assertThat(actual, samePropertyValuesAs(expected));
    }

}
