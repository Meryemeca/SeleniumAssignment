package com.cbt.tests.Assignment1_BasicNavigationDeneme.VasylTestCase;

import com.cbt.utils.BrowserFactory;
import com.cbt.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TestCase3 {

    public static void main(String[] args) {


        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice-cybertekschool.herokuapp.com/");
        //click on multiple buttons
        WebElement button = driver.findElement(By.linkText("Multiple Buttons"));
        button.click();
        //click on button1
        //<button class="btn btn-primary" onclick="button1()">Button 1</button>

        WebElement button1 = driver.findElement(By.xpath("//button[1]"));
        button1.click();

        BrowserUtils.wait(3);
        driver.quit();
    }

}