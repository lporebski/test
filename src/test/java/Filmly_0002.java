import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class Filmly_0002 extends variables{

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
		Thread.sleep(2000);
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
		options.setHeadless(true);
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-extensions");
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("--proxy-server='direct://'");
		options.addArguments("--proxy-bypass-list=*");
		options.addArguments("--start-maximized");
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.navigate().to("https://filmly-app.vercel.app");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.MILLISECONDS);
	}

	@Test
	public void Filmly_0002() throws InterruptedException
	{

		Assert.assertTrue(click(".//header//a[normalize-space(text())='Zaloguj sie']"));
		Assert.assertTrue(input(".//form//input[@placeholder='E-mail']", variables.email));
		Assert.assertTrue(input(".//form//input[@name='password']", variables.password));
		Assert.assertTrue(click(".//form//button"));
		Assert.assertTrue(click(".//main//div//h2[normalize-space(text())='Efekt motyla']/.."));
		Assert.assertTrue(ifexist(".//main//div//h2[normalize-space(text())='Komentarze']"));
		driver.findElement(By.xpath(".//main//div//button[normalize-space(text())='Dodaj']")).sendKeys(Keys.END);
		Assert.assertTrue(input(".//main//div//textarea[@placeholder='Dodaj publiczny komentarz']", "Komentarz_ATp0002"));
		Assert.assertTrue(click(".//main//div//button[normalize-space(text())='Dodaj']"));
		Assert.assertTrue(click(".//main//div//button[normalize-space(text())='Dodaj']"));
		Assert.assertTrue(ifexist(".//main//div//p[normalize-space(text())='Komentarz_ATp0002']"));
		Assert.assertTrue(click(".//header//a[normalize-space(text())='Wyloguj sie']"));
	}

	@AfterTest
	public void tearDown(){
		if (driver != null) {
			driver.quit();
		}
	}



}
