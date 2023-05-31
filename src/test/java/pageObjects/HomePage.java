package pageObjects;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HomePage {

	public WebDriver driver;
	String monthYear;
	int displayedNumber,roomsGuestsCounter=0;

	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//span[text()='Hotels']") WebElement hotels;
	@FindBy(xpath="//span[text()='City, Property name or Location']") WebElement selectNameBox;
	@FindBy(xpath="//input[@placeholder='Enter city/ Hotel/ Area/ Building']") WebElement cityName;
	@FindBy(xpath="(//div[@class='DayPicker-Caption'])[1]") WebElement displayedMonthYear;
	@FindBy(xpath="//span[@aria-label='Next Month']") WebElement nextMonthArrow;
	@FindBy(xpath="//li[@id='react-autowhatever-1-section-0-item-1']") WebElement selectCityFromList;
	@FindBy(xpath="//input[@id='checkin']") WebElement checkin;
	@FindBy(xpath="//span[text()='ROOMS & GUESTS']") WebElement roomsGuest;
	@FindBy(xpath="//li[@data-cy='GuestSelect$$_323']") List<WebElement> total;
	@FindBy(xpath="//div[@data-cy='GuestSelect$$_324']") List<WebElement>selectRoomsAndGuest;
	@FindBy(xpath="//button[text()='Apply']") WebElement apply;
	@FindBy(xpath="//p[@class='font20 blackText latoBlack']") WebElement estimatedPrice;
	@FindBy(xpath="//button[text()='Search']") WebElement search;
	@FindBy(xpath="//div[@class='listingRowOuter hotelTileDt makeRelative ']") WebElement hotelName;
	@FindBy(xpath="//a[text()='BOOK THIS NOW']") WebElement bookNow; 
	@FindBy(xpath="(//p[@class='latoBold'])") WebElement actualPrice;
	@FindBy(xpath="//a[text()='click here']") WebElement listPropertyLink;


	public void selectHotelMenu()
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOf(hotels));
		hotels.click();
	}

	public void clickPropertyLink()
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		
		wait.until(ExpectedConditions.visibilityOf(listPropertyLink));
		listPropertyLink.click();
	}
	public void enterHotelDetails(String city,String checkinDate,String checkoutDate,int rooms,int adults,int children) 
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		HomePage dateCall=new HomePage(driver);

		wait.until(ExpectedConditions.visibilityOf(selectNameBox));
		selectNameBox.click();
		wait.until(ExpectedConditions.visibilityOf(cityName));
		cityName.sendKeys(city);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // To wait for the list to get populated
		selectCityFromList.click();
		dateCall.formatDate(checkinDate);
		dateCall.formatDate(checkoutDate);

		for(WebElement roomsAndGuests:selectRoomsAndGuest)
		{
			roomsAndGuests.click();
			roomsGuestsCounter++;
			for(WebElement totalDisplayed:total)
			{
				displayedNumber=Integer.parseInt(totalDisplayed.getText());

				if(roomsGuestsCounter==1)
				{
					if(displayedNumber==rooms)
					{
						totalDisplayed.click();
						break;
					}
				}

				if(roomsGuestsCounter==2)
				{
					if(displayedNumber==adults)
					{
						totalDisplayed.click();
						break;
					}
				}

				if(roomsGuestsCounter==3)
				{
					if(displayedNumber==children)
					{
						totalDisplayed.click();
						break;
					}
				}
			}
		}
		apply.click();
		search.click();
	}

	public void clickHotel()
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		
		wait.until(ExpectedConditions.visibilityOf(hotelName));
		hotelName.click();
	}
	public int bookHotel()
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		Set<String>ids=driver.getWindowHandles(); 
		Iterator<String> it = ids.iterator();
		it.next();
		String childId = it.next(); 
		driver.switchTo().window(childId);
		wait.until(ExpectedConditions.visibilityOf(estimatedPrice));
		String estimatedPrices=estimatedPrice.getText();
		estimatedPrices=estimatedPrices.substring(2);
		estimatedPrices=estimatedPrices.replace(",","");
		int estimatedPriceNumber=Integer.parseInt(estimatedPrices);
		bookNow.click();
		return estimatedPriceNumber;
	}

	public int reviewFinalPrice() {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOf(actualPrice));
		String actualPrices=actualPrice.getText();
		actualPrices=actualPrices.substring(2);
		actualPrices=actualPrices.replace(",", "");
		int actualPriceNumber=Integer.parseInt(actualPrices);
		return actualPriceNumber;
	}

	void formatDate(String userDate)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		LocalDate desireddate=LocalDate.parse(userDate);
		int date=desireddate.getDayOfMonth();
		Month month=desireddate.getMonth();
		String actualmonth=month.toString();
		int year=desireddate.getYear();
		String userMonthYear=actualmonth+year;
		wait.until(ExpectedConditions.visibilityOf(displayedMonthYear));
		monthYear=displayedMonthYear.getText();

		while(!userMonthYear.equalsIgnoreCase(monthYear))
		{	
			nextMonthArrow.click();
			monthYear=displayedMonthYear.getText();
		}
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@class='DayPicker-Day'])["+date+"]"))));
		driver.findElement(By.xpath("(//div[@class='DayPicker-Day'])['+date+']")).click();

	}

}
