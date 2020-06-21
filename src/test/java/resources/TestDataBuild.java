package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address) {
		
        AddPlace addPlace = new AddPlace();
	    
	    Location location = new Location();
	    
	    List <String> myList = new ArrayList<String>();
	    
	    addPlace.setAccuracy(50);
	    //addPlace.setAddress("29, side layout, cohen 09");
	    addPlace.setAddress(address);
	    
	    //addPlace.setLanguage("French-IN");
	    addPlace.setLanguage(language);
	    
	    //addPlace.setName("Frontline house");
	    addPlace.setName(name);
	    
	    addPlace.setPhone_number("(+91) 983 893 3937");
	    addPlace.setWebsite("http://google.com");
	    
	    location.setLat(-38.383494);
	    location.setLng(33.427362);
	    addPlace.setLocation(location);
	    
	    myList.add("shoe park");
	    myList.add("shop");
	    
	    addPlace.setTypes(myList);
	    
	    return addPlace;
	}
	
	public String deletePlacePayload(String place_id)
	{
		return "{\r\n    \"place_id\":\"" + place_id + "\"\r\n}";
	}

}
