package pageObjects;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class RegisterHotel {

	WebDriver driver;

	public RegisterHotel(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[text()='LIST YOUR HOTEL']") WebElement listHotel;
	@FindBy(xpath="//input[@id='id_yourname']") WebElement fullName;
	@FindBy(xpath="//input[@id='id_email']") WebElement email;
	@FindBy(xpath="//input[@id='id_password1']") WebElement password;
	@FindBy(xpath="//input[@id='id_password2']") WebElement confirmPassword;
	@FindBy(xpath="//input[@value='Register Now']") WebElement registerNow;
	@FindBy(xpath="//div[@class='text-muted']/p[1]") WebElement textAfterRegistartion;

	public void openListHotel()
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(listHotel));
		listHotel.click();
	}
	public String enterDetails(String name,String emailId,String userPassword, String userConfirmPassword)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		
		Set<String>windowHandles=driver.getWindowHandles();
		Iterator<String>it=windowHandles.iterator();
		it.next();
		String childWindow=it.next();
		driver.switchTo().window(childWindow);
        wait.until(ExpectedConditions.visibilityOf(fullName));
        fullName.sendKeys(name);
		email.sendKeys(emailId);
		password.sendKeys(userPassword);
		confirmPassword.sendKeys(userConfirmPassword);
		registerNow.click();
		wait.until(ExpectedConditions.visibilityOf(textAfterRegistartion));
		return textAfterRegistartion.getText();
	}
}
