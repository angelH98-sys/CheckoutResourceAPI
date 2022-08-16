package dev.ahernandez.checkoutresourceapi.web.controller;

import dev.ahernandez.checkoutresourceapi.helpers.CustomRequestResponses;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.userbased.chains.UserSaveChain;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import dev.ahernandez.checkoutresourceapi.web.dto.user.UserSaveDto;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Manages User controller endpoints.
 */
@RestController
@Slf4j
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

  private UserSaveChain userSaveChain;

  /**
   * Persist a user in DB.
   *
   * @param userSaveDto to request user information
   * @return ResponseEntity
   */
  @PostMapping("save")
  public ResponseEntity<String> saveUser(@Valid @RequestBody
                                           UserSaveDto userSaveDto) {

    ModelMapper mapper = new ModelMapper();
    User user = mapper.map(userSaveDto, User.class);

    String userId =
            SecurityContextHolder.getContext().getAuthentication().getName();

    user.setUserId(userId);

    try {

      ChainResponse response = userSaveChain.saveUser(user);

      if (response.isItSucceed()) {
        return ResponseEntity.ok(response.getMessage());
      } else {
        return ResponseEntity.badRequest().body(response.getMessage());
      }

    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseEntity.internalServerError()
              .body(CustomRequestResponses.SERVER_ERROR_MESSAGE);
    }

  }
}
