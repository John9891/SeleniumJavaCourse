package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PageReservation {
	private WebDriver driver;
	private By titleText;
	private By passengersDrop;
	private By fromDrop;
	private By arriveDrop;
	public PageReservation(WebDriver driver) {
		this.driver = driver;
		titleText = By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/font");
		passengersDrop = By.name("passCount");
		fromDrop = By.name("fromPort");
		arriveDrop = By.name("toPort");
	}
	
	public void assertPage() {
		Assert.assertTrue(driver.findElement(titleText).getText().contains("Flight Finder to search"));
	}
	
	public void selectPassengers(int cant) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cantPassengers = wait.until(ExpectedConditions.presenceOfElementLocated(passengersDrop)); 
		Select selectPassengers = new Select(driver.findElement(passengersDrop));
		selectPassengers.selectByVisibleText(Integer.toString(cant));	
	}
	
	public void selectDepart(int index) {
		Select selectDepart = new Select(driver.findElement(fromDrop));
		selectDepart.selectByIndex(index);		
	}
	
	public void selectArrive(String city) {
		Select selectarrive = new Select(driver.findElement(arriveDrop));
		selectarrive.selectByValue(city);
		
	}
	
	
	
	

}
