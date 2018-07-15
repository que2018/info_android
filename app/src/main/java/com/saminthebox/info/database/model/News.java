package com.saminthebox.info.database.model;

public class News {

	private String title;
	private String imageUrl;
	private String description;
	
	public String getTitle() {
		return this.title;
	}
	
	public String getImageUrl() {
		return this.imageUrl;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setImageUrl(String imgUrl) {
		this.imageUrl = imgUrl;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}

