package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;


public class OBSPage {

	public static Locator OBSScrn = Locator.byXpath("//a[@fieldname='OBS Booking']");
	public static Locator OBSiFrame = Locator.byXpath("//div[@fieldname='CUSTOM_BOOKING']//iframe");
	public static Locator SignInLabel = Locator.byXpath("//h3[text()='Sign In']");
	public static Locator UserName = Locator.byXpath("//*[@id='user_login']");
	public static Locator Password = Locator.byXpath("//*[@id='user_pass']");
	public static Locator SignInBtn = Locator.byXpath("//input[@value='Sign In']");
	public static Locator CustomerCard = Locator.byXpath("//div[@id='customer-card']");
	public static Locator DealCard = Locator.byXpath("//div[@id='deal-card']");
	public static Locator FacilityCard = Locator.byXpath("//div[@id='facility-card']");
	public static Locator AccountCard = Locator.byXpath("//div[@id='account-card']");
	public static Locator CardLinks = Locator.byXpath("//a[@class='list-group-item d-flex justify-content-between align-items-center list-group-item-action list-group-item-secondary']/span");
	public static Locator BookBtn = Locator.byXpath("//*[@id='request_book_button']");
	public static Locator ProgressBar = Locator.byXpath("//*[@id='progress-bar']");
	public static Locator ProgressBarMessage = Locator.byXpath("//*[@id='progress-bar-message']");
	public static Locator ErrorMessage = Locator.byXpath("//*[@id='celery-result']");
	public static Locator EntitiesGrid = Locator.byXpath("//table[@id='DataTables_Table_0']");
	public static Locator FirstName = Locator.byXpath("//*[@id='id_name_part_2']");
	public static Locator MiddleName = Locator.byXpath("//*[@id='id_name_part_3']");
	public static Locator LastName = Locator.byXpath("//*[@id='id_name_part_4']");
	public static Locator LegalName = Locator.byXpath("//*[@id='id_name']");
	public static Locator ShortName = Locator.byXpath("//*[@id='id_short_name']");
	public static Locator CustomerNumber = Locator.byXpath("//*[@id='id_customerNumber']");
	public static Locator LIQStatus = Locator.byXpath("//*[@id='id_status']");
	public static Locator IndustryCode = Locator.byXpath("//*[@id='select2-id_industry_code-container']");
	public static Locator Branch = Locator.byXpath("//*[@id='id_branch']");
	public static Locator ExpenseCode = Locator.byXpath("//*[@id='select2-id_costCenter-container']");
	public static Locator Department = Locator.byXpath("//*[@id='id_department']");
	public static Locator BusinessClassification = Locator.byXpath("//*[@id='id_businessClass']");
	public static Locator AddressesGrid = Locator.byXpath("//table[@id='address-dataTable']");
	public static Locator ContactsGrid = Locator.byXpath("//table[@id='contact-dataTable']");
	public static Locator RemitInstructionsGrid = Locator.byXpath("//table[@id='instruction-dataTable']");
	public static Locator ServicingGroupsGrid = Locator.byXpath("//table[@id='sg-dataTable']");
	public static Locator ProfilesGrid = Locator.byXpath("//table[@id='role-dataTable']");
	public static Locator AddressType = Locator.byXpath("//table[@id='address-dataTable']//th[text()='ADDRESS TYPE']");
	public static Locator Address = Locator.byXpath("//table[@id='address-dataTable']//th[text()='ADDRESS']");

	public static Locator DealGrid = Locator.byXpath("//table[@id='DataTables_Table_0']");
	public static Locator DealName = Locator.byXpath("//input[@name='deal_name']");
	public static Locator Deal_Classification = Locator.byXpath("//select[@name='deal_classification']");
	public static Locator Deal_ProposedCommitment= Locator.byXpath("//input[@name='proposed_commitment']");
	public static Locator Deal_AgreementDate= Locator.byXpath("//input[@name='deal_agreement_dte']");
	public static Locator Deal_Department = Locator.byXpath("//select[@name='department']");
	public static Locator Deal_CloseDate = Locator.byXpath("//input[@name='deal_close_dte']");
	public static Locator Deal_Status = Locator.byXpath("//select[@name='deal_status']");
	public static Locator Deal_Branch = Locator.byXpath("//select[@name='branch']");
	public static Locator Deal_Personnel = Locator.byXpath("//li[@class='select2-selection__choice']");
	public static Locator Deal_CostCenter = Locator.byXpath("//select[@name='costCenter']");
	public static Locator Deal_PricingRules_Table = Locator.byXpath("//table[@id='DataTables_Table_2']");
	public static Locator Deal_Borrowers = Locator.byXpath("//select[@class='form-control form-control-sm col-4']/option");
	public static Locator Deal_Riskfor = Locator.byPartialLinkText("Risks for");
	public static Locator Deal_Relatedfor = Locator.byXpath("//span[contains(text(),'Related Parties for')]");
	public static Locator Deal_RelatedPartiesTable = Locator.byXpath("//table[@id='DataTables_Table_3']");
	public static Locator FacName = Locator.byXpath("//label[contains(text(),'Facility')]//following::input[1]");//input[@id='id_facility_name']
	public static Locator FacType = Locator.byXpath("//label[contains(text(),'facility type')]//following::select[1]");
	public static Locator FacClosingCommitment= Locator.byXpath("//input[@name='closing_commitment']");
	public static Locator FacLoanPurpose = Locator.byXpath("//li[@class='select2-selection__choice']");
	public static Locator FacEffectiveDate = Locator.byXpath("//input[@name='fac_effective_dte']");
	public static Locator FacAgreementDate = Locator.byXpath("//input[@name='fac_agreement_dte']");
	public static Locator FacExpirationDate = Locator.byXpath("//input[@name='fac_expiry_dte']");
	public static Locator FacMaturityDate = Locator.byXpath("//input[@name='fac_maturity_dte']");
	public static Locator FacControlNumber = Locator.byXpath("//input[@id='id_facility_number']");
	public static Locator FacIndustryCode = Locator.byXpath("//input[@id='id_facility_number']");
	public static Locator FacRelatedParties = Locator.byXpath("//a[text()='Related Parties']");//a[text()='Related Parties']//div[@id='facility-card']/div/div[2]/a[4]
	public static Locator FacAF_PrePayPenal = Locator.byXpath("//td[text()='PREPAYMENT PENALTY CALC TYPE']//following::a[1]");
	public static Locator FacAF_PrePayPenalEffecDate = Locator.byXpath("//td[text()='PREPAYMENT PENALTY EFFECTIVE DATE']//following::a[1]");
	public static Locator FacRiskRateDesc = Locator.byXpath("//table[@id='DataTables_Table_4']/tbody/tr[1]/td[4]");










}
