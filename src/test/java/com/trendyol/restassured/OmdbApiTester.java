package com.trendyol.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;

public class OmdbApiTester {

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = Constants.OMDB_URI;
	}
	
	@Test
	public void validate_movie_and_check_status_code() {
		OmdbApiManager omdbApiManager = new OmdbApiManager();
		String id = omdbApiManager.getMovieIdByTitleFromSearchQueryResponse(Constants.SEARCH_QUERY, Constants.MOVIE_TO_FIND);
		
		given()
			.contentType(ContentType.JSON)
			.param("apikey", Constants.API_KEY)
			.param("i", id)
			.when()
			.get("/")
			.then().assertThat()
			.statusCode(Constants.SUCCESS_CODE).and()
			.body("Title", not(emptyOrNullString())).and()
			.body("Year", not(emptyOrNullString())).and()
			.body("Released", not(emptyOrNullString()));		

	}
}