package stepDefinitions;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.en.*;
import utilities.Reports;
import pageObjects.UserFilters;


public class ApplyFilter {

	int errorExcpetion=0,screenShot;
	public ExtentTest test;
	UserFilters applyStarFilter=new UserFilters(Commons.getDriver());
	
	@When("User applies a filter according to the star category")
    public void applyFilter()
	{
		try {
			applyStarFilter.applyStaredFilter();
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
	
	@Then ("The results should be filtered accordingly")
	public void verifyStaredFilter() throws IOException
	{
		test=Reports.extent.createTest("Filter Result according to rating");
		try {
			List<WebElement>filteredList=applyStarFilter.listAfterFiltering();
			for(WebElement filteredListIterate:filteredList)
			{
				Double rating=Double.parseDouble(filteredListIterate.getText());
				Assert.assertTrue(rating>=4.5);
			}
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
			
			if(errorExcpetion==0)
				test.log(Status.PASS, "Filtered according to rating");
			else
			{
				test.log(Status.FAIL,"Filtering failed");
				int screenShot=Commons.getSS();
				test.addScreenCaptureFromPath(".\\test-output\\Test"+screenShot+".png", "ScreenShot for failed test case");
			}

			if(errorExcpetion!=0)
				Assert.fail();
		}
	}
}