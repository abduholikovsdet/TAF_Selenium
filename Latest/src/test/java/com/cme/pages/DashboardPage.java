package com.cme.pages;


import com.fcbt.taf.core.driver.ui.Locator;
public class DashboardPage {

	public static Locator dashboard = Locator.byXpath("//div[@fieldname='Dashboard GridBox']");
	public static Locator tasks = Locator.byXpath("//div[@fieldname='Tasks']");
	public static Locator newDealCreation = Locator.byXpath("//div[@fieldname='New Deal Creation']");
	public static Locator PleaseWait = Locator.byXpath("//div[@fieldname='Please Wait...']");
	public static Locator AgFastApplication = Locator.byXpath("//div[@fieldname='AgFast Application']");
	
	
}
