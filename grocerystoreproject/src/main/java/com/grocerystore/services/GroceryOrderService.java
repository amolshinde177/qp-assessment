package com.grocerystore.services;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.grocerystore.booking.request.BookedItemDetails;
import com.grocerystore.booking.request.GroceryItemBookingRequest;
import com.grocerystore.booking.response.GroceryItemBookingResponse;
import com.grocerystore.entities.GroceryItem;
import com.grocerystore.entities.GroceryOrder;
import com.grocerystore.entities.OrderItem;
import com.grocerystore.repositories.GroceryItemRepository;
import com.grocerystore.repositories.GroceryOrderRepository;

@Component
public class GroceryOrderService {

	@Autowired
	GroceryOrderRepository groceryOrderRepository;

	@Autowired
	GroceryItemRepository groceryItemRepository;

	@Autowired
	OrderItemService orderItemService;

	public GroceryOrder saveGroceryOrder(GroceryOrder groceryOrder) {
		groceryOrder = groceryOrderRepository.save(groceryOrder);
		return groceryOrder;
	}

	public GroceryOrder createGroceryOrderObject(Long userId, Double totalPrice) {
		GroceryOrder groceryOrder = new GroceryOrder();
		groceryOrder.setUserId(userId);
		groceryOrder.setTotalPrice(totalPrice);
		groceryOrder.setCreatedAt(ZonedDateTime.now());
		return groceryOrder;
	}

	public GroceryItemBookingResponse bookGroceryItems(@RequestBody GroceryItemBookingRequest groceryItemBookingRequest) {
		Long userId = groceryItemBookingRequest.getUserId();
		List<BookedItemDetails> bookedItemDetails = groceryItemBookingRequest.getBookedItemDetails();
		List<OrderItem> orderItems = new ArrayList<>();
		Double totalPrice = 0d;
		for (BookedItemDetails bookedItem : bookedItemDetails) {
			Long itemId = bookedItem.getItemId();
			Long quantity = bookedItem.getQuantity();

			GroceryItem groceryItem = groceryItemRepository.findById(itemId).get();
			Double price = groceryItem.getPrice();
			totalPrice = quantity * price + totalPrice;

			OrderItem orderItem = new OrderItem();
			orderItem.setGroceryItem(groceryItem);
			orderItem.setPrice(price);
			orderItem.setQuantity(quantity);
			orderItems.add(orderItem);
		}

		GroceryOrder groceryOrder = createGroceryOrderObject(userId, totalPrice);
		groceryOrder = saveGroceryOrder(groceryOrder);

		for (OrderItem orderItem : orderItems) {
			orderItem.setGroceryOrder(groceryOrder);
			orderItem = orderItemService.addOrUpdateOrderItem(orderItem);
		}

		GroceryItemBookingResponse groceryItemBookingResponse = createGroceryItemBookingResponse(groceryOrder.getId(), totalPrice, orderItems);
		return groceryItemBookingResponse;
	}

	public GroceryItemBookingResponse createGroceryItemBookingResponse(Long orderId, Double totalPrice, List<OrderItem> orderItems) {
		GroceryItemBookingResponse groceryItemBookingResponse = new GroceryItemBookingResponse();
		List<BookedItemDetails> bookedItemDetailsList = new ArrayList<>();
		groceryItemBookingResponse.setOrderId(orderId);
		groceryItemBookingResponse.setTotalPrice(totalPrice);

		for (OrderItem orderItem : orderItems) {
			BookedItemDetails bookedItemDetails = new BookedItemDetails();
			bookedItemDetails.setItemId(orderItem.getId());
			bookedItemDetails.setQuantity(orderItem.getQuantity());
			bookedItemDetailsList.add(bookedItemDetails);
		}
		groceryItemBookingResponse.setBookedItemDetails(bookedItemDetailsList);
		return groceryItemBookingResponse;
	}

}
