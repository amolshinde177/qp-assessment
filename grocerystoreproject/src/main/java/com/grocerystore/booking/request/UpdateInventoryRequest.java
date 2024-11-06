package com.grocerystore.booking.request;

public class UpdateInventoryRequest {

	private Long itemId;
	private Long inventory;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getInventory() {
		return inventory;
	}

	public void setInventory(Long inventory) {
		this.inventory = inventory;
	}

}
