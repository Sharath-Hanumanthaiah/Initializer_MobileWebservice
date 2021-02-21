package com.initializers.api.model.output;

import java.time.LocalDate;

public class CreateOrderOutput {
	private Long orderId;
	private LocalDate deliveredBy;
	private Float totalAmount;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public LocalDate getDeliveredBy() {
		return deliveredBy;
	}

	public void setDeliveredBy(LocalDate deliveredBy) {
		this.deliveredBy = deliveredBy;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}
}
