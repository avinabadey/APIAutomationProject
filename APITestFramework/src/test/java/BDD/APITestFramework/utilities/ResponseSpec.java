package BDD.APITestFramework.utilities;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpec {
	
	public static ResponseSpecification getResponseSpec(){
		return new ResponseSpecBuilder()
//			.expectStatusCode(200)
			.expectContentType(ContentType.JSON)
//			.expectBody("status", equalTo("OK"))
			.build();
	}
	

}
