package stepdefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	//adding this so that when the @Deleteplace is run alone
	@Before(" @DeletePlace ")
	public void beforeScenario() throws IOException
	{
		//write a code that will give you place id
		//execute this code only place id is null
 if ( stepDefinition.Place_ID==null) {
	 stepDefinition m=new stepDefinition();
	 m.add_Place_Payload_with("shetty", "hindi", "India");
	 m.user_calls_with_http_request("AddPlaceAPI", "POST");
	 m.verify_place_Id_created_maps_to_using("shetty", "getPlaceAPI");
 }
}
}
