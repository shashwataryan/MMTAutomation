package stepDefinitions;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.HomePage;
import pageObjects.PopUps;
import utilities.Reports;

public class Commons{

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static HomePage searchForHotelList;
	public static PopUps dismissPopups;
	public ExtentTest test;
	int errorExcpetion=0,screenShot;
	static int screenShotCount=1;

	public static WebDriver getDriver()
	{
		return driver.get();
	}

	public static  void setDriver() {
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver());
	}
	public static int getSS()
	{
		TakesScreenshot screenshot=(TakesScreenshot)getDriver();
		File sourceFile=screenshot.getScreenshotAs(OutputType.FILE);
		File destFile=new File(".\\test-output\\Test"+screenShotCount+".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenShotCount++;
	}

	@Given("User opens MMT")
	public void openMMTWebSite()
	{
		try {
			setDriver();
            getDriver().get("https://www.makemytrip.com/");
			getDriver().manage().window().maximize();
			searchForHotelList=new HomePage(getDriver());
			dismissPopups=new PopUps(getDriver());
			dismissPopups.closePopup();
		}
		catch(Exception e)
		{
			errorExcpetion++;
		}
		catch(AssertionError e1)
		{
			errorExcpetion++;
		}
	}

	@And ("Search for hotel {string}")
	public void hotelSearch(String parameterizedString) throws IOException
	{
		try {
			searchForHotelList.selectHotelMenu();
			searchForHotelList.enterHotelDetails(parameterizedString,"2023-08-04", "2023-08-05",1,2, 0);
		}
		catch(Exception e)
		{
			errorExcpetion++;
		}
		catch(AssertionError e1)
		{
			errorExcpetion++;
		}

		finally {
			if(errorExcpetion!=0)
			{
				test=Reports.extent.createTest("Check common steps");
				test.log(Status.FAIL,"Common Step failed");
				screenShot=Commons.getSS();
				test.addScreenCaptureFromPath(".\\test-output\\Test"+screenShot+".png", "ScreenShot for failed test case");
			}

			if(errorExcpetion!=0)
				Assert.fail();
		}
	}
}

