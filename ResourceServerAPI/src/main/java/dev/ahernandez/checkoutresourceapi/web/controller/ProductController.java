package dev.ahernandez.checkoutresourceapi.web.controller;

import dev.ahernandez.checkoutresourceapi.helpers.CustomRequestResponses;
import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import dev.ahernandez.checkoutresourceapi.service.imp.ProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Manages Product endpoints.
 */
@RestController
@Slf4j
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

  private ProductService productService;

  /**
   * Request to db all products in DB, then return it to client.
   *
   * @return ResponseEntity with list of products
   */
  @GetMapping("get/all")
  public ResponseEntity<Object> getAllProducts() {
    try {
      List<Product> products = productService.findAll();
      return ResponseEntity.ok(products);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseEntity.internalServerError()
              .body(CustomRequestResponses.SERVER_ERROR_MESSAGE);
    }
  }
}
