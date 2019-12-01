package com.cbt.tests.Assignment1_BasicNavigationDeneme.VasylTestCase;

import com.cbt.utils.BrowserFactory;
import com.cbt.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TestCase5 {
    public static void main(String[] args) {
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice-cybertekschool.herokuapp.com/");
        // click on Registration form

        //click on "Registration Form"
        WebElement registrationForm = driver.findElement(By.linkText("Registration Form"));
        registrationForm.click();
        //wait 2 seconds
        BrowserUtils.wait(2);

        //enter"123" into lastnameinput box
        WebElement lastName = driver.findElement(By.name("lastname"));
        lastName.sendKeys("123");
        BrowserUtils.wait(2);
        String message = "The last name can only consist of alphabetical letters and dash";
        //#registrationForm > div:nth-child(2) > div > small:nth-child(5)
        String warning = driver.findElement(By.cssSelector("#registrationForm > div:nth-child(2) > div > small:nth-child(5)")).getText();
        System.out.println(warning);
        if(warning.equals(message)){
            System.out.println("PASSED");
        }
        else
        {
            System.out.println("FAILED");
        }

    driver.quit();
    }


}
