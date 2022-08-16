package dev.ahernandez.checkoutresourceapi.web.controller;

import dev.ahernandez.checkoutresourceapi.helpers.CustomRequestResponses;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserPayment;
import dev.ahernandez.checkoutresourceapi.service.imp.UserPaymentService;
import dev.ahernandez.checkoutresourceapi.web.dto.userpayment.UserPaymentDto;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Manages UserPayments endpoit.
 */
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/user/payment")
public class UserPaymentController {

  private UserPaymentService paymentService;

  /**
   * Get all payments by user.
   *
   * @return ResponseEntity
   */
  @GetMapping("get/all")
  public ResponseEntity<Object> getAllPaymentByUser() {
    try {
      String userId =
              SecurityContextHolder.getContext().getAuthentication().getName();

      List<UserPayment> paymentList =
              paymentService.getAllPaymentByUserId(userId);

      return ResponseEntity.ok(paymentList);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseEntity.internalServerError()
              .body(CustomRequestResponses.SERVER_ERROR_MESSAGE);
    }
  }

  /**
   * Persist a user in DB.
   *
   * @param paymentDto to request payment information
   * @return ResponseEntity
   */
  @PostMapping("create")
  public ResponseEntity<Object> saveUserPayment(@Valid @RequestBody
                                                  UserPaymentDto paymentDto) {
    ModelMapper mapper = new ModelMapper();
    UserPayment payment = mapper.map(paymentDto, UserPayment.class);
    payment.setPaymentId(0);
    payment.setUserId(
            SecurityContextHolder.getContext().getAuthentication().getName());
    payment.setEnabled(true);
    try {
      payment = paymentService.savePayment(payment);
      return ResponseEntity.ok(payment);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseEntity.internalServerError()
              .body(CustomRequestResponses.SERVER_ERROR_MESSAGE);
    }
  }
}
