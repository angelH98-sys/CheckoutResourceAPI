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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

@DisplayName("Product Get Methods Test")
class ProductServiceGetMethodsTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findById product return")
    void successFindById(){
        Product product = new Product();
        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        Product result = productService.findById(0);

        assertEquals(product, result);
    }

    @Test
    @DisplayName("findById empty return")
    void emptyFindById(){
        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

        Product result = productService.findById(0);

        assertEquals(null, result);
    }

    @Test
    @DisplayName("findAll list return")
    void successFindAll(){
        List<Product> list = new ArrayList<>();
        list.add(new Product());
        Iterable<Product> iterable = list;
        Mockito.when(productRepository.findAll()).thenReturn(list);

        List<Product> result = productService.findAll();

        assertEquals(list, result);
    }
}
