package org;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class FlightSearchSteps {
	
	public WebDriver driver;
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	
	@Given("User is on the MMT website")
	public void user_is_on_the_mmt_website() {
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\dell\\eclipse-workspace2\\org\\server\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.makemytrip.com/flights/");
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	@When("User searches for flights from Delhi to Mumbai")
	public void user_searches_for_flights_from_delhi_to_mumbai() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id='fromCity']")).click();
	       driver.findElement(By.xpath("//*[@placeholder='From']")).sendKeys("DELHI");
	       driver.findElement(By.xpath("//*[text()='DEL']")).click();
	       driver.findElement(By.xpath("//*[@id='toCity']")).click();
	       driver.findElement(By.xpath("//*[@placeholder='To']")).sendKeys("MUMBAI");
	       driver.findElement(By.xpath("//*[text()='BOM']")).click();
	       
	}
	
	@When("User sorts the results by departure")
	public void user_sorts_the_results_by_departure() throws InterruptedException {
		driver.findElement(By.xpath("//div[text() = 'March 2023']/../..//div[@class='dateInnerCell']/p[text()='25']")).click();
	       driver.findElement(By.xpath("//*[text() = 'Search']")).click();
	       driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	       driver.findElement(By.xpath("//*[text()='OKAY, GOT IT!']")).click();
	       driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	       driver.findElement(By.xpath("//*[@id='sorting-togglers']//span[text()='Departure']")).click();
	}
	
	@Then("User prints the airline name and price with the 2nd lowest price")
	public void user_prints_the_airline_name_and_price_with_the_2nd_lowest_price() {
		List<WebElement> prices = driver.findElements(By.xpath("//*[@class='priceSection']//p"));
	       List<String> priceTexts = prices.stream().map(WebElement::getText).collect(Collectors.toList());
	       List<Integer> intPrices = new ArrayList<>();
	       for (String priceText : priceTexts) {
	           int price = Integer.parseInt(priceText.replaceAll("[₹,\\s]", ""));
	           intPrices.add(price);
	       }
	       Collections.sort(intPrices);
	       System.out.println("Price---->" + String.format("%,d", intPrices.get(1)));

	       String secondLowestPrice =  String.format("%,d", intPrices.get(1));
	       
	       System.out.println("2nd Lowest Airline Name"+driver.findElement(By.xpath("(//p[contains(text(),'"+secondLowestPrice+"')]//ancestor::div[@class='priceSection']"
	       		+ "/preceding-sibling::div[contains(@class,'airline-info')]"
	       		+ "//p[contains(@class,'airlineName')])[1]")).getText());

		
		
		driver.quit();
		
	}
}


