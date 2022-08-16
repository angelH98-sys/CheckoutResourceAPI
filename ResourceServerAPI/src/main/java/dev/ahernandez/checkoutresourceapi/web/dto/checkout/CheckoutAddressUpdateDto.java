package dev.ahernandez.checkoutresourceapi.web.dto.checkout;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * DTO for address checkout update.
 */
@Data
public class CheckoutAddressUpdateDto implements Serializable {
  @NotBlank(message = "checkoutUUID is required")
  @Length(max = 36, message = "CheckoutUUID length exceeded")
  private final String checkoutUuid;
  @Min(message = "AddressId required/invalid", value = 1)
  private final int addressId;
}
