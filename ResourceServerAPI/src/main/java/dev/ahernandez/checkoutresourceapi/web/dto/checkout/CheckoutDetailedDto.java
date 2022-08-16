package dev.ahernandez.checkoutresourceapi.web.dto.checkout;

import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import dev.ahernandez.checkoutresourceapi.web.dto.productorder.ProductOrderLiteDto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Checkout detail dto for get checkout request.
 */
public class CheckoutDetailedDto {
  @Getter
  @Setter
  private String checkoutUuid;
  @Getter
  @Setter
  private User user;
  @Getter
  @Setter
  private UserAddress userAddress;
  @Getter
  @Setter
  private UserPayment userPayment;
  @Getter
  @Setter
  private Double totalAmount;
  @Getter
  @Setter
  private String checkoutStatus;
  @Getter
  private List<ProductOrderLiteDto> productOrders;

  /**
   * Sets current object to checkout states.
   *
   * @param checkout to be setted
   */
  public CheckoutDetailedDto(Checkout checkout) {
    checkoutUuid = checkout.getCheckoutUuid();
    user = checkout.getUser();
    userAddress = checkout.getUserAddress();
    userPayment = checkout.getUserPayment();
    totalAmount = checkout.getTotalAmount();
    checkoutStatus = checkout.getCheckoutStatus();
    productOrders = setProductOrders(checkout.getProductOrders());
  }

  /**
   * Sets productOrderList.
   *
   * @param productOrders to be setted in current object
   * @return List of ProductOrderLiteDTO
   */
  private List<ProductOrderLiteDto> setProductOrders(
          List<ProductOrder> productOrders) {
    List<ProductOrderLiteDto> productOrderLiteDtos = new ArrayList<>();
    Iterator<ProductOrder> productOrderIterable = productOrders.iterator();
    while (productOrderIterable.hasNext()) {
      productOrderLiteDtos
              .add(new ProductOrderLiteDto(productOrderIterable.next()));
    }
    return productOrderLiteDtos;
  }

}
