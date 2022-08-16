package dev.ahernandez.checkoutresourceapi.web.dto.productorder;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * ProductOrderDelete dto.
 */
@Data
public class ProductOrderDeleteDto implements Serializable {

  @NotBlank(message = "CheckoutUUID is required")
  @Length(max = 36, message = "CheckoutUUID length exceeded")
  private final String checkoutUuid;

  @Min(message = "ProductId required/invalid", value = 1)
  private final int productId;
}
