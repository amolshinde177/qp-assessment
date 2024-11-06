package com.grocerystore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grocerystore.entities.OrderItem;
import com.grocerystore.repositories.OrderItemRepository;

@Component
public class OrderItemService {
	@Autowired
	OrderItemRepository orderItemRepository;

	public OrderItem addOrUpdateOrderItem(OrderItem orderItem) {
		orderItem = orderItemRepository.save(orderItem);
		return orderItem;
	}

}
