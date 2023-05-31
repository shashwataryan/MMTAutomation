package stepDefinitions;


import java.io.IOException;
import org.testng.Assert;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Hooks;
import utilities.Reports;


public class HotelPayment{

	Integer error;
	int interr;
	int estimatedPrice,finalPrice;
	int screenShot;

	@When("User select the hotel from search results")
	public void bookHotel()
	{
		try {
			Commons.searchForHotelList.clickHotel();
			estimatedPrice=Commons.searchForHotelList.bookHotel();
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

	@Then("The same amount should show in the booking review page and payments page")
	public void reviewPrice() throws IOException
	{
		Hooks.test=Reports.extent.createTest("Check Hotel Price");
		try {
			finalPrice=Commons.searchForHotelList.reviewFinalPrice();
			Assert.assertTrue(estimatedPrice==finalPrice);

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
}