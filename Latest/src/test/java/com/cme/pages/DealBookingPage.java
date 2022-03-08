package com.cme.pages;

import com.fcbt.taf.core.driver.ui.Locator;

public class DealBookingPage {
    public static Locator DBScreen = Locator.byXpath("//a[@fieldname='Deal Booking']");
    public static Locator DealRow = Locator.byXpath("//st-grid-box[@fieldname='LIQ_Deals_PostDocs_Grid']/table/tbody/tr[1]");
    public static Locator FacilityRow = Locator.byXpath("//st-grid-box[@fieldname='LIQ_Deals_PostDocs_Grid']/table/tbody/tr[2]");
    public static Locator SaveAllBtn = Locator.byXpath("//button[text()='Save All']");
    public static Locator liqStatus = Locator.byXpath("//st-grid-box[@fieldname='LIQ_Deals_PostDocs_Grid']/table");
    public static Locator SaveAllSuccess = Locator.byXpath("//p[contains(text(),'All ready deals have been booked and updated.')]");
    public static Locator dealAlias = Locator.byXpath("//p[@fieldname='TxtField_Alias_Deal']");
    public static Locator dealAgreementDate = Locator.byXpath("//input[@fieldname='DP_Agreement_Date']");
    public static Locator ExpandAll = Locator.byXpath("//button[@fieldname='Expand_All_Button']");
    public static Locator dealAmount = Locator.byXpath("//p[@fieldname='TxtField_Amount']");
    public static Locator branch = Locator.byXpath("//p[@fieldname='TxtField_Branch']");
    public static Locator primaryBorrower = Locator.byXpath("//p[@fieldname='TxtField_Primary_Borrower']");
    public static Locator currency = Locator.byXpath("//button[@fieldname='DD_Currency_btn']//div[@ng-if='options.displayField']");
    public static Locator classification = Locator.byXpath("//button[@fieldname='Classification_btn']//div[@ng-if='options.displayField']");
    public static Locator department = Locator.byXpath("//button[@fieldname='DD_Department_btn']//div[@ng-if='options.displayField']");
    public static Locator expensecode = Locator.byXpath("//button[@fieldname='DD_Expense_Code_Desc_btn']//div[@ng-if='options.displayField']");
    public static Locator pricingoptions = Locator.byXpath("//pre[@fieldname='TxtArea_Pricing_Options_Deal']");
    public static Locator okBtn = Locator.byXpath("//button[contains(text(),'OK')]");
    //Relationship Tab
    public static Locator adminAgent = Locator.byXpath ("//st-grid-box[@fieldname='Servicing_Group_Grid']/table/tbody/tr[1]");
    public static Locator borrowerRow = Locator.byXpath("//st-grid-box[@fieldname='Servicing_Group_Grid']/table/tbody/tr[2]");
    public static Locator dbSGDD = Locator.byXpath("//button[@fieldname='Servicing_Group_btn']//following::div[1]");
    public static Locator SG = Locator.byXpath("//div[@fieldname='Servicing_Group_menu']/table/tbody/tr[2]");
    public static Locator updateRS = Locator.byXpath("//button[text()='Update Relationships']");
    public static Locator DBRIMsg = Locator.byXpath("//st-grid-box[@fieldname='Servicing_Group_Grid']/table");
    public static Locator RelationshipTable = Locator.byXpath("//st-grid-box[@fieldname='Servicing_Group_Grid']/table");

    //Deal MIS/Additional Tab
    public static Locator misAddFields = Locator.byXpath("//span[text()='MIS/Additional Fields']");
    public static Locator bookMis = Locator.byXpath("//button[text()='Book MIS/Additional Fields']");

    //Primary Tab
    public static Locator primaryTab = Locator.byXpath("//span[text()='Primary']");
    public static Locator riskBookDD = Locator.byXpath("//button[@fieldname='Risk_Book_btn']");
    public static Locator riskBookDDValue = Locator.byXpath("//div[@fieldname='Risk_Book_menu']/table/tbody/tr[2]");
    public static Locator portfolioExpenseDD = Locator.byXpath("//button[@fieldname='PortfolioExpense_btn']");
    public static Locator portfolioExpenseDDValue = Locator.byXpath("//div[@fieldname='PortfolioExpense_menu']/table/tbody/tr[7]");
    public static Locator createPrimaryCircle = Locator.byXpath("//button[@fieldname='Create_Primary_Circle']");

    //Risk Types Tab
    public static Locator RisktypesTable = Locator.byXpath("//st-grid-box[@fieldname='Risk_Type_Grid']/table/tbody");
    public static Locator bookRiskTypes = Locator.byXpath("//button[@fieldname='Book_Risk_Types_Button']");
    public static Locator riskBookStatus = Locator.byXpath("//st-grid-box[@fieldname='Risk_Type_Grid']/table");
    //public static  String riskBookStatusString = "//st-grid-box[@fieldname='Risk_Type_Grid']/table/tbody/tr['%d']/td[5]";

    //Facility Basic Information
    public static Locator FacId = Locator.byXpath("//fieldset[@class='Facility_ID']/label/span/h4");
    public static Locator Name = Locator.byName("//fieldset[@class='TxtField_Name_Facility']/label/span/p");
    public static Locator Type = Locator.byXpath("//fieldset[@class='TxtField_Type_Facility']/label/span/p");
    public static Locator Currency = Locator.byXpath("//fieldset[@class='DD_Currency']/label/span/p");
    public static Locator Amount= Locator.byXpath("//screen-gen[@fieldname='Book_Facility_subscreen']/div/fieldset[7]/label/span[2]");
    public static Locator PrimaryBorrower = Locator.byXpath("(//fieldset[@class='TxtField_Primary_Borrower']/label/span)[4]");
    public static Locator SecondaruBorrower = Locator.byXpath("(//fieldset[@class='TxtArea_Secondary_Borrower']/label/span)[2]");
    public static Locator Effectivedate = Locator.byXpath("//input[@name='TxtField_Effective_Date']");
    public static Locator Expirationdate = Locator.byXpath("(//fieldset[@class='TxtField_Expiration_Date']/label/span)[2]");
    public static Locator Maturitydate = Locator.byXpath("(//fieldset[@class='TxtField_Maturity_Date']/label/span)[2]");
    public static Locator Purpose = Locator.byXpath("(//fieldset[@class='TxtField_Purpose_Facility']/label/span)[2]");
    public static Locator SupportedRiskTypes = Locator.byXpath("//screen-gen[@fieldname='Book_Facility_subscreen']/div/fieldset[13]/label/span/pre");

    //Facility Guarantor
    public static Locator FacilityGuarantorTab  = Locator.byXpath("//span[text()='Facility Guarantors']");
    public static Locator facGuarantorTable = Locator.byXpath("//st-grid-box[@fieldname='Guarantor_Grid']/table");
    public static Locator facGuaCustomerName = Locator.byXpath("(//fieldset[@class='Customer_Name']/label/span)[2]");
    public static Locator facGuaLocation = Locator.byXpath("//button[@fieldname='Location_btn']/div/div");
    public static Locator facGuaLimitAmount = Locator.byXpath("//input[@fieldname='Limit_Amount']");
    public static Locator facGuaSG = Locator.byXpath("//button[@fieldname='Servicing_Group_btn']/div/div");
    public static Locator facGuaranteeType= Locator.byXpath("//button[@fieldname='Guarantee_Type_DD_btn']/div/div");
    public static Locator facGuaLimitPercent = Locator.byXpath("//input[@fieldname='Limit_Percent']");
    public static Locator facUpdateGuarantors = Locator.byXpath("//button[text()='Update Guarantors']");
    public static Locator facGuarantorIndicator = Locator.byXpath("(//span[text()='Facility Guarantors']//preceding::span[@ng-show='tab.showStatusIndicator'])[6]");


