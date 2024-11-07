package com.grocerystore.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.grocerystore.booking.request.GroceryItemBookingRequest;
import com.grocerystore.booking.response.GroceryItemBookingResponse;
import com.grocerystore.entities.GroceryItem;
import com.grocerystore.services.GroceryItemService;
import com.grocerystore.services.GroceryOrderService;

@RestController
public class UserController {

	@Autowired
	GroceryItemService groceryItemService;

	@Autowired
	GroceryOrderService groceryOrderService;

	@GetMapping("/api/user/grocery")
	public ResponseEntity<?> getGroceryItem(@RequestParam(required = false) Long itemId, @RequestParam(required = false) String name) {
		try {
			GroceryItem groceryItem = null;
			List<GroceryItem> groceryItems = new ArrayList<>();
			if (itemId != null) {
				groceryItem = groceryItemService.getGroceryItemById(itemId);
				groceryItems.add(groceryItem);
			} else if (name != null) {
				groceryItem = groceryItemService.getGroceryItemByName(name);
				groceryItems.add(groceryItem);
			} else {
				groceryItems = groceryItemService.getAllGroceryItem();
			}
			if (groceryItems != null && groceryItems.size() > 0) {
				return ResponseEntity.of(Optional.of(groceryItems));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@PostMapping("/api/user/grocery/order")
	public ResponseEntity<GroceryItemBookingResponse> bookGroceryItems(@RequestBody GroceryItemBookingRequest groceryItemBookingRequest) {
		GroceryItemBookingResponse groceryItemBookingResponse = null;
		try {
			groceryItemBookingResponse = groceryOrderService.bookGroceryItems(groceryItemBookingRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(groceryItemBookingResponse);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
