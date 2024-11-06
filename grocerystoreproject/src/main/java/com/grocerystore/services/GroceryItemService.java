package com.grocerystore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grocerystore.booking.request.AddGroceryItemRequest;
import com.grocerystore.booking.request.UpdateInventoryRequest;
import com.grocerystore.booking.response.UpdateInventoryResponse;
import com.grocerystore.entities.GroceryItem;
import com.grocerystore.entities.GroceryOrder;
import com.grocerystore.entities.OrderItem;
import com.grocerystore.repositories.GroceryItemRepository;
import com.grocerystore.repositories.GroceryOrderRepository;
import com.grocerystore.repositories.OrderItemRepository;

@Component
public class GroceryItemService {

	@Autowired
	GroceryItemRepository groceryItemRepository;
	@Autowired
	OrderItemRepository orderItemRepository;
	@Autowired
	GroceryOrderRepository groceryOrderRepository;

	public GroceryItem addGroceryItem(AddGroceryItemRequest addGroceryItemRequest) {
		GroceryItem groceryItem = new GroceryItem();
		if (addGroceryItemRequest != null) {
			String name = addGroceryItemRequest.getName();
			String description = addGroceryItemRequest.getDescription();
			Double price = addGroceryItemRequest.getPrice();
			Long inventory = addGroceryItemRequest.getInventory();

			groceryItem.setName(name);
			groceryItem.setDescription(description);
			groceryItem.setPrice(price);
			groceryItem.setInventory(inventory);

			groceryItem = groceryItemRepository.save(groceryItem);
		}
		return groceryItem;
	}

	public GroceryItem updateGroceryItem(GroceryItem groceryItem, Long itemId) {
		GroceryItem updatedGroceryItem = null;
		if (groceryItem != null && itemId != null && itemId > 0) {
			Optional<GroceryItem> updatedGroceryItemOpt = groceryItemRepository.findById(itemId);
			if (updatedGroceryItemOpt.isPresent()) {
				updatedGroceryItem = updatedGroceryItemOpt.get();

				if (groceryItem.getName() != null) {
					updatedGroceryItem.setName(groceryItem.getName());
				}
				if (groceryItem.getDescription() != null) {
					updatedGroceryItem.setDescription(groceryItem.getDescription());
				}
				if (groceryItem.getPrice() != null) {
					updatedGroceryItem.setPrice(groceryItem.getPrice());
				}
				if (groceryItem.getInventory() != null) {
					updatedGroceryItem.setInventory(groceryItem.getInventory());
				}
				updatedGroceryItem = groceryItemRepository.save(updatedGroceryItem);
			}

		}
		return updatedGroceryItem;
	}

	public List<GroceryItem> getAllGroceryItem() {
		List<GroceryItem> groceryItems = (List<GroceryItem>) groceryItemRepository.findAll();
		return groceryItems;
	}

	public GroceryItem getGroceryItemById(Long id) {
		GroceryItem groceryItem = groceryItemRepository.findById(id).get();
		return groceryItem;
	}

	public GroceryItem getGroceryItemByName(String name) {
		GroceryItem groceryItem = groceryItemRepository.findGroceryItemByName(name);
		return groceryItem;
	}

	public void removeAllGroceryItem() {
		orderItemRepository.deleteAll();
		groceryOrderRepository.deleteAll();
		groceryItemRepository.deleteAll();
	}

	public void removeGroceryItem(Long itemId) {
		List<OrderItem> orderItems = orderItemRepository.findOrderItemByItemId(itemId);
		List<Long> orderIds = new ArrayList<>();
		if (orderItems != null && orderItems.size() > 0) {
			for (OrderItem orderItem : orderItems) {
				GroceryOrder groceryOrder = orderItem.getGroceryOrder();
				if (groceryOrder != null && groceryOrder.getId() != null && groceryOrder.getId() > 0) {
					Long orderId = groceryOrder.getId();
					orderIds.add(orderId);
				}
			}
			orderItemRepository.deleteOrderItemByItemId(itemId);

			if (orderIds != null && orderIds.size() > 0) {
				for (Long orderId : orderIds) {
					orderItems = orderItemRepository.findOrderItemByOrderId(orderId);
					if (orderItems != null && orderItems.size() > 0) {

					} else {
						groceryOrderRepository.deleteById(orderId);
					}
				}
			}
		}
		groceryItemRepository.deleteById(itemId);
	}

	public UpdateInventoryResponse updateInventory(UpdateInventoryRequest updateInventoryRequest) {
		UpdateInventoryResponse updateInventoryResponse = null;
		if (updateInventoryRequest != null) {
			Long itemId = updateInventoryRequest.getItemId();
			Long inventory = updateInventoryRequest.getInventory();
			Optional<GroceryItem> groceryItemOpt = groceryItemRepository.findById(itemId);
			if (groceryItemOpt.isPresent()) {
				GroceryItem groceryItem = groceryItemOpt.get();
				groceryItem.setInventory(inventory);
				groceryItem = groceryItemRepository.save(groceryItem);

				updateInventoryResponse = new UpdateInventoryResponse();
				updateInventoryResponse.setItemId(groceryItem.getId());
				updateInventoryResponse.setNewInventory(groceryItem.getInventory());
				updateInventoryResponse.setResponseMessage("Inventory updated successfully.");
			}
		}
		return updateInventoryResponse;
	}

}
