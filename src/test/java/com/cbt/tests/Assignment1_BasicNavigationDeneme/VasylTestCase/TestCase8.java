package com.cbt.tests.Assignment1_BasicNavigationDeneme.VasylTestCase;

import com.cbt.utils.BrowserFactory;
import com.cbt.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class TestCase8 {

    public static void main(String[] args) {
        //Go to "http://practice-cybertekschool.herokuapp.com/"
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice-cybertekschool.herokuapp.com/");
        BrowserUtils.wait(1);

        //click on "Registration Form"
         driver.findElement(By.linkText("Registration Form")).click();

         //Enter"5711234354" into phone number input box
        driver.findElement(By.cssSelector("#registrationForm > div:nth-child(6) > div > input")).sendKeys("5711234354");
        BrowserUtils.wait(2);

        //Verify that warning message is displayed."Phone format is not correct"
        String warningMessage = driver.findElement(By.cssSelector("#registrationForm > div.form-group.has-feedback.has-error > div > small:nth-child(4)")).getText();

        if(warningMessage.equals("Phone format is not correct")){
            System.out.println("passed");
        }
        else
        {
            System.out.println("failed");
        }


        driver.quit();

    }





}
