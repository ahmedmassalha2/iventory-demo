package com.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api(description = "Class representing an Item in the application.")
@Entity
@Table(name = "item")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generated product ID", position = 1)
	private long itemID;

	@ApiModelProperty(notes = "Item name", position = 2)
	private String name;

	@ApiModelProperty(notes = "Item amount", position = 3)
	private long amount;

	@ApiModelProperty(notes = "Item inventory code", position = 4)
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

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getInventCode() {
		return inventCode;
	}

	public void setInventCode(String inventCode) {
		this.inventCode = inventCode;
	}

	public Item(String name, long amount, String inventCode) {
		super();
		this.name = name;
		this.amount = amount;
		this.inventCode = inventCode;
	}

	public Item() {
		this.name = "";
		this.amount = 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Item discpription:\n" + "Item id = " + itemID + "\nItem name = " + name + "\nItem amount = " + amount
				+ "\nItem inventory code = " + inventCode;
	}

}
