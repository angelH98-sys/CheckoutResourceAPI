package dev.ahernandez.checkoutresourceapi.service.imp;

import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import dev.ahernandez.checkoutresourceapi.persistence.repository.ProductRepository;
import dev.ahernandez.checkoutresourceapi.service.ProductServiceMethods;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Defines and implements methods to interact with Product DB entity.
 */
@Service
@AllArgsConstructor
public class ProductService implements ProductServiceMethods {

  /**
   * Access to Product DB methods.
   */
  private ProductRepository productRepository;

  /**
   * Gets a Product, filtering by productId.
   *
   * @param productId to filter
   * @return Product
   */
  @Override
  public Product findById(int productId) {
    Optional<Product> optionalProduct = productRepository.findById(productId);
    if (optionalProduct.isEmpty()) {
      return null;
    } else {
      return optionalProduct.get();
    }
  }

  /**
   * Persist a product in db.
   *
   * @param product to persist
   * @return Product
   */
  @Override
  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }

  /**
   * Gets all products in DB.
   *
   * @return list of products
   */
  @Override
  public List<Product> findAll() {

    Iterator<Product> productIterator = productRepository.findAll().iterator();
    List<Product> productList = new ArrayList<>();

    while (productIterator.hasNext()) {
      productList.add(productIterator.next());
    }

    return productList;
  }

  /**
   * Persist a list of products in DB.
   *
   * @param productList to persist
   * @return list of products
   */
  @Override
  public List<Product> saveAll(List<Product> productList) {
    productList = (List<Product>) productRepository.saveAll(productList);
    return productList;
  }

}
