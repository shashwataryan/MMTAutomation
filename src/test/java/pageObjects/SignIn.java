package pageObjects;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignIn {
	WebDriver driver;

	public SignIn(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//p[text()='Login or Create Account']") WebElement loginOrCreateAccount;
	@FindBy(xpath="//input[@placeholder='Enter email or mobile number']") WebElement emailOrMobile;
	@FindBy(xpath="//span[text()='Continue']") WebElement continueButton;
	@FindBy(xpath="//input[@id='password']") WebElement password;
	@FindBy(xpath="//span[text()='Login']") WebElement login;
	@FindBy(xpath="//li[@data-cy='account']") WebElement accountHover;
	@FindBy(xpath="//span[@data-cy='serverError']") WebElement serverError;

	public void loginToMMT()
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));

		wait.until(ExpectedConditions.visibilityOf(loginOrCreateAccount));
		loginOrCreateAccount.click();

	}

	public void enterEmailAndPassword(String email,String getPassword)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));

		emailOrMobile.sendKeys(email);
		wait.until(ExpectedConditions.visibilityOf(continueButton));
		continueButton.click();
		wait.until(ExpectedConditions.visibilityOf(password));
		password.sendKeys(getPassword);
		wait.until(ExpectedConditions.visibilityOf(login));
		login.click();

	}
	public String verifyAccount()
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));

		wait.until(ExpectedConditions.visibilityOf(serverError));
		return serverError.getText();
	}

}