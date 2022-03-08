package com.cme.pages;

import com.fcbt.taf.core.driver.ui.Locator;

public class EntityInfoPage {

	// Entity Info Section
	
		public static Locator entInfoSrn = Locator.byXpath("//a[@fieldname='Entity Information']");
		public static Locator AdditionalEntityDetailsScrn = Locator.byXpath("//a[contains(text(), '- Additional Entity Details')]");
		public static Locator entCustID = Locator.byXpath("//div[@fieldname='ent_obgr_num']/input");
		public static Locator entPDRating = Locator.byXpath("//div[@fieldname='ent_pd_rating']/input");
		public static Locator entAgFastScore = Locator.byXpath("//div[@fieldname='configField52148']/input");
		public static Locator entLastEditDate = Locator.byXpath("//div[@fieldname='use_last_edit_dt']/input");
		public static Locator entLastEditName = Locator.byXpath("//div[@fieldname='use_last_initial']/input");
		public static Locator entCreditScore = Locator.byXpath("//div[@fieldname='ent_beacon_score']/input");
		public static Locator entNxtAnnualReview = Locator.byXpath("//div[@fieldname='ent_next_annual_review_dt']/input");
		public static Locator entRelType = Locator.byXpath("//div[@fieldname='rel_type']/input");
		public static Locator entTypeInd = Locator.byXpath("//a[@fieldname='ent_type_I']");
		public static Locator entTypeCom = Locator.byXpath("//a[@fieldname='ent_type_C']");
		public static Locator EditGuaranteedProdsYes = Locator.byXpath("//a[@fieldname='rel_edit_guar_yn_Y']");
		public static Locator EditGuaranteedProdsNo = Locator.byXpath("//a[@fieldname='rel_edit_guar_yn_N']");
		public static Locator entStruct = Locator.byXpath("//div[@fieldname='ENT_STRUCT_DESC']/input");
		public static Locator entliqProfType = Locator.byXpath("//div[@fieldname='ent_liq_profile_types']/input");
		public static Locator borrFarmCateg = Locator.byXpath("//div[@fieldname='ENT_INVOLVE_FARM_DESC']/input");
		public static Locator WhichProducts = Locator.byXpath("//div[@fieldname='loan_id']/input");
		public static Locator yearBeganFarm = Locator.byXpath("//div[@fieldname='ent_begin_farmer_year']/input");
		public static Locator grossAgSales = Locator.byXpath("//div[@fieldname='configField51631']/input");
		public static Locator entOfficialLoanY = Locator.byXpath("//a[@fieldname='ent_regulation_o_yn_Y']");
		public static Locator entOfficialLoanN = Locator.byXpath("//a[@fieldname='ent_regulation_o_yn_N']");
		public static Locator entOfficialType = Locator.byXpath("//div[@fieldname='ENT_OFF_TYPE_DESC_X']/input");
		public static Locator entOfficialName = Locator.byXpath("//div[@fieldname='ent_off_name']/input");
		public static Locator GuaranteedProductsScrn = Locator.byXpath("//a[@fieldname='Guaranteed Products']");
		public static Locator GuaranteeType = Locator.byXpath("//div[@fieldname='REL_LN_GUARANTEE_TYPE_DESCRIPTION']/input");
		public static Locator GuaranteeLimitPct = Locator.byXpath("//div[@fieldname='rel_guar_limit_pct']/input");
		public static Locator GuaranteeLimitAmnt = Locator.byXpath("//div[@fieldname='rel_guar_limit']/input");
		public static Locator GuaranteeBasisType = Locator.byXpath("//div[@fieldname='configField53037']/input");
		public static Locator UnlimitedGuarantyYes = Locator.byXpath("//a[@fieldname='configField52062_Y']");
		public static Locator UnlimitedGuarantyNo = Locator.byXpath("//a[@fieldname='configField52062_N']");
		public static Locator GuarantyPercentYes = Locator.byXpath("//a[@fieldname='configField51107_Y']");
		public static Locator GuarantyPercentNo = Locator.byXpath("//a[@fieldname='configField51107_N']");
		
		
		// Personal Info Section and Company Information
		
		public static Locator entSalutation = Locator.byXpath("//div[@fieldname='configField51114']/input");
		public static Locator entSuffix = Locator.byXpath("//div[@fieldname='ENT_SUFFIX_DESC']/input");
		public static Locator entProfCode = Locator.byXpath("//div[@fieldname='ENT_PROF_CODE']/input");
		public static Locator entFname = Locator.byXpath("//div[@fieldname='ent_fname']/input");
		public static Locator entMname = Locator.byXpath("//div[@fieldname='ent_mname']/input");
		public static Locator entLname = Locator.byXpath("//div[@fieldname='ent_lname']/input");
		public static Locator entLegalName = Locator.byXpath("//div[@fieldname='ent_name']/input");
		public static Locator entAKA = Locator.byXpath("//div[@fieldname='ent_aka']/input");
		public static Locator entTaxIDFlag = Locator.byXpath("//div[@fieldname='ENT_TAX_FLAG_DESC']/input");
		public static Locator entSSN = Locator.byXpath("//div[@fieldname='ent_socno']/input");
		public static Locator entTaxID = Locator.byXpath("//div[@fieldname='ent_tin']/input");
		public static Locator entTaxIDCert = Locator.byXpath("//div[@fieldname='ENT_TAX_ID_CERT_CODE']/input");
		public static Locator entDOB = Locator.byXpath("//div[@fieldname='ent_dob']/input");
		public static Locator entUSCitizenY = Locator.byXpath("//a[@fieldname='configField50092_Y']");
		public static Locator entUSCitizenN = Locator.byXpath("//a[@fieldname='configField50092_N']");
		public static Locator entGenderMale = Locator.byXpath("//a[@fieldname='ent_gender_M']");
		public static Locator entGenderFemale = Locator.byXpath("//a[@fieldname='ent_gender_F']");
		public static Locator entMaritalSts = Locator.byXpath("//div[@fieldname='ENT_MAR_STAT_DESC']/input");
		public static Locator entDateOfMarriage = Locator.byXpath("//div[@fieldname='configField53077']/input");
		public static Locator entMortRecital = Locator.byXpath("//div[@fieldname='configField54817']/input");
		public static Locator entIDType = Locator.byXpath("//div[@fieldname='ENT_ID_TYPE_X']/input");
		public static Locator entActvMilitaryY = Locator.byXpath("//a[@fieldname='configField50095_Y']");
		public static Locator entActvMilitaryN = Locator.byXpath("//a[@fieldname='configField50095_N']");
		public static Locator entMltrySrvcStrtDate = Locator.byXpath("//div[@fieldname='configField50096']/input");
		public static Locator entMltrySrvcEndDate = Locator.byXpath("//div[@fieldname='configField50097']/input");
		public static Locator entActvMaritalStsAcquPropertyY = Locator.byXpath("//a[@fieldname='configField50714_Y']");
		public static Locator entActvMaritalStsAcquPropertyN = Locator.byXpath("//a[@fieldname='configField50714_N']");
		public static Locator entMnthlyIncome = Locator.byXpath("//div[@fieldname='ent_monthly_inc']/input");
		public static Locator entNoOfYrsOwner = Locator.byXpath("//div[@fieldname='ent_yrs_curr_owner']/input");
		public static Locator entNetWorth = Locator.byXpath("//div[@fieldname='ent_net_worth']/input");
		public static Locator entTaxExemptY = Locator.byXpath("//a[@fieldname='ent_tax_exempt_yn_Y']");
		public static Locator entTaxExemptN = Locator.byXpath("//a[@fieldname='ent_tax_exempt_yn_N']");
		public static Locator entNotFrProfitY = Locator.byXpath("//a[@fieldname='ent_not_for_profit_yn_Y']");
		public static Locator entNotFrProfitN = Locator.byXpath("//a[@fieldname='ent_not_for_profit_yn_N']");
		
		// Legal Address Section
		
		public static Locator entUSAddressY = Locator.byXpath("//a[@fieldname='configField51515_Y']");
		public static Locator entUSAddressN = Locator.byXpath("//a[@fieldname='configField51515_N']");
		public static Locator entMailingAddressY = Locator.byXpath("//a[@fieldname='configField51529_Y']");
		public static Locator entMailingAddressN = Locator.byXpath("//a[@fieldname='configField51529_N']");
		public static Locator entaddr1 = Locator.byXpath("//div[@fieldname='adr_addr1']/input");
		public static Locator entaddr2 = Locator.byXpath("//div[@fieldname='adr_addr_sup']/input");
		public static Locator addrCity = Locator.byXpath("//div[@fieldname='adr_city']/input");
		public static Locator addrZip1 = Locator.byXpath("//div[@fieldname='adr_zip']/input");
		public static Locator addrZip2 = Locator.byXpath("//div[@fieldname='adr_zip_4']/input");
		public static Locator addrState = Locator.byXpath("//div[@fieldname='adr_state']/input");
		public static Locator addrCounty = Locator.byXpath("//div[@fieldname='adr_county']/input");
		public static Locator addrCurrLivesOn = Locator.byXpath("//div[@fieldname='configField54599']/input");
		public static Locator occupySecurityYrRoundResidence_Yes = Locator.byXpath("//a[@fieldname='configField55001_Y']");
		public static Locator occupySecurityYrRoundResidence_No = Locator.byXpath("//a[@fieldname='configField55001_N']");
		public static Locator claimPropertyAsHomestead_Yes = Locator.byXpath("//a[@fieldname='configField54600_Y']");
		public static Locator claimPropertyAsHomestead_No = Locator.byXpath("//a[@fieldname='configField54600_N']");
		
