package task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Task1 {
	static WebDriver driver;
	static String homeurl="https://www.saucedemo.com/";
	public static void login() {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		Assert.assertNotEquals(homeurl, driver.getCurrentUrl());		
		System.out.println("Login Passed");
	}
	public static void addToCart() throws InterruptedException {
		WebElement addtoc=driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"));
		String buttonmsg=addtoc.getText();
		addtoc.click();
		Thread.sleep(500);
		try {
			driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"));
			System.out.println("Add to Cart Passed");
		}
		catch(Exception e){
			System.out.println("Add to Cart Failed");
		}
	}
	public static void viewCart() {
		String homepage=driver.getCurrentUrl();
		driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
		Assert.assertNotEquals(homepage,driver.getCurrentUrl());
	}
	public static void checkOut() {
		driver.findElement(By.id("checkout")).click();
		Assert.assertEquals("Checkout: Your Information", driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText());
		System.out.println("Checkout Page Passed");
	}
	public static void fillCheckOut() throws InterruptedException {
		driver.findElement(By.id("first-name")).sendKeys("ABC");
		driver.findElement(By.id("last-name")).sendKeys("XYZ");
		driver.findElement(By.id("postal-code")).sendKeys("560076");
		Thread.sleep(500);
		driver.findElement(By.id("continue")).click();
		Thread.sleep(500);
		Assert.assertEquals("Checkout: Overview", driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText());
		System.out.println("Overview Page Passed");
	}
	public static void displayItem() {
		String itemname=driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();
		String itemprice=driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[3]/div[2]/div[2]/div")).getText();
		System.out.println("Item Name: "+itemname);
		System.out.println("Item Price: "+itemprice);
	}
	public static void checkTitleUrl() {
		Assert.assertEquals("Swag Labs", driver.getTitle());
		System.out.println("Assert Title Passed");
		Assert.assertEquals("https://www.saucedemo.com/checkout-step-two.html", driver.getCurrentUrl());
		System.out.println("Assert URL Passed");
	}
	public static void main(String args[]) throws InterruptedException{
		WebDriverManager.edgedriver().setup();
		EdgeOptions opt=new EdgeOptions();
		opt.addArguments("--remote-allow-origins=*");
		driver=new EdgeDriver(opt);
		driver.manage().window().maximize();
		driver.get(homeurl);
		login();
		Thread.sleep(500);
		addToCart();
		Thread.sleep(500);
		viewCart();
		Thread.sleep(500);
		checkOut();
		Thread.sleep(500);
		fillCheckOut();
		Thread.sleep(500);
		displayItem();
		Thread.sleep(500);
		checkTitleUrl();
	}
}
