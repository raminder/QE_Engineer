package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import resources.Utils;
import support.Error;
import support.People;
import support.Results;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import config.Configuration;

public class GetStarCharacters {
	private Response response;

	@Given("user calls people api with {string} get request")
	public void user_calls_people_api_with_get_request(String requestType) {
		if (requestType.equalsIgnoreCase("default")) {
			response = given().spec(Utils.requestSpecification()).when().get(Configuration.getPeopleUri()).then()
					.extract().response();
		} else if (requestType.equalsIgnoreCase("valid")) {
			response = given().spec(Utils.requestSpecification()).when().get(Configuration.getPeopleUri() + "2/").then()
					.extract().response();
		} else {
			response = given().spec(Utils.requestSpecification()).when().get(Configuration.getPeopleUri() + "00/")
					.then().extract().response();
		}
	}

	@Then("api response got success with status code {int}")
	public void api_response_got_success_with_status_code(int code) {
		Utils.verifyStatusCode(response, code);
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
			Error noResult = response.as(Error.class);
			assertEquals(noResult.getDetail(), "Not found");
		}
	}

}
