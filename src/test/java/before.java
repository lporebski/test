import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

public class before {
	WebDriver driver;
	@BeforeTest
	public void before_test()  
	  {  
		 
		 String pathToGeckoDriver = "E:\\Filmly";
	        //Setting webdriver.gecko.driver property
	        System.setProperty("webdriver.gecko.driver", pathToGeckoDriver + "\\geckodriver.exe");
	        
	        //Instantiating driver object and launching browser
	        driver = new FirefoxDriver();
	        
	        //Using get() method to open a webpage
	        driver.get("https://filmly-app.vercel.app"); 
	  }  
}
