package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import resources.Utils;
import support.People;
import support.Results;

public class Search extends Utils {
	private Response response;

	@Given("user calls {string} with {string} as search term")
	public void user_calls_with_as_search_term(String people, String searchTerm) {
		response = given().spec(requestSpecification()).queryParam("search", searchTerm).when().get(people).then()
				.extract().response();
	}

	@Then("api response got success with a status code {int}")
	public void api_response_got_success_with_a_status_code(int code) {
		verifyStatusCode(response, code);
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
