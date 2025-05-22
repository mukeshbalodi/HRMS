package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class salaryPage {
	 private WebDriver driver;

	public salaryPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    @FindBy(xpath =  "//a[normalize-space()='Salary']")
	    private WebElement salaryTab;
	    
	    public void clickOnSalaryTab() {
	    	salaryTab.click();
	    }
	    
	    @FindBy(id =  "salary_file")
	    private WebElement salaryFile;
	    
	    @FindBy(xpath = "//*[@id=\"salary-upload-form\"]/div/button")
	    private WebElement uploadSalaryBtn;
	    
	    @FindBy(id = "year")
	    private WebElement salaryYear;
	    
	    
	    @FindBy(id = "filterSalary")
	    private WebElement filterSalary;
	    
	    public void uploadSalary(String salary) {
	    	salaryFile.sendKeys(salary);
	    }
	    
	    public void uploadSalaryButton() {
	    	uploadSalaryBtn.click();
	    }
	    
	    public void selectYear(String year) {
	    	
	    	Select slt = new Select(salaryYear);
	    	slt.selectByValue( year);
	    }
	    
	    public void filterSalaryBtn() {
	    	filterSalary.click();
	    }
	    
}
