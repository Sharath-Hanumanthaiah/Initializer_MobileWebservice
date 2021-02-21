package com.initializers.api.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "item_details")
public class ItemDetails {

	
	@Transient
    public static final String SEQUENCE_NAME = "item_details";
	
	private String id;
	@Id
	@Field("id")
	private Long previousApiId;
	private Long categoryId;
	private Long subCategoryId;
	private String name;
	private ItemDescription description;
	private List<String> imageLinks;
	private String status;
	@Transient
	private List<ItemAvailability> itemAvailability;
	@Transient
	private String imageLink;
	@Transient
	private Boolean isWishlist;
	@Transient
	private Float averageRating;
	
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
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ItemDescription getDescription() {
		return description;
	}
	public void setDescription(ItemDescription description) {
		this.description = description;
	}
	public List<String> getImageLinks() {
		return imageLinks;
	}
	public void setImageLinks(List<String> imageLinks) {
		this.imageLinks = imageLinks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ItemAvailability> getItemAvailability() {
		return itemAvailability;
	}
	public void setItemAvailability(List<ItemAvailability> itemAvailability) {
		this.itemAvailability = itemAvailability;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public Boolean getIsWishlist() {
		return isWishlist;
	}
	public void setIsWishlist(Boolean isWishlist) {
		this.isWishlist = isWishlist;
	}
	public Float getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}
}
