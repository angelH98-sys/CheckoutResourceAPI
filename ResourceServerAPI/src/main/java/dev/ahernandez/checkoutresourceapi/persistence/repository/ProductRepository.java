package dev.ahernandez.checkoutresourceapi.persistence.repository;

import dev.ahernandez.checkoutresourceapi.persistence.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Product implementation of crudrepository.
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
