package TestCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.Status;
import com.opencsv.exceptions.CsvValidationException;

import Base.BaseClassLocal;
import PageObjects.AdminDashboardPage;
import PageObjects.LoginAndLogoutPage;
import PageObjects.applyLeavePage;
import PageObjects.attendancePage;
import PageObjects.departmentPage;
import PageObjects.designationPage;
import PageObjects.salaryPage;
import Utils.CSVReaderUtil;
import Utils.ConfigReader;

public class HRIS_Local extends BaseClassLocal {

    LoginAndLogoutPage page;
    ConfigReader config;
    AdminDashboardPage adminPage;
    departmentPage deptPage;
    designationPage degPage;
    attendancePage atdPage;
    salaryPage salPage;
    applyLeavePage  leavePage;
    @BeforeClass(alwaysRun = true)
    public void loginOnce() throws InterruptedException {
        config = new ConfigReader();
        page = new LoginAndLogoutPage(getDriver());

        getDriver().get("http://192.168.1.173:8003/");
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        page.enterUsername(config.username());
        getTest().log(Status.INFO, "Valid Username entered");

        page.enterPassword(config.userpassword());
        getTest().log(Status.INFO, "Valid Password entered");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[@class='mdc-button mdc-button--raised w-100 mdc-ripple-upgraded']")));
        loginButton.click();

        Thread.sleep(3000);
        getTest().log(Status.INFO, "Clicked on Login Button");

        WebElement dashboardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//a[@role='button']")));
        Assert.assertTrue(dashboardButton.isDisplayed(), "Dashboard not displayed");

        getTest().log(Status.PASS, "Successfully Logged In");
    }

    @Test(priority = 1)
    public void addNewEmployee() throws CsvValidationException, IOException {
        adminPage = new AdminDashboardPage(getDriver());

        adminPage.clickOnEmployeeBtn();
        adminPage.clickAddNewEmployee();
        List<String[]> data = CSVReaderUtil.readCSV();

        for (String[] row : data) {
            adminPage.fillForm(row);
            adminPage.submitForm();

            try {
                WebDriverWait alertWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
                alertWait.until(ExpectedConditions.alertIsPresent());
                getDriver().switchTo().alert().accept();
            } catch (TimeoutException e) {
                System.out.println("No alert appeared.");
            }
        }

        getTest().log(Status.PASS, "Employee(s) added successfully from CSV");
    }

    @Test(priority = 2)
    public void InValidLogin() throws InterruptedException {
        // New driver session needed for this independent test
        getDriver().get("http://192.168.1.98:8003/");
        page = new LoginAndLogoutPage(getDriver());
        config = new ConfigReader();

        page.enterUsername(config.getAppInvalidUsername());
        getTest().log(Status.INFO, "Invalid Username entered");

        page.enterPassword(config.getAppValidPassword());
        getTest().log(Status.INFO, "Valid Password entered");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[@class='mdc-button mdc-button--raised w-100 mdc-ripple-upgraded']")));
        loginButton.click();

        Thread.sleep(3000);

        WebElement errorAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@class='alert alert-danger err-msg']")));

