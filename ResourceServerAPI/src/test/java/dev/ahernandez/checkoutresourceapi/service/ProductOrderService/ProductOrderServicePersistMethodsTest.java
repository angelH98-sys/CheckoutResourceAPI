package dev.ahernandez.checkoutresourceapi.service.ProductOrderService;

import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.persistence.repository.ProductOrderRepository;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@DisplayName("ProductOrderService Persist Methods Test")
class ProductOrderServicePersistMethodsTest {

    @Mock
    private ProductOrderRepository productOrderRepository;
    @InjectMocks
    private ProductOrderService productOrderService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("saveProductOrder ProductOrder return")
    void successSaveProduct(){

        ProductOrder productOrder = new ProductOrder();

        Mockito.when(productOrderRepository.save(any(ProductOrder.class)))
                .thenReturn(productOrder);

        ProductOrder result = productOrderService.saveProductOrder(productOrder);

        assertEquals(productOrder, result);
    }

    @Test
    void succeedDeleteProductOrder(){
        Mockito.doNothing().when(productOrderRepository).deleteById(anyInt());

        productOrderService.deleteProductOrderByOrderId(1);

        assertTrue(true);
    }

}
