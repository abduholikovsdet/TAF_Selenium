package com.cme.pagemethods;

import com.cme.pages.AuthorizationPage;
import com.cme.pages.DealInfoPage;
import com.cme.pages.DealModelsPage;
import com.cme.pages.FacilityInfoPage;
import com.fcbt.taf.core.BasePage;
import com.fcbt.taf.core.SystemConstants;
import com.fcbt.taf.core.driver.ui.Locator;
import com.fcbt.taf.core.reporting.Reporter;
import io.cucumber.messages.Messages;
import org.apache.commons.lang.WordUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Now;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class CmeBasePage extends BasePage{

	public static HashMap<String, String> dataMap = new HashMap<String, String>();
	public static HashMap<String, String> CollateralDesc = new HashMap<String, String>();
	public static HashMap<String, String> CSiDocsMap = new HashMap<String, String>();
	public static HashMap<String, ArrayList<String>> multiValueMap = new HashMap<String, ArrayList<String>>();
	public static Map<String,String> outStandingValues = new HashMap<String,String>();
	public static Map<String,String> facilityValues = new HashMap<String,String>();
	private String file;
    private static Workbook workbook = null;
	private static Sheet sheet=null;
    private static FileInputStream fs = null;
	public static FileWriter csvWriter = null;
	private static String filePath = "";
	private static String testDatafilePath;
	private static String excelextensionxlsx = ".xlsx";
	private static String excelextensionxlsm = ".xlsm";
	private static String excelextensionxls = ".xls";
	public static String scrnName;
	public static String UserAccount;
	public static String UserAccountPwd;
	public static String dealAls;
	public static String prmBorrFullName1; // format: "firstname middlename Lastname"
	public static String prmBorrFullName2; // format: "Lastname, firstname middlename "
	public static String PrmLoanOfficer;
	public static String DDTOfficerName;
	public static String Appraiser;
	public static String LoanAdmin;
	public static String Branch;
	public String EnvName;
	public static String EnvDomain;
	public static String Association;
	public static String bankNumber;
	public static String BranchCode;
	public static String bankState;
	public static String LUSType;
	public static String RoleBasedUsrAcct;
	public static String SubIndustry;
	public static String TargetCloseDate;
	public static String locationCode;
	public static String ProcessCenter;
	public static String DDTLoanAdmin;
	public static int numOfEnts;
	public static int numOfLoans;
	public static int numOfCollaterals;
	public static Boolean BLStk;
	public static String LTVScore;
	public static String TitleCompany;
	public static String angJSFacSelect = "Facility #1" + " - ";
	public static String prmBorrCustomerNumber;
	public static String complexity;
	public static List<String> ServiceGroup = new ArrayList<String>();
	public static List<String> LoanOfficer = new ArrayList<String>();
	public static List<String> CBData = new ArrayList<String>();
	public static List<String> PreferedRemitInstructions = new ArrayList<String>();
	public static List<String> ContactsFullName = new ArrayList<String>();
	public static List<String> ContactsProfileLocations = new ArrayList<String>();
	public static List<String> ContactsSG = new ArrayList<String>();
	public static List<String> BorrSpread = new ArrayList<String>();
	public static List<String> Report1098Int = new ArrayList<String>();
	public static List<String> Guarantors = new ArrayList<String>();
	public static List<String> GuaranteeType = new ArrayList<String>();
	public static List<String[]> PerfMetrics = new ArrayList<String[]>();
	public static boolean CapturePerfMetrics = false;
	public static boolean ReleaseNumberToGrafana = false;
	public static double start;
	public static double finish;
	public static double timeElapsed;
	public static String timeStampForPerf = new SimpleDateFormat("M/d/yyyy HH:mm:ss").format(new java.util.Date());
	public static String Activity = null;
	public static String Department;
	public static String lenderCode;
	public static String PortfolioExpenseCode;
	public static String RiskBook;
	public static String ProfileLocation;
	public static String BorrowerProfileLocation;
	public static String GuarantorProfileLocation;
	public static Locator DealBooking = Locator.byXpath("//span[text()='Deal Booking']");
	public static String FacilityType;
	public static String productTypeValue;
	public static String supportedRiskTypeValue;
	public static String decisionAmountValue;
	public static String borrowerStatedRateValue;
	public static String pricingOptionValue;
	public static String lateChargeValue;
	public static String cofSpreadValue;
	public static boolean ExistingCustomerFlag = false;
	public static boolean AFC3rdApprover = false;
	public static List <Boolean> GuarantorFlg = new ArrayList<Boolean>();
	public static List <Boolean> GuaranteePrgrmFlg = new ArrayList<Boolean>();
	public static List <Boolean> BookRPSFlg = new ArrayList<Boolean>();;
	public static List<String> GuaranteePrgrmYN = new ArrayList<String>();
	//Common page objects across CME
	public static Locator CMESignOutIcon = Locator.byXpath("//*[@id='main_a_user_dropdown']");
	public static Locator CMELogOutBtn = Locator.byXpath("//button[@qa-id='main_a_logout' and text()='Log out']");
	public static Locator PleaseWait = Locator.byXpath("//div[@class='label' and contains(text(), 'Please Wait')]"); //("//div[@fieldname='Please Wait...']");
	public static Locator OperatorAlert = Locator.byXpath("//div[@fieldname='OPERATOR ALERT']");
	public static Locator DoYouAcceptNewActivityPopUp = Locator.byXpath("//div[@class='label' and contains(text(), 'Do you accept receipt of this new activity?')]");
	public static Locator WaitIcon = Locator.byXpath("//img[contains(@src,'?_twr_=wait.png')]");
	public static Locator ErrorIcon = Locator.byXpath("//img[contains(@src,'?_twr_=error.png')]");
	public static Locator LoadCircleGIF = Locator.byXpath("//img[contains(@src,'?_twr_=loadcircle.gif')]");
	public static Locator DialogOKbtn = Locator.byXpath("//div[@class='dialog']//a[@fieldname='OK' and contains(@style, 'display: block')]");
	public static Locator continueBtn = Locator.byXpath("//a[@fieldname='Continue']");
	public static Locator saveBtn = Locator.byXpath("//a[@fieldname='Save']");
	public static Locator editBtn = Locator.byXpath("//a[@fieldname='Edit']");
	public static Locator searchBtn = Locator.byXpath("//a[@fieldname='Search']");
	public static Locator refreshBtn = Locator.byXpath("//a[@fieldname='Search']");
	public static Locator newBtn = Locator.byXpath("//a[@fieldname='New']");
	public static Locator yesBtn = Locator.byXpath("//a[@fieldname='Yes']");
	public static Locator NoBtn = Locator.byXpath("//a[@fieldname='No']");
	public static Locator okBtn = Locator.byXpath("//a[@fieldname='Ok']");
	public static Locator OKBtn = Locator.byXpath("//a[@fieldname='OK']");
	public static Locator cancelBtn = Locator.byXpath("//a[@fieldname='Cancel']");
	public static Locator submitBtn = Locator.byXpath("//a[@fieldname='Submit']");
	public static Locator clickHereToViewDocument = Locator.byXpath("//a[@fieldname='Click here to view the document.']");
	public static Locator EntityActiveInOtherDeals = Locator.byXpath("//div[@fieldname='Entity Active in Other Deals']");
	public static Locator DoneBtn = Locator.byXpath("//a[@fieldname='Done']");
	public static Locator finishBtn = Locator.byXpath("//a[@fieldname='Finish']");
	public static Locator appNumber = Locator.byXpath("//div[@fieldname='Resize Panel']/div");
	public static Locator Tasks = Locator.byXpath("//div[@class='mainMenuButton' and text() = 'Tasks']");
	public static Locator ApplicationF = Locator.byXpath("//span[@class='menuButtonText' and text() = 'Application (F)']");
	public static Locator ApplicationB = Locator.byXpath("//span[@class='menuButtonText' and text() = 'Application (B)']");
	public static Locator Deal = Locator.byXpath("//div[@class='mainMenuButton' and text() = 'Deal']");
	public static Locator DealManager = Locator.byXpath("//span[@class='menuButtonText' and text() = 'Deal Manager']");
	public static Locator ExitCurrentTask = Locator.byXpath("//span[@class='menuButtonText' and text() = 'Exit Current Task']");
	public static Locator additionalEntDetailsSrn = Locator.byXpath("//a[@class='floatDivLeft' and contains(text(),'- Additional Entity Details')]");
	public static Locator AdditionalTermSrn = Locator.byXpath("//a[@class='floatDivLeft' and contains(text(),'Additional Terms')]");
	public static Locator ClosingInstructionsSrn = Locator.byXpath("//a[@class='floatDivLeft' and contains(text(),'Closing Instructions')]");
	public static Locator LetterToTitleCompanySrn = Locator.byXpath("//a[@class='floatDivLeft' and contains(text(),'Letter To Title Company')]");
	public static Locator AdditionalTermsSecurityAgreement = Locator.byXpath("//a[@class='floatDivLeft' and contains(text(),'Additional Terms - Security Agreement')]");
	public static Locator approvalPurposeSrn = Locator.byXpath("//a[@class='floatDivLeft' and contains(text(),'Approval Purpose')]");
	public static Locator AngSaveBtn = Locator.byXpath("//button[@class='btn btn-default' and contains(text(),'Save')]");
	public static Locator AngAddBtn = Locator.byXpath("//button[text()='Add']");
	public static Locator AngPleaseWait = Locator.byXpath("//img[@alt-text='Loading circle']");
	public static Locator AppSearchBox = Locator.byXpath("//div[@fieldname='Go to Deal Number']/input");
	public static Locator NoDealsFound = Locator.byXpath("//div[@class='label' and contains(text(),'No Deals found')]");
	public static String dealNumber;
	public static Locator liquidCreditDealTree = Locator.byXpath("//span[@fieldname='Liquid Credit']");
	public static Locator obtainDecision = Locator.byXpath("//a[@fieldname='Obtain Decision']");
	public static Locator AgFastApplicationF = Locator.byXpath("//span[@class='menuButtonText' and text() = 'AgFAST Application (F)']");
	public static Locator AgFastApplicationB = Locator.byXpath("//span[@class='menuButtonText' and text() = 'AgFAST Application (B)']");
	public static Locator creditRequested = Locator.byXpath("//div[@fieldname='Do you still want to request information from Liquid Credit at this time?']");
	public static Locator addBtn = Locator.byXpath("//a[@fieldname='Add']");
	public static Locator SelectMultipleSectionsScrn = Locator.byXpath("//a[@fieldname='Select Multiple Sections']");
	public static Locator PrintBtn = Locator.byXpath("//a[@fieldname='Print']");
	public static Locator DealStatus = Locator.byXpath("//span[contains(text(), ' - Status: ')]");
	public static Locator FinalCDA = Locator.byXpath("//div[@class='gridBoxCell' and contains(text() , 'Final CDA')]");
	public static Locator DocPkg1 = Locator.byXpath("//div[@class='gridBoxCell' and contains(text() , '_DocPkg_1.PDF')]");
	public static Locator DocPkg2 = Locator.byXpath("//div[@class='gridBoxCell' and contains(text() , '_DocPkg_2.PDF')]");
	public static Locator AddCoborrowerGuarantorCollateralOwnerPopUp = Locator.byXpath("//div[@class='label' and contains(text() , 'Co-Borrower, Guarantor or Collateral Owner?')]");
	public static Locator AddAlternateAddressPopUp = Locator.byXpath("//div[@class='label' and contains(text() , 'Would you like to view/add an Alternate')]");
	public static Locator LoanClosingInstructionsYes = Locator.byXpath("//a[@fieldname='configField56480_Y' or 'configField53116_Y']");
	public static Locator LoanClosingInstructionsNo = Locator.byXpath("//a[@fieldname='configField56480_N']");
	public static Locator LetterToTitleCompanyYes = Locator.byXpath("//a[@fieldname='configField53311_Y']");
	public static Locator LetterToTitleCompanyNo = Locator.byXpath("//a[@fieldname='configField53325_N']");
	public static Locator InfoPopUp = Locator.byXpath("//div[@class='container']/div[@fieldname='Information']");
	public static Locator FacCertificationChecklist = Locator.byXpath("//*[contains(text(), '- Certification Checklist')]");
	public static Locator OSTCertificationChecklist = Locator.byXpath("//*[contains(text(), 'Loan (Outstanding) - Certification Checklist')]");
	public static List <String> FacilityNumber = new ArrayList<String>();
	public static List <String> LoanID = new ArrayList<String>();
	public static List <String> CustomerNumber = new ArrayList<String>();
	public static String RelPD;
	public static String ObsStk;
	public static List <String> LgdRating = new ArrayList<String>();
	public static List <String> YbsYoung = new ArrayList<String>();
	public static List <String> YbsBeginner = new ArrayList<String>();
	public static List <String> YbsSmall = new ArrayList<String>();
	public static HashMap<String, String> CompanyContactSG = new HashMap<String, String>();
	public static HashMap<String, String> CompanyContactFName = new HashMap<String, String>();
	public static HashMap<String, String> CompanyContactLName = new HashMap<String, String>();
	public static HashMap<String, String> CompanyContactMName = new HashMap<String, String>();
	public static HashMap<String, String> CompanyContactPhone = new HashMap<String, String>();
	public static HashMap<String, String> CompanyContactAddress = new HashMap<String, String>();
	public static List <String> CollateralID = new ArrayList<String>();
//	public static List<String> CSiDocsValues = new ArrayList<String>();
	public static String CompanyContact;
	public static List <String> CustomerSG = new ArrayList<String>();
	public static Locator screen;
	public static Locator popUp;
	public static Locator button;
	public static Locator grdBxCellElem;
	public static Locator dealTreeEl;
	public static Locator AngularDDElem;
	public static Locator tempLocator;
	public static Locator ScreenNotSavedMsg = Locator.byXpath("//div[@fieldname='Screen Not Saved']");
	public static String RqsURL;
	public static Locator AngOKBtn = Locator.byXpath("//button[text()='OK']");
	public static Locator OptimistLoginWndw = Locator.byXpath("//div[@fieldname='Optimist Login']");;
	public static Locator SystemException = Locator.byXpath("//div[@fieldname='System Exception']");
	public static Locator FileMenu = Locator.byXpath("//div[@fieldname='File']");
	public static Locator MyWorkMenu = Locator.byXpath("//div[@fieldname='My Work']");
	public static Locator AdminMenu = Locator.byXpath("//div[@fieldname='Admin']");
	public static Locator SystemSetupMenu = Locator.byXpath("//div[@fieldname='System Setup ']");
	public static Locator SecurityExpand = Locator.byXpath("//span[@fieldname='Security']/parent::div/img[1]");
	public static Locator SystemUsers = Locator.byXpath("//span[@fieldname='System Users']");
	public static Locator LastNameSearch = Locator.byXpath("//div[@fieldname='Dropdown 1']/input");
	public static Locator IDSecure = Locator.byXpath("//div[@fieldname='IDSECURE']");
	public static Locator HelpMenu = Locator.byXpath("//div[@fieldname='Help']");
	public static Locator AboutMenu = Locator.byXpath("//div[@fieldname='About Credit Management Enterprise']");
	public static Locator ReleaseNumberWindw = Locator.byXpath("//div[@fieldname='Built on Credit Management Enterprise']");
	public static Locator EntitiesTreeRow = Locator.byXpath("//span[@fieldname='Borrowing Entities']//parent::div//span[@class='treeBranch']//span[contains(text(), ' Info (')]");
	//Verifies screen names 
	public void VerifyScreenName(Locator locator) {
		String scrnName = getElementText(locator);
		try {
			waitForClickable(locator);
			if (isWebElementVisible(locator)) {
				Reporter.pass("Verifying "+ scrnName + " screen displayed");
			}else{
				Reporter.fail("Verifying "+ scrnName + " screen displayed");
				Assert.fail("Verifying "+ scrnName + " screen displayed");
			}

		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Verifying "+ scrnName + " screen displayed");
		}
		if (scrnName.contains("#")) {
			scrnName = scrnName.replace("#", "_");
			scrnName = scrnName.replace("/", "_");
			scrnName = scrnName.replace("(", "_");
			scrnName = scrnName.replace(")", "_");
		}
		Reporter.addScreenshot(scrnName, scrnName);

	}

	//Verifies screen names by Text
	public void VerifyScreenNameByText(String scrnName) {
		try {
			screen = Locator.byXpath("//a[@class='floatDivLeft' and contains(text(), '"+scrnName + "']");
			isWebElementVisible(screen);
			Reporter.pass("Verifying "+ scrnName+ " screen displayed");
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Verifying "+ scrnName + " screen displayed");
		}
	}
	//Wait for element visibility for given seconds and returns boolean
	public boolean isElementExist(Locator locator, int waitDuration) {
		boolean found = false;
		driver().manage().timeouts().implicitlyWait(waitDuration, TimeUnit.SECONDS);
		try {
			List<WebElement> foundEls = driver().findElements(locator.getBy());
			if (foundEls.size() > 0) {
				found = true;
			}
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Reporter.addScreenshot("Element", "element");
			Assert.fail(e.getMessage());
		}
		driver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		return found;
	}
	//Used for clicking pop ups which displayed on Please Wait. Ex: CSi pop pup while loading Documents screen
	public void ClickElementOnPleaseWait(Locator topWndw){
		boolean found = false;
		int count = 0;
		driver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		do {
			if (count==100) {
				break;
			}
			List<WebElement> foundEls = this.driver().findElements(By.xpath("//div[@class='label' and contains(text(), 'Please Wait')]"));
				if (foundEls.size() > 0) {
					found = true;
					sleep(2000);
					if (isWebElementVisible(topWndw)) {
						//System.out.println("Dialog visible");
						click(OKBtn);
					}

				} else {
					found = false;
				}
			count++;

		} while (found);

	}
	public boolean waitForInVisibile(Locator locator) {
		if (locator.toString().equalsIgnoreCase("//div[@class='label' and contains(text(), 'Please Wait')]")) {
			CheckCMESystemExceptionError();
			return true;
		} else {
			return this.waitForInVisibile(locator, SystemConstants.DEFAULT_EXPLICITE_WAIT);
		}

	}
	//Clicks gridboxcell element
	public void clickGridBoxCellElement(String elem) {
		grdBxCellElem = Locator.byXpath("//div[@class='gridBoxCell' and text() = '"+elem + "']");
		try {
			clickJS(grdBxCellElem);

		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail(elem + " Not Found");
		}

	}
	//Verifies gridboxcell element
	public void VerifyGridBoxCellElement(String elem) {
		grdBxCellElem = Locator.byXpath("//div[@class='gridBoxCell' and contains(text(), '"+elem + "')]");
		try {
			isElementExist(grdBxCellElem, 2);
			Reporter.pass(elem+" Exists in the grid");

		} catch (Exception e) {
			Reporter.fail(elem+" Exists in the grid"+ e.getMessage());
		}

	}
	public Locator GetLocator(String xpath){
		tempLocator = Locator.byXpath(xpath);
		return tempLocator;
	}

	public void ClickLinkByPartialText(String txt){
		tempLocator = Locator.byPartialLinkText(txt);
		waitForClickable(tempLocator);
		clickJS(tempLocator);

	}
	//Clicks Row element in Angular screen tables
	public void clickAngularDDElement(String elem) {
		AngularDDElem = Locator.byXpath("//td[text() = '"+elem + "']");
		try {
			isWebElementVisible(AngularDDElem);
			clickJS(AngularDDElem);
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail(elem + " Not Found");
		}

	}
	//Gets element attribute
	public String getElementAttribute(Locator locator, String attrbName) {
		WebElement element = this.driver().findElement(locator.getBy());
		return element.getAttribute(attrbName).trim();
	}

	public void pressDownKey(Locator locator) {
		this.waitForVisible(locator);
		this.driver().findElement(locator.getBy()).sendKeys(new CharSequence[]{Keys.ARROW_DOWN});
	}
	public void pressSpaceKey(Locator locator) {
		this.waitForVisible(locator);
		this.driver().findElement(locator.getBy()).sendKeys(new CharSequence[]{Keys.SPACE});
	}

	//Get Application number from left top panel
	public String getApplicationNumber(){
		try {
			waitForVisibleIgnoreStaleElement(appNumber);
			dealAls = getElementText(appNumber);
			String[] str = dealAls.split("#");
			dealAls = str[1];

		} catch (Exception e) {
			Assert.fail("Fetching Application Number"+ "\n"+ e.getMessage());
		}
		return dealAls;
	}
	//Checks Radio Button
	public void CheckRadioButton(Locator locator){
		String fldNm = getElementAttribute(locator, "fieldname");
		Locator rbImg = Locator.byXpath("//a[@fieldname='"+ fldNm +"']/div/div");
		WebElement element = this.driver().findElement(rbImg.getBy());
		String cssValue = element.getCssValue(("background-image"));
		if (!cssValue.contains("Radio_checked")) {
			click(locator);
		}
	}
	//Verify radio button checked
	public boolean VerifyRadioButtonChecked(Locator locator){
		Boolean checked = false;
		String fldNm = getElementAttribute(locator, "fieldname");
		Locator rbImg = Locator.byXpath("//a[@fieldname='"+ fldNm +"']/div/div");
		WebElement element = this.driver().findElement(rbImg.getBy());
		String cssValue = element.getCssValue(("background-image"));
		if (cssValue.contains("Radio_checked")) {
			checked = true;
		}
		return checked;
	}
	//Verifies and clicks Pop ups
	public void InformationPopUps(String message, String YesNo) {
		popUp = Locator.byXpath("//div[@class='label' and contains(text(), '"+message + "')]");
		button = Locator.byXpath("//a[@fieldname='" + YesNo + "']");
		try {
			waitForVisible(InfoPopUp);
			if (isWebElementVisible(popUp)) {
//				Reporter.info(message+ " - "+ YesNo);
//				sleep(500);
//				click(button);
			} else {
				Reporter.fail(message+ " - "+ YesNo);
//				Reporter.info("Expected pop up: "+ getElementText(InfoPopUp));
				Reporter.addScreenshot("popup", "popup");
			}
			sleep(500);
			click(button);
			Reporter.info("Clicking "+ YesNo + " in Pop Up - "+ message);
		} catch (Exception e) {
			try {
				sleep(3000);
				isWebElementVisible(popUp);
				click(button);
				Reporter.info("Clicking "+ YesNo + " in Pop Up - "+ message);
			} catch (Exception exception) {
				Reporter.fail(e.getMessage());
				Assert.fail(message);
			}

		}
	}

	//Handles Would you like to view/add an Alternate Address pop up
	public void ViewAddAltAddressPopUp(String YesNo){
		InformationPopUps("Would you like to view/add an Alternate", YesNo);
	}
	//Handles Add Co-Borrower, Guarantor or Collateral Owner? pop up
	public void AddCoborrGuarCollatOwnerPopUp(String YesNo){ InformationPopUps("Co-Borrower, Guarantor or", YesNo);
	}
	//Handles Would you like to add or review Related Entities? pop up
	public void AddRelatedEntitiesPopUp(String YesNo){
		InformationPopUps("Would you like to add or review Related Entities?", YesNo);
	}
	//Handles Would you like to add another Facility pop up
	public void AddAnotherFacilityPopUp(String YesNo){
		InformationPopUps("Would you like to add another Facility", YesNo);
	}
	//Handles Would you like to add new Collateral?
	public void AddNewCollateralPopUp(String YesNo){
		InformationPopUps("Would you like to add new Collateral?", YesNo);
	}
	//Handles Would you like to add new Collateral?
	public void IsThisDealSecuredWithCollateralPopUp(String YesNo){
		InformationPopUps("Is this deal secured with Collateral?", YesNo);
	}
	//Handles Do you wish to continue to add to deal?
	public void DoYouWishToContinueToAddDeal(String YesNo){
		InformationPopUps("Do you wish to continue to add to deal?", YesNo);
	}

	public void WouldYouLikeToAddExistingCollateral(String YesNo){
		InformationPopUps("Would you like to add existing", YesNo);
	}

	//Handles Would you like to Do You Want To Go To Deal Models PopUp
	public void DoYouWantToGoToDealModelsPopUp(String YesNo){ InformationPopUps("Before you proceed, please review/update following Inputs:", YesNo);
	}

	//Handles Would you like to add Fees?
	public void AddFeesPopUp(String YesNo){
		InformationPopUps("Would you like to add Fees?", YesNo);
	}
	//Handles Would you like to Generate Application
	public void GenerateAppRelatedDocs(String YesNo){
		InformationPopUps("Would you like to Generate Application", YesNo);
	}
	//Handles Would you like to download any Related documents pop up
	public void DownloadAnyRelatedDocsPopUp(String YesNo){
		InformationPopUps("Would you like to", YesNo);
	}
	//Handles Would you like to Pull Credit Report pop up
	public void PullCreditReport(String YesNo){
		InformationPopUps("Credit Report", YesNo);
	}
	//Handles Would you like to resubmit/rescore AgFast
	public void ResubmitRescoreAgFast(String YesNo){
		InformationPopUps("Would you like to resubmit/rescore", YesNo);
	}
	//Handles Would you like to View/Edit Collateral pop up
	public void ViewEditCollateral(String YesNo){
		InformationPopUps("Would you like to View/Edit Collateral", YesNo);
	}
	//Handles Would you like to add Collateral to the pop up
	public void AddCollateralToDeal(String YesNo){
		InformationPopUps("Would you like to add Collateral to the", YesNo);
	}
	//Handles Would you like to Review Fees? the pop up
	public void ReviewFees(String YesNo){
		InformationPopUps("Fees?", YesNo);
	}
	public void RevistDealModels(String YesNo) {
		InformationPopUps("Do you want to revisit Deal Models?", YesNo);
	}
	//Handles Would you like to print Draft CDA? pop up for CFC
	public void PrintDraftCDACFCTrad(String YesNo){
		InformationPopUps("Would you like to upload the Credit Narrative", YesNo);
	}
	//Handles Would you like to create/update pop up
	public void CreateUpdateCovenants(String YesNo){
		InformationPopUps("Covenants", YesNo);
	}
	//Handles Would you like to print Draft CDA? pop up
	public void PrintDraftCDA(String YesNo){
		InformationPopUps("Draft CDA?", YesNo);
	}
	//Handles Would you like to print Draft CDA? pop up
	public void DoYouAcceptReceiptOfThisNewActivity(String YesNo){
		InformationPopUps("Do you accept receipt of this new activity?", YesNo);
	}
	//Handles Are you ready to create the booking pop up
	public void AreYouReadyToCreateBooking(String YesNo){
		InformationPopUps("Are you ready to create the booking", YesNo);
	}

	public void AddReviewFeesPopUp(String YesNo){
		InformationPopUps("Fees?", YesNo);
	}
	//Handles Would you like to print Draft CDA? pop up for CFC
	public void PrintDraftCDACFC(String YesNo){
		InformationPopUps("Would you like to view/print Draft ", YesNo);
	}

	public void ModifyAgFast(String YesNo) {
		InformationPopUps("Do you want to modify AgFAST Input and", YesNo);
	}


	public void ExercisePreApproval(String YesNo) {
		InformationPopUps("Exercise Pre-Approval/Conditional", YesNo);
	}

	//
	public void PreClosingPopup(String YesNo) {
		InformationPopUps("The selected activity", YesNo);
	}

	//Handles Add/Adjust Signatories in Related
	public void AddAdjustPopup(String YesNo) {
		InformationPopUps("Add/Adjust Signatories in Related", YesNo);
	}

	//Handles Is one or more Facility Secured by
	public void FacilitySecured(String YesNo) {
		InformationPopUps("Is one or more Facility Secured by", YesNo);
	}

	//Handles Is one or more Facility Secured by
	public void FacilitySecuredChattel(String YesNo) {
		InformationPopUps("Is one or more Facility Secured by", YesNo);
	}

	//Handles Is one or more Facility Secured by Chattel
	public void AreDocsReadyForClosing(String YesNo) {
		InformationPopUps("Are Docs Ready for Closing", YesNo);
	}
	//Handles Is one or more Facility Secured by Chattel
	public void WouldYouLikeToAddAdditionalTerms(String YesNo) {
		InformationPopUps("Would you like to add Additional Terms", YesNo);
	}

	//Clicks Menu items
	public void ClickMenuItem(Locator locator1, Locator locator2){
		try {
			sleep(1000);
			click(locator1);
			sleep(1000);
			click(locator2);
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("Clicking Menu Item");
		}
	}
	//Switching to CME iFrame
	public void SwitchToCMEFrame(){
		switchToFrameToBeAvailable("iframe");
	}
	//Switching to Angular iFrame
	public void SwitchToAngularFrame(){
		WebDriverWait wait = new WebDriverWait(this.driver(), 60L);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//div[@class='webBrowser']/iframe")));
	}
	//Switching to Angular iFrame
	public void SwitchToOBSFrame(){
		WebDriverWait wait = new WebDriverWait(this.driver(), 60L);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//div[@fieldname='CUSTOM_BOOKING']//iframe")));

	}
	public void SwitchToECM(){
		WebDriverWait wait = new WebDriverWait(this.driver(), 60L);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@class='button']/iframe")));

	}
	/**
	 * Author: Muzaffar A.
	 * @param filePath
	 * @param sheetName
	 */
	public static void init(String filePath, String sheetName) {
		String fileExtensionName = filePath.substring(filePath.indexOf('.'));
		try {
			fs = new FileInputStream(filePath);
			if (fileExtensionName.equals(excelextensionxlsx)) {
				// If it is xlsx file then create object of XSSFWorkbook class
				workbook = new XSSFWorkbook(fs);
			}
			// Check condition if the file is xlsm file
			else if (fileExtensionName.equals(excelextensionxlsm)) {
				// If it is xlsm file then create object of XSSFWorkbook class
				workbook = new XSSFWorkbook(fs);
			}
			// Check condition if the file is xls file
			else if (fileExtensionName.equals(excelextensionxls)) {
				// If it is xls file then create object of XSSFWorkbook class
				workbook = new HSSFWorkbook(fs);
			}
			sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Reporter.info("Extension Not Matched", e);
			
		}

	}

	/**
	 * Author: Muzaffar A.
	 * Description: Reading test data to hashMap data structure
	 * @param testDatafilePath
	 * @param sheetName
	 * @return
	 */
	public static HashMap<String, String> ReadTestDataFromExcel(String testDatafilePath, String sheetName) {
			DataFormatter formatter = new DataFormatter();
			final HashMap<String, String> currentRowData = new HashMap<String, String>();
			init(testDatafilePath, sheetName);
			Iterator<Row> rowIterator = sheet.iterator();
			try {
				//int iCellCounter = 0;
				Boolean multiValueMapFlag 	= false;
				Boolean CSiValueMapFlag 	= false;
				while (rowIterator.hasNext()) {
					Row row =  rowIterator.next();
					int valueColumns = row.getPhysicalNumberOfCells();
					String key = row.getCell(0).getStringCellValue().trim();
					if (key.equalsIgnoreCase("Entities:")
						|| key.equalsIgnoreCase("Related Entities:")
						|| key.equalsIgnoreCase("Facilities:")
						|| key.equalsIgnoreCase("Collaterals::")
						|| key.equalsIgnoreCase("ECM Documents:")) {
						multiValueMapFlag = true;
					}else if ((key.equalsIgnoreCase("CME-CSi Documents:"))){
						CSiValueMapFlag = true;
						multiValueMapFlag = false;

					}else if (key.equalsIgnoreCase("Deal info")
							  || key.equalsIgnoreCase("Scored Financial")){
						multiValueMapFlag = false;
						CSiValueMapFlag = false;
					}

					//System.out.println("Value Columns: "+ valueColumns);
					String cellValue = null;
					ArrayList<String> cellValues = new ArrayList<String>();
					if (multiValueMapFlag) {
						for (int i = 0; i<valueColumns; i++) {
							if (i==0) {
								continue;
							}
							cellValues.add(formatter.formatCellValue(row.getCell(i)).trim());

						}
						multiValueMap.put(key, cellValues);
					}else if (CSiValueMapFlag){
						cellValue = formatter.formatCellValue(row.getCell(1)).trim();

						if (!key.equalsIgnoreCase("CME-CSi Documents:")) {
							CSiDocsMap.put(key, cellValue);
						}
					}
					else {
						cellValue = formatter.formatCellValue(row.getCell(1)).trim();
						currentRowData.put(key, cellValue);
					}
				}

				fs.close();
			} catch (Exception e) {
				Reporter.fail("Reading test from test data file "+ e.getMessage());
			}
			return currentRowData;
		}

	/**
	 * Author: Muzaffar A.
	 * Description: Reading test data to hashMap data structure
	 * @param testDatafilePath
	 * @param sheetName
	 * @return
	 */
	public HashMap<String, String> GenerateTestDataWriteToExcel(String testDatafilePath, String assc, String env, String sheetName) {
		DataFormatter formatter 						=new DataFormatter();
		final HashMap<String, String> currentRowData 	=new HashMap<String, String>();
		boolean CurrentLIQDate 							=true;
		boolean traditionalComplx						=false;
		boolean AgFastComplx							=false;
		boolean AltAddrBool								=false;
		boolean spouseBool								=false;
		int spsEntNum									=10;
		List<Boolean> IndEntTypeFlag					=new ArrayList<Boolean>();
		List<Boolean> CBDataUsedBool					=new ArrayList<Boolean>();
		init(testDatafilePath, sheetName);

		EnvName 	=env;
		EnvDomain 	=InitDomain();
		bankNumber	=assc;
		GetBranchCodeLUSTypeSubInd();
		TargetCloseDate = QueryGetLIQDate();
		sleep(2000);
		QueryGetLoanOfficer();
		Iterator<Row> rowIterator = sheet.iterator();
		try {
			//int iCellCounter = 0;
			boolean multiValueMapFlag 	=false;
			boolean CSiValueMapFlag 	=false;
			List<String> fName			=new ArrayList<>();
			List<String> mName			=new ArrayList<>();
			List<String> lName			=new ArrayList<>();
			List<String> city			=new ArrayList<>();
			List<String> zip			=new ArrayList<>();
			List<String> county			=new ArrayList<>();
			List<String> LegalName		=new ArrayList<>();
			List<String> FullName		=new ArrayList<>();
			ArrayList<Integer> frstPmtInMnths	=new ArrayList<>();
			ArrayList<Integer> NumOfIOPmts		=new ArrayList<>();
			ArrayList<Integer> loanTermMnths	=new ArrayList<>();
			ArrayList<String> pmtStrDte			=new ArrayList<>();
			ArrayList<String> PrinPmtStrDte		=new ArrayList<>();
			ArrayList<String> AdvExpDate		=new ArrayList<>();
			ArrayList<String> pmtFreq			=new ArrayList<>();

			int j=0;
			int matDateMnth	=0;

			while (rowIterator.hasNext()) {
				Row row =  rowIterator.next();
				int valueColumns = row.getPhysicalNumberOfCells();
				String key = row.getCell(0).getStringCellValue().trim();
//				System.out.println("Key: "+ key);
				if (key.equalsIgnoreCase("Entities:")
						|| key.equalsIgnoreCase("Related Entities:")
						|| key.equalsIgnoreCase("Facilities:")
						|| key.equalsIgnoreCase("Collaterals::")
						|| key.equalsIgnoreCase("ECM Documents:")) {
					multiValueMapFlag = true;
				}else if (key.equalsIgnoreCase("Deal info")){
					multiValueMapFlag = false;

				}

				ArrayList<String> cellValues = new ArrayList<String>();
				if (multiValueMapFlag) {
					for (int i = 0; i<valueColumns; i++) {
						if (i==0) {
							continue;
						}
						j = i-1;

						if (key.equalsIgnoreCase("Entity Type")) {
							if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase("Individual")) {
								IndEntTypeFlag.add(j, true);
							}else{
								IndEntTypeFlag.add(j, false);
							}
//							System.out.println("Entity Type: "+ row.getCell(i).getStringCellValue());
//							System.out.println("IndEntTypeFlag: "+ IndEntTypeFlag);
						}else if (key.equalsIgnoreCase("First Name")) {
							if (IndEntTypeFlag.get(j)) {
								if (!CBDataUsedBool.get(0) && AgFastComplx) {
									fName.add(j, CBData.get(0));
									CBDataUsedBool.add(0, true);
								} else {
									fName.add(j, QueryGetRandomFirstName());
								}
							}else{
								fName.add(j, "N/A");
							}
							row.getCell(i).setCellValue(fName.get(j));
						}else if (key.equalsIgnoreCase("Last Name")) {
							if (IndEntTypeFlag.get(j)) {
								if (!CBDataUsedBool.get(2) && AgFastComplx) {
									lName.add(j, CBData.get(2));
									CBDataUsedBool.add(2, true);
								}else {
									lName.add(j, GetRandomString(7));
								}
							}else{
								lName.add(j, "N/A");
							}
							row.getCell(i).setCellValue(lName.get(j));
						}else if (key.equalsIgnoreCase("Middle Name")) {
							if (IndEntTypeFlag.get(j)) {
								if (!CBDataUsedBool.get(1) && AgFastComplx) {
									mName.add(j, CBData.get(1));
									CBDataUsedBool.add(1, true);
								}else {
									mName.add(j, GetRandomString(1));
								}
							}else{
								mName.add(j, "N/A");
							}
							row.getCell(i).setCellValue(mName.get(j));
						}else if (key.equalsIgnoreCase("Legal Name")) {
							if (IndEntTypeFlag.get(j)) {
								LegalName.add(j,fName.get(j) +" "+ mName.get(j) +" "+ lName.get(j));
							}else{
								LegalName.add(j, GetRandomString(7) +" Corp");
							}
							row.getCell(i).setCellValue(LegalName.get(j));
						}else if (key.equalsIgnoreCase("Full Name")) {

							if (IndEntTypeFlag.get(j)) {
								FullName.add(j, lName.get(j) +", "+ fName.get(j) +" "+ mName.get(j));
							}else{
								FullName.add(j, LegalName.get(j));
							}
							row.getCell(i).setCellValue(FullName.get(j));
						}else if (key.equalsIgnoreCase("SSN")) {
							String strSSN		=null;
							if (IndEntTypeFlag.get(j)) {
								if (!CBDataUsedBool.get(3) && AgFastComplx) {
									strSSN = CBData.get(3);
									strSSN = strSSN.substring(0, 3) + "-"+ strSSN.substring(3, 5) + "-"+ strSSN.substring(5, 9);
									CBDataUsedBool.add(3, true);
								}else {
									strSSN = GetRandomStringWithNumbers(3)+ "-"+ GetRandomStringWithNumbers(2)+ "-"+ GetRandomStringWithNumbers(4);

								}
								row.getCell(i).setCellValue(strSSN);

							}else{
								strSSN ="N/A";
								row.getCell(i).setCellValue(strSSN);
							}

						}else if (key.equalsIgnoreCase("Tax ID #")) {
							String strTaxID =null;
							if (!IndEntTypeFlag.get(j)) {
								strTaxID = GetRandomStringWithNumbers(2)+ "-"+ GetRandomStringWithNumbers(7);

							}
							row.getCell(i).setCellValue(strTaxID);
//							System.out.println("strTaxID: "+ strTaxID);
						}else if (key.equalsIgnoreCase("Address 1")) {
							String rndStrtAddr;
							if (!CBDataUsedBool.get(4) && AgFastComplx && IndEntTypeFlag.get(j)) {
								rndStrtAddr = CBData.get(4);
								CBDataUsedBool.add(4, true);
							} else {
								rndStrtAddr = GetRandomStringWithNumbers(4) + " " + GetRandomString(7);
							}
							row.getCell(i).setCellValue(rndStrtAddr);

						}else if (key.equalsIgnoreCase("State")) {
							row.getCell(i).setCellValue(bankState);
						}else if (key.equalsIgnoreCase("City")) {
							String strCity = null;
							String[] strArr;
							if (!CBDataUsedBool.get(5) && AgFastComplx && IndEntTypeFlag.get(j)) {
								city.add(j,CBData.get(5));
								zip.add(j, CBData.get(7));
								county.add(j, "N/A");
								strCity = CBData.get(5);
								CBDataUsedBool.add(5, true);
							} else {
								String str = QueryGetCityZipCounty();
								strArr= str.split("-");
								city.add(j, strArr[0]);
								zip.add(j, strArr[1]);
								county.add(j, strArr[2]);
								strCity = city.get(j);
							}
							row.getCell(i).setCellValue(strCity);
						}else if (key.equalsIgnoreCase("Zip Code")) {
							if (!CBDataUsedBool.get(7) && AgFastComplx && IndEntTypeFlag.get(j)) {
								row.getCell(i).setCellValue(CBData.get(7));
								CBDataUsedBool.add(7, true);
							}else{
								row.getCell(i).setCellValue(zip.get(j));
							}

						}else if (key.equalsIgnoreCase("County")) {
							if (!CBDataUsedBool.get(6) && AgFastComplx && IndEntTypeFlag.get(j)) {
								row.getCell(i).setCellValue("N/A");
								CBDataUsedBool.add(6, true);
							}else {
								row.getCell(i).setCellValue(county.get(j));
							}
						}else if (key.equalsIgnoreCase("Alternate Address")) {
							if (row.getCell(1).getStringCellValue().trim().equalsIgnoreCase("Yes")) {
								AltAddrBool = true;
							}
						}else if (key.equalsIgnoreCase("Alt. Address 1")) {
							if (AltAddrBool) {
								row.getCell(i).setCellValue(GetRandomStringWithNumbers(4) + " " + GetRandomString(7));
							}else{
								row.getCell(i).setCellValue("N/A");
							}
						}else if (key.equalsIgnoreCase("Alt. Address State")) {
							if (AltAddrBool) {
								row.getCell(i).setCellValue(bankState);
							}else{
								row.getCell(i).setCellValue("N/A");
							}
						}else if (key.equalsIgnoreCase("Alt. Address City")) {
							String str = QueryGetCityZipCounty();
							String[] strArr= str.split("-");
							city.add(j, strArr[0]);
							zip.add(j, strArr[1]);
							county.add(j, strArr[2]);
							if (AltAddrBool) {
								row.getCell(i).setCellValue(city.get(j));
							}else{
								row.getCell(i).setCellValue("N/A");
							}
						}else if (key.equalsIgnoreCase("Alt. Address Zip")) {
							if (AltAddrBool) {
								row.getCell(i).setCellValue(zip.get(j));
							}else{
								row.getCell(i).setCellValue("N/A");
							}
						}else if (key.equalsIgnoreCase("Alt. Address County")) {
							if (AltAddrBool) {
								row.getCell(i).setCellValue(county.get(j));
							}else{
								row.getCell(i).setCellValue("N/A");
							}
						}else if (key.equalsIgnoreCase("Primary Phone Number")) {
							row.getCell(i).setCellValue("("+ GetRandomStringWithNumbers(3) +") "+ GetRandomStringWithNumbers(3)+ "-"+ GetRandomStringWithNumbers(4));
						}else if (key.equalsIgnoreCase("Loan Effective Date")) {
							if (CurrentLIQDate) {
								row.getCell(i).setCellValue(TargetCloseDate);
							}
						}else if (key.equalsIgnoreCase("Payment Frequency")) {
							pmtFreq.add(row.getCell(i).getStringCellValue().trim());
						}else if (key.equalsIgnoreCase("First Payment (in Months)")) {
							if (CurrentLIQDate) {
								frstPmtInMnths.add(j, (int) row.getCell(i).getNumericCellValue());
							}
						}else if (key.equalsIgnoreCase("Loan Term Months")) {
							if (CurrentLIQDate) {
								loanTermMnths.add(j, (int) row.getCell(i).getNumericCellValue());
							}
						}else if (key.equalsIgnoreCase("No. of Interest Only Pmnts")) {
							if (CurrentLIQDate) {
								NumOfIOPmts.add(j, (int) row.getCell(i).getNumericCellValue());
							}
						}else if (key.equalsIgnoreCase("Payment Start Date")) {
							if (CurrentLIQDate) {
								pmtStrDte.add(j, AddMonthsToDateSetFirstDayOfMonth(TargetCloseDate, frstPmtInMnths.get(j)));
								row.getCell(i).setCellValue(pmtStrDte.get(j));
							}
						}else if (key.equalsIgnoreCase("Principal Payment Start Date")) {
							if (CurrentLIQDate) {
								PrinPmtStrDte.add(j, AddMonthsToDateSetFirstDayOfMonth(pmtStrDte.get(j), NumOfIOPmts.get(j)));
								row.getCell(i).setCellValue(PrinPmtStrDte.get(j));
							}
						}else if (key.equalsIgnoreCase("Advance Expiration Date")) {
							if (CurrentLIQDate) {
								AdvExpDate.add(j, AddMonthsToDateSetFirstDayOfMonth(PrinPmtStrDte.get(j), -1));
								row.getCell(i).setCellValue(AdvExpDate.get(j));
							}
						}else if (key.equalsIgnoreCase("Maturity Date")) {
							if (pmtFreq.get(j).equalsIgnoreCase("ANN")) {
								matDateMnth = 12;
							} else if (pmtFreq.get(j).equalsIgnoreCase("MON")) {
								matDateMnth = 1;
							} else if (pmtFreq.get(j).equalsIgnoreCase("QUA")) {
								matDateMnth = 3;
							} else if (pmtFreq.get(j).equalsIgnoreCase("SEM")) {
								matDateMnth = 6;
							}
							if (CurrentLIQDate) {
								String maturDate = AddMonthsToDate(pmtStrDte.get(j), loanTermMnths.get(j)-matDateMnth);
								row.getCell(i).setCellValue(maturDate);
							}
						}
						else if (key.equalsIgnoreCase("Collateral Address")) {
							row.getCell(i).setCellValue(GetRandomStringWithNumbers(4) + " " + GetRandomString(7));
						}else if (key.equalsIgnoreCase("Collateral State")) {
							row.getCell(i).setCellValue(bankState);
						}else if (key.equalsIgnoreCase("Collateral City")) {
							String str = QueryGetCityZipCounty();
							String[] strArr= str.split("-");
							city.add(j, strArr[0]);
							zip.add(j, strArr[1]);
							county.add(j, strArr[2]);
							row.getCell(i).setCellValue(city.get(j));
						}else if (key.equalsIgnoreCase("Collateral Zip Code")) {
							row.getCell(i).setCellValue(zip.get(j));
						}else if (key.equalsIgnoreCase("Collateral County")) {
							row.getCell(i).setCellValue(county.get(j));
						}
					}

				} else {
					if (key.equalsIgnoreCase("Environment")) {
						row.getCell(1).setCellValue(EnvName);
					}else if (key.equalsIgnoreCase("Bank")) {
						row.getCell(1).setCellValue(bankNumber);
					}else if (key.equalsIgnoreCase("Process Center")) {
						row.getCell(1).setCellValue(Association);
						System.out.println("Association "+Association);
					}else if (key.equalsIgnoreCase("Branch")) {
						row.getCell(1).setCellValue(BranchCode);
						System.out.println("BranchCode "+BranchCode);
					}else if (key.equalsIgnoreCase("Loan IQ System Date")) {
						if (!row.getCell(1).getStringCellValue().trim().equalsIgnoreCase("Current")) {
							CurrentLIQDate = false;
						}
					}else if (key.equalsIgnoreCase("Application Date")) {
						if (CurrentLIQDate) {
							String appDate = AddDaysToDate(TargetCloseDate, -30);
							row.getCell(1).setCellValue(appDate);
							System.out.println("appDate "+appDate);
						}
					}else if (key.equalsIgnoreCase("Application Completed Date")) {
						if (CurrentLIQDate) {
							String appComplDate = AddDaysToDate(TargetCloseDate, -15);
							row.getCell(1).setCellValue(appComplDate);
							System.out.println("appComplDate "+appComplDate);
						}
					}else if (key.equalsIgnoreCase("Target Close date")) {
						if (CurrentLIQDate) {
							row.getCell(1).setCellValue(TargetCloseDate);
							System.out.println("TargetCloseDate "+TargetCloseDate);
						}
					}else if (key.equalsIgnoreCase("Deal Complexity")) {
						if (row.getCell(1).getStringCellValue().trim().toLowerCase().contains("agfast")) {
							QueryGetCBData();
							AgFastComplx = true;

						}else {
							AgFastComplx = false;
							traditionalComplx = true;
						}
						for (int b=0; b<=8; b++) {
							CBDataUsedBool.add(b, false);
						}
					}else if (key.equalsIgnoreCase("LUS Type")) {
						row.getCell(1).setCellValue(LUSType);
						System.out.println("LUSType "+ LUSType);
					}else if (key.equalsIgnoreCase("Primary Loan Officer")) {
						row.getCell(1).setCellValue(LoanOfficer.get(0));
						System.out.println("Loan Officer "+ LoanOfficer.get(0));
					}else if (key.equalsIgnoreCase("Primary Loan Officer Name")) {
						row.getCell(1).setCellValue(LoanOfficer.get(1)+ " " +LoanOfficer.get(2));
					}else if (key.equalsIgnoreCase("Sub Industry")) {
						row.getCell(1).setCellValue(SubIndustry);
					}else if (key.equalsIgnoreCase("Appraiser")) {
						row.getCell(1).setCellValue(QueryGetAppraiser());
					}else if (key.equalsIgnoreCase("Loan Admin")) {
						row.getCell(1).setCellValue(QueryGetLoanAdmin());
					}else if (key.equalsIgnoreCase("Credit Bureau for AgFast")) {
						String CB = CBData.get(8);
						if (CB.equalsIgnoreCase("Transunion")) {
							CB = "TransUnion";
						}
						row.getCell(1).setCellValue(CB);
					}
				}
			}

			fs.close();
			FileOutputStream outFile =new FileOutputStream(new File(testDatafilePath));
			workbook.write(outFile);
			outFile.close();
		} catch (Exception e) {
			Reporter.fail("Generating test data "+ e.getMessage());
			Assert.fail(e.getMessage());
		}
		return currentRowData;
	}


	/**
	 * Author: Muzaffar A.
	 * Description: Opens excel test data file in a given path and run the macro to generate new dataset
	 * @param xlPath
	 */
	public void RunMacrosInExcel(String xlPath){
		File file = new File(xlPath);
		String absPath = file.getAbsolutePath();
		File vbsFile = new File("src/test/resources/testData/RunMacrosXL.vbs");
		Reporter.info("Running VBS file location: "+ vbsFile.getAbsolutePath());
		Reporter.info("Generating test data in file: "+ absPath);
		try {
			Runtime.getRuntime().exec("cscript "+ vbsFile.getAbsolutePath()+ " "+ absPath).waitFor();

		} catch (Exception e) {
			Reporter.info("Running RunMacrosXL.vbs "+ e.getMessage());
			Assert.fail("Running RunMacrosXL.vbs "+ e.getMessage());
		}

	}


	/**
	 *Author: Muzaffar A.
	 * Description: Returns number of rows for given table
	 */
	public String GetNumberOfRowsOfTable(Locator locator)
	{
		WebElement table =this.driver().findElement(locator.getBy());
		WebElement tbody=table.findElement(By.tagName("tbody"));
		List<WebElement> rows=tbody.findElements(By.tagName("tr"));

		return Integer.toString(rows.size());
	}
	public int GetRowNumberWithCellText(String tableXpath, String cellText){
		int rowNum=0;
		driver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			int numOfRows = this.driver().findElements(By.xpath(tableXpath + "//tr")).size();
			for (int j = 1; j <= numOfRows; j++) {
				List<WebElement> Cells = this.driver().findElements(By.xpath(tableXpath + "//tr[" + j + "]/td"));
				for (WebElement cell : Cells) {
					if (cell.getText().equalsIgnoreCase(cellText)) {
						rowNum = j;
						break;
					}
				}
			}
		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail(e.getMessage());
		}
		return rowNum;
	}

	//Handles screen information has not yet saved, do you wish to go back and save it
	public void ScreenInfoNotYetSaved(String YesNo) {
		InformationPopUps("Screen information has not yet been saved, do you wish to go back and save it?", YesNo);
	}

	//Handles Liquid credit pop-up There is no Business Entity to be the Main Borrowing Entity.
	public void LiquidCreditAlertMessage(String YesNo) {
		InformationPopUps("There is no Business Entity to be the Main Borrowing Entity.", YesNo);
	}
	// Handles Credit report requested if it includes last 30 days
	public void CreditReportRequested(String YesNo) {
		InformationPopUps("A credit report has been requested for the following SSN within the last 30 days", YesNo);
	}
	public boolean isNumeric(Locator locator,String attrName) {
		String fldName = getElementAttribute(locator, attrName);
		if (fldName == null) {
			Reporter.fail("field is empty");
		}
		try {

			int i = Integer.parseInt(fldName);
			//	Reporter.pass("Verifying value is numeric " +i);
			//	boolean boolFlag = true;
			return true;
		} catch (NumberFormatException nfe) {
			//	Assert.fail("Given field is not numeric");
			return false;
		}

	}
	/**
	 * Author: Muzaffar A.
	 * Description: Logs in to Optimist login prompt
	 */
	public void OptimistLogin(){

//		inputText(DealModelsPage.userName, "mabduholikov0418");
//		inputText(DealModelsPage.password, "SamiyaYasmina@122021");
		inputText(DealModelsPage.userName, UserAccount);
		inputText(DealModelsPage.password, UserAccountPwd);


		sleep(500);
		if (isWebElementVisible(AuthorizationPage.AuthorizationScrn)) {
			click(yesBtn);
		} else {
			click(OKBtn);
		}
		waitForInVisibile(PleaseWait);
	}
	public void AuthorizeLogin(){
//		inputText(DealModelsPage.userName, "SVCCMETESTUSER01");
//		inputText(DealModelsPage.password, "FVcmeTestingAuto01");
		inputText(DealModelsPage.userName, UserAccount);
		inputText(DealModelsPage.password, UserAccountPwd);
		sleep(500);
		click(yesBtn);

		waitForInVisibile(PleaseWait);
	}

	/**
	 * Author: Muzaffar A
	 * Description: Launches Optimist with existing model
	 */
	public void LaunchOptimistWithExistingModel(String portfolio){
		start = System.currentTimeMillis();
		VerifyScreenName(DealModelsPage.dealModelsScrn);
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start)/1000;
		PerfMetrics.add(new String[] { Activity, "Deal Models Screen (After Optimist Login)", String.valueOf(timeElapsed) });
		inputTextUsingJS(DealModelsPage.searchType, "Customer Name");
		pressTabKey(DealModelsPage.searchType);
		sleep(500);
		if (dataMap.get("Environment").equalsIgnoreCase("FBBUILD") || dataMap.get("Environment").equalsIgnoreCase("FBLASSN")) {
			inputTextUsingJS(DealModelsPage.searchValue, "VFAWDIZZ, ARTHUR I");
		} else if (dataMap.get("Environment").equalsIgnoreCase("FBLTRAIN")) {
			inputTextUsingJS(DealModelsPage.searchValue, "FPKGNSJJ, TRISHA I");
		}else if (dataMap.get("Environment").equalsIgnoreCase("FBLLEARN")) {
			inputTextUsingJS(DealModelsPage.searchValue, "ZOURRQL, CHRISTOPHER V");
		}else if (dataMap.get("Environment").equalsIgnoreCase("FBLDATO")) {
			inputTextUsingJS(DealModelsPage.searchValue, "QYYXGJQ, HENRY U");
		}else if (dataMap.get("Environment").equalsIgnoreCase("FBLDATV")) {
			inputTextUsingJS(DealModelsPage.searchValue, "LVQMTYPP, HARRISON S");
		}else {
			clearInput(DealModelsPage.searchValue);
			sleep(500);
			inputTextUsingJS(DealModelsPage.searchValue, "AUTOEVVPIV, MABDZTEZUB A");
		}
		sleep(1000);
		click(searchBtn);
		sleep(1000);
		waitForInVisibile(PleaseWait);
		sleep(1000);
		waitForInVisibile(PleaseWait);
		if (!isWebElementVisible(DealModelsPage.NoRecordsFound)) {
			sleep(500);
			click(DealModelsPage.launchBtn); //Clicking Launch Button
			waitForVisible(DealModelsPage.selectPortfolio);
			sleep(1000);
			clickGridBoxCellElement(portfolio);
			sleep(500);
			click(OKBtn);
			start = System.currentTimeMillis();
			Reporter.addScreenshot("Optimist Portfolio", "Optimist Portfolio");
//			if (isElementExist(DealModelsPage.loadHTML5Version, 20)) {
//				sleep(1000);
//				click(NoBtn);
//			}
			waitForInVisibile(PleaseWait);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Launch Optimist", String.valueOf(timeElapsed) });
			sleep(10000);
			SwitchWindow(1);
			this.driver().close();
			SwitchWindow(0);
			//this.driver().switchTo().defaultContent();
			SwitchToCMEFrame();
			sleep(1000);
			click(DealModelsPage.refreshBtn);
			if (isElementExist(DealModelsPage.selectStatements, 60)) {
				sleep(1000);
				clickJS(DealModelsPage.selectStatementsChkbx);
				sleep(1000);
				click(OKBtn);
				start = System.currentTimeMillis();
				sleep(1000);
				waitForInVisibile(PleaseWait);
				finish = System.currentTimeMillis();
				timeElapsed = (finish - start)/1000;
				PerfMetrics.add(new String[] { Activity, "Refresh - Deal Models", String.valueOf(timeElapsed) });
			} else {
				Reporter.fail("Select Statements window not found");
			}

		} else {
			Reporter.fail("Customer Not Found");
			Assert.fail("Customer Not Found");
		}
	}
	/**
	 * Author: Muzaffar A
	 * Description:
	 */
	public void VerifyPDF(){
		waitForPageLoaded();
		sleep(2000);
		String url = this.driver().getCurrentUrl();

		if (url.toLowerCase().contains(".pdf")) {
			Reporter.pass("PDF File opened in new tab");
		} else {
			Reporter.fail("PDF File opened in new tab");
		}
		Reporter.addScreenshot("PDF File", "PDF File");


//		try {
//			String pdfContent = readPdfContent(url);
//			System.out.println(pdfContent);
//			Assert.assertTrue(pdfContent.contains("AMORTIZATION SCHEDULE"));
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public String readPdfContent(String url) throws IOException {
		URL pdfUrl = new URL(url);
		InputStream in = pdfUrl.openStream();
		BufferedInputStream bf = new BufferedInputStream(in);
		PDDocument doc = PDDocument.load(bf);
		String content = new PDFTextStripper().getText(doc);
		doc.close();

		return content;
	}

	public void SwitchWindow(int windIndx){
		sleep(2000);
		ArrayList<String> tabs = new ArrayList<String> (this.driver().getWindowHandles());
		this.driver().switchTo().window(tabs.get(windIndx));
	}
	public void LoadDealByApplicationNumber(String dealAls, String actStatus){
		Reporter.info("User loads the deal in Activity: "+ actStatus);
		Activity = actStatus;
		clearInput(AppSearchBox);
		sleep(500);
		inputText(AppSearchBox, dealAls);
		sleep(500);
		pressEnter(AppSearchBox);

		if (isElementExist(NoDealsFound, 2)) {
			Reporter.fail("Application Not Found");
			Assert.fail("Application Not Found");
		}
		waitForInVisibile(PleaseWait);
		clickGridBoxCellElement(dealAls);
		sleep(500);
		click(OKBtn);
		start = System.currentTimeMillis();
		sleep(1000);
		if (actStatus.equalsIgnoreCase("Pre-Closing") && bankNumber.equalsIgnoreCase("001")) {
			PreClosingPopup("Yes");
		}else if (actStatus.equalsIgnoreCase("Credit Approved")) {
			ClickElementOnPleaseWait(DialogOKbtn);
		}else if (actStatus.equalsIgnoreCase("Application")) {
			if (isElementExist(GetLocator("//div[@fieldname='Do you accept receipt of this new activity?']"), 5)) {
				click(yesBtn);
			}
		}
		String loadedAppNum = getApplicationNumber();

		try {
			if (loadedAppNum.equalsIgnoreCase(dealAls)) {
				if (getElementText(DealStatus).contains(actStatus)) {
					Reporter.pass("Verifying Deal Status - "+ actStatus);
					finish = System.currentTimeMillis();
					timeElapsed = (finish - start)/1000;
					PerfMetrics.add(new String[] { Activity, "Loading Application from Dashboard", String.valueOf(timeElapsed) });
				}else {
					Reporter.fail("Verifying Deal Status - " + actStatus);
					Assert.fail("Verifying Deal Status - " + actStatus);
				}
			} else {
				Reporter.fail("Application number found");
			}

		} catch (Exception e) {
			Assert.fail("Loading Application "+e.getMessage());
		}
	}

	public String GetRandomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int)
					(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;

	}

	public String GetBranch(){
		switch(bankNumber) {

			case "001":
				Branch = "001 Capital Farm Credit, ACA";
				break;
			case "003":
				Branch = "003 Agriland, Farm Credit Services";
				break;
			case "005":
				Branch = "005 Lone Star, ACA";
				break;
			case "006":
				Branch = "006 AgTexas Farm Credit Services, ACA";
				break;
			case "007":
				Branch = "007 AgCredit of South Texas, ACA";
				break;
			case "008":
				Branch = "008 Texas Farm Credit Services";
				break;
			case "009":
				Branch = "009 Great Plains Ag Credit, ACA";
				break;
			case "011":
				Branch = "011 Plains Land Bank, FLCA";
				break;
			case "013":
				Branch = "013 Central Texas Farm Credit, ACA";
				break;
			case "014":
				Branch = "014 Legacy AgCredit, ACA";
				break;
			case "015":
				Branch = "015 Texas Land Bank, ACA";
				break;
			case "016":
				Branch = "016 Louisiana Land Bank, ACA";
				break;
			case "017":
				Branch = "017 Mississippi Land Bank, ACA";
				break;
			case "018":
				Branch = "018 Southern AgCredit, ACA";
				break;
			case "019":
				Branch = "019 Alabama Farm Credit";
				break;
			case "020":
				Branch = "020 Alabama Ag Credit";
				break;
			case "022":
				Branch = "022 Ag New Mexico, Farm Credit Services, ACA";
				break;
			case "023":
				Branch = "023 Louisiana Ag Credit, ACA";
				break;
			case "110":
				Branch = "110 Farm Credit Bank of Texas";
				break;

			default:
				throw new IllegalStateException("Unexpected value: " + bankNumber);
		}
		return Branch;
	}

	public String GetExpenseCode(){
		String expenseCode;
		switch(dataMap.get("Branch")) {
			case "011-100":
				expenseCode = "011-100 Central Office";
				break;
			case "001-120":
				expenseCode = "001-120 Bryan";
				break;
			case "019-100":
				expenseCode = "019-100 Cullman Administrative Office";
				break;
			case "014-101":
				expenseCode = "014-101 Gilmer Office";
				break;
			case "017-100":
				expenseCode = "017-100 Senatobia Admin Office";
				break;
			case "013-100":
				expenseCode = "013-100 Administrative Office";
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + dataMap.get("Branch"));
		}
		return expenseCode;
	}
	public String GetFBLExpenseCode(){
		String expenseCode;
		switch(dataMap.get("Branch")) {
			case "011-100":
				expenseCode = "011-100 - Central Office";
				break;
			case "001-120":
				expenseCode = "001-120 - Bryan";
				break;
			case "017-100":
				expenseCode = "017-100 - Senatobia Admin Office";
				break;
			case "019-100":
				expenseCode = "019-100 - Cullman Administrative Office";
				break;
			case "014-101":
				expenseCode = "014-101 - Gilmer Office";
				break;
			case "013-100":
				expenseCode = "013-100 - Administrative Office";
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + dataMap.get("Branch"));
		}
		return expenseCode;
	}
	public String  InitializeBankRelatedData(){

		switch(bankNumber) {
			case "001":
				locationCode	="BRYAN: Bryan, TX United States";
				bankState		="TX";
				break;
			case "011":
				locationCode 	="AMA: Amarillo, TX";
				bankState		="TX";
				break;
			case "019":
				locationCode	="CULL: Cullman, AL United States";
				bankState		="AL";
				break;
			case "013":
				locationCode	="CLMN: Coleman, TX United States";
				bankState		="TX";
				break;
			case "016":
				locationCode	="";
				bankState		="LA";
				break;
			case "017":
				locationCode	="SENA: Senatobia, MS United States";
				bankState		="MS";
				break;
			case "020":
				locationCode	="";
				bankState		="AL";
				break;
			case "022":
				locationCode	="";
				bankState		="NM";
				break;
			case "023":
				locationCode	="023";
				bankState		="LA";
				break;
			default:
//				throw new IllegalStateException("Unexpected value: " + bankNumber);
		}
		return locationCode;

	}
	public String GetBusinessClassification(int i){
		String classification;
		switch(multiValueMap.get("Entity Structure").get(i)) {
			case "Individual":
				classification = "Individual";
				break;
			case "Corporation":
				classification = "Domestic Corporation";
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + multiValueMap.get("Entity Structure").get(i));
		}
		return classification;
	}

	public ArrayList GetCollaterals(){
		ArrayList <String> collats= new ArrayList<String>();
		List <WebElement> collatEls = this.driver().findElements(By.xpath("//span[@fieldname='Collateral']/following-sibling::span/div[@class='treeRow']"));
		int numOfCollats = collats.size();
		for (int i=0; i<numOfCollats; i++){
			if (i==0 || i==(numOfCollats-1)) {
				continue;
			}
			collats.add(i-1, collatEls.get(i).getText());

		}
		return collats;

	}

	public String VerifyEBDBTabsindicator(Locator locator) {
		this.waitForVisible(locator);
		return getElementAttribute(locator,"uib-tooltip");

	}

	/**
	 *Author: Padmini B.
	 * Description: Returns cell value based on Row number and Column
	 */
	public String GetCellValue(int ROW_NUM,int COL_NUM,Locator locator){
		WebElement table =this.driver().findElement(locator.getBy());
		WebElement tbody=table.findElement(By.tagName("tbody"));
		List<WebElement> rows=tbody.findElements(By.tagName("tr"));
		List<WebElement> colns = rows.get(ROW_NUM).findElements(By.tagName("td"));
		String cellValue= colns.get(COL_NUM).getText();
		return cellValue;
	}

	public String GetFBLBranch(){
		switch(bankNumber) {
			case "011":
				Branch = "011 - Plains Land Bank";
				break;
			case "001":
				Branch = "001 - Capital Farm Credit";
				break;
			case "013":
				Branch = "013 - Central Texas Farm Credit";
				break;
			case "017":
				Branch = "017 - Mississippi Land Bank";
				break;
			case "019":
				Branch = "019 - Alabama Farm Credit";
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + bankNumber);
		}
		return Branch;
	}
	public String GetFBLDepartment(){
		switch(bankNumber) {
			case "011":
				Department = "011 - Plains Land Bank, FLCA";
				break;
			case "001":
				Department = "001 - Capital Farm Credit, ACA";
				break;
			case "013":
				Department = "013 - Central Texas Farm Credit";
				break;
			case "017":
				Department = "017 - Mississippi Land Bank";
				break;
			case "019":
				Department= "019 - Alabama Farm Credit";
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + bankNumber);
		}
		return Department;
	}
	public String GetLenderLocationCode() {
		//bankNumber = dataMap.get("Bank");
		switch (bankNumber) {
			case "011":
				locationCode = "Amarillo, TX";
				break;
			case "001":
				locationCode = "Bryan, TX United States";
				break;
			case "017":
				locationCode = "Senatobia, MS United States";
				break;
			case "019":
				locationCode = "Cullman, AL United States";
				break;
			case "013":
				locationCode = "Coleman, TX United States";
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + lenderCode);
		}
		return locationCode;
	}

	public String GetProfileLocations(){
		switch(bankNumber) {
			case "011":
				ProfileLocation = "Amarillo, TX";
				break;
			case "001":
				ProfileLocation= "Bryan, TX United States";
				break;
			case "017":
				ProfileLocation = "Senatobia, MS United States";
				break;
			case "019":
				ProfileLocation = "Cullman, AL United States";
				break;
			case "013":
				ProfileLocation = "Coleman, TX United States";
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + bankNumber);
		}
		return ProfileLocation;
	}

	public String GetRiskBook(){
		switch(bankNumber) {
			case "011":
				RiskBook = "Plains Land Bank, FLCA";
				break;
			case "001":
				RiskBook = "Capital Farm Credit, ACA";
				break;
			case "017":
				RiskBook = "Mississippi Land Bank, ACA";
				break;
			case "019":
				RiskBook = "Alabama Farm Credit";
				break;
			case "013":
				RiskBook = "Central Texas Farm Credit, ACA";
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + RiskBook);
		}
		return RiskBook;
	}

	public String GetPortfolioExpenseCode(){

		String holdForInvStr = "Hold for Investment - Non Taxable - ";
		for (int j=0; j<numOfLoans; j++){
			if (multiValueMap.get("Institution Type").get(j).equalsIgnoreCase("PCA")) {
				holdForInvStr = "Hold for Investment - Taxable - ";
			}
		}
		switch(bankNumber) {
			case "011":
				PortfolioExpenseCode = holdForInvStr+ "011-100 Central Office";
				break;
			case "001":
				PortfolioExpenseCode = holdForInvStr+ "001-120 Bryan";
				break;
			case "013":
				PortfolioExpenseCode = holdForInvStr+ "013-102 Coleman Office";
				break;
			case "017":
				PortfolioExpenseCode = holdForInvStr+ "017-100 Senatobia Admin Office";
				break;
			case "019":
				PortfolioExpenseCode = holdForInvStr+ "019-100 Cullman Administrative Office";
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + PortfolioExpenseCode);
		}
		return PortfolioExpenseCode;
	}

	public String GetLenderCode(){
		switch(bankNumber) {
			case "011":
				lenderCode = "PLAINS LAND BANK, FLCA-011";
				break;
			case "001":
				lenderCode = "Bryan, TX United States";
				break;
			case "013":
				lenderCode = "CENTRAL TEXAS FC, ACA-013";
				break;
			case "017":
				lenderCode = "MISSISSIPPI LAND BANK, ACA-017";
				break;
			case "019":
				lenderCode = "";
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + lenderCode);
		}
		return lenderCode;
	}
	public String GetFacilityType(String prodType) {
		switch (prodType) {
			case "Term":
				FacilityType = "TAM";
				break;
			case "Revolver To Term":
				FacilityType = "RTE";
				break;
			case "Revolver":
				FacilityType = "";
				break;
			case "Operating Master":
				FacilityType = "MST";
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + FacilityType);
		}
		return FacilityType;
	}

	public void ScrollDown(){
		Actions action = new Actions(this.driver());
		sleep(1000);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		sleep(1000);
	}

		public void ScrollUp(){
			Actions action = new Actions(this.driver());
			sleep(1000);
			action.sendKeys(Keys.PAGE_UP).build().perform();
			sleep(1000);
		}

	public void CheckCMESystemExceptionError(){
		boolean found = false;
		int count = 0;
		driver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		do {
			if (count==100) {
				break;
			}
			sleep(500);
			List<WebElement> foundEls = this.driver().findElements(By.xpath("//div[@class='label' and contains(text(), 'Please Wait')]"));
			if (foundEls.size() > 0) {
				FetchCMESystemExceptionError();
				found = true;

			} else {
				FetchCMESystemExceptionError();
				found = false;
			}
			count++;

		} while (found);
	}

	public void FetchCMESystemExceptionError(){
		try {

			if (isWebElementVisible(SystemException)) {
				WebElement ShowDetailsBtn = this.driver().findElement(By.xpath("//a[@fieldname='Show Details >>']"));
				sleep(500);
				ShowDetailsBtn.click();
				sleep(500);
				String errMsgXpath = "//div[@fieldname='System Exception']/parent::div[@class='container']//textarea";
				String excMsg = getElementAttribute(GetLocator(errMsgXpath), "value");
				Reporter.fail("System Exception Error: ");
				Assert.fail(excMsg);
				click(OKBtn);
			}

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}


	public String GetPassword(String usrNme){
		String pwd = null;

			switch(usrNme.toUpperCase()) {

			case "SVCCMETESTUSER01":
				pwd = "FVcmeTestingAuto01";
				break;
			case "SVCCMETESTUSER02":
				pwd = "FVcmeTestingAuto02";
				break;
			case "CMETEST14":
				pwd = "jytOSRfXUHU0Su9I";
				break;
			case "MABDUHOLIKOV0418":
				pwd = "SamiyaYasmina%40122021";
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + usrNme);
		}
		return pwd;
	}

	public String QueryGetLIQDate(){
		String liqDate = null;
		String qry = "SELECT Convert(varchar(10), cast(env_txt_var_value as date), 101) AS [Current Business date] FROM [FBLoanIQ].[LS2USER].[TLS_ENVIRONMENT] WHERE ENV_NME_VAR_NAME='Current Business Date' AND env_nme_var_class='Zone1';";
		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println(EnvName + EnvDomain);
			String connectionUrl = "jdbc:sqlserver://"+ EnvName + EnvDomain +":1433;databaseName=FBCME;user=SVCCMETESTUSER01;password=This1is2a3sqllogin"; //integratedSecurity=true";
			Connection con = DriverManager.getConnection(connectionUrl);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(qry);

			// Iterate through the data in the result set and display it.

			while (rs.next()) {
				liqDate = rs.getString(1);
			}
			con.close();
		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}

		return liqDate;
	}

	public void QueryGetLoanOfficer(){

		String qry	="SELECT ao.officer_num, ao.officer_fname, ao.officer_lname "
				.concat("FROM [FBCME].[dbo].[t_ccs_admin_officer]  ao ")
				.concat("join [FBCME].[dbo].[t_ccs_admin_legal_ent] le on le.lgl_ent_id = ao.officer_affil_id ")
				.concat("join [FBLoanIQ].[LS2USER].[VLS_USER_PROFILE] lup on lup.UPT_NME_USER_FIRST = ao.officer_fname And lup.UPT_NME_USER_LAST = ao.officer_lname ")
				.concat("where [officer_num] is not null AND lup.UPT_CDE_JOB_FUNC like '%LO%' and le.lgl_ent_short_name = '" +bankNumber+ "'");
//				.concat("where [officer_num] is not null AND le.lgl_ent_short_name = '" +bankNumber+ "'");


		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://"+ EnvName + EnvDomain +":1433;databaseName=FBCME;user=SVCCMETESTUSER01;password=This1is2a3sqllogin"; //integratedSecurity=true";
			Connection con = DriverManager.getConnection(connectionUrl);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(qry);

			// Iterate through the data in the result set and display it.

			while (rs.next()) {
				LoanOfficer.add(0,rs.getString("officer_num"));
				LoanOfficer.add(1,rs.getString("officer_fname"));
				LoanOfficer.add(2,rs.getString("officer_lname"));
				break;
			}
			con.close();


		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String QueryGetAppraiser(){
		String Apprsr = null;
		String qry	="SELECT e.emp_fname, e.emp_lname "
				.concat("FROM [FBCME].[dbo].[t_ccs_admin_emp] e ")
				.concat("join [FBCME].[dbo].[t_ccs_admin_emp_role_rel] r on r.emp_id = e.emp_id ")
				.concat("join [FBCME].[dbo].[t_ccs_admin_code] c on c.code_id = r.role_id ")
				.concat("join [FBCME].[dbo].[t_ccs_admin_emp_affiliate_rel] er on er.emp_id = e.emp_id ")
				.concat("join [FBCME].[dbo].[t_ccs_admin_legal_ent] le on le.lgl_ent_id = er.lgl_ent_id ")
				.concat("where e.emp_external_yn = 'N' and e.inactive_ind is NULL and c.code_desc = 'Appraiser' and le.lgl_ent_short_name = '" +bankNumber+ "'");
//				.concat(bankNumber);

		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://"+ EnvName + EnvDomain +":1433;databaseName=FBCME;user=SVCCMETESTUSER01;password=This1is2a3sqllogin"; //integratedSecurity=true";
			Connection con = DriverManager.getConnection(connectionUrl);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(qry);

			// Iterate through the data in the result set and display it.

			while (rs.next()) {
				Apprsr = rs.getString("emp_fname") + " " + rs.getString("emp_lname");
				break;
			}
			con.close();
		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}
		return Apprsr;
	}

	public String QueryGetLoanAdmin(){
		String LnAdm = null;
		String qry	="SELECT e.emp_fname, e.emp_lname "
				.concat("FROM [FBCME].[dbo].[t_ccs_admin_emp] e ")
				.concat("join [FBCME].[dbo].[t_ccs_admin_emp_role_rel] r on r.emp_id = e.emp_id ")
				.concat("join [FBCME].[dbo].[t_ccs_admin_code] c on c.code_id = r.role_id ")
				.concat("join [FBCME].[dbo].[t_ccs_admin_emp_affiliate_rel] er on er.emp_id = e.emp_id ")
				.concat("join [FBCME].[dbo].[t_ccs_admin_legal_ent] le on le.lgl_ent_id = er.lgl_ent_id ")
				.concat("where e.emp_external_yn = 'N' and e.inactive_ind is NULL and c.code_desc = 'Loan Admin' and le.lgl_ent_short_name = '" +bankNumber+ "'");

		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://"+ EnvName + EnvDomain +":1433;databaseName=FBCME;user=SVCCMETESTUSER01;password=This1is2a3sqllogin"; //integratedSecurity=true";
			Connection con = DriverManager.getConnection(connectionUrl);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(qry);

			// Iterate through the data in the result set and display it.

			while (rs.next()) {
				LnAdm = rs.getString("emp_fname") + " " + rs.getString("emp_lname");
				break;
			}
			con.close();
		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}

		return LnAdm;
	}

	public String QueryGetRandomFirstName(){
		String fname = null;

		String qry	="SELECT Top 1 [First_Name] FROM [PERF_TEST_RESULTS].[dbo].[CreditBureauTestData] ORDER BY NEWID()";

		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://QASTIMPRDFA04:1433;databaseName=PERF_TEST_RESULTS;user=SVCCMETESTUSER01;password=This1is2a3sqllogin"; //integratedSecurity=true";

			Connection con = DriverManager.getConnection(connectionUrl);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(qry);

			// Iterate through the data in the result set and display it.

			while (rs.next()) {
				fname = rs.getString("First_Name");
				break;
			}
			con.close();
		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}
		return WordUtils.capitalizeFully(fname);
	}

	public void QueryGetCBData(){

		String qry	="SELECT Top 1 First_Name, Middle_Name, Last_Name, Test_Social_Security_Number, Street_Name, City, State, ZIP_Code, CreditBureau "
				.concat("FROM [PERF_TEST_RESULTS].[dbo].[CreditBureauTestData] ")
				.concat("where Test_Social_Security_Number is not null and Middle_Name is not null ")
				.concat("and CreditBureau != 'Experian' and State = '" + bankState + "' ORDER BY NEWID()");

		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://QASTIMPRDFA04:1433;databaseName=PERF_TEST_RESULTS;user=SVCCMETESTUSER01;password=This1is2a3sqllogin";
			Connection con = DriverManager.getConnection(connectionUrl);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(qry);

			// Iterate through the data in the result set and display it. test

			while (rs.next()) {
				CBData.add(0, WordUtils.capitalizeFully(rs.getString("First_Name")));
				CBData.add(1,WordUtils.capitalizeFully(rs.getString("Middle_Name")));
				CBData.add(2,WordUtils.capitalizeFully(rs.getString("Last_Name")));
				CBData.add(3,WordUtils.capitalizeFully(rs.getString("Test_Social_Security_Number")));
				CBData.add(4,WordUtils.capitalizeFully(rs.getString("Street_Name")));
				CBData.add(5,rs.getString("City"));
				CBData.add(6,rs.getString("State"));
				CBData.add(7,rs.getString("ZIP_Code"));
				CBData.add(8,rs.getString("CreditBureau"));
				break;
			}
			con.close();


		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String QueryGetCityZipCounty(){
		String CityZipCounty = null;

		String qry	="SELECT Top 1 City,Zip_Code,County FROM [PERF_TEST_RESULTS].[dbo].[AddressData] where State = '"+ bankState +"' ORDER BY NEWID()";

		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://QASTIMPRDFA04:1433;databaseName=PERF_TEST_RESULTS;user=SVCCMETESTUSER01;password=This1is2a3sqllogin"; //integratedSecurity=true";

			Connection con = DriverManager.getConnection(connectionUrl);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(qry);

			// Iterate through the data in the result set and display it.

			while (rs.next()) {
				CityZipCounty = rs.getString("City");
				CityZipCounty = CityZipCounty +"-"+ rs.getString("Zip_Code");
				CityZipCounty = CityZipCounty +"-"+ rs.getString("County");
				break;
			}
			con.close();
		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}
		return CityZipCounty;
	}

	public String GetRandomString(int len){
		String generatedString = null;
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			int randomLimitedInt = leftLimit + (int)
					(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		generatedString = buffer.toString();

		return WordUtils.capitalizeFully(generatedString);
	}

	public String GetRandomStringWithNumbers(int len){
		String SALTCHARS = "1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < len) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();

		return saltStr;
	}

	public String InitDomain(){
		String domain = null;
		if (EnvName.equalsIgnoreCase("FBLTRAIN") || EnvName.equalsIgnoreCase("FBLDATV") || EnvName.equalsIgnoreCase("FBLASSN")
				|| EnvName.equalsIgnoreCase("FBLLEARN") || EnvName.equalsIgnoreCase("FBBUILD")) {
			domain = "BE.nterprise.net";
		} else {
			domain = "BE.develop.fcbt";
		}

		return domain;
	}

	public void GetBranchCodeLUSTypeSubInd(){
		BranchCode = null;
		switch(bankNumber) {

			case "001":
				Association	="Capital Farm Credit";
				BranchCode 	="001-120";
				LUSType		="Cow Calf";
				bankState	="TX";
				break;
			case "003":
				Association	="Agriland";
				BranchCode 	="003-100";
				LUSType		="Cattle Cow/Calf";
				bankState	="TX";
				break;
			case "005":
				Association	="Lone Star";
				BranchCode 	="005-100";
				LUSType		="";
				bankState	="TX";
				break;
			case "006":
				Association	="AgTexas Farm Credit Services";
				BranchCode 	="006-100";
				LUSType		="Dairy (Market)";
				bankState	="TX";
				break;
			case "007":
				Association	="AgCredit of South Texas";
				BranchCode 	="007-100";
				LUSType		="";
				bankState	="TX";
				break;
			case "008":
				Association	="Texas Farm Credit Services";
				BranchCode 	="008-100";
				LUSType		="";
				bankState	="TX";
				break;
			case "009":
				Association	="Great Plains Ag Credit";
				BranchCode 	="009-100";
				LUSType		="";
				bankState	="TX";
				break;
			case "011":
				Association	="Plains Land Bank";
				BranchCode 	="011-100";
				LUSType		="Core or Retail Loan Portfolio";
				bankState	="TX";
				break;
			case "013":
				Association	="Central Texas Farm Credit";
				BranchCode 	="013-100";
				LUSType		="Dairy (013)";
				bankState	="TX";
				break;
			case "014":
				Association	="Legacy Ag Credit";
				BranchCode 	="014-101";
				LUSType		="Dairy (014)";
				bankState	="TX";
				break;
			case "015":
				Association	="Texas Land Bank";
				BranchCode 	="015-NA";
				LUSType		="";
				bankState	="TX";
				break;
			case "016":
				Association	="Louisiana Land Bank";
				BranchCode 	="016-100";
				LUSType		="Poultry (016)";
				bankState	="LA";
				break;
			case "017":
				Association	="Mississippi Land Bank";
				BranchCode 	="017-100";
				LUSType		="Full Time Farmer (017)";
				bankState	="MS";
				break;
			case "018":
				Association	="Southern AgCredit";
				BranchCode 	="018-100";
				LUSType		="";
				bankState	="TX";
				break;
			case "019":
				Association	="Alabama Farm Credit";
				BranchCode 	="019-100";
				LUSType		="Cow-Calf Producer (019)";
				bankState	="AL";
				break;
			case "020":
				Association	="Alabama Ag Credit";
				BranchCode 	="020-100";
				LUSType		="";
				bankState	="AL";
				break;
			case "022":
				Association	="Ag New Mexico";
				BranchCode 	="022-100";
				LUSType		="";
				bankState	="NM";
				break;
			case "023":
				Association	="Louisiana Ag Credit";
				BranchCode 	="023-100";
				LUSType		="";
				bankState	="LA";
				break;
			case "110":
				Association	="Farm Credit Bank of Texas";
				BranchCode 	="110-853";
				LUSType		="";
				bankState	="TX";
				break;

			default:
				throw new IllegalStateException("Unexpected value: " + bankNumber);
		}
		if (bankNumber.equalsIgnoreCase("017")) {
			SubIndustry = "Mississippi Land - Ag Cash and Personal";
		}else if (bankNumber.equalsIgnoreCase("006")) {
			SubIndustry = "Ag Texas - Ag Cash and Personal";
		}else{

			SubIndustry = Association +" - Ag Cash and Personal";
		}

	}

	public String AddDaysToDate(String sDate, int numDays){
		String newDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		try{
			//Setting the date to the given date
			c.setTime(sdf.parse(sDate));
		}catch(ParseException e){
			e.printStackTrace();
		}

		//Number of Days to add
		c.add(Calendar.DAY_OF_MONTH, numDays);
		//Date after adding the days to the given date
		 newDate = sdf.format(c.getTime());
		return newDate;
	}

	public String AddMonthsToDateSetFirstDayOfMonth(String sDate, int numMonths){
		String newDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		try{
			//Setting the date to the given date
			c.setTime(sdf.parse(sDate));
		}catch(ParseException e){
			e.printStackTrace();
		}

		//Number of Months to add
		c.add(Calendar.MONTH, numMonths);
		if (c.get(Calendar.DAY_OF_MONTH) != 1) {
			c.add(Calendar.MONTH, 1);
			c.set(Calendar.DAY_OF_MONTH, 1);
		}
		//Date after adding the months to the given date
		newDate = sdf.format(c.getTime());
		return newDate;
	}
	public String AddMonthsToDate(String sDate, int numMonths){
		String newDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		try{
			//Setting the date to the given date
			c.setTime(sdf.parse(sDate));
		}catch(ParseException e){
			e.printStackTrace();
		}

		//Number of Months to add
		c.add(Calendar.MONTH, numMonths);
		//Date after adding the months to the given date
		newDate = sdf.format(c.getTime());
		return newDate;
	}

	public void AddApproversInDealTeam() {
		String credApprover =null;
		String reltApprover =null;
		String roleSrch1	=null;
		String roleSrch2	=null;


		if (bankNumber.equalsIgnoreCase("001")) {
			credApprover	="svchubautomation svchubautomation";
			reltApprover	="svcQAAutomation svcQAAutomation";
			roleSrch1		="Credit Approver";
			roleSrch2		="Relationship Approver";
		} else if (bankNumber.equalsIgnoreCase("019")){
			credApprover 	="Raminaidu Sasapu";
			reltApprover	="Shruti Aila";
			roleSrch1		="Credit Officer";
			roleSrch2		="Loan Officer";
			double AggrDebt 	=0;
			for (int j=0; j<numOfLoans; j++){
				AggrDebt = AggrDebt + Double.parseDouble(multiValueMap.get("Decision Amount").get(j).replace(",", ""));

			}
			if (AggrDebt > 2000000.00) {
				AFC3rdApprover = true;
				sleep(500);
				clickJS(DealInfoPage.dealinfoNewBtn); // Selecting New button
				sleep(500);
				inputTextUsingJS(DealInfoPage.dealinfoRoleSearch, "Chief Executive Officer");
				sleep(500);
				inputTextUsingJS(DealInfoPage.dealinfoContactName, "Sunil Kumar");
				sleep(500);
				inputTextUsingJS(DealInfoPage.dealinfoRole, "Chief Executive Officer");
			}

		}

		sleep(500);
		clickJS(DealInfoPage.dealinfoNewBtn); // Selecting New button
		sleep(500);
		inputTextUsingJS(DealInfoPage.dealinfoRoleSearch, roleSrch1);
		sleep(500);
		inputTextUsingJS(DealInfoPage.dealinfoContactName, reltApprover);
		sleep(500);
//		clearInput(DealInfoPage.dealinfoRole);
		inputTextUsingJS(DealInfoPage.dealinfoRole, roleSrch1);

		sleep(500);
		clickJS(DealInfoPage.dealinfoNewBtn);
		sleep(500);
		inputTextUsingJS(DealInfoPage.dealinfoRoleSearch, roleSrch2);
		sleep(500);
		inputTextUsingJS(DealInfoPage.dealinfoContactName, credApprover);
		sleep(500);
//		clearInput(DealInfoPage.dealinfoRole);
		inputTextUsingJS(DealInfoPage.dealinfoRole, roleSrch2);
		sleep(500);

	}

	public void SelectApproversInAuthorization() {
		String credApprover =null;
		String reltApprover =null;

		if (bankNumber.equalsIgnoreCase("001")) {
			credApprover	="svchubautomation svchubautomation";
			reltApprover	="svcQAAutomation svcQAAutomation";
		} else if (bankNumber.equalsIgnoreCase("019")){
			credApprover	="Raminaidu Sasapu";
			reltApprover	="Shruti Aila";
		}
		//Third Approver added when AFC deal loan amount > 2M
		if (AFC3rdApprover) {
			sleep(1000);
			click(AuthorizationPage.NewDecisionBtn);
			AuthorizeLogin();
			waitForInVisibile(PleaseWait);
			inputTextUsingJS(AuthorizationPage.DealDecisionNameDD, "Sunil Kumar");
			waitForInVisibile(PleaseWait);
			inputTextUsingJS(AuthorizationPage.DealDecisionDD, "Approved");
		}
		sleep(1000);
		click(AuthorizationPage.NewDecisionBtn);
		OptimistLogin();
		waitForInVisibile(PleaseWait);
		inputTextUsingJS(AuthorizationPage.DealDecisionNameDD, credApprover);
		waitForInVisibile(PleaseWait);
		inputTextUsingJS(AuthorizationPage.DealDecisionDD, "Approved");

		sleep(1000);
		click(AuthorizationPage.NewDecisionBtn);
		OptimistLogin();
		waitForInVisibile(PleaseWait);
		inputTextUsingJS(AuthorizationPage.DealDecisionNameDD, reltApprover);
		waitForInVisibile(PleaseWait);
		inputTextUsingJS(AuthorizationPage.DealDecisionDD, "Approved");

	}

	public void UpdatePDInDealInfo(String pdRating){
		String qry = "UPDATE t_ccs_app_field_rel SET rel_data = '"+ pdRating +"' WHERE field_id = 53248 and app_id = '"+ dealNumber +"' COMMIT TRAN";

		try {
			String connectionUrl = "jdbc:sqlserver://"+ EnvName + EnvDomain +":1433;databaseName=FBCME;user=SVCCMETESTUSER01;password=This1is2a3sqllogin"; //integratedSecurity=true";
			Connection con = DriverManager.getConnection(connectionUrl);
			PreparedStatement preparedStatement = con.prepareStatement(qry);
			preparedStatement.execute();
			con.close();

		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void UpdateAgFastScoreInDealInfo(String AgFastScore){
		String qry = "UPDATE t_ccs_app_field_rel SET rel_data = '"+ AgFastScore +"' WHERE field_id = 52149 and app_id = '"+ dealNumber +"' COMMIT TRAN";

		try {
			String connectionUrl = "jdbc:sqlserver://"+ EnvName + EnvDomain +":1433;databaseName=FBCME;user=SVCCMETESTUSER01;password=This1is2a3sqllogin"; //integratedSecurity=true";
			Connection con = DriverManager.getConnection(connectionUrl);
			PreparedStatement preparedStatement = con.prepareStatement(qry);
			preparedStatement.execute();
			con.close();

		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void UpdateCBScoreInCredAssmt(String cbScore){
		String qry = "UPDATE t_ccs_app_range_field_rel SET rel_data = '"+ cbScore +"' WHERE field_id = 51541 and app_id = '"+ dealNumber +"' COMMIT TRAN";

		try {
			String connectionUrl = "jdbc:sqlserver://"+ EnvName + EnvDomain +":1433;databaseName=FBCME;user=SVCCMETESTUSER01;password=This1is2a3sqllogin"; //integratedSecurity=true";
			Connection con = DriverManager.getConnection(connectionUrl);
			PreparedStatement preparedStatement = con.prepareStatement(qry);
			preparedStatement.execute();
			con.close();

		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String GetDealNumber(){
		String dealNum = null;
		try {
			dealNum = getElementText(DealStatus);
			dealNum = dealNum.replace("Deal #", "");
			String[] str = dealNum.split(" - Status:");
			dealNum = str[0];
		} catch (Exception e) {
			Assert.fail("Fetching Deal Number"+ "\n"+ e.getMessage());
		}
		return dealNum;
	}

	public void GetOBSLog() {
		try {
			Reporter.info("Opening OBS Log for Application# "+ dealAls);
			click(GetLocator("//div[@fieldname='Tools']"));
			sleep(500);
			click(GetLocator("//div[@fieldname='Advanced']"));
			sleep(500);
			click(GetLocator("//div[@fieldname='View OBS Log']"));
			click(clickHereToViewDocument);
			SwitchWindow(1);
			String shermXML = this.driver().findElement(By.xpath("/html/body/pre")).getText();
			File file = new File("target/html-report/OBSLog_" + dealAls +".txt");
			FileWriter fw = new FileWriter(file, true);
			fw.write(shermXML);
			fw.flush();
			fw.close();
			Reporter.addScreenshot("OBSLog", "OBSLog");
			this.driver().close();
			SwitchWindow(0);
			SwitchToCMEFrame();
			sleep(1000);
			click(DoneBtn);
			sleep(1000);
		} catch (IOException e) {
			Reporter.fail(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}
	public String GetLTVinFacilityScreen(){
		String LTV = null;
		try {
			LTV = getElementAttribute(FacilityInfoPage.facLTV, "title");

		} catch (Exception e) {
			Assert.fail("Fetching Deal Number"+ "\n"+ e.getMessage());
		}
		return LTV;
	}
	public String GetOBSFacilityType(String prodType) {
		String FacilityTypeOBS=null;
		switch (prodType) {
			case "Term":
				FacilityTypeOBS = "Term Amortizing";
				break;
			case "Revolver To Term":
				FacilityTypeOBS = " ";
				break;
			case "Revolver":
				FacilityTypeOBS = "Revolver/Standard";
				break;
			case "Operating Master":
				FacilityTypeOBS = "Master Term";
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + FacilityTypeOBS);
		}
		return FacilityTypeOBS;
	}

	public String PDDescription(String pdScore){
		String desc=null;

		switch (pdScore) {
			case"2":
				desc = "02 - Superior Quality";
				break;
			case"3":
				desc = "03 - Exceptional Quality";
				break;
			case"4":
				desc = "04 - Excellent Quality";
				break;
			case"5":
				desc = "05 - Strong Quality";
				break;
			case"6":
				desc = "06 - Good Quality";
				break;
			case"7":
				desc = "07 - Average Quality";
				break;
			case"8":
				desc = "08 - Adequate Quality";
				break;
			case"9":
				desc = "09 - Min Accept Quality";
				break;
			case"10":
				desc = "10 - OAEM";
				break;
			case"11":
				desc = "11 - Substandard Viable";
				break;
			case"12":
				desc = "12 - Substandard Not Viable";
				break;
			case"13":
				desc = "13 - Doubtful";
				break;
			case"14":
				desc = "14 - Loss";
				break;
		}
		return desc;

	}

	public String GetPPPCalcType(String PrePaymentOption) {
		String ppCalculateType = null;
		if (PrePaymentOption.equalsIgnoreCase("OPO")) { //facility
			ppCalculateType = "Open Prepay"; // OBS Page
		} else if (PrePaymentOption.equalsIgnoreCase("PLO")) {
			ppCalculateType = "Period Lockout (5-4-3-2-1)";
		} else if (PrePaymentOption.equalsIgnoreCase("OLO")) {
			ppCalculateType = "Period Lockout (1 Year)";
		} else if (PrePaymentOption.equalsIgnoreCase("FYM")) {
			ppCalculateType = "Full Yield Maintenance";
		}
		return ppCalculateType;
	}

	public void WritePerfMetricsToCSVFile(){
		boolean createHeaders=false;
		if (Activity.equalsIgnoreCase("Application")) {
			createHeaders = true;
		}
		try {
			if (CapturePerfMetrics) {
				if (createHeaders) {
					csvWriter = new FileWriter("target/html-report/ScreenLoadMetrics.csv");
					csvWriter.append("TestID");
					csvWriter.append(",");
					csvWriter.append("Activity");
					csvWriter.append(",");
					csvWriter.append("Object Name");
					csvWriter.append(",");
					csvWriter.append("Elapsed time (In Seconds)");
					csvWriter.append("\n");
				} else {
					csvWriter = new FileWriter("target/html-report/ScreenLoadMetrics.csv", true);
				}
				for (String[] rowData : PerfMetrics) {
					csvWriter.append(timeStampForPerf);
					csvWriter.append(",");
					csvWriter.append(String.join(",", rowData));
					csvWriter.append("\n");
				}
				csvWriter.flush();
				csvWriter.close();
				PerfMetrics.clear();
			}


		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}

	public void WriteCSVtoDB(){
		String csvFilePath = "target/html-report/ScreenLoadMetrics.csv";

		try {

			BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
			String lineText = null;
			lineReader.readLine(); // skip header line

			String qry	="INSERT INTO [PERF_TEST_RESULTS].[dbo].[LoadMetricStepDetails] ([testID], [testActivity], [testStepName], [testStepRunTime])"
					.concat("VALUES (?, ?, ?, ?)");


			String connectionUrl = "jdbc:sqlserver://QASTIMPRDFA04:1433;databaseName=PERF_TEST_RESULTS;user=SVCCMETESTUSER01;password=This1is2a3sqllogin"; //integratedSecurity=true";

			Connection con = DriverManager.getConnection(connectionUrl);
			PreparedStatement statement = con.prepareStatement(qry);

			while ((lineText = lineReader.readLine()) != null) {
				String[] data = lineText.split(",");
				String testID = data[0];
				String activity = data[1];
				String objName = data[2];
				String elapsTime = data[3];

				statement.setString(1, testID);
				statement.setString(2, activity);
				statement.setString(3, objName);
				statement.setString(4, elapsTime);
				statement.addBatch();
				statement.executeBatch();
			}
			lineReader.close();
			statement.executeBatch();
			con.commit();
			con.close();
		}catch (IOException | SQLException e) {
			Assert.fail(e.getMessage());
		}
		Reporter.info("Importing performance data to database from "+ csvFilePath);

	}

	public HashMap <String, String> GetConversionLoan(){
		HashMap<String, String> conversionData = new HashMap<>();

		return conversionData;
	}

}
