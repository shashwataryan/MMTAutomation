package stepDefinitions;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import io.cucumber.java.en.*;
import utilities.Hooks;
import utilities.Reports;
import pageObjects.UserFilters;


public class ApplyFilter {

	int screenShot;
	Integer error;
	int interr;
	
	UserFilters applyStarFilter=new UserFilters(Commons.getDriver());

	@When("User applies a filter according to the star category")
	public void applyFilter()
	{
		try {
			applyStarFilter.applyStaredFilter();
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

	@Then ("The results should be filtered accordingly")
	public void verifyStaredFilter() throws IOException
	{
		Hooks.test=Reports.extent.createTest("Filter Result according to rating");
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