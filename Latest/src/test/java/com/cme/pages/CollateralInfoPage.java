package com.cme.pages;

import com.fcbt.taf.core.driver.ui.Locator;

public class CollateralInfoPage {
	//Collateral Analysis Screen
	public static Locator collateralAnalysisScrn = Locator.byXpath("//a[@fieldname='Collateral Analysis - (Product)']");

	public static Locator collateralScreen = Locator.byXpath("//a[@fieldname='Collateral Information'][@class='floatDivLeft']");
	public static Locator collatOwnerName = Locator.byXpath("//div[@fieldname='COLLAT_OWNER_NAME']/input");
	public static Locator collatOwnerDDbtn = Locator.byXpath("//*[@fieldname='COLLAT_OWNER_NAME']/div[2]/div");
	public static Locator facilitiesSecuredByCollat = Locator.byXpath("//div[@fieldname='loan_id']/input");
	public static Locator facilitiesSecuredByCollatDDbtn = Locator.byXpath("//*[@fieldname='loan_id']/div[2]/div");
	public static Locator isThisPrimaryCollatYes = Locator.byXpath("//a[@fieldname='collat_primary_yn_Y']");
	public static Locator isThisPrimaryCollatNo = Locator.byXpath("//a[@fieldname='collat_primary_yn_N']");
	public static Locator primaryForWhichFacility = Locator.byXpath("//div[@fieldname='LN_PRIMARY_COLLAT_LOAN_IDS']/input");
	public static Locator primaryForWhichFacilityDDbtn = Locator.byXpath("//*[@fieldname='LN_PRIMARY_COLLAT_LOAN_IDS']/div[2]/div");
	public static Locator isThisRealEstateCollatYes = Locator.byXpath("//a[@fieldname='configField51516_Y']");
	public static Locator isThisRealEstateCollatNo= Locator.byXpath("//a[@fieldname='configField51516_N']");
	public static Locator collatCategory= Locator.byXpath("//div[@fieldname='COLLAT_CATEGORY_DESC']/input");
	public static Locator isCollatCrossedCardinalYes = Locator.byXpath("//a[@fieldname='configField55000_Y']");
	public static Locator isCollatCrossedCardinalNo = Locator.byXpath("//a[@fieldname='configField55000_N']");
	public static Locator collatType = Locator.byXpath("//div[@fieldname='COLLAT_TYPE_X']/input");
	public static Locator collatDispalyDesc = Locator.byXpath("//div[@fieldname='collat_desc']/textarea");
	public static Locator isCollatHazardInsuranceYes = Locator.byXpath("//a[@fieldname='configField52067_Y']");
	public static Locator isCollatHazardInsuranceNo= Locator.byXpath("//a[@fieldname='configField52067_N']");
	public static Locator isThisCollateralPoolableYes = Locator.byXpath("//a[@fieldname='configField51517_Y']");
	public static Locator isThisCollateralPoolableYNo = Locator.byXpath("//a[@fieldname='configField51517_N']");
	//Collateral value fields
	public static Locator collatMarketVal = Locator.byXpath("//div[@fieldname='canal_fmv']/input");
	public static Locator collatPurchasePrice = Locator.byXpath("//div[@fieldname='configField54810']/input");
	public static Locator collatNetRealizableValYes = Locator.byXpath("//a[@fieldname='configField55383_Y']");
	public static Locator collatNetRealizableValNo = Locator.byXpath("//a[@fieldname='configField55383_N']");
	public static Locator collatAdvacneRate = Locator.byXpath("//div[@fieldname='canal_adv_rt']/input");
	public static Locator collatMaxRate = Locator.byXpath("//div[@fieldname='canal_adv_rt_max']/input");
	public static Locator collatValuationType = Locator.byXpath("//div[@fieldname='configField50238']/input");
	public static Locator collatValuationSubType = Locator.byXpath("//div[@fieldname='configField50641']/input");
	public static Locator collatInternalAppraiserYes = Locator.byXpath("//a[@fieldname='configField52063_Y']");
	public static Locator collatInternalAppraiserNo = Locator.byXpath("//a[@fieldname='configField52063_N']");
	public static Locator collatValuationDate = Locator.byXpath("//div[@fieldname='collat_appraisal_dt']/input");
	public static Locator collatAppraiserEvaluator = Locator.byXpath("//div[@fieldname='configField50640']/input");
	public static Locator ValuationSubType = Locator.byXpath("//div[@fieldname='configField50641']/input");
	public static Locator ValueSource = Locator.byXpath("//div[@fieldname='configField51656']/input");
	public static Locator optiCollatType = Locator.byXpath("//div[@fieldname='configField52134']/input");
	//Collateral location fields
	public static Locator collatAddressType = Locator.byXpath("//div[@fieldname='configField53201']/input");
	public static Locator collatNumberOfProperties = Locator.byXpath("//div[@fieldname='configField54598']/input");
	public static Locator collatAddress1 = Locator.byXpath("//div[@fieldname='collat_addr1']/input");
	public static Locator collatAddress2 = Locator.byXpath("//div[@fieldname='collat_address2']/input");
	public static Locator collatCity= Locator.byXpath("//div[@fieldname='collat_city']/input");
	public static Locator collatState= Locator.byXpath("//div[@fieldname='collat_state']/input");
	public static Locator collatZipcode= Locator.byXpath("//div[@fieldname='collat_zip']/input");
	public static Locator collatCounty= Locator.byXpath("//div[@fieldname='COLLAT_COUNTY_DESC']/input");
	public static Locator collatLatitude= Locator.byXpath("//div[@fieldname='configField51654']/input");
	public static Locator collatLongitude= Locator.byXpath("//div[@fieldname='configField51655']/input");
	public static Locator collatLegalDesc = Locator.byXpath("//div[@fieldname='estate_legal_desc']/textarea");
	//Request flood report
	public static Locator collatFloodReportYes = Locator.byXpath("//a[@fieldname='configField53018_Y']");
	public static Locator collatFloodReportNo = Locator.byXpath("//a[@fieldname='configField53018_N']");
	public static Locator collatRentedLeasedYes = Locator.byXpath("//a[@fieldname='configField50254_Y']");
	public static Locator collatRentedLeasedNo = Locator.byXpath("//a[@fieldname='configField50254_N']");
	public static Locator collatOwnerOccupiedYes = Locator.byXpath("//a[@fieldname='estate_owner_occup_yn_Y']");
	public static Locator collatOwnerOccupiedNo = Locator.byXpath("//a[@fieldname='estate_owner_occup_yn_N']");
	public static Locator collatRecordingInfoYes = Locator.byXpath("//a[@fieldname='configField53030_Y']");
	public static Locator collatRecordingInfoNo = Locator.byXpath("//a[@fieldname='configField53030_N']");
	public static Locator IndexingInstructions= Locator.byXpath("//div[@fieldname='configField53038']/textArea");
	public static Locator InsuranceRequiredYes = Locator.byXpath("//a[@fieldname='configField52064_Y']");
	public static Locator InsuranceRequiredNo = Locator.byXpath("//a[@fieldname='configField52064_N']");
	public static Locator EnterUCCInformationYes = Locator.byXpath("//a[@fieldname='configField50674_Y']");
	public static Locator EnterUCCInformationNo = Locator.byXpath("//a[@fieldname='configField50674_N']");
	//Acreage information
	public static Locator collatTimberAcres= Locator.byXpath("//div[@fieldname='configField53124']/input");
	public static Locator collatPastureAcres= Locator.byXpath("//div[@fieldname='configField53126']/input");
	public static Locator collatCultivatedAcres= Locator.byXpath("//div[@fieldname='configField53128']/input");
	public static Locator collatIrrigatedAcres= Locator.byXpath("//div[@fieldname='configField53130']/input");
	public static Locator collatOther= Locator.byXpath("//div[@fieldname='configField53132']/input");
	public static Locator collatTotalDeedAcres= Locator.byXpath("//div[@fieldname='configField54965']/input");
	public static Locator collatTotalLandValue= Locator.byXpath("//div[@fieldname='configField50266']/input");
	public static Locator collatEnvironmentalAggrementYes = Locator.byXpath("//a[@fieldname='estate_environ_agree_yn_Y']");
	public static Locator CollatEnvironmentalAggrementNo = Locator.byXpath("//a[@fieldname='estate_environ_agree_yn_N']");
	public static Locator collatHomestedYes = Locator.byXpath("//a[@fieldname='estate_waive_homestead_yn_Y']");
	public static Locator collatHomestedNo = Locator.byXpath("//a[@fieldname='estate_waive_homestead_yn_N']");
	public static Locator collatGF= Locator.byXpath("//div[@fieldname='configField53167']/input");
	public static Locator CollatOptType= Locator.byXpath("//div[@fieldname='configField52134']/input");

	//Buttons
	public static Locator backBtn = Locator.byXpath("//a[@fieldname='Back']");
	public static Locator continueBtn = Locator.byXpath("//a[@fieldname='Continue']");
	public static Locator saveBtn = Locator.byXpath("//a[@fieldname='Save']");
	//Page load
	public static Locator CollateralAnalysisScrn = Locator.byXpath("//a[@fieldname='Collateral Analysis - (Product)']");
	public static Locator CollateralPoolScrn = Locator.byXpath("//a[@fieldname='Collateral Pool']");
	public static Locator collatPoolType= Locator.byXpath("//div[@fieldname='cpool_field01']/input");
	public static Locator collatPoolDesc= Locator.byXpath("//div[@fieldname='cpool_field02']/input");
	public static Locator collatPoolNumOfUnits= Locator.byXpath("//div[@fieldname='cpool_units']/input");
	public static Locator collatPoolUnitPrice= Locator.byXpath("//div[@fieldname='cpool_unit_value']/input");
	public static Locator NRELocationDescRequiredYes = Locator.byXpath("//a[@fieldname='configField53255_Y']");
	public static Locator NRELocationDescRequiredNo = Locator.byXpath("//a[@fieldname='configField53255_N']");
	public static Locator RentedYes = Locator.byXpath("//a[@fieldname='configField50254_Y']");
	public static Locator OwnerOccupiedNo = Locator.byXpath("//a[@fieldname='estate_owner_occup_yn_N']");
	public static Locator ImprovementsLandNo = Locator.byXpath("//a[@fieldname='configField51640_N']");
	public static Locator PurchasePropertyYes = Locator.byXpath("//a[@fieldname='estate_proceeds_used_yn_Y']");
	public static Locator HomesteadNo = Locator.byXpath("//a[@fieldname='estate_waive_homestead_yn_N']");
	public static Locator ProceedsUsedToPurchaseYes = Locator.byXpath("//a[@fieldname='estate_proceeds_used_yn_Y']");
	public static Locator ProceedsUsedToPurchaseNo = Locator.byXpath("//a[@fieldname='estate_proceeds_used_yn_N']");












}
