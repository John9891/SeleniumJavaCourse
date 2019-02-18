package helpers;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class WebDriverManager {
	
	public static void setWindowSize(WebDriver driver, String size) {
		
		if(size == "maximize") {
			driver.manage().window().maximize();
		}else if(size == "fullscreen") {
			driver.manage().window().fullscreen();
		}
	}
	
	public static void setWindowSize(WebDriver driver, int x, int y) {
		driver.manage().window().setSize(new Dimension(x, y));
		
	}
	
	public static void setPosition(WebDriver driver, int x, int y) {
		driver.manage().window().setPosition(new Point(600, 100));
	}

}
