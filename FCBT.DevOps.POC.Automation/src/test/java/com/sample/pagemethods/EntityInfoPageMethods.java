package com.sample.pagemethods;

import java.util.HashMap;

import com.sample.pages.*;
import com.fcbt.taf.core.utils.*;

public class EntityInfoPageMethods {
	
	public static void verifyEntityInfoScreen()
	{
		KeywordUtil.isWebElementPresent(EntityInfoPage.entInfoSrn, "Verifying Entity Info Screen");
	}
	
	public static void AddIndividualEntity(HashMap<String, String> dataMap) throws Exception
	{
		
		//System.out.println(ExcelDataUtil.ReadTestDataFromExcel("First Name"));
		//clickEntityType();
		KeywordUtil.click(EntityInfoPage.entType, "Selecting Individual Entity Type radio button");
		KeywordUtil.inputTextJS(EntityInfoPage.entStruct, "Individual", "Selecting Individual from Entity Sctructure drop down");
		Thread.sleep(500);
		KeywordUtil.inputTextJS(EntityInfoPage.liqProfType, "Borrower", "Selecting Borrower from Loan IQ Profile Type drop down");
		KeywordUtil.inputTextJS(EntityInfoPage.borrFarmCateg, "Full-Time Farmer", "Selecting Full-Time Farmer from Farming Category drop down");
		KeywordUtil.inputText(EntityInfoPage.yearBeganFarm, dataMap.get("Year Began Farming"), "Enter Year Began Farming");
		KeywordUtil.inputText(EntityInfoPage.grossAgSales, dataMap.get("Gross Annual Ag Sales"), "Enter Gross Annual Ag Sales");
		KeywordUtil.inputText(EntityInfoPage.entFname, dataMap.get("First Name"), "Enter First Name");
		KeywordUtil.inputText(EntityInfoPage.entMname, dataMap.get("Middle Name"), "Enter Middle Name");
		KeywordUtil.inputText(EntityInfoPage.entLname, dataMap.get("Last Name"), "Enter Last Name");
		KeywordUtil.inputText(EntityInfoPage.entSSN, dataMap.get("Soc Sec #"), "Enter Soc Sec #");
		KeywordUtil.inputText(EntityInfoPage.entDOB, dataMap.get("Birthdate"), "Enter Birthdate");
		KeywordUtil.click(EntityInfoPage.entGenderMale, "Selecting Male Gender");
		KeywordUtil.inputTextJS(EntityInfoPage.entMaritalSts, "Divorced", "Selecting Divorced from Marital Status drop down");
		KeywordUtil.inputText(EntityInfoPage.addr1, dataMap.get("Address 1"), "Enter Address 1");
		KeywordUtil.inputText(EntityInfoPage.addrCity, dataMap.get("City"), "Enter City");
		KeywordUtil.inputText(EntityInfoPage.addrZip, dataMap.get("Zip Code"), "Enter Zip Code");
		KeywordUtil.inputTextJS(EntityInfoPage.addrState, dataMap.get("State"), "Enter State");
		KeywordUtil.inputTextJS(EntityInfoPage.addrCounty, dataMap.get("County"), "Enter County");
		KeywordUtil.inputTextJS(EntityInfoPage.entNAICS_Code1, "111110", "Selecting 111110 from NAICS Code1 drop down");
		Thread.sleep(1000);
		KeywordUtil.click(EntityInfoPage.saveBtn, "Clicking Save Button");
		
	    KeywordUtil.waitForInVisibile(EntityInfoPage.PleaseWait);
	}
	
	
	/*
	 * public void clickEntityType() { KeywordUtil.click(EntityInfoPage.entType,
	 * "Selecting Individual Entity Type radio button"); }
	 */
			
}
