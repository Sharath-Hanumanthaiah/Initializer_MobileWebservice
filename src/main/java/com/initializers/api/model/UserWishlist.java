package com.initializers.api.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user_wishlist")
public class UserWishlist {
	
	@Id
	private Long userID;
	private Set<Long> itemsId;
	@Transient 
	private List<ItemDetails> itemDetails;
	
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Set<Long> getItemsId() {
		return itemsId;
	}
	public void setItemsId(Long itemsId) {
		if(this.itemsId == null) {
			this.itemsId = new HashSet<Long>();
		}
		this.itemsId.add(itemsId);
	}
	public List<ItemDetails> getItemDetails() {
		return itemDetails;
	}
	public void setItemDetails(List<ItemDetails> itemDetails) {
		this.itemDetails = itemDetails;
	}

}
