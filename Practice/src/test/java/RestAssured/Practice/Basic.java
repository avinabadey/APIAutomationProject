package RestAssured.Practice;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.Assert;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Basic 
{
	public static void main(String []args) throws IOException{
		
		Properties prop = new Properties();
//		SoftAssertion softAssert = new SoftAssertion(); 
		
		FileInputStream fis = new FileInputStream("C:\\Users\\avina\\OneDrive\\Projects\\RestAssuredWorkspace\\Practice\\src\\main\\java\\RestAssured\\Practice\\env.properties");
		prop.load(fis);
		
		RestAssured.baseURI = prop.getProperty("HOST");
		Response res = given()
				.log().all()
				.queryParam("key",prop.getProperty("KEY"))
				.header("Content-Type","application/json")
//				.body(Payload.getPostPayload())
				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\avina\\OneDrive\\Projects\\RestAssuredWorkspace\\Practice\\src\\main\\java\\RestAssured\\Practice\\AddPlace.json"))))
//				.log().all()
				.when().post(Resources.getPostResource())
				.then().assertThat().statusCode(200)
				.and().contentType(ContentType.JSON)
				.and().body("status",equalTo("OK"))
				.extract().response();
		
		System.out.println("POST response is: " + res.asString());
		JsonPath jspth = ResponseToJSON.convertResponseToJSON(res);
		System.out.println("The Place ID is: " + jspth.getString("place_id"));
		String placeId = jspth.getString("place_id");
		
		//Update place
		String newAddress = "Summer Walk, Africa";
		Response putRes = given()
			.queryParam("key",prop.getProperty("KEY"))
//			.log().all()
			.body("{\r\n" + 
					"\"place_id\":\""+placeId+"\",\r\n" + 
					"\"address\":\""+newAddress+"\",\r\n" + 
					"\"key\":\"qaclick123\"\r\n" + 
					"}")
			.when().put(Resources.getPutResource())
			.then().assertThat().statusCode(200)
//			.log().all()
			.and().contentType(ContentType.JSON)
//			.and().body("status",equalTo("OK"))
			.body("msg", equalTo("Address successfully updated"))
			.extract().response();
		
		System.out.println("PUT response body: " + putRes.asString());
		
		//Get same place
		Response getResp = given()
				.queryParam("key",prop.getProperty("KEY"))
				.queryParam("place_id",placeId)
				.when().get(Resources.getGetResource())
				.then().assertThat().statusCode(200)
				.body("address",equalTo(newAddress))
				.extract().response();
		
		System.out.println("GET response: " + getResp.asString());
		JsonPath jsonGet = new JsonPath(getResp.asString());
		System.out.println(jsonGet.getString("address"));
		Assert.assertEquals(jsonGet.getString("address"), newAddress);
		
	}
    
}
