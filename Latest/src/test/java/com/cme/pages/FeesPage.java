package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;

public class FeesPage {

	public static Locator feesScreen = Locator.byXpath("//a[@fieldname='Fees']");
	public static Locator feesLoanIDDesc = Locator.byXpath("//div[@fieldname='product_type_desc']");
	public static Locator feesLastEditDate = Locator.byXpath("//div[@fieldname='use_last_edit_dt']");
	public static Locator feesLastEditUsername = Locator.byXpath("//div[@fieldname='use_last_initial']");
	public static Locator feesLoanAmount = Locator.byXpath("//div[@fieldname='readOnly_ln_amount']");
	public static Locator feesLoanAmountCurrency = Locator.byXpath("//div[@fieldname='readOnly_ln_amount_currcode']");

	//Fees Details Section
	
	public static Locator feesFeeType = Locator.byXpath("//div[@fieldname='FEE_TYPE_CODE']/input");
	public static Locator feesFeeTypeDesc = Locator.byXpath("//div[@fieldname='FEE_TYPE_CODE1']/input");
	public static Locator feesBorrAmountpaid = Locator.byXpath("//div[@fieldname='fee_amt_borrower_pd']/input");
	public static Locator feesFeePercentage = Locator.byXpath("//div[@fieldname='fee_requested_percent']/input");
	public static Locator feesLoanID = Locator.byXpath("//div[@fieldname='loan_id']/input");
	public static Locator feesFeeAmount = Locator.byXpath("//div[@fieldname='fee_amt']/input");
	public static Locator feesFeeFinancedYes = Locator.byXpath("//a[@fieldname='fee_financed_yn_Y']");
	public static Locator feesFeeFinancedNo = Locator.byXpath("//a[@fieldname='fee_financed_yn_N']");
	public static Locator fees1098ReportablePointsYes = Locator.byXpath("//a[@fieldname='configField55168_Y']");
	public static Locator fees1098ReportablePointsNo = Locator.byXpath("//a[@fieldname='configField55168_N']");

	//Fees Included in Calculation Section
	
	public static Locator feesEIRYes = Locator.byXpath("//a[@fieldname='fee_type_eir_yn_Y']");
	public static Locator feesEIRNo = Locator.byXpath("//a[@fieldname='fee_type_eir_yn_N']");
	public static Locator feesAPRYes = Locator.byXpath("//a[@fieldname='fee_type_apr_yn_Y']");
	public static Locator feesAPRNo = Locator.byXpath("//a[@fieldname='fee_type_apr_yn_N']");
	
	//Fees Grid box
	public static Locator feesFeeGrid = Locator.byXpath("//div[@fieldname='GRID_FEE']");
	
	public static Locator feesTotalFees = Locator.byXpath("//div[@fieldname='fee_tot']");
	public static Locator feesTotalFeesCurrency = Locator.byXpath("//div[@fieldname='fee_tot_currcode']");
	public static Locator feesTotalBorrPaid = Locator.byXpath("//div[@fieldname='borrower_tot']");
	public static Locator feesTotalBorrPaidCurrency = Locator.byXpath("//div[@fieldname='borrower_tot_currcode']");
	public static Locator feesTotalSellerPaid = Locator.byXpath("//div[@fieldname='seller_tot']");
	public static Locator feesTotalSellerPaidCurrency = Locator.byXpath("//div[@fieldname='seller_tot_currcode']");
	public static Locator feesTotalOtherPaid = Locator.byXpath("//div[@fieldname='other_tot']");
	public static Locator feesTotalOtherPaidCurrency = Locator.byXpath("//div[@fieldname='other_tot_currcode']");

	//Buttons
	public static Locator feesNewBtn = Locator.byXpath("//a[@fieldname='New']");
	public static Locator feesRemoveFromListBtn = Locator.byXpath("//a[@fieldname='Remove from List']");
	public static Locator feesSaveBtn = Locator.byXpath("//a[@fieldname='Save']");
	public static Locator feesContinueBtn = Locator.byXpath("//a[@fieldname='Continue']");
	public static Locator feesResetBtn = Locator.byXpath("//a[@fieldname='Reset']");
	public static Locator feesEditBtn = Locator.byXpath("//a[@fieldname='Edit']");
	public static Locator feesBackBtn = Locator.byXpath("//a[@fieldname='Back']");

	
}
