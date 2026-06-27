package BasicsLearn;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.PayLoad;
import Files.ReUsableMethods;
public class Basics {
	public static void main(String[] args) 
	//Validate if Add place API is working as expected
	{
		
		//given method- all input details
		// when method - Submit the API
		//then method - validate the response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(PayLoad.Addplace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200)                      //--Removed .log().all() as we are extracting the response
		.body("scope", equalTo("APP"))                         //Validates the Body Scop
		.header("Server","Apache/2.4.52 (Ubuntu)")           // Validates Server
		.extract().response().asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
		
		
		//Add place -> Update place with new address -> Get place to Validates if new address is present in response
		// Update Place
		
	String getaddress ="70 Summer walk, USA";
			given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+getaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"))
		.extract().response().asString();
		
	//get place
	String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
	.when().get("/maps/api/place/get/json")
	.then().assertThat().statusCode(200)
    .extract().response().asString();
	
	JsonPath jsp =ReUsableMethods.rawToJson(getPlaceResponse);
	String actualAddress = jsp.get("address");
	System.out.println(actualAddress);
	Assert.assertEquals(actualAddress, getaddress);
	
	}

}
