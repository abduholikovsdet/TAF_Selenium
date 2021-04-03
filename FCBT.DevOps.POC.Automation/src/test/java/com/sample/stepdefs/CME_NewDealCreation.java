package com.sample.stepdefs;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.sample.pages.*;
import com.sample.pagemethods.*;
import io.cucumber.java.en.*;

import com.fcbt.taf.core.driver.DriverManager;
import com.fcbt.taf.core.reporting.Reporter;
import com.fcbt.taf.core.utils.*;

public class CME_NewDealCreation extends KeywordUtil {

	@SuppressWarnings("rawtypes")
	static Class thisClass = CME_NewDealCreation.class;
	static String testCaseID = thisClass.getSimpleName();

	static String logStep;
	public WebDriver driver;
	public static HashMap<String, String> dataMap = new HashMap<String, String>();
	String email = "testing" + KeywordUtil.getCurrentDateTime() + "@testing.com";




	@Given("^User Launches CME$")
	public void user_Launches_CME() throws Throwable {
		try {
			
			//navigateToUrl(ExcelDataUtil.getCommonSettings().getUrl());
			navigateToUrl("http://fv-inttst.farmcreditbank.com/");
			//Thread.sleep(5000);
			KeywordUtil.waitForInVisibile(CME_DashboardPage.PleaseWait);
			KeywordUtil.switchToFrameToBeAvailable("iframe");
			//Thread.sleep(2000);
			KeywordUtil.waitForInVisibile(CME_DashboardPage.PleaseWait);
			/*
			 * String ActualTitle = DriverManager.getDriver().getTitle(); String
			 * ExpectedTitle = "CME-Onboarding1"; Assert.assertEquals(ActualTitle,
			 * ExpectedTitle);
			 */
			
		} catch (Throwable e) {
			Reporter.fail("Launching URL");
			Assert.fail(e.getMessage());
		}
	}

	@When("^User Verifies Dashboard screen loaded$")
	public void user_Verifies_Dashboard_screen_loaded() throws Throwable {
		try {
			CME_DashboardPageMethods.verifyDashboard();
			  Reporter.pass("Verifying Dashboard");
			  //Reporter.addScreenshot("dashboard", "CME Dashboard Loaded");
		} catch (Throwable e) {
			Reporter.fail("Verifying Dashboard", e);
			Assert.fail(e.getMessage());
		}
	}

	@Then("^User clicks New Deal Creation$")
	public void user_clicks_New_Deal_Creation() throws Throwable {
		try {
			CME_DashboardPageMethods.newDealCreation();
		} catch (Throwable e) {
			Reporter.fail("Clicking New Deal Creation");
			Assert.fail(e.getMessage());
		}

	}

	@Then("^User verifies Customer Search Page loaded$")
	public void user_verifies_Customer_Search_Page_loaded() throws Throwable {
		try {
			CustomerSearchPageMethods.verifyCustomerSearchScreen();
			Reporter.pass("Verifying Customer Search Screen Loaded");
			//Reporter.addScreenshot("custSearchScreen", "Customer Search Screen Loaded");
		} catch (Throwable e) {
			Reporter.fail("Verifying Customer Search Screen Loaded");
			Assert.fail(e.getMessage());
		}

	}

	@Then("^User Enters Customer name in Name Search field and Clicks New button$")
	public void user_Enters_Customer_name_in_Name_Search_field_and_Clicks_New_button() throws Throwable {
		dataMap = ExcelDataUtil.ReadTestDataFromExcel("CME");
		
		try {
			CustomerSearchPageMethods.SearchNewCustomer();
		} catch (Throwable e) {
			Assert.fail(e.getMessage());
		}

	}

	@Then("^User Verifies Entity Information screen loaded$")
	public void user_Verifies_Entity_Information_screen_loaded() throws Throwable {
		try {
			KeywordUtil.isWebElementPresent(EntityInfoPage.entInfoSrn, "Verifying Entity Info Screen");
			Reporter.pass("Verifying Entity Info Screen Loaded");
			//Reporter.addScreenshot("EntInfoScrn", "Entity Info Screen Loaded");
		} catch (Throwable e) {
			Reporter.fail("Verifying Entity Info Screen Loaded");
			Assert.fail(e.getMessage());
		}

	}

	@Then("^User Enters Customer Info and Saves$")
	public void user_Enters_Customer_Info_and_Saves() throws Throwable {
		try {
			EntityInfoPageMethods.AddIndividualEntity(dataMap);
			Thread.sleep(1000);
			Reporter.addScreenshot("IndEntCreated", "Entity Created");
		} catch (Throwable e) {
			
			Assert.fail(e.getMessage());
		}

	}

	
	
	
	@Then("User clicks Entity Maintenance from Tasks")
	public void user_clicks_entity_maintenance_from_tasks() {
		try {
			CME_DashboardPageMethods.ClickEntityMaintenance();
		} catch (Throwable e) {
			Reporter.fail("Clicking Entity Maintenance");
			Assert.fail(e.getMessage());
		}
		
	}

	
	
	
	
}
