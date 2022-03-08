package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;

public class FacilityInfoPage {
	
	//Facility Request Section
	public static Locator ProductInfoSrn = Locator.byXpath("//a[@fieldname='Product Information']");
	public static Locator FacilityInfoSrn = Locator.byXpath("//a[@fieldname='Facility Information']");
	public static Locator OutstandingSrn = Locator.byXpath("//a[@fieldname='Outstanding Information']");
	public static Locator facLGDRating = Locator.byXpath("//div[@fieldname='ln_lgd_rating']/input");
	public static Locator facLGDScore = Locator.byXpath("//div[@fieldname='ln_lgd_score']/input");
	public static Locator facLGDOverrideRating = Locator.byXpath("//div[@fieldname='configField51689']/input");
	public static Locator facLastEditDt = Locator.byXpath("//div[@fieldname='use_last_edit_dt']/input");
	public static Locator facLastEditName = Locator.byXpath("//div[@fieldname='use_last_initial']/input");
	public static Locator facFacilityNo = Locator.byXpath("//div[@fieldname='ln_obgat_num']/input");
	public static Locator facUniversalNoteNo = Locator.byXpath("//div[@fieldname='ln_universal_num']/input");
	public static Locator facExistingNoteNo = Locator.byXpath("//div[@fieldname='ln_exis_note_num']/input");
	public static Locator facPrimBorrName = Locator.byXpath("//div[@fieldname='LN_PRIMARY_BORROWER']/input");
	public static Locator facCoBorrName = Locator.byXpath("//div[@fieldname='LN_SECONDARY_BORROWER']/input");
	public static Locator facDealNo = Locator.byXpath("//div[@fieldname='LN_LIQ_DEAL']/input");
	public static Locator facDealAlias = Locator.byXpath("//div[@fieldname='LN_LIQ_DEAL_ALIAS']/input");
	public static Locator facDeal = Locator.byXpath("//div[@fieldname='LN_LIQ_DEAL']/input");
	public static Locator facInstType = Locator.byXpath("//div[@fieldname='LN_HOLD_COMPANY_DESC']/input");
	public static Locator facLoanPgm = Locator.byXpath("//div[@fieldname='configField50455']/input");
	public static Locator facConsumerY = Locator.byXpath("//a[@fieldname='configField50339_Y']");
	public static Locator facConsumerN = Locator.byXpath("//a[@fieldname='configField50339_N']");
	public static Locator facProdType = Locator.byXpath("//div[@fieldname='LN_PROD_TYPE_DESC']/input");
	public static Locator facOptimistProd = Locator.byXpath("//div[@fieldname='configField52138']/input");
	public static Locator facTypOfReq = Locator.byXpath("//div[@fieldname='LN_REQUEST_TYPE_DESC']/input");
	public static Locator facLoanPurp = Locator.byXpath("//div[@fieldname='LN_PRIMARY_PURP']/input");
	public static Locator facAquaLoanY = Locator.byXpath("//a[@fieldname='configField55750_Y']");
	public static Locator facAquaLoanN = Locator.byXpath("//a[@fieldname='configField55750_N']");
	public static Locator facParticipationLoanY = Locator.byXpath("//a[@fieldname='ln_part_synd_yn_Y']");
	public static Locator facParticipationLoanN = Locator.byXpath("//a[@fieldname='ln_part_synd_yn_N']");
	public static Locator facBankOffY = Locator.byXpath("//a[@fieldname='configField51623_Y']");
	public static Locator facBankOffN = Locator.byXpath("//a[@fieldname='configField51623_N']");
	public static Locator facWaivePatronageY = Locator.byXpath("//a[@fieldname='configField53579_Y']");
	public static Locator facWaivePatronageN = Locator.byXpath("//a[@fieldname='configField53579_N']");
	public static Locator facEVaultY = Locator.byXpath("//a[@fieldname='configField56290_Y']");
	public static Locator facEVaultN = Locator.byXpath("//a[@fieldname='configField56290_N']");
	
	//Guarantee Section
	
