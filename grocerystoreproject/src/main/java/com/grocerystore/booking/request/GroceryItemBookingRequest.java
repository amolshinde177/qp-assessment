package com.grocerystore.booking.request;

import java.util.List;

public class GroceryItemBookingRequest {

	private Long userId;
	private List<BookedItemDetails> bookedItemDetails;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<BookedItemDetails> getBookedItemDetails() {
		return bookedItemDetails;
	}

	public void setBookedItemDetails(List<BookedItemDetails> bookedItemDetails) {
		this.bookedItemDetails = bookedItemDetails;
	}

}