		// Contact Info Section
		
		public static Locator entPrimPhone = Locator.byXpath("//div[@fieldname='ent_phn1']/input");
		public static Locator entPrimPhoneExt = Locator.byXpath("//div[@fieldname='ent_phn1_ext']/input");
		public static Locator entSecPhone = Locator.byXpath("//div[@fieldname='ent_phn2']/input");
		public static Locator entSecPhoneExt = Locator.byXpath("//div[@fieldname='ent_phn2_ext']/input");
		public static Locator entFax = Locator.byXpath("//div[@fieldname='ent_phn4']/input");
		public static Locator entEmail = Locator.byXpath("//div[@fieldname='ent_email_addr']/input");
		public static Locator entWebsite = Locator.byXpath("//div[@fieldname='configField50123']/input");
		
		
		// Employment Information Section
		
		public static Locator entOccupation = Locator.byXpath("//div[@fieldname='configField50127']/input");
		public static Locator entYrsInOccupation = Locator.byXpath("//div[@fieldname='ent_yrs_in_industry']/input");
		public static Locator entSelfEmployedY = Locator.byXpath("//a[@fieldname='ent_self_employed_yn_Y']");
		public static Locator entSelfEmployedN = Locator.byXpath("//a[@fieldname='ent_self_employed_yn_N']");
		public static Locator entCurrOrFormerEmplyrs = Locator.byXpath("//div[@fieldname='configField53061']/input");
		
		// Employer Information 1
		
		public static Locator entEmployer = Locator.byXpath("//div[@fieldname='ent_employer_name']/input");
		public static Locator entContactName = Locator.byXpath("//div[@fieldname='configField50128']/input");
		public static Locator entContactPhone = Locator.byXpath("//div[@fieldname='configField50211']/input");
		public static Locator entAddress = Locator.byXpath("//div[@fieldname='ent_employer_addr']/input");
		public static Locator entVeriRequrdY = Locator.byXpath("//a[@fieldname='configField52146_Y']");
		public static Locator entVeriRequrdN = Locator.byXpath("//a[@fieldname='configField52146_N']");
		public static Locator entYrsWthEmployer = Locator.byXpath("//div[@fieldname='ent_yrs_with_company']/input");
		public static Locator entCity = Locator.byXpath("//div[@fieldname='ent_employer_city']/input");
		public static Locator EmpZip = Locator.byXpath("//div[@fieldname='ent_employer_zip']/input");
		public static Locator EmpZipExt = Locator.byXpath("//div[@fieldname='ent_employer_zipext']/input");
		public static Locator EmpState = Locator.byXpath("//div[@fieldname='ent_employer_state']/input");
		public static Locator entAnualSal = Locator.byXpath("//div[@fieldname='configField53074']/input");
		public static Locator entEmpTypeC = Locator.byXpath("//a[@fieldname='configField56281_1']");
		public static Locator entEmpTypeF = Locator.byXpath("//a[@fieldname='configField56281_2']");
		public static Locator entBankruptcyY = Locator.byXpath("//a[@fieldname='configField50711_Y']");
		public static Locator entBankruptcyN = Locator.byXpath("//a[@fieldname='configField50711_N']");
		public static Locator entJobTitle = Locator.byXpath("//div[@fieldname='configField53161']/input");
		public static Locator entBnkruptcyLegalCode = Locator.byXpath("//div[@fieldname='configField50712']/input");
		
		// Employer Information 2
		
		public static Locator entEmployer1 = Locator.byXpath("//div[@fieldname='configField50130']/input");
		public static Locator entContactName1 = Locator.byXpath("//div[@fieldname='configField53064']/input");
		public static Locator entContactPhone1 = Locator.byXpath("//div[@fieldname='configField50129']/input");
		public static Locator entAddress1 = Locator.byXpath("//div[@fieldname='configField50104']/input");
		public static Locator entVeriRequrd1Y = Locator.byXpath("//a[@fieldname='configField53066_Y']");
		public static Locator entVeriRequrd1N = Locator.byXpath("//a[@fieldname='configField53066_N']");
		public static Locator entYrsWthEmployer1 = Locator.byXpath("//div[@fieldname='configField53068']/input");
		public static Locator entCity1 = Locator.byXpath("//div[@fieldname='configField50137']/input");
		public static Locator EmpZip1 = Locator.byXpath("//div[@fieldname='configField50139']/input");
		public static Locator EmpZipExt1 = Locator.byXpath("//div[@fieldname='configField53072']/input");
		public static Locator EmpState1 = Locator.byXpath("//div[@fieldname='configField50220']/input");
		public static Locator entAnualSal1 = Locator.byXpath("//div[@fieldname='configField53075']/input");
		public static Locator entEmp1TypeC = Locator.byXpath("//a[@fieldname='configField56282_1']");
		public static Locator entEmp1TypeF = Locator.byXpath("//a[@fieldname='configField56282_2']");
		public static Locator entJobTitle1 = Locator.byXpath("//div[@fieldname='configField53162']/input");
		
		
		// Employer Information 3
			
