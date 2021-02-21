package com.initializers.api.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class UserCartList {
	private String id;
	@Id
	@Field("id")
	private UserCart.CompositeKeyCart previousApiId;
	private Long quantity;
	private Float discountPrice;
	private Float value; 
	private String unit;
	private String itemName;
	private ArrayList<Object> imageLinks;
	private String imageLink;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UserCart.CompositeKeyCart getPreviousApiId() {
		return previousApiId;
	}
	public void setPreviousApiId(UserCart.CompositeKeyCart previousApiId) {
		this.previousApiId = previousApiId;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Float getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(Float discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public ArrayList<Object> getImageLinks() {
		return imageLinks;
	}
	public void setImageLinks(ArrayList<Object> imageLinks) {
		this.imageLinks = imageLinks;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
}
