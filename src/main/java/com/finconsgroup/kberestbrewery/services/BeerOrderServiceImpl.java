package com.finconsgroup.kberestbrewery.services;


import com.finconsgroup.kberestbrewery.domain.Customer;
import com.finconsgroup.kberestbrewery.repositories.CustomerRepository;
import com.finconsgroup.kberestbrewery.web.mappers.BeerOrderMapper;
import com.finconsgroup.kberestbrewery.web.model.BeerOrderDto;
import com.finconsgroup.kberestbrewery.web.model.BeerOrderList;
import com.finconsgroup.kberestbrewery.domain.BeerOrder;
import com.finconsgroup.kberestbrewery.domain.OrderStatusEnum;
import com.finconsgroup.kberestbrewery.repositories.BeerOrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BeerOrderServiceImpl implements BeerOrderService {

    private final BeerOrderRepository beerOrderRepository;
    private final CustomerRepository customerRepository;
    private final BeerOrderMapper beerOrderMapper;

    @Override
    public BeerOrderList listOrders(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            List<BeerOrder> beerOrderPage =
                    beerOrderRepository.findAllByCustomer(customerOptional.get());

            return new BeerOrderList(beerOrderPage
                    .stream()
                    .map(beerOrderMapper::beerOrderToDto)
                    .collect(Collectors.toList()));
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public BeerOrderDto placeOrder(Long customerId, BeerOrderDto beerOrderDto) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            BeerOrder beerOrder = beerOrderMapper.dtoToBeerOrder(beerOrderDto);
            beerOrder.setId(null); //should not be set by outside client
            beerOrder.setCustomer(customerOptional.get());
            beerOrder.setOrderStatus(OrderStatusEnum.NEW);

            beerOrder.getBeerOrderLines().forEach(line -> line.setBeerOrder(beerOrder));

            BeerOrder savedBeerOrder = beerOrderRepository.saveAndFlush(beerOrder);

            log.debug("Saved Beer Order: " + beerOrder.getId());

            return beerOrderMapper.beerOrderToDto(savedBeerOrder);
        }
        //todo add exception type
        throw new RuntimeException("Customer Not Found");
    }

    @Override
    public BeerOrderDto getOrderById(Long customerId, Long orderId) {
        return beerOrderMapper.beerOrderToDto(getOrder(customerId, orderId));
    }

    @Override
    public void pickupOrder(Long customerId, Long orderId) {
        BeerOrder beerOrder = getOrder(customerId, orderId);
        beerOrder.setOrderStatus(OrderStatusEnum.PICKED_UP);
        beerOrderRepository.save(beerOrder);
    }

    private BeerOrder getOrder(Long customerId, Long orderId){
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if(customerOptional.isPresent()){
            Optional<BeerOrder> beerOrderOptional = beerOrderRepository.findById(orderId);

            if(beerOrderOptional.isPresent()){
                BeerOrder beerOrder = beerOrderOptional.get();

                // fall to exception if customer id's do not match - order not for customer
                if(beerOrder.getCustomer().getId().equals(customerId)){
                    return beerOrder;
                }
            }
            throw new RuntimeException("Beer Order Not Found");
        }
        throw new RuntimeException("Customer Not Found");
    }
}