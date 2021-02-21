package com.initializers.api.model;

public class HomePageContent {
	
	private String id;
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
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
}
