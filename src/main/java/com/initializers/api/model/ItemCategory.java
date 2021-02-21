package com.initializers.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "item_category")
public class ItemCategory {
	
	@Transient
    public static final String SEQUENCE_NAME = "item_category_sequence";
 
	private String id;
	@Id
	@Field("id")
	private Long previousApiId;
	private String name;
	private String description;
	private Long offer;
	private String imageLink;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getOffer() {
		return offer;
	}
	public void setOffer(Long offer) {
		this.offer = offer;
	}
}
