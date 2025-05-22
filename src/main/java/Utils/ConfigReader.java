package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties prop;

    public ConfigReader() {
        prop = new Properties();  // Initialize once
        try {
            String path = System.getProperty("user.dir") + "/src/test/resources/config.properties";
            FileInputStream fis = new FileInputStream(path);
            prop.load(fis);
        } catch (IOException e) {
            System.out.println("Failed to load config.properties file.");
            e.printStackTrace();
        }
    }

    public String getLambdaUsername() {
        return prop.getProperty("lt.username");
    }

    public String getLambdaPassword() {
        return prop.getProperty("lt.password");
    }

    public String getAppValidUsername() {
        return prop.getProperty("app.validusername");
    }

    public String getAppValidPassword() {
        return prop.getProperty("app.validpassword");
    }

    public String getAppInvalidUsername() {
        return prop.getProperty("app.invalidusername");
    }

    public String getAppInvalidPassword() {
        return prop.getProperty("app.invalidpassword");
    }
    
    public String oldpassword() {
		return prop.getProperty("app.oldpassword");
    	
    }
    
    public String newpassword() {
		return prop.getProperty("app.newpassword");
    	
    }
    
    public String confirmpassword() {
		return prop.getProperty("app.confirmpassword");
    	
    }
    
    public String username() {
		return prop.getProperty("app.username");
    	
    } 
    
    public String userpassword() {
		return prop.getProperty("app.userpassword");
    	
    } 
}
