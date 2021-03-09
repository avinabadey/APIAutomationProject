package RestAssured.Practice;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJSONParse {

	public static void main(String[] args) {
		
		JsonPath jspath = new JsonPath(Payload.getCoursePriceResponse());
		
//		1. Print No of courses returned by API
		System.out.println(jspath.getInt("courses.size()"));
//		2.Print Purchase Amount
		System.out.println(jspath.getInt("dashboard.purchaseAmount"));
//		3. Print Title of the first course
		System.out.println(jspath.getString("courses[0].title"));
//		4. Print All course titles and their respective Prices
		for(int i = 0; i < jspath.getInt("courses.size()"); i++){
			System.out.print(jspath.getString("courses[" + i + "].title"));
			System.out.print(" -->  ");
			System.out.println(jspath.getInt("courses[" + i + "].price"));
		}
		
//		5. Print no of copies sold by RPA Course
		for(int i = 0; i < jspath.getInt("courses.size()"); i++){
			if(jspath.getString("courses[" + i + "].title").equalsIgnoreCase("RPA")){
				System.out.println("RPA copies: " + jspath.getInt("courses[" + i + "].copies"));
				break;
			}
		}
		
//		6. Verify if Sum of all Course prices matches with Purchase Amount
		int sumOfCourses = 0;
		for(int i = 0; i < jspath.getInt("courses.size()"); i++){
			sumOfCourses += jspath.getInt("courses[" + i + "].price") *  jspath.getInt("courses[" + i + "].copies");
		}
		Assert.assertEquals(sumOfCourses, jspath.getInt("dashboard.purchaseAmount"));
	}

}
