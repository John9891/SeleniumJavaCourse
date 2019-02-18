package tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Helpers;
import helpers.Screenshooter;
import helpers.WebDriverManager;
import pages.PageLogin;
import pages.PageLogon;
import pages.PageReservation;

public class Tests {
	private WebDriver driver;
	ArrayList<String>	tabs;
	
	@BeforeMethod
	public void SetUp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
		driver = new ChromeDriver();		
		driver.navigate().to("http://www.newtours.demoaut.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Abrir varios tabs
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor)driver;
		String googleWindow = "window.open('http://www.google.com')";
		javaScriptExecutor.executeScript(googleWindow);
		tabs = new ArrayList<String> (driver.getWindowHandles());
		
		//Helpers helper = new Helpers();
		//helper.sleep_seconds(5);		
	}

	//Test de login incorrecto
	@Test
	public void prueba1() {
		
		WebDriverManager.setWindowSize(driver, "maximize");
		//Manipulando tabs
		driver.switchTo().window(tabs.get(1)).navigate().to("https://www.youtube.com/");
		driver.switchTo().window(tabs.get(0));
		
		PageLogin pageLogin = new PageLogin(driver);
		PageLogon pageLogon = new PageLogon(driver);
		pageLogin.Login("user", "user");
		pageLogon.assertLogonPage();
	}
	
	//Test de login correcto
	@Test
	public void prueba2() {
		WebDriverManager.setWindowSize(driver, "fullscreen");
		PageLogin pageLogin = new PageLogin(driver);
		PageReservation pageReservation = new PageReservation(driver);
		pageLogin.Login("mercury", "mercury");		
		pageReservation.assertPage();
		}
	
	@Test
	public void prueba3() {
		WebDriverManager.setWindowSize(driver, 400, 400);
		PageLogin pageLogin = new PageLogin(driver);
		PageReservation pageReservation = new PageReservation(driver);
		pageLogin.Login("mercury", "mercury");		
		pageReservation.selectPassengers(2);
		pageReservation.selectDepart(3);
		pageReservation.selectArrive("Seattle");
		}	
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(!result.isSuccess()) {
			Screenshooter.takeScreenshot("Error", driver);
		}
		
		//driver.close();
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0)).close();
	}
	
	
	
}
