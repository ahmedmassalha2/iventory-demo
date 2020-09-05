package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

	public Object getSItemById(Long id) {
		// TODO Auto-generated method stub
		if (itRepo.existsById(id))
			return itRepo.findById(id).get();
		return "Item not available in stock";
	}

	public String delete(Long id) {
		if (itRepo.existsById(id)) {
			itRepo.deleteById(id);
			return "deleted successfuly";
		}
		return "Item not available in stock";

	}

	public Item add(String name, Long amount, String code) {
		Item newItem = new Item(name, amount, code);
		itRepo.save(newItem);
		return newItem;
	}

	public Object addQuant(Item item) {

		if (itRepo.existsById(item.getItemID())) {
			Item item1 = (Item) getSItemById(item.getItemID());
			item1.setAmount(item1.getAmount() + item.getAmount());
			itRepo.save(item1);
			return item1;
		}
		return "Item not available in stock";
	}

	public Object withdrawalQuantity(Item item) {

		if (itRepo.existsById(item.getItemID())) {
			Item item1 = (Item) getSItemById(item.getItemID());
			long newAmount = item1.getAmount() - item.getAmount();
			if (newAmount < 0) {
				return "Item can't have negative amount!!\n";
			}
			item1.setAmount(newAmount);
			itRepo.save(item1);
			return item1;
		}
		return "Item not available in stock";
	}

}
