package week4.day2;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Nykaa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		{
			WebDriverManager.chromedriver().setup();
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.get("https://www.nykaa.com/");
			Actions action=new Actions(driver);
			WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
			action.moveToElement(brand).perform();
			driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris",Keys.ENTER);
			driver.findElement(By.xpath("//div[@id=\"scroller-container\"]/div[2]/a")).click();
			System.out.println(driver.getTitle());
			driver.findElement(By.xpath("//div[@id=\"filter-sort\"]/div/div/button")).click();
			driver.findElement(By.xpath("//div[@id=\"filter-sort\"]/div/div/ul/div[4]/label/div[2]")).click();
			driver.findElement(By.xpath("//div[@id='first-filter']/div")).click();
			driver.findElement(By.xpath("//div[@id='first-filter']/ul/ul/li")).click();
			driver.findElement(By.xpath("//div[@id='first-filter']/ul/ul/li/ul/li/div")).click();
			driver.findElement(By.xpath("//ul[@id=\"custom-scroll\"]/ul/li/ul/li/ul/li[1]/div/label/div[2]")).click();
			driver.findElement(By.xpath("//div[@class='sidebar__inner']/div/div[6]/div")).click();
			driver.findElement(By.xpath("//ul[@id=\"custom-scroll\"]/div/div[4]/label/div[2]")).click();
			String filter_value=driver.findElement(By.xpath("//div[@id=\"filters-listing\"]/div[1]/div[2]/div[1]/span")).getText();
			if(filter_value.equalsIgnoreCase("Shampoo"))
			{
				System.out.println("Shampoo filtered");
			}
			else
			{
				System.out.println("Shampoo not filter");
			}
			driver.findElement(By.xpath("//div[@id=\"product-list-wrap\"]/div[1]/div/div[1]/a/div[2]/div[1]")).click();
			Set<String> win = driver.getWindowHandles();
			List<String> win_list=new ArrayList<String>(win);
			driver.switchTo().window(win_list.get(1));
			WebElement select = driver.findElement(By.xpath("//select[@title='SIZE']"));
			Select pick=new Select(select);
			pick.selectByVisibleText("175ml");
			String mrp = driver.findElement(By.xpath("//div[@id=\"app\"]/div/div[2]/div[2]/div/div[1]/span[2]")).getText();
			System.out.println("The MRP is : "+mrp);
			driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
			driver.findElement(By.xpath("//div[@id=\"header_id\"]/div[2]/div/div[2]/div[2]/button")).click();
			WebElement cart_frame = driver.findElement(By.xpath("//div[@id=\"portal-root\"]/div/div[1]/iframe"));
			driver.switchTo().frame(cart_frame);
			String total = driver.findElement(By.xpath("//div[@class='first-col']/div")).getText();
			System.out.println("The grand total amount is "+total);
			driver.findElement(By.xpath("//div[@class='second-col']/button/i")).click();
			driver.switchTo().defaultContent();
			driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
			String grand_total = driver.findElement(By.xpath("(//div[@class='value'])[2]")).getText();
			System.out.println("The Grand total is "+grand_total);
			if(total.equalsIgnoreCase(grand_total))
			{
				System.out.println("Total is same");
			}
			else
			{
				System.out.println("Total is not same");
			}
			driver.quit();
		}
	}

}
