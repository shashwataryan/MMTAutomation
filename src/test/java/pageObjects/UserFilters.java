package pageObjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserFilters {
	public WebDriver driver;

	public UserFilters(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@id='USER_RATING']//ul//li[1]") WebElement userRating;
	@FindBy(xpath="//span[@class='rating font12 latoBlack blueBg']") List<WebElement> listAfterFilter;


	public void applyStaredFilter()
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOf(userRating));
		userRating.click();
	}
	public List<WebElement> listAfterFiltering()
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(7));
		
		wait.until(ExpectedConditions.visibilityOfAllElements(listAfterFilter));
		return listAfterFilter;
	}
}