package RestAssured.Practice;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;


public class JiraTest {
	
	public static void main(String []args){
		
		RestAssured.baseURI = "http://localhost:8080";
		
		//create session
		SessionFilter sessionFilter = new SessionFilter();
		Response sessionRes = given()
				.relaxedHTTPSValidation()
				.header("Content-Type", "application/json").log().all()
				.body("{"+
				    "\"username\": \"admin\"," +
				    "\"password\": \"admin\"" +
				"}")
				.filter(sessionFilter)
				.when()
				.post("rest/auth/1/session")
				.then().log().all()
				.extract().response();
		
		//add comment
		String commentInputText = "This is my also my text.";
		Response addCommentRes = given()
			.pathParam("id", "10100").log().all()
			.header("Content-Type", "application/json")
			.body("{\"body\": \"" + commentInputText + "\","+
			    "\"visibility\": {" +
			        "\"type\": \"role\"," +
			        "\"value\": \"Administrators\"}" +
				"}")
			.filter(sessionFilter)
			.when()
			.post("rest/api/2/issue/{id}/comment")
			.then().log().all()
			.assertThat().statusCode(201)
			.extract().response();
		
		JsonPath js = new JsonPath(addCommentRes.asString());
		String commentID = js.getString("id");
		
//		//add attachments
//		given().log().all()
//		.pathParam("id", "10100")
//		.header("Content-Type", "multipart/form-data")
//		.header("X-Atlassian-Token", "no-check")
//		.multiPart("file", new File("JiraText.txt"))
//		.filter(sessionFilter)
//		.when()
//		.post("rest/api/2/issue/{id}/attachments")
//		.then().log().all()
//		.assertThat().statusCode(200);
		
		//get Issue
		Response getIssue = given().log().all()
			.pathParam("id", "10100")
			.queryParam("fields", "comment")
//			.headers("Content-Type", "")
			.filter(sessionFilter)
			.when()
			.get("rest/api/2/issue/{id}")
			.then().log().all()
			.assertThat().statusCode(200)
			.extract().response();
		
		System.out.println(getIssue.asString());
		
		JsonPath issuejspath = new JsonPath(getIssue.asString());
		int count  = issuejspath.getInt("fields.comment.comments.size()");
		for(int i = 0; i < count; i++){
			if(issuejspath.getString("fields.comment.comments[ " + i + " ].id").equals(commentID)){
				System.out.println("Comment found!!! ID of comment is: " + issuejspath.getString("fields.comment.comments[ " + i + " ].id"));
				Assert.assertEquals(issuejspath.getString("fields.comment.comments[ " + i + " ].body"), commentInputText);
				System.out.println(issuejspath.getString("fields.comment.comments[ " + i + " ].body"));
				break;
			}
		}
		
	}

}
