package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class StepDefinition extends Utils{
	
	RequestSpecification res;
	
	Response response;
	
	TestDataBuild data = new TestDataBuild();
	
	static String place_id;

	
	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_Payload_with(String name, String language, String address) throws IOException 
	{
	    res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
	}

	@When("User calls {string} with {string} request")
	public void user_calls_with_request(String resource, String APIMethod)
	{
		APIResources resourceAPI = APIResources.valueOf(resource);
		
		System.out.println(resourceAPI.getResource());
		
		if(APIMethod.equalsIgnoreCase("POST"))
		    response = res.when().post(resourceAPI.getResource()).then().spec(responseSpecification()).extract().response();
		else if (APIMethod.equalsIgnoreCase("GET")) 
			response = res.when().get(resourceAPI.getResource()).then().spec(responseSpecification()).extract().response();
	}
	
	@Then("API call is success with status code {int}")
	public void api_call_is_success_with_status_code(Integer int1) 
	{
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) 
	{
    assertEquals(getJsonPath(response, keyValue), expectedValue);
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String ExpectedName, String resource) throws IOException {
		
		place_id = getJsonPath(response, "place_id");
		 
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		
		user_calls_with_request(resource, "GET");
		
		String actualName = getJsonPath(response, "name");
		
		assertEquals(actualName, ExpectedName);
	}
	
	@Given("Delete Place payload")
	public void delete_Place_payload() throws IOException 
	{
	    res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}

}
