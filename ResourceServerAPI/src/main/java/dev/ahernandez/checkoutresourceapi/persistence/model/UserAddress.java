package dev.ahernandez.checkoutresourceapi.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * UserAddress entity.
 */
@Entity
@Table(name = "useraddress")
public class UserAddress {

  @Getter
  @Setter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "addressid", nullable = false)
  private int addressId;
  @Getter
  @Setter
  @Column(name = "userid", nullable = false)
  private String userId;
  @Getter @Setter
  @Column(name = "addressname", nullable = false)
  private String addressName;
  @Getter @Setter
  @Column(name = "state", nullable = false)
  private String state;
  @Getter @Setter
  @Column(name = "city", nullable = false)
  private String city;
  @Getter @Setter
  @Column(name = "housenumber")
  private String houseNumber;
  @Getter @Setter
  @Column(name = "addressdetails")
  private String addressDetails;
  @Getter
  @Setter
  @Column(name = "enabled", nullable = false)
  private boolean enabled;

}