    //Facility MIS/Additional Tab
    public static Locator facMisAddFields = Locator.byXpath("//cme-tab-group[@fieldname='FacilityBookingWithoutPastDuePricing_GeneratedTabGroup']/div/div/ul/li[3]/a/uib-tab-heading/span[2]");
    public static Locator BookMISAFButton = Locator.byXpath("//cme-tab-group[@fieldname='FacilityBookingWithoutPastDuePricing_GeneratedTabGroup']//button[@fieldname='Book_MIS_Additional_Fields']");
    public static Locator BeginningFarmer = Locator.byXpath("//span[text()='Beginning Farmer']/parent::label/div//button//div[@ng-if='options.displayField']");
    public static Locator CommittedUncommited = Locator.byXpath("//span[text()='Committed/Uncommitted']/parent::label/div//button//div[@ng-if='options.displayField']");
    public static Locator SmallFarmer = Locator.byXpath("//span[text()='Small Farmer']/parent::label/div//button//div[@ng-if='options.displayField']");
    public static Locator CapitalAllocation = Locator.byXpath("//span[text()='Capital Allocation']/parent::label/div//button//div[@ng-if='options.displayField']");
    public static Locator Secured = Locator.byXpath("//span[text()='Secured/Unsecured']/parent::label/div//button//div[@ng-if='options.displayField']");
    public static Locator YoungFarmer = Locator.byXpath("//span[text()='Young Farmer']/parent::label/div//button//div[@ng-if='options.displayField']");
    public static Locator CharteredTerritory = Locator.byXpath("//span[text()='Chartered Territory']/parent::label/div//button//div[@ng-if='options.displayField']");
    public static Locator DBSuccessMsgPopup = Locator.byXpath("//div[@class='modal-body custom-modal-body']");
    //Fac Additional Fileds
    public static Locator CollateralDescription = Locator.byXpath("//span[text()='Collateral Description']/following-sibling::textarea");
    public static Locator PrimaryRepayment = Locator.byXpath("//span[text()='Primary Repayment']/parent::label/div//button//div[@ng-if='options.displayField']");
    public static Locator PPCalType = Locator.byXpath("//span[text()='Prepayment Penalty Calc Type']/parent::label/div//button//div[@ng-if='options.displayField']");
    public static Locator PPPEffectiveDate = Locator.byXpath("//span[text()='Prepayment Penalty Effective Date']/following-sibling::div/input");
    public static Locator facMISAddfieldsIndicator = Locator.byXpath("(//span[text()='MIS/Additional Fields']//preceding::span[@ng-show='tab.showStatusIndicator'])[7]");
    //Outstandings
    public static Locator outstandingTab = Locator.byXpath("//span[text()='Outstandings']");
    public static Locator BookOSTButton = Locator.byXpath("//button[text()='Book Outstandings']");
    public static Locator outstandingTable = Locator.byXpath("//st-grid-box[@fieldname='OutstandingsGridBox']/table");
    public static Locator ostName = Locator.byName("//fieldset[@class='Name']/label/span[2]/p");
    public static Locator ostLiqId= Locator.byName("///fieldset[@class='LoanIQ_ID']/label/span[2]/p");
    public static Locator ostID = Locator.byName("//fieldset[@class='ID']/label/span[2]/p");
    public static Locator ostStatus = Locator.byName("//fieldset[@class='Status']/label/span[2]/p");
    public static Locator ostAmount = Locator.byName("//fieldset[@class='Amount']/label/span[2]/p");
    public static Locator ostCurrency = Locator.byName("//fieldset[@class='Currency']/label/span[2]/p");
    public static Locator ostBorrower = Locator.byName("//fieldset[@class='Borrower']/label/span[2]/p");
    public static Locator ostEffectiveDate = Locator.byName("//fieldset[@class='EffectiveDate']/label/span[2]/p");
    public static Locator ostRiskType = Locator.byName("//fieldset[@class='Risk_Type']/label/span[2]/p");
    public static Locator ostPricingOption = Locator.byName("//p[@fieldname='Pricing_Option']");
    public static Locator ostBaseRate = Locator.byName("//p[@fieldname='Base_Rate']");
    public static Locator ostPaymentFrequency = Locator.byName("//p[@fieldname='paymentFrequency']");
    public static Locator ostMaturityDate = Locator.byName("//p[@fieldname='Maturity_Date']");
    public static Locator ostCOFSpread = Locator.byName("//p[@fieldname='NonMatchFunded_COF_Spread']");
    public static Locator ostPaymentStartDate = Locator.byName("//p[@fieldname='paymentStartDate']");
    public static Locator ostLateCharge = Locator.byName("//p[@fieldname='lateChargeId']");
    public static Locator ostFrequency = Locator.byName("//div[@class='st-dd-text font-data selected_content']");
    public static Locator ostSuccessMsg = Locator.byXpath("//st-grid-box[@fieldname='OutstandingsGridBox']/table/tbody/tr[1]/td[9]");
    public static Locator RepaymentScheduleButton = Locator.byXpath("//button[text()='Repayment Schedule']");
    public static Locator repSuccessMsg = Locator.byXpath("//st-grid-box[@fieldname='OutstandingsGridBox']/table/tbody/tr[1]/td[9]");
    public static Locator ostIndicator = Locator.byXpath("(//span[text()='Outstandings']//preceding::span[@ng-show='tab.showStatusIndicator'])[8]");

