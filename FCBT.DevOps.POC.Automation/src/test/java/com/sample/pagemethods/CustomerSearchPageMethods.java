package com.sample.pagemethods;

import com.sample.pages.*;
import com.fcbt.taf.core.utils.*;

public class CustomerSearchPageMethods {
	
	public static void verifyCustomerSearchScreen()
	{
		KeywordUtil.waitForVisible(CustomerSearchPage.custSearchSrn);
		KeywordUtil.isWebElementPresent(CustomerSearchPage.custSearchSrn, "Verifying Customer Search Screen");
	}
	
	public static void SearchNewCustomer() throws InterruptedException
	{
		//Searching for random value to enable New button
		KeywordUtil.inputText(CustomerSearchPage.nameSearchFld, "jhjhks", "Entering Random Text");
		Thread.sleep(1000);
		KeywordUtil.click(CustomerSearchPage.searchBtn, "Clicking Search Button");
		Thread.sleep(1000);
		KeywordUtil.waitForInVisibile(CustomerSearchPage.PleaseWait);
		KeywordUtil.click(CustomerSearchPage.newBtn, "Clicking New Button");
		KeywordUtil.waitForInVisibile(CustomerSearchPage.PleaseWait);
		
	}
	
}
