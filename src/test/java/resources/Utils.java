package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

public class Utils {
	RequestSpecification request;

	public RequestSpecification requestSpecification() {
		request = new RequestSpecBuilder().setBaseUri("https://swapi.dev/api/").build();
		return request;
	}

	public boolean verifyStatusCode(Response response, int code) {
		if (response.getStatusCode() == code) {
			return true;
		} else {
			return false;
		}
	}

}
