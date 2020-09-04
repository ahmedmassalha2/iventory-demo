package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Item;
import com.example.services.ItemService;

@RestController
public class ItemRestController {
	@Autowired(required = true)
	ItemService itemService;
//need to check
	// get all items
	@GetMapping("/Items")
	public List<Item> getAllItems() {
		return itemService.getAllItems();
	}

	// get specific item from stock
	@GetMapping("/item/{id}")
	private Item getStudent(@PathVariable("id") Long id) {
		return itemService.getSItemById(id);
	}

	// delete item from stock
	@DeleteMapping("/item/delete/{id}")
	private String deleteItem(@PathVariable("id") Long id) {
		return itemService.delete(id);
	}

	// add item to stock
	@PostMapping(value = "/item/addStock/{name}/{amount}/{code}")
	private String addItem(@PathVariable("name") String name, @PathVariable("amount") int amount,
			@PathVariable("code") String code) {
		return itemService.add(name, amount, code);

	}

	// add quantity to item
	@PostMapping("/item/depositQuantity/{id}/{amount}")
	private String addQuantitiy(@PathVariable("id") Long id, @PathVariable("amount") int amount) {
		return itemService.aaddQuantitiy(id, amount);
	}

	// remove quantity from specific item
	@PostMapping("/item/withdrawalQuantity/{id}/{amount}")
	private String withdrawalQuantity(@PathVariable("id") Long id, @PathVariable("amount") int amount) {

		return itemService.withdrawalQuantity(id, amount);

	}

	// remove quantity from specific item and delete it if becomes empty
	@PostMapping("/item/withdrawalQuantityDellIf/{id}/{amount}")
	private String withdrawalQuantityDellIf(@PathVariable("id") Long id, @PathVariable("amount") int amount) {

		return itemService.withdrawalQuantityDellIf(id, amount);

	}

}
