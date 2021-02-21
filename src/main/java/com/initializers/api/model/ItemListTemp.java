package com.initializers.api.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class ItemListTemp {

	@Id
	private Long id;
	private String name;
	private String imageLink;
	private List<ItemAvailability> itemAvailability;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public List<ItemAvailability> getItemAvailability() {
		return itemAvailability;
	}
	public void setItemAvailability(List<ItemAvailability> itemAvailability) {
		this.itemAvailability = itemAvailability;
	}
}
