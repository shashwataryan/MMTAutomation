package testRunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		plugin = {
				"json:target/cucumber-reports/cucumber.json",
				"html:target/cucumber-reports/cucumber.html"
		},
		features = {"src/test/resources"},
		glue = {"stepDefinitions","utilities"})

public class runnerTest extends AbstractTestNGCucumberTests {

	/*@Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
}*/
}