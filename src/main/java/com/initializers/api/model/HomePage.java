package com.initializers.api.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "homepage")
public class HomePage {
	
	@Transient
    public static final String SEQUENCE_NAME = "homepage";
	
	private String id;
	@Id
	@Field("id")
	private Long previousApiId;
	private String itemType; // ItemCategory or ItemSubCategory
	private Long typeId;
	private String widget;
	private String name;
	@Transient
	private List<HomePageContent> content;
	
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
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getWidget() {
		return widget;
	}
	public void setWidget(String widget) {
		this.widget = widget;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<HomePageContent> getContent() {
		return content;
	}
	public void setContent(List<HomePageContent> content) {
		this.content = content;
	}
}
