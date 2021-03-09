package RestAssured.Practice;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DynamicJSON {
	
	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle){
		Properties prop = new Properties();
		
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\avina\\OneDrive\\Projects\\RestAssuredWorkspace\\Practice\\src\\main\\java\\RestAssured\\Practice\\env.properties");
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RestAssured.baseURI = prop.getProperty("LIBRARY_HOST");
		Response res = given()
				.header("Content-Type","application/json")
//				.body(Payload.getAddBookPayload("mine", "97665"))
				.body(Payload.getAddBookPayload(isbn, aisle))
				.when()
				.post(Resources.getAddBookResource())
				.then().log().all()
				.assertThat().statusCode(200)
				.extract().response();
		
		JsonPath js = ResponseToJSON.convertResponseToJSON(res);
		System.out.println(js.getString("ID"));
		
	}
	
	@DataProvider(name = "BooksData")
	public Object[][] getData(){
		return new Object[][] {{"asdfghhh", "846545"}, {"tyui", "2345"}, {"xcvbn", "567456"}};
	}
	

}
