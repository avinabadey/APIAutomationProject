package BDD.APITestFramework.utilities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {
	
	public static RequestSpecification getRequestSpec(Properties prop){
//		RestAssured.baseURI = prop.getProperty("HOST");
		PrintStream log = null;
		try {
			log = new PrintStream(new FileOutputStream("log.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new RequestSpecBuilder()
			.setContentType(ContentType.JSON)
			.setBaseUri(prop.getProperty("HOST"))
			.addQueryParam("key",prop.getProperty("KEY"))
			.addFilter(RequestLoggingFilter.logRequestTo(log))
			.addFilter(ResponseLoggingFilter.logResponseTo(log))
			.build();	
	}

}
