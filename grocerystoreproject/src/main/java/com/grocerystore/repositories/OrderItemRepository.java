package com.grocerystore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.grocerystore.entities.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

}
