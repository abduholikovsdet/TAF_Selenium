package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;


public class GuidelineCompliancePage {

	public static Locator GuidelineComplianceScrn = Locator.byXpath("//a[@fieldname='Guideline Compliance']");
	public static Locator IsLoanTermsLessThanOrEqual360Months_Y = Locator.byXpath("//div[@fieldname = 'Is Loan Terms Less Than or Equal To 360 Months?']/following-sibling::a[1]");
	public static Locator IsLoanTermsLessThanOrEqual360Months_N = Locator.byXpath("//div[@fieldname = 'Is Loan Terms Less Than or Equal To 360 Months?']/following-sibling::a[2]");
	public static Locator IsSolvencyRatioGreaterThanOrEqualTo030_Y = Locator.byXpath("//div[@fieldname = 'Is Solvency Ratio Greater Than or Equal To  0.30?']/following-sibling::a[1]");
	public static Locator IsSolvencyRatioGreaterThanOrEqualTo030_N = Locator.byXpath("//div[@fieldname = 'Is Solvency Ratio Greater Than or Equal To  0.30?']/following-sibling::a[2]");
	public static Locator IsDSCRGreaterThanOrEqualTo115_Y = Locator.byXpath("//div[@fieldname = 'Is DSCR Greater Than or Equal To 1.15?']/following-sibling::a[1]");
	public static Locator IsDSCRGreaterThanOrEqualTo115_N = Locator.byXpath("//div[@fieldname = 'Is DSCR Greater Than or Equal To 1.15?']/following-sibling::a[2]");
	public static Locator IsLTVLessThanOrEqualTo085_Y	 = Locator.byXpath("//div[@fieldname = 'Is LTV Less Than or Equal To 0.85?']/following-sibling::a[1]");
	public static Locator IsLTVLessThanOrEqualTo085_N = Locator.byXpath("//div[@fieldname = 'Is LTV Less Than or Equal To 0.85?']/following-sibling::a[2]");
	public static Locator IsSolvencyRatioGreaterThanOrEqualTo030_Value = Locator.byXpath("//div[@fieldname = 'Is Solvency Ratio Greater Than or Equal To  0.30?']/following-sibling::div[6]/input");
	public static Locator IsLoanTermsLessThanOrEqual360Months_Value = Locator.byXpath("//div[@fieldname = 'Is Loan Terms Less Than or Equal To 360 Months?']/following-sibling::div[6]/input");
	public static Locator IsLTVLessThanOrEqualTo085_Value = Locator.byXpath("//div[@fieldname = 'Is LTV Less Than or Equal To 0.85?']/following-sibling::div[6]/input");
	public static Locator IsDSCRGreaterThanOrEqualTo115_Value = Locator.byXpath("//div[@fieldname = 'Is DSCR Greater Than or Equal To 1.15?']/following-sibling::div[6]/input");
	public static Locator IsLoanAvLessThanOrEqualTo085_Yes	 = Locator.byXpath("//div[contains(text(),'Loan/AV Less Than or Equal To')]/following::a[1]");
	public static Locator IsLoanAvLessThanOrEqualTo085_No	 = Locator.byXpath("//div[contains(text(),'Loan/AV Less Than or Equal To')]/following::a[2]");
	public static Locator IsLoanAvLessThanOrEqualTo085_NA	 = Locator.byXpath("//div[contains(text(),'Loan/AV Less Than or Equal To')]//following::a[3]");
	public static Locator IsLoanAvLessThanOrEqualTo085_Desc	 = Locator.byXpath("//div[contains(text(),'Loan/AV Less Than or Equal To')]/following-sibling::a[3]/following-sibling::div[1]/textarea");
	public static Locator IsLoanAvLessThanOrEqualTo085_JustCode	 = Locator.byXpath("//div[contains(text(),'Loan/AV Less Than or Equal To')]/following-sibling::a[3]/following-sibling::div[2]/input");
	public static Locator IsLoanAvLessThanOrEqualTo085_Value	 = Locator.byXpath("//div[contains(text(),'Loan/AV Less Than or Equal To')]/following-sibling::div[6]/input");
	public static Locator DoesCredApplicationMeetAll_Y	 = Locator.byXpath("//div[@fieldname = 'Does the Credit Application Meet All the Remaining Loan Underwriting Standards?']/following-sibling::a[1]");
	public static Locator IsLoanTerms5to30_Y= Locator.byXpath("//div[@fieldname = 'Is Loan Term 5 to 30 Years?']/following-sibling::a[1]");
	public static Locator IsLoanTerms5to30_N= Locator.byXpath("//div[@fieldname = 'Is Loan Term 5 to 30 Years?']/following-sibling::a[2]");
	public static Locator IsLoanTerms5to30_NA= Locator.byXpath("//div[@fieldname = 'Is Loan Term 5 to 30 Years?']/following-sibling::a[3]");
	public static Locator IsLoanTerms5to30_Desc = Locator.byXpath("//div[contains(text(),'Is Loan Term 5 to 30 Years?')]/following-sibling::a[3]/following-sibling::div[1]/textarea");
	public static Locator IsLoanTerms5to30_JustCode	 = Locator.byXpath("//div[contains(text(),'Is Loan Term 5 to 30 Years?')]/following-sibling::a[3]/following-sibling::div[2]/input");
	public static Locator IsLoanTerms5to30_Value	 = Locator.byXpath("//div[contains(text(),'Is Loan Term 5 to 30 Years?')]/following-sibling::a[3]/following-sibling::div[6]/input");
	public static Locator IsLAgFastScoreGreaterEqualTo155_Y	 = Locator.byXpath("//div[@fieldname = 'Is AgFast Score Greater Than or Equal To 155?']/following-sibling::a[1]");
	public static Locator IsLAgFastScoreGreaterEqualTo155_N	 = Locator.byXpath("//div[@fieldname = 'Is AgFast Score Greater Than or Equal To 155?']/following-sibling::a[2]");
	public static Locator IsLAgFastScoreGreaterEqualTo155_Desc = Locator.byXpath("//div[contains(text(),'Is AgFast Score Greater Than or Equal To 155?')]/following-sibling::a[3]/following-sibling::div[1]/textarea");
	public static Locator IsLAgFastScoreGreaterEqualTo155_JustCode	 = Locator.byXpath("//div[contains(text(),'Is AgFast Score Greater Than or Equal To 155?')]/following-sibling::a[3]/following-sibling::div[2]/input");
	public static Locator IsLAgFastScoreGreaterEqualTo155_Value	 = Locator.byXpath("//div[contains(text(),'Is AgFast Score Greater Than or Equal To 155?')]/following-sibling::div[6]/input");

}
