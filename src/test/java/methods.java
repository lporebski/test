import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class methods {
	static WebDriver driver;
	public static boolean click(String xpath) throws InterruptedException {
		
		Thread.sleep(2000);
		driver.findElement(By.xpath(xpath)).click();
		return true;
	}
	
	public static boolean input(String xpath, String value){
		
		driver.findElement(By.xpath(xpath)).sendKeys(value);
		return true;
	}
	
	public static boolean ifexist(String xpath) throws InterruptedException {

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
}
