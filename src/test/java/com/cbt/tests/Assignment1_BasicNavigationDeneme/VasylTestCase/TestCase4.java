package com.cbt.tests.Assignment1_BasicNavigationDeneme.VasylTestCase;

import com.cbt.utils.BrowserFactory;
import com.cbt.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TestCase4 {

    public static void main(String[] args) {
        WebDriver driver= BrowserFactory.getDriver("chrome");
        driver.get("http://practice-cybertekschool.herokuapp.com/");

        //click on "Registration Form"
        WebElement registrationForm = driver.findElement(By.linkText("Registration Form"));
        registrationForm.click();
        //wait 2 seconds
        BrowserUtils.wait(2);

        //enter"123" into first name input box
        WebElement firstName = driver.findElement(By.name("firstname"));
        firstName.sendKeys("1234");
       BrowserUtils.wait(5);
        // //*[@id="registrationForm"]/div[1]/div/input
        String warning = driver.findElement(By.cssSelector("#registrationForm > div:nth-child(1) > div > small:nth-child(5)")).getText();
        BrowserUtils.wait(3);
        System.out.println(warning);
        if(warning.equals("first name can only consist of alphabetical letters"))
        System.out.println("Test passed");
       else
           System.out.println("Test failed.");


        BrowserUtils.wait(2);
        driver.quit();

    }
}
