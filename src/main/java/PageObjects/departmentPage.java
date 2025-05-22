package PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class departmentPage {
	  WebDriver driver;
	    WebDriverWait wait;

	    public departmentPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    @FindBy(xpath = "//a[@href='/department/']")
	    private WebElement departmentBtn;
	    
	    @FindBy(id="create_new")
	    private WebElement createDept;
	    
	    @FindBy(id="name")
	    private WebElement departmentName;
	    
	    
	    @FindBy(id="hod")
	    private WebElement departmentHod;
	    
	    @FindBy(id="submit")
	    private WebElement saveDept;
	    
	    public void clickOnDepartment() {
	    	departmentBtn.click();
	    	}
	    public void createNewDept() {
	    	createDept.click();
	    	}
	    public void enterDeptName(String DeptName) {
	    	departmentName.sendKeys(DeptName);
	    }
	    
	    public void enterHod(String Hod) {
	    	departmentHod.sendKeys(Hod);
	    }
	    
	    public void saveDept() {
	    	saveDept.click();
	    }
}
