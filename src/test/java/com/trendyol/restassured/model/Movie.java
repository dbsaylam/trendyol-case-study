package com.trendyol.restassured.model;

import com.fasterxml.jackson.annotation.*;

public class Movie {
	@JsonProperty("Title")
	private String title;
	@JsonProperty("Year")
    private String year;
	@JsonProperty("imdbID")
    private String imdbID;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Poster")
    private String poster;
    
	public String getTitle() { return title; }
	
	public void setTitle(String title) { this.title = title; }
	
	public String getYear() { return year; }
	
	public void setYear(String year) { this.year = year; }
	
	public String getId() { return imdbID; }
	
	public void setId(String imdbID) { this.imdbID = imdbID; }
	
	public String getType() { return type; }
	
	public void setType(String type) { this.type = type; }
	
	public String getPoster() { return poster; }
	
	public void setPoster(String poster) { this.poster = poster; }
}
