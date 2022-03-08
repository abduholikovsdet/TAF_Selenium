package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;


public class DisbursementsPage {

	public static Locator disbursementsScreen = Locator.byXpath("//a[@fieldname='Disbursements']");
	public static Locator disbursementsGrid = Locator.byXpath("//div[@fieldname='GRID_DISB_INFO']");
	public static Locator DocumentedLoanAmount = Locator.byXpath("//div[@fieldname='ln_documented_amount']");
	public static Locator AmountAvailable = Locator.byXpath("//div[@fieldname='TextField3380']/input");

	//Disbursements Details Section	
	//Fee Disbursement	
	public static Locator DisbursementType = Locator.byXpath("//div[@fieldname='DISBURSE_TYPE_DESC']/input");
	public static Locator FeeToDisburse = Locator.byXpath("//div[@fieldname='fee_amt']/input");
	public static Locator Amount = Locator.byXpath("//div[@fieldname='disburse_amt']/input");
	public static Locator DisburseFeesYes = Locator.byXpath("//a[@fieldname='disburse_loan_fee_ind_Y']");
	public static Locator DisburseFeesNo = Locator.byXpath("//a[@fieldname='disburse_loan_fee_ind_N']");
	public static Locator Comments = Locator.byXpath("//div[@fieldname='configField53026']/textarea");
	
	//Payable to Others
	public static Locator Payee = Locator.byXpath("//div[@fieldname='disburse_payee']/input");
	public static Locator DisbursalType = Locator.byXpath("//div[@fieldname='configField53113']/input");
	
	//Funds Held
	public static Locator FundsHeldType = Locator.byXpath("//div[@fieldname='configField53112']/input");
	public static Locator FundsHeldSpread = Locator.byXpath("//div[@fieldname='configField54593']/input");

	//Pay off Existing Debt and Principal Pay Down Existing Debt
	public static Locator LoanNumber = Locator.byXpath("//div[@fieldname='configField53107']/input");
	public static Locator PayOffDate = Locator.byXpath("//div[@fieldname='disburse_eff_dt']/input");
	public static Locator Principal = Locator.byXpath("//div[@fieldname='configField50399']/input");
	public static Locator AccruedInterest = Locator.byXpath("//div[@fieldname='configField53108']/input");
	public static Locator AccruedFees = Locator.byXpath("//div[@fieldname='configField53109']/input");
	public static Locator GetPayOffBtn = Locator.byXpath("//a[@fieldname='Get Payoff']");
	

}
