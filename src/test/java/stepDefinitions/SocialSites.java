package stepDefinitions;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import io.cucumber.java.en.*;
import pageObjects.MmtSocialSites;
import utilities.Hooks;
import utilities.Reports;

public class SocialSites   {


	List<WebElement>social;
	int counterSocialSites=0,errorExcpetion=0,screenShot;
	MmtSocialSites socialSites=new MmtSocialSites(Commons.driver);

	@When ("User opens the MMT social media")
	public void openMMTSocialMedia()
	{
		try {
			social=socialSites.clickOnMMTSocialMediaSites();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
			JavascriptExecutor js = (JavascriptExecutor) Commons.driver;
			//Scroll down till the bottom of the page
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
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

	@Then("All the social media sites should open correctly")
	public void verifySocialMediaSites() throws IOException
	{
		Hooks.test=Reports.extent.createTest("MMT Social Sites");
		try {
			for(WebElement iterateSocialSites:social)
			{
				counterSocialSites++;
				//wait for scrolling
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}
				Actions action=new Actions(Commons.driver);
				action.keyDown(Keys.CONTROL).moveToElement(iterateSocialSites).click().perform();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				Set<String>windowHandles=Commons.driver.getWindowHandles();
				Iterator<String>it=windowHandles.iterator();
				String parentWindow=it.next();
				String childWindow=it.next();
				Commons.driver.switchTo().window(childWindow);
				String url=Commons.driver.getCurrentUrl();
				if(counterSocialSites==1)
					Assert.assertEquals(url, "https://twitter.com/makemytrip/");
				if(counterSocialSites==2)
					Assert.assertEquals(url, "https://www.facebook.com/makemytrip/");
				Commons.driver.close();
				Commons.driver.switchTo().window(parentWindow);
				windowHandles.clear();
			}
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
