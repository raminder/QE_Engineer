package config;

public class Configuration {
	private static final String BASE_URI = "https://swapi.dev/api/";
	private static final String PEOPLE_URI = "people/";
	private static final String INVALID_PEOPLE_URI = "peoples/";
	private static final String PLANET_URI = "planets/";
	private static final String INVALID_PLANET_URI = "planet/";

	public static String getBaseUri() {
		return BASE_URI;
	}

	public static String getPeopleUri() {
		return PEOPLE_URI;
	}

	public static String getInvalidPeopleUri() {
		return INVALID_PEOPLE_URI;
	}

	public static String getPlanetUri() {
		return PLANET_URI;
	}

	public static String getInvalidPlanetUri() {
		return INVALID_PLANET_URI;
	}
}
