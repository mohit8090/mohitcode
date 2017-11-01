import com.sun.javafx.PlatformUtil;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignInTest {
	FlightBookingTest flight=new  FlightBookingTest();   

	  WebDriver driver ;
	  @BeforeTest
		public void driverLunch()//before test method will execute before test method 
		{ 
			setDriverPath();
			driver = new ChromeDriver();
	        driver.get("https://www.cleartrip.com/");
	        waitFor(2000);
	      }
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
    	driver.findElement(By.linkText("Your trips")).click();
    	driver.findElement(By.id("SignIn")).click();
    	waitFor(2000);
    	driver.switchTo().frame("modal_window");//Switching to frame 
    	waitFor(2000);
    	driver.findElement(By.id("signInButton")).click();
    	String errors1 = driver.findElement(By.id("errors1")).getText();
    	Assert.assertTrue(errors1.contains("There were errors in your submission"));                                                                   
        
    	driver.close();		
            }		
    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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

 