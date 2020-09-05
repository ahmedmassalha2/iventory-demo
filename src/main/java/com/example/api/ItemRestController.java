package com.example.api;

import java.util.List;
import java.util.zip.CheckedInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Item;
import com.example.services.ItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Inventory stock", description = "Operations on stock")
@CrossOrigin(origins = "http://localhost:4200")
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

	@ApiOperation(value = "Search Item with ID", response = Object.class)
	@GetMapping("/item/{id}")
	private Object getItem(@PathVariable("id") String id) {

		return itemService.getSItemById(Long.parseLong(id));
	}

	// delete item from stock
	@DeleteMapping("/item/delete/{id}")
	@ApiOperation(value = "Delete an item with ID", response = Object.class)
	private Object deleteItem(@PathVariable("id") String id) {

		return itemService.delete(Long.parseLong(id));
	}

	// add item to stock
	@ApiOperation(value = "Add an Item object", response = Object.class)
	@RequestMapping(value = "/item/addStock", method = RequestMethod.POST)

	private Object addItem(@RequestBody Item item, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return ("error");
		return itemService.add(item.getName(), (item.getAmount()), item.getInventCode());

	}

	@ApiOperation(value = "deposit Quantity X for item with ID\"", response = Object.class)
	@RequestMapping(value = "/item/depositQuantity", method = RequestMethod.POST)

	private Object addQuantitiy(@RequestBody Item item, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "error";
		return itemService.addQuant(item);

	}

	// remove quantity from specific item
	@PostMapping("/item/withdrawalQuantity")
	@ApiOperation(value = "withdrawal Quantity X for item with ID", response = Object.class)
	private Object withdrawalQuantity(@RequestBody Item item, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return ("error");
		return itemService.withdrawalQuantity(item);

	}

}
