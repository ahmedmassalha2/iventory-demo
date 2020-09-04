package com.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long itemID;

	private String name;
	private int amount;
	private String inventCode;

	public long getItemID() {
		return itemID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getInventCode() {
		return inventCode;
	}

	public void setInventCode(String inventCode) {
		this.inventCode = inventCode;
	}

	public Item(String name, int amount, String inventCode) {
		super();
		this.name = name;
		this.amount = amount;
		this.inventCode = inventCode;
	}

	public Item() {
		this.name = name;
		this.amount = 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Item discpription:\n" + "Item id = " + itemID + "\nItem name = " + name + "\nItem amount = " + amount
				+ "\nItem inventory code = " + inventCode;
	}

}
