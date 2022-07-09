package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void deletePlaceAPI() throws IOException {
		
		System.out.println("Before hook for DeletePlace tag");
		StepDefinition m = new StepDefinition();
		m.request_payload_for_add_place_api_with("Jitesh", "English", "AddressTest");
		m.user_calls_the_with_method("AddPlaceAPI", "POST");
		m.verify_place_id_created_is_equal_to_using("Jitesh", "getPlaceAPI");
		
		
	}
	
	@After("@DeletePlace")
	public void deletePlaceAPI2() {
		
		System.out.println("After hook for DeletePlace tag");
		
		
	}

}
