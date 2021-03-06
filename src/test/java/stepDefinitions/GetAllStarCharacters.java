package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import resources.Utils;
import support.People;
import support.Results;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GetAllStarCharacters extends Utils {
	private Response response;

	@Given("user calls {string} with get request")
	public void user_calls_with_get_request(String people) {
		response = given().spec(requestSpecification()).when().get(people).then().extract().response();
//	    RestAssured.baseURI = "https://swapi.dev/api";
//	    response = given().when().get(people).then().extract().response();
	}

	@Then("api response got success with status code {int}")
	public void api_response_got_success_with_status_code(int code) {
		assertEquals(response.getStatusCode(), code);
	}

	@Then("user should see {int} star war character in response")
	public void user_should_see_star_war_character_in_response(int characterCount) {
		if (characterCount == 1) {
			Results result = response.as(Results.class);
			assertEquals(result.getName(), "C-3PO");
			assertEquals(result.getBirth_year(), "112BBY");
		} else {
			People people = response.as(People.class);
			assertEquals(people.getCount(), 82);
			assertEquals(people.getResults().size(), characterCount);
			assertFalse(people.getResults().get(0).getName().isEmpty());
		}
	}

}
