package utilities;

import io.cucumber.java.After;
import stepDefinitions.Commons;

public class Hooks {

	@After
	public void afterSuite() {
		Commons.getDriver().quit();
	}
}
