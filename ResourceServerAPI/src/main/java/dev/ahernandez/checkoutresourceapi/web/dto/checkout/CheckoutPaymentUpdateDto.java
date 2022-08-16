package dev.ahernandez.checkoutresourceapi.web.dto.checkout;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * DTO to request checkoutuuid and paymentId.
 */
@Data
public class CheckoutPaymentUpdateDto implements Serializable {
  @NotBlank(message = "CheckoutUUID is required")
  @Length(max = 36, message = "CheckoutUUID length exceeded")
  private final String checkoutUuid;
  @Min(message = "paymentId required/invalid", value = 1)
  private final int paymentId;
}