        try {
            Assert.assertTrue(errorAlert.isDisplayed());
            getTest().log(Status.PASS, "Error message displayed: " + errorAlert.getText());
        } catch (AssertionError e) {
            getTest().log(Status.FAIL, "Error alert not found or not displayed");
        }
    }
    
    
    @Test(priority = 3)
    public void addSameEmployee() throws CsvValidationException, IOException {
        adminPage = new AdminDashboardPage(getDriver());

        adminPage.clickOnEmployeeBtn();
        adminPage.clickAddNewEmployee();
        List<String[]> data = CSVReaderUtil.readCSV();

        for (String[] row : data) {
            adminPage.fillForm(row);
            adminPage.submitForm();

            try {
                WebElement ele = getDriver().findElement(By.xpath("//div[@class='alert alert-danger err-msg']"));
                if (ele.isDisplayed()) {
                    getTest().log(Status.PASS, "Employee already exists: " + ele.getText());
                }
            } catch (NoSuchElementException e) {
                getTest().log(Status.FAIL, "Employee added successfully: " + String.join(", ", row));
            }
        }
    }
    
   @Test
    public void addDepartment() {
	   deptPage = new departmentPage(getDriver());
	   deptPage.clickOnDepartment();
	   getTest().log(Status.INFO, "clicked on Department Tab ");
	   deptPage.createNewDept();
	   getTest().log(Status.INFO, "Clicked on create new Department Button");
	   deptPage.enterDeptName("Asdf");
	   getTest().log(Status.INFO, "Department Name entered");
	   deptPage.enterHod("XYZ");
	   getTest().log(Status.INFO, "Department HOD Name Enetered");
	   deptPage.saveDept();
	   try {
           WebDriverWait alertWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
           alertWait.until(ExpectedConditions.alertIsPresent());
           getDriver().switchTo().alert().accept();
           getTest().log(Status.PASS, "Department added Successfully");
       } catch (TimeoutException e) {
           System.out.println("No alert appeared.");
           getTest().log(Status.FAIL, "Department not added");
         
       }
   }

	   
   @Test
   public void addDesignation() {
	   degPage = new designationPage(getDriver());
	   degPage.clickOnDesignationTab();
	   getTest().log(Status.INFO, "clicked on Designation Tab ");
	   degPage.addDesignationBtn();
	   getTest().log(Status.INFO, "Clicked on add Designation");
	   degPage.enterDesignation("Junior Manager");
	   getTest().log(Status.INFO, "Designation Entered");
	   getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	   degPage.saveDesignationBtn();
	   try {
          WebDriverWait alertWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
          alertWait.until(ExpectedConditions.alertIsPresent());
          getDriver().switchTo().alert().accept();
          getTest().log(Status.PASS, "Department added Successfully");
      } catch (TimeoutException e) {
          System.out.println("No alert appeared.");
          getTest().log(Status.FAIL, "Department not added");
        
      }
  }
   
   @Test
   public void addAssetDetails() throws InterruptedException {
	   adminPage = new AdminDashboardPage(getDriver());
	   adminPage.clickOnEmployeeBtn();
	   getTest().log(Status.INFO, "clicked on employee Tab");
	   adminPage.assetBtn();
	   getTest().log(Status.INFO, "clicked on Asset icon");

	   adminPage.editAsset();


	   adminPage.enterAssetName("Mouse");
	   getTest().log(Status.INFO, "entered Asset Name in asset Name field  ");
	   adminPage.enterAssetSerial("ENQMOUSE");
	   getTest().log(Status.INFO, "entered Asset Serial Number  in asset Serial number  field  ");

	   adminPage.setDateUsingJS("2025-02-16");
	   getTest().log(Status.INFO, "entered issue date in issue date  field  ");

	   adminPage.enterAssetCode("ENQMOUSE_1");
	   getTest().log(Status.INFO, "entered Asset Code in asset Code field  ");

	   adminPage.saveAsset();
	   getTest().log(Status.INFO, "Clicked on save asset Button");

	   try {
		   WebDriverWait alertWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		   alertWait.until(ExpectedConditions.alertIsPresent());
		   Alert alert = getDriver().switchTo().alert();
		   String alertText = alert.getText();
		   alert.accept();
		   if (alertText.equals("Asset saved successfully.")) {
			   getTest().log(Status.PASS, "Asset added Successfully");
			   }

		   else  getTest().log(Status.FAIL, alertText);
	   } catch (TimeoutException e) {
		   System.out.println("No alert appeared.");

	   }
	    }
   
   @Test
   public void deleteAsset() {
	   adminPage = new AdminDashboardPage(getDriver());
	   adminPage.clickOnEmployeeBtn();
	   getTest().log(Status.INFO, "clicked on employee Tab");
	   adminPage.assetBtn();
	   getTest().log(Status.INFO, "clicked on Asset icon");
	   adminPage.deleteAsset();
	   
	   getDriver().switchTo().alert().accept();
	   
	   try {
		   WebDriverWait alertWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		   alertWait.until(ExpectedConditions.alertIsPresent());
		   Alert alert = getDriver().switchTo().alert();
		   String alertText = alert.getText();
		   alert.accept();
		   if (alertText.equals("Asset deleted successfully.")) {
			   getTest().log(Status.PASS, "Asset deleted Successfully");
			   }

		   else  getTest().log(Status.FAIL, alertText);
	   } catch (TimeoutException e) {
		   System.out.println("No alert appeared.");

	   }
	  
   }
   
   @Test
   public void editAsset() {
	   adminPage = new AdminDashboardPage(getDriver());
	   adminPage.clickOnEmployeeBtn();
	   getTest().log(Status.INFO, "clicked on employee Tab");
	   adminPage.assetBtn();
	   getTest().log(Status.INFO, "clicked on Asset icon");
	   adminPage.editAssetDetails();
	   getTest().log(Status.INFO, "clicked on editAsset icon");
	   adminPage.enterAssetSerial("ENQMOUSE_New");
	   getTest().log(Status.INFO, "new Asset Serial Number entered");
	   adminPage.saveAsset();
	   
	   try {
		   WebDriverWait alertWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		   alertWait.until(ExpectedConditions.alertIsPresent());
		   Alert alert = getDriver().switchTo().alert();
		   String alertText = alert.getText();
		   alert.accept();
		   if (alertText.equals("Asset saved successfully.")) {
			   getTest().log(Status.PASS, "Asset added Successfully");
			   }

		   else  getTest().log(Status.FAIL, alertText);
	   } catch (TimeoutException e) {
		   System.out.println("No alert appeared.");

	   }
	    
   }
  
   @Test
  public void addBankDetails() {
	   adminPage = new AdminDashboardPage(getDriver());
	   adminPage.clickOnEmployeeBtn();
	   getTest().log(Status.INFO, "clicked on employee Tab");
	   adminPage.enterBankDetails();
	   getTest().log(Status.INFO, "clicked on Bank Details Icon");
	   adminPage.enterPanNumber("45623");
	   getTest().log(Status.INFO, "PAN Number entered into the field");

	   adminPage.enterAccountName("Dummy");
	   getTest().log(Status.INFO, "Account Holder Name entered in the field");

	   adminPage.enterAccountNumber("451278");
	   getTest().log(Status.INFO, "Account Number entered into the field");

	   adminPage.enterIfsc("12345");
	   getTest().log(Status.INFO, "IFSC Code entered into the field");

	   adminPage.enterBankName("BOB");
	   getTest().log(Status.INFO, "Bank Name entered into the field");
	   adminPage.saveBankDetails();
	   getTest().log(Status.INFO, "Clicked on save Bank Details Button");
	   
	   try {
		   WebDriverWait alertWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		   alertWait.until(ExpectedConditions.alertIsPresent());
		   Alert alert = getDriver().switchTo().alert();
		   String alertText = alert.getText();
		   alert.accept();
		   if (alertText.equals("Bank details Added successfully.")) {
			   getTest().log(Status.PASS, "Bank Details  added Successfully");
			   }

		   else  getTest().log(Status.FAIL, alertText);
	   } catch (TimeoutException e) {
		   System.out.println("No alert appeared.");

	   }
  }
   
   
   @Test
   public void editEmpDetails() {
	   adminPage = new AdminDashboardPage(getDriver());
	   adminPage.clickOnEmployeeBtn();
	   getTest().log(Status.INFO, "clicked on employee Tab");
	   adminPage.editEmpDetails();
	   getTest().log(Status.INFO, "clicked on editEmployee Details Icon");
	   adminPage.editEmpAddress("Sector 16, Faridabad");
	   getTest().log(Status.INFO, "New Address entered into the Address field");
	   adminPage.submitAddress();
	   try {
		   WebDriverWait alertWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		   alertWait.until(ExpectedConditions.alertIsPresent());
		   Alert alert = getDriver().switchTo().alert();
		   String alertText = alert.getText();
		   alert.accept();
		   if (alertText.equals("Employee Details Updated Successfully")) {
			   getTest().log(Status.PASS, "Employee Details Updated Successfully");
			   }

		   else  getTest().log(Status.FAIL, alertText);
	   } catch (TimeoutException e) {
		   System.out.println("No alert appeared.");

	   }
	   
   }
   
   @Test
   public void deleteEmployee() {
	   adminPage = new AdminDashboardPage(getDriver());
	   adminPage.clickOnEmployeeBtn();
	   getTest().log(Status.INFO, "clicked on employee Tab");
	   adminPage.deleteEmployee();
	   getTest().log(Status.INFO, "clicked on delete employee icon");
	   adminPage.confirmDeleteEmp();
	   getTest().log(Status.INFO, "clicked on confirm  delete employee Button");
	   try {
		   WebDriverWait alertWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		   alertWait.until(ExpectedConditions.alertIsPresent());
		   Alert alert = getDriver().switchTo().alert();
		   String alertText = alert.getText();
		   alert.accept();
		   if (alertText.equals("Employee and associated user deleted successfully.")) {
			   getTest().log(Status.PASS, "Employee Details Updated Successfully");
			   }

		   else  getTest().log(Status.FAIL, alertText);
	   } catch (TimeoutException e) {
		   System.out.println("No alert appeared.");

	   }
   
   }
   
   @Test
   public void manageExitDetails() {
	   adminPage = new AdminDashboardPage(getDriver());
	   adminPage.clickOnEmployeeBtn();
	   getTest().log(Status.INFO, "clicked on employee Tab");
	   adminPage.manageExitDetails();
	   getTest().log(Status.INFO, "clicked on Manage Exit Details Icon");

	   adminPage.exitReasonDropdown();
	   getTest().log(Status.INFO, "exit reason selected from the dropdown");
	   adminPage.uploadClearanceForm();
	   getTest().log(Status.INFO, "clearance form uploaded successfully");
	   adminPage.uploadExperienceLetter();
	   getTest().log(Status.INFO, "experience letter uploaded successfully");
	   adminPage.uploadRelievingLetter();
	   getTest().log(Status.INFO, "relieving letter uploaded successfully");
	   adminPage.uploadNoDuesForm();
	   getTest().log(Status.INFO, "No Dues Form  uploaded successfully");
	   adminPage.clickAllCheckBoxes();
	   getTest().log(Status.INFO, "clicked on all checkboxes");
	   adminPage.saveExitDetails();
	   try {
		   WebDriverWait alertWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		   alertWait.until(ExpectedConditions.alertIsPresent());
		   Alert alert = getDriver().switchTo().alert();
		   String alertText = alert.getText();
		   alert.accept();
		   if (alertText.equals("Exit details saved successfully.")) {
			   getTest().log(Status.PASS, "Employee Exit Details Saved Successfully");
			   }

		   else  getTest().log(Status.FAIL, alertText);
	   } catch (TimeoutException e) {
		   System.out.println("No alert appeared.");

	   }
	   
	   
   }
   
   @Test
   public void searchEmployee() {
	   adminPage = new AdminDashboardPage(getDriver());
	   adminPage.clickOnEmployeeBtn();
	   getTest().log(Status.INFO, "clicked on employee Tab");
	   adminPage.searchEmployee("Nitin");
	   getTest().log(Status.INFO, "employee name entered into the input field");
	   if (adminPage.verifySearch().equals("Nitin Adhikari")) {
		   getTest().log(Status.PASS, "employee name verified : "+adminPage.verifySearch());
	   }
	   else  getTest().log(Status.FAIL, "employee not showing");
   }
   
   @Test
   public void logout() throws InterruptedException {
	   page = new LoginAndLogoutPage(getDriver());
	   page.clickonUserIcon();
	   getTest().log(Status.INFO, "clicked on user profile icon");
	   page.clickOnLogoutBtn();
	   getTest().log(Status.INFO, "clicked on Log out Button");
	   WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
	   WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
	   By.xpath("//*[@id=\"login-user\"]/div/div/div[3]/button")));
	   assertTrue(element.isDisplayed());
	   getTest().log(Status.PASS, "Logout Successfully element : "+element+ " is visible");
   }
   
   @Test
   public void changePassword() throws InterruptedException {
	   page = new LoginAndLogoutPage(getDriver());
	   page.clickonUserIcon();
	   getTest().log(Status.INFO, "clicked on user profile icon");
	   page.changePassword();
	   getTest().log(Status.INFO, "Clicked on change password Button");
	   page.enterOldPassword(config.oldpassword());
	   getTest().log(Status.INFO, "old password entered into the field");
	   page.enterNewPassword(config.newpassword());
	   getTest().log(Status.INFO, "new password entered into the field");
	   page.confirmNewPassword(config.confirmpassword());
	   getTest().log(Status.INFO, "confirm new password entered into the field");
	   page.savePassword();
	   getTest().log(Status.INFO, "clicked on save password Button");
	   
	   try {
		   WebDriverWait alertWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		   alertWait.until(ExpectedConditions.alertIsPresent());
		   Alert alert = getDriver().switchTo().alert();
		   String alertText = alert.getText();
		   alert.accept();
		   if (alertText.equals("Password changed successfully!")) {
			   getTest().log(Status.PASS, "Employee Exit Details Saved Successfully");
			   }

		   else  getTest().log(Status.FAIL, alertText);
	   } catch (TimeoutException e) {
		   System.out.println("No alert appeared.");

	   }
   }
   
   @Test
   public void uploadAttendance() {
	   atdPage = new attendancePage(getDriver());
	   atdPage.clickOnAttendanceTab();
	   getTest().log(Status.INFO, "clicked on Attendance Tab");
	   atdPage.uploadAttendance("C:\\Users\\enque\\Downloads\\AttendanceSheet.xlsx");
	   getTest().log(Status.INFO, "Attendance sheet uploading...");
	   atdPage.uploadAttendanceButton();
	   getTest().log(Status.INFO, "Clicked on upload attendance sheet Button");
	   
	   try {
		   WebDriverWait alertWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		   alertWait.until(ExpectedConditions.alertIsPresent());
		   Alert alert = getDriver().switchTo().alert();
		   String alertText = alert.getText();
		   alert.accept();
		   if (alertText.equals("Attendance data uploaded successfully.")) {
			   getTest().log(Status.PASS, "Attendance Uploaded successfully");
			   }

		   else  getTest().log(Status.FAIL, alertText);
	   } catch (TimeoutException e) {
		   System.out.println("No alert appeared.");

	   }
   }
   
   @Test
   public void viewAttendance() {
	   atdPage = new attendancePage(getDriver());
	   atdPage.clickOnAttendanceTab();
	   getTest().log(Status.INFO, "clicked on Attendance Tab");
	   atdPage.viewAttendance();
	   getTest().log(Status.INFO, "Year selected from the dropdown");
	   atdPage.viewAttendanceBtn();
	   getTest().log(Status.INFO, "clicked on filter attendance button");
	   getTest().log(Status.PASS, "Attendance is visible");
	   
	   WebElement attendanceTable = getDriver().findElement(By.id("AttendanceTableBody"));
	   assertTrue(attendanceTable.isDisplayed());
	   getTest().log(Status.PASS, "Attendance Table is visible");
   }
   
   @Test
   public void uploadSalary() {
	   salPage= new salaryPage(getDriver());
	   salPage.clickOnSalaryTab();
	   getTest().log(Status.INFO, "clicked on Salary Tab");
	   salPage.uploadSalary("C:\\Users\\enque\\Downloads\\SalaryDetails (1).xlsx");
	   getTest().log(Status.INFO, "Uploading salary file");
	   salPage.uploadSalaryButton();
	   getTest().log(Status.INFO, "clicked on upload salary Button");
	   
	   try {
		   WebDriverWait alertWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		   alertWait.until(ExpectedConditions.alertIsPresent());
		   Alert alert = getDriver().switchTo().alert();
		   String alertText = alert.getText();
		   alert.accept();
		   if (alertText.equals("Salary data uploaded successfully!")) {
			   getTest().log(Status.PASS, "Salary data uploaded successfully");
			   }

		   else  getTest().log(Status.FAIL, alertText);
	   } catch (TimeoutException e) {
		   System.out.println("No alert appeared.");

	   }
   }
   
   @Test
   public void viewSalary() {
	   salPage= new salaryPage(getDriver());
	   salPage.clickOnSalaryTab();
	   getTest().log(Status.INFO, "clicked on Salary Tab");
	   salPage.selectYear("2025");
	   getTest().log(Status.INFO, "salary year selected");
	   salPage.filterSalaryBtn();
	   getTest().log(Status.INFO, "clicked on filtersalary Button");
	   
	   WebElement salaryTable = getDriver().findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div/table"));
	   assertTrue(salaryTable.isDisplayed());
	   getTest().log(Status.PASS, "Attendance Table is visible");
   }
   
   @Test
   public void applyForLeave() {
	   leavePage = new applyLeavePage(getDriver());
	   leavePage.applyLeaveTab();
	   getTest().log(Status.PASS, "clicked on Apply Leave tab");

	   leavePage.enterStartDate("2025-05-22");
	   getTest().log(Status.PASS, "Leave start date entered");

	   leavePage.enterEndDate("2025-05-25");
	   getTest().log(Status.PASS, "Leave end date entered");

	   leavePage.leaveReason("Due to suffering from fever");
	   getTest().log(Status.PASS, "Leave Reason Entered");
	   leavePage.applyLeaveBtn();  
	   getTest().log(Status.PASS, "clicked on apply leave Button");
	   try {
		   WebDriverWait alertWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		   alertWait.until(ExpectedConditions.alertIsPresent());
		   Alert alert = getDriver().switchTo().alert();
		   String alertText = alert.getText();
		   alert.accept();
		   if (alertText.equals("Form submitted successfully!")) {
			   getTest().log(Status.PASS, "Leave applied Successfully");
			   }

		   else  getTest().log(Status.FAIL, alertText);
	   } catch (TimeoutException e) {
		   System.out.println("No alert appeared.");
	   }
	   
   }
}
   
