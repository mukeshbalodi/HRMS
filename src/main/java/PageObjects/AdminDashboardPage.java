package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import net.bytebuddy.asm.MemberSubstitution.FieldValue;

import java.time.Duration;
import java.util.List;

public class AdminDashboardPage {
    WebDriver driver;
    WebDriverWait wait;

    public AdminDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//*[@id=\"topNav\"]/ul[1]/li[4]/a")
    private WebElement employeeBtn;

    @FindBy(xpath = "//i[@class='material-icons align-middle me-1']")
    private WebElement addNewEmployeeBtn;

    // Personal
    @FindBy(id = "emp_img")
    private WebElement profilePhoto;

    @FindBy(id = "emp_code")
    private WebElement empCode;

    @FindBy(id = "firstname")
    private WebElement firstName;

    @FindBy(id = "middlename")
    private WebElement middleName;

    @FindBy(id = "lastname")
    private WebElement lastName;

    @FindBy(id = "gender")
    private WebElement genderDropdown;

    @FindBy(id = "dob")
    private WebElement dob;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "marital_status")
    private WebElement maritalStatus;

    @FindBy(id = "contact")
    private WebElement contact;

    @FindBy(id = "address")
    private WebElement address;

    // Professional
    @FindBy(id = "professional-tab")
    private WebElement professionalTab;

    @FindBy(id = "id_user_type")
    private WebElement userTypeDropdown;

    @FindBy(id = "department_id")
    private WebElement departmentDropdown;

    @FindBy(id = "position_id")
    private WebElement designationDropdown;

    @FindBy(id = "reporting_mng")
    private WebElement reportingManagerDropdown;

    @FindBy(id = "education_qualification")
    private WebElement education;

    @FindBy(name = "last_emp_details")
    private WebElement lastEmployment;

    @FindBy(id = "date_hired")
    private WebElement dateHired;

    @FindBy(id = "status")
    private WebElement statusDropdown;

    // Emergency
    @FindBy(id = "emergency-tab")
    private WebElement emergencyTab;

    @FindBy(id = "emergency_contact_name")
    private WebElement emergencyContactName;

    @FindBy(id = "emergency_contact_number")
    private WebElement emergencyContactNumber;

    // Documents
    @FindBy(id = "documents-tab")
    private WebElement documentsTab;

    @FindBy(id = "appointment_document")
    private WebElement appointmentDoc;

    @FindBy(id = "offer_letter")
    private WebElement offerLetter;

    @FindBy(id = "company_policies")
    private WebElement companyPolicies;
    
    
    @FindBy(id="aadhar_card")
    private WebElement aadharCard;
    
    @FindBy(id="pan_card")
    private WebElement panCard;
    
    
    
    @FindBy(id="edu_cert")
    private WebElement eduCertificates;
    
    
    @FindBy(id="pay_compensation-tab")
    private WebElement paycompensationTab;
    
    
    @FindBy(id="basic_salary")
    private WebElement basicSalary;
    
    @FindBy(id= "med_allow")
    private WebElement medicalallowance;
    
    @FindBy(id="conv")
    private WebElement conv;
    
    @FindBy(id="other_allowance")
    private WebElement otherAllowances;
    
    @FindBy(id="pf_contribution")
    private WebElement pfContribution;
    
    @FindBy(id="retention_bonus")
    private WebElement retentionBonus;
    
    @FindBy(id="ex_gratia")
    private WebElement exgratia;
    
    
    // Submit
    @FindBy(id = "submit")
    private WebElement submitBtn;

    @FindBy(xpath = "//*[@id=\"employeeTableBody\"]/tr[2]/td[5]/button/i")
    private WebElement assetBtn;
    
    @FindBy(id = "edit-tab")
    private WebElement editAsset;
    
    @FindBy(id = "name")
    private WebElement assetName;
    
    
    @FindBy(id="serial_number")
    private WebElement enterAssetSerial;
    
    
    @FindBy(id = "issue_date")  
    private WebElement dateInputField;
    
    @FindBy(id="asset_code")
    private WebElement assetCode;
    
    
    @FindBy(xpath = "//button[normalize-space(text()) = 'Save Asset']")
    private WebElement saveAssetBtn;
    
    @FindBy(xpath = "//i[normalize-space(text())='delete']")
    private WebElement deleteAssetBtn;
    
    
    @FindBy(xpath = "//*[@id=\"assetsTableBody\"]/tr/td[6]/button[1]/i")
    private WebElement editAssetBtn;
    
    @FindBy(xpath = "//*[@id=\"employeeTableBody\"]/tr[1]/td[6]/button/i")
    private WebElement leaveStatusIcon;
    
    @FindBy(xpath = "//*[@id=\"april\"]")
    private WebElement enterLeave;
	
    @FindBy(xpath = "//*[@id=\"leaveForm\"]/div[3]/button")
    private WebElement saveLeaveStatusBtn;
    
    @FindBy(xpath = "//*[@id=\"employeeTableBody\"]/tr[3]/td[7]/button")
    private WebElement bankDetailsIcon;
    
    @FindBy(xpath = "//*[@id=\"pan_card_number\"]")
    private WebElement panNumber;
    
    @FindBy(xpath = "//*[@id=\"acc_holder_name\"]")
    private WebElement accountHolderName;
    
    @FindBy(xpath = "//*[@id=\"acc_no\"]")
    private WebElement accountNumber;
    
    @FindBy(xpath = "//*[@id=\"IFSC\"]")
    private WebElement ifsc;
    
    @FindBy(xpath = "//*[@id=\"bnk_name\"]")
    private WebElement bankName;
    
    @FindBy(xpath = "//*[@id=\"UploadDocumentForm\"]/div[4]/button")
    private WebElement saveBankDetailsButton;
    
    @FindBy(id="address")
    private WebElement editEmpAddress;
    
    // Click actions
    public void clickOnEmployeeBtn() {
        employeeBtn.click();
    }

    public void clickAddNewEmployee() {
        addNewEmployeeBtn.click();
    }
    
   @FindBy(xpath = "//*[@id=\"employeeTableBody\"]/tr[1]/td[9]/button[2]")
   private WebElement editEmpIcon;
   
   @FindBy(id = "submit")
   private WebElement saveAddress;
   
   @FindBy(xpath = "//*[@id=\"employeeTableBody\"]/tr[9]/td[9]/button[3]/i")
   private WebElement deleteEmployeeIcon;
   
   
   @FindBy(id="confirm")
   private WebElement confirmDeleteEmployee;
   
   
   @FindBy(xpath = "//*[@id=\"employeeTableBody\"]/tr[8]/td[10]/button/i")
   private WebElement exitDetailsIcon;

   @FindBy(id="clearance_form")
   private WebElement uploadClearanceForm;
   
   @FindBy(id="experience_letter")
   private WebElement uploadExpLetter; 
   
   @FindBy(id="relieving_letter")
   private WebElement uploadRelievingLetter;
   
   @FindBy(id="no_dues_form")
   private WebElement  uploadNoDuesForm;
   
   @FindBy(xpath = "//input[@class='form-check-input']")
   private List<WebElement> chkBoxes;
   
   @FindBy(xpath = "//*[@id=\"exitForm\"]/div[4]/button")
   private WebElement saveExitDetailsBtn;
   
   @FindBy(id="searchInput")
   private WebElement empSearch;
   
   @FindBy(xpath = "/html/body/div[2]/div/div[1]/div[2]/div/div/div/div/table/tbody/tr[8]/td[3]")
   private WebElement verifyName;
   
    // Fill the form
    public void fillForm(String[] row) {
        profilePhoto.sendKeys(row[0]);
        empCode.sendKeys(row[1]);
        firstName.sendKeys(row[2]);
        middleName.sendKeys(row[3]);
        lastName.sendKeys(row[4]);

        new Select(genderDropdown).selectByVisibleText(row[5]);

        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", dob, row[6]);

        email.sendKeys(row[7]);
        maritalStatus.sendKeys(row[8]);
        contact.sendKeys(row[9]);
        address.sendKeys(row[10]);

        professionalTab.click();

        wait.until(ExpectedConditions.visibilityOf(userTypeDropdown));
        new Select(userTypeDropdown).selectByValue(row[11]);

        wait.until(ExpectedConditions.visibilityOf(departmentDropdown));
        new Select(departmentDropdown).selectByValue(row[12]);

        new Select(designationDropdown).selectByValue(row[13]);
        new Select(reportingManagerDropdown).selectByValue(row[14]);

        education.sendKeys(row[15]);
        lastEmployment.sendKeys(row[16]);

        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", dateHired, row[17]);

        new Select(statusDropdown).selectByVisibleText(row[18]);

        emergencyTab.click();
        wait.until(ExpectedConditions.visibilityOf(emergencyContactName));
        emergencyContactName.sendKeys(row[19]);
        emergencyContactNumber.sendKeys(row[20]);

        documentsTab.click();
        wait.until(ExpectedConditions.visibilityOf(appointmentDoc));
        appointmentDoc.sendKeys(row[21]);
        offerLetter.sendKeys(row[22]);
        companyPolicies.sendKeys(row[23]);
        aadharCard.sendKeys(row[24]);
        panCard.sendKeys(row[25]);
        eduCertificates.sendKeys(row[26]);
        
        //salary Tab
        paycompensationTab.click();
       basicSalary.sendKeys(row[27]);
       medicalallowance.sendKeys(row[28]);
       conv.sendKeys(row[29]);
       otherAllowances.sendKeys(row[30]);
       pfContribution.sendKeys(row[31]);
       retentionBonus.sendKeys(row[32]);
       exgratia.sendKeys(row[33]);
       
      
        
    }

    public void submitForm() {
        submitBtn.click();
    }
    
    public void assetBtn() {
    	assetBtn.click();
    }
    
    public void editAsset() {
    	editAsset.click();
    }
    
    public void enterAssetName(String enterAssetName) {
    	assetName.sendKeys(enterAssetName);
    }
    
    public void enterAssetSerial(String enterSerialNo) {
    	enterAssetSerial.clear();
    	enterAssetSerial.sendKeys(enterSerialNo);
    }
    
    public void setDateUsingJS(String date) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];" +
                         "arguments[0].dispatchEvent(new Event('change'));", 
                         dateInputField, date);
    }
    
    
    public void enterAssetCode(String enterAssetCode) {
    	assetCode.sendKeys(enterAssetCode);
    }
    
    public void saveAsset() {
    	saveAssetBtn.click();
    }
    
    public void deleteAsset() {
    	deleteAssetBtn.click();
    }
    
    public void editAssetDetails() {
    	editAssetBtn.click();
    	
    }
    
    public void updateLeave() {
    	leaveStatusIcon.click();
    }
    
    public void enterLeave(String days) {
    	enterLeave.clear();
    	enterLeave.sendKeys(days);
    }
    
    public void saveLeaveStatus() {
    	saveLeaveStatusBtn.click();
    }
    
    public void enterBankDetails() {
    	bankDetailsIcon.click();
    }
    
    public void enterPanNumber(String enterPanNumber) {
    	panNumber.clear();
    	panNumber.sendKeys(enterPanNumber);
    }
    
    public void enterAccountName(String enterAccountName) {
    	accountHolderName.clear();
    	accountHolderName.sendKeys(enterAccountName);
    }
    
    public void enterAccountNumber(String accountNum) {
    	accountNumber.clear();
    	accountNumber.sendKeys(accountNum);
    	    }
    
    public void enterIfsc(String ifscCode) {
    	ifsc.clear();
    	ifsc.sendKeys(ifscCode);
    }
    
    public void enterBankName(String bank) {
    	bankName.clear();
    	bankName.sendKeys(bank);
    }
    
    public void saveBankDetails() {
    	saveBankDetailsButton.click();
    }
    
    public void editEmpDetails() {
    	editEmpIcon.click();
    }
    
    public void editEmpAddress(String address) {
    	editEmpAddress.clear();
    	editEmpAddress.sendKeys(address);
    }
    
    public void submitAddress() {
    	saveAddress.click();
    }
    
    public void deleteEmployee() {
    	deleteEmployeeIcon.click();
    }
    
    public void confirmDeleteEmp() {
    	confirmDeleteEmployee.click();
    }
    
    public void manageExitDetails() {
    	exitDetailsIcon.click();
    }
    
    public void exitReasonDropdown() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reason")));
    WebElement reasonDropdown = driver.findElement(By.id("reason"));
    new Select(reasonDropdown).selectByValue("Resignation");
    }
    
    public void uploadClearanceForm() {
    	uploadClearanceForm.sendKeys("C:\\Users\\enque\\Downloads\\BA_Finalprojectppt.pdf");
    }
    
    public void uploadExperienceLetter() {
    	uploadExpLetter.sendKeys("C:\\Users\\enque\\Downloads\\BA_Finalprojectppt.pdf");
    }
    
    public void uploadRelievingLetter() {
    	uploadRelievingLetter.sendKeys("C:\\Users\\enque\\Downloads\\BA_Finalprojectppt.pdf");
    }
    
    public void uploadNoDuesForm() {
    	uploadNoDuesForm.sendKeys("C:\\Users\\enque\\Downloads\\BA_Finalprojectppt.pdf");
    }
    
    public void clickAllCheckBoxes() {
        for (WebElement chkBox : chkBoxes) {
            if (!chkBox.isSelected()) { 
                chkBox.click();
            }
        }
    }
    
    public void saveExitDetails() {
    	saveExitDetailsBtn.click();
    }
    
    public void searchEmployee(String empName) {
    	empSearch.clear();
    	empSearch.sendKeys(empName);
    }
    
    public String verifySearch() {
    	return verifyName.getText();
    }
}
