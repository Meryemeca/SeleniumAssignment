package com.cbt.tests.Assignment4_VytrackCalenderEvents;

import com.cbt.utils.BrowserFactory;
import com.cbt.utils.BrowserUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class VytrackCalnderEvents {

    private WebDriver driver;
    private  WebDriverWait wait;




    @BeforeMethod
    public void setUp(){

        driver= BrowserFactory.getDriver("chrome");

        //explicit wait
        wait = new WebDriverWait(driver, 10);

        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //maximize browser
        driver.manage().window().maximize();
        driver.get("https://qa1.vytrack.com/");

        driver.findElement(By.id("prependedInput")).sendKeys("storemanager85");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123", Keys.ENTER);


        WebElement activitiesElement = driver.findElement(By.linkText("Activities"));
        wait.until(ExpectedConditions.visibilityOf(activitiesElement));
        wait.until(ExpectedConditions.elementToBeClickable(activitiesElement));
        BrowserUtils.wait(1);
        //to hover on dropdown menu
        Actions action = new Actions(driver);
        action.moveToElement(activitiesElement).perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Calendar Events"))));
        driver.findElement(By.linkText("Calendar Events")).click();

        //we need to wait until mask is gone
        WebElement mask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(mask));
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }


    @Test(description = "Hover on three dots “...” for “Testers meeting” calendar even")
    public void test1(){
        WebElement treeDots = driver.findElement(By.xpath("//tbody//tr[14]//td[9]"));
        wait.until(ExpectedConditions.elementToBeClickable(treeDots));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", treeDots);
        BrowserUtils.wait(2);


        Actions action = new Actions(driver);
        action.moveToElement(treeDots).perform();
        BrowserUtils.wait(1);

        Assert.assertTrue(driver.findElement(By.cssSelector("a[href='#']")).isDisplayed());
        BrowserUtils.wait(1);
        Assert.assertTrue(driver.findElement(By.xpath("//a[@title='Edit']")).isDisplayed());
        BrowserUtils.wait(1);
        Assert.assertTrue(driver.findElement(By.cssSelector("a[href='/calendar/event/view/184")).isDisplayed());
       BrowserUtils.wait(1);
    }



    @Test (description = "Click on “Grid Options” button5.Deselect all options except “Title”6.Verify that “Title” column still displayed")
    public void test2() {


        WebElement gridOptions = driver.findElement(By.cssSelector("a[title='Grid Settings']"));
        gridOptions.click();
        BrowserUtils.wait(2);

        //WebElement titleCheckBox = driver.findElement(By.xpath("//tr[1]//td[3]//input"));

        //List<WebElement> options = new ArrayList<>(driver.findElements(By.xpath("//tr//td[3]//input")));


        for (int i=2; i<8; i++){
            driver.findElement(By.xpath("//tr["+i+"]//td[3]//input")).click();
        }

        WebElement titleColumn = driver.findElement(By.xpath("//span[.='Title']"));

        Assert.assertTrue(titleColumn.isDisplayed());

            }


        @Test (description="4.Click on “Create Calendar Event” button5.Expand “Save And Close” menu6.Verify that “Save And Close”, “Save And New” and “Save” options are available")
        public void test3(){

        //Click on “Create Calendar Event” button
        driver.findElement(By.cssSelector("a[title='Create Calendar event'")).click();

        //wait until mask is gone
        WebElement loadingMask = driver.findElement(By.cssSelector("div[class='loading-bar']"));
        wait.until(ExpectedConditions.invisibilityOf(loadingMask));

        //Expand “Save And Close” menu
        driver.findElement(By.cssSelector("a[class='btn-success btn dropdown-toggle']")).click();

        // Verify that “Save And Close”, “Save And New” and “Save” options are available

            WebElement saveAndCose = driver.findElement(By.xpath("//li//button[normalize-space()='Save and Close']"));
            Assert.assertTrue(saveAndCose.isDisplayed());

            WebElement saveAndNew = driver.findElement(By.xpath("//li//button[normalize-space()='Save and New']"));
            Assert.assertTrue(saveAndNew.isDisplayed());

            WebElement newButton = driver.findElement(By.xpath("//li//button[normalize-space()='Save']"));
            Assert.assertTrue(newButton.isDisplayed());

        //BrowserUtils.wait(2);

        }

        @Test (description = "Click on “Create Calendar Event” button 5.Then, click on “Cancel” button 6.Verify that “All Calendar Events” page subtitle is displayed")
        public void test4(){

            //Click on “Create Calendar Event
            driver.findElement(By.cssSelector("a[title='Create Calendar event'")).click();

            //wait until mask is gone
            WebElement loadingMask = driver.findElement(By.cssSelector("div[class='loading-bar']"));
            wait.until(ExpectedConditions.invisibilityOf(loadingMask));

            //5.Then, click on “Cancel” button
            WebElement cancel = driver.findElement(By.cssSelector("a[title='Cancel']"));
            cancel.click();

            //wait until mask is gone
            wait.until(ExpectedConditions.invisibilityOf(loadingMask));

            // 6.Verify that “All Calendar Events” page subtitle is displayed

            WebElement subtitle = driver.findElement(By.xpath("//h1[contains(text(),'All')] "));
            Assert.assertTrue(subtitle.isDisplayed());

        }

        @Test(description = "Click on “Create Calendar Event” button 5.Verify that difference between end and start time is exactly 1 hour")
        public void test5(){

           // Click on “Create Calendar Event” button
            driver.findElement(By.cssSelector("a[title='Create Calendar event'")).click();

            //wait until mask is gone
            WebElement loadingMask = driver.findElement(By.cssSelector("div[class='loading-bar']"));
            wait.until(ExpectedConditions.invisibilityOf(loadingMask));

            //5.Verify that difference between end and start time is exactly 1 hour
            WebElement starting = driver.findElement(By.cssSelector("input[class='input-small timepicker-input start ui-timepicker-input'"));
            String startingTime=starting.getAttribute("value");
            System.out.println(startingTime);
            WebElement ending = driver.findElement(By.cssSelector("input[class='input-small timepicker-input end ui-timepicker-input'"));
            String endingTime=ending.getAttribute("value");
            System.out.println(endingTime);

            int startHour = Integer.parseInt(startingTime.substring(0,startingTime.indexOf(':')));
            int endHour = Integer.parseInt(endingTime.substring(0,endingTime.indexOf(':')));

            System.out.println(startHour +" "+endHour);

            if( startingTime.substring(startingTime.indexOf(':')).equals(endingTime.substring(endingTime.indexOf(':'))) ){
                if(startHour!=12)
                    Assert.assertTrue(startHour+1==endHour,"meeting duration is not one hour");
                else{
                    Assert.assertTrue(endHour==1);
                }
            }
            else
                System.out.println("Meeting duration is not 1 hour");

        }

        @Test(description = "4.Click on “Create Calendar Event” button 5.Select “9:00 PM” as a start time6.Verify that end time is equals to “10:00 PM”")
        public void test6(){




            //Click on “Create Calendar Event” button
            driver.findElement(By.cssSelector("a[title='Create Calendar event'")).click();

            //wait until mask is gone
            WebElement loadingMask = driver.findElement(By.cssSelector("div[class='loading-bar']"));
            wait.until(ExpectedConditions.invisibilityOf(loadingMask));

            // Select “9:00 PM” as a start time
           WebElement time= driver.findElement(By.cssSelector("[class='input-small timepicker-input start ui-timepicker-input'] "));
           time.click();



            WebElement startingTime = driver.findElement(By.xpath("//li[(text()='9:00 PM')]"));
            startingTime.click();
            time.click();
            System.out.println("start"+startingTime.getText());

            BrowserUtils.wait(3);


            WebElement EndTime = driver.findElement(By.cssSelector("[class='ui-timepicker-pm ui-timepicker-selected']:nth-of-type(3)"));
            String actualEndTime = EndTime.getText();
            String expectedEndTime = "10:00 PM";
            Assert.assertTrue(EndTime.isDisplayed());
            Assert.assertEquals(actualEndTime,expectedEndTime, "Start time and end time are not 1 hour apart");


           /*
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].setAttribute('value', '9:00 PM')", time);
            BrowserUtils.wait(4);
            */

        }

        @Test(description = "4.Click on “Create Calendar Event” button 5.Select “All-Day Event” checkbox6.Verify that “All-Day Event” checkbox is selected" +
                "7.Verify that start and end time input boxes are not displayed8.Verify that start and end date input boxes are displayed")
        public void test7(){

            // Click on “Create Calendar Event” button
            driver.findElement(By.cssSelector("a[title='Create Calendar event'")).click();

            //wait until mask is gone
            WebElement loadingMask = driver.findElement(By.cssSelector("div[class='loading-bar']"));
            wait.until(ExpectedConditions.invisibilityOf(loadingMask));

            WebElement allDayCheckbox = driver.findElement(By.cssSelector("[name='oro_calendar_event_form[allDay]']"));
            if(allDayCheckbox.isDisplayed() && !allDayCheckbox.isSelected())
                allDayCheckbox.click();
            Assert.assertTrue(allDayCheckbox.isSelected(), "It is not selected");

            WebElement startTime =driver.findElement(By.cssSelector("[class='input-small timepicker-input start ui-timepicker-input']"));
            WebElement endTime = driver.findElement(By.cssSelector("[class='input-small timepicker-input end ui-timepicker-input']"));
             WebElement startDate =driver.findElement(By.cssSelector("[class='input-small datepicker-input start hasDatepicker']"));
            WebElement endDate = driver.findElement(By.cssSelector("[class='input-small datepicker-input end hasDatepicker']"));

            Assert.assertFalse(startTime.isDisplayed());
            Assert.assertFalse(endTime.isDisplayed());
            Assert.assertTrue(startDate.isDisplayed());
            Assert.assertTrue(endDate.isDisplayed());


        }

        @Test(description = "Click on “Create Calendar Event” button 5.Select “Repeat” checkbox" +
                "6.Verify that “Repeat” checkbox is selected7.Verify that “Daily” is selected by default and following options are available in “Repeats” drop-down:")
        public void test8(){

            // Click on “Create Calendar Event” button
            driver.findElement(By.cssSelector("a[title='Create Calendar event'")).click();

            //wait until mask is gone
            WebElement loadingMask = driver.findElement(By.cssSelector("div[class='loading-bar']"));
            wait.until(ExpectedConditions.invisibilityOf(loadingMask));

            //Select “Repeat” checkbox" +
            WebElement repeatCheckbox = driver.findElement(By.cssSelector("input[data-name='recurrence-repeat']"));
            wait.until(ExpectedConditions.elementToBeClickable(repeatCheckbox));
            repeatCheckbox.click();
            BrowserUtils.wait(2);

            // 6.Verify that “Repeat” checkbox is selected
            Assert.assertTrue(repeatCheckbox.isSelected());

            //7.Verify that “Daily” is selected by default and options are available
                WebElement dailyDropdown = driver.findElement(By.cssSelector("select[data-name='recurrence-repeats']"));
                Select select = new Select(dailyDropdown);
                Assert.assertEquals(select.getFirstSelectedOption().getText(), "Daily");

                List<WebElement> list = select.getOptions();
                ArrayList<String> actualOptions = new ArrayList<>();
                ArrayList<String> expectedOptions = new ArrayList<>();
                expectedOptions.addAll(Arrays.asList("Daily","Weekly","Monthly","Yearly"));
                for(WebElement each: list){
                actualOptions.add(each.getText());
                }
                Assert.assertEquals(actualOptions,expectedOptions,"Options are not available");
        }


            @Test(description="5.Select “Repeat” checkbox6.Verify that “Repeat” checkbox is selected7.Verify that “Repeat Every” radio button is selected" +
                    "8.Verify that “Never” radio button is selected as an “Ends” option.9.Verify that following message as a summary is displayed: “Summary: Daily every 1 day”")
            public void test9(){

                // Click on “Create Calendar Event” button
                driver.findElement(By.cssSelector("a[title='Create Calendar event'")).click();

                //wait until mask is gone
                WebElement loadingMask = driver.findElement(By.cssSelector("div[class='loading-bar']"));
                wait.until(ExpectedConditions.invisibilityOf(loadingMask));

                //Select “Repeat” checkbox" +
                WebElement repeatCheckbox = driver.findElement(By.cssSelector("input[data-name='recurrence-repeat']"));
                wait.until(ExpectedConditions.elementToBeClickable(repeatCheckbox));
                repeatCheckbox.click();
                BrowserUtils.wait(2);

                // 6.Verify that “Repeat” checkbox is selected
                Assert.assertTrue(repeatCheckbox.isSelected());

                //7.Verify that “Repeat Every” radio button is selected"
                Assert.assertTrue(driver.findElement(By.cssSelector("input[type='radio'][checked='checked']")).isSelected(),"Repeat every button is not selected");

                //8.Verify that “Never” radio button is selected as an “Ends” option.
                Assert.assertTrue(driver.findElement(By.cssSelector("input[type='radio'][checked='']")).isSelected());

                //9.Verify that following message as a summary is displayed: “Summary: Daily every 1 day”"
                String summaryMessage = driver.findElement(By.cssSelector("[data-name='recurrence-summary']")).getText();
                String expectedMessage = "Daily every 1 day";
                Assert.assertEquals(summaryMessage,expectedMessage);
            }

            @Test(description = "Select “Repeat” checkbox6.Select “After 10 occurrences” as an “Ends” option." +
                    "7.Verify that following message as a summary is displayed: “Summary: Daily every 1 day, endafter 10 occurrences”")
            public void test10(){

                // Click on “Create Calendar Event” button
                driver.findElement(By.cssSelector("a[title='Create Calendar event'")).click();

                //wait until mask is gone
                WebElement loadingMask = driver.findElement(By.cssSelector("div[class='loading-bar']"));
                wait.until(ExpectedConditions.invisibilityOf(loadingMask));

                //Select “Repeat” checkbox" +
                WebElement repeatCheckbox = driver.findElement(By.cssSelector("input[data-name='recurrence-repeat']"));
                wait.until(ExpectedConditions.elementToBeClickable(repeatCheckbox));
                repeatCheckbox.click();
                BrowserUtils.wait(2);

                // Verify that “Repeat” checkbox is selected
                Assert.assertTrue(repeatCheckbox.isSelected());

                //Select “After 10 occurrences” as an “Ends” option."
                driver.findElement(By.xpath("//*[(text()='After')]")).click();
                driver.findElement(By.cssSelector("[data-related-field='occurrences'] ")).sendKeys("10",Keys.ENTER);
                //7.Verify that following message as a summary is displayed: “Summary: Daily every 1 day, endafter 10 occurrences”
                String actualMessage = driver.findElement(By.cssSelector("[data-name='recurrence-summary']")).getText();
                String expectedMessage="Daily every 1 day, end after 10 occurrences";
                Assert.assertEquals(actualMessage,expectedMessage);

            }

            @Test(description = "Select “Repeat” checkbox6.Select “By Nov 18, 2021” as an “Ends” option." +
                    "7.Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end by Nov 18, 2021”")
            public void test11(){

                // Click on “Create Calendar Event” button
                driver.findElement(By.cssSelector("a[title='Create Calendar event'")).click();

                //wait until mask is gone
                WebElement loadingMask = driver.findElement(By.cssSelector("div[class='loading-bar']"));
                wait.until(ExpectedConditions.invisibilityOf(loadingMask));

                //Select “Repeat” checkbox" +
                WebElement repeatCheckbox = driver.findElement(By.cssSelector("input[data-name='recurrence-repeat']"));
                wait.until(ExpectedConditions.elementToBeClickable(repeatCheckbox));
                repeatCheckbox.click();
                BrowserUtils.wait(2);

                // Verify that “Repeat” checkbox is selected
                Assert.assertTrue(repeatCheckbox.isSelected());

                //6.Select “By Nov 18, 2021” as an “Ends” option."
                driver.findElement(By.xpath("//*[(text()='By')]")).click();
                WebElement date = driver.findElement(By.cssSelector("[class='datepicker-input hasDatepicker']"));
              // wait.until(ExpectedConditions.elementToBeClickable(date));
               // date.click();
                date.sendKeys("Nov 18, 2021",Keys.ENTER);
                BrowserUtils.wait(3);
                //Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end by Nov 18, 2021”
                String expectedMessage = "Summary: Daily every 1 day, end by Nov 18, 2021";
                String actualMessage = driver.findElement(By.cssSelector("[data-name='recurrence-summary']")).getText();
                Assert.assertEquals("Summary: "+actualMessage,expectedMessage);

            }


            @Test(description ="5.Select “Repeat” checkbox6.Select “Weekly” options as a “Repeat” option" +
                    "7.Select “Monday and Friday” options as a “Repeat On” options8.Verify that “Monday and Friday” options are selected" +
                    "9.Verify that following message as a summary is displayed: “Summary: Weekly every 1 week onMonday, Friday”")
            public void test12(){

                // Click on “Create Calendar Event” button
                driver.findElement(By.cssSelector("a[title='Create Calendar event'")).click();

                //wait until mask is gone
                WebElement loadingMask = driver.findElement(By.cssSelector("div[class='loading-bar']"));
                wait.until(ExpectedConditions.invisibilityOf(loadingMask));

                //Select “Repeat” checkbox" +
                WebElement repeatCheckbox = driver.findElement(By.cssSelector("input[data-name='recurrence-repeat']"));
                wait.until(ExpectedConditions.elementToBeClickable(repeatCheckbox));
                repeatCheckbox.click();
                BrowserUtils.wait(2);

                // Verify that “Repeat” checkbox is selected
                Assert.assertTrue(repeatCheckbox.isSelected());

                //6.Select “Weekly” options as a “Repeat” option"
                WebElement dailyDropdown = driver.findElement(By.cssSelector("select[data-name='recurrence-repeats']"));
                Select select = new Select(dailyDropdown);
               select.selectByVisibleText("Weekly");
               BrowserUtils.wait(2);

                //7.Select “Monday and Friday” options as a “Repeat On” options
                WebElement mondayRadioButton = driver.findElement(By.cssSelector("[name='recurrence[dayOfWeek]'][value='monday']"));
                wait.until(ExpectedConditions.visibilityOf(mondayRadioButton));
                mondayRadioButton.click();
                WebElement fridayRadioButton = driver.findElement(By.cssSelector("[name='recurrence[dayOfWeek]'][value='friday']"));
                fridayRadioButton.click();
                //8.Verify that “Monday and Friday” options are selected"
                Assert.assertTrue(mondayRadioButton.isSelected());
                Assert.assertTrue(fridayRadioButton.isSelected());
                //"9.Verify that following message as a summary is displayed: “Summary: Weekly every 1 week onMonday, Friday”")
                String expectedMessage = "Summary: Weekly every 1 week on Monday, Friday";
                String actualMessage = driver.findElement(By.cssSelector("[data-name='recurrence-summary']")).getText();
                Assert.assertEquals("Summary: "+actualMessage,expectedMessage);

            }
}






