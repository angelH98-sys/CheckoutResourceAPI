package dev.ahernandez.checkoutresourceapi.web.dto.user;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * UserSaveDto.
 */
@Data
public class UserSaveDto implements Serializable {

  @NotBlank(message = "First name is required")
  @Length(max = 25, message = "First name length exceeded")
  private final String firstName;

  @NotBlank(message = "Last name is required")
  @Length(max = 36, message = "Last name length exceeded")
  private final String lastName;

  @Email
  @NotBlank(message = "Mail is required")
  @Length(max = 50, message = "Email length exceeded")
  private final String mail;

  @Pattern(message = "Phone format incorrect, must be like +503 #### ####",
          regexp = "^\\+503 [0-9]{4} [0-9]{4}$")
  @NotBlank(message = "Phone is required")
  private final String phone;
}
