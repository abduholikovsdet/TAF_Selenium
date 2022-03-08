package com.cme.pagemethods;

import com.cme.pages.AlternateAddressPage;
import com.cme.pages.CollateralInfoPage;
import com.cme.pages.EntityInfoPage;
import com.fcbt.taf.core.reporting.Reporter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.ArrayList;

public class EntityInfoPageMethods extends CmeBasePage{
	
	public void verifyEntityInfoScreen()
	{
		try {
			isWebElementVisible(EntityInfoPage.entInfoSrn); 
			Reporter.pass("Verifying Entity Info Screen displayed");
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Reporter.addScreenshot("Exception", "Verifying Entity Info Screen displayed");
			Assert.fail("Verifying Entity Info Screen displayed");
		}
	}
	
	//Selects Entity type, entity structure, LIQ Profile type, Borrower Farming info and etc.
	public void SelectingEntityType(int i) {

		String entRelType 		=multiValueMap.get("Relationship Type").get(i);
		String entType 			=multiValueMap.get("Entity Type").get(i);
		String entStructure 	=multiValueMap.get("Entity Structure").get(i);
		String LIQProfileType 	=multiValueMap.get("LIQ Profile Type").get(i);
		String farmingCategory 	=multiValueMap.get("Farming Category").get(i);
		String yearBeganFarming =multiValueMap.get("Year Began Farming").get(i);
		String annualAgSales 	=multiValueMap.get("Gross Annual Ag Sales").get(i);
		String OfficialLoan 	=multiValueMap.get("Official Loan").get(i);

		try {
			if (!getElementAttribute(EntityInfoPage.entRelType, "title").equalsIgnoreCase(entRelType)) {
				
				inputTextUsingJS(EntityInfoPage.entRelType, entRelType); //Entering Relationship Type if not defaulted
			}

			if (entType.equalsIgnoreCase("Individual")) {
				sleep(1000);
				CheckRadioButton(EntityInfoPage.entTypeInd ); //Selecting Individual Entity Type radio button
			} else {
				sleep(1000);
				CheckRadioButton(EntityInfoPage.entTypeCom ); //Selecting Company Entity Type radio button
				sleep(1000);
			}
			inputTextUsingJS(EntityInfoPage.entStruct, entStructure);// "Selecting Individual from Entity Structure drop down
			inputTextUsingJS(EntityInfoPage.entliqProfType, LIQProfileType); //Selecting Loan IQ Profile Type
			inputTextUsingJS(EntityInfoPage.borrFarmCateg, farmingCategory); //Selecting Farming Category
			inputText(EntityInfoPage.yearBeganFarm, yearBeganFarming); //Enter Year Began Farming
			inputText(EntityInfoPage.grossAgSales, annualAgSales); //Enter Gross Annual Ag Sales
			if (OfficialLoan.equalsIgnoreCase("Yes")) {
				CheckRadioButton(EntityInfoPage.entOfficialLoanY);
				inputTextUsingJS(EntityInfoPage.entOfficialType, multiValueMap.get("Official Type").get(i));
			}
			Reporter.info("Selecting Entity Type, Structure, LIQ Profile and Farming info for Entity# "+(i+1));
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Selecting Entity Type"+"\n"+e.getMessage());
		}
	}
	
	//Enters mandatory fields in Personal Information section
	public void EnterIndEntPersonalInfo(int i) {

		String entFname			=multiValueMap.get("First Name").get(i);
		String entMname			=multiValueMap.get("Middle Name").get(i);
		String entLname			=multiValueMap.get("Last Name").get(i);
		String entSSN			=multiValueMap.get("SSN").get(i);
		String entDOB			=multiValueMap.get("Birthdate").get(i);
		String entMaritalSts 	=multiValueMap.get("Marital Status").get(i);
		String entMnthlyIncome	=multiValueMap.get("Monthly Income").get(i);
		String entNoOfYrsOwner	=multiValueMap.get("Years As Owner").get(i);
		String entNetWorth		=multiValueMap.get("Net Worth").get(i);

		try {
			inputText(EntityInfoPage.entFname, entFname); //Enter First Name
			if (entMname != "") {
				inputText(EntityInfoPage.entMname, entMname);//Enter Middle Name
			}
			inputText(EntityInfoPage.entLname, entLname); //Enter Last Name
			inputText(EntityInfoPage.entSSN, entSSN); //Enter Soc Sec #
			inputText(EntityInfoPage.entDOB, entDOB); //Enter Birthdate
			inputTextUsingJS(EntityInfoPage.entMaritalSts, entMaritalSts); //Selecting Marital Status
			inputText(EntityInfoPage.entMnthlyIncome, entMnthlyIncome); //Entering Monthly Income
			inputText(EntityInfoPage.entNoOfYrsOwner, entNoOfYrsOwner); //Entering # Years as Owner
			inputText(EntityInfoPage.entNetWorth, entNetWorth); //Entering Net Worth
			Reporter.info("Entering Personal Information for Entity# "+(i+1));
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Entering Personal Information for Entity");
		}
		
	}

	//Enters mandatory fields in Company Information section
	public void EnterCompanyInfo(int i) {

		String entLegalName			=multiValueMap.get("Legal Name").get(i);
		String entTaxID				=multiValueMap.get("Tax ID #").get(i);
		String entTaxIDCert			=multiValueMap.get("Tax ID Cert").get(i);


		try {
			inputText(EntityInfoPage.entLegalName, entLegalName); //Enter Legal Name
			inputText(EntityInfoPage.entTaxID, entTaxID); //Enter TaxID
			inputTextUsingJS(EntityInfoPage.entTaxIDCert, entTaxIDCert); //Enter TaxIDCert
			inputTextUsingJS(EntityInfoPage.entStateOrg, multiValueMap.get("State").get(i));
			Reporter.info("Entering Company Information for Entity# "+(i+1));
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Entering Company Information for Entity");
		}

	}
	
