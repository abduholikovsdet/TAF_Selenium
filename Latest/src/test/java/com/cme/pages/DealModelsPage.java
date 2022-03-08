package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;


public class DealModelsPage {

	public static Locator DealModelsScrn = Locator.byXpath("//a[@fieldname='Deal Models']");

	public static Locator dealModelsScrn = Locator.byXpath("//a[@fieldname='Deal Models']");
	public static Locator userName = Locator.byXpath("//div[@fieldname='WRK_USERNAME']/input");
	public static Locator password = Locator.byXpath("//div[@fieldname='WRK_PASSWORD']/input");
	public static Locator createBtn = Locator.byXpath("//a[@fieldname='Create']");
	public static Locator refreshBtn = Locator.byXpath("//a[@fieldname='Refresh']");
	public static Locator launchBtn = Locator.byXpath("//a[@fieldname='Launch']");
	public static Locator searchType = Locator.byXpath("//div[@fieldname='searchType']/input");
	public static Locator searchValue = Locator.byXpath("//div[@fieldname='searchValue']/input");
	public static Locator loadHTML5Version = Locator.byXpath("//div[@fieldname='Would you like to load the new HTML5 Version?']");
	public static Locator NoRecordsFound = Locator.byXpath("//div[@class='gridBoxCell' and text() = 'No Records Found']");
	public static Locator selectPortfolio = Locator.byXpath("//div[@class='label' and text() = 'Select Portfolio']");
	public static Locator selectStatements = Locator.byXpath("//div[@class='label' and text() = 'Select Statements to Import']");
	public static Locator selectStatementsChkbx = Locator.byXpath("//div[@fieldname='Select Statements to Import']/parent::div//div[text()='Statement Date']/div");
}
