package com.sample.pagemethods;

import com.fcbt.taf.core.driver.DriverManager;
import com.sample.pages.AmazonHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonHomePageMethods {

    private WebDriver driver;
    public AmazonHomePageMethods(WebDriver driver)
    {
        this.driver = driver;
    }

    public void clickOnLoginButton(){
        driver.findElement(By.xpath(AmazonHomePage.btnSignIn)).click();
        DriverManager.getDriver();
    }

}
