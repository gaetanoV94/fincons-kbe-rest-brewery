package com.finconsgroup.kberestbrewery.services;


import com.finconsgroup.kberestbrewery.web.model.BeerOrderDto;
import com.finconsgroup.kberestbrewery.web.model.BeerOrderList;

public interface BeerOrderService {

    BeerOrderList listOrders(Long customerId);

    BeerOrderDto placeOrder(Long customerId, BeerOrderDto beerOrderDto);

    BeerOrderDto getOrderById(Long customerId, Long orderId);

    void pickupOrder(Long customerId, Long orderId);

    BeerOrderDto getOneOrderById(Long orderId);
}
