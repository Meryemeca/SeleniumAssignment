package com.cbt.tests.Assignment1_BasicNavigationDeneme.VasylTestCase;

import com.cbt.utils.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TestCase1 {
    public static void main(String[] args) {
       String text ="Thank you for signing up. Click the button below to return to the home page.";
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice-cybertekschool.herokuapp.com/");
        WebElement button = driver.findElement(By.linkText("Sign Up For Mailing List"));
        button.click();
        WebElement userName= driver.findElement(By.name("full_name"));
        userName.sendKeys("meryem");
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("meryem.kus@gmail.com");
        WebElement button2 = driver.findElement(By.name("wooden_spoon"));
        button2.click();
     WebElement heading = driver.findElement(By.className("subheader"));
        if(heading.getText().equals(text)){
            System.out.println("Test passed");
        }

        driver.quit();
    }
}
