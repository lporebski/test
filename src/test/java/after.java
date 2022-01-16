import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class after {
	WebDriver driver;
	@AfterTest
	public void after_test()  
	  {  
	        //Using get() method to open a webpage
	        driver.close();
	  } 
}
