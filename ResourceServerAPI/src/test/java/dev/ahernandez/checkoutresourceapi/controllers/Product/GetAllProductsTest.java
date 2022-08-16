package dev.ahernandez.checkoutresourceapi.controllers.Product;

import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductService;
import dev.ahernandez.checkoutresourceapi.web.controller.ProductController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@DisplayName("GetAllProducts Test")
class GetAllProductsTest {
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController controller;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Success /api/product/get/all")
    void successEndpoint(){
        List<Product> productList = new ArrayList<>();
        Mockito.when(productService.findAll()).thenReturn(productList);

        ResponseEntity actual = controller.getAllProducts();

        ResponseEntity expected = ResponseEntity.ok(productList);

        assertThat(actual, samePropertyValuesAs(expected));
    }
}
