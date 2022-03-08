package com.cme.pagemethods;

import com.cme.pages.GuidelineCompliancePage;
import com.fcbt.taf.core.driver.ui.Locator;
import com.fcbt.taf.core.reporting.Reporter;

public class GuidelineCompliancePageMethods extends CmeBasePage{
    /**
     *Author:      Muzaffar A.
     *Description: Verifies Guideline Compliance screen questions
     */
    public void VerifyGuidelineComplianceDefaultSelections(String dealComplx){
        switch (bankNumber){
            case "011":
                if (dealComplx.equalsIgnoreCase("Traditional")) {
                    GCPLBTraditional();
                } else if (dealComplx.equalsIgnoreCase("AgFast")) {
                    GCPLBAgFast();
                }
                break;
            case "001":
                //add CFC here
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + bankNumber);
        }

    }

    /**
     * Author: Muzaffar A
     * Description: Verifies GC questions when deal is PLB Traditional
     */
    public void GCPLBTraditional(){

        if (VerifyRadioButtonChecked(GuidelineCompliancePage.IsSolvencyRatioGreaterThanOrEqualTo030_Y)) {
            Reporter.pass("Yes radio button checked for Is Solvency Ratio Greater Than Or Equal To 0.30?");
        } else {
            Reporter.fail("Yes radio button checked for Is Solvency Ratio Greater Than Or Equal To 0.30?");
        }
        if (VerifyRadioButtonChecked(GuidelineCompliancePage.IsDSCRGreaterThanOrEqualTo115_Y)) {
            Reporter.pass("Is DSCR Greater Than or Equal To 1.15?");
        } else {
            Reporter.fail("Is DSCR Greater Than or Equal To 1.15?");
        }
        if (VerifyRadioButtonChecked(GuidelineCompliancePage.IsLTVLessThanOrEqualTo085_Y)) {
            Reporter.pass("Yes radio button checked for Is LTV Less Than or Equal To 0.85?");
        } else {
            Reporter.fail("Yes radio button checked for IIs LTV Less Than or Equal To 0.85?");
        }
        if (VerifyRadioButtonChecked(GuidelineCompliancePage.IsLoanTermsLessThanOrEqual360Months_Y)) {
            Reporter.pass("Yes radio button checked for Is Loan Terms Less Than or Equal To 360 Months?");
        } else {
            Reporter.fail("Yes radio button checked for Is Loan Terms Less Than or Equal To 360 Months?");
        }

    }

    public void GCPLBAgFast(){

    }



}
