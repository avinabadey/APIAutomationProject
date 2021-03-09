package BDD.APITestFramework.utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResponseToJSON {
	
	public static JsonPath convertResponseToJSON(Response res){
		return new JsonPath(res.asString());
	}

}
