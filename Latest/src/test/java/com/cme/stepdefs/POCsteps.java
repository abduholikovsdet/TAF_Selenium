package com.cme.stepdefs;

import com.cme.pagemethods.*;
import com.cme.pages.*;
import com.fcbt.taf.core.reporting.Reporter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;
import java.io.FileInputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class POCsteps extends CmeBasePage {

	private Logger logger = LoggerFactory.getLogger(POCsteps.class);
	@Inject
	private CustomerSearchPageMethods customerSearchPageMethods;
	@Inject
	private DashboardPageMethods dashboardPageMethods;
	@Inject
	private DealInfoPageMethods dealInfoPageMethods;
	@Inject
	private EntityInfoPageMethods entityInfoPageMethods;
	@Inject
	private FacilityPageMethods facilityPageMethods;
	@Inject
	private CollateralInfoPageMethods collateralPageMethods;
	@Inject
	private FeesPageMethods feesPageMethods;
	@Inject
	private DDTPageMethods ddtPageMethods;
	@Inject
	private RoutePageMethods routePageMethods;
	@Inject
	private BorrowerRiskTypesMethods borrowerRiskTypesMethods;
	@Inject
	private YBSReportingMethods ybsReportingMethods;
	@Inject
	private LiquidCreditMethods LiquidCreditMethods;
	@Inject
	private RelatedDocsMethods RelatedDocsMethods;
	@Inject
	private GuidelineCompliancePageMethods guidelineCompliancePageMethods;
	@Inject
	private AuthorizationPageMethods authorizationPageMethods;
	@Inject
	private DocumentsPageMethods documentsPageMethods;
	@Inject
	private RelatedEntityInfoPageMethods relatedEntityInfoPageMethods;
	@Inject
	private DisbursementsPageMethods disbursementsPageMethods;
	@Inject
	private CovenantPageMethods covenantPageMethods;
	@Inject
	private RelatedDocsMethods relatedDocsMethods;
	@Inject
	private OBSPageMethods obsPageMethods;
	@Inject
	private EntityBoardingPageMethods entityBoardingPageMethods;
	@Inject
	private DealBookingPageMethods dealBookingPageMethods;

	//This class will be used as shortcut when doing R&D on any functionality.
	@When("POC Steps app number {string}")
	public void pocStepsAppNumber(String arg0) throws IOException {
//		FacilityNumber.add(0, "6009750");
//		dealAls = "37310056951";
//		int i=0;
		SwitchToOBSFrame();




	}

	@And("User loads the CRM published deal in Application activity and verifies data")
	public void userLoadsTheCRMPublishedDealInApplicationActivityAndVerifiesData() {
		dealAls = dataMap.get("Application Number");
		System.out.println("App# "+ dealAls);
		LoadDealByApplicationNumber(dealAls, "Application");
		VerifyScreenName(DealInfoPage.ApplicationSrn);
		dealInfoPageMethods.ValidateCRMpushedDataInApplicationScreen();
		inputText(DealInfoPage.dealinfoAppCompltDt, dataMap.get("Application Completed Date"));
		inputTextUsingJS(DealInfoPage.dealinfoLUSType, dataMap.get("LUS Type"));
		dealInfoPageMethods.SelectNotificationRequired(dataMap.get("Notification Required?"));
//		dealInfoPageMethods.SelectFCBTPriorApprovalRequired(dataMap.get("FCBT Prior Approval Required"));
		inputText(DealInfoPage.dealinfoCreditActionReq, "New Money Loan");
		dealInfoPageMethods.EnteringIndustryTemplate(dataMap.get("Financial Industry"), dataMap.get("Sub Industry"));
		dealInfoPageMethods.SelectingDealTeam();
		sleep(500);
		Reporter.addScreenshot("Application screen", "Application screen");
		click(continueBtn);
		waitForInVisibile(PleaseWait);
		Reporter.info("User continues to Entity Info screen");
	}


	@Then("User verifies <{int}> CRM added Entities and clicks {string} to Add-View Related Entities pop up")
	public void userVerifiesCRMAddedEntitiesAndClicksToAddViewRelatedEntitiesPopUp(int arg0, String arg1) {

		for (int i=0; i<arg0; i++) {
			int failCount = 0;
			VerifyScreenName(EntityInfoPage.entInfoSrn);
			Reporter.info("Verifying Entity# "+ (i+1));
			waitForInVisibile(PleaseWait);
			if (!getElementAttribute(EntityInfoPage.entCustID, "title").equalsIgnoreCase(multiValueMap.get("Customer Number").get(i))) {
				Reporter.fail("Verifying Customer Number displays: "+ multiValueMap.get("Customer Number").get(i));
				failCount++;
			}
			if (!getElementAttribute(EntityInfoPage.entRelType, "title").equalsIgnoreCase(multiValueMap.get("Relationship Type").get(i))) {
				Reporter.fail("Verifying Relationship type displays: "+ multiValueMap.get("Relationship Type").get(i));
				failCount++;
			}
			if (!getElementAttribute(EntityInfoPage.entStruct, "title").equalsIgnoreCase(multiValueMap.get("Entity Structure").get(i))) {
				Reporter.fail("Verifying Entity Structure displays: "+ multiValueMap.get("Entity Structure").get(i));
				failCount++;
			}
			if (!getElementAttribute(EntityInfoPage.entliqProfType, "title").equalsIgnoreCase(multiValueMap.get("LIQ Profile Type").get(i))) {
				Reporter.fail("Verifying LIQ Profile Type displays: "+ multiValueMap.get("LIQ Profile Type").get(i));
				failCount++;
			}
			if (!getElementAttribute(EntityInfoPage.entLegalName, "title").equalsIgnoreCase(multiValueMap.get("Legal Name").get(i))) {
				Reporter.fail("Verifying Legal Name displays: "+ multiValueMap.get("Legal Name").get(i));
				failCount++;
			}
			if (!getElementAttribute(EntityInfoPage.entNAICS_Code1, "title").equalsIgnoreCase(multiValueMap.get("NAICS Code 1").get(i))) {
				Reporter.fail("Verifying NAICS Code 1 displays: "+ multiValueMap.get("NAICS Code 1").get(i));
				failCount++;
			}
			if (!multiValueMap.get("Alternate Address").get(i).equalsIgnoreCase("Yes")) {
				if (!VerifyRadioButtonChecked(EntityInfoPage.entMailingAddressY)) {
					Reporter.fail("Mailing Address - Yes");
					failCount++;
				}
			} else {
				if (!VerifyRadioButtonChecked(EntityInfoPage.entMailingAddressN)) {
					Reporter.fail("Mailing Address - No");
					failCount++;
				}
			}

			if (failCount==0) {
				Reporter.pass("Verifying CRM published data for Entity# "+ (i+1));
			}
			inputText(EntityInfoPage.grossAgSales, multiValueMap.get("Gross Annual Ag Sales").get(i));

			if (multiValueMap.get("Entity Structure").get(i).equalsIgnoreCase("Individual")) {
				inputText(EntityInfoPage.entSSN, multiValueMap.get("SSN").get(i));
				inputTextUsingJS(EntityInfoPage.entMaritalSts, multiValueMap.get("Marital Status").get(i)); //Selecting Marital Status
				inputText(EntityInfoPage.entMnthlyIncome, multiValueMap.get("Monthly Income").get(i)); //Entering Monthly Income
				inputText(EntityInfoPage.entNoOfYrsOwner, multiValueMap.get("Years As Owner").get(i)); //Entering # Years as Owner
				inputText(EntityInfoPage.entNetWorth, multiValueMap.get("Net Worth").get(i)); //Entering Net Worth
				Reporter.info("Entering Personal Information for Entity# "+(i+1));

			} else if (multiValueMap.get("Entity Structure").get(i).equalsIgnoreCase("Corporation")){
				inputText(EntityInfoPage.entTaxID, multiValueMap.get("Tax ID #").get(i));
				inputTextUsingJS(EntityInfoPage.entTaxIDCert, multiValueMap.get("Tax ID Cert").get(i)); //Enter TaxIDCert
				inputTextUsingJS(EntityInfoPage.entStateOrg, multiValueMap.get("State").get(i));
				Reporter.info("Entering Company Information for Entity# "+(i+1));

			}
			ScrollDown();

			sleep(1000);

			Reporter.addScreenshot("Entity Info bottom screen", "Entity Info bottom screen");
			CustomerNumber.add(i, getElementAttribute(EntityInfoPage.entCustID, "title"));
			click(continueBtn);
			Reporter.info("Clicking Continue button in "+ getElementText(EntityInfoPage.entInfoSrn)+ " Screen");
			waitForInVisibile(PleaseWait);
			if (multiValueMap.get("Relationship Type").get(i).equalsIgnoreCase("Guarantor")) {
				VerifyScreenName(EntityInfoPage.GuaranteedProductsScrn);
				sleep(500);
				click(continueBtn);

			}
			if (multiValueMap.get("Alternate Address").get(i).equalsIgnoreCase("Yes")) {
				ViewAddAltAddressPopUp("Yes");
				entityInfoPageMethods.AddAlternateAddress(i);
			} else {
				ViewAddAltAddressPopUp("No");
			}

		}
		AddCoborrGuarCollatOwnerPopUp("No");
		AddRelatedEntitiesPopUp(arg1);

		if (arg1.equalsIgnoreCase("Yes")) {
			Reporter.info("Continuing to add Related Entity");
			int numOfRelEnts = multiValueMap.get("Related Entities:").size();
			for (int i=0; i<numOfRelEnts; i++) {
				if (multiValueMap.get("New/Existing?").get(i).equalsIgnoreCase("Existing")) {
					relatedEntityInfoPageMethods.AddExistingEntity(i);
				}else{
					relatedEntityInfoPageMethods.AddNewRelatedEntity(i);
				}
			}
			Reporter.addScreenshot("Related Entity added", "Related Entity added");
			click(continueBtn);
		}
		waitForInVisibile(PleaseWait);
	}

	@Then("User verifies <{int}> CRM added Facilities and continues to add collateral")
	public void userVerifiesCRMAddedFacilitiesAndContinuesToAddCollateral(int arg0) {

		try {
			for (int i=0; i<arg0; i++) {
				String productType      =multiValueMap.get("Product Type").get(i);
				String pricingType      =multiValueMap.get("Pricing Type").get(i);
				String decisionAmount   =multiValueMap.get("Decision Amount").get(i);
				String fxtmYesNo        =multiValueMap.get("Fixed To Maturity").get(i);
				String repriceOption    =multiValueMap.get("Reprice Option").get(i);
				String advExpireDate    =multiValueMap.get("Advance Expiration Date").get(i);
				String accrualMethod    =multiValueMap.get("Accrual Method").get(i);
				String repaymtType      =multiValueMap.get("Repayment Type").get(i);
				String paymtFreq        =multiValueMap.get("Payment Frequency").get(i);
				String loanTerm         =multiValueMap.get("Loan Term Months").get(i);
				String numOfIO          =multiValueMap.get("No. of Interest Only Pmnts").get(i);
				String loanEffDate      =multiValueMap.get("Loan Effective Date").get(i);
				String pmtStrtDate      =multiValueMap.get("Payment Start Date").get(i);
				String prePmtOption      =multiValueMap.get("Prepayment Option").get(i);

				VerifyScreenName(FacilityInfoPage.FacilityInfoSrn);
			Reporter.info("Verifying CRM published data in Facility "+(i+1));
			int failCount=0;
			if (getElementAttribute(FacilityInfoPage.facPrimBorrName, "title").equalsIgnoreCase(prmBorrFullName1)) {
				Reporter.pass("Verifying Primary Borrower field value as "+ prmBorrFullName1);
			} else {
				Reporter.fail("Verifying Primary Borrower field value as "+ prmBorrFullName1 + "Actual " +getElementAttribute(FacilityInfoPage.facPrimBorrName, "title"));
				Assert.fail("Verifying Primary Borrower field value");
			}
			FacilityNumber.add(i,getElementAttribute(FacilityInfoPage.facFacilityNo, "title"));
			if (!getElementAttribute(FacilityInfoPage.facFacilityNo, "title").equalsIgnoreCase(multiValueMap.get("Facility Number").get(i))) {
				Reporter.fail("Verifying Facility Number displays: "+ multiValueMap.get("Facility Number").get(i));
				failCount++;
			}
			if (!getElementAttribute(FacilityInfoPage.facInstType, "title").equalsIgnoreCase(multiValueMap.get("Institution Type").get(i))) {
				Reporter.fail("Verifying Institution Type displays: "+ multiValueMap.get("Institution Type").get(i));
				failCount++;
			}
			if (!getElementAttribute(FacilityInfoPage.facDealAlias, "title").equalsIgnoreCase(dataMap.get("Application Number"))) {
				Reporter.fail("Verifying Application Number displays: "+ dataMap.get("Application Number"));
				failCount++;
			}
			if (!getElementAttribute(FacilityInfoPage.facProdType, "title").equalsIgnoreCase(multiValueMap.get("Product Type").get(i))) {
				Reporter.fail("Verifying Product Type displays: "+ multiValueMap.get("Product Type").get(i));
				failCount++;
			}
			if (!getElementAttribute(FacilityInfoPage.facLoanPurp, "title").equalsIgnoreCase(multiValueMap.get("Loan Purpose").get(i))) {
				Reporter.fail("Verifying Loan Purpose displays: "+ multiValueMap.get("Loan Purpose").get(i));
				failCount++;
			}
			String YesNo = multiValueMap.get("Consumer Loan").get(i);
			if (YesNo.equalsIgnoreCase("Yes")) {
				if (!VerifyRadioButtonChecked(FacilityInfoPage.facConsumerY)) {
					Reporter.fail("Verifying Consumer Loan displays: "+ YesNo);
					failCount++;
				}
			} else {
				if (!VerifyRadioButtonChecked(FacilityInfoPage.facConsumerN)) {
					Reporter.fail("Verifying Consumer Loan displays: "+ YesNo);
					failCount++;
				}
			}
			YesNo = multiValueMap.get("Guarantee Program").get(i);
			if (YesNo.equalsIgnoreCase("Yes")) {
				if (!VerifyRadioButtonChecked(FacilityInfoPage.facGuaranteePgmY)) {
					Reporter.fail("Verifying Guarantee Program displays: "+ YesNo);
					failCount++;
				}
			} else {
				if (!VerifyRadioButtonChecked(FacilityInfoPage.facGuaranteePgmN)) {
					Reporter.fail("Verifying Guarantee Program displays: "+ YesNo);
					failCount++;
				}
			}
			if (!getElementAttribute(FacilityInfoPage.facReqAmt, "title").equalsIgnoreCase(multiValueMap.get("Requested Amount").get(i))) {
				Reporter.fail("Verifying Requested Amount displays: "+ multiValueMap.get("Requested Amount").get(i));
				failCount++;
			}
			if (!getElementAttribute(FacilityInfoPage.facPricingTyp, "title").equalsIgnoreCase(pricingType)) {
				Reporter.fail("Verifying Requested Amount displays: "+ pricingType);
				failCount++;
			}
			if (!getElementAttribute(FacilityInfoPage.facLoanNAICS, "title").equalsIgnoreCase(multiValueMap.get("Loan NAICS Code").get(i))) {
				Reporter.fail("Verifying Loan NAICS Code displays: "+ multiValueMap.get("Loan NAICS Code").get(i));
				failCount++;
			}
			if (!getElementAttribute(FacilityInfoPage.facNAICSRepayCd, "title").equalsIgnoreCase(multiValueMap.get("Repayment Source Code").get(i))) {
				Reporter.fail("Verifying Repayment Source Code displays: "+ multiValueMap.get("Repayment Source Code").get(i));
				failCount++;
			}
			if (!getElementAttribute(FacilityInfoPage.facAccrualMethod, "title").equalsIgnoreCase(accrualMethod)) {
				Reporter.fail("Verifying Accrual Method displays: "+ accrualMethod);
				failCount++;
			}
			if (!getElementAttribute(FacilityInfoPage.facRepaymentTyp, "title").equalsIgnoreCase(repaymtType)) {
				Reporter.fail("Verifying Repayment Type displays: "+ repaymtType);
				failCount++;
			}
			if (!repaymtType.equalsIgnoreCase("SP")) {
				if (!getElementAttribute(FacilityInfoPage.facPaymentFreq, "title").equalsIgnoreCase(paymtFreq.substring(0,2))) {
					Reporter.fail("Verifying Payment Frequency displays: "+ repaymtType);
					failCount++;
				}
			}

			if (!getElementAttribute(FacilityInfoPage.facLoanTermMon, "title").equalsIgnoreCase(loanTerm)) {
				Reporter.fail("Verifying Loan Term Months displays: "+ loanTerm);
				failCount++;
			}
			clearInput(FacilityInfoPage.facLoanEffDate);
			inputText(FacilityInfoPage.facLoanEffDate, loanEffDate);

			if (!getElementAttribute(FacilityInfoPage.facPrepaymentOpt, "title").equalsIgnoreCase(prePmtOption)) {
				Reporter.fail("Verifying Prepayment Option displays: "+ prePmtOption);
				failCount++;
			}

			if (failCount==0) {
				Reporter.pass("Verifying CRM published data for Facility# "+(i+1));
			}

				inputText(FacilityInfoPage.facDecisionAmt, decisionAmount); //Entering Decision Amount
			sleep(500);
			if (multiValueMap.get("Secured by Real Estate").get(i).equalsIgnoreCase("Yes")) {
				CheckRadioButton(FacilityInfoPage.facSecRealEstY); //Clicking Yes to Secured by Real Estate
			} else {
				CheckRadioButton(FacilityInfoPage.facSecRealEstN); //Clicking No to Secured by Real Estate
				if (multiValueMap.get("Secured?").get(i).equalsIgnoreCase("Yes")) {
					CheckRadioButton(FacilityInfoPage.facSecuredY);
				}else {
					CheckRadioButton(FacilityInfoPage.facSecuredN);
				}

			}
			sleep(500);
			CheckRadioButton(FacilityInfoPage.facNonAgIncDepN); //Clicking No to Non-AG Income dependent
			sleep(500);
			CheckRadioButton(FacilityInfoPage.facNonFarmIncDepN); //Clicking No to Non-Farm Income dependent
			Reporter.info("Entering Decision Amount = "+ multiValueMap.get("Decision Amount").get(i));
			Reporter.addScreenshot("Facility screen bottom", "Facility screen bottom");


			try {
				if (pricingType.equalsIgnoreCase("Fixed")) {
					if (fxtmYesNo.trim().equalsIgnoreCase("Yes")) {
						CheckRadioButton(FacilityInfoPage.facFixdToMatY); //Selecting FXTM RB as Yes

					} else if (fxtmYesNo.trim().equalsIgnoreCase("No")) {
						CheckRadioButton(FacilityInfoPage.facFixdToMatN); //Selecting FXTM RB as No
						inputTextUsingJS(FacilityInfoPage.facRepriceOption, repriceOption);
					}
				}

				if(productType.equalsIgnoreCase("Revolver To Term"))  {
					String principlePmtStrtDte = getElementAttribute(FacilityInfoPage.principlePmtStrtDte, "title");
					advExpireDate = AddMonthsToDateSetFirstDayOfMonth(principlePmtStrtDte, -1);
					inputText(FacilityInfoPage.facAdvExpireDate, advExpireDate );
				}
				inputText(FacilityInfoPage.facPaymntStrtDt, pmtStrtDate); //Entering Payment Start Date
				if(!repaymtType.equalsIgnoreCase("IO"))
					if (!repaymtType.equalsIgnoreCase("SP")) {
						clearInput(FacilityInfoPage.facNumOfIOPmts);
						inputText(FacilityInfoPage.facNumOfIOPmts, numOfIO); //Entering Number of IO payments
					}

				if (!getElementAttribute(FacilityInfoPage.facPrepaymentOpt, "title").equalsIgnoreCase(prePmtOption)) {
					inputTextUsingJS(FacilityInfoPage.facPrepaymentOpt, prePmtOption);
				}

			} catch (Exception e) {
				Reporter.fail(e.getMessage());
				Assert.fail(e.getMessage());
				Assert.fail("Entering Product Information");
			}
			sleep(500);
			click(continueBtn);
			waitForInVisibile(PleaseWait);
			Reporter.info("Continuing to Approval Purpose screen for Facility "+(i+1));
			waitForInVisibile(PleaseWait);
			VerifyScreenName(approvalPurposeSrn);
			inputTextUsingJS(FacilityInfoPage.ApprovalPurposePurposeDD, multiValueMap.get("Approval Purpose").get(i));
			inputText(FacilityInfoPage.ApprovalPurposeDescription, "Automated Test");
			clearInput(FacilityInfoPage.ApprovalPurposeAmount);
			inputText(FacilityInfoPage.ApprovalPurposeAmount, multiValueMap.get("Decision Amount").get(i));
			sleep(1000);
			Reporter.info("Clicking Continue button in "+ getElementText(approvalPurposeSrn)+" screen");
			click(continueBtn);
			waitForInVisibile(PleaseWait);
			}
			AddAnotherFacilityPopUp("No");
			waitForInVisibile(PleaseWait);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}
}
