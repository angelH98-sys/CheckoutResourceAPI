package dev.ahernandez.checkoutresourceapi.web.dto.useraddress;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * UserAddressDto.
 */
@Data
@NoArgsConstructor
public class UserAddressDto implements Serializable {

  @Getter
  @Setter
  @NotBlank(message = "Address name is required")
  @Length(max = 24, message = "Address name length exceeded")
  private String addressName;

  @Getter
  @Setter
  @Length(max = 20, message = "State name length exceeded")
  @NotBlank(message = "State name is required")
  private String state;

  @Getter
  @Setter
  @Length(max = 50, message = "City name length exceeded")
  @NotBlank(message = "City name is required")
  private String city;

  @Getter
  @Setter
  @Length(max = 10, message = "House number length exceeded")
  @NotBlank(message = "House number is required")
  private String houseNumber;

  @Getter
  @Setter
  @Length(max = 36, message = "Address details length exceeded")
  private String addressDetails;
}