	public static Locator facGuaranteePgmY = Locator.byXpath("//a[@fieldname='ln_guar_prog_yn_Y']");
	public static Locator facGuaranteePgmN = Locator.byXpath("//a[@fieldname='ln_guar_prog_yn_N']");
	public static Locator facGuaranteePrgms = Locator.byXpath("//div[@fieldname='LN_GUAR_PROGRAMS_CODE']/input");
	public static Locator facGuaranteePercent = Locator.byXpath("//div[@fieldname='ln_SBA_guaranty_pct']/input");
	public static Locator facGuaranteeAmt = Locator.byXpath("//div[@fieldname='ln_guar_amt']/input");
	
	//Facility Amount Section
	
	public static Locator facSuppCurrencies = Locator.byXpath("//div[@fieldname='ln_sup_cur']/input");
	public static Locator facReqAmt = Locator.byXpath("//div[@fieldname='ln_requested_amount']/input");
	public static Locator facDecisionAmt = Locator.byXpath("//div[@fieldname='ln_amount']/input");
	public static Locator facDocAmt = Locator.byXpath("//div[@fieldname='ln_documented_amount']/input");
	public static Locator facNewMoneyAmt = Locator.byXpath("//div[@fieldname='ln_new_money_amt']/input");
	public static Locator facNewMoneyAdjAmt = Locator.byXpath("//div[@fieldname='ln_payoff_amt']/input");
	public static Locator facLTV = Locator.byXpath("//div[@fieldname='ln_max_ltv']/input");
	public static Locator facSecRealEstY = Locator.byXpath("//a[@fieldname='configField50461_Y']");
	public static Locator facSecRealEstN = Locator.byXpath("//a[@fieldname='configField50461_N']");
	public static Locator facSecuredY = Locator.byXpath("//a[@fieldname='ln_secured_yn_Y']");
	public static Locator facSecuredN = Locator.byXpath("//a[@fieldname='ln_secured_yn_N']");
	public static Locator facNonAgIncDepY = Locator.byXpath("//a[@fieldname='configField50463_Y']");
	public static Locator facNonAgIncDepN = Locator.byXpath("//a[@fieldname='configField50463_N']");
	public static Locator facNonFarmIncDepY = Locator.byXpath("//a[@fieldname='configField50465_Y']");
	public static Locator facNonFarmIncDepN = Locator.byXpath("//a[@fieldname='configField50465_N']");
	public static Locator facTransLandSpecCollatY = Locator.byXpath("//a[@fieldname='configField52060_Y']");
	public static Locator facTransLandSpecCollatN = Locator.byXpath("//a[@fieldname='configField52060_N']");
	
	//Loan NAICS Code Section
	
	public static Locator facLoanNAICS = Locator.byXpath("//div[@fieldname='LN_NAICS_CODE_X']/input");
	
	//Repayment Source Code Section
	
	public static Locator facNAICSRepayCd = Locator.byXpath("//div[@fieldname='LN_NAICS_REPAY_CODE1_X']/input");
	public static Locator facNAICSSearchBtn = Locator.byXpath("//a[@fieldname='NAICS Search']");
	
	//Product Information Section
	
