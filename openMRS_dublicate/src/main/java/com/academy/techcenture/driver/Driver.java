package com.academy.techcenture.driver;

import com.academy.techcenture.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Driver {
    /**
     * Hide the constructor so that this class cannot be instantiated
     */
    private Driver(){

    }
    private static WebDriver driver;

    public static WebDriver getDriver(){

        String browser = ConfigReader.getProperty("browser").trim().toLowerCase(); //chrome

        int pageLoadTime = Integer.parseInt(ConfigReader.getProperty("pageLoadTime"));
        int implicitWait = Integer.parseInt(ConfigReader.getProperty("implicitWait"));

        switch (browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("autofill.profile_enabled", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.default_content_setting_values.notifications", 2);
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            default:
                throw new RuntimeException("No Driver found!!!!!");
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTime));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver(){
        if(driver != null){
            driver.quit();
        }
    }

    }


