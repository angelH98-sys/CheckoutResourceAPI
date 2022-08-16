package dev.ahernandez.checkoutresourceapi.web.dto.userpayment;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * UserPaymentDto.
 */
@Data
@NoArgsConstructor
public class UserPaymentDto implements Serializable {

  @Length(max = 10, message = "Payment name length exceeded")
  @NotBlank(message = "Payment name is required")
  private String paymentName;

  @Length(max = 50, message = "Owner name length exceeded")
  @NotBlank(message = "Owner name is required")
  private String ownerName;

  @Length(max = 64, message = "Card number length exceeded")
  @NotBlank(message = "Card number is required")
  @Pattern(regexp = "^4[0-9]{12}(?:[0-9]{3})?$",
          message = "Correct card format ################")
  private String cardNumber;

  @Length(max = 64, message = "expiration length exceeded")
  @NotBlank(message = "Expiration is required")
  @Pattern(regexp = "^[0-9]{2}\\/[0-9]{2}$",
          message = "Correct exp format ##/##")
  private String expiration;

  @Length(max = 64, message = "cvv length exceeded")
  @NotBlank(message = "CVV is required")
  @Pattern(regexp = "^[0-9]{3}$", message = "Correct cvv format ###")
  private String cvv;
}
