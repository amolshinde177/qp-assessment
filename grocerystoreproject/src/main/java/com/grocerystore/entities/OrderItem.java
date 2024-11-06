package com.grocerystore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "grocery_order_id", nullable = false)
	private GroceryOrder groceryOrder;

	@ManyToOne
	@JoinColumn(name = "grocery_item_id", nullable = false)
	private GroceryItem groceryItem;

	@Column(nullable = false)
	private Long quantity;
	
	@Column(nullable = false)
	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GroceryOrder getGroceryOrder() {
		return groceryOrder;
	}

	public void setGroceryOrder(GroceryOrder groceryOrder) {
		this.groceryOrder = groceryOrder;
	}

	public GroceryItem getGroceryItem() {
		return groceryItem;
	}

	public void setGroceryItem(GroceryItem groceryItem) {
		this.groceryItem = groceryItem;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
