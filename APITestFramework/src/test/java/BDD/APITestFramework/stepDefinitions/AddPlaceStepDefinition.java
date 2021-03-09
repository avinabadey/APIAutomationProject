package BDD.APITestFramework.stepDefinitions;

import static org.junit.Assert.*;

import BDD.APITestFramework.steps.AddPlaceSteps;
import BDD.APITestFramework.utilities.APIResources;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class AddPlaceStepDefinition {
	
	AddPlaceSteps addPlaceSteps;
	Response response;
	static Response addPlaceResponse;
	
	@Given("Add Place Payload with {string}, {string} and {string}")
	public void add_place_payload(String name, String lang, String address) {
		if(addPlaceSteps == null){
			addPlaceSteps = new AddPlaceSteps();
		}
		addPlaceSteps.getSystemEnvDetails();
		addPlaceSteps.createRequestSpecification();
		addPlaceSteps.createResponseSpecification();
		addPlaceSteps.createAddPlacePayload(addPlaceSteps.getAddPlacePayload(name, lang, address));
	}
	
	@When("User call {string} with {string} http request")
	public void user_call_with_http_request(String apiResource, String requestType) {
		APIResources apiResources = APIResources.valueOf(apiResource);
		if(requestType.equalsIgnoreCase("POST")){
			response = addPlaceSteps.postPlace(apiResources.getResource());
			addPlaceResponse = response;
		}
		else if(requestType.equalsIgnoreCase("GET")){
			addPlaceSteps.createGetPlaceRequestSpecification(addPlaceSteps.getResponseValueByFieldname(response, "place_id"));
			response = addPlaceSteps.getPlace(apiResources.getResource());
		}
	}
	
	@Then("API call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer expectedStatusCode) {
		assertEquals(expectedStatusCode.toString(), Integer.toString(response.getStatusCode()));
	}
	
	@Then("Verify {string} in response body is {string}")
	public void verify_in_response_body_is(String fieldname, String expectedValue) {
		assertEquals(expectedValue, addPlaceSteps.getResponseValueByFieldname(response, fieldname));
	}
	
	@Then("Verify place_id matches {string} using {string}")
	public void verify_place_id_matches(String expectedValue, String apiResource) {
//		if(addPlaceSteps == null){
//			addPlaceSteps = new AddPlaceSteps();
//		}
		user_call_with_http_request(apiResource, "GET");
		assertEquals(expectedValue, addPlaceSteps.getResponseValueByFieldname(response, "name"));
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() {
		if(addPlaceSteps == null){
			addPlaceSteps = new AddPlaceSteps();
		}
		addPlaceSteps.createDeletePlacePayload(addPlaceSteps.getResponseValueByFieldname(addPlaceResponse, "place_id"));
	}
	
}
