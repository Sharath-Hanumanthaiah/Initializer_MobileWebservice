package com.initializers.api.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user_cart")
public class UserCart {

	@Transient
    public static final String SEQUENCE_NAME = "users_cart_sequence";
	
	private String id;
	@Id
	@Field("id")
	private CompositeKeyCart previousApiId;
	
	private Long quantity;
	
	@Transient 
	private Float discountPrice;
	@Transient 
	private Float value;
	@Transient 
	private String unit;
	@Transient
	private String itemName;
	
    public static class CompositeKeyCart implements Serializable {
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Long userId;
    	private Long itemId;
    	private Long availabilityId;
        
    	public Long getUserId() {
    		return userId;
    	}
    	public void setUserId(Long userId) {
    		this.userId = userId;
    	}
    	public Long getItemId() {
    		return itemId;
    	}
    	public void setItemId(Long itemId) {
    		this.itemId = itemId;
    	}
    	public Long getAvailabilityId() {
    		return availabilityId;
    	}
    	public void setAvailabilityId(Long availabilityId) {
    		this.availabilityId = availabilityId;
    	}
    }
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CompositeKeyCart getPreviousApiId() {
		return previousApiId;
	}
	public void setPreviousApiId(CompositeKeyCart previousApiId) {
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
	
	
}
