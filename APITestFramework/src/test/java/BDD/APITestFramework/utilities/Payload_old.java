package BDD.APITestFramework.utilities;

public class Payload_old {
	
	public static String getPostPayload() {
		return "{"+
			  "\"location\": {"+
			    "\"lat\": -33.8669710,"+
			    "\"lng\": 151.1958750"+
			  "},"+
			  "\"accuracy\": 50,"+
			  "\"name\": \"Google Shoes!\","+
			  "\"phone_number\": \"(02) 9374 4000\","+
			  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
			  "\"types\": [\"shoe_store\"],"+
			  "\"website\": \"http://www.google.com.au/\","+
			  "\"language\": \"en-AU\""+
			"}";
	}
	
	public static String getPutPayload() {
		return "{"+
			  "\"accuracy\": 50,"+
			  "\"place_id\": \"Google Shoes!\","+
			  "\"phone_number\": \"(02) 9374 4000\","+
			  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
			  "\"types\": [\"shoe_store\"],"+
			  "\"key\": \"qaclick123\"\r\n"+
			"}";
	}
	
	public static String getDeletePayload(String placeId) {
		return "{"+
			  "\"place_id\": \""+placeId+"\""+
			  "}";
	}
	
	public static String getCoursePriceResponse(){ 
		return "{\r\n" + 
				"  \"dashboard\": {\r\n" + 
				"    \"purchaseAmount\": 1162,\r\n" + 
				"    \"website\": \"rahulshettyacademy.com\"\r\n" + 
				"  },\r\n" + 
				"  \"courses\": [\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Selenium Python\",\r\n" + 
				"      \"price\": 50,\r\n" + 
				"      \"copies\": 6\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Cypress\",\r\n" + 
				"      \"price\": 40,\r\n" + 
				"      \"copies\": 4\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"RPA\",\r\n" + 
				"      \"price\": 45,\r\n" + 
				"      \"copies\": 10\r\n" + 
				"    },\r\n" + 
				"     {\r\n" + 
				"      \"title\": \"Appium\",\r\n" + 
				"      \"price\": 36,\r\n" + 
				"      \"copies\": 7\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"  ]\r\n" + 
				"}\r\n" + 
				"";
	}
	
	public static String getAddBookPayload(String isbn, String aisle){
		return "{\r\n\r\n\"name\":\"Learn Appium Automation with Java\",\r\n\"isbn\":\"" + isbn + "\",\r\n\"aisle\":\"" + aisle + "\",\r\n\"author\":\"John foe\"\r\n}\r\n \r\n";
	}
	
}
