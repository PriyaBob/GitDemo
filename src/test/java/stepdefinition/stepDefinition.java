package stepdefinition;


import static io.restassured.RestAssured.given;

//import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;

import static org.junit.Assert.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.RestAssured;
//import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
//import io.restassured.filter.log.RequestLoggingFilter;
//import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
//import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinition extends Utils {
	// import the utils from resources
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String Place_ID;
	//object TestDataBuild is created and data is used in body
	TestDataBuild data=new TestDataBuild();
	
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
	  
		 
		 res= given().spec(requestSpecfication()).body(data.addPlacePayLoad(name,language,address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    
		
		//resource is ADD/Delete/getplaceAPI and method is POST, GET
		APIResources resourceAPI= APIResources.valueOf(resource);
			System.out.println(resourceAPI.getResources());
			resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	    
		
		 //.then().spec(resspec).extract().response();
			
			
		if (method.equalsIgnoreCase("POST"))
		{
			 response =res.when().post(resourceAPI.getResources());
			 //response will have post output
		}
		else if (method.equalsIgnoreCase("GET"))
		{
			 response =res.when().get(resourceAPI.getResources());
			 //response will have get output
		}
	}
	
	

	@Then("The API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
	    assertEquals(response.getStatusCode(),200);
	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
		
		//response,keyvalue is response extracted from sending post request and 
		assertEquals(getJsonPath(response,keyValue),Expectedvalue);
	}
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedname, String resource) throws IOException {
	   
		//response will have place id from post output
        Place_ID=getJsonPath(response,"place_id");
		res= given().spec(requestSpecfication()).queryParam("place_id", Place_ID);
        user_calls_with_http_request(resource,"GET"); 
        //we called get method so we should compare the name, so get the name from GET output
        String actualname=getJsonPath(response,"name");
        
        assertEquals(actualname,expectedname);
        		
	}
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		//making Place_ID static u can use the place id throughout the project
		res= given().spec(requestSpecfication()).body(data.deleteplacePayload(Place_ID)); 
	}




}
