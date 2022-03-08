package com.cme.pagemethods;

import com.cme.pages.DealInfoPage;
import com.cme.pages.FacilityInfoPage;
import com.fcbt.taf.core.reporting.Reporter;
import org.testng.Assert;

public class DealInfoPageMethods extends CmeBasePage{

	//Enters Branch number
	public void EnterBranch(String branchNum) {
		try {
			inputTextUsingJS(DealInfoPage.dealinfoBranchNum, branchNum);
			Reporter.info("Entering Branch as "+ branchNum);
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Entering Branch Number");
		}
		
	}

	public void ValidateCRMpushedDataInApplicationScreen() {
		Reporter.info("User loads Application screen to verify CRM published Opportunity");
		int failCount=0;
		try {
			if (!getElementAttribute(DealInfoPage.dealinfoAppNum, "title").equalsIgnoreCase(dealAls)) {
				failCount++;
				Reporter.fail("Verifying Application Number displays: "+ dealAls);
			}
			if (!getElementAttribute(DealInfoPage.dealinfoAssociation, "title").equalsIgnoreCase(bankNumber)) {
				failCount++;
				Reporter.fail("Verifying Association Number displays: "+ bankNumber);
			}
			if (!getElementAttribute(DealInfoPage.dealinfoBranchNum, "title").equalsIgnoreCase(dataMap.get("Branch"))) {
				failCount++;
				Reporter.fail("Verifying Branch number displays: "+ dataMap.get("Branch"));
			}
//			double AggrDebt 	=0;
//			for (int j=0; j<numOfLoans; j++){
//				AggrDebt = AggrDebt + Double.parseDouble(multiValueMap.get("Decision Amount").get(j).replace(",", ""));
//
//			}
//			double TtlLnAmt = Double.parseDouble(getElementAttribute(DealInfoPage.dealinfoTotalLoanAmt, "title"));
//			if (TtlLnAmt!=AggrDebt) {
//				failCount++;
//				Reporter.fail("Verifying Total Loan Amount displays: "+ AggrDebt);
//			}
//			if (TtlLnAmt!=AggrDebt) {
//				failCount++;
//				Reporter.fail("Verifying Total Loan Amount displays: "+ AggrDebt);
//			}
			if (!getElementAttribute(DealInfoPage.dealinfoOppName, "title").equalsIgnoreCase(dataMap.get("Opportunity Name"))) {
				failCount++;
				Reporter.fail("Verifying Opportunity Name displays: "+ dataMap.get("Opportunity Name"));
			}
			if (!getElementAttribute(DealInfoPage.dealinfoAppDt, "title").equalsIgnoreCase(dataMap.get("Application Date"))) {
				failCount++;
				Reporter.fail("Verifying Application Date displays: "+ dataMap.get("Application Date"));
			}
			if (!getElementAttribute(DealInfoPage.dealinfoTrgtCloseDt, "title").equalsIgnoreCase(dataMap.get("Target Close date"))) {
				failCount++;
				Reporter.fail("Verifying Target Close date displays: "+ dataMap.get("Target Close date"));
			}
			if (!getElementAttribute(DealInfoPage.dealinfoDealComplx, "title").equalsIgnoreCase(dataMap.get("Deal Complexity"))) {
				failCount++;
				Reporter.fail("Verifying Deal Complexity displays: "+ dataMap.get("Deal Complexity"));
			}
			if (!getElementAttribute(DealInfoPage.dealinfoCDAType, "title").equalsIgnoreCase("New")) {
				failCount++;
				Reporter.fail("Verifying CDA Type displays: New");
			}
			if (!getElementAttribute(DealInfoPage.dealinfoOfficer, "title").equalsIgnoreCase(dataMap.get("Primary Loan Officer"))) {
				failCount++;
				Reporter.fail("Verifying Primary Loan Officer displays: "+ dataMap.get("Primary Loan Officer"));
			}
			String YesNo = dataMap.get("Territorial Concurrence?");
			if (YesNo.equalsIgnoreCase("Yes")) {
				if (!VerifyRadioButtonChecked(DealInfoPage.dealinfoTrtyCncrncReqYes)) {
					failCount++;
					Reporter.fail("Verifying Territorial Concurrence? displays: "+ YesNo);
				}
			}else{
				if (!VerifyRadioButtonChecked(DealInfoPage.dealinfoTrtyCncrncReqNo)) {
					failCount++;
					Reporter.fail("Verifying Territorial Concurrence? displays: "+ YesNo);
				}

			}
			if (failCount==0) {
				Reporter.pass("Verifying CRM published Opportunity in Application screen");
			}


		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Verifying CRM pushed data in Application screen");
		}

	}
	
	//Enters Application, Application Completed and Target Close Dates
	public void EnterApplicationDates(String appDate, String appCompleteDate, String targetCloseDate) {
		try {
			clearInput(DealInfoPage.dealinfoAppDt);
			sleep(500);
			inputText(DealInfoPage.dealinfoAppDt, appDate);
			inputText(DealInfoPage.dealinfoAppCompltDt, appCompleteDate);
			inputText(DealInfoPage.dealinfoTrgtCloseDt, targetCloseDate);
			Reporter.info("Entering Application dates");
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Entering Application Dates");
		}

	}
	
