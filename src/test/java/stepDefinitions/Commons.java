package stepDefinitions;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pageObjects.HomePage;
import pageObjects.PopUps;
import utilities.Hooks;


public class Commons{

	public static WebDriver driver;
	public static HomePage searchForHotelList;
	public PopUps dismissPopups;

	static int screenShotCount=1;

	public static int getSS()
	{
		TakesScreenshot screenshot=(TakesScreenshot)driver;
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
			System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.get("https://www.makemytrip.com/");
			driver.manage().window().maximize();
			searchForHotelList=new HomePage(driver);
			dismissPopups=new PopUps(driver);
			dismissPopups.closePopup();
		}
		catch(Exception e)
		{
			Hooks.errorException++;
		}
		catch(AssertionError e1)
		{
			Hooks.errorException++;
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
			Hooks.errorException++;
		}
		catch(AssertionError e1)
		{
			Hooks.errorException++;
		}

	}
}

