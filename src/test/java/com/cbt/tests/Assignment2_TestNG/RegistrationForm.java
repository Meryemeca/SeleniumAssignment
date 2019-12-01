package com.cbt.tests.Assignment2_TestNG;

import com.cbt.utils.BrowserFactory;
import com.cbt.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationForm {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("Registration Form")).click();
        BrowserUtils.wait(2);

    }

    /*

    Step 1. Go to “https://practice- cybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”
    Step 3. Enter “wrong_dob” into date of birth input box.
    Step 4. Verify that warning message is displayed: “The date of birth is not valid”
     */

    @Test // TestCase1
    public void testCase1() {


        driver.findElement(By.xpath("//input[@name='birthday']")).sendKeys("wrong_dob");
        BrowserUtils.wait(2);

        String expectedResult = "The date of birth is not valid";
        String actualResult = driver.findElement(By.xpath("//*[text() = 'The date of birth is not valid']")).getText();
        System.out.println(actualResult);
        System.out.println(expectedResult);
        Assert.assertEquals(actualResult, expectedResult);

    }
    /*
    Step 1. Go to “https://practice- cybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”
    Step 3. Verify that following options for programming languages are displayed: c++, java, JavaScript

     */



    @Test //TestCase2
    public void testCase2() {

        WebElement checkBoxC = driver.findElement(By.xpath("//label[text()='C++']"));
        WebElement checkBoxJava = driver.findElement(By.xpath("//label[text()='Java']"));
        WebElement checkBoxJS= driver.findElement(By.xpath("//label[text()='JavaScript']"));

        Assert.assertTrue(checkBoxC.isDisplayed());
        Assert.assertTrue(checkBoxJava.isDisplayed());
        Assert.assertTrue((checkBoxJS.isDisplayed()));

    }

    /*

    Test case #3
        Step 1. Go to “https://practice- cybertekschool.herokuapp.com”
        Step 2. Click on “Registration Form”
        Step 3. Enter only one alphabetic character into first name input box.
        Step 4. Verify that warning message is displayed: “first name must be
        more than 2 and less than 64 characters long”
     */
    @Test
    public void testCase3(){
        driver.findElement(By.cssSelector("[name='firstname']")).sendKeys("a");
        String actualMessage= driver.findElement(By.xpath("//small[text()='first name must be more than 2 and less than 64 characters long']")).getText();
        String expectedMessage = "first name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actualMessage,expectedMessage);
    }


    /*

    Test case #4
    Step 1. Go to https://practice-
    cybertekschool.herokuapp.com
    Step 2. Click on “Registration Form”
    Step 3. Enter only one alphabetic character into last name input box.
    Step 4. Verify that warning message is displayed: “The last name must be more than 2 and less than 64 characters long”
     */

    @Test
    public void testCase4(){
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("A");
        String expectedMessage="The last name must be more than 2 and less than 64 characters long";
        String actualMessage = driver.findElement(By.xpath("//small[text()='The last name must be more than 2 and less than 64 characters long']")).getText();
        Assert.assertEquals(actualMessage,expectedMessage);
    }


    /*

    Test case #5
        Step 1. Go to “https://practice- cybertekschool.herokuapp.com” Step 2. Click on “Registration Form”
          Step 3. Enter any valid
        Step 4. Enter any valid
        Step 5. Enter any valid
        Step 6. Enter any valid
        Step 7. Enter any valid
        Step 8. Select gender.
        Step 9. Enter any valid
        Step 10. Select any department.
        Step 11. Enter any job title.
        Step 12. Select java as a programming language. Step 13. Click Sign up.
        Step 14. Verify that following success message is displayed: “You've successfully completed registration!”
     */

    @Test
    public void testCase5(){
        driver.findElement(By.cssSelector("input[name = 'firstname']")).sendKeys("Meryem");
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Celebi");
        driver.findElement(By.cssSelector("input[name = 'username']")).sendKeys("Meryemce");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("meryem@gmail.com");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("12345678");
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("123-456-7890");
        driver.findElement(By.cssSelector("input[value='female']")).click();
        driver.findElement(By.cssSelector("input[name='birthday']")).sendKeys("11/11/2000");
        WebElement department = driver.findElement(By.cssSelector("select[name='department']"));
        Select select = new Select(department);
        select.selectByVisibleText("Department of Engineering");
        WebElement jobTitle = driver.findElement(By.cssSelector("select[name='job_title']"));
        Select jobtitle = new Select((jobTitle));
        jobtitle.selectByVisibleText("SDET");
        driver.findElement(By.xpath("//label[text()='Java']")).click();
        driver.findElement(By.id("wooden_spoon")).click();
        String actualMessage= "You've successfully completed registration!";
        String expectedMessage = driver.findElement(By.xpath("//p[starts-with(text(),'You')]")).getText();
        Assert.assertEquals(actualMessage,expectedMessage, "You couldn't do it");


    }
    @AfterMethod
    public void teardown() {
        driver.quit();

    }


}
