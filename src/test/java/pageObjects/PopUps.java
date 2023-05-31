package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import stepDefinitions.Commons;

import java.util.Iterator;
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
	@FindBy(xpath="//span[text()='Or Login/Signup With']") WebElement loginPopUp;

	public void closePopup()
	{
		try {
			Thread.sleep(5000);
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
		/*if(loginPopUp.isDisplayed())
		{
			int windowWidth = driver.manage().window().getSize().getWidth();
	        int windowHeight = driver.manage().window().getSize().getHeight();

	        // Generate random X and Y coordinates within the browser window's dimensions
	        Random random = new Random();
	        int randomX = random.nextInt(windowWidth);
	        int randomY = random.nextInt(windowHeight);

	        // Use Actions class to perform the mouse click action
	        Actions actions = new Actions(driver);

	        // Move the mouse to the generated random coordinates and perform the click action
	        actions.moveByOffset(randomX, randomY).click().perform();

		}*/
		/*if(loginPopUp.isDisplayed())
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.close();");
		}*/
/*if(loginPopUp.isDisplayed())
{
	String main=driver.getWindowHandle();
	Set<String>windowHandles=driver.getWindowHandles();
	Iterator<String>it=windowHandles.iterator();
	it.next();
	String child=it.next();
	driver.switchTo().window(child);
	driver.close();
	driver.switchTo().window(main);
}*/
	}
}