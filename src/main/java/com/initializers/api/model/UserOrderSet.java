package com.initializers.api.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user-order")
public class UserOrderSet { 


	@Transient
    public static final String SEQUENCE_NAME = "users_order";
	private String id;
	@Id
	@Field("id")
	private Long previousApiId;
	private List<UserOrder> orderList;
	@Transient
	private String orderDetails;
	private Long addressId;
	@Transient
	private Address addressDetails;
	private OrderStatus status;
	private LocalDate orderAt;
	private LocalDate deliveredBy;
	@Transient
	private Float netAmount;
	private Float totalAmount;
	private Float deliveryCharge;
	private String coupenCode;
	private Float coupenDiscount;
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getPreviousApiId() {
		return previousApiId;
	}
	public void setPreviousApiId(Long previousApiId) {
		this.previousApiId = previousApiId;
	}
	public List<UserOrder> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<UserOrder> orderList) {
		this.orderList = orderList;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public Address getAddressDetails() {
		return addressDetails;
	}
	public void setAddressDetails(Address addressDetails) {
		this.addressDetails = addressDetails;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public LocalDate getOrderAt() {
		return orderAt;
	}
	public void setOrderAt(LocalDate orderAt) {
		this.orderAt = orderAt;
	}
	public LocalDate getDeliveredBy() {
		return deliveredBy;
	}
	public void setDeliveredBy(LocalDate deliveredBy) {
		this.deliveredBy = deliveredBy;
	}
	public Float getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(Float netAmount) {
		this.netAmount = netAmount;
	}
	public Float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Float getDeliveryCharge() {
		return deliveryCharge;
	}
	public void setDeliveryCharge(Float deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}
	public String getCoupenCode() {
		return coupenCode;
	}
	public void setCoupenCode(String coupenCode) {
		this.coupenCode = coupenCode;
	}
	public Float getCoupenDiscount() {
		return coupenDiscount;
	}
	public void setCoupenDiscount(Float coupenDiscount) {
		this.coupenDiscount = coupenDiscount;
	}
	public String getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
}
