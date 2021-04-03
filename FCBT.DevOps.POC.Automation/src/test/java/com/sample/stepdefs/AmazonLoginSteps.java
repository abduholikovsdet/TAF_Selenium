package com.sample.stepdefs;

import com.fcbt.taf.core.driver.DriverManager;
import com.fcbt.taf.core.reporting.Reporter;
import com.sample.pagemethods.AmazonHomePageMethods;
import io.cucumber.java.en.Given;

public class AmazonLoginSteps {

    private AmazonHomePageMethods amazonHomePageMethods;

    public AmazonLoginSteps(){
        amazonHomePageMethods = new AmazonHomePageMethods(DriverManager.getDriver());
    }

    @Given("user navigate to amazon")
    public void userNavigateToAmazon(){
        DriverManager.getDriver().navigate().to("https://www.amazon.in/");
    }

    @Given("user click on Login button")
    public void userClickOnLoginButton(){
        amazonHomePageMethods.clickOnLoginButton();
        sleep(2000);
        Reporter.addScreenshot("Login_Page", "My Description");
    }

    public void sleep(int time){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
