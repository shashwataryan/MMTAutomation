package stepDefinitions;


import java.io.IOException;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Reports;


public class HotelPayment{


	int estimatedPrice,finalPrice;
	int errorExcpetion=0,screenShot;
	public ExtentTest test;

	@When("User select the hotel from search results")
	public void bookHotel()
	{
		try {
			Commons.searchForHotelList.clickHotel();
			estimatedPrice=Commons.searchForHotelList.bookHotel();
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

	@Then("The same amount should show in the booking review page and payments page")
	public void reviewPrice() throws IOException
	{
		test=Reports.extent.createTest("Check Hotel Price");
		try {
			finalPrice=Commons.searchForHotelList.reviewFinalPrice();
			Assert.assertTrue(estimatedPrice==finalPrice);

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
				test.log(Status.PASS, "Hotel price validated");
			else
			{
				test.log(Status.FAIL,"Hotel Price Validation failed");
				screenShot=Commons.getSS();
				test.addScreenCaptureFromPath(".\\test-output\\Test"+screenShot+".png", "ScreenShot for failed test case");
			}

			if(errorExcpetion!=0)
				Assert.fail();
		}
	}
}