package pageObjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PopUps {
	public WebDriver driver;

	public PopUps(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[@class='close']") WebElement closeExploreHotels;
	@FindBy(xpath="//iframe[@id='webklipper-publisher-widget-container-notification-frame']") WebElement frameSwitch;
	@FindBy(xpath="//iframe") List<WebElement> findIframe;

	public void closePopup()
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(findIframe.size()>2)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(frameSwitch));

			driver.switchTo().frame(frameSwitch);
			wait.until(ExpectedConditions.visibilityOf(closeExploreHotels));

			closeExploreHotels.click();
			driver.switchTo().parentFrame();
		}
	}
}