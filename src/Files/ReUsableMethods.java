package Files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

	public static JsonPath rawToJson(String response)
	{
		JsonPath jsp = new JsonPath(response);
		return jsp;
	}
}
