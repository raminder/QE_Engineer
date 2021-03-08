package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import config.Configuration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import resources.Utils;
import support.People;
import support.Results;

public class SearchResults {
	private Response response;

	@Given("user calls {string} {string} api with {string} as search term")
	public void user_calls_api_with_as_search_term(String requestType, String request, String searchTerm) {
		if (request.contains("people") && requestType.equalsIgnoreCase("valid")) {
			response = given().spec(Utils.requestSpecification()).queryParam("search", searchTerm).when()
					.get(Configuration.getPeopleUri()).then().extract().response();
		} else if (request.contains("people") && requestType.equalsIgnoreCase("invalid")) {
			response = given().spec(Utils.requestSpecification()).queryParam("search", searchTerm).when()
					.get(Configuration.getInvalidPeopleUri()).then().extract().response();
		} else if (request.contains("planet") && requestType.equalsIgnoreCase("valid")) {
			response = given().spec(Utils.requestSpecification()).queryParam("search", searchTerm).when()
					.get(Configuration.getPlanetUri()).then().extract().response();
		} else {
			response = given().spec(Utils.requestSpecification()).queryParam("search", searchTerm).when()
					.get(Configuration.getInvalidPlanetUri()).then().extract().response();
		}
	}

	@Then("api response got success with a status code {int}")
	public void api_response_got_success_with_a_status_code(int code) {
		Utils.verifyStatusCode(response, code);
	}

	@And("user should see {string} result containing {string} in name")
	public void user_should_see_result_containing_in_name(String resultsCount, String searchTerm) {
		People people = response.as(People.class);
		List<Results> results = people.getResults();
		if (resultsCount.equalsIgnoreCase("one")) {
			assertEquals(results.size(), 1);
			assertTrue(people.getResults().get(0).getName().contains(searchTerm));
		} else if (resultsCount.equalsIgnoreCase("many")) {
			assertTrue(results.size() > 1);
			for (int i = 0; i < results.size(); i++) {
				assertTrue(people.getResults().get(i).getName().toLowerCase().contains(searchTerm));
			}
		} else {
			assertTrue(results.isEmpty());
		}
	}
}
