package task2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Task2 {
	static WebDriver driver;
	static String homeurl="https://www.saucedemo.com/";
	public static void login() {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		Assert.assertNotEquals(homeurl, driver.getCurrentUrl());		
	}
	public static void filteratoz() throws InterruptedException {
		WebElement drop=driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select"));
		drop.click();
		Thread.sleep(1500);
		drop.sendKeys(Keys.ENTER);
		Thread.sleep(1500);
		List<WebElement> products=driver.findElements(By.className("inventory_item_name"));
		List<WebElement> prices=driver.findElements(By.className("inventory_item_price"));
		System.out.println("A TO Z : ");
		System.out.println(products.get(0).getText()+" "+prices.get(0).getText());
	}
	public static void filterztoa() throws InterruptedException {
		WebElement drop=driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select"));
		drop.click();
		Thread.sleep(1500);
		drop.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
		Thread.sleep(1500);
		List<WebElement> products=driver.findElements(By.className("inventory_item_name"));
		List<WebElement> prices=driver.findElements(By.className("inventory_item_price"));
		System.out.println("Z TO A : ");
		System.out.println(products.get(0).getText()+" "+prices.get(0).getText());	
	}
	public static void filterltoh() throws InterruptedException {
		WebElement drop=driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select"));
		drop.click();
		Thread.sleep(1500);
		drop.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
		Thread.sleep(1500);
		List<WebElement> products=driver.findElements(By.className("inventory_item_name"));
		List<WebElement> prices=driver.findElements(By.className("inventory_item_price"));
		System.out.println("Low to High : ");
		System.out.println(products.get(0).getText()+" "+prices.get(0).getText());	
	}
	public static void filterhtol() throws InterruptedException {
		WebElement drop=driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select"));
		drop.click();
		Thread.sleep(1500);
		drop.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
		Thread.sleep(1500);
		List<WebElement> products=driver.findElements(By.className("inventory_item_name"));
		List<WebElement> prices=driver.findElements(By.className("inventory_item_price"));
		System.out.println("High to Low : ");
		System.out.println(products.get(0).getText()+" "+prices.get(0).getText());		
	}
	public static void main(String args[]) throws InterruptedException {
		WebDriverManager.edgedriver().setup();
		EdgeOptions opt=new EdgeOptions();
		opt.addArguments("--remote-allow-origins=*");
		driver=new EdgeDriver(opt);
		driver.manage().window().maximize();
		driver.get(homeurl);
		login();
		filteratoz();
		filterztoa();
		filterltoh();
		filterhtol();
	}
}
