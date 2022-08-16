package dev.ahernandez.checkoutresourceapi.controllers.ProductOrder;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.chains.ProductOrderCreateChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.chains.ProductOrderDeleteChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.chains.ProductOrderUpdateChain;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.web.controller.ProductOrderController;
import dev.ahernandez.checkoutresourceapi.web.dto.productorder.ProductOrderDeleteDto;
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

@DisplayName("DeleteProductOrder Test")
class DeleteProductOrderTest {
    @Mock
    private ProductOrderCreateChain additionChain;
    @Mock
    private ProductOrderUpdateChain updateChain;
    @Mock
    private ProductOrderDeleteChain deleteChain;

    @InjectMocks
    private ProductOrderController controller;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success /api/order/delete")
    void successEndPoint(){
        ProductOrderDeleteDto dto = new ProductOrderDeleteDto("", 1);

        Mockito.when(deleteChain.deleteProductOrder(any(ProductOrder.class)))
                .thenReturn(new ChainResponse(true, ""));

        ResponseEntity actual = controller.deleteProductOrder(dto);

        ResponseEntity expected = ResponseEntity.ok("");

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail /api/order/delete")
    void failEndPoint(){
        ProductOrderDeleteDto dto = new ProductOrderDeleteDto("", 1);

        Mockito.when(deleteChain.deleteProductOrder(any(ProductOrder.class)))
                .thenReturn(new ChainResponse(false, ""));

        ResponseEntity actual = controller.deleteProductOrder(dto);

        ResponseEntity expected = ResponseEntity.badRequest().body("");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
