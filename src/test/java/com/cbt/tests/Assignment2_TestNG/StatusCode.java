package com.cbt.tests.Assignment2_TestNG;

import com.cbt.utils.BrowserFactory;
import com.cbt.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StatusCode {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("Status Codes")).click();
        BrowserUtils.wait(2);

    }

    @DataProvider(name = "testData")
    public static Object[][] testData(){
        return new Object[][]{{"200", "This page returned a 200 status code"}, //1st set of data
                {"301", "This page returned a 301 status code"} ,   //2nd set of data
                {"404", "This page returned a 404 status code"},    //3rd set of data
                {"500", "This page returned a 500 status code"}     //4th set of data
        };
    }
    //data provider must return 2d array of Object
    //1st parameter  = 1 object in the inner array, 2nd parameter = 2 object in the inner array

    @Test(dataProvider = "testData") // this test will run 4 times, because we have 4 sets of data
    public void testStatusCodeWithDataProvider(String statusCode, String expectedMessage){
        driver.findElement(By.linkText(statusCode)).click();
        String actualMessage =driver.findElement(By.xpath("//p")).getText();
        actualMessage = actualMessage.trim().substring(0,36);
        System.out.println(actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage,"It does not match actual error code");
    }




    @AfterMethod
    public void teardown(){
        driver.quit();
    }


}



