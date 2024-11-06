package com.grocerystore.booking.response;

public class UpdateInventoryResponse {

	private Long itemId;
	private Long newInventory;
	private String responseMessage;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getNewInventory() {
		return newInventory;
	}

	public void setNewInventory(Long newInventory) {
		this.newInventory = newInventory;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
