package com.cbt.tests.Assignment1_BasicNavigationDeneme.VasylTestCase;

import com.cbt.utils.BrowserFactory;
import com.cbt.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestCase6 {

    public static void main(String[] args) {
        //Go to "http://practice-cybertekschool.herokuapp.com/"
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice-cybertekschool.herokuapp.com/");
        BrowserUtils.wait(1);

        //click on "Registration Form"
        WebElement form= driver.findElement(By.linkText("Registration Form"));
        form.click();

        //Enter "user" into username input box.
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("user");

        //Verify that warning message is displayed
        String warning = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[3]/div/small[2]")).getText();
       if(warning.equals("The username must be more than 6 and less than 30 characters long")){
           System.out.println("Test PASSED");
       }
       else
           System.out.println("Test FAILED");

        System.out.println(warning);



        driver.quit();

    }
}
