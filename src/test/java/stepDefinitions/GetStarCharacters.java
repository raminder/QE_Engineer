package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import resources.Utils;
import support.NoResult;
import support.People;
import support.Results;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GetStarCharacters extends Utils {
	private Response response;

	@Given("user calls {string} with {string} get request")
	public void user_calls_with_get_request(String people, String requestType) {
		response = given().spec(requestSpecification()).when().get(people).then().extract().response();
	}

	@Then("api response got success with status code {int}")
	public void api_response_got_success_with_status_code(int code) {
		assertEquals(response.getStatusCode(), code);
	}

	@Then("user should see {string} star war character details")
	public void user_should_see_star_war_character_details(String resultCriteria) {
		if (resultCriteria.equalsIgnoreCase("specific")) {
			Results result = response.as(Results.class);
			assertFalse(result.getName().isEmpty());
			assertFalse(result.getBirth_year().isEmpty());
		} else if (resultCriteria.equalsIgnoreCase("all")) {
			People people = response.as(People.class);
			assertTrue(people.getResults().size() >= 0);
			assertFalse(people.getResults().isEmpty());
		} else {
			NoResult noResult = response.as(NoResult.class);
			assertEquals(noResult.getDetail(), "Not found");
		}
	}

}
