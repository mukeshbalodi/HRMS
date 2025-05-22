package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class designationPage {
    WebDriver driver;
    WebDriverWait wait;

    public designationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//a[normalize-space()='Designations']")
    private WebElement designationTab;

    @FindBy(xpath = "//i[normalize-space()='add']")
    private WebElement addDesignationBtn;

    @FindBy(id = "name")
    private WebElement designationField;

    @FindBy(id = "submit")
    private WebElement saveDesignationBtn;

    public void clickOnDesignationTab() {
        designationTab.click();
    }

    public void addDesignationBtn() {
        addDesignationBtn.click();
    }

    public void enterDesignation(String Designation) {
        designationField.sendKeys(Designation);
    }

    public void saveDesignationBtn() {
    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveDesignationBtn);
}
}