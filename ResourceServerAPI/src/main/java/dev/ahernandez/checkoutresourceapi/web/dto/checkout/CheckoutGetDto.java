package dev.ahernandez.checkoutresourceapi.web.dto.checkout;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * Checkout get dto to request checkoutuuid.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutGetDto {
  @NotBlank(message = "checkoutUUID is required")
  @Length(max = 36, message = "CheckoutUUID length exceeded")
  private String checkoutUuid;
}
