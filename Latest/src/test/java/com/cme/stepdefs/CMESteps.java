package com.cme.stepdefs;

import com.cme.pagemethods.*;
import com.cme.pages.*;
import com.fcbt.taf.core.driver.ui.Locator;
import com.fcbt.taf.core.reporting.Reporter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CMESteps extends CmeBasePage{

	private Logger logger = LoggerFactory.getLogger(CMESteps.class);
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
	private CovenantPageMethods covenantPageMethods;
	@Inject
	private AuthorizationPageMethods authorizationPageMethods;
	@Inject
	private DocumentsPageMethods documentsPageMethods;
	@Inject
	private RelatedEntityInfoPageMethods relatedEntityInfoPageMethods;
	@Inject
	private DisbursementsPageMethods disbursementsPageMethods;
	@Inject
	private RelatedDocsMethods relatedDocsMethods;
	@Inject
	private OBSPageMethods obsPageMethods;
	@Inject
	private EntityBoardingPageMethods entityBoardingPageMethods;
	@Inject
	private DealBookingPageMethods dealBookingPageMethods;




	/**
	 * Author: Muzaffar A.
	 * Description: Generates new data in test data file for new execution upon calling
	 * @param string - test data file path
	 */
	@Given("Generating new test data in test data file {string}")
	public void generatingNewTestDataInTestDataFile(String string) {
		CustomerNumber.clear();
		FacilityNumber.clear();
		LoanID.clear();
//		CSiDocsValues.clear();
//		CompanyContact.clear();
		RunMacrosInExcel(string); //Generating new data in test data file
		sleep(5000);

	}

	/**
	 * Author: Muzaffar A.
	 * Description: Generates new data in java class and writes in test data file for new execution
	 * @param arg0 - testDataFilePath
	 * @param arg1 - Association number
	 * @param arg2 - Environment
	 */
	@Given("Generating new test data {string} for Association {string} in Environment {string}")
	public void generatingNewTestDataForAssociationInEnvironment(String arg0, String arg1, String arg2) {
		CustomerNumber.clear();
		FacilityNumber.clear();
		LoanID.clear();
		CollateralDesc.clear();
//		CSiDocsValues.clear();
//		CompanyContact.clear();
		GenerateTestDataWriteToExcel(arg0, arg1, arg2, "CME"); //Generating new data in test data file
	}

	/**
	 * Author: Muzaffar A.
	 * Activity: All
	 * Description: Reads test data from excel in given location
	 * @param string - test data file path
	 */
	@Given("Reading test data from excel file {string}")
	public void reading_test_data_from_excel_file(String string) {

		try {
			dataMap 			=ReadTestDataFromExcel(string, "CME");
			EnvName				=dataMap.get("Environment");
			bankNumber			=dataMap.get("Bank");
			ProcessCenter		=dataMap.get("Process Center");
			complexity 			=dataMap.get("Deal Complexity");
			numOfEnts 			=multiValueMap.get("Entities:").size();
			numOfLoans 			=multiValueMap.get("Facilities:").size();
			numOfCollaterals	=multiValueMap.get("Collaterals:").size();
			prmBorrFullName1 	=multiValueMap.get("Legal Name").get(0);
			prmBorrFullName2 	=multiValueMap.get("Full Name").get(0);
			PrmLoanOfficer 		=dataMap.get("Primary Loan Officer");
			Appraiser 			=dataMap.get("Appraiser");
			LoanAdmin			=dataMap.get("Loan Admin");
			for (int f=0;f<numOfLoans;f++){
				GuarantorFlg.add(f, false);
				GuaranteePrgrmFlg.add(f, false);
			}
			if (bankNumber.equalsIgnoreCase("001")) {
				DDTOfficerName 		=Appraiser;
			}else{
				DDTOfficerName 		=LoanAdmin;
			}
			ProcessCenter		=dataMap.get("Process Center");

			System.out.println("Loan Admin: "+ LoanAdmin +"\n" + "Appraiser: "+ Appraiser);

			//Borrower Level stock Associations
			BLStk = false;
			if (bankNumber.equalsIgnoreCase("020") || bankNumber.equalsIgnoreCase("019") || bankNumber.equalsIgnoreCase("004") || bankNumber.equalsIgnoreCase("008") ||
					bankNumber.equalsIgnoreCase("014") || bankNumber.equalsIgnoreCase("005") || bankNumber.equalsIgnoreCase("016"))
			{
				BLStk = true;
			}
			driver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			InitializeBankRelatedData();
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
		}
	}

	/**
	 * Author: Muzaffar A.
	 * Activity: All
	 * Description: User launches CME and verifies Dashboard screen loaded
	 */
	@When("User launches CME as {string} and verifies Dashboard screen loaded")
	public void userLaunchesCMEAsAndVerifiesDashboardScreenLoaded(String arg0) {

		try {
			EnvName =dataMap.get("Environment");
			dashboardPageMethods.launchCMEInstance(EnvName, arg0);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Author: Muzaffar A.
	 * Activity: Application
	 * Description: User enters mandatory fields in Application screen
	 *
	 */
	@Then("User enters mandatory fields in Application screen")
	public void userEntersMandatoryFieldsInApplicationScreen() {

		VerifyScreenName(DealInfoPage.ApplicationSrn);
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start)/1000;
		PerfMetrics.add(new String[] { Activity, "Application Screen", String.valueOf(timeElapsed) });
		dealInfoPageMethods.EnterBranch(dataMap.get("Branch"));
		dealInfoPageMethods.EnterApplicationDates(dataMap.get("Application Date"), dataMap.get("Application Completed Date"), dataMap.get("Target Close date"));
		dealInfoPageMethods.EnterDealComplxAndLUStype(dataMap.get("Deal Complexity"), dataMap.get("LUS Type"), bankNumber);
		dealInfoPageMethods.SelectTerritorialConcurrence(dataMap.get("Territorial Concurrence?"));
		dealInfoPageMethods.SelectNotificationRequired(dataMap.get("Notification Required?"));
		if (dataMap.get("FCBT Prior Approval Required")!=null) {
			dealInfoPageMethods.SelectFCBTPriorApprovalRequired(dataMap.get("FCBT Prior Approval Required"));
		}

		if (!bankNumber.equalsIgnoreCase("001")) {
			inputText(DealInfoPage.dealinfoCreditActionReq, "New Money Loan");
		}
		dealInfoPageMethods.SelectPrimLoanOfficer(PrmLoanOfficer);
		dealInfoPageMethods.EnteringIndustryTemplate(dataMap.get("Financial Industry"), dataMap.get("Sub Industry"));
		dealInfoPageMethods.SelectingDealTeam();

		sleep(500);
		Reporter.addScreenshot("Application screen", "Application screen");
		dealNumber = GetDealNumber();
		click(continueBtn);
//		waitForInVisibile(PleaseWait);
	}
	/**
	 * Author: Muzaffar A.
	 * Activity: Application
	 * Description: User User starts New Deal Creation or AgFast Application wizard
	 */
	@And("User starts {string} wizard")
	public void userStartsWizard(String arg0) {
		Activity = "Application";
		String custName = multiValueMap.get("Legal Name").get(0);
		dashboardPageMethods.newDealCreation(arg0, bankNumber, custName);
		ClickMenuItem(Deal, ExitCurrentTask);
		waitForInVisibile(PleaseWait);
		if (BLStk) {
			if (arg0.equalsIgnoreCase("New Deal Creation")) {
				ClickMenuItem(Tasks, ApplicationB);
			} else {
				ClickMenuItem(Tasks, AgFastApplicationB);
			}
		} else  {
			if (arg0.equalsIgnoreCase("New Deal Creation")) {
				ClickMenuItem(Tasks, ApplicationF);
			} else {
				ClickMenuItem(Tasks, AgFastApplicationF);
			}
		}

		start = System.currentTimeMillis();
	}

	/**
	 * Author:
	 * Activity: Application
	 * Description: User enters Scored Financials and continues to Product Info screen
	 */
	@And("User enters Scored Financials and continues to Product Info screen")
	public void userEntersScoredFinancialsAndContinuesToProductInfoScreen() {
		VerifyScreenName(Liquidscorepage.scoredFinancial);
		LiquidCreditMethods.FillScoredFinancialscreen();
		//Click continue button
		click(continueBtn); //click continue button in liquid credit'
		waitForInVisibile(PleaseWait);
		VerifyScreenName(Liquidscorepage.liquidCreditScreen);
		click(continueBtn); //click continue to product info screen
		waitForInVisibile(PleaseWait);
		sleep(1000);

	}

	/**
	 * Author: Muzaffar A.
	 * Activity: Application
	 * Description: 1. If Add Coborrower, Guarantor pop up exists it clicks Yes button, chooses bank and clicks New button in Customer search screen
	 *				2. Adds Entity, continues to Additional Entity Details screen and Adds Alternate Address based on selection in test data file
	 * @param arg0 - Number of entities to be added
	 * @param arg1 - Add/View Related Entities selection Yes/No
	 */
	@Then("User adds <{int}> Entities and clicks {string} to Add-View Related Entities pop up")
	public void userAddsEntitiesAndClicksToAddViewRelatedEntitiesPopUp(int arg0, String arg1)  {
		if (ExistingCustomerFlag) {
			Assert.fail("Existing Customer");
		}
		for (int i=0; i<arg0; i++) {

			String custName = null;
			custName = multiValueMap.get("Legal Name").get(i);
			if (i>0) {
				AddCoborrGuarCollatOwnerPopUp("Yes");
				waitForInVisibile(PleaseWait);
				VerifyScreenName(CustomerSearchPage.custSearchSrn);// Verifying Customer Search Screen
				inputTextUsingJS(CustomerSearchPage.bankNumber, bankNumber); //Selecting Bank number
				inputText(CustomerSearchPage.nameSearchFld, custName); //Entering Random Text
				sleep(1000);
				click(CustomerSearchPage.searchBtn); //Clicking Search Button
				sleep(1000);
				waitForInVisibile(PleaseWait);
				click(CustomerSearchPage.newBtn); //Clicking New Button
				waitForInVisibile(PleaseWait);
				waitForInVisibile(PleaseWait);
				Reporter.info("Clicking New button to add a new customer");
				VerifyScreenName(EntityInfoPage.entInfoSrn);
			}
			Reporter.info("Adding Entity# "+ (i+1));

			if (i==0) {
				waitForInVisibile(PleaseWait);
				if (getElementAttribute(EntityInfoPage.entRelType, "title").equalsIgnoreCase("Borrower")) {
					Reporter.pass("Verifying Relationship type is defaulted to Borrower");
				} else {
					Reporter.fail("Verifying Primary Borrower Relationship type is defaulted to Borrower");
					Reporter.addScreenshot("Primary Borrower Relationship type","Verifying Primary Borrower Relationship type is defaulted to Borrower");
				}
			}
			entityInfoPageMethods.SelectingEntityType(i);

			if (multiValueMap.get("Entity Type").get(i).equalsIgnoreCase("Individual")) {
				entityInfoPageMethods.EnterIndEntPersonalInfo(i);
			} else if (multiValueMap.get("Entity Type").get(i).equalsIgnoreCase("Company")) {
				entityInfoPageMethods.EnterCompanyInfo(i);
			}
			entityInfoPageMethods.EnterLegalAddress(i);
			entityInfoPageMethods.EnterContactInfo(i);
			entityInfoPageMethods.EnterIndustryCodes(i);
			sleep(1000);
			click(saveBtn);
			waitForInVisibile(PleaseWait);
			entityInfoPageMethods.VerifyCustomerNumberGenerated(i);
			click(continueBtn);
			Reporter.info("Clicking Continue button in Entity Information screen");
			if (multiValueMap.get("Relationship Type").get(i).equalsIgnoreCase("Guarantor")) {
				start = System.currentTimeMillis();
				VerifyScreenName(EntityInfoPage.GuaranteedProductsScrn);
				finish = System.currentTimeMillis();
				timeElapsed = (finish - start)/1000;
				PerfMetrics.add(new String[] { Activity, "Guaranteed Products Screen", String.valueOf(timeElapsed) });
				sleep(500);
				click(continueBtn);

			}
			if (multiValueMap.get("Alternate Address").get(i).equalsIgnoreCase("Yes")) {
				ViewAddAltAddressPopUp("Yes");
				start = System.currentTimeMillis();
				entityInfoPageMethods.AddAlternateAddress(i);
			} else {
				ViewAddAltAddressPopUp("No");
			}

		}
		AddCoborrGuarCollatOwnerPopUp("No");
		AddRelatedEntitiesPopUp(arg1);

		if (arg1.equalsIgnoreCase("Yes")) {
			start = System.currentTimeMillis();
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
		start = System.currentTimeMillis();
//		waitForInVisibile(PleaseWait);
	}

	/**
	 * Author: Muzaffar A.
	 * Activity: Application
	 * Description: 1. If Add Coborrower, Guarantor pop up exists it clicks Yes button, chooses bank and clicks New button in Customer search screen
	 *				2. Adds Entity, continues to Additional Entity Details screen and Adds Alternate Address based on selection in test data file
	 * @param arg0 - Number of entities to be added
	 * @param arg1 - Add/View Related Entities selection Yes/No
	 */
	@Then("User adds <{int}> Existing Entities and clicks {string} to Add-View Related Entities pop up")
	public void userAddsExistingEntitiesAndClicksToAddViewRelatedEntitiesPopUp(int arg0, String arg1)  {
		int i=0;
		Reporter.info("Adding Entity# "+ (i+1));

		String entCustID = getElementAttribute(EntityInfoPage.entCustID, "title");
		CustomerNumber.add(i, entCustID);
		click(continueBtn);
		Reporter.info("Clicking Continue button in Entity Information screen");
		waitForInVisibile(PleaseWait);

		ViewAddAltAddressPopUp("No");
		AddCoborrGuarCollatOwnerPopUp("No");
		AddRelatedEntitiesPopUp(arg1);
		waitForInVisibile(PleaseWait);
	}

	/**
	 * Author: Muzaffar A.
	 * Activity: Application
	 * Description: User adds given number of facilities and continues to Collateral
	 * @param arg0
	 */
	@Then("User adds <{int}> Facilities and continues to add collateral")
	public void userAddsFacilitiesAndContinuesToAddCollateral(int arg0) {
		waitForClickable(FacilityInfoPage.ProductInfoSrn);
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start)/1000;
		PerfMetrics.add(new String[] { Activity, "Product Info Screen", String.valueOf(timeElapsed) });
		for (int i=0; i<arg0; i++) {
			if (i > 0) {
				AddAnotherFacilityPopUp("Yes");

			}
			VerifyScreenName(FacilityInfoPage.ProductInfoSrn);
			Reporter.info("Adding Facility "+(i+1));

			if (getElementAttribute(FacilityInfoPage.facPrimBorrName, "title").equalsIgnoreCase(prmBorrFullName1)) {
				Reporter.pass("Verifying Primary Borrower field value as "+ prmBorrFullName1);
			} else {
				Reporter.fail("Verifying Primary Borrower field value as "+ prmBorrFullName1 + "Actual " +getElementAttribute(FacilityInfoPage.facPrimBorrName, "title"));
//				Assert.fail("Verifying Primary Borrower field value");
			}
			String instType = multiValueMap.get("Institution Type").get(i);
			if (bankNumber.equalsIgnoreCase("011")) {
				if (getElementAttribute(FacilityInfoPage.facInstType, "title").equalsIgnoreCase(instType)) {
					Reporter.pass("Verifying Institution Type field value as "+ instType);
				} else {
					Reporter.fail("Verifying Institution Type field value as "+ instType);
				}
			}
			facilityPageMethods.EnterFacilityRequest(i);
			facilityPageMethods.EnterGuaranteeProgram(i);
			facilityPageMethods.EnterFacilityAmount(i);
			facilityPageMethods.EnterNAICSCodes(i);
			facilityPageMethods.EnterProductInformation(i);
			sleep(500);
			click(saveBtn);
			waitForInVisibile(PleaseWait);
			sleep(1000);
			facilityPageMethods.VerifyFacilityNumberCreated(i);
			sleep(500);
			click(continueBtn);
			start = System.currentTimeMillis();
			Reporter.info("Continuing to Approval Purpose screen for Facility "+(i+1));
			VerifyScreenName(approvalPurposeSrn);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Approval Purpose screen", String.valueOf(timeElapsed) });
			inputTextUsingJS(FacilityInfoPage.ApprovalPurposePurposeDD, multiValueMap.get("Approval Purpose").get(i));
			inputText(FacilityInfoPage.ApprovalPurposeDescription, "Automated Test");
			inputText(FacilityInfoPage.ApprovalPurposeAmount, multiValueMap.get("Decision Amount").get(i));
			sleep(1000);
			sleep(1000);
			click(continueBtn);
			waitForInVisibile(PleaseWait);
			Reporter.info("Clicking Continue button in Approval Purpose screen");

		}
		AddAnotherFacilityPopUp("No");
		waitForInVisibile(PleaseWait);

	}

	/**
	 * Author: Muzaffar A.
	 * Activity: Application
	 * Description: User adds given number of collaterals and continues to Collateral Analysis screen
	 * @param arg0
	 */
	@And("User adds <{int}> new Collaterals and continues to Collateral Analysis screen")
	public void userAddsNewCollateralsAndContinuesToCollateralAnalysisScreen(int arg0) {
		IsThisDealSecuredWithCollateralPopUp("Yes");
		if (ExistingCustomerFlag) {
			WouldYouLikeToAddExistingCollateral("No");
		}
		waitForInVisibile(PleaseWait);
		for (int i=0; i<arg0; i++) {
			AddNewCollateralPopUp("Yes");
			start = System.currentTimeMillis();
			Reporter.info("Adding Collateral " + (i + 1));
			VerifyScreenName(CollateralInfoPage.collateralScreen);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Collateral Info Screen", String.valueOf(timeElapsed) });
			collateralPageMethods.SelectCollatOwnerFirstEntity(i);
			collateralPageMethods.SelectFacilitsSecuredByThisCollat(i);
			collateralPageMethods.isThisPrimaryCollateral(i);
			collateralPageMethods.isThisRealEstateCollateral(i);
			collateralPageMethods.SelectCollatCategoryTypeDisplayDesc(i);
			collateralPageMethods.EnterCollateralLocation(i);
			collateralPageMethods.EnterRECollateralValue(i);
			if (multiValueMap.get("Is this Real Estate Collateral").get(i).equalsIgnoreCase("Yes")) {
				collateralPageMethods.RequestFloodReportRB(i);
				collateralPageMethods.EnterAcreageInfo(i);
				collateralPageMethods.EnterOwnershipRecordingInfo(i);
			} else {
				collateralPageMethods.EnterInsuranceUCCInfo(i);
			}
			if (bankNumber.equalsIgnoreCase("001") && dataMap.get("Deal Complexity").contains("Traditional")) {

				inputTextUsingJS(CollateralInfoPage.CollatOptType,multiValueMap.get("Optimist Product").get(i) );
				sleep(1000);
			}
			Reporter.addScreenshot("Collateral"+(i+1), "Collateral"+(i+1));
			sleep(500);
			click(continueBtn);

			Reporter.info("Clicking Continue button in Collateral "+(i+1));
			if (multiValueMap.get("Collateral Poolable").get(i).equalsIgnoreCase("Yes")) {
				start = System.currentTimeMillis();
				VerifyScreenName(CollateralInfoPage.CollateralPoolScrn);
				finish = System.currentTimeMillis();
				timeElapsed = (finish - start)/1000;
				PerfMetrics.add(new String[] { Activity, "Collateral Pool Screen", String.valueOf(timeElapsed) });
				collateralPageMethods.EnterCollateralPool(i);
				sleep(500);
				click(continueBtn);
			}

		}
		AddNewCollateralPopUp("No");

		for (int i=0; i<numOfLoans; i++) {
			start = System.currentTimeMillis();
			VerifyScreenName(CollateralInfoPage.CollateralAnalysisScrn);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Collateral Analysis Screen", String.valueOf(timeElapsed) });
			Reporter.info("Clicking Continue button in Collateral Analysis screen for Facility #"+(i+1));
			waitForInVisibile(PleaseWait);
			sleep(500);
			click(continueBtn);

		}

	}

	/**
	 * Author: Muzaffar A.
	 * Activity: Application
	 * Description: User continues to Stockholder screen in wizard and adds voter
	 * @param arg0 - voter name (identified by order number of Entities involved in deal)
	 */
	@And("User continues to Stockholder screen and adds voter - Entity <{int}>")
	public void userContinuesToStockholderScreenAndAddsVoterEntity(int arg0) {
		for (int i=0; i<numOfLoans; i++) {
			start = System.currentTimeMillis();
			waitForInVisibile(PleaseWait);
			if (BLStk) {
				VerifyScreenName(StockholderPage.StockholderScrnBorLevel);
			} else {
				VerifyScreenName(StockholderPage.StockholderScrnFacLevel);
			}
			SwitchToAngularFrame();
			waitForInVisibile(AngPleaseWait);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Stockholder screen", String.valueOf(timeElapsed) });
			waitForInVisibile(AngPleaseWait);
			String requiredAmt = getElementAttribute(StockholderPage.Required, "value");

			if (Double.parseDouble(requiredAmt.replace(",", ""))==1000) {
				Reporter.pass("Verifying Required Fee defaulted 1,000.00");
			} else {
				Reporter.fail("Verifying Required Fee defaulted 1,000.00");
				Reporter.addScreenshot("Required Fee defaulted", "Stock fee");
			}
			String expVoterNme = multiValueMap.get("Legal Name").get(arg0-1);
			if (BLStk && i>0) {

			}else {
				if (BLStk) {
					clickJS(StockholderPage.WhichProdForAssessment);
					clickAngularDDElement("Facility #"+ (i+1) + " - "+multiValueMap.get("Product Type").get(i));
					ObsStk = FacilityNumber.get(i)+ "_BORROWER LEVEL STOCK";
				}
				clickJS(StockholderPage.StockholderVoter);
				sleep(500);
				clickAngularDDElement(expVoterNme);
			}
			sleep(500);
			if (isWebElementVisible(AngSaveBtn)) {
				clickJS(AngSaveBtn);
				waitForInVisibile(AngPleaseWait);
			}

			sleep(500);
			this.driver().switchTo().defaultContent();
			SwitchToCMEFrame();
			sleep(500);
			Reporter.info("Adding Stock Voter for Facility #"+ (i+1));
			Reporter.addScreenshot("Stock added", "Stock");
			click(continueBtn);
		}
	}
	/**
	 * Author: Padmini B.
	 * Description:
	 * @throws ParseException
	 */
	@Then("User continues to Fees screen and verifies defaulted Fees for CFC")
	public void userContinuesToFeesScreenAndVerifiesDefaultedFeesForCFC() throws ParseException { // for CFC
		sleep(1000);
		click(continueBtn);
		sleep(1000);
		AddFeesPopUp("Yes");
		start = System.currentTimeMillis();
		VerifyScreenName(FeesPage.feesScreen);
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start)/1000;
		PerfMetrics.add(new String[] { Activity, "Fees screen", String.valueOf(timeElapsed) });
		feesPageMethods.verifyDefaultFeesforCFC(dataMap.get("Institution Type"),dataMap.get("Decision Amount"));

		sleep(1000);
//		feesPageMethods.VerifyStockFee();
	}
	/**
	 * Author: Muzaffar A.
	 * Description: Continues to Fees screen in the wizard and adds fees
	 */
	@Then("User continues to Fees screen and adds Fees")
	public void userContinuesToFeesScreenAndAddsFees() {
		int numOfLoans = multiValueMap.get("Facilities:").size();

		for (int i=0; i<numOfLoans; i++) {
			if (i==0) {
				AddFeesPopUp("Yes");
			}
			start = System.currentTimeMillis();
			Reporter.info("Adding Fees for Facility #"+ (i+1));
			VerifyScreenName(FeesPage.feesScreen);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Fees screen", String.valueOf(timeElapsed) });
			if (bankNumber.equalsIgnoreCase("001")) {
				feesPageMethods.AddFees(i);
				waitForInVisibile(PleaseWait);
				feesPageMethods.VerifyStockFee(i);
			}else
			{
				if (BLStk && i>0) {

				}else{
					feesPageMethods.VerifyStockFee(i);
				}
				feesPageMethods.AddFees(i);

			}
			sleep(1000);
			LoanID.add(i, getElementAttribute(FeesPage.feesLoanID, "title"));
			Reporter.addScreenshot("Fees", "Fees");
			click(continueBtn);
			waitForInVisibile(PleaseWait);
		}
	}
	/**
	 * Author: Muzaffar A.
	 * Description: Continues to DDT screen from previous screen, assigns ddt items to bank personnel, changes status and continues to next screen.
	 * @param arg0 - DDT item status change
	 */
	@And("User continues to DDT screen and assigns Status to {string}")
	public void userContinuesToDDTScreenAndAssignsStatusTo(String arg0) {
		sleep(1000);
		if (getElementText(DealStatus).contains("Application")) {
			GenerateAppRelatedDocs("No");
		}

		if(bankNumber.equalsIgnoreCase("001") && (getElementText(DealStatus).contains("Due Diligence"))){
			start = System.currentTimeMillis();
			VerifyScreenName(RelatedDocsPage.RelatedDocsScreen);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Related Documents Screen", String.valueOf(timeElapsed) });
			click(continueBtn);
			waitForInVisibile(PleaseWait);
		}
		start = System.currentTimeMillis();
		VerifyScreenName(DDTPage.DDTScreen);
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start)/1000;
		PerfMetrics.add(new String[] { Activity, "DDT Screen", String.valueOf(timeElapsed) });
		ddtPageMethods.AssignDDTItems(DDTOfficerName);
		ddtPageMethods.ChangeDDTItemStatus(arg0);

		Reporter.addScreenshot("DDT Screen", "DDT Screen");
	}
	/**
	 * Author: Muzaffar A.
	 * Description: Continues to related Docs screen from previous screen, verifies screen name and continues to next screen
	 * @param arg0 - expected screen
	 */
	@Then("User continues to {string} screen")
	public void userContinuesToScreen(String arg0) {
		sleep(1000);
		click(continueBtn);
		start = System.currentTimeMillis();
		switch (arg0){
			case "Related Documents": VerifyScreenName(RelatedDocsPage.RelatedDocsScreen);
				finish = System.currentTimeMillis();
				timeElapsed = (finish - start)/1000;
				PerfMetrics.add(new String[] { Activity, arg0+" Screen", String.valueOf(timeElapsed) });
				break;
			case "Approval Purpose"	: VerifyScreenName(approvalPurposeSrn);
				finish = System.currentTimeMillis();
				timeElapsed = (finish - start)/1000;
				PerfMetrics.add(new String[] { Activity, arg0+" Screen", String.valueOf(timeElapsed) });
				break;
		}
		Reporter.info("User clicks Continue button in "+ arg0+ " screen");
	}
	/**
	 *Author: Muzaffar A.
	 * Description: Continues to Route screen from previous screen. Routes the deal to next activity. Parameters are coming from feature file step
	 * @param arg0 - new Activity
	 * @param arg1 - user Name
	 */
	@And("User continues to Route screen and routes the deal to {string} to {string}")
	public void userContinuesToRouteScreenAndRoutesTheDealToTo(String arg0, String arg1) {

		sleep(1000);
		click(continueBtn);
		if  (bankNumber.equalsIgnoreCase("001") & (getElementText(DealStatus).contains("Due Diligence") )){
			ExercisePreApproval("No");
		}
		start = System.currentTimeMillis();
		VerifyScreenName(RoutePage.routeScreen);
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start)/1000;
		PerfMetrics.add(new String[] { Activity, "Route Screen", String.valueOf(timeElapsed) });
		routePageMethods.RouteDeal(arg0, arg1);
	}
	/**
	 * Author: Muzaffar A.
	 * Activity: Due Diligence
	 * Description: Navigates through the wizard from Application to Facility screen clicking No to pop ups after loading the application
	 */
	@Then("User loads the deal by Application Number and navigates to Facility screen")
	public void userLoadsTheDealByApplicationNumberAndNavigatesToFacilityScreen() {
		LoadDealByApplicationNumber(dealAls, "Due Diligence");

		if (bankNumber.equalsIgnoreCase("001") && dataMap.get("Deal Complexity").contains("Traditional")) {
			DoYouWantToGoToDealModelsPopUp("Yes");
			OptimistLogin();
			String portfolio = ProcessCenter;
			if (portfolio.equalsIgnoreCase("AgTexas Farm Credit Services")) {
				portfolio = "AgTexas Credit Services";
			}
			LaunchOptimistWithExistingModel(portfolio + " - Credit Analyst");
			click(continueBtn);
		}

		VerifyScreenName(DealInfoPage.ApplicationSrn);
		if (bankNumber.equalsIgnoreCase("001") && dataMap.get("Deal Complexity").contains("Traditional") ) {
			inputTextUsingJS(DealInfoPage.dealinfoAccrualStatus, "Accrual");
			inputTextUsingJS(DealInfoPage.dealinfoCommitteelvl, "03 Committee");
			inputTextUsingJS(DealInfoPage.dealinfoCreditAnalysislvl, "02 Level 2");
			inputTextUsingJS(DealInfoPage.dealinfoLUSType, "Cow Calf");

			AddApproversInDealTeam();
		}else if (bankNumber.equalsIgnoreCase("019")) {
			AddApproversInDealTeam();
		}
		sleep(1000);
		click(continueBtn);
		waitForInVisibile(PleaseWait);
		DownloadAnyRelatedDocsPopUp("No");
		start = System.currentTimeMillis();
		for (int i=0; i<numOfEnts; i++) {
			waitForInVisibile(PleaseWait);
			VerifyScreenName(EntityInfoPage.entInfoSrn);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Entity Info Screen", String.valueOf(timeElapsed) });
			if (multiValueMap.get("Relationship Type").get(i).equalsIgnoreCase("Guarantor")) {
				if (!VerifyRadioButtonChecked(EntityInfoPage.EditGuaranteedProdsYes)) {
					Reporter.fail("Edit Guaranteed Products RB not defaulted to Yes");
					sleep(1000);
					CheckRadioButton(EntityInfoPage.EditGuaranteedProdsYes);
				}
				int whichLoan = Integer.parseInt(multiValueMap.get("Which Products (Guarantor)").get(i));
				sleep(500);
				click(EntityInfoPage.WhichProducts);
				sleep(500);
				String xpath = "//div[@class='gridBoxCell' and contains(text(), '"+ FacilityNumber.get(whichLoan-1) +"')]";
				click(GetLocator(xpath));
				pressSpaceKey(EntityInfoPage.WhichProducts);
				sleep(500);
				pressTabKey(EntityInfoPage.WhichProducts);
				sleep(500);
				click(continueBtn);
				waitForInVisibile(PleaseWait);
				VerifyScreenName(EntityInfoPage.GuaranteedProductsScrn);
				String guarType = multiValueMap.get("Guarantee Type").get(i);
				inputTextUsingJS(EntityInfoPage.GuaranteeType, guarType);
				sleep(1000);
				if (guarType.equalsIgnoreCase("Individual Guar w/o Limit")) {
					if (!VerifyRadioButtonChecked(EntityInfoPage.UnlimitedGuarantyYes)) {
						Reporter.fail("Unlimited Guaranty RB defaulted as Yes");
					}
				} else if (guarType.equalsIgnoreCase("Individual Guarantee w/Lim")) {
					if (!VerifyRadioButtonChecked(EntityInfoPage.UnlimitedGuarantyNo)) {
						Reporter.fail("Unlimited Guaranty RB defaulted as No");
					}
					String guarPercent = multiValueMap.get("Guaranty %?").get(i);
					if (guarPercent.equalsIgnoreCase("Yes")) {
						CheckRadioButton(EntityInfoPage.GuarantyPercentYes);
						sleep(500);
						inputText(EntityInfoPage.GuaranteeLimitPct, multiValueMap.get("Guaranty Limit Pct").get(i));
						inputTextUsingJS(EntityInfoPage.GuaranteeBasisType, multiValueMap.get("Basis Type").get(i));
					}else if (guarPercent.equalsIgnoreCase("No")) {
						CheckRadioButton(EntityInfoPage.GuarantyPercentNo);
						inputText(EntityInfoPage.GuaranteeLimitAmnt, multiValueMap.get("Guaranty Limit Amt").get(i));
					}

				}
				Guarantors.add(whichLoan-1, multiValueMap.get("Legal Name").get(i));
				GuaranteeType.add(whichLoan-1, multiValueMap.get("Guarantee Type").get(i));
				GuarantorFlg.add(whichLoan-1, true);
				GuaranteePrgrmYN.add(whichLoan-1, "No");
				sleep(1000);
				click(saveBtn);
				waitForInVisibile(PleaseWait);
			}
			sleep(1000);
			waitForVisibleIgnoreStaleElement(continueBtn);
			clickJS(continueBtn);
			sleep(2000);
			if (!bankNumber.equalsIgnoreCase("001") && dataMap.get("Deal Complexity").toLowerCase().contains("agfast")) {
				//Do nothing
			}else{
				PullCreditReport("No");
			}
		}
		start = System.currentTimeMillis();

	}

	/**
	 * Author: Muzaffar A.
	 * Activity: Due Diligence
	 * Description: User Gets Rate, Calculates payment, adds Risk types and verifies YBS calculations for Facilities
	 */
	@And("User Gets Rate, Calculates payment, adds Risk types and verifies YBS calculations for Facilities")
	public void userGetsRateCalculatesPaymentAddsRiskTypesAndVerifiesYBSCalculationsForFacilities() {

		for (int i=0; i<numOfLoans; i++) {
			VerifyScreenName(FacilityInfoPage.FacilityInfoSrn);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Facility Info Screen", String.valueOf(timeElapsed) });
			facilityPageMethods.GetRateCalculatePayment(i);
			facilityPageMethods.VerifyAmortizationSchedule(i);
			sleep(500);
			click(continueBtn);
			waitForInVisibile(PleaseWait);
			VerifyScreenName(approvalPurposeSrn);
			sleep(500);
			click(continueBtn);
			start = System.currentTimeMillis();
			waitForInVisibile(PleaseWait);
			VerifyScreenName(BorrowerRiskTypesPage.BorrowerRiskTypesScrn);
			SwitchToAngularFrame();
			waitForInVisibile(AngPleaseWait);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Borrower Risk Types Screen", String.valueOf(timeElapsed) });
			borrowerRiskTypesMethods.AddRiskType(i);
			this.driver().switchTo().defaultContent();
			SwitchToCMEFrame();
			Reporter.addScreenshot("Borrower Risk Types", "Risk Types");
			Reporter.info("Continuing to YBS screen for Facility#"+(i+1));
			click(continueBtn);
			start = System.currentTimeMillis();
			waitForInVisibile(PleaseWait);
			VerifyScreenName(YBSReportingPage.YBSScrn);
			SwitchToAngularFrame();
			waitForInVisibile(AngPleaseWait);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "YBS Screen", String.valueOf(timeElapsed) });
			Reporter.addScreenshot("YBS Screen", "YBS Calculations");
			this.driver().switchTo().defaultContent();
			SwitchToCMEFrame();
			sleep(500);
			click(continueBtn);
		}
	}


	/**
	 * Author: Muzaffar A.
	 * Activity: Due Diligence
	 * Description: User continues to Deal Models screen saying No to all popups and passing Collateral Analysis, Debt Information screens.
	 * 				User launches Optimist and pulls PD/LGD ratings
	 */
	@And("User continues to Deal Models screen and performs Optimist steps")
	public void userContinuesToDealModelsScreenAndPerformsOptimistSteps() {
		ViewEditCollateral("Yes");
		for (int i=0; i<numOfCollaterals; i++) {
			start = System.currentTimeMillis();
			VerifyScreenName(CollateralInfoPage.collateralScreen);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Collateral Info Screen", String.valueOf(timeElapsed) });
			sleep(500);
			waitForInVisibile(PleaseWait);
			if (isWebElementVisible(CollateralInfoPage.NRELocationDescRequiredNo)) {
				sleep(500);
				click(CollateralInfoPage.NRELocationDescRequiredNo);
			}
			sleep(500);
			click(continueBtn);
			if (multiValueMap.get("Collateral Poolable").get(i).equalsIgnoreCase("Yes")) {
				start = System.currentTimeMillis();
				VerifyScreenName(CollateralInfoPage.CollateralPoolScrn);
				finish = System.currentTimeMillis();
				timeElapsed = (finish - start)/1000;
				PerfMetrics.add(new String[] { Activity, "Collateral Pool Screen", String.valueOf(timeElapsed) });
				sleep(500);
				click(continueBtn);
			}
		}
		AddCollateralToDeal("No");

		for (int j=0; j<numOfLoans;j++) {
			waitForInVisibile(PleaseWait);
			VerifyScreenName(CollateralInfoPage.CollateralAnalysisScrn);
			sleep(500);
			click(continueBtn);
			Reporter.info("Clicking Continue button in Collateral Analysis-Product# "+(j+1));
		}
		ReviewFees("No");
		start = System.currentTimeMillis();
		VerifyScreenName(DebtInformationPage.DebtInfoScrn);
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start)/1000;
		PerfMetrics.add(new String[] { Activity, "Debt Info screen", String.valueOf(timeElapsed) });
		sleep(500);
		click(continueBtn);
		OptimistLogin();
		String portfolio = ProcessCenter;
		if (portfolio.equalsIgnoreCase("AgTexas Farm Credit Services")) {
			portfolio = "AgTexas Credit Services";
		}
		LaunchOptimistWithExistingModel(portfolio + " - Credit Analyst");
		Reporter.addScreenshot("Optimist","DealModels");
		sleep(500);
		click(continueBtn);
	}

	@Then("User adds scored financial and continue to Liquid credit screen")
	public void user_Adds_Scored_Financial_And_Continue_To_LiquidCreditScreen() {
		VerifyScreenName(Liquidscorepage.scoredFinancial);
		LiquidCreditMethods.FillScoredFinancialscreen();
		//Click continue button
		click(continueBtn); //click continue button in Scored Financial
		waitForInVisibile(PleaseWait);
		VerifyScreenName(Liquidscorepage.liquidCreditScreen);
		click(continueBtn); //click continue to product info screen
		waitForInVisibile(PleaseWait);

	}

	@And("User navigates liquid credit screen and verify liquid credit scores")
	public void user_Verifies_LiquidCredit_ScreenScores() {

		String bank=dataMap.get("Bank");
		if (!bank.contentEquals("001")) {
			ResubmitRescoreAgFast("Yes");
			VerifyScreenName(Liquidscorepage.scoredFinancial);
			sleep(1000);
			click(continueBtn); //click continue to product info screen
			waitForInVisibile(PleaseWait);
		}else {
			LoadDealByApplicationNumber(dealAls, "Due Diligence");
		}
		VerifyScreenName(Liquidscorepage.liquidCreditScreen);
		//click obtain decision button
		sleep(500);
		click(obtainDecision);
		String popUpXpath = "//div[contains(text(),'There is no Business Entity to be the Main Borrowing Entity')]";
		if (isElementExist(GetLocator(popUpXpath), 10)) {
			sleep(500);
			click(yesBtn);
		}
		sleep(1000);
		for (int i=0; i<numOfEnts; i++) {
			if (isElementExist(creditRequested, 2)) {
				sleep(500);
				if (isWebElementVisible(yesBtn)) {
					click(yesBtn);
				}
			}
		}
		waitForInVisibile(PleaseWait);
		Reporter.addScreenshot("Liquid credit", "Liquid credit");
		if (isNumeric(Liquidscorepage.totalScore,"title")){
			Reporter.pass("Verifying total score populated with numerical value");
		} else {
			Reporter.fail("Failed to verify total score as numerical value");
		}
		Reporter.addScreenshot("Liquid credit", "Liquid credit");
		sleep(1000);
		click(continueBtn); //click continue to product info screen
		waitForInVisibile(PleaseWait);

	}


	/**
	 * Author: Muzaffar A.
	 * Activity: Due Diligence
	 * Description: User continues to Guideline Compliance screen to verify defaulted values in Due Diligence activity and clicks continue
	 */
	@Then("User continues to Guideline Compliance screen and verifies questionary")
	public void userContinuesToGuidelineComplianceScreenAndVerifiesQuestionary() {
		Reporter.info("Continuing to Compliance screen");
		start = System.currentTimeMillis();
		waitForInVisibile(PleaseWait);
		VerifyScreenName(GuidelineCompliancePage.GuidelineComplianceScrn);
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start)/1000;
		PerfMetrics.add(new String[] { Activity, "Guideline Compliance Screen", String.valueOf(timeElapsed) });
		waitForInVisibile(PleaseWait);
		sleep(1000);
