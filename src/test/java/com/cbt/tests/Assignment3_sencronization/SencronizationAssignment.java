package com.cbt.tests.Assignment3_sencronization;

import com.cbt.utils.BrowserFactory;
import com.cbt.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SencronizationAssignment {

    private WebDriver driver;
    private WebDriverWait wait;

    /*
    Test Data:
    Environment:“https://qa1.vytrack.com/"
    User Name: storemanager85
    Password: UserUser123
     */

    @BeforeMethod
    public void setup (){

        /*
        1.Go to “https://qa1.vytrack.com/"
        2.Login as a store manager
        3.Navigate to “Activities -> Calendar Events”


         */

        driver = BrowserFactory.getDriver("chrome");
        //explicit wait
        wait = new WebDriverWait(driver,10);

        //implicit waiting
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //maximize browser
        driver.manage().window().maximize();
        // Go to “https://qa1.vytrack.com/
        driver.get("https://qa1.vytrack.com");

        //Login as store manager
        driver.findElement(By.id("prependedInput")).sendKeys("storemanager85");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123", Keys.ENTER);

        //Navigate to “Activities -> Calendar Events”
        WebElement activitiesElement = driver.findElement(By.linkText("Activities"));
        wait.until(ExpectedConditions.visibilityOf(activitiesElement));
        wait.until(ExpectedConditions.elementToBeClickable(activitiesElement));
        BrowserUtils.wait(4);
        activitiesElement.click();

        // Calender Events
        WebElement calenderEventsElement = driver.findElement(By.linkText("Calendar Events"));
        wait.until(ExpectedConditions.visibilityOf(calenderEventsElement));
        wait.until(ExpectedConditions.elementToBeClickable(calenderEventsElement));
        calenderEventsElement.click();

        WebElement loaderMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));



    }

    @Test (description = "Verify that page subtitle 'Options' is displayed")
    public void testcase1(){

        String expectedSubtitle = "Options";
        String actualSubtitle= driver.findElement(By.cssSelector("div[class='btn btn-link dropdown-toggle']")).getText();

        Assert.assertEquals(actualSubtitle,expectedSubtitle,"Subtitle did not match");

    }

    @Test(description = "Verify that page number is equals to 1 ")
    public void testcase2(){

        String expectedPageNumber = "1";
        String actualPageNumber= driver.findElement(By.cssSelector("input[type='number']")).getAttribute("Value");

        Assert.assertEquals(actualPageNumber,expectedPageNumber,"Page number did not match");

    }


    @Test ( description = "Verify that view per page number is equals to 25 " )
    public void testcase3(){

        String expectedNumber = "25";
        String actualNumber= driver.findElement(By.cssSelector("button[class='btn dropdown-toggle ']")).getText();

        Assert.assertEquals(actualNumber,expectedNumber,"Per page number did not match");
    }

    @Test (description = "Verify that number of calendar events (rows in the table) is equals to number of records")
    public void testcase4(){
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("tr[class='grid-row row-click-action'"));
        System.out.println(checkboxes.size());
        String actualNumberofRecords = checkboxes.size()+"";
        String numberofRecordsString = driver.findElement(By.xpath("//label[@class='dib'][3]")).getText();
        String expectedNumberofRecords = numberofRecordsString.trim().substring(9,numberofRecordsString.indexOf('R')).trim();
        System.out.println(expectedNumberofRecords);

        Assert.assertEquals(actualNumberofRecords,expectedNumberofRecords,"Records did not match");
    }

    @Test(description = "Click on the top checkbox to select all 5.Verify that all calendar events were selected")
    public void testcase5(){

        int expectedNumberOfSelectedNumbers = driver.findElements(By.cssSelector("tr[class='grid-row row-click-action']")).size();

        driver.findElement(By.xpath("//button[@class='btn btn-default btn-small dropdown-toggle']/input")).click();
        int numberOfSelectedItems = driver.findElements(By.xpath("//tr[@class='grid-row row-click-action row-selected']")).size();

        Assert.assertEquals(numberOfSelectedItems,expectedNumberOfSelectedNumbers,"All calender events were not selected");

    }

    @Test(description = "Select “Testers meeting”5.Verify that following data is displayed:")
    public void testcase6(){


        //to click the tester meetings
        driver.findElement(By.cssSelector("[class='grid-row row-click-action']:nth-of-type(14)")).click();

        // to wait until mask is invisible
        WebElement mask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(mask));

        List<String> list = new ArrayList<>(Arrays.asList(
                "Testers Meeting",
                "This is a a weekly testers meeting",
                "Nov 27, 2019, 9:30 PM",
                "Nov 27, 2019, 10:30 PM",
                "No",
                "Stephan Haley",
                "Tom Smith",
                "Weekly every 1 week on Wednesday",
                "No"));



            // for elements //div[@class='responsive-block']/div/div/div


            List<WebElement> actualRes = driver.findElements(By.xpath("//div[@class='responsive-block']/div/div/div"));
            for(int i=0; i<list.size(); i++){
            System.out.println(actualRes.get(i).getText());

            if(i==6) {
                Assert.assertEquals(actualRes.get(i).getText().replace(" - Required",""), list.get(i));
            }

            else {
                Assert.assertEquals(actualRes.get(i).getText(), list.get(i));
            }
        }
    }





    @AfterMethod
    public void teardown(){
        driver.quit();
    }



}
