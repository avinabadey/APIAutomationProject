Feature: Validating the Place API's

@addPlace
Scenario Outline: Verify that Place is successfully added using Add Place API
	Given Add Place Payload with "<name>", "<language>" and "<address>"
	When User call "ADD_PLACE_RESOURCE" with "POST" http request
	Then API call got success with status code 200
	And Verify "status" in response body is "OK"
	And Verify "scope" in response body is "APP"
	And Verify place_id matches "<name>" using "GET_PLACE_RESOURCE"
		
Examples:
	| name	| language	| address	|	
	|Juan	| Spanish	| Spain		|
	|Eren	| Jap		| Eldia		|

@deletePlace	
Scenario: Verify that Delete Place functionality is working
	Given Delete Place Payload
	When User call "DELETE_PLACE_RESOURCE" with "POST" http request
	Then API call got success with status code 200
	And Verify "status" in response body is "OK"