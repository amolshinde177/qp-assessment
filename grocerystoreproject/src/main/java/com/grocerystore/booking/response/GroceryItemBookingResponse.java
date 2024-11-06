package com.grocerystore.booking.response;

import java.util.List;

import com.grocerystore.booking.request.BookedItemDetails;

public class GroceryItemBookingResponse {

	private Long orderId;
	private Double totalPrice;
	private List<BookedItemDetails> bookedItemDetails;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<BookedItemDetails> getBookedItemDetails() {
		return bookedItemDetails;
	}

	public void setBookedItemDetails(List<BookedItemDetails> bookedItemDetails) {
		this.bookedItemDetails = bookedItemDetails;
	}

}
