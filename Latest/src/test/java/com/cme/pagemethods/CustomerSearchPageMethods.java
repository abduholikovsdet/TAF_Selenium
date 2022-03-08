package com.cme.pagemethods;

import com.cme.pages.CustomerSearchPage;
import com.fcbt.taf.core.reporting.Reporter;
import org.testng.Assert;

public class CustomerSearchPageMethods extends CmeBasePage{
	
	public void verifyCustomerSearchScreen()
	{
		waitForVisible(CustomerSearchPage.custSearchSrn);
		isWebElementPresent(CustomerSearchPage.custSearchSrn); // Verifying Customer Search Screen
	}
	
	public void SearchNewCustomer()
	{
		try {
			//Searching for random value to enable New button
			inputText(CustomerSearchPage.nameSearchFld, "jhjhks"); //Entering Random Text
			sleep(1000);
			click(CustomerSearchPage.searchBtn); //Clicking Search Button
			sleep(1000);
			waitForInVisibile(PleaseWait);
			click(CustomerSearchPage.newBtn); //Clicking New Button
			waitForInVisibile(PleaseWait);

		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail(e.getMessage());
		}

	}
	
}
