package com.example.api;

import java.util.List;
import java.util.zip.CheckedInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Item;
import com.example.services.ItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Inventory stock", description = "Operations on stock")
public class ItemRestController {
	@Autowired(required = true)
	ItemService itemService;

	// need to check if input is long
	public boolean CheckedInput(String input) {
		try {
			Long.parseLong(input);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@ApiOperation(value = "View a list of available Items", response = Iterable.class)
	// get all items
	@GetMapping("/Items")
	public List<Item> getAllItems() {
		return itemService.getAllItems();
	}

	// get specific item from stock

	@ApiOperation(value = "Search Item with ID", response = Item.class)
	@GetMapping("/item/{id}")
	private Object getItem(@PathVariable("id") String id) {
		if (CheckedInput(id) == false)
			return ("ID must be number");
		return itemService.getSItemById(Long.parseLong(id));
	}

	// delete item from stock
	@DeleteMapping("/item/delete/{id}")
	@ApiOperation(value = "Delete an item with ID", response = String.class)
	private String deleteItem(@PathVariable("id") String id) {
		if (CheckedInput(id) == false)
			return ("ID must be number");
		return itemService.delete(Long.parseLong(id));
	}

	// add item to stock
	@ApiOperation(value = "Add an Item, name, amount , inventory code", response = String.class)
	@PostMapping(value = "/item/addStock/{name}/{amount}/{code}")

	private String addItem(@PathVariable("name") String name, @PathVariable("amount") String amount,
			@PathVariable("code") String code) {
		if (CheckedInput(amount) == false)
			return ("Amount must be number");
		return itemService.add(name, Long.parseLong(amount), code);

	}

	// add quantity to item
	@PostMapping("/item/depositQuantity/{id}/{amount}")
	@ApiOperation(value = "deposit Quantity X for item with ID", response = String.class)
	private String addQuantitiy(@PathVariable("id") String id, @PathVariable("amount") String amount) {
		if (CheckedInput(id) == false)
			return ("ID must be number");
		if (CheckedInput(amount) == false)
			return ("Amount must be number");
		return itemService.aaddQuantitiy(Long.parseLong(id), Long.parseLong(amount));
	}

	// remove quantity from specific item
	@PostMapping("/item/withdrawalQuantity/{id}/{amount}")
	@ApiOperation(value = "withdrawal Quantity X for item with ID", response = String.class)
	private String withdrawalQuantity(@PathVariable("id") String id, @PathVariable("amount") String amount) {
		if (CheckedInput(id) == false)
			return ("ID must be number");
		if (CheckedInput(amount) == false)
			return ("Amount must be number");
		return itemService.withdrawalQuantity(Long.parseLong(id), Long.parseLong(amount));

	}

	// remove quantity from specific item and delete it if becomes empty
	@PostMapping("/item/withdrawalQuantityDellIf/{id}/{amount}")
	@ApiOperation(value = "withdrawal Quantity X for item with ID, Delete item if amount is 0", response = String.class)
	private String withdrawalQuantityDellIf(@PathVariable("id") String id, @PathVariable("amount") String amount) {
		if (CheckedInput(id) == false)
			return ("ID must be number");
		if (CheckedInput(amount) == false)
			return ("Amount must be number");
		return itemService.withdrawalQuantityDellIf(Long.parseLong(id), Long.parseLong(amount));

	}

}