	public static Locator facPricingTyp = Locator.byXpath("//div[@fieldname='LN_PAYMENT_METHOD_TYPE_DESC']/input");
	public static Locator facRepriceOption = Locator.byXpath("//div[@fieldname='LN_TERM_ARM_YEARS_DESC']/input");
	public static Locator facFixdToMatY = Locator.byXpath("//a[@fieldname='configField52130_Y']");
	public static Locator facFixdToMatN = Locator.byXpath("//a[@fieldname='configField52130_N']");
	public static Locator facAccrualMethod = Locator.byXpath("//div[@fieldname='LN_ACCRUAL_BASIS_DESC_END']/input");
	public static Locator facRepaymentTyp = Locator.byXpath("//div[@fieldname='LN_PAYMENT_TYPE_CODE_END_X']/input");
	public static Locator facPaymentFreq = Locator.byXpath("//div[@fieldname='LN_INT_PAYMENT_FREQ_END_X']/input");
	public static Locator facLoanEffDate = Locator.byXpath("//div[@fieldname='ln_eff_dt']/input");
	public static Locator facLoanTermMon = Locator.byXpath("//div[@fieldname='ln_term']/input");
	public static Locator facNumOfIOPmts = Locator.byXpath("//div[@fieldname='ln_no_of_int_only_pmts']/input");
	public static Locator facMaturityDate = Locator.byXpath("//div[@fieldname='ln_mat_dt']/input");
	public static Locator facPaymntStrtDt = Locator.byXpath("//div[@fieldname='ln_pmt_start_dt']/input");
	public static Locator principlePmtStrtDte = Locator.byXpath("//div[@fieldname='ln_prin_first_pmnt_dt']/input");
	public static Locator facAdvExpireDate = Locator.byXpath("//div[@fieldname='ln_advance_exp_dt']/input");
	public static Locator facPrepaymentOpt = Locator.byXpath("//div[@fieldname='INDEX_PRE_PMT_OPTION_DESC']/input");
	public static Locator facPrePmtPenaltyY = Locator.byXpath("//a[@fieldname='ln_prepay_penalty_yn_Y']");
	public static Locator facPrePmtPenaltyN = Locator.byXpath("//a[@fieldname='ln_prepay_penalty_yn_N']");
	public static Locator facAutoPayY = Locator.byXpath("//a[@fieldname='ln_auto_pay_yn_Y']");
	public static Locator facAutoPayN = Locator.byXpath("//a[@fieldname='ln_auto_pay_yn_N']");
	public static Locator facPrepaymentOptDesc = Locator.byXpath("//div[@fieldname='INDEX_PRE_PMT_OPTION_DESC1']/input");
	public static Locator facFullyAmrtY = Locator.byXpath("//a[@fieldname='ln_fully_amortizing_ind_Y']");
	public static Locator facFullyAmrtN = Locator.byXpath("//a[@fieldname='ln_fully_amortizing_ind_N']");
	public static Locator facCalcTypRegPmt = Locator.byXpath("//a[@fieldname='ln_calc_pmt_ind_R']");
	public static Locator facCalcTypBalPmt = Locator.byXpath("//a[@fieldname='ln_calc_pmt_ind_B']");
	public static Locator facCalcTypNeither = Locator.byXpath("//a[@fieldname='ln_calc_pmt_ind_N']");
	public static Locator facLateCharge = Locator.byXpath("//div[@fieldname='configField53114']/input");
	public static Locator paymentFreqDDbtn = Locator.byXpath("//*[@fieldname='LN_INT_PAYMENT_FREQ_END_X']/div[2]/div");
	//Interest Rate Section
	
