package RestAssured.Practice;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;

import RestAssured.Practice.POJO.AddPlace.AddPlaceBody;
import RestAssured.Practice.POJO.AddPlace.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class SpecBuilder 
{
	public static void main(String []args) throws IOException{
		
		Properties prop = new Properties();
//		SoftAssertion softAssert = new SoftAssertion(); 
		
		FileInputStream fis = new FileInputStream("C:\\Users\\avina\\OneDrive\\Projects\\RestAssuredWorkspace\\Practice\\src\\main\\java\\RestAssured\\Practice\\env.properties");
		prop.load(fis);
		
		AddPlaceBody addPlaceBody = new AddPlaceBody();
		Location location = new Location();
		location.setLat(-33.8669710);
		location.setLng(151.1958750);
		
		addPlaceBody.setAccuracy(90);
		addPlaceBody.setAddress("my home at my home");
		addPlaceBody.setLanguage("Bengali-IN");
		addPlaceBody.setLocation(location);
		addPlaceBody.setName("Avinaba Dey");
		addPlaceBody.setPhone_number("8374874542");
		addPlaceBody.setTypes(Arrays.asList("shoe_store","books","pens","medicines"));
		addPlaceBody.setWebsite("myacademy.com");
		
//		RestAssured.baseURI = prop.getProperty("HOST");
		RequestSpecification reqSpec = new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.setBaseUri(prop.getProperty("HOST"))
				.addQueryParam("key",prop.getProperty("KEY"))
				.build();
		
		ResponseSpecification resSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.expectBody("status", equalTo("OK"))
				.build();
		
		Response res = given()
				.log().all()
				.spec(reqSpec)
//				.queryParam("key",prop.getProperty("KEY"))
//				.header("Content-Type","application/json")
//				.body(Payload.getPostPayload())
//				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\avina\\OneDrive\\Projects\\RestAssuredWorkspace\\Practice\\src\\main\\java\\RestAssured\\Practice\\AddPlace.json"))))
				.body(addPlaceBody)
//				.log().all()
				.when().post(Resources.getPostResource())
				.then()
				.spec(resSpec)
//				.assertThat().statusCode(200)
//				.and().contentType(ContentType.JSON)
//				.and().body("status",equalTo("OK"))
				.extract().response();
		
		System.out.println("POST response is: " + res.asString());
		JsonPath jspth = ResponseToJSON.convertResponseToJSON(res);
		System.out.println("The Place ID is: " + jspth.getString("place_id"));
		String placeId = jspth.getString("place_id");
		
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
//		
	}
    
}
