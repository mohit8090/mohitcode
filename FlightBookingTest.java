import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest {




	WebDriver driver ;
	@BeforeTest
	public void driverLunch()
	{ 
		setDriverPath();
		driver = new ChromeDriver();
		driver.get("https://www.cleartrip.com/");

	}



	@Test
	public void testThatResultsAppearForAOneWayJourney() {
		waitFor(2000);
		driver.findElement(By.id("OneWay")).click();

		driver.findElement(By.id("FromTag")).clear();
		driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

		//wait for the auto complete options to appear for the origin

		waitFor(2000);
		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(0).click();
		driver.findElement(By.xpath(".//*[@id='ToTag']")).clear();//id was not proper so used xpath
		driver.findElement(By.xpath(".//*[@id='ToTag']")).sendKeys("NEW Delhi");
		//waitFor(2000);
		//driver.findElement(By.id("toTag")).clear();
		// driver.findElement(By.id("toTag")).sendKeys("NEW DELHI");
		//this id is  not working so i used xpath
		//wait for the auto complete options to appear for the destination


		//select the first item from the destination auto complete list
		waitFor(2000);
		List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();
		driver.findElement(By.cssSelector("#DepartDate")).sendKeys("25/11/2017");//sending the date


		//all fields filled in. Now click on search
		driver.findElement(By.id("SearchBtn")).click();


		waitFor(5000);
		//verify that result appears for the provided journey search
		Assert.assertTrue(isElementPresent(By.className("searchSummary")));

		//close the browser
		driver.quit();

	}


	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}


	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
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
}
