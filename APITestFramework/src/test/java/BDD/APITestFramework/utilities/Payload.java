package BDD.APITestFramework.utilities;

import java.util.Arrays;

import BDD.APITestFramework.POJO.AddPlace.AddPlaceBody;
import BDD.APITestFramework.POJO.AddPlace.Location;

public class Payload {
	
	public AddPlaceBody getAddPlaceBody(String name, String lang, String address){
		AddPlaceBody addPlaceBody = new AddPlaceBody();
		addPlaceBody.setAccuracy(90);
		addPlaceBody.setAddress(address);
		addPlaceBody.setLanguage(lang);
		addPlaceBody.setLocation(getLocation());
		addPlaceBody.setName(name);
		addPlaceBody.setPhone_number("8374874542");
		addPlaceBody.setTypes(Arrays.asList("shoe_store","books","pens","medicines"));
		addPlaceBody.setWebsite("myacademy.com");
		return addPlaceBody;
	}
	
	public Location getLocation(){
		Location location = new Location();
		location.setLat(-33.8655710);
		location.setLng(151.1911750);
		return location;
	}
	
	public String getDeletePlaceBody(String placeId){
		return "{"+
			  "\"place_id\": \"" + placeId + "\""+
			  "}";
	}
	
}
