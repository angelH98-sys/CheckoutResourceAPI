package dev.ahernandez.checkoutresourceapi.web.dto.productorder;

import java.io.Serializable;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ProductOrderCreate dto.
 */
@Data
@AllArgsConstructor
public class ProductOrderCreateDto implements Serializable {

  @Min(message = "ProductId required/invalid", value = 1)
  private int productId;

  @Min(message = "Quantity must be at least 1", value = 1)
  private int quantity;
}
