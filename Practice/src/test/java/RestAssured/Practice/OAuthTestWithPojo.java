package RestAssured.Practice;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import RestAssured.Practice.POJO.OauthCourse.API;
import RestAssured.Practice.POJO.OauthCourse.CoursesResponse;
import RestAssured.Practice.POJO.OauthCourse.Mobile;
import RestAssured.Practice.POJO.OauthCourse.WebAutomation;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class OAuthTestWithPojo {

	public static void main(String[] args) throws InterruptedException {
		
		
//		WebDriver driver = null;
//		WebDriverManager.chromedriver().browserVersion("88.0.4324.190").setup();
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("start-maximized"); 
//		options.addArguments("enable-automation"); 
//		options.addArguments("--no-sandbox"); 
//		options.addArguments("--disable-infobars");
//		options.addArguments("--disable-dev-shm-usage");
//		options.addArguments("--disable-browser-side-navigation"); 
//		options.addArguments("--disable-gpu"); 
//		driver = new ChromeDriver(options); 
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&flowName=GeneralOAuthFlow");
//		driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys("avinabag36");
//		driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys(Keys.ENTER);
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//div[@class='VfPpkd-RLmnJb']")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//div[@class='vxx8jf']")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("InvestInFuture#1");
//		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Keys.ENTER);
//		Thread.sleep(3000);
//		String url = driver.getCurrentUrl();
		
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g5AcI3nETYrEgvq2zWU6_7N7hM7agvNPi_Qg2OvYjcCawMhpeTxfSQl05spIwyUXA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String partialCode = url.split("code=")[1];
		String code = partialCode.split("&scope")[0];
		System.out.println("Code is: " + code);
		
		
		
		String accessTokenRes = given()
				.urlEncodingEnabled(false)
				.queryParam("code", code)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code")
				.queryParam("state", "verifyfjdss")
				.queryParam("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
				.when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath jsToken = new JsonPath(accessTokenRes);
		String accessToken = jsToken.getString("access_token");

		
		
		CoursesResponse courseResponse = given()
				.queryParam("access_token", accessToken)
				.expect().defaultParser(Parser.JSON)
				.when()
				.get("https://rahulshettyacademy.com/getCourse.php")
				.as(CoursesResponse.class);
		
		System.out.println("LinkedIn: " + courseResponse.getLinkedIn());
		System.out.println("Webautomation 2nd course: " + courseResponse.getCourses().getWebAutomation().get(1).getCourseTitle());
		
		List<API> apiList = courseResponse.getCourses().getApi();
		List<WebAutomation>  webAutoList= courseResponse.getCourses().getWebAutomation();
		List<Mobile> mobileList = courseResponse.getCourses().getMobile();
		
		System.out.println("API automation courses :");
//		for(API api : apiList){
//			System.out.println("Course Title: " + api.getCourseTitle());
//			System.out.println("Course Price: " + api.getPrice());
//		}
		apiList.stream()
			.forEach(s -> System.out.println(s.getCourseTitle() + " : " + s.getPrice()));
		
	}

}
