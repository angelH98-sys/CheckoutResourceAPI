package dev.ahernandez.checkoutresourceapi.web.dto.productorder;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * ProductOrderUpdateDto.
 */
@Data
public class ProductOrderUpdateDto implements Serializable {
  @NotBlank(message = "checkoutUUID is required")
  @Length(max = 36, message = "CheckoutUUID length exceeded")
  private final String checkoutUuid;
  @Min(message = "productId is required/invalid", value = 1)
  private final int productId;
  @Min(message = "Quantity must be at least 1", value = 1)
  private final int quantity;
}
