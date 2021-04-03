package com.sample.pagemethods;

import com.sample.pages.*;
import com.fcbt.taf.core.driver.DriverManager;
//import com.fcbt.taf.core.driver.DriverManager;
import com.fcbt.taf.core.utils.*;

public class CME_DashboardPageMethods {

	public static void verifyDashboard()
	{
		KeywordUtil.waitForPresent(CME_DashboardPage.dashboard);
		KeywordUtil.isWebElementPresent(CME_DashboardPage.dashboard, "Verifying Dashboard");
		
	}
	
	public static void newDealCreation() throws InterruptedException 
	{
			
		KeywordUtil.click(CME_DashboardPage.tasks, "Clicking Tasks from Menu Bar");
		Thread.sleep(300);
		KeywordUtil.click(CME_DashboardPage.newDealCreation, "Clicking New Deal Creation under Tasks");
		Thread.sleep(1000);
		KeywordUtil.waitForInVisibile(CME_DashboardPage.PleaseWait);
		
		
	}
	
	public static void ClickEntityMaintenance() throws InterruptedException 
	{
			
		KeywordUtil.click(CME_DashboardPage.tasks, "Clicking Tasks from Menu Bar");
		Thread.sleep(300);
		DriverManager.getDriver().findElement(CME_DashboardPage.EntityMaintenance).click();
		//KeywordUtil.click(CME_DashboardPage.EntityMaintenance, "Clicking Entity Maintenance Tasks");
		Thread.sleep(1000);
		KeywordUtil.waitForInVisibile(CME_DashboardPage.PleaseWait);
		
		
	}
	
}
