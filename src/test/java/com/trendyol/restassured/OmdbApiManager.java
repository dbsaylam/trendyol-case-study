package com.trendyol.restassured;

import static io.restassured.RestAssured.given;

import java.util.List;

import com.trendyol.restassured.model.Movie;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class OmdbApiManager {

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
	
	public List<Movie> deserializeJsonContent(String searchQuery) {
		
		Response response = sendGetRequestWithQueryParam(searchQuery);
		JsonPath path = response.jsonPath();
		List<Movie> movies = path.getList("Search", Movie.class);
		
		return movies;

	}
	
	public String getMovieIdFromMappedJsonContent() {
		List<Movie> movies = deserializeJsonContent(Constants.SEARCH_QUERY);
		
		String id = "";
		for(int i = 0; i < movies.size(); i++) {
			if(movies.get(i).getTitle().equals(Constants.MOVIE_TO_FIND)) {
				id = movies.get(i).getId();
				break;
			}
		}
		return id;

	}
}