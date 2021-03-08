package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Utils {
	RequestSpecification request;
	
	public RequestSpecification requestSpecification() {
		request = new RequestSpecBuilder().setBaseUri("https://swapi.dev/api/").build();
		return request;
	}

}
