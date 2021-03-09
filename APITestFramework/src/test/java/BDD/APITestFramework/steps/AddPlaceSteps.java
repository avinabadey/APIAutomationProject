package BDD.APITestFramework.steps;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import BDD.APITestFramework.POJO.AddPlace.AddPlaceBody;
import BDD.APITestFramework.utilities.Payload;
import BDD.APITestFramework.utilities.RequestSpec;
import BDD.APITestFramework.utilities.ResponseSpec;
import BDD.APITestFramework.utilities.ResponseToJSON;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AddPlaceSteps {
	
	static Properties prop;
	Payload payload;
	static RequestSpecification requestSpecification;
	static ResponseSpecification responseSpecification;
	
	public Properties getSystemEnvDetails(){
		if(prop==null){
			prop = new Properties();
			FileInputStream fis = null;
			try {
				fis = new FileInputStream("C:\\Users\\avina\\OneDrive\\Projects\\RestAssuredWorkspace\\Practice\\src\\main\\java\\RestAssured\\Practice\\env.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;
	}
	
	public void createRequestSpecification(){
		if(requestSpecification == null){
			requestSpecification = RequestSpec.getRequestSpec(prop);
		}
	}
	
	public void createResponseSpecification(){
		if(responseSpecification == null){
			responseSpecification = ResponseSpec.getResponseSpec();
		}
	}
	
	public AddPlaceBody getAddPlacePayload(String name, String lang, String address){
		payload  = new Payload();
		return payload.getAddPlaceBody(name, lang, address);
	}
	
	public void createAddPlacePayload(AddPlaceBody addPlaceBody){
		requestSpecification =  given()
				.spec(requestSpecification)
				.body(addPlaceBody);
	}
	
	public Response postPlace(String resource){
		Response res = 
//		given()
//				.log().all()
//				.spec(requestSpecification)
//				.queryParam("key",prop.getProperty("KEY"))
//				.header("Content-Type","application/json")
//				.body(Payload.getPostPayload())
//				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\avina\\OneDrive\\Projects\\RestAssuredWorkspace\\Practice\\src\\main\\java\\RestAssured\\Practice\\AddPlace.json"))))
//				.body(addPlaceBody)
//				.log().all()
				requestSpecification
				.when().post(resource)
				.then()
				.spec(responseSpecification)
//				.assertThat().statusCode(200)
//				.and().contentType(ContentType.JSON)
//				.and().body("status",equalTo("OK"))
				.extract().response();
		
//		String placeId = jspth.getString("place_id");

		return res;
		
//		//Update place
//		String newAddress = "Summer Walk, Africa";
//		Response putRes = given()
//			.queryParam("key",prop.getProperty("KEY"))
////			.log().all()
//			.body("{\r\n" + 
//					"\"place_id\":\""+placeId+"\",\r\n" + 
//					"\"address\":\""+newAddress+"\",\r\n" + 
//					"\"key\":\"qaclick123\"\r\n" + 
//					"}")
//			.when().put(Resources.getPutResource())
//			.then().assertThat().statusCode(200)
////			.log().all()
//			.and().contentType(ContentType.JSON)
////			.and().body("status",equalTo("OK"))
//			.body("msg", equalTo("Address successfully updated"))
//			.extract().response();
//		
//		System.out.println("PUT response body: " + putRes.asString());
//		
//		//Get same place
//		Response getResp = given()
//				.queryParam("key",prop.getProperty("KEY"))
//				.queryParam("place_id",placeId)
//				.when().get(Resources.getGetResource())
//				.then().assertThat().statusCode(200)
//				.body("address",equalTo(newAddress))
//				.extract().response();
//		
//		System.out.println("GET response: " + getResp.asString());
//		JsonPath jsonGet = new JsonPath(getResp.asString());
//		System.out.println(jsonGet.getString("address"));
//		Assert.assertEquals(jsonGet.getString("address"), newAddress);
		
	}
	
	
	public void createGetPlaceRequestSpecification(String value){
//		if(requestSpecification != null){
//			requestSpecification = null;
//		}
		requestSpecification =  given()
//				.spec(RequestSpec.getRequestSpec(prop))
				.spec(requestSpecification)
				.queryParam("place_id", value);
	}
	
	public Response getPlace(String resource){
		Response res = requestSpecification
				.when().get(resource)
				.then()
				.spec(responseSpecification)
				.extract().response();
		return res;
	}
	
	public String getResponseValueByFieldname(Response response, String fieldname){
		JsonPath jspath = ResponseToJSON.convertResponseToJSON(response);
		return jspath.getString(fieldname);
	}
	
	public void createDeletePlacePayload(String placeId){
		payload  = new Payload();
		requestSpecification =  given()
				.spec(requestSpecification)
				.body(payload.getDeletePlaceBody(placeId));
	}
	
}
