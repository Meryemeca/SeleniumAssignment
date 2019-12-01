package com.cbt.tests.Assignment2_TestNG;

import com.cbt.utils.BrowserFactory;
import com.cbt.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FileUpload {

    /*

Step 1. Go to “https://practice- cybertekschool.herokuapp.com”
Step 2. And click on “File Upload".
Step 3. Upload any file with .txt extension from your computer.
Step 4. Click “Upload” button.
Step 5. Verify that subject is: “File Uploaded!” Step 6. Verify that uploaded file name is displayed.
Note: use element.sendKeys(“/file/path”) with specifying path to the file for uploading. Run this method against “Choose File” button.

 */
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://practice-cybertekschool.herokuapp.com");
    }

    @Test
    public void fileUpload(){

        driver.findElement(By.linkText("File Upload")).click();
        BrowserUtils.wait(2);
        WebElement fileUpload = driver.findElement(By.id("file-upload"));

        fileUpload.sendKeys("/Users/meryemcelebi/Desktop/testNG.txt");
        driver.findElement(By.id("file-submit")).click();

        WebElement Message = driver.findElement(By.xpath("//h3[starts-with(text(),'File Uploaded')]"));
        String actualMessage = Message.getText();
        String expectedMessage = "File Uploaded!";
        Assert.assertEquals(actualMessage, expectedMessage, " Message does not match.");

        WebElement fileName = driver.findElement(By.id("uploaded-files"));
        //Verify that uploaded file name is displayed
        String actualFileName = fileName.getText();
        String expectedFileName = "testNG.txt";
        Assert.assertEquals(actualFileName, expectedFileName, "File name does not match");

    }


    /*
         Step 1. Go to “https://practice- cybertekschool.herokuapp.com”
         Step 2. And click on “Autocomplete”.
         Step 3. Enter “United States of America” into country input box.
         Step 4. Verify that following message is displayed: “You selected: United States of America”
     */
    @Test(description = "Test Case8")
    public void testCase8(){
        driver.findElement(By.linkText("Autocomplete")).click();
        BrowserUtils.wait(2);
        WebElement textBox= driver.findElement(By.cssSelector("#myCountry"));
        textBox.sendKeys("United States of America");
        driver.findElement(By.cssSelector("input[type='button']")).click();
        String actualMessage =driver.findElement(By.cssSelector("#result")).getText();
        String expectedMessage = "You selected: United States of America";
        Assert.assertEquals(actualMessage,expectedMessage,"Messages are not same");


    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}