	public static Locator facBaseRate = Locator.byXpath("//div[@fieldname='ln_requested_rate']/input");
	public static Locator facAssociationSpread = Locator.byXpath("//div[@fieldname='ln_index_spread_end']/input");
	public static Locator facBorrStatedRate = Locator.byXpath("//div[@fieldname='ln_assumed_calc_rate']/input");
	public static Locator facBorrSpreadToIndxRate = Locator.byXpath("//div[@fieldname='ln_index_spread_final']/input");
	public static Locator facRecSpread = Locator.byXpath("//div[@fieldname='configField50514']/input");
	public static Locator facVariance = Locator.byXpath("//div[@fieldname='configField50516']/input");
	public static Locator facVarianceReason = Locator.byXpath("//div[@fieldname='configField53577']/input");
	public static Locator facRoundingOpt = Locator.byXpath("//div[@fieldname='configField53535']/input");
	public static Locator facRateLockTyp = Locator.byXpath("//div[@fieldname='INDEX_RATE_LOCK_TYPE_DESC']/input");
	public static Locator facWholesaleRtConfNo = Locator.byXpath("//div[@fieldname='ln_conf_num']/input");
	public static Locator facQuoteExpDt = Locator.byXpath("//div[@fieldname='index_quote_exp_date']/input");
	public static Locator facLockInDt = Locator.byXpath("//div[@fieldname='rate_lock_in_date']/input");
	public static Locator facLockExpDt = Locator.byXpath("//div[@fieldname='rate_lock_exp_date']/input");
	public static Locator facDNRRateLockY = Locator.byXpath("//a[@fieldname='ln_dnr_rate_lock_yn_Y']");
	public static Locator facDNRRateLockN = Locator.byXpath("//a[@fieldname='ln_dnr_rate_lock_yn_N']");
	public static Locator facGetRateBtn = Locator.byXpath("//a[@fieldname='Get Rate']");
	public static Locator facLockRateBtn = Locator.byXpath("//a[@fieldname='Lock Rate']");
	public static Locator facCalcPmtBtn = Locator.byXpath("//a[@fieldname='CALCULATE PAYMENT']");
	public static Locator facAmortizationBtn = Locator.byXpath("//a[@fieldname='Amortization']");
	public static Locator facFirstPmt = Locator.byXpath("//div[@fieldname='ln_payment_end']/input");
	public static Locator facFinalPmt = Locator.byXpath("//div[@fieldname='ln_new_payment']/input");
	public static Locator facPmtTotal = Locator.byXpath("//div[@fieldname='lamort_gtotal_pmt']/input");
	public static Locator facNoOfOddDays = Locator.byXpath("//div[@fieldname='ln_odd_days_count']/input");
	public static Locator facPartialInt = Locator.byXpath("//div[@fieldname='ln_odd_days_int']/input");
	public static Locator facIntTotal = Locator.byXpath("//div[@fieldname='lamort_gtotal_int']/input");
	public static Locator facAPR = Locator.byXpath("//div[@fieldname='ln_pmtcalc_apr']/input");
	public static Locator facEIR = Locator.byXpath("//div[@fieldname='ln_pmtcalc_eir']/input");
	public static Locator facPricingOpts = Locator.byXpath("//div[@fieldname='LN_SUPPORTED_PRICING_OPT_DD']/input");
	public static Locator facSuccessCalulationMsg = Locator.byXpath("//div[@class='label' and @fieldname='Successful Calculation']");
	//Construction Details
	
	public static Locator facConstStrtDt = Locator.byXpath("//div[@fieldname='configField53158']/input");
	public static Locator facConstEndDt = Locator.byXpath("//div[@fieldname='configField53159']/input");
	public static Locator facConstTyp = Locator.byXpath("//div[@fieldname='configField53615']/input");
	public static Locator facContDesc = Locator.byXpath("//div[@fieldname='configField53581']/input");
	
	//Loan Coding Obj
	
