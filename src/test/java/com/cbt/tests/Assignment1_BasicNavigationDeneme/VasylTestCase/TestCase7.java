package com.cbt.tests.Assignment1_BasicNavigationDeneme.VasylTestCase;

import com.cbt.utils.BrowserFactory;
import com.cbt.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TestCase7 {

    public static void main(String[] args) {

        //Go to "http://practice-cybertekschool.herokuapp.com/"
        WebDriver driver= BrowserFactory.getDriver("chrome");
        driver.get("http://practice-cybertekschool.herokuapp.com/");
        BrowserUtils.wait(1);

        //click on "Registration Form"
        WebElement registrationForm = driver.findElement(By.linkText("Registration Form"));
        registrationForm.click();

         //Enter "tester@email" into email input box.
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("testers@email");
        //Verify that warning message is displayed
        String warning1 = driver.findElement(By.cssSelector("#registrationForm > div.form-group.has-feedback.has-error > div > small:nth-child(4)")).getText();
        String warning2 =driver.findElement(By.cssSelector("#registrationForm > div.form-group.has-feedback.has-error > div > small:nth-child(5)")).getText();

        if (warning1.equals("email address is not a valid")&&
        warning2.equals("Email format is not correct")){
            System.out.println("PASSED");
        }
        else
        {
            System.out.println("FAILED");
        }

        driver.quit();
    }


}
