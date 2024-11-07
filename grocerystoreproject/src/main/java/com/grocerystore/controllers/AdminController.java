package com.grocerystore.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.grocerystore.booking.request.AddGroceryItemRequest;
import com.grocerystore.booking.request.UpdateInventoryRequest;
import com.grocerystore.booking.response.UpdateInventoryResponse;
import com.grocerystore.entities.GroceryItem;
import com.grocerystore.services.GroceryItemService;

@RestController
public class AdminController {

	@Autowired
	GroceryItemService groceryItemService;

	@PostMapping("/api/admin/grocery")
	public ResponseEntity<GroceryItem> addGroceryItem(@RequestBody AddGroceryItemRequest addGroceryItemRequest) {
		try {
			GroceryItem groceryItem = groceryItemService.addGroceryItem(addGroceryItemRequest);
			if (groceryItem != null && groceryItem.getId() != null && groceryItem.getId() > 0) {
				return ResponseEntity.status(HttpStatus.CREATED).body(groceryItem);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@GetMapping("/api/admin/grocery")
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

	@PutMapping("/api/admin/grocery/{item_id}")
	public ResponseEntity<GroceryItem> updateGroceryItem(@RequestBody GroceryItem groceryItem, @PathVariable("item_id") Long itemId) {
		GroceryItem updatedGroceryItem = null;
		try {
			updatedGroceryItem = groceryItemService.updateGroceryItem(groceryItem, itemId);
			if (updatedGroceryItem != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(updatedGroceryItem);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@DeleteMapping("/api/admin/grocery")
	public ResponseEntity<?> removeAllGroceryItem() {
		try {
			groceryItemService.removeAllGroceryItem();
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/api/admin/grocery/{item_id}")
	public ResponseEntity<?> removeGroceryItem(@PathVariable("item_id") Long itemId) {
		try {
			groceryItemService.removeGroceryItem(itemId);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@PatchMapping("/api/admin/grocery/inventory")
	public ResponseEntity<UpdateInventoryResponse> updateInventory(@RequestBody UpdateInventoryRequest updateInventoryRequest) {
		UpdateInventoryResponse updateInventoryResponse = null;
		try {
			updateInventoryResponse = groceryItemService.updateInventory(updateInventoryRequest);
			if (updateInventoryResponse != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(updateInventoryResponse);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
