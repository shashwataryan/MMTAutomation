package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		plugin = {
				"pretty",
				"json:target/cucumber-reports/cucumber.json",
				"html:target/cucumber-reports/cucumber.html"
		},
		features = {"src/test/resources"},
		glue = {"stepDefinitions","utilities"})
//mvn test -Dcucumber.filter.tags=@SmokeTest
public class runnerTest extends AbstractTestNGCucumberTests {


}