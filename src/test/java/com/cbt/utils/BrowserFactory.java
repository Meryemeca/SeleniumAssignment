package com.cbt.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {

    // we gonna create a method
    // that will return a webdriver object
    // this method will take one parameter - String browser
    // based on the value of the browser parameter
    // method will turn return corresponded webdriver object
    //if browser= chrome, then return chromedriver object

    public static WebDriver getDriver(String browser){

        if (browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }

        else if (browser.equalsIgnoreCase("safari")) {
            // WebDriverManager.safaridriver().setup();
            return new SafariDriver();
        }
        return null; // if you do not write this it will not compile
    }


}
