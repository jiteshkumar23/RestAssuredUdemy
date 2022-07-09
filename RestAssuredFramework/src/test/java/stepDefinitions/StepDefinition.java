package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;



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
import junit.framework.Assert;
import pojoClasses.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import pojoClasses.AddPlace;


public class StepDefinition extends Utils {
	
	
	ResponseSpecification res;
	RequestSpecification request;
	Response response;
	static String place_id ;
	
	@Given("request payload for add place API with {string} {string} {string}")
	public void request_payload_for_add_place_api_with(String name, String language, String address) throws IOException {
		//System.out.println("Hello");
		
		
				TestDataBuild obj = new TestDataBuild();
				
				//till body is Request
				 request =	given().spec(requestBuilder()).body(obj.addPlacePayload(name,language,address));
	}
	
	

	@When("user calls the {string} with {string} method")
	public void user_calls_the_with_method(String resource, String method) {
		
		//this calls constructor of enum call - APIResources.valueOf(resource)
		APIResources resourceAPI = APIResources.valueOf(resource)  ;
		
		//res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 
		if(method.equalsIgnoreCase("post")) {
		 response = request.when().post(resourceAPI.getResource());

		}
		else if(method.equalsIgnoreCase("get"))
			 response = request.when().get(resourceAPI.getResource());
		
		else if(method.equalsIgnoreCase("delete"))
			 response = request.when().delete(resourceAPI.getResource());
		
	}

	
	@Then("success response is received with status code {int}")
	public void success_response_is_received_with_status_code(Integer int1) {
		
		Integer statusfromResponse = response.getStatusCode();
		Assert.assertEquals(statusfromResponse, int1);
	  
	}

	@Then("{string} in response is {string}")
	public void in_response_is(String key, String ExpectedValue) {
	
	Assert.assertEquals(getJSONPath(response, key), ExpectedValue);
		
	    
	}
	
	
	@Then("verify place_id created is equal to {string} using {string}")
	public void verify_place_id_created_is_equal_to_using(String expectedName, String resource) throws IOException {
	    
       place_id = getJSONPath(response, "place_id");
      //adding place_id in the get request, creat
      
      request =	given().spec(requestBuilder()).queryParam("place_id", place_id);
      
      user_calls_the_with_method(resource, "GET");
      String actualName = getJSONPath(response, "name");
      Assert.assertEquals( actualName ,expectedName);
      
	}
	
	@Given("DeletePlaceAPI payload")
	public void delete_place_api_payload() throws IOException {
		
		TestDataBuild obj = new TestDataBuild();
		 request =	given().spec(requestBuilder()).body(obj.getDeletePlaceAPIBody(place_id));
	    
	}
	
}
