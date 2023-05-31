package stepDefinitions;

import java.io.IOException;
import org.testng.Assert;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.RegisterHotel;
import utilities.Hooks;
import utilities.Reports;

public class ListUserHotel{

	HomePage clickHotelMenu=new HomePage(Commons.getDriver());
	RegisterHotel listingUserHotel=new RegisterHotel(Commons.getDriver());
	String textAfterRegistration;
	int screenShot;
	Integer error;
	int interr;

	@And("Clicks on the link to list hotel")
	public void clickToListHotel()
	{
		try {
			
			clickHotelMenu.selectHotelMenu();
			clickHotelMenu.clickPropertyLink();
			listingUserHotel.openListHotel();
		}
		catch(Exception e)
		{
			error=Hooks.errorException.get();
			interr=error+1;
			Hooks.errorException.set(interr);
		}
		catch(AssertionError e1)
		{
			error=Hooks.errorException.get();
			interr=error+1;
			Hooks.errorException.set(interr);
		}


	}
	@When("User enter the valid details to register")
	public void enterUserDetails()
	{
		try {
			textAfterRegistration=listingUserHotel.enterDetails("Shashwat", "testuseremail51@yopmail.com", "Test@123", "Test@123");
		}
		catch(Exception e)
		{
			error=Hooks.errorException.get();
			interr=error+1;
			Hooks.errorException.set(interr);
		}
		catch(AssertionError e1)
		{
			error=Hooks.errorException.get();
			interr=error+1;
			Hooks.errorException.set(interr);
		}

	}

	@Then("A link to the registered email id is sent")
	public void verifyRegistration() throws IOException
	{ 

		try {
			Hooks.test = Reports.extent.createTest("List Hotel");
			Assert.assertEquals(textAfterRegistration,"We've sent you an email with an account activation link.");
			
		}
		catch(AssertionError e)
		{
			error=Hooks.errorException.get();
			interr=error+1;
			Hooks.errorException.set(interr);
		}
		catch(Exception e1)
		{
			error=Hooks.errorException.get();
			interr=error+1;
			Hooks.errorException.set(interr);
		}


	}
}
