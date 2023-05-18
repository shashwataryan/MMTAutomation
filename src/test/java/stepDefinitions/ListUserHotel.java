package stepDefinitions;

import java.io.IOException;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.RegisterHotel;
import utilities.Reports;

public class ListUserHotel{

	HomePage clickHotelMenu=new HomePage(Commons.getDriver());
	RegisterHotel listingUserHotel=new RegisterHotel(Commons.getDriver());
	String textAfterRegistration;
	int errorExcpetion=0,screenShot;
	public ExtentTest test;

	@And("Clicks on the link to list hotel")
	public void clickToListHotel()
	{
		try {
			test = Reports.extent.createTest("List Hotel");
			clickHotelMenu.selectHotelMenu();
			clickHotelMenu.clickPropertyLink();
			listingUserHotel.openListHotel();
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
	@When("User enter the valid details to register")
	public void enterUserDetails()
	{
		try {
			textAfterRegistration=listingUserHotel.enterDetails("Shashwat", "testuseremail45@yopmail.com", "Test@123", "Test@123");
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

	@Then("A link to the registered email id is sent")
	public void verifyRegistration() throws IOException
	{ 

		try {
			Assert.assertEquals(textAfterRegistration,"We've sent you an email with an account activation link.");
			test.log(Status.PASS, "Product added to cart");
		}
		catch(AssertionError e)
		{
			errorExcpetion++;
		}
		catch(Exception e1)
		{
			errorExcpetion++;
		}

		finally {
			if(errorExcpetion>0)
			{
				test.log(Status.FAIL,"Listing of Hotel Failed");
				screenShot=Commons.getSS();
				test.addScreenCaptureFromPath(".\\test-output\\Test"+screenShot+".png", "ScreenShot for failed test case");

			}

			if(errorExcpetion>0)
				Assert.fail();

		}

	}
}
