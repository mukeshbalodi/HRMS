package Base;

import com.aventstack.extentreports.*;
import Utils.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class BaseClassLocal {

    private static ExtentReports extent;
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    public ExtentTest getTest() {
        return test.get();
    }

    @BeforeSuite(alwaysRun = true)
    public synchronized void setupReport() {
        if (extent == null) {
            extent = ExtentManager.getInstance();
        }
    }

    @Parameters({"browser", "browserVersion", "platform"})
    @BeforeClass(alwaysRun = true)
    public void setupClass(@Optional("chrome") String browser,
                           @Optional("latest") String browserVersion,
                           @Optional("local") String platform,
                           Method method) {

        // Create class-level ExtentTest node
        ExtentTest extentTest = extent.createTest("Test Class: " + this.getClass().getSimpleName())
                .assignCategory(browser)
                .assignDevice(browser + " " + browserVersion + " on " + platform);
        test.set(extentTest);

        WebDriver localDriver = null;
        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    localDriver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    localDriver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    localDriver = new EdgeDriver();
                    break;
                case "safari":
                    localDriver = new SafariDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser: " + browser);
            }

            localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            localDriver.manage().window().maximize();
            driver.set(localDriver);

            extentTest.log(Status.INFO, "Browser setup complete for: " + browser);
        } catch (Exception e) {
            extentTest.fail("Browser setup failed: " + e.getMessage());
            throw new RuntimeException("Browser setup failed", e);
        }
    }

    @AfterClass(alwaysRun = true)
    public void teardownClass(ITestContext context) {
        WebDriver localDriver = driver.get();
        ExtentTest extentTest = test.get();

        if (localDriver != null) {
            localDriver.quit();
            extentTest.log(Status.INFO, "Browser closed");
        }

        driver.remove();
        test.remove();
    }

    @AfterMethod(alwaysRun = true)
    public void captureResult(ITestResult result) {
        ExtentTest extentTest = test.get();

        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                String screenshotPath = takeScreenshot(result.getName());
                String relativePath = "./screenshots/" + result.getName() + ".png";

                extentTest.fail(result.getThrowable(),
                        MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
            } else if (result.getStatus() == ITestResult.SKIP) {
                extentTest.skip("Test skipped: " + result.getThrowable());
            } else {
                extentTest.pass("Test passed");
            }
        } catch (Exception e) {
            extentTest.warning("Could not attach screenshot or result details: " + e.getMessage());
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush();
        }
    }

    public String takeScreenshot(String testName) {
        WebDriver localDriver = driver.get();
        try {
            File src = ((TakesScreenshot) localDriver).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/test-output/screenshots/" + testName + ".png";
            File destination = new File(path);
            Files.createDirectories(destination.getParentFile().toPath());
            Files.copy(src.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return path;
        } catch (IOException | WebDriverException e) {
            e.printStackTrace();
            return null;
        }
    }
}
