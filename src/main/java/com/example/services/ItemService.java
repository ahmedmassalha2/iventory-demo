package com.example.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Item;
import com.example.repository.itrepo;

@Service

public class ItemService {

	@Autowired(required = true)
	itrepo itRepo;

	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<Item>();
		itRepo.findAll().forEach(item -> items.add(item));
		return items;
	}

	public Item getSItemById(Long id) {
		// TODO Auto-generated method stub
		if (itRepo.existsById(id))
			return itRepo.findById(id).get();
		return null;
	}

	public String delete(Long id) {
		if (itRepo.existsById(id)) {
			itRepo.deleteById(id);
			return "deleted successfuly";
		}
		return "Item not available in stock";

	}

	public String add(String name, int amount, String code) {
		Item newItem = new Item(name, amount, code);
		itRepo.save(newItem);
		return "Item added to stock successfully:\n" + newItem.toString();
	}

	public String aaddQuantitiy(Long id, int amount) {
		Item item = getSItemById(id);
		if (item != null) {
			item.setAmount(item.getAmount() + amount);
			itRepo.save(item);
			return item.toString();
		}
		return "Item not available in stock";
	}

	public String withdrawalQuantity(Long id, int amount) {
		Item item = getSItemById(id);
		if (item != null) {
			int newAmount = item.getAmount() - amount;
			if (newAmount < 0) {
				return "Item can't have negative amount!!\n" + item.toString();
			}
			item.setAmount(newAmount);
			itRepo.save(item);
			return item.toString();
		}
		return "Item not available in stock";
	}

	public String withdrawalQuantityDellIf(Long id, int amount) {
		Item item = getSItemById(id);
		if (item != null) {
			int newAmount = item.getAmount() - amount;
			if (newAmount < 0) {
				return "Item can't have negative amount!!\n" + item.toString();
			}
			if (newAmount == 0) {
				itRepo.delete(item);
				return "Item with name: " + item.getName() + ", and Id: " + item.getItemID()
						+ " was withdrawal and deleted";
			}
			item.setAmount(newAmount);
			itRepo.save(item);
			return item.toString();
		}
		return "Item not available in stock";
	}

}
