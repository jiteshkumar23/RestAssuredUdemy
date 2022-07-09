package resources;

import java.util.ArrayList;

import pojoClasses.AddPlace;
import pojoClasses.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address) {
		
		
        AddPlace addPlaceRequest = new AddPlace();
		
		Location test = new Location();
		test.setLat(-99.38349);
		test.setLng(-99.0000);
		
	    ArrayList<String> list = new ArrayList<String>();
	    list.add("shoe park");
	    list.add("shop");
		
		addPlaceRequest.setLocation(test);
		addPlaceRequest.setAccuracy(50);
		addPlaceRequest.setName(name);
		addPlaceRequest.setPhone_number("(+91) 983 893 3937");
		addPlaceRequest.setAddress(address);
		addPlaceRequest.setWebsite("http://google1.com");
		addPlaceRequest.setLanguage(language);
		
		addPlaceRequest.setTypes(list);
		
		
		
		return addPlaceRequest;
		
		
	}
	
	public String getDeletePlaceAPIBody(String place_id) {
		String requestbody= "{\n"
				+ "\n"
				+ "    \"place_id\":\""+place_id+"\"\n"
				+ "}\n"
				+ "";
		return requestbody;
	}

}