	//Enters Deal Complexity and LUS type
	public void EnterDealComplxAndLUStype(String dealComplx, String LUStype, String bankName) {
		try {
			inputTextUsingJS(DealInfoPage.dealinfoDealComplx, dealComplx);
			if (dealComplx.equalsIgnoreCase("AgFast") && bankNumber.equalsIgnoreCase("019")) {
				CheckRadioButton(DealInfoPage.AgfastPlusNo);
			}
			if(!bankName.contentEquals("001")) {
				inputTextUsingJS(DealInfoPage.dealinfoLUSType, LUStype);
				Reporter.info("Entering Deal Complexity, LUS type");
			}
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Entering Deal Complexity, LUS type");
		}
		
	}
	
	//Selecting Territorial Concurrence
	public void SelectTerritorialConcurrence(String territConcurr) {
		try {
			if (territConcurr.equalsIgnoreCase("Yes")) {
				CheckRadioButton(DealInfoPage.dealinfoTrtyCncrncReqYes);
			} else {
				CheckRadioButton(DealInfoPage.dealinfoTrtyCncrncReqNo);
			}
			Reporter.info("Selecting Territorial Concurrence RB as "+ territConcurr);
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Selecting Territorial Concurrence");
		}
		
		
	}
	
	//Selecting Notification Required RB
	public void SelectNotificationRequired(String notifRequired) {
		try {
			if (notifRequired.equalsIgnoreCase("Yes")) {
				CheckRadioButton(DealInfoPage.dealinfoNotificationYes);
			} else {
				CheckRadioButton(DealInfoPage.dealinfoNotificationNo);
			}
			Reporter.info("Selecting Notification Required RB as "+ notifRequired);
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Selecting Notification Required RB");
		}

	}
	//Selecting Notification Required RB
	public void SelectFCBTPriorApprovalRequired(String apprRequired) {
		try {

			if (isWebElementVisible(DealInfoPage.dealinfoFCBTApvlReqYes)) {
				if (apprRequired.equalsIgnoreCase("Yes")) {
	//				System.out.println("apprRequired: "+ apprRequired);
					CheckRadioButton(DealInfoPage.dealinfoFCBTApvlReqYes);
				} else {
					CheckRadioButton(DealInfoPage.dealinfoFCBTApvlReqNo);
				}
				Reporter.info("Selecting FCBT Prior Approval Required RB as "+ apprRequired);
			}
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail(e.getMessage());
		}

	}

	
	//Selecting Primary Loan Officer
	public void SelectPrimLoanOfficer(String primLO) {
		try {
			inputTextUsingJS(DealInfoPage.dealinfoOfficer, primLO);
			Reporter.info("Selecting Primary Loan Officer as "+ primLO);
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Selecting Primary Loan Officer");
		}
	}
	
	//Selecting Industry Template
	public void EnteringIndustryTemplate(String financialIndustry, String subIndustry) {
		try {
			if(bankNumber.equalsIgnoreCase("001") && (complexity.equalsIgnoreCase("AgFast w/Financials")))
			{
			}
			else {
				inputTextUsingJS(DealInfoPage.dealinfoFinIndstry, financialIndustry);
				inputTextUsingJS(DealInfoPage.dealinfoSubIndstry, subIndustry);
				Reporter.info("Selecting Industry Templates as " + financialIndustry + " and " + subIndustry);
			}
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Selecting Industry Template");
		}
	}
	//Selecting Appraiser from Deal Team
	public void SelectingDealTeam(){
		try{
			sleep(500);
			clickJS(DealInfoPage.dealinfoNewBtn); // Selecting New button
			sleep(500);
			CheckRadioButton(DealInfoPage.dealinfoRoleInternal ); //Selecting Internal radio button
			sleep(500);
			if (bankNumber.equalsIgnoreCase("001")) {

				inputTextUsingJS(DealInfoPage.dealinfoRoleSearch, "Appraiser");
				sleep(500);
				inputTextUsingJS(DealInfoPage.dealinfoContactName, Appraiser);

			}else {
				inputTextUsingJS(DealInfoPage.dealinfoRoleSearch, "Loan Admin");
				sleep(500);
				inputTextUsingJS(DealInfoPage.dealinfoContactName, LoanAdmin);
			}
			sleep(500);
			Reporter.info("Selecting "+ LoanAdmin+ " as Contact name");
			sleep(500);
			clearInput(DealInfoPage.dealinfoEmail);
		}catch (Exception e){
			Reporter.fail(e.getMessage());
			Assert.fail("Selecting deal team " + e.getMessage());
		}
	}
	//Verifies Application Number populated
	public void verifyApplicationNumberCreated(){
		sleep(1000);
		try {
			if (!getElementAttribute(DealInfoPage.dealinfoAppNum, "title").isEmpty()) {
				Reporter.pass("Application Number Generated");
				Reporter.addScreenshot("Application Number Generated", "Application Number Generated");
			}else {
				Reporter.fail("Application Number Not Generated");
				Assert.fail("Application Number Not Generated");
			}

		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Verifying Application number populated");
		}
	}

}
