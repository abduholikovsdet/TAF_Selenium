package com.cme.pages;

import com.fcbt.taf.core.driver.ui.Locator;

public class CustomerSearchPage {

	public static Locator custSearchSrn = Locator.byXpath("//a[@class='floatDivLeft' and contains(text(),'Customer Search')]");
	public static Locator bankNumber = Locator.byXpath("//div[@fieldname='WK_CLSBKNO']/input");
	public static Locator SearchType = Locator.byXpath("//div[@fieldname='SRCH_TYPE']/input");
	public static Locator nameSearchFld = Locator.byXpath("//div[@fieldname='WRK_NAME_SEARCH']/input");
	public static Locator searchBtn = Locator.byXpath("//a[@fieldname='Search']");
	public static Locator newBtn = Locator.byXpath("//a[@fieldname='New']");
	public static Locator continueBtn = Locator.byXpath("//a[@fieldname='Continue']");
	public static Locator NoMatchesFound = Locator.byXpath("//div[@class='gridBoxCell' and text() = 'NO MATCHES FOUND']");
		
}
