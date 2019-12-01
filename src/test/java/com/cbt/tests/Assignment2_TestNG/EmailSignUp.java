package com.cbt.tests.Assignment2_TestNG;

import com.cbt.utils.BrowserFactory;
import com.cbt.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmailSignUp {



    /*
    Test case #6
        Step 1. Go to "https://www.tempmailaddress.com/" Step 2. Copy and save email as a string.
        Step 3. Then go to “https://practice- cybertekschool.herokuapp.com”
        Step 4. And click on “Sign Up For Mailing List".
        Step 5. Enter any valid name.

        Step 6. Enter email from the Step 2.
        Step 7. Click Sign Up
        Step 8. Verify that following message is displayed: “Thank you for signing up. Click the button below to return to the home page.”
        Step 9. Navigate back to the “https:// www.tempmailaddress.com/”
        Step 10. Verify that you’ve received an email from
        “do-not-reply@practice.cybertekschool.com”
        Step 11. Click on that email to open it.
        Step 12. Verify that email is from: “do-not- reply@practice.cybertekschool.com”
        Step 13. Verify that subject is: “Thanks for subscribing to practice.cybertekschool.com!”
     */






        @Test
        public void emailSignUp() {

            WebDriver driver = BrowserFactory.getDriver("chrome");
            driver.get("https://www.tempmailaddress.com/");

            String email = driver.findElement(By.id("email")).getText();
            BrowserUtils.wait(2);

            driver.navigate().to("http://practice-cybertekschool.herokuapp.com/");
            BrowserUtils.wait(1);

            driver.findElement(By.linkText("Sign Up For Mailing List")).click();
            driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Ahmet Arif");
            driver.findElement(By.cssSelector("input[type='email']")).sendKeys(email);
            BrowserUtils.wait(2);
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            String actualMessage = driver.findElement(By.cssSelector("h3[class='subheader']")).getText();
            String expectedMessage= "Thank you for signing up. Click the button below to return to the home page.";
            Assert.assertEquals(actualMessage,expectedMessage,"Please try again!");

            BrowserUtils.wait(5);
            driver.navigate().to("https://www.tempmailaddress.com/");
            BrowserUtils.wait(5);

            driver.findElement(By.cssSelector("[class='from']")).click();

            BrowserUtils.wait(5);

            //Verify that email is from: “do-not- reply@practice.cybertekschool.com”
            String actualSender = driver.findElement(By.cssSelector("span[id='odesilatel']")).getText();
            String expectedSender ="do-not-reply@practice.cybertekschool.com";
            Assert.assertEquals(actualSender,expectedSender,"You get email from another address");

            //Verify that subject is: “Thanks for subscribing to practice.cybertekschool.com!”
            String actualSubject = driver.findElement(By.cssSelector("#predmet")).getText();
            String expectedSubject = "Thanks for subscribing to practice.cybertekschool.com!";
            Assert.assertEquals(actualSubject,expectedSubject,"Subject is different");



            driver.quit();
        }


    }



