package stepDefinitions;

import java.io.IOException;
import org.testng.Assert;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.RegisterHotel;
import utilities.Hooks;
import utilities.Reports;

public class ListUserHotel{

	HomePage clickHotelMenu=new HomePage(Commons.driver);
	RegisterHotel listingUserHotel=new RegisterHotel(Commons.driver);
	String textAfterRegistration;
	int screenShot;
	

	@And("Clicks on the link to list hotel")
	public void clickToListHotel()
	{
		try {
			Hooks.test = Reports.extent.createTest("List Hotel");
			clickHotelMenu.selectHotelMenu();
			clickHotelMenu.clickPropertyLink();
			listingUserHotel.openListHotel();
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
	@When("User enter the valid details to register")
	public void enterUserDetails()
	{
		try {
			textAfterRegistration=listingUserHotel.enterDetails("Shashwat", "testuseremail45@yopmail.com", "Test@123", "Test@123");
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

	@Then("A link to the registered email id is sent")
	public void verifyRegistration() throws IOException
	{ 

		try {
			Assert.assertEquals(textAfterRegistration,"We've sent you an email with an account activation link.");
			
		}
		catch(AssertionError e)
		{
			Hooks.errorException++;
		}
		catch(Exception e1)
		{
			Hooks.errorException++;
		}


	}
}
