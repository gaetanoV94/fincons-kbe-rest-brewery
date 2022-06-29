package com.finconsgroup.kberestbrewery.repositories;

import com.finconsgroup.kberestbrewery.domain.BeerOrder;
import com.finconsgroup.kberestbrewery.domain.Customer;
import com.finconsgroup.kberestbrewery.domain.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

public interface BeerOrderRepository extends JpaRepository<BeerOrder, Long> {

    List<BeerOrder> findAllByCustomer(Customer customer);

    List<BeerOrder> findAllByOrderStatus(OrderStatusEnum orderStatusEnum);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    BeerOrder findOneById(Long id);
}
