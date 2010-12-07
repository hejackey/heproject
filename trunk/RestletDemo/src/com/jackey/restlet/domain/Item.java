package com.jackey.restlet.domain;

public class Item {
	private String name;
	private String description;
	
	public Item(String itemName){
		this.name = itemName;
	}
	
	public Item(String itemName,String desc){
		this.name = itemName;
		this.description = desc;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	} 
}
