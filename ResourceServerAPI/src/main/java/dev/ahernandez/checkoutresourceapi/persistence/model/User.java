package dev.ahernandez.checkoutresourceapi.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * User entity.
 */
@Entity
@Table(name = "users")
public class User {
  @Id
  @Getter
  @Setter
  @Column(name = "userid")
  private String userId;
  @Getter
  @Setter
  @Column(name = "firstname")
  private String firstName;
  @Getter
  @Setter
  @Column(name = "lastname")
  private String lastName;
  @Getter
  @Setter
  @Column(name = "mail")
  private String mail;
  @Getter
  @Setter
  @Column(name = "phone")
  private String phone;
}
