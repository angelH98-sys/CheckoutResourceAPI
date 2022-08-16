package dev.ahernandez.checkoutresourceapi.service.ProductOrderService;

import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@DisplayName("ProductOrderService get methods test")
class ProductOrderServiceGetMethodsTest {

    @Mock
    private ProductOrderRepository productOrderRepository;
    @InjectMocks
    private ProductOrderService productOrderService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getProductOrderByCheckoutAndProduct ProductOrder return")
    void successGetByCheckoutAndProduct(){

        List<ProductOrder> orderList = new ArrayList<>();
        ProductOrder productOrder = new ProductOrder();
        Product product = new Product();

        productOrder.setProduct(product);
        orderList.add(productOrder);

        Mockito.when(productOrderRepository.findByCheckout(any(Checkout.class)))
                .thenReturn(Optional.of(orderList));

        ProductOrder result = productOrderService
                .getProductOrderByCheckoutAndProduct(new Checkout(), product);

        assertEquals(productOrder, result);
    }

    @Test
    @DisplayName("getProductOrderByCheckoutAndProduct empty return")
    void emptyFindByCheckoutAndProduct(){
        Mockito.when(productOrderRepository.findByCheckout(any(Checkout.class)))
                .thenReturn(Optional.empty());
        ProductOrder result = productOrderService
                .getProductOrderByCheckoutAndProduct(new Checkout(), new Product());

        assertEquals(null, result);
    }

    @Test
    @DisplayName("getProductOrderByCheckoutAndProduct with no product")
    void noProductInFindByCheckoutAndProduct(){

        List<ProductOrder> orderList = new ArrayList<>();
        ProductOrder productOrder = new ProductOrder();
        Product productInDB = new Product();

        productInDB.setProductId(1);
        productOrder.setProduct(productInDB);
        orderList.add(productOrder);

        Mockito.when(productOrderRepository.findByCheckout(any(Checkout.class)))
                .thenReturn(Optional.of(orderList));

        Product currentProduct = new Product();
        currentProduct.setProductId(2);

        ProductOrder result = productOrderService
                .getProductOrderByCheckoutAndProduct(new Checkout(), currentProduct);

        assertEquals(null, result);
    }

    @Test
    @DisplayName("getCheckoutByUserId")
    void successFindByCheckout(){
        List<ProductOrder> orderList = new ArrayList<>();
        orderList.add(new ProductOrder());

        Mockito.when(productOrderRepository.findByCheckout(any(Checkout.class)))
                .thenReturn(Optional.of(orderList));

        List<ProductOrder> result = productOrderService
                .getProductOrderByCheckout(new Checkout());

        assertEquals(orderList, result);
    }

    @Test
    @DisplayName("getCheckoutByUserId empty return")
    void emptyFindByCheckout(){
        Mockito.when(productOrderRepository.findByCheckout(any(Checkout.class)))
                .thenReturn(Optional.empty());
        List<ProductOrder> result = productOrderService.getProductOrderByCheckout(new Checkout());

        assertEquals(null, result);
    }

    @Test
    @DisplayName("Success getProductOrderById")
    void successGetProductOrderById(){
        ProductOrder order = new ProductOrder();
        Mockito.when(productOrderRepository.findById(anyInt()))
                .thenReturn(Optional.of(order));

        ProductOrder actual = productOrderService.getProductOrderById(1);

        assertEquals(order, actual);
    }

    @Test
    @DisplayName("Empty getProductOrderById")
    void emptysuccessGetProductOrderById(){

        Mockito.when(productOrderRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        ProductOrder actual = productOrderService.getProductOrderById(1);

        assertEquals(null, actual);
    }
}
