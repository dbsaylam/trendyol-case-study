package com.trendyol.restassured;

import static io.restassured.RestAssured.given;

import java.util.List;

import com.trendyol.restassured.model.Movie;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class OmdbApiManager {
	
	/*
	// We can use singleton design pattern if needed.
	private static OmdbApiManager instance = null;
	
	public static OmdbApiManager getInstance() {
		if(instance == null) {
			instance = new OmdbApiManager();
		}
		
		return instance;
	}
	*/

	private Response sendGetRequestWithQueryParam(String queryString) {
		Response response = given()
				.contentType(ContentType.JSON)
				.param("apikey", Constants.API_KEY)
				.param("s", queryString)
				.when()
				.get("/")
				.then()
				.extract().response();

		return response;
	}
	
	public String getMovieIdByTitleFromSearchQueryResponse(String searchQuery, String movieTitleToFind) {
		Response response = sendGetRequestWithQueryParam(searchQuery);

		JsonPath path = response.jsonPath();
		List<Movie> movies = path.getList("Search", Movie.class);

		String id = "";
		for(int i = 0; i < movies.size(); i++) {
			if(movies.get(i).getTitle().equals(movieTitleToFind)) {
				id = movies.get(i).getId();
				break;
			}
		}
		
		return id;
			
	}
	
	public String getPosterLinkByTitleFromSearchQueryResponse(String searchQuery, String movieTitleToFind) {
		Response response = sendGetRequestWithQueryParam(searchQuery);

		JsonPath path = response.jsonPath();
		List<Movie> movies = path.getList("Search", Movie.class);

		String link = "";
		for(int i = 0; i < movies.size(); i++) {
			if(movies.get(i).getTitle().equals(movieTitleToFind)) {
				link = movies.get(i).getPoster();
				break;
			}
		}
		return link;
			
	}

}
