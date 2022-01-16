import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class Filmly_0001 extends variables{
	
	WebDriver driver;
	
	public boolean click(String xpath) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath(xpath)).click();
		return true;
	}
	
	public boolean input(String xpath, String value){
		driver.findElement(By.xpath(xpath)).sendKeys(value);
		return true;
	}
	
	public boolean ifexist(String xpath) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		List<WebElement> exists = driver.findElements(By.xpath(xpath));
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		if(exists.size() != 0){
			 //If list size is non-zero, element is present
			 System.out.println("Element present");
			 return true;
			}
			else{
			 //Else if size is 0, then element is not present
			 System.out.println("Element not present");
			 return false;
			}
		
	}


	@BeforeClass
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
//		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.navigate().to("https://filmly-app.vercel.app/filmy");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.MILLISECONDS);
	}

	@Test
	public void Filmly_0001() throws InterruptedException
	  {

		Assert.assertTrue(click(".//header//a[normalize-space(text())='Zarejestruj się']"));
		Assert.assertTrue(input(".//form//input[@placeholder='Nickname']", variables.nickname));
		Assert.assertTrue(input(".//form//input[@placeholder='E-mail']", variables.email));
		Assert.assertTrue(input(".//form//input[@placeholder='URL do avatara']" ,"https://i.stack.imgur.com/l60Hf.png"));
		Assert.assertTrue(input(".//form//input[@placeholder='Has³o']", variables.password));
		Assert.assertTrue(input(".//form//input[@placeholder='Powtórz has³o']", variables.password));
		Assert.assertTrue(click(".//form//button[normalize-space(text())='Zarejestruj siê']"));
		Assert.assertTrue(click(".//header//a[normalize-space(text())='Zaloguj siê']"));
		Assert.assertTrue(ifexist(".//h2[normalize-space(text())='Zaloguj siê']"));
		Assert.assertTrue(input(".//form//input[@placeholder='E-mail']", variables.email));
		Assert.assertTrue(input(".//form//input[@placeholder='Has³o']", variables.password));
		Assert.assertTrue(click(".//form//button[normalize-space(text())='Zaloguj siê']"));
		Assert.assertTrue(ifexist(".//header//a[normalize-space(text())='Wyloguj siê']"));
		Assert.assertTrue(click(".//header//a[normalize-space(text())='Wyloguj siê']"));
	  }

	@AfterClass
	public void tearDown(){
		if (driver != null) {
			driver.quit();
		}
	}
	
	
	
}