		public static Locator entEmployer2 = Locator.byXpath("//div[@fieldname='configField53070']/input");
		public static Locator entContactName2 = Locator.byXpath("//div[@fieldname='configField53065']/input");
		public static Locator entContactPhone2 = Locator.byXpath("//div[@fieldname='configField53071']/input");
		public static Locator entAddress2 = Locator.byXpath("//div[@fieldname='configField50133']/input");
		public static Locator entVeriRequrd2Y = Locator.byXpath("//a[@fieldname='configField53067_Y']");
		public static Locator entVeriRequrd2N = Locator.byXpath("//a[@fieldname='configField53067_N']");
		public static Locator entYrsWthEmployer2 = Locator.byXpath("//div[@fieldname='configField53069']/input");
		public static Locator entCity2 = Locator.byXpath("//div[@fieldname='configField50218']/input");
		public static Locator EmpZip2 = Locator.byXpath("//div[@fieldname='configField50221']/input");
		public static Locator EmpZipExt2 = Locator.byXpath("//div[@fieldname='configField53073']/input");
		public static Locator EmpState2 = Locator.byXpath("//div[@fieldname='configField50220']/input");
		public static Locator entAnualSal2 = Locator.byXpath("//div[@fieldname='configField53076']/input");
		public static Locator entEmp2TypeC = Locator.byXpath("//a[@fieldname='configField56283_1']");
		public static Locator entEmp2TypeF = Locator.byXpath("//a[@fieldname='configField56283_2']");
		public static Locator entJobTitle2 = Locator.byXpath("//div[@fieldname='configField53163']/input");
				
				
				
		// Industry Codes Section
		
		public static Locator entNAICS_Code1 = Locator.byXpath("//div[@fieldname='ENT_NAICS_CODE_X']/input");

	public static Locator entaddInfoSrn = Locator.byXpath("//a[@class='floatDivLeft' and contains(text(),'Additional Entity Details')]");
		
		// Company Details
		
		public static Locator entNoOfEmployees = Locator.byXpath("//div[@fieldname='ent_num_employees']/input");
		public static Locator entStateOrg = Locator.byXpath("//div[@fieldname='ent_state_inc']/input");
		public static Locator entYrsInBuisns = Locator.byXpath("//div[@fieldname='ent_yrs_est']/input");
		public static Locator entDateOrganized = Locator.byXpath("//div[@fieldname='ent_est_dt']/input");	
		public static Locator entDirMtngDate = Locator.byXpath("//div[@fieldname='ent_meeting_dt']/input");
		public static Locator entSecrName = Locator.byXpath("//div[@fieldname='ent_secretary_name']/input");
		public static Locator entMembrMtngDate = Locator.byXpath("//div[@fieldname='ent_mem_meeting_dt']/input");
		public static Locator entRvnueDate = Locator.byXpath("//div[@fieldname='ent_gross_rev_dt']/input");
		public static Locator entOperAgrmntDate = Locator.byXpath("//div[@fieldname='ent_op_agree_dt']/input");
		public static Locator entAnualRevenue = Locator.byXpath("//div[@fieldname='ent_gross_rev']/input");
		public static Locator entDateOfTrust = Locator.byXpath("//div[@fieldname='ent_trust_date']/input");
		public static Locator entTrustCreatdBy = Locator.byXpath("//div[@fieldname='configField50159']/input");
		public static Locator entTrstRevocableY = Locator.byXpath("//a[@fieldname='ent_revoke_yn_Y']");
		public static Locator entTrstRevocableN = Locator.byXpath("//a[@fieldname='ent_revoke_yn_N']");
		
		// Buttons
		public static Locator saveBtn = Locator.byXpath("//a[@fieldname='Save']");
		public static Locator continueBtn = Locator.byXpath("//a[@fieldname='Continue']");
		
		
		
		// Waiting For Page Load
		public static Locator PleaseWait = Locator.byXpath("//div[@fieldname='Please Wait...']");
		
		
	
	
	
}