	public static Locator facLCARCapDesc = Locator.byXpath("//div[@fieldname='LN_ABL_AR_CAP_DESC']/input");
	public static Locator facLCARCapAmt = Locator.byXpath("//div[@fieldname='ln_abl_ar_cap_amt']/input");
	public static Locator facLCInvCapDesc = Locator.byXpath("//div[@fieldname='LN_ABL_INV_CAP_DESC']/input");
	public static Locator facLCInvCalcTyp = Locator.byXpath("//div[@fieldname='LN_ABL_INV_CALC_TYPE']/input");
	public static Locator facLCLoanID = Locator.byXpath("//div[@fieldname='loan_id']/input");
	public static Locator facLCFacNo = Locator.byXpath("//div[@fieldname='ln_obgat_num']/input");
	public static Locator facLCLastEdit = Locator.byXpath("//div[@fieldname='use_last_edit_dt']/input");
	public static Locator facLCLoanAmt = Locator.byXpath("//div[@fieldname='ln_amount']/input");
	public static Locator facLCMatDt = Locator.byXpath("//div[@fieldname='ln_mat_dt']/input");
	public static Locator facLCIntRtPrdY = Locator.byXpath("//a[@fieldname='configField54584_Y']");
	public static Locator facLCIntRtPrdN = Locator.byXpath("//a[@fieldname='configField54584_N']");
	public static Locator facLCIntRtPrdNA = Locator.byXpath("//a[@fieldname='configField54584_X']");
	public static Locator facLCMortY = Locator.byXpath("//a[@fieldname='ln_hmda_closedmort_or_openline_yn_Y']");
	public static Locator facLCMortN = Locator.byXpath("//a[@fieldname='ln_hmda_closedmort_or_openline_yn_N']");
	public static Locator facLCHDMARprtY = Locator.byXpath("//a[@fieldname='ln_hmda_yn_Y']");
	public static Locator facLCHDMARprtN = Locator.byXpath("//a[@fieldname='ln_hmda_yn_N']");
	public static Locator facLCHouseNo = Locator.byXpath("//div[@fieldname='adr_addr_house_no']/input");
	public static Locator facLCBrAddr = Locator.byXpath("//div[@fieldname='adr_addr1']/input");
	public static Locator facLCAddr2 = Locator.byXpath("//div[@fieldname='adr_addr_sup']/input");
	public static Locator facLCCity = Locator.byXpath("//div[@fieldname='adr_city']/input");
	public static Locator facLCState = Locator.byXpath("//div[@fieldname='adr_state']/input");
	public static Locator facLCZip1 = Locator.byXpath("//div[@fieldname='adr_zip']/input");
	public static Locator facLCZip2 = Locator.byXpath("//div[@fieldname='adr_zip_4']/input");
	public static Locator facLCCounty = Locator.byXpath("//div[@fieldname='adr_county']/input");
	public static Locator facLCCensusTrct = Locator.byXpath("//div[@fieldname='adr_census_tract']/input");
	public static Locator facLCMSA = Locator.byXpath("//div[@fieldname='adr_msa_num']/input");
	public static Locator facLCFDICCd1 = Locator.byXpath("//div[@fieldname='LN_FDIC_CODE_X']/input");
	public static Locator facLCFDICCd2 = Locator.byXpath("//div[@fieldname='LN_FDIC_CODE_X1']/input");
	public static Locator facLCCREGrp = Locator.byXpath("//div[@fieldname='CRE_CODE_CAT_DESC']/input");
	public static Locator facLCCRECode1 = Locator.byXpath("//div[@fieldname='LN_CRE_CODE_X']/input");
	public static Locator facLCCRECode2 = Locator.byXpath("//div[@fieldname='LN_CRE_CODE_X1']/input");
	public static Locator facLCNASInd1 = Locator.byXpath("//div[@fieldname='LEXNEX_NAS_IND']/input");
	public static Locator facLCNASInd2 = Locator.byXpath("//div[@fieldname='LEXNEX_NAS_IND1']/input");
	public static Locator facLCHmImprY = Locator.byXpath("//a[@fieldname='ln_home_imp_yn_Y']");
	public static Locator facLCHmImprN = Locator.byXpath("//a[@fieldname='ln_home_imp_yn_N']");
	public static Locator facLCCrdCnvNotes = Locator.byXpath("//div[@fieldname='configField51676']/input");
	
	//Common Buttons
	
	public static Locator backBtn = Locator.byXpath("//a[@fieldname='Back']");
	public static Locator continueBtn = Locator.byXpath("//a[@fieldname='Continue']");
	public static Locator saveBtn = Locator.byXpath("//a[@fieldname='Save']");
	public static Locator editBtn = Locator.byXpath("//a[@fieldname='Edit']");
	public static Locator resetBtn = Locator.byXpath("//a[@fieldname='Reset']");
	public static Locator deleteBtn = Locator.byXpath("//a[@fieldname='Delete']");
	
	//Facility Approval screen objects
	public static Locator ApprovalPurposePurposeDD = Locator.byXpath("//div[@fieldname='configField53541']/input");
	public static Locator ApprovalPurposeDescription = Locator.byXpath("//div[@fieldname='configField53542']/input");
	public static Locator ApprovalPurposeAmount = Locator.byXpath("//div[@fieldname='configField53546']/input");

}

