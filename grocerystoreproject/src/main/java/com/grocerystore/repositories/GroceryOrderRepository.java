package com.grocerystore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.grocerystore.entities.GroceryOrder;

public interface GroceryOrderRepository extends CrudRepository<GroceryOrder, Long> {

}
