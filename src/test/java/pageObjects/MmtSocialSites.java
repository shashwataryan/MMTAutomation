package pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MmtSocialSites {

	WebDriver driver;

	public MmtSocialSites(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[@class='makeFlex perfectCenter']") List<WebElement> socialSites;

	public List<WebElement> clickOnMMTSocialMediaSites()
	{
		return socialSites;
	}
}
