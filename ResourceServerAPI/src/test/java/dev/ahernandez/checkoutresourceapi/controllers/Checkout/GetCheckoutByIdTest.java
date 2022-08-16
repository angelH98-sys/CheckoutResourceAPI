package dev.ahernandez.checkoutresourceapi.controllers.Checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutAddressUpdateChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutGetChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutPaymentUpdateChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutStatusUpdateChain;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.service.imp.CheckoutService;
import dev.ahernandez.checkoutresourceapi.web.controller.CheckoutController;
import dev.ahernandez.checkoutresourceapi.web.dto.checkout.CheckoutGetDto;
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

@DisplayName("GetCheckoutById Test")
class GetCheckoutByIdTest {

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
    @DisplayName("Success api/checkout/get")
    void successEndpoint(){
        CheckoutGetDto dto = new CheckoutGetDto("");
        Checkout checkout = new Checkout();

        Mockito.when(checkoutGetChain.getCheckout(any(Checkout.class)))
                .thenReturn(new ChainResponse(true, "", checkout));

        ResponseEntity actual = controller.getCheckoutById(dto);

        ResponseEntity expected = ResponseEntity.ok(checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail api/checkout/get")
    void failEndpoint(){
        CheckoutGetDto dto = new CheckoutGetDto("");

        Mockito.when(checkoutGetChain.getCheckout(any(Checkout.class)))
                .thenReturn(new ChainResponse(false, ""));

        ResponseEntity actual = controller.getCheckoutById(dto);

        ResponseEntity expected = ResponseEntity.badRequest().body("");

        assertThat(actual, samePropertyValuesAs(expected));
    }

}
