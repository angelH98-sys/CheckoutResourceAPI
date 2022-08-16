package dev.ahernandez.checkoutresourceapi.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Product entity.
 */
@Entity
@Table(name = "product")
public class Product {

  @Getter
  @Setter
  @Id
  @Column(name = "productid", nullable = false)
  private int productId;
  @Getter
  @Setter
  @Column(name = "productname")
  private String productName;
  @Getter
  @Setter
  @Column(name = "stock", nullable = false)
  private int stock;
  @Getter
  @Setter
  @Column(name = "unitprice")
  private double unitPrice;
  @Getter
  @Setter
  @Column(name = "enabled", nullable = false)
  private boolean enabled;

}
