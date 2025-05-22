package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class attendancePage {
	 private WebDriver driver;
	    
	    public attendancePage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    @FindBy(xpath =  "//a[normalize-space()='Attendance']")
	    private WebElement attendanceTab;
	    
	    @FindBy(id="attendance_file")
	    private WebElement attendanceFile;
	    
	    @FindBy(xpath = "//button[normalize-space()='Upload']")
	    private WebElement uploadAttendanceBtn;
	    
	    @FindBy(id="year")
	    private WebElement year;

	    public void clickOnAttendanceTab() {
	    	attendanceTab.click();
	    }
	    
	    @FindBy(xpath = "//*[@id=\"filterAttendance\"]")
	    private WebElement filterAttendanceBtn;
	    
	    public void uploadAttendance(String filePath) {
	    	attendanceFile.sendKeys(filePath);
	    }
	    
	    public void uploadAttendanceButton() {
	    uploadAttendanceBtn.click();
	    }
	    
	    public void viewAttendance() {
	    	Select slt=new Select(year);
	    	slt.selectByValue("2025");
	    }
	    
	    public void viewAttendanceBtn() {
	    	filterAttendanceBtn.click();
	    }
}
