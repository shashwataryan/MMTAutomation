package utilities;

import java.io.IOException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import stepDefinitions.Commons;

public class Hooks {

	public static ThreadLocal<Integer> errorException = new ThreadLocal<Integer>();
	public static ExtentTest test;
	int screenShot;
	
	@Before
	public static void beforeScenario()
	{
		errorException.set(0);
		System.out.println("Before Scenario:"+errorException);
	}
	
	@After
	public void afterScenario() throws IOException {
		
		System.out.println("After Scenario:"+errorException);
		if(errorException.get()==0)
			{
			test.pass("Test passed");
			Commons.getDriver().quit();
			}
		else
		{
			test.fail("Test failed");
			screenShot=Commons.getSS();
			test.addScreenCaptureFromPath(".\\test-output\\Test"+screenShot+".png", "ScreenShot for failed test case");
			Commons.getDriver().quit();
			Assert.fail();
		}
		
	}
}
