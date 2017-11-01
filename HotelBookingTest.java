import com.sun.javafx.PlatformUtil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTest {
	WebDriver driver ;


	@FindBy(linkText = "Hotels")
	public WebElement hotelLink;

	@FindBy(id = "Tags")
	public WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	public WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	public WebElement travellerSelection;


//Lunching the browser

	@BeforeTest
	public void driverLunch()
	{ 
		setDriverPath();
		driver = new ChromeDriver();
		driver.get("https://www.cleartrip.com/");
		waitFor(2000);
	}
	
	@Test
	public void shouldBeAbleToSearchForHotels() {//running the test method 

		HotelBookingTest hotel=PageFactory.initElements(driver,HotelBookingTest.class);//Initializing the web element 
		hotel.hotelLink.click();

		hotel.localityTextBox.sendKeys("Indiranagar, Bangalore");

		waitFor(2000);
		hotel.localityTextBox.sendKeys(Keys.TAB);
		//used Tab keyword to move another tab


		new Select(hotel.travellerSelection).selectByVisibleText("1 room, 2 adults");
		hotel.searchButton.click();
		driver.quit();
	}


	@SuppressWarnings("restriction")
	private void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}

	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}




	}}