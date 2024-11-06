package com.grocerystore.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.grocerystore.entities.GroceryItem;

public interface GroceryItemRepository extends CrudRepository<GroceryItem, Long> {

	@Query(value = "select * from grocery_items gi where LOWER(gi.name) = LOWER(:name)", nativeQuery = true)
	public GroceryItem findGroceryItemByName(@Param("name") String name);

}
