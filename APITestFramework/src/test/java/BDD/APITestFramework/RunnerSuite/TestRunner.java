package BDD.APITestFramework.RunnerSuite;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/BDD/APITestFramework/features",
//		glue = {"stepDefinitions"},
		glue = "BDD/APITestFramework/stepDefinitions",
//		tags = "@addPlace",
		plugin = {"pretty", 
				"html:target/html-reports",
				"json:target/json-reports/cucumber-report.json",
				"junit:target/junit-reports/cucumber-report.xml"},
		monochrome = true
//		dryRun=true,
		
		)
public class TestRunner {

}
