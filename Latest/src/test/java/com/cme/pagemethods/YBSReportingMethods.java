package com.cme.pagemethods;

import com.cme.pages.BorrowerRiskTypesPage;
import com.cme.pages.YBSReportingPage;
import com.fcbt.taf.core.driver.ui.Locator;
import com.fcbt.taf.core.reporting.Reporter;

public class YBSReportingMethods extends CmeBasePage{
    public void VerifyQualifiedYesNo(int i){
        String young        =multiValueMap.get("Young").get(i);
        String beginning    =multiValueMap.get("Beginning").get(i);
        String small        =multiValueMap.get("Small").get(i);

        int count = 0;
        if (!getElementAttribute(YBSReportingPage.QualYNYoung, "value").equalsIgnoreCase(young)) {
            Reporter.fail("Verifying Qualified Young Y/N as " + young);
            count++;
        }
        if (!getElementAttribute(YBSReportingPage.QualYNBegin, "value").equalsIgnoreCase(beginning)) {
            Reporter.fail("Verifying Qualified beginning Y/N as "+ beginning);
            count++;
        }
        if (!getElementAttribute(YBSReportingPage.QualYNSmall, "value").equalsIgnoreCase(small)) {
            Reporter.fail("Verifying Qualified small Y/N as "+ small);
            count++;
        }
        if (count==0) {
            Reporter.pass("Validating YBS calculations for Facility# "+(i+1));
        }
    }

    public void GetYbsValues(int i){
        String val = getElementAttribute(YBSReportingPage.QualYNYoung, "value");
        if (val.equalsIgnoreCase("Y")) {
            val = "Yes";
        } else {
            val = "No";
        }
        YbsYoung.add(i, val);
        val = getElementAttribute(YBSReportingPage.QualYNBegin, "value");
        if (val.equalsIgnoreCase("Y")) {
            val = "Yes";
        } else {
            val = "No";
        }
        YbsBeginner.add(i, val);
        val = getElementAttribute(YBSReportingPage.QualYNSmall, "value");
        if (val.equalsIgnoreCase("Y")) {
            val = "Yes";
        } else {
            val = "No";
        }
        YbsSmall.add(i, val);
    }
}
