package dev.ahernandez.checkoutresourceapi.service;

import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import java.util.List;

/**
 * Interface to define all methods to
 * request/persit info in Product entity.
 */
public interface ProductServiceMethods {

  Product findById(int productId);

  Product saveProduct(Product product);

  List<Product> findAll();

  List<Product> saveAll(List<Product> productList);
}
