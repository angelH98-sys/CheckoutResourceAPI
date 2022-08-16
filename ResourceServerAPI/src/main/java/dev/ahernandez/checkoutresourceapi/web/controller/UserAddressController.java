package dev.ahernandez.checkoutresourceapi.web.controller;

import dev.ahernandez.checkoutresourceapi.helpers.CustomRequestResponses;
import dev.ahernandez.checkoutresourceapi.persistence.model.UserAddress;
import dev.ahernandez.checkoutresourceapi.service.imp.UserAddressService;
import dev.ahernandez.checkoutresourceapi.web.dto.useraddress.UserAddressDto;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Manages UserAddress endpoints.
 */
@RestController
@Slf4j
@RequestMapping("api/user/address")
@AllArgsConstructor
public class UserAddressController {

  private UserAddressService addressService;

  /**
   * Get all addresses by userid.
   *
   * @return ResponseEntity
   */
  @GetMapping("get")
  public ResponseEntity<Object> getAllAddressByUserId() {

    String userId =
            SecurityContextHolder.getContext().getAuthentication().getName();

    try {

      List<UserAddress> addressList =
              addressService.getAllAddressByUserId(userId);

      if (addressList == null) {
        return ResponseEntity.ok(new ArrayList<UserAddress>());
      }

      return ResponseEntity.ok(addressList);

    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseEntity.internalServerError()
              .body(CustomRequestResponses.SERVER_ERROR_MESSAGE);
    }

  }

  /**
   * Persist a UserAddress object in DB.
   *
   * @param addressDto to be persisted
   * @return ResponseEntity
   */
  @PostMapping("create")
  public ResponseEntity<Object> createAddressByUser(@Valid @RequestBody
                                                    UserAddressDto addressDto) {

    ModelMapper mapper = new ModelMapper();
    UserAddress address = mapper.map(addressDto, UserAddress.class);
    String userId =
            SecurityContextHolder.getContext().getAuthentication().getName();

    address.setAddressId(0);
    address.setEnabled(true);
    address.setUserId(userId);

    try {
      address = addressService.saveAddress(address);
      return ResponseEntity.ok(address);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseEntity.internalServerError()
              .body(CustomRequestResponses.SERVER_ERROR_MESSAGE);
    }
  }
}
