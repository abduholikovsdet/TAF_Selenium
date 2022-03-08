package com.cme.pagemethods;

import com.cme.pages.CustomerSearchPage;
import com.cme.pages.EntityInfoPage;
import com.cme.pages.RelatedEntitiesPage;
import com.fcbt.taf.core.reporting.Reporter;
import org.testng.Assert;

public class RelatedEntityInfoPageMethods extends CmeBasePage{

	public void AddTitleCompany(){
		TitleCompany = GetRandomString()+ " Titles";
//		String city = multiValueMap.get("Collateral City").get(0);
//		String zip = multiValueMap.get("Collateral Zip Code").get(0);
//		String county = multiValueMap.get("Collateral County").get(0);
		String state = multiValueMap.get("Collateral State").get(0);
		Reporter.info("Adding Title company as "+ TitleCompany);
		CheckRadioButton(RelatedEntitiesPage.RelEntTypeComp);
		inputTextUsingJS(RelatedEntitiesPage.RelEntEntStruc, "Limited Liability Company");
		inputTextUsingJS(RelatedEntitiesPage.RelEntReltype, "Document Relationship");
		inputTextUsingJS(RelatedEntitiesPage.RelEntDocRelType, "Title Insurance Provider");
		CheckRadioButton(RelatedEntitiesPage.RelEntDocSignerNo);
		inputText(RelatedEntitiesPage.RelEntLegalName, TitleCompany);
		inputTextUsingJS(RelatedEntitiesPage.TaxIDCert, "Limited Liability Company");
		inputTextUsingJS(RelatedEntitiesPage.StateOfOrg, state);
//		inputText(RelatedEntitiesPage.RelEntAdd1, "569 "+ GetRandomString());
//		inputText(RelatedEntitiesPage.RelEntCity, city);
//		inputText(RelatedEntitiesPage.RelEntZipCode, zip);
//		inputTextUsingJS(RelatedEntitiesPage.RelEntState, state);
//		inputTextUsingJS(RelatedEntitiesPage.RelEntCounty, county);
		sleep(500);
		click(saveBtn);
		waitForInVisibile(PleaseWait);
		Reporter.addScreenshot("Title company added", "Title company added");
	}
	//Adds Related entity as per test data file
	public void AddNewRelatedEntity(int i){
		try {
			VerifyScreenName(RelatedEntitiesPage.RelatedEntitiesScrn);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Related Entities Screen", String.valueOf(timeElapsed) });
			sleep(500);
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	//Adds Related entity as per test data file
	public void AddExistingEntity(int i){
		String RelatToCust 		=multiValueMap.get("Related To").get(i);
		String custNumber 		=multiValueMap.get("Rel Ent Customer Number").get(i);
		String RelEntRelType 	=multiValueMap.get("Rel Ent Relationship Type").get(i);
		String RelEntTitle 		=multiValueMap.get("Rel Ent Title").get(i);
		String DocSigner 		=multiValueMap.get("Rel Ent Document Signer").get(i);
		String selectEnt;
		String[] RelatToCustArr = RelatToCust.split(" ");
		int ReToEntNum = Integer.parseInt(RelatToCustArr[1]);
		ReToEntNum = ReToEntNum-1;
		if (custNumber.contains("Entity")) {
			String[] custNumArr = custNumber.split(" ");
			int num = Integer.parseInt(custNumArr[1]);
			custNumber = CustomerNumber.get(num-1);
		}

		if (multiValueMap.get("Entity Type").get(ReToEntNum).equalsIgnoreCase("Individual")) {
			selectEnt = multiValueMap.get("Full Name").get(ReToEntNum);
		} else {
			selectEnt = multiValueMap.get("Legal Name").get(ReToEntNum);

		}
		try {
			VerifyScreenName(RelatedEntitiesPage.RelatedEntitiesScrn);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Related Entities Screen", String.valueOf(timeElapsed) });
			sleep(500);
			clickGridBoxCellElement(selectEnt);
			sleep(500);
			click(RelatedEntitiesPage.AddRelatedEntityBtn);
			waitForInVisibile(PleaseWait);
			VerifyScreenName(CustomerSearchPage.custSearchSrn);// Verifying Customer Search Screen
			inputTextUsingJS(CustomerSearchPage.bankNumber, bankNumber); //Selecting Bank number
			inputTextUsingJS(CustomerSearchPage.SearchType, "Customer Number");
			sleep(1000);
			inputText(CustomerSearchPage.nameSearchFld, custNumber); //Entering Customer number
			sleep(1000);
			click(CustomerSearchPage.searchBtn); //Clicking Search Button
			sleep(1000);
			waitForInVisibile(PleaseWait);
			String custNumXpath = "//div[@class='gridBoxCell' and text()='"+ custNumber + "']";
			if (isWebElementVisible(GetLocator(custNumXpath))) {
				click(CustomerSearchPage.continueBtn); //Clicking Continue Button
			} else {
				Reporter.fail("Customer Number Not Found");
				Assert.fail();
			}
			waitForInVisibile(PleaseWait);
			Reporter.info("Clicking Continue button to add existing customer");

			if (!getElementAttribute(RelatedEntitiesPage.RelEntCustNum, "title").equalsIgnoreCase(custNumber)) {
				Reporter.fail("Customer Number not matched");
				Assert.fail();
			}
			inputTextUsingJS(RelatedEntitiesPage.RelEntReltype, RelEntRelType);
			inputTextUsingJS(RelatedEntitiesPage.RelEntTitle, RelEntTitle);
			if (DocSigner.equalsIgnoreCase("Yes")) {
				CheckRadioButton(RelatedEntitiesPage.RelEntDocSignerYes);
			} else {
				CheckRadioButton(RelatedEntitiesPage.RelEntDocSignerNo);
			}
			if (getElementAttribute(RelatedEntitiesPage.RelEntPrmryPh, "title").isEmpty()) {
				inputText(RelatedEntitiesPage.RelEntPrmryPh, multiValueMap.get("Primary Phone Number").get(ReToEntNum));
			}


			Reporter.addScreenshot("Related Entity added", "Related Entity added");
			if (multiValueMap.get("Entity Type").get(ReToEntNum).equalsIgnoreCase("Company")) {
				GetCompanyContact(ReToEntNum);
			}
			sleep(500);
			click(saveBtn);
			waitForInVisibile(PleaseWait);
			Reporter.info("Related Entity# "+ (i+1)+" added");


		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail(e.getMessage());
		}

	}

	public void GetCompanyContact(int i){
		try {

			CompanyContactFName.put(CustomerNumber.get(i), getElementAttribute(RelatedEntitiesPage.RelEntFName, "title"));
			CompanyContactMName.put(CustomerNumber.get(i), getElementAttribute(RelatedEntitiesPage.RelEntMName, "title"));
			CompanyContactLName.put(CustomerNumber.get(i), getElementAttribute(RelatedEntitiesPage.RelEntLName, "title"));
			CompanyContactPhone.put(CustomerNumber.get(i), getElementAttribute(RelatedEntitiesPage.RelEntPrmryPh, "title"));
			String addr1 = getElementAttribute(RelatedEntitiesPage.RelEntAdd1, "title");
			String city = getElementAttribute(RelatedEntitiesPage.RelEntCity, "title");
			String state = getElementAttribute(RelatedEntitiesPage.RelEntState, "title");
			String zip = getElementAttribute(RelatedEntitiesPage.RelEntZipCode, "title");
			String county = getElementAttribute(RelatedEntitiesPage.RelEntCounty, "title");
			String fullAddr = addr1 + "," + " " + city + "," + " " + state + "," + " " + zip + "," + " " + county;
			CompanyContactAddress.put(CustomerNumber.get(i), fullAddr);
//			System.out.println("Company contact for "+CustomerNumber.get(i)+"\n"+CompanyContactFName.get(CustomerNumber.get(i))+"\n"+CompanyContactMName.get(CustomerNumber.get(i))+"\n"+CompanyContactLName.get(CustomerNumber.get(i))+"\n"+CompanyContactPhone.get(CustomerNumber.get(i))+"\n"+CompanyContactAddress.get(CustomerNumber.get(i)));


		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail(e.getMessage());
		}

	}

}
