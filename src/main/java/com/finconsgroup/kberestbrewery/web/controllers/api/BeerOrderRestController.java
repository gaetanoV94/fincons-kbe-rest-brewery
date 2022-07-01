package com.finconsgroup.kberestbrewery.web.controllers.api;

import com.finconsgroup.kberestbrewery.services.BeerOrderService;
import com.finconsgroup.kberestbrewery.web.model.BeerOrderDto;
import com.finconsgroup.kberestbrewery.web.model.BeerOrderList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class BeerOrderRestController {

    private final BeerOrderService orderService;

    @GetMapping(produces = {"application/json"}, path = "customers/{customerId}/orders")
    public ResponseEntity<BeerOrderList> listCustomerOrders(@RequestParam(value = "customerId", required = true) Long customerId) {

        log.debug("Listing Orders by Customer id");
        BeerOrderList beerOrderList = orderService.listOrders(customerId);
        return new ResponseEntity<>(beerOrderList, HttpStatus.OK);
    }

    @PostMapping(path = "customers/{customerId}/orders")
    public ResponseEntity createOrder(@RequestParam(value = "customerId", required = false) Long customerId, @Valid @RequestBody BeerOrderDto beerOrderDto) {

        try {
            return new ResponseEntity(orderService.placeOrder(beerOrderDto.getCustomerId(), beerOrderDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping(path = {"customers/{customerId}/orders/{orderId}"}, produces = {"application/json"})
    public ResponseEntity<BeerOrderDto> getCustomerOrderById(@PathVariable("customerId") Long customerId, @PathVariable("orderId") Long orderId) {

        log.debug("Getting customer order by id");
        return new ResponseEntity<>(orderService.getOrderById(customerId, orderId), HttpStatus.OK);
    }

    @PutMapping(path = {"customers/{customerId}/orders/{orderId}/pickup"}, produces = {"application/json"})
    public ResponseEntity pickupCustomerOrder(@PathVariable("customerId") Long customerId, @PathVariable("orderId") Long orderId) {
        orderService.pickupOrder(customerId, orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
