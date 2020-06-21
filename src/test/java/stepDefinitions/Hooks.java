package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenarion() throws IOException 
	{
		// write a code that will give you place id
		// execute this code only when place id is null
		
		StepDefinition stepDefinition = new StepDefinition();
		
		if(stepDefinition.place_id == null) 
		{
			stepDefinition.add_place_Payload_with("nir", "hebrew", "dovnov");
			stepDefinition.user_calls_with_request("AddPlaceAPI", "POST");
			stepDefinition.verify_place_id_created_maps_to_using("nir", "getPlaceAPI");
		}
		
	}

}
