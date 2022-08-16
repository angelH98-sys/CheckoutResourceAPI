package dev.ahernandez.checkoutresourceapi.service.ProductService;

import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import dev.ahernandez.checkoutresourceapi.persistence.repository.ProductRepository;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("ProductService Persist Methods Test")
class ProductServicePersistMethodsTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success saveProduct")
    void successSaveProduct(){
        Product product = new Product();
        Mockito.when(productRepository.save(product)).thenReturn(product);

        Product result = productService.saveProduct(product);

        assertEquals(product, result);
    }

    @Test
    @DisplayName("Success SaveAll")
    void successSaveAll(){
        List<Product> productList = new ArrayList<>();
        Mockito.when(productRepository.saveAll(any()))
                .thenReturn(productList);

        List<Product> actual = productService.saveAll(productList);

        assertEquals(productList, actual);
    }
}
