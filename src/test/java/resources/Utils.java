package resources;

import config.Configuration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

public class Utils {
	static RequestSpecification request;

	public static RequestSpecification requestSpecification() {
		request = new RequestSpecBuilder().setBaseUri(Configuration.getBaseUri()).build();
		return request;
	}

	public static boolean verifyStatusCode(Response response, int code) {
		if (response.getStatusCode() == code) {
			return true;
		} else {
			return false;
		}
	}

}
