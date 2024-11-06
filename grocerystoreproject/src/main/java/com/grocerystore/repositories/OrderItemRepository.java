package com.grocerystore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.grocerystore.entities.OrderItem;

import jakarta.transaction.Transactional;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

	@Query(value = "select * from order_item oi where oi.item_id = :itemId", nativeQuery = true)
	public List<OrderItem> findOrderItemByItemId(@Param("itemId") Long itemId);

	@Query(value = "select * from order_item oi where oi.order_id = :orderId", nativeQuery = true)
	public List<OrderItem> findOrderItemByOrderId(@Param("orderId") Long itemId);

	@Modifying
    @Transactional
	@Query(value = "delete from order_item oi where oi.item_id = :itemId", nativeQuery = true)
	public void deleteOrderItemByItemId(@Param("itemId") Long itemId);

}
