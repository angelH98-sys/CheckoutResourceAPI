package dev.ahernandez.checkoutresourceapi.web.controller;

import dev.ahernandez.checkoutresourceapi.helpers.CustomRequestResponses;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutAddressUpdateChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutGetChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutPaymentUpdateChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.chains.CheckoutStatusUpdateChain;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import dev.ahernandez.checkoutresourceapi.service.imp.CheckoutService;
import dev.ahernandez.checkoutresourceapi.web.dto.checkout.CheckoutAddressUpdateDto;
import dev.ahernandez.checkoutresourceapi.web.dto.checkout.CheckoutGetDto;
import dev.ahernandez.checkoutresourceapi.web.dto.checkout.CheckoutPaymentUpdateDto;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Manages checkout endpoints.
 */
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/checkout/")
public class CheckoutController {

  private CheckoutAddressUpdateChain addressUpdateChain;
  private CheckoutPaymentUpdateChain paymentUpdateChain;
  private CheckoutGetChain checkoutGetChain;
  private CheckoutStatusUpdateChain checkoutStatusUpdateChain;
  private CheckoutService checkoutService;

  /**
   * To update address in a checkout.
   *
   * @param checkoutAddressUpdateDto dto to update address
   * @return ResponseEntity
   */
  @PutMapping("update/address")
  public ResponseEntity<String> updateCheckoutAddress(@Valid @RequestBody
                            CheckoutAddressUpdateDto checkoutAddressUpdateDto) {

    ModelMapper mapper = new ModelMapper();
    UserAddress address =
            mapper.map(checkoutAddressUpdateDto, UserAddress.class);
    Checkout checkout =
            mapper.map(checkoutAddressUpdateDto, Checkout.class);
    checkout.setUserAddress(address);
    try {

      ChainResponse response =
              addressUpdateChain.updateCheckoutAddress(checkout);

      if (response.isItSucceed()) {
        return ResponseEntity.ok(response.getMessage());
      } else {
        return ResponseEntity.badRequest().body(response.getMessage());
      }

    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseEntity.internalServerError()
              .body(CustomRequestResponses.SERVER_ERROR_MESSAGE);
    }
  }

  /**
   * To update payment in checkout.
   *
   * @param checkoutPaymentUpdateDto to set checkoutuuid and paymentid
   * @return ResponseEntity
   */
  @PutMapping("update/payment")
  public ResponseEntity<String> updateCheckoutPayment(@Valid @RequestBody
                            CheckoutPaymentUpdateDto checkoutPaymentUpdateDto) {
    ModelMapper mapper = new ModelMapper();
    UserPayment payment =
            mapper.map(checkoutPaymentUpdateDto, UserPayment.class);
    Checkout checkout = mapper.map(checkoutPaymentUpdateDto, Checkout.class);
    checkout.setUserPayment(payment);
    try {
      ChainResponse response = paymentUpdateChain.paymentUpdate(checkout);

      if (response.isItSucceed()) {
        return ResponseEntity.ok(response.getMessage());
      } else {
        return ResponseEntity.badRequest().body(response.getMessage());
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseEntity.internalServerError()
              .body(CustomRequestResponses.SERVER_ERROR_MESSAGE);
    }
  }

  /**
   * To get a checkout by id.
   *
   * @param checkoutGetDto to request checkoutuuid
   * @return ResponseEntity
   */
  @GetMapping("get")
  public ResponseEntity<Object> getCheckoutById(@Valid @RequestBody
                                                CheckoutGetDto checkoutGetDto) {

    ModelMapper mapper = new ModelMapper();
    Checkout checkout = mapper.map(checkoutGetDto, Checkout.class);

    try {
      ChainResponse response = checkoutGetChain.getCheckout(checkout);

      if (response.isItSucceed()) {
        return ResponseEntity.ok(response.getObject());
      } else {
        return ResponseEntity.badRequest().body(response.getMessage());
      }
    } catch (Exception e) {

      log.error(e.getMessage(), e);
      return ResponseEntity.internalServerError()
              .body(CustomRequestResponses.SERVER_ERROR_MESSAGE);
    }
  }

  /**
   * To get all checkout by userID.
   *
   * @return ResponseEntity
   */
  @GetMapping("get/all")
  public ResponseEntity<Object> getAllCheckoutByUser() {
    try {
      String userId =
              SecurityContextHolder.getContext().getAuthentication().getName();
      List<Checkout> checkoutList = checkoutService.getCheckoutByUserId(userId);

      return ResponseEntity.ok(checkoutList);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseEntity.internalServerError()
              .body(CustomRequestResponses.SERVER_ERROR_MESSAGE);
    }
  }

  /**
   * To set checkout completed.
   *
   * @param checkoutGetDto to request checkoutuuid
   * @return ResponseEntity
   */
  @PostMapping("set/completed")
  public ResponseEntity<String> setCheckoutCompleted(@Valid @RequestBody
                                               CheckoutGetDto checkoutGetDto) {
    ModelMapper mapper = new ModelMapper();
    Checkout checkout = mapper.map(checkoutGetDto, Checkout.class);

    try {
      ChainResponse response = checkoutStatusUpdateChain.setCompleted(checkout);
      if (response.isItSucceed()) {
        return ResponseEntity.ok("");
      } else {
        return ResponseEntity.badRequest().body(response.getMessage());
      }
    } catch (Exception e) {

      log.error(e.getMessage(), e);
      return ResponseEntity.internalServerError()
              .body(CustomRequestResponses.SERVER_ERROR_MESSAGE);
    }
  }
}
