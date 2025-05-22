package TestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import Base.BaseClassLambda;

import PageObjects.LoginAndLogoutPage;
import Utils.ConfigReader;

public class herokuapp_Lambda extends BaseClassLambda{
	LoginAndLogoutPage page; 
	
	ConfigReader config; 
	


	    @Test(priority = 1)
	    public void ValidLogin() {
	        LoginAndLogoutPage page = new LoginAndLogoutPage(driver);
	        config = new ConfigReader();

	        driver.get("https://the-internet.herokuapp.com/login");
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	        page.enterUsername(config.getAppValidUsername());
	        page.enterPassword(config.getAppValidPassword());
	        page.clickLogin();

	        WebElement flashMessage = driver.findElement(By.id("flash"));
	        assertTrue(flashMessage.isDisplayed(), "Login failed: Success message not displayed.");
	    
	}
	    
	 

	        @Test(priority = 2)
	        public void inValidLogin() {
	            page = new LoginAndLogoutPage(driver);
	            config = new ConfigReader();

	            driver.get("https://the-internet.herokuapp.com/login");
	            driver.manage().window().maximize();
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	            page.enterUsername(config.getAppInvalidUsername());
	            page.enterPassword(config.getAppInvalidPassword());
	            page.clickLogin();

	            WebElement logoutButton = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/a[1]/i[1]"));
		        assertTrue(logoutButton.isDisplayed(), "Login failed: Success message not displayed.");
	        
	    }


	      
}
