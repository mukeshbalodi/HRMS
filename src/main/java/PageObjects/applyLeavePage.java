package PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class applyLeavePage {
	 private WebDriver driver;
	    
	    public applyLeavePage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    @FindBy(id = "startDate")
	    private WebElement leaveStartdate;
	    
	    @FindBy(id = "endDate")
	    private WebElement leaveEnddate;
	    
	    @FindBy(id="reasonForLeave")
	    private WebElement reasonForLeave;
	    
	    @FindBy(xpath = "//*[@id=\"leaveForm\"]/button")
	    private WebElement leaveBtn;
	    
	    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/button[1]/span")
	    private WebElement applyLeaveTab;
	    
	    public void enterStartDate(String startDate) {
	         JavascriptExecutor js = (JavascriptExecutor) driver;
	         js.executeScript("document.getElementById('startDate').value = arguments[0];", startDate);
	    }
	    
	    public void applyLeaveTab() {
	    	applyLeaveTab.click();
	    }
	    
	    public void enterEndDate(String endDate) {
	         JavascriptExecutor js = (JavascriptExecutor) driver;
	         js.executeScript("document.getElementById('endDate').value = arguments[0];", endDate);
	    }
	    
	   public void leaveReason(String reason) {
		   reasonForLeave.clear();
		   reasonForLeave.sendKeys(reason);
	   } 
	   
	   public void applyLeaveBtn() {
		   leaveBtn.click();
	   }
}
