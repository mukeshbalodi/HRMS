package PageObjects;

import java.time.Duration;import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginAndLogoutPage {

    private WebDriver driver;
    
    public LoginAndLogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@class='mdc-button mdc-button--raised w-100 mdc-ripple-upgraded']")
    private WebElement loginButton;
    
    @FindBy(xpath ="//*[@id=\"topNav\"]/ul[2]/li/a/img")
    private WebElement usernameicon;
    
    @FindBy(xpath = "//*[@id=\"topNav\"]/ul[2]/li/ul/li[2]/a")
    private WebElement logoutBtn;
    
    @FindBy(xpath = "//*[@id=\"topNav\"]/ul[2]/li/ul/li[1]/a")
    private WebElement changePasswordBtn;
    
    @FindBy(id="old_password")
    private WebElement oldpassword;
    
    @FindBy(xpath = "//*[@id=\"new_password\"]")
    private WebElement enterNewpassword;
    
    
    @FindBy(xpath = "//*[@id=\"confirm_password\"]")
    private WebElement confirmNewpassword;

	@FindBy(xpath = "//*[@id=\"chng_pass_Form\"]/div[4]/button")
	private WebElement savePasswordButton;
    
    public void enterUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

   
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    
    public void clickonUserIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(usernameicon));

        // Click to open dropdown
        usernameicon.click();

        // Wait for logout to appear
        wait.until(ExpectedConditions.visibilityOf(logoutBtn));
    }

    public void clickOnLogoutBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
    }

    public void changePassword() {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         wait.until(ExpectedConditions.elementToBeClickable(changePasswordBtn)).click();
    
    }
    
    public void enterOldPassword(String oldPassword) {
    	oldpassword.sendKeys(oldPassword);
    	
    }
    
    public void enterNewPassword(String newPassword) {
    	enterNewpassword.sendKeys(newPassword);
    }
    
    
    public void confirmNewPassword(String confirmPassword) {
    	confirmNewpassword.sendKeys(confirmPassword);
    }
    
    public void savePassword() {
    	savePasswordButton.click();
    }
    
    
    
}