	//Enters Legal Address section
	public void EnterLegalAddress(int i) {

		String entaddr1 	=multiValueMap.get("Address 1").get(i);
		String entaddr2 	=multiValueMap.get("Address 2").get(i);
		String addrCity 	=multiValueMap.get("City").get(i);
		String addrZip1 	=multiValueMap.get("Zip Code").get(i);
		String addrState	=multiValueMap.get("State").get(i);
		String addrCounty	=multiValueMap.get("County").get(i);

		try {
			inputText(EntityInfoPage.entaddr1, entaddr1); //Enter Address 1
			inputText(EntityInfoPage.entaddr2, entaddr2); //Enter Address 1
			inputText(EntityInfoPage.addrCity, addrCity); //Enter City
			inputTextUsingJS(EntityInfoPage.addrState, addrState); //Enter State
			inputText(EntityInfoPage.addrZip1, addrZip1); //Enter Zip Code
			pressTabKey(EntityInfoPage.addrZip1);
			sleep(500);
			if (getElementAttribute(EntityInfoPage.addrCounty, "title").isEmpty()) {
				if (!addrCounty.equalsIgnoreCase("N/A")) {
					inputTextUsingJS(EntityInfoPage.addrCounty, addrCounty); //Enter County
				}else {
					click(EntityInfoPage.addrCounty);
					sleep(500);
					Actions action = new Actions(this.driver());
					action.sendKeys(Keys.ARROW_DOWN).build().perform();
					sleep(500);
					action.sendKeys(Keys.ENTER).build().perform();
					sleep(500);
				}
			}
			if (addrCounty.equalsIgnoreCase("N/A")) {
				String county = getElementAttribute(EntityInfoPage.addrCounty, "title");
				ArrayList<String> countyValues = new ArrayList<String>();
				for (int m=0; m<multiValueMap.get("County").size();m++) {
					if (m==i) {
						countyValues.add(m, county);
					}else {
						countyValues.add(m, multiValueMap.get("County").get(m));
					}
				}
				multiValueMap.get("County").clear();
				multiValueMap.put("County", countyValues);
			}


			if(!bankNumber.contentEquals("001") && (multiValueMap.get("Entity Type").get(i).equalsIgnoreCase("Individual"))) {
				inputTextUsingJS(EntityInfoPage.addrCurrLivesOn, "Same As Legal Address"); //Selecting Currently Lives on field
				CheckRadioButton(EntityInfoPage.occupySecurityYrRoundResidence_Yes); //Selecting Yes to Will you occupy security as year round residence RB
				CheckRadioButton(EntityInfoPage.claimPropertyAsHomestead_No); //Selecting No to Claim Property as homestead
			}
			Reporter.info("Entering Legal Address for Entity# "+(i+1));
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Entering Legal Address for Entity# "+(i+1));
		}
		
	}
	
	//Enters Contact Information section
	public void EnterContactInfo(int i) {
		String phoneNum = multiValueMap.get("Primary Phone Number").get(i);
		try {
			inputText(EntityInfoPage.entPrimPhone, phoneNum); //
			Reporter.info("Entering Primary Phone number as "+phoneNum);
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Entering Primary Phone number");
		}
	}
	
	//Enters NAICS Code 1 for entity
	public void EnterIndustryCodes(int i) {
		String entNAICS_Code1 = multiValueMap.get("NAICS Code 1").get(i);
		try {
			inputTextUsingJS(EntityInfoPage.entNAICS_Code1, entNAICS_Code1); //
			Reporter.info("Selecting  NAICS Code1 as "+ entNAICS_Code1);
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Selecting  NAICS Code1 as "+ entNAICS_Code1);
		}
	}
	
	//Verifies Customer number populated
	public void VerifyCustomerNumberGenerated(int i) {
		try {
			String entCustID = getElementAttribute(EntityInfoPage.entCustID, "title");
			if (!entCustID.isEmpty()) {
				Reporter.pass("Customer Number Generated for Entity# "+ (i+1));
				CustomerNumber.add(i, entCustID);
				Reporter.addScreenshot("Entity Creation","Entity #"+ (i+1) + " Created");

			} else {
				Reporter.fail("Customer Number Not Generated");
				Assert.fail("Customer Number Not Generated");
			}
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Verifying Customer number populated"+"\n"+e.getMessage());
		}
	}

	//Adds Alternate Address
	public void AddAlternateAddress(int i){
		VerifyScreenName(AlternateAddressPage.EntityAlternateAddress);
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start)/1000;
		PerfMetrics.add(new String[] { Activity, "Guaranteed Products Screen", String.valueOf(timeElapsed) });
		sleep(500);
		click(continueBtn);
	}
	//Continues through Entity Information screen for entities dynamically
	public void LoopThroughEntityInfoScreens(){
		boolean flg;
		do {
			flg = isWebElementVisible(EntityInfoPage.entInfoSrn);
			click(continueBtn);
			waitForInVisibile(PleaseWait);
			AddRelatedEntitiesPopUp("No");
			waitForInVisibile(PleaseWait);
		} while (flg);

	}
}
