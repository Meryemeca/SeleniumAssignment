package com.cbt.tests.Assignment1_BasicNavigationDeneme.VasylTestCase;


import com.cbt.utils.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class TestCase2 {

    public static void main(String[] args) {
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice-cybertekschool.herokuapp.com/");

        int numberOfElements= driver.findElements(By.className("list-group-item")).size();


        if(numberOfElements ==48){
        System.out.println("Test passed ");}
        else
        System.out.println("Test failed. There are "+ numberOfElements+"elements");

        driver.quit();
    }
}