    //Deal Basic Info Section
    public static Locator agreementDate = Locator.byXpath("//input[@fieldname='DP_Agreement_Date']");
    public static Locator closeDealBtn = Locator.byXpath("//button[@fieldname='Button_Close_Deal']");
    public static Locator closeDate = Locator.byXpath("//input[@fieldname='closeDate']");
    public static Locator closeDeal = Locator.byXpath("//button[@fieldname='CloseDeal']");
    public static Locator closeWindow = Locator.byXpath("//button[text()='Close Window']");
    //Relationship tab fields
    public static Locator RelationshipsTab = Locator.byXpath("//span[text()='Relationships']");
    public static Locator PaymentRulesTab = Locator.byXpath("//span[text()='Payment Rules']");
    public static Locator DealLvlMISAFTab = Locator.byXpath("//fieldset[@class='TabGroup_DealBooking']//span[text()='MIS/Additional Fields']");
    public static Locator RelTabCustName = Locator.byXpath("//p[@fieldname='Customer_Name']");
    public static Locator RelTabRole = Locator.byXpath("//p[@fieldname='Role']");
    public static Locator RelTabLocation = Locator.byXpath("//button[@fieldname='Location_btn']//div[@ng-if ='options.displayField']");
    public static Locator RelTabBorrowerIndicator = Locator.byXpath("//input[@fieldname='Borrower_Indicator']");
    public static Locator RelTabDepositorIndicator = Locator.byXpath("//input[@fieldname='Depositor_Indicator']");
    public static Locator RelTabSG = Locator.byXpath("//button[@fieldname='Servicing_Group_btn']//div[@ng-if ='options.displayField']");
    public static Locator RelTabDD = Locator.byXpath("//button[@fieldname='Servicing_Group_btn']");
    public static Locator RelTabPreferredRI = Locator.byXpath("//button[@fieldname='Preferred_Remit_Instruction_btn']");
    //Deal row tabs indicators
    public static Locator RelationshipsTabIndicator = Locator.byXpath("//span[text()='Relationships']/preceding-sibling::span[@ng-show='tab.showStatusIndicator']");
    public static Locator PaymentRulesTabIndicator = Locator.byXpath("//span[text()='Payment Rules']/preceding-sibling::span[@ng-show='tab.showStatusIndicator']");
    public static Locator MISAFTabIndicator = Locator.byXpath("//span[text()='MIS/Additional Fields']/preceding-sibling::span[@ng-show='tab.showStatusIndicator']");
    public static Locator PrimaryTabIndicator = Locator.byXpath("//span[text()='Primary']/preceding-sibling::span[@ng-show='tab.showStatusIndicator']");
    //Primary tab
    public static Locator lender = Locator.byXpath("//p[@fieldname='Lender']");
    public static Locator lenderLocation = Locator.byXpath("//button[@fieldname='Lender_Location_btn']//div[@ng-if ='options.displayField']");

    public static Locator RiskTypesTabIndicator = Locator.byXpath("//span[text()='Risk Types']/preceding-sibling::span[@ng-show='tab.showStatusIndicator']");
    public static Locator RiskTypesTab = Locator.byXpath("//span[text()='Risk Types']");

    public static Locator FacGuarantorsTabIndicator = Locator.byXpath("//span[text()='Facility Guarantors']/preceding-sibling::span[@ng-show='tab.showStatusIndicator']");
    public static Locator FacMISAFTabIndicator = Locator.byXpath("//fieldset[@class='TabGroup_Facility_Booking']//span[text()='MIS/Additional Fields']/preceding-sibling::span[@ng-show='tab.showStatusIndicator']");
    public static Locator OutstandingsTabIndicator = Locator.byXpath("//span[text()='Outstandings']/preceding-sibling::span[@ng-show='tab.showStatusIndicator']");
    public static Locator OSTFrequency = Locator.byXpath("//button[@fieldname='Frequency_btn']");

}
