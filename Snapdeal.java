package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver(); 
        driver.manage().window().maximize(); 
        driver.get("https://www.snapdeal.com/"); 
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement menFashion = driver.findElement(By.xpath("//span[@class = 'catText']"));
        Actions builder = new Actions(driver);
        builder.click(menFashion).perform();
        WebElement sportsShoe = driver.findElement(By.xpath("//span[text()='Sports Shoes']"));
        builder.click(sportsShoe).perform();
        WebElement trainingShoe = driver.findElement(By.xpath("//div[text()='Training Shoes']"));
        builder.click(trainingShoe).perform();
        String titleOfPage = driver.getCurrentUrl();
        System.out.println("Title of page before sorting :: "+titleOfPage);
        Thread.sleep(1000);
        WebElement sort = driver.findElement(By.xpath("//div[@class='sort-drop clearfix']//i[1]")); 
        WebElement lowToHigh = driver.findElement(By.xpath("(//li[@data-index='1'])[2]"));
        builder.click(sort).click(lowToHigh).perform();
        String titleOfSortedPage = driver.getCurrentUrl();
        System.out.println("Title of Sorted Page :: "+titleOfSortedPage);

        if(titleOfSortedPage.equals(titleOfPage)) {
        	System.out.println("The page is not sorted");
        }else {
        	System.out.println("The page is sorted");
        }
        Thread.sleep(1000);

        WebElement startingRange = driver.findElement(By.xpath("//input[@class='input-filter']"));
        startingRange.clear();
        builder.click(startingRange).sendKeys("900").perform();

        WebElement endRange = driver.findElement(By.xpath("(//input[@class='input-filter'])[2]"));
        endRange.clear();
        builder.click(endRange).sendKeys("1200").perform();
        WebElement goElement = driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow btn')]"));
        builder.click(goElement).perform();
        Thread.sleep(2000);

        WebElement color = driver.findElement(By.xpath("//label[@for='Color_s-Navy']"));
        builder.click(color).perform();
        Thread.sleep(2000);
        WebElement firstElement = driver.findElement(By.xpath("//p[@title='Red Tape Walking  Navy Training Shoes']"));
        builder.moveToElement(firstElement).perform();
        WebElement quickView = driver.findElement(By.xpath("//div[text()[normalize-space()='Quick View']]"));
        builder.click(quickView).perform();
        String firstWindow = driver.getWindowHandle();
        Set<String>windowHandles = driver.getWindowHandles();
        WebElement price = driver.findElement(By.xpath("//div[contains(@class,'product-price pdp-e-i-PAY-l')]//span[1]"));
        System.out.println("Price of the shoe is :: "+ price.getText());
        WebElement offerPercentage = driver.findElement(By.xpath("//span[text()='83% Off']"));
        System.out.println("Discount percentage of the product is :: "+offerPercentage.getText());
        File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./src/main/resources/snaps/SnapDeal01.jpg");
		FileUtils.copyFile(source, dest);
		driver.findElement(By.xpath("//div[contains(@class,'close close1')]")).click();

		driver.close();

	}

}
