package utilities;

import java.io.IOException;

import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import stepDefinitions.Commons;

public class Hooks {

	public static int errorException;
	public static ExtentTest test;
	int screenShot;
	
	@Before
	public void beforeScenario()
	{
		errorException=0;
	}
	
	@After
	public void afterScenario() throws IOException {
		
		
		if(errorException==0)
			{
			test.pass("Test passed");
			Commons.driver.quit();
			}
		else
		{
			test.fail("Test failed");
			screenShot=Commons.getSS();
			test.addScreenCaptureFromPath(".\\test-output\\Test"+screenShot+".png", "ScreenShot for failed test case");
			Commons.driver.quit();
			Assert.fail();
		}
		
	}
}
