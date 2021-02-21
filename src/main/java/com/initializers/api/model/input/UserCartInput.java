package com.initializers.api.model.input;

import com.initializers.api.model.UserCart;

public class UserCartInput {
	
	private UserCart.CompositeKeyCart previousApiId;
	
	private Long quantity;

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
	
	
}
