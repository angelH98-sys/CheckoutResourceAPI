package dev.ahernandez.checkoutresourceapi.web.controller;

import dev.ahernandez.checkoutresourceapi.helpers.CustomRequestResponses;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.chains.ProductOrderCreateChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.chains.ProductOrderDeleteChain;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.productorderbased.chains.ProductOrderUpdateChain;
import dev.ahernandez.checkoutresourceapi.persistence.model.ProductOrder;
import dev.ahernandez.checkoutresourceapi.web.dto.productorder.ProductOrderCreateDto;
import dev.ahernandez.checkoutresourceapi.web.dto.productorder.ProductOrderDeleteDto;
import dev.ahernandez.checkoutresourceapi.web.dto.productorder.ProductOrderUpdateDto;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * ProductOrderController handles request for.
 *
 * <ul>
 *     <li>Adding products to order</li>
 *     <li>Update an existing order</li>
 *     <li>Delete an existing order</li>
 * </ul>
 *
 * Each operation will be handle by a request mapping.
 * and then send it to a particular ProductOrderChain.
 * </p>
 */

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/order")
public class ProductOrderController {

  private ProductOrderCreateChain additionChain;
  private ProductOrderUpdateChain updateChain;
  private ProductOrderDeleteChain deleteChain;

  /**
   * Handles additions of products to checkout.
   * This will executes a chain of responsability named
   * AdditionChaain.addProductToCheckout
   *
   * @param productOrderCreateDto to execute chain
   * @return <p>Ok response if everything works fine, badRequest
   *          if exist any error on the client data</p>
   */
  @PostMapping("/create")
  public ResponseEntity<String> addProductOrder(@Valid @RequestBody
                                  ProductOrderCreateDto productOrderCreateDto) {

    ModelMapper mapper = new ModelMapper();
    ProductOrder productOrder =
            mapper.map(productOrderCreateDto, ProductOrder.class);
    productOrder.setOrderId(0);

    try {

      ChainResponse response = additionChain.addProductToCheckout(productOrder);
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

  /**
   * Handles updates of an existing productOrder.
   * This will executes a chain of responsability named
   * UpdateChain.updateProductOrder().
   *
   * @param productOrderUpdateDto to execute chain
   * @return <p>Ok response if everything works fine,
   *          badRequest if exist any error on the client data.</p>
   */
  @PutMapping("/update")
  public ResponseEntity<String> modifyProductOrder(@Valid @RequestBody
                                   ProductOrderUpdateDto productOrderUpdateDto) {
    ModelMapper mapper = new ModelMapper();
    ProductOrder productOrder =
            mapper.map(productOrderUpdateDto, ProductOrder.class);
    productOrder.setOrderId(0);
    try {

      ChainResponse response = updateChain.updateProductOrder(productOrder);

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

  /**
   * Handles a delete from an existing productOrder
   * This will executes a chain of responsability named
   * DeleteChain.deleteProductOrder()
   *
   * @param productOrderDeleteDto to execute chain
   * @return <p>Ok response if everything works fine, badRequest
   *          if exist any error on the client data</p>
   */
  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteProductOrder(@Valid @RequestBody
                                   ProductOrderDeleteDto productOrderDeleteDto) {
    ModelMapper mapper = new ModelMapper();
    ProductOrder productOrder =
            mapper.map(productOrderDeleteDto, ProductOrder.class);
    productOrder.setOrderId(0);
    try {

      ChainResponse response = deleteChain.deleteProductOrder(productOrder);

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