//		guidelineCompliancePageMethods.VerifyGuidelineComplianceDefaultSelections(dataMap.get("Deal Complexity"));
		if (bankNumber.equalsIgnoreCase("011")) {
			if (complexity.toLowerCase().contains("agfast")) {
				if (!VerifyRadioButtonChecked(GuidelineCompliancePage.IsLAgFastScoreGreaterEqualTo155_Y)) {
					CheckRadioButton(GuidelineCompliancePage.IsLAgFastScoreGreaterEqualTo155_N);
					inputText(GuidelineCompliancePage.IsLAgFastScoreGreaterEqualTo155_Desc, "Exceptional sense of humor");
					sleep(500);
					inputTextUsingJS(GuidelineCompliancePage.IsLAgFastScoreGreaterEqualTo155_JustCode, "Exceptional Cash Flow");
					pressTabKey(GuidelineCompliancePage.IsLAgFastScoreGreaterEqualTo155_JustCode);
				}
			}
		} else if (bankNumber.equalsIgnoreCase("017")) {
			double LnAv = Double.parseDouble(LTVScore);
			LnAv = LnAv *100;
			if (LnAv <= 85) {
				CheckRadioButton(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_Yes);
				clearInput(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_Value);
				inputText(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_Value, String.valueOf(LnAv));
				Reporter.info("Is the Loan/AV Less Than or Equal To 85? - Selecting Yes and entering LTV value: "+ LnAv);

			}else {
				CheckRadioButton(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_No);
				clearInput(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_Value);
				inputText(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_Value, String.valueOf(LnAv));
				inputTextUsingJS(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_JustCode, "Exceptional Cash Flow");
				clearInput(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_Desc);
				inputText(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_Desc, "Automated Test");
				Reporter.info("Is the Loan/AV Less Than or Equal To 85? - Selecting No and entering LTV value: "+ LnAv);
			}
		}else if(bankNumber.equalsIgnoreCase("001")) {
			waitForInVisibile(PleaseWait);
			if (isWebElementVisible(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_NA)) {
				double LnAv = Double.parseDouble(LTVScore);
				if (LnAv <= 0.85) {
					CheckRadioButton(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_Yes);
					clearInput(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_Value);
					inputText(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_Value, String.valueOf(LnAv));
					Reporter.info("Is the Loan/AV Less Than or Equal To 85? - Selecting Yes and entering LTV value: "+ LnAv);

				}else {
					CheckRadioButton(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_No);
					clearInput(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_Value);
					inputText(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_Value, String.valueOf(LnAv));
					inputTextUsingJS(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_JustCode, "Exceptional Cash Flow");
					clearInput(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_Desc);
					inputText(GuidelineCompliancePage.IsLoanAvLessThanOrEqualTo085_Desc, "Exceptional Sense of Humor");
					Reporter.info("Is the Loan/AV Less Than or Equal To 85? - Selecting No and entering LTV value: "+ LnAv);
				}
			}
			if (isWebElementVisible(GuidelineCompliancePage.IsLoanTerms5to30_NA)) {
				int lnTrms = Integer.parseInt(multiValueMap.get("Loan Term Months").get(0));
				lnTrms = Math.abs(lnTrms/12);
				sleep(500);
				if ((lnTrms >=5) && (lnTrms <=30)) {
					CheckRadioButton(GuidelineCompliancePage.IsLoanTerms5to30_Y);
					inputText(GuidelineCompliancePage.IsLoanTerms5to30_Value, String.valueOf(lnTrms));
				} else {
					CheckRadioButton(GuidelineCompliancePage.IsLoanTerms5to30_N);
					inputText(GuidelineCompliancePage.IsLoanTerms5to30_Desc, String.valueOf(lnTrms));
					sleep(500);
					inputTextUsingJS(GuidelineCompliancePage.IsLoanTerms5to30_JustCode, "Positive Prior Repayment");
					pressTabKey(GuidelineCompliancePage.IsLoanTerms5to30_JustCode);
				}
			}
		}

		Reporter.addScreenshot("Guideline Compliance screen", "Guideline Compliance screen ");
		sleep(500);
		clickJS(continueBtn);
	}

	/**
	 * Author: Muzaffar A
	 * Activity: Due Diligence
	 * Description: User continues to Covenants screen to add covenants
	 */
	@Then("User continues to Covenants screen to add covenants")
	public void userContinuesToCovenantsScreenToAddCovenants() {
		if (!ExistingCustomerFlag) {
			CreateUpdateCovenants("Yes");
			start = System.currentTimeMillis();
			waitForClickable(CovenantsPage.covenantScreen);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Covenants screen", String.valueOf(timeElapsed) });
			for (int i=0; i<numOfEnts; i++) {
				waitForInVisibile(PleaseWait);
				VerifyScreenName(CovenantsPage.covenantScreen);
				covenantPageMethods.AddCovenants(i);
				Reporter.addScreenshot("Covenants screen", "Added Covenants");
				sleep(500);
				click(continueBtn);
			}
		}else{
			CreateUpdateCovenants("No");
		}
	}
	/**
	 * Author: Muzaffar A
	 * Activity: Due Diligence
	 * Description: User continues and prints Draft CDA
	 */
	@Then("User continues to print Draft CDA")
	public void userContinuesToPrintDraftCDA() {
		if(!bankNumber.equalsIgnoreCase("001")) {
			waitForInVisibile(PleaseWait);
			VerifyScreenName(RelatedDocsPage.RelatedDocsScreen);
			sleep(500);
			click(continueBtn);
			PrintDraftCDA("Yes");
			waitForInVisibile(PleaseWait);
		}
		else{
			VerifyScreenName(DebtInformationPage.DebtInfoScrn);
			sleep(500);
			click(continueBtn);
			PrintDraftCDACFC("Yes");
			waitForInVisibile(PleaseWait);
		}
		VerifyScreenName(SelectMultipleSectionsScrn);
		clickGridBoxCellElement("Credit Desk Analysis");
		sleep(500);
		click(PrintBtn);
		start = System.currentTimeMillis();
		waitForInVisibile(PleaseWait);
		waitForInVisibile(WaitIcon);
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start)/1000;
		PerfMetrics.add(new String[] { Activity, "Draft CDA", String.valueOf(timeElapsed) });
		//sleep(5000);
		SwitchWindow(1);
		VerifyPDF();
		this.driver().close();
		SwitchWindow(0);
		SwitchToCMEFrame();
		sleep(500);
		click(continueBtn);
	}

	/**
	 * Author: Muzaffar A
	 * Activity: Credit Decision
	 * Description: User loads the deal in Credit Decision and continues to Authorization screen
	 */

	@When("User loads the deal in Credit Decision and continues to Authorization screen")
	public void userLoadsTheDealInCreditDecisionAndContinuesToAuthorizationScreen() {
		LoadDealByApplicationNumber(dealAls, "Credit Decision");
		VerifyScreenName(DealInfoPage.ApplicationSrn);
		RelPD = getElementAttribute(DealInfoPage.dealinfoRelPD, "title");
		sleep(1000);
		click(continueBtn);
		waitForInVisibile(PleaseWait);
		VerifyScreenName(RelatedDocsPage.RelatedDocsScreen);
		sleep(1000);
		click(continueBtn);
		waitForInVisibile(PleaseWait);
		VerifyScreenName(DebtInformationPage.DebtInfoScrn);
		sleep(1000);
		click(continueBtn);
		sleep(1000);
		start = System.currentTimeMillis();
		waitForInVisibile(PleaseWait);
		VerifyScreenName(GuidelineCompliancePage.GuidelineComplianceScrn);
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start)/1000;
		PerfMetrics.add(new String[] { Activity, "Guideline Compliance Screen", String.valueOf(timeElapsed) });
		waitForInVisibile(PleaseWait);
		click(continueBtn);
		if (bankNumber.equalsIgnoreCase("001") && (dataMap.get("Deal Complexity").toLowerCase().contains("agfast"))) {
//			No Optimist Login required for CFC Agfast deal only
		}else{
			if (isElementExist(OptimistLoginWndw, 10)) {
				OptimistLogin();
			}
		}
		start = System.currentTimeMillis();
		VerifyScreenName(AuthorizationPage.AuthorizationScrn);
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start)/1000;
		PerfMetrics.add(new String[] { Activity, "Authorization Screen", String.valueOf(timeElapsed) });
	}

	@Then("User approves the deal and completes CDA")
	public void userApprovesTheDealAndCompletesCDA() {
		authorizationPageMethods.EnterProductDecision();
		authorizationPageMethods.EnterDealDecision();
		sleep(500);
		click(saveBtn);
		waitForInVisibile(PleaseWait);
		sleep(1000);
		Reporter.addScreenshot("Authorization", "Authorization");
		authorizationPageMethods.CDAComplete();
	}

	@Then("User proceeds to approve the deal with a given Approval level")
	public void userProceedsToApproveTheDealWithAGivenApprovalLevel() {
		String dealDecision		=dataMap.get("Deal Decision");
		String ExceptionType	=dataMap.get("Exception Type");
		String ExceptionMsg		=dataMap.get("Expected Message");
		String ExceptionXpath	="//div[@class='label' and contains(text(), '" + ExceptionMsg + "') ]";
		authorizationPageMethods.EnterProductDecision();
		authorizationPageMethods.EnterDealDecision();
		sleep(500);
		click(saveBtn);
		waitForInVisibile(PleaseWait);
		sleep(1000);
		Reporter.addScreenshot("Authorization", "Authorization");
		Reporter.info("Clicking CDA Complete");
		sleep(500);
		waitForInVisibile(PleaseWait);
		click(AuthorizationPage.CDACompleteBtn);
		sleep(1000);
		if (!dealDecision.equalsIgnoreCase("Approved")) {
			if (ExceptionType.equalsIgnoreCase("Operator Alert")) {
				waitForVisible(OperatorAlert);
				if (isWebElementVisible(GetLocator(ExceptionXpath))){
					Reporter.pass("Operator Alert Message");
				}else{
					Reporter.fail("Operator Alert Message");
					Assert.fail("Operator Alert Message");
				}
				Reporter.addScreenshot("Operator Alert Message", "Operator Alert Message");
			}
		} else {
			waitForInVisibile(PleaseWait);
			waitForInVisibile(PleaseWait);
			SwitchWindow(1);
			VerifyPDF();
			this.driver().close();
			SwitchWindow(0);
			SwitchToCMEFrame();
		}
	}

	/**
	 * Author		:Muzaffar A
	 * Activity		:Credit Approved
	 * Description	:User loads the deal in Credit Approved and Verifies Pre-Closing CSi Documents
	 * 				 Continues to Rel Docs to verify CSi docs package pushed
	 */
	@Then("User loads the deal in Credit Approved and Verifies Pre-Closing CSi Documents")
	public void userLoadsTheDealInCreditApprovedAndVerifiesPreClosingCSiDocuments() {
		LoadDealByApplicationNumber(dealAls, "Credit Approved");
		VerifyScreenName(DocumentsPage.DocumentsScrn);
		waitForInVisibile(PleaseWait);
		documentsPageMethods.VerifyCsiDocuments();
		Reporter.info("Continuing to Related Docs screen");
		sleep(500);
		click(continueBtn);
		waitForInVisibile(PleaseWait);
		VerifyScreenName(RelatedDocsPage.RelatedDocsScreen);
		try {
			isWebElementVisible(FinalCDA);
			isWebElementVisible(DocPkg1);
			Reporter.pass("Verifying Final CDA and Pre-CLosing CSi docs package available in Related Docs grid");
		} catch (Exception e) {
			Reporter.fail("Verifying Final CDA and Pre-CLosing CSi docs package available in Related Docs grid "+ e.getMessage());
		}
		Reporter.addScreenshot("Rel Docs", "Rel Docs");
	}

	/**
	 * Author		:Muzaffar A.
	 * Activity		:Credit Approved
	 * Description	:In Related Docs screen continues through screens and adds Title company
	 */
	@And("User continues to add Title Company related entity")
	public void userContinuesToAddTitleCompanyRelatedEntity() {
		Reporter.info("Continuing through Credit Approved Activity screens to add Title company");
		sleep(500);
		click(continueBtn);
		waitForInVisibile(PleaseWait);

		for (int i=0; i<numOfLoans; i++) {
			VerifyScreenName(FacilityInfoPage.FacilityInfoSrn);
			sleep(500);
			click(continueBtn);
			waitForInVisibile(PleaseWait);
			VerifyScreenName(YBSReportingPage.YBSScrn);
			SwitchToAngularFrame();
			sleep(500);
			waitForInVisibile(AngPleaseWait);
			ybsReportingMethods.GetYbsValues(i);
			this.driver().switchTo().defaultContent();
			SwitchToCMEFrame();
			sleep(1000);
			click(continueBtn);
		}
		if (bankNumber.equalsIgnoreCase("001")) {
			ViewEditCollateral("Yes");
			for (int i=0; i<numOfCollaterals; i++) {
				VerifyScreenName(CollateralInfoPage.collateralScreen);
				if (multiValueMap.get("Is this Real Estate Collateral").get(i).equalsIgnoreCase("Yes")) {
					inputText(CollateralInfoPage.collatTotalDeedAcres, "340");
				}
				click(continueBtn);
			}
			sleep(500);
		}else {
			ViewEditCollateral("No");
		}
		AddRelatedEntitiesPopUp("Yes");
		start = System.currentTimeMillis();
		waitForClickable(RelatedEntitiesPage.AddRelatedEntityBtn);
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start)/1000;
		PerfMetrics.add(new String[] { Activity, "Related Entity Screen", String.valueOf(timeElapsed) });
		sleep(500);
		click(RelatedEntitiesPage.AddRelatedEntityBtn);
		waitForInVisibile(PleaseWait);
		VerifyScreenName(CustomerSearchPage.custSearchSrn);
		customerSearchPageMethods.SearchNewCustomer();
		VerifyScreenName(RelatedEntitiesPage.RelatedEntitiesScrn);
		relatedEntityInfoPageMethods.AddTitleCompany();
		sleep(500);
		click(continueBtn);
		waitForInVisibile(PleaseWait);

	}

	/**
	 * Author		:Muzaffar A
	 * Activity		:Pre-Closing
	 * Description	:User loads the deal in Pre-Closing and navigates to Disbursements screen based on number of loans in the deal
	 * 				 Adds disbursements, continues through screens til Closing Documents screen
	 */
	@And("User loads the deal in Pre-Closing and adds new disbursement")
	public void userLoadsTheDealInPreClosingAndAddsNewDisbursement() {
		LoadDealByApplicationNumber(dealAls, "Pre-Closing");

		VerifyScreenName(DealInfoPage.ApplicationSrn);
		sleep(1000);
		click(continueBtn);
		waitForInVisibile(PleaseWait);
		VerifyScreenName(RelatedDocsPage.RelatedDocsScreen);
		sleep(1000);
		click(continueBtn);
		for (int i=0; i<numOfEnts; i++) {
			waitForInVisibile(PleaseWait);
			VerifyScreenName(EntityInfoPage.entInfoSrn);
			waitForVisible(continueBtn);
			sleep(1000);
			clickJS(continueBtn);
		}
		AddRelatedEntitiesPopUp("No");
		ViewEditCollateral("No");
		waitForInVisibile(PleaseWait);
		for (int i=0; i<numOfLoans; i++) {
			VerifyScreenName(FacilityInfoPage.FacilityInfoSrn);
			sleep(500);
			click(continueBtn);
			waitForInVisibile(PleaseWait);
			VerifyScreenName(FeesPage.feesScreen);
			sleep(500);
			click(continueBtn);
			disbursementsPageMethods.AddNewDisbursement(i);
			try {
				waitForInVisibile(PleaseWait);
				sleep(1000);
				waitForInVisibile(PleaseWait);
				List <WebElement> Outstandings = this.driver().findElements(By.xpath("//span[contains(text(), '-Loan (Outstanding)')]"));
				if (Outstandings.get(i).isDisplayed()) {
					Reporter.pass(Outstandings.get(i).getText()+ " Added in deal tree");
				} else {
					Reporter.fail("Outstanding Added in deal tree for Facility# "+(i+1));
				}
			} catch (Exception e) {
				Reporter.fail("Outstanding Added in deal tree for Facility# "+(i+1)+ "\n"+ e.getMessage());
				Assert.fail(e.getMessage());
			}
			sleep(500);
			click(continueBtn);
			start = System.currentTimeMillis();
			VerifyScreenName(ClosingInstructionsSrn);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Closing Instructions Screen", String.valueOf(timeElapsed) });
			sleep(500);
			Locator ClosInstrYes = null;
			if (!bankNumber.equalsIgnoreCase("011")) {
				ClosInstrYes = GetLocator("//a[@fieldname='configField56480_Y']");
			}else{
				ClosInstrYes = GetLocator("//a[@fieldname='configField53116_Y']");
			}

			if (isWebElementVisible(ClosInstrYes)) {
				CheckRadioButton(ClosInstrYes);
			}
			Reporter.info("Checking Yes RB for Loan Closing Instructions");
			sleep(500);
			click(continueBtn);
			start = System.currentTimeMillis();
			VerifyScreenName(LetterToTitleCompanySrn);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Letter To Title Company Screen", String.valueOf(timeElapsed) });
			sleep(500);
			if (!VerifyRadioButtonChecked(LetterToTitleCompanyYes)) {
				sleep(500);
				CheckRadioButton(LetterToTitleCompanyYes);
				Reporter.info("Checking Yes RB for Loan Letter to Title Company");
			}
			sleep(500);
			click(continueBtn);
			if (multiValueMap.get("Secured by Real Estate").get(i).equalsIgnoreCase("Yes")) {
				WouldYouLikeToAddAdditionalTerms("No");
			}

		}


	}
	@And("User continues to Documents screen and verifies Closing CSi Documents")
	public void userContinuesToDocumentsScreenAndVerifiesClosingCSiDocuments() {
		Reporter.info("User continues to Documents screen and verifies Closing CSi Document");
		start = System.currentTimeMillis();
		ClickElementOnPleaseWait(DialogOKbtn);
		VerifyScreenName(DocumentsPage.DocumentsScrn);
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start)/1000;
		PerfMetrics.add(new String[] { Activity, "Documents screen", String.valueOf(timeElapsed) });
		if (isWebElementVisible(OKBtn)) {
			sleep(500);
			click(OKBtn);
		}
		documentsPageMethods.VerifyCsiDocuments();
		Reporter.info("Continuing to Related Docs screen");
		sleep(500);
		click(continueBtn);
		start = System.currentTimeMillis();
		VerifyScreenName(RelatedDocsPage.RelatedDocsScreen);
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start)/1000;
		PerfMetrics.add(new String[] { Activity, "Related Documents screen", String.valueOf(timeElapsed) });
		try {
			isWebElementVisible(DocPkg2);
			Reporter.pass("Verifying Final CDA and Closing CSi docs package available in Related Docs grid");
		} catch (Exception e) {
			Reporter.fail("Verifying Final CDA and Closing CSi docs package available in Related Docs grid "+ e.getMessage());
		}
		Reporter.addScreenshot("Rel Docs", "Rel Docs");
		if (!bankNumber.equalsIgnoreCase("001")) {
			sleep(500);
			click(continueBtn);
			waitForInVisibile(PleaseWait);
		}

	}

	/**
	 * Author		:Muzaffar A
	 * Activity		:CLosing Doc execution
	 * Description	:Loads deal in CLosing Doc execution and uploads docs to ECM
	 */
	@And("User loads the deal in Closing Doc Execution and Upload, Archive ECM Documents")
	public void userLoadsTheDealInClosingDocExecutionAndUploadArchiveECMDocuments() {
		LoadDealByApplicationNumber(dealAls, "Closing Doc Execution");
		VerifyScreenName(RelatedDocsPage.RelatedDocsScreen);
		Reporter.info("Uploading 3 types of Documents to ECM");
		waitForInVisibile(PleaseWait);
		int ECMDocsNum = multiValueMap.get("File Path").size();
		for (int i=0; i<ECMDocsNum; i++) {
			relatedDocsMethods.AddDocumentECM(i);
		}
		Reporter.addScreenshot("Related Documents Uploaded", "Related Documents");
		sleep(500);
		click(continueBtn);
		waitForInVisibile(PleaseWait);
	}

	/**
	 * Author: Muzaffar A.
	 * Description: User loads the deal in Closing, continues to OBS page and books the deal in OBS
	 */
	@And("User loads the deal in Closing and books the deal via {string}")
	public void userLoadsTheDealInClosingAndBooksTheDealViaOBS(String arg0) {
		LoadDealByApplicationNumber(dealAls, "Closing Fund/Book");
		if (!bankNumber.equalsIgnoreCase("001")) {
			VerifyScreenName(RelatedDocsPage.RelatedDocsScreen);
			Reporter.info("Continuing to "+ arg0 +" Booking");
			click(continueBtn);
			waitForInVisibile(PleaseWait);
		}
		for (int i=0; i<numOfLoans; i++) {
			VerifyScreenName(FacilityInfoPage.FacilityInfoSrn);
			LgdRating.add(i, getElementAttribute(FacilityInfoPage.facLGDRating, "title"));
			click(continueBtn);
			start = System.currentTimeMillis();
			VerifyScreenName(FacilityInfoPage.OutstandingSrn);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Outstanding screen", String.valueOf(timeElapsed) });
			sleep(1000);
			Reporter.addScreenshot("Outstanding screen", "Outstanding screen");
			BookRPSFlg.add(i, true);
			if (!multiValueMap.get("Amount").get(i).equalsIgnoreCase("100%")) {
				String prodType = multiValueMap.get("Product Type").get(i);
				if (!(prodType.equalsIgnoreCase("Operating Master") || prodType.equalsIgnoreCase("Revolver"))) {
					sleep(1000);
					clickJS(FacilityInfoPage.facCalcPmtBtn);
					waitForInVisibile(PleaseWait);
					if (isWebElementVisible(FacilityInfoPage.facSuccessCalulationMsg)) {
						Reporter.pass("Successful Calculation");
						sleep(1000);
						click(OKBtn);
						waitForInVisibile(PleaseWait);
					} else {
						Reporter.fail("Successful Calculation");
						Assert.fail("Successful Calculation");
					}
					facilityPageMethods.VerifyAmortizationSchedule(i);

				}else{
					BookRPSFlg.add(i, false);
				}
			}
			sleep(500);
			click(continueBtn);
		}
		if (!arg0.equalsIgnoreCase("FBL")) {
			AreYouReadyToCreateBooking("Yes");
			start = System.currentTimeMillis();
			InitializeBankRelatedData();
			waitForVisible(LoadCircleGIF);
			waitForVisible(OBSPage.OBSScrn, 180);
			VerifyScreenName(OBSPage.OBSScrn);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "OBS Package Creation", String.valueOf(timeElapsed) });
			SwitchToOBSFrame();
			obsPageMethods.SignInToOBS(UserAccount, UserAccountPwd);
			obsPageMethods.VerifyNoRedErrorInOBSCards();
			obsPageMethods.VerifyCustomerCard();
			obsPageMethods.VerifyDealCard();
			obsPageMethods.VerifyFacilityCard();
			obsPageMethods.VerifyAccountsCard();
			obsPageMethods.BookAndVerifyOBS();
			this.driver().switchTo().defaultContent();
			SwitchToCMEFrame();
			GetOBSLog();
			sleep(1000);
			click(continueBtn);
		}else{
			AreYouReadyToCreateBooking("No");
			waitForInVisibile(PleaseWait);
			VerifyScreenName(DDTPage.DDTScreen);
			entityBoardingPageMethods.FBLEntityBoarding();
			dealBookingPageMethods.FBLDealBooking();
			this.driver().switchTo().defaultContent();
			SwitchToCMEFrame();
			sleep(1000);
			Activity="Closing Fund/Book";
			ClickMenuItem(Deal, DealManager);
			waitForInVisibile(PleaseWait);
		}


	}

	@Then("User continues to print Draft CDA CFC Trad")
	public void userContinuesToPrintDraftCDACFCTrad() {
		PrintDraftCDACFCTrad("Yes");
		waitForInVisibile(PleaseWait);
	}

	@Then("CFC User verifies Application Screen and navigates to Facility screen")
	public void userLoadsTheAgFastDealByApplicationNumberAndNavigatesToFacilityScreen() {
		String dealComplexity = dataMap.get("Deal Complexity");
		if (!dealComplexity.equalsIgnoreCase("Traditional")) {
			VerifyScreenName(DealInfoPage.ApplicationSrn);
			sleep(1000);
			inputTextUsingJS(DealInfoPage.dealinfoAccrualStatus, "Accrual");
			inputTextUsingJS(DealInfoPage.dealinfoCommitteelvl, "03 Committee");
			sleep(1000);
			click(continueBtn);

			for (int i=0; i<numOfEnts; i++) {
				waitForInVisibile(PleaseWait);
				VerifyScreenName(EntityInfoPage.entInfoSrn);

				if (multiValueMap.get("Relationship Type").get(i).equalsIgnoreCase("Guarantor")) {
					if (!VerifyRadioButtonChecked(EntityInfoPage.EditGuaranteedProdsYes)) {
						Reporter.fail("Edit Guaranteed Products RB not defaulted to Yes");
						sleep(1000);
						CheckRadioButton(EntityInfoPage.EditGuaranteedProdsYes);
					}
					int whichLoan = Integer.parseInt(multiValueMap.get("Which Products (Guarantor)").get(i));
					sleep(500);
					click(EntityInfoPage.WhichProducts);
					sleep(500);
					String xpath = "//div[@class='gridBoxCell' and contains(text(), '"+ FacilityNumber.get(whichLoan-1) +"')]";
					click(GetLocator(xpath));
					pressSpaceKey(EntityInfoPage.WhichProducts);
					sleep(500);
					pressTabKey(EntityInfoPage.WhichProducts);
					sleep(500);
					click(continueBtn);
					waitForInVisibile(PleaseWait);
					VerifyScreenName(EntityInfoPage.GuaranteedProductsScrn);
					String guarType = multiValueMap.get("Guarantee Type").get(i);
					inputTextUsingJS(EntityInfoPage.GuaranteeType, guarType);
					sleep(1000);
					if (guarType.equalsIgnoreCase("Individual Guar w/o Limit")) {
						if (!VerifyRadioButtonChecked(EntityInfoPage.UnlimitedGuarantyYes)) {
							Reporter.fail("Unlimited Guaranty RB defaulted as Yes");
						}
					} else if (guarType.equalsIgnoreCase("Individual Guarantee w/Lim")) {
						if (!VerifyRadioButtonChecked(EntityInfoPage.UnlimitedGuarantyNo)) {
							Reporter.fail("Unlimited Guaranty RB defaulted as No");
						}
						String guarPercent = multiValueMap.get("Guaranty %?").get(i);
						if (guarPercent.equalsIgnoreCase("Yes")) {
							CheckRadioButton(EntityInfoPage.GuarantyPercentYes);
							sleep(500);
							inputText(EntityInfoPage.GuaranteeLimitPct, multiValueMap.get("Guaranty Limit Pct").get(i));
							inputTextUsingJS(EntityInfoPage.GuaranteeBasisType, multiValueMap.get("Basis Type").get(i));
						}else if (guarPercent.equalsIgnoreCase("No")) {
							CheckRadioButton(EntityInfoPage.GuarantyPercentNo);
							inputText(EntityInfoPage.GuaranteeLimitAmnt, multiValueMap.get("Guaranty Limit Amt").get(i));
						}

					}
					sleep(1000);
					click(saveBtn);
					waitForInVisibile(PleaseWait);
				}
				sleep(1000);
				waitForVisibleIgnoreStaleElement(continueBtn);
				clickJS(continueBtn);
				sleep(2000);

			}

			ModifyAgFast("No");
			waitForInVisibile(PleaseWait);
			ViewEditCollateral("No");
			waitForInVisibile(PleaseWait);
			AddCollateralToDeal("No");

			for (int i=0; i<numOfLoans; i++) {
				waitForInVisibile(PleaseWait);
				VerifyScreenName(CollateralInfoPage.CollateralAnalysisScrn);
				sleep(1000);
				click(continueBtn);

			}

		}
	}

	@And("In Facility screen user Gets Rate and Calculates payment")
	public void inFacilityScreenUserGetsRateAndCalculatesPayment() {
		String dealComplexity = dataMap.get("Deal Complexity");

		VerifyScreenName(FacilityInfoPage.FacilityInfoSrn);

		if ( bankNumber.equalsIgnoreCase("001")) {

			for (int i=0; i<numOfLoans; i++) {

				facilityPageMethods.GetRatesfromRateQuoteSystem(i);
				waitForInVisibile(PleaseWait);
				this.driver().switchTo().defaultContent();
				SwitchToCMEFrame();
				sleep(10000); //Giving some time for rate to be approved and for email sent
//				CheckRadioButton(FacilityInfoPage.facSecRealEstY);
				facilityPageMethods.GetRateCalculatePayment(i);
				facilityPageMethods.VerifyAmortizationSchedule(i);
				sleep(500);
				click(continueBtn);
				waitForInVisibile(PleaseWait);
				VerifyScreenName(BorrowerRiskTypesPage.BorrowerRiskTypesScrn);
				waitForInVisibile(AngPleaseWait);
				SwitchToAngularFrame();
				waitForInVisibile(AngPleaseWait);
				borrowerRiskTypesMethods.AddRiskType(i);
				this.driver().switchTo().defaultContent();
				SwitchToCMEFrame();
				Reporter.addScreenshot("Borrower Risk Types", "Risk Types");
				if (dealComplexity.equalsIgnoreCase("Traditional"))
				{
					Reporter.info("Continuing to YBS screen for Facility#" + (i + 1));
					click(continueBtn);
					waitForInVisibile(PleaseWait);
					VerifyScreenName(YBSReportingPage.YBSScrn);
					SwitchToAngularFrame();
					waitForInVisibile(AngPleaseWait);
					ybsReportingMethods.GetYbsValues(i);
					Reporter.addScreenshot("YBS Screen", "YBS Calculations");
					this.driver().switchTo().defaultContent();
					SwitchToCMEFrame();
				}
				sleep(1000);
				click(continueBtn);
				if(bankNumber.equalsIgnoreCase("001") && (complexity.toLowerCase().contains("agfast")))
				{
					feesPageMethods.AddReviewFeesPopUp("No");
					VerifyScreenName(approvalPurposeSrn);
					sleep(500);
					click(continueBtn);
					waitForInVisibile(PleaseWait);
				}

			}
		}

	}

	@When("User loads the deal in Pre Decision Review and continues to DDT screen")
	public void userLoadsTheDealInPreDecisionReviewAndContinuesToDDTScreen() {
		LoadDealByApplicationNumber(dealAls, "Pre-Decision Review");
		VerifyScreenName(DealInfoPage.ApplicationSrn);
		click(continueBtn);
		waitForInVisibile(PleaseWait);
		for (int i=0; i<numOfEnts; i++) {
			waitForInVisibile(PleaseWait);
			VerifyScreenName(EntityInfoPage.entInfoSrn);
			click(continueBtn);
			waitForInVisibile(PleaseWait);
			VerifyScreenName(EntityInfoPage.entaddInfoSrn);
			click(continueBtn);
		}
		waitForInVisibile(PleaseWait);
		for (int j=0; j<numOfLoans; j++) {
			VerifyScreenName(FacilityInfoPage.FacilityInfoSrn);
			click(continueBtn);
			waitForInVisibile(PleaseWait);
			VerifyScreenName(approvalPurposeSrn);
			sleep(1000);
			click(continueBtn);
			waitForInVisibile(PleaseWait);
			VerifyScreenName(StockholderPage.StockholderScrnFacLevel);
			SwitchToAngularFrame();
			sleep(1000);
			waitForInVisibile(AngPleaseWait);
			this.driver().switchTo().defaultContent();
			SwitchToCMEFrame();
			sleep(1000);
			click(continueBtn);
			waitForInVisibile(PleaseWait);
			VerifyScreenName(FeesPage.feesScreen);
			sleep(1000);
			click(continueBtn);
			waitForInVisibile(PleaseWait);
		}

		sleep(1000);
		ViewEditCollateral("Yes");
		waitForInVisibile(PleaseWait);

		for (int k=0; k<numOfCollaterals; k++) {

//			if (!multiValueMap.get("Is this Real Estate Collateral").get(k).equalsIgnoreCase("No")) {
//				waitForInVisibile(PleaseWait);
//				CheckRadioButton(CollateralInfoPage.RentedYes);
//				CheckRadioButton(CollateralInfoPage.OwnerOccupiedNo);
//				CheckRadioButton(CollateralInfoPage.ImprovementsLandNo);
//				CheckRadioButton(CollateralInfoPage.PurchasePropertyYes);
//				CheckRadioButton(CollateralInfoPage.HomesteadNo);
//
//			}
			sleep(1000);
			click(continueBtn);
		}
		for (int i=0; i<numOfEnts; i++) {
			waitForInVisibile(PleaseWait);
			VerifyScreenName(CovenantsPage.covenantScreen);
			sleep(1000);
			click(continueBtn);
		}
		waitForInVisibile(PleaseWait);
		VerifyScreenName(DDTPage.DDTScreen);
		waitForInVisibile(PleaseWait);
	}

	@And("CFC User loads the deal in Pre-Closing and navigates till Document screen")
	public void userLoadsTheDealInPreClosingAndverifiesDocumentScreen() {
		LoadDealByApplicationNumber(dealAls, "Pre-Closing");
		sleep(1000);
		VerifyScreenName(RelatedDocsPage.RelatedDocsScreen);
		click(continueBtn);
		for (int i = 0; i < numOfEnts; i++) {
			waitForInVisibile(PleaseWait);
			VerifyScreenName(EntityInfoPage.entInfoSrn);
			sleep(1000);
			click(continueBtn);
		}
		sleep(2000);
		AddAdjustPopup("No");
		ClickElementOnPleaseWait(DialogOKbtn);
		VerifyScreenName(DocumentsPage.DocumentsScrn);
		click(continueBtn);
		ViewEditCollateral("No");
		waitForInVisibile(PleaseWait);
		for (int i = 0; i < numOfLoans; i++) {
			VerifyScreenName(FacilityInfoPage.FacilityInfoSrn);
			sleep(1000);
			click(continueBtn);
			ClickElementOnPleaseWait(DialogOKbtn);
			VerifyScreenName(DocumentsPage.DocumentsScrn);
			click(continueBtn);
			boolean securedByREFlg = false;
			for (int j=0; j<numOfLoans; j++) {
				if (multiValueMap.get("Secured by Real Estate").get(j).equalsIgnoreCase("Yes")) {
					securedByREFlg = true;
				}

			}
			if (securedByREFlg) {
				FacilitySecured("Yes");
				waitForInVisibile(PleaseWait);
				VerifyScreenName(AdditionalTermSrn);
				click(continueBtn);
				waitForInVisibile(PleaseWait);
				VerifyScreenName(LetterToTitleCompanySrn);
				waitForInVisibile(PleaseWait);
				sleep(500);
				click(continueBtn);
				sleep(1000);
				FacilitySecuredChattel("No");
				waitForInVisibile(PleaseWait);
			}else{
				FacilitySecured("No");
				sleep(1000);
				FacilitySecuredChattel("Yes");
				VerifyScreenName(AdditionalTermsSecurityAgreement);
				waitForInVisibile(PleaseWait);
				click(continueBtn);
			}
			waitForInVisibile(PleaseWait);
			VerifyScreenName(DisbursementsPage.disbursementsScreen);
			disbursementsPageMethods.AddNewDisbursement(i);
			try {
				waitForInVisibile(PleaseWait);
				sleep(1000);
				List<WebElement> Outstandings = this.driver().findElements(By.xpath("//span[contains(text(), '-Loan (Outstanding)')]"));
				waitForInVisibile(PleaseWait);
				if (Outstandings.get(i).isDisplayed()) {
					Reporter.pass(Outstandings.get(i).getLocation() + " Added in deal tree");
				} else {
					Reporter.fail("Outstanding Added in deal tree for Facility# " + i);
				}
			} catch (Exception e) {
				Reporter.fail("Outstanding Added in deal tree for Facility# " + i + "\n" + e.getMessage());
				Assert.fail(e.getMessage());
			}
			sleep(500);
			click(continueBtn);
			waitForInVisibile(PleaseWait);
			VerifyScreenName(ClosingInstructionsSrn);
			sleep(500);
			Locator ClosInstrYes = null;
			ClosInstrYes = GetLocator("//a[@fieldname='configField54182_Y']");
			if (isWebElementVisible(ClosInstrYes)) {
				CheckRadioButton(ClosInstrYes);
			}
			Reporter.info("Checking Yes RB for Loan Closing Instructions");
			sleep(500);
			click(continueBtn);
		}
	}

	@Then("User loads the deal in Pre-Closing Doc Review and navigates to DDT screen")
	public void userLoadsTheDealInPreClosingDocReviewAndNavigatesToDDTScreen() {
		LoadDealByApplicationNumber(dealAls, "Pre-Closing Review");
		VerifyScreenName(RelatedDocsPage.RelatedDocsScreen);
		sleep(500);
		click(continueBtn);
		waitForInVisibile(PleaseWait);

		VerifyScreenName(EntityInfoPage.entInfoSrn);
		sleep(1000);
		waitForVisibleIgnoreStaleElement(continueBtn);
		clickJS(continueBtn);
		sleep(2000);
		AreDocsReadyForClosing("Yes");
	}

	@Then("User continues to Debt Information screen and says No to Revist DealModels Popup")
	public void userContinuesToDebtInformationScreenAndSaysNoToRevistDealModelsPopup() {
		ViewEditCollateral("No");
		AddCollateralToDeal("No");
		VerifyScreenName(CollateralInfoPage.collateralAnalysisScrn);
		sleep(1000);
		click(continueBtn);
		sleep(3000);
		//	waitForInVisibile(PleaseWait);
		ReviewFees("No");
		VerifyScreenName(approvalPurposeSrn);
		click(continueBtn);
		VerifyScreenName(DebtInformationPage.DebtInfoScrn);
		sleep(1000);
		click(continueBtn);
		RevistDealModels("No");
		waitForInVisibile(PleaseWait);
	}

	@Then("User loads the deal in Post-Closing and navigates to DDT screen")
	public void userLoadsTheDealInPostClosingAndNavigatesToDDTScreen() {
		LoadDealByApplicationNumber(dealAls, "Post-Closing");
		VerifyScreenName(DealInfoPage.ApplicationSrn);
		sleep(500);
		click(continueBtn);
		VerifyScreenName(RelatedDocsPage.RelatedDocsScreen);
		sleep(500);
		click(continueBtn);
		ViewEditCollateral("Yes");
		for (int i=0; i<numOfCollaterals; i++) {
			VerifyScreenName(CollateralInfoPage.collateralScreen);
			sleep(500);
			waitForInVisibile(PleaseWait);
			click(continueBtn);
			if (multiValueMap.get("Collateral Poolable").get(i).equalsIgnoreCase("Yes")) {
				waitForInVisibile(PleaseWait);
				VerifyScreenName(CollateralInfoPage.CollateralPoolScrn);
				sleep(500);
				click(continueBtn);
			}
		}

	}

	@Then("User loads the deal in Post-Closing RE Certification and navigates to DDT screen")
	public void userLoadsTheDealInPostClosingRECertificationAndNavigatesToDDTScreen() {
		LoadDealByApplicationNumber(dealAls, "Post-Closing RE Certification");
		VerifyScreenName(DealInfoPage.ApplicationSrn);
		sleep(500);
		click(continueBtn);
//		if (!bankNumber.equalsIgnoreCase("001")) {
//			VerifyScreenName(RelatedDocsPage.RelatedDocsScreen);
//			sleep(500);
//			click(continueBtn);
//		}
		ViewEditCollateral("Yes");
		for (int i=0; i<numOfCollaterals; i++) {
			VerifyScreenName(CollateralInfoPage.collateralScreen);
			sleep(500);
			waitForInVisibile(PleaseWait);
			click(continueBtn);
			if (multiValueMap.get("Collateral Poolable").get(i).equalsIgnoreCase("Yes")) {
				waitForInVisibile(PleaseWait);
				VerifyScreenName(CollateralInfoPage.CollateralPoolScrn);
				sleep(500);
				click(continueBtn);
			}
		}
		for (int l=0; l<numOfLoans; l++) {
			waitForInVisibile(PleaseWait);
			VerifyScreenName(FacCertificationChecklist);
			sleep(500);
			click(continueBtn);
			waitForInVisibile(PleaseWait);
			VerifyScreenName(OSTCertificationChecklist);
			waitForInVisibile(PleaseWait);
			sleep(500);
			click(continueBtn);
			waitForInVisibile(PleaseWait);
		}

	}

	@Then("User loads the deal in Post-Closing RE Final and navigates to DDT screen")
	public void userLoadsTheDealInPostClosingREFinalAndNavigatesToDDTScreen() {
		LoadDealByApplicationNumber(dealAls, "Post-Closing (RE Final)");
		VerifyScreenName(DealInfoPage.ApplicationSrn);
		sleep(500);
		click(continueBtn);
		VerifyScreenName(RelatedDocsPage.RelatedDocsScreen);
		sleep(500);
		click(continueBtn);
		ViewEditCollateral("Yes");
		for (int i=0; i<numOfCollaterals; i++) {
			VerifyScreenName(CollateralInfoPage.collateralScreen);
			sleep(500);
			click(continueBtn);
		}
	}


	@Then("User validates Approval Level for {string}")
	public void userValidatesApprovalLevelFor(String arg0) {
		Reporter.info("Validating user Approval level in Admin tree - System Users for "+ arg0);
		try {
			sleep(500);
			click(AdminMenu);
			sleep(500);
			click(SystemSetupMenu);
			waitForInVisibile(PleaseWait);
			click(SecurityExpand);
			sleep(500);
			click(SystemUsers);
			waitForInVisibile(PleaseWait);
			clearInput(LastNameSearch);
			inputTextUsingJS(LastNameSearch, arg0);
			sleep(3000);
			Reporter.addScreenshot("Approval level", "Approval level");
			click(IDSecure);
			sleep(500);
			ScrollDown();
			sleep(500);
			Reporter.addScreenshot("Approval level", "Approval level");
			click(FileMenu);
			sleep(500);
			List<WebElement> myWorkEls = this.driver().findElements(By.xpath("//div[@fieldname='My Work']"));
			myWorkEls.get(0).click();
			waitForInVisibile(PleaseWait);
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}
	@Then("User validates User Role for {string} activity in System Users screen")
	public void userValidatesUserRoleForActivityInSystemUsersScreen(String arg0) {

		String SecurityGrp = null;
		String ProcessGrp = null;

		try {
			switch(bankNumber) {
				case "019":
					RoleBasedUsrAcct = "CMETest14";
					switch (arg0){
						case "Application":
						case "Credit Decision":
						case "Credit Approved":
							SecurityGrp	="Loan Admin (No Booking)";
							ProcessGrp	="AFC Loan Admin";
							break;
						case "Due Diligence":
							SecurityGrp	="Loan Admin Credit Hybrid (NB)";
							ProcessGrp	="AFC Loan Admin";
							break;
						case "Pre-Closing":
						case "Closing Doc Execution":
						case "Closing Fund/Book":
							SecurityGrp	="Loan Closer (Booking Hybrid)";
							ProcessGrp	="AFC Loan Closer";
							break;
						case "Post-Closing":
						case "Post-Closing RE Certification":
							SecurityGrp	="Collateral Custodian";
							ProcessGrp	="AFC Collateral Custodian";
							break;
						default:
							throw new IllegalStateException("Unexpected Activity value: " + arg0);
					}
				case "011":

					break;

				default:
					throw new IllegalStateException("Unexpected Bank nvalue: " + bankNumber);
			}
			Reporter.info("Admin goes to System Users and updates Role settings for "+ RoleBasedUsrAcct+ " to"+ "Association="+ bankNumber +", Security Group="+SecurityGrp + ", Process Group="+ ProcessGrp);
			sleep(500);
			click(AdminMenu);
			sleep(500);
			click(SystemSetupMenu);
			waitForInVisibile(PleaseWait);
			click(SecurityExpand);
			sleep(500);
			click(SystemUsers);
			waitForInVisibile(PleaseWait);
			clearInput(LastNameSearch);
			inputTextUsingJS(LastNameSearch, RoleBasedUsrAcct);
			sleep(3000);
			String NtwrkLogID = getElementAttribute(SystemUsersPage.NetworkLogonID, "title");
			if (NtwrkLogID.equalsIgnoreCase(RoleBasedUsrAcct)) {
				if (!getElementAttribute(SystemUsersPage.MainProcessCenter, "title").equalsIgnoreCase(bankNumber)) {
					clearInput(SystemUsersPage.MainProcessCenter);
					inputTextUsingJS(SystemUsersPage.MainProcessCenter, bankNumber);
				}
				if (!getElementAttribute(SystemUsersPage.ProcessCenters, "title").equalsIgnoreCase(bankNumber)) {
					clearInput(SystemUsersPage.ProcessCenters);
					inputTextUsingJS(SystemUsersPage.ProcessCenters, bankNumber);
				}
				if (!getElementAttribute(SystemUsersPage.Affiliates, "title").equalsIgnoreCase(bankNumber)) {
					clearInput(SystemUsersPage.Affiliates);
					inputTextUsingJS(SystemUsersPage.Affiliates, bankNumber);
				}
				if (!getElementAttribute(SystemUsersPage.Affiliates, "title").equalsIgnoreCase(bankNumber)) {
					clearInput(SystemUsersPage.PrmAffiliate);
					inputTextUsingJS(SystemUsersPage.PrmAffiliate, bankNumber);
				}
				inputTextUsingJS(SystemUsersPage.SecurityGrp, SecurityGrp);
				inputTextUsingJS(SystemUsersPage.ProcessGrp, ProcessGrp);

				Reporter.addScreenshot("Security Group", "Security Group");
				click(IDSecure);
				sleep(500);
				ScrollDown();
				sleep(500);
				Reporter.addScreenshot("Approval level", "Approval level");
				ScrollDown();
				sleep(500);
				Reporter.addScreenshot("User can enter deal decision", "User can enter deal decision");
				sleep(500);
				clickJS(saveBtn);
				waitForInVisibile(PleaseWait);
				waitForInVisibile(PleaseWait);
				sleep(500);
				ClickMenuItem(FileMenu, MyWorkMenu);
				waitForInVisibile(PleaseWait);
				waitForVisible(DashboardPage.dashboard);
			}else{
				Reporter.fail("Wrong User: "+ NtwrkLogID);
				Assert.fail("Wrong User: "+ NtwrkLogID);
			}




		} catch (IllegalStateException e) {
			Assert.fail(e.getMessage());
		}


	}

	@Then("User updates {string}")
	public void userUpdates(String arg0) {
		if (arg0.equalsIgnoreCase("CB Score")) {
			UpdateCBScoreInCredAssmt(dataMap.get("CB Score"));
		} else if (arg0.equalsIgnoreCase("AgFast Score")) {
			UpdateAgFastScoreInDealInfo(dataMap.get("AgFast Score"));
		}else if (arg0.equalsIgnoreCase("PD Rating")) {
			UpdatePDInDealInfo(dataMap.get("Relationship PD"));
		}
	}

	@Then("User captures Performance metrics {string}")
	public void userCapturesPerformanceMetrics(String arg0) {
		if (arg0.equalsIgnoreCase("yes")) {
			CapturePerfMetrics = true;
			ReleaseNumberToGrafana = true;
		}
	}

	@Then("User inserts performance data to Database")
	public void userInsertsPerformanceDataToDatabase() {
		WriteCSVtoDB();
	}
}