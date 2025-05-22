package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.*;

import Utils.ConfigReader;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;



public class BaseClassLambda {

    protected WebDriver driver;

    @Parameters({"browser", "browserVersion", "platform"})
    @BeforeMethod
    public void setUp(@Optional("Chrome") String browser,
                      @Optional("latest") String browserVersion,
                      @Optional("Windows 10") String platform,
                      Method method) throws MalformedURLException, URISyntaxException {

     ConfigReader reader = new ConfigReader();
        String username = reader.getLambdaUsername();
        String accessKey = reader.getLambdaPassword();

        // Set common capabilities
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", username);
        ltOptions.put("accessKey", accessKey);
        ltOptions.put("project", "Selenium TestNG Project");
        ltOptions.put("build", "Build-001");
        ltOptions.put("name", method.getName());
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        ltOptions.put("console", true);
        ltOptions.put("network", true);
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("platformName", platform);
        ltOptions.put("browserVersion", browserVersion);

        // Setup options based on browser
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setCapability("LT:Options", ltOptions);
                driver = new RemoteWebDriver(new URI(getHubURL(username, accessKey)).toURL(), chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability("LT:Options", ltOptions);
                driver = new RemoteWebDriver(new URL(getHubURL(username, accessKey)), firefoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setCapability("LT:Options", ltOptions);
                driver = new RemoteWebDriver(new URL(getHubURL(username, accessKey)), edgeOptions);
                break;

            case "safari":
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setCapability("LT:Options", ltOptions);
                driver = new RemoteWebDriver(new URL(getHubURL(username, accessKey)), safariOptions);
                break;

            

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private String getHubURL(String username, String accessKey) {
        return "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
