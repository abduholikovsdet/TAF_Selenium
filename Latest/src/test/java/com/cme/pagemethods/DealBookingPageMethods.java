package com.cme.pagemethods;

import com.aventstack.extentreports.model.Report;
import com.cme.pages.DealBookingPage;
import com.cme.pages.FacilityInfoPage;
import com.fcbt.taf.core.reporting.Reporter;
import io.cucumber.java.en_scouse.An;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class DealBookingPageMethods extends CmeBasePage {


    public void FBLDealBooking() {

        try {
            Activity = "FBL: Deal Booking";
            sleep(1000);
            this.driver().switchTo().defaultContent();
            SwitchToCMEFrame();
            sleep(1000);
            clickJS(DealBooking);
            start = System.currentTimeMillis();
            if (isElementExist(ScreenNotSavedMsg, 1)) {
                sleep(500);
                click(NoBtn);
            }
            VerifyScreenName(DealBookingPage.DBScreen);
            SwitchToAngularFrame();
            waitForInVisibile(AngPleaseWait);
            waitForInVisibile(AngPleaseWait);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Deal Booking Screen", String.valueOf(timeElapsed) });
            VerifyBasicInfoAndSaveAll();
            sleep(1000);
            click(DealBookingPage.DealRow);
            waitForInVisibile(AngPleaseWait);
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            ScrollDown();
            Reporter.info("Verifying Deal Level Tabs indicators pre-updating to LIQ");

            if (!getElementAttribute(DealBookingPage.RelationshipsTabIndicator, "uib-tooltip").equalsIgnoreCase("Incomplete")) {
                Reporter.fail("Verifying Relationships tab indicator is incomplete pre-updating");
            }
            if (!getElementAttribute(DealBookingPage.PaymentRulesTabIndicator, "uib-tooltip").equalsIgnoreCase("Complete")) {
                Reporter.fail("Verifying Payment Rules tab indicator is complete");
            }
            if (!getElementAttribute(DealBookingPage.MISAFTabIndicator, "uib-tooltip").equalsIgnoreCase("Complete")) {
                Reporter.fail("Verifying MIS/AF tab indicator is incomplete");
            }
            if (!getElementAttribute(DealBookingPage.PrimaryTabIndicator, "uib-tooltip").equalsIgnoreCase("Incomplete")) {
                Reporter.fail("Verifying Primary tab indicator is incomplete pre-updating");
                scrollingToElement(DealBookingPage.PrimaryTabIndicator);
                Reporter.addScreenshot("PrimaryTabIndicator", "PrimaryTabIndicator");
            }
            VerifyRelationshipTab();
            VerifyPaymentRules();
            VerifyDealMISAF();
//            Facility Level validations start here
            for (int i=0; i<numOfLoans; i++) {
                String FacRowXpath = "//st-grid-box[@fieldname='LIQ_Deals_PostDocs_Grid']/table/tbody//td//span[contains(text(), '"+ FacilityNumber.get(i) +"')]";
                Reporter.info("Clicking Facility level for Facility #"+ (i+1));
                sleep(1000);
                click(GetLocator(FacRowXpath));
                sleep(1000);
                waitForInVisibile(AngPleaseWait);
                sleep(1000);
                waitForInVisibile(AngPleaseWait);
                ScrollDown();
                Reporter.info("Verifying Facility Level Tabs indicators pre-updating to LIQ - Facility #"+ (i+1));
                if (!getElementAttribute(DealBookingPage.RiskTypesTabIndicator, "uib-tooltip").equalsIgnoreCase("Incomplete")) {
                    Reporter.fail("Verifying Risk Types tab indicator is incomplete pre-updating");
                }
                if (!getElementAttribute(DealBookingPage.FacGuarantorsTabIndicator, "uib-tooltip").equalsIgnoreCase("Complete")) {
                    Reporter.fail("Verifying Facility Guarantors tab indicator is complete");
                }
                if (!getElementAttribute(DealBookingPage.FacMISAFTabIndicator, "uib-tooltip").equalsIgnoreCase("Incomplete")) {
                    Reporter.fail("Verifying MIS/AF tab indicator is incomplete pre-updating");
                    Reporter.addScreenshot("MISAF", "MISAF");
                }
                if (!getElementAttribute(DealBookingPage.OutstandingsTabIndicator, "uib-tooltip").equalsIgnoreCase("Incomplete")) {
                    Reporter.fail("Verifying Outstanding tab indicator is incomplete pre-updating");
                }
                Reporter.addScreenshot("Facility level", "Facility level tabs");
                VerifyRiskTypesTab(i);

                if (GuarantorFlg.get(i)) {
                    VerifyFacilityGuarantorTab(i);
                }
                VerifyFacMisAdditionalFieldsTab(i);

            }
            //Navigating to Deal level - Primary tab
            sleep(1000);
            click(DealBookingPage.DealRow);
            waitForInVisibile(AngPleaseWait);
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            VerifyPrimaryTab();
            CloseDeal();

            //Outstanding booking starts here
            for (int i=0; i<numOfLoans; i++) {
                String FacRowXpath = "//st-grid-box[@fieldname='LIQ_Deals_PostDocs_Grid']/table/tbody/tr["+ (i+2)+ "]/td[count(//st-grid-box[@fieldname='LIQ_Deals_PostDocs_Grid']/table/thead/tr/th[text()='Deal/Facility ID']/preceding-sibling::th)+1]";
                Reporter.info("Clicking Facility level for Facility #"+ (i+1));
                sleep(1000);
                click(GetLocator(FacRowXpath));
                sleep(1000);
                waitForInVisibile(AngPleaseWait, 2000);
                sleep(500);

                if (isWebElementVisible(GetLocator("//pre[contains(text(), 'TypeError: Cannot read property ')]"))) {
                    sleep(500);
                    clickJS(AngOKBtn);
                    waitForInVisibile(AngPleaseWait, 2000);
                }
//                VerifyAndBookOutstandingTab(i);

            }

        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }

    }


    public void VerifyBasicInfoAndSaveAll(){
        List<WebElement> DBGridRows = this.driver().findElements(By.xpath("//st-grid-box[@fieldname='LIQ_Deals_PostDocs_Grid']/table/tbody/tr"));
        try {
            for(int i=0; i<DBGridRows.size(); i++){
                DBGridRows.get(i).click();
                start = System.currentTimeMillis();
                waitForInVisibile(AngPleaseWait);
                clickJS(DealBookingPage.ExpandAll);
                if (i==0) {
                    finish = System.currentTimeMillis();
                    timeElapsed = (finish - start)/1000;
                    PerfMetrics.add(new String[] { Activity, "Deal Level Loading", String.valueOf(timeElapsed) });
                    VerifyDealBasicinfo();

                }else{
                    finish = System.currentTimeMillis();
                    timeElapsed = (finish - start)/1000;
                    PerfMetrics.add(new String[] { Activity, "Facility Level Loading", String.valueOf(timeElapsed) });
                    VerifyFacilityBasicInfo(i-1);
                    VerifyPricingDetailsGrid(i-1);
                }
            }
            sleep(1000);
            clickJS(DealBookingPage.SaveAllBtn);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            waitForClickable(DealBookingPage.SaveAllBtn);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Save All", String.valueOf(timeElapsed) });
            if (isWebElementVisible(DealBookingPage.SaveAllSuccess)) {
                Reporter.pass("Save All: All ready deals have been booked and updated");
                Reporter.addScreenshot("Save All", "Save All");
                sleep(1000);
                click(AngOKBtn);
                start = System.currentTimeMillis();
                sleep(1000);
                waitForInVisibile(AngPleaseWait);
                waitForInVisibile(AngPleaseWait);

                int failCount   =0;
                for (int i=1; i<=DBGridRows.size(); i++) {
                    String tablexpath = "//st-grid-box[@fieldname='LIQ_Deals_PostDocs_Grid']/table";
                    String LIQStatusCol =tablexpath+ "/tbody/tr["+ i +"]/td[count("+ tablexpath +"/thead/tr/th[text()='Loan IQ Status']/preceding-sibling::th)+1]";
                    String LIQIDCol =tablexpath+ "/tbody/tr["+ i +"]/td[count("+ tablexpath +"/thead/tr/th[text()='Loan IQ ID']/preceding-sibling::th)+1]";
                    String StatusCol =tablexpath+ "/tbody/tr["+ i +"]/td[count("+ tablexpath +"/thead/tr/th[text()='Status']/preceding-sibling::th)+1]";

                    if (!getElementText(GetLocator(LIQStatusCol)).equalsIgnoreCase("Pending")) {
                       Reporter.fail("LIQ Status Column displays Pending"+ getElementText(GetLocator(LIQStatusCol)));
                        failCount++;
                    }
                    if (getElementText(GetLocator(LIQIDCol)).isEmpty()) {
                        Reporter.fail("LIQ ID Column: "+ getElementText(GetLocator(LIQIDCol)));
                        failCount++;
                    }
                    if (!getElementText(GetLocator(StatusCol)).isEmpty()) {
                        Reporter.fail("Status Column: "+ getElementText(GetLocator(StatusCol)));
                        failCount++;
                    }
                    if (failCount==0) {
                        Reporter.pass("LIQ Status Column displays Pending");
                        finish = System.currentTimeMillis();
                        timeElapsed = (finish - start)/1000;
                    }

                }
                PerfMetrics.add(new String[] { Activity, "Save All - OK", String.valueOf(timeElapsed) });


            } else {
                Reporter.fail("Save All: All ready deals have been booked and updated");
                Assert.fail("Save All: All ready deals have been booked and updated");
            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }


    /**
     * Author: Raminaidu
     */
    public void VerifyDealBasicinfo() {
        Reporter.info("Navigating to Deal level to verify Basic Info");
        try {
            String Aggrementdate = dataMap.get("Target Close date");
            String Branch = dataMap.get("Process Center");
            String Currency = "USD";
            String classification = "General Corp Purpose";
            double AggrDebt 	=0;
			for (int j=0; j<numOfLoans; j++){
				AggrDebt = AggrDebt + Double.parseDouble(multiValueMap.get("Decision Amount").get(j).replace(",", ""));
			}
            String Department = GetFBLDepartment();
            String primaryborrower = multiValueMap.get("Full Name").get(0);
            String Expensecode = GetFBLExpenseCode();
            int failCount = 0;
            //Verification starts here
            //Deal alias
            if (!getElementText(DealBookingPage.dealAlias).equalsIgnoreCase(dealAls)) {
                Reporter.fail("Verifying deal alias as " + dealAls);
                failCount++;
            }
            //Aggrement date
            if (!getElementAttribute(DealBookingPage.dealAgreementDate, "value").contains(Aggrementdate)) {
                Reporter.fail("Verifying deal agreement date in Deal basic information. Expected value: " + Aggrementdate);
                failCount++;
            }

            //branch
            if (!getElementText(DealBookingPage.branch).equalsIgnoreCase(Branch)) {
                Reporter.fail("Verifying branch in Deal basic information. Expected value: " + Branch);
                failCount++;
            }
            //Currency
            if (!getElementText(DealBookingPage.currency).equalsIgnoreCase("USD")) {
                Reporter.fail("Verifying Currency in Deal basic information. Expected value: USD");
                failCount++;
            }
            //classification
            if (!getElementText(DealBookingPage.classification).equalsIgnoreCase(classification)) {
                Reporter.fail("Verifying classification in Deal basic information. Expected value: " + classification);
                failCount++;
            }
            //Deal amount
            double TtlLnAmt = Double.parseDouble(getElementText(DealBookingPage.dealAmount).replace(",", ""));
            if (TtlLnAmt!=AggrDebt) {
                Reporter.fail("Verifying Deal amount in Deal basic information. Expected value: " + AggrDebt);
                failCount++;
            }
            //Department
            if (!getElementText(DealBookingPage.department).contains(Department)) {
                Reporter.fail("Verifying department in Deal basic information. Expected value: " + Department);
                failCount++;
            }
            //primary brrower
            if (!getElementText(DealBookingPage.primaryBorrower).equalsIgnoreCase(primaryborrower)) {
                Reporter.fail("Verifying primary borrower as " + primaryborrower);
                failCount++;
            }
            if (!getElementText(DealBookingPage.expensecode).equalsIgnoreCase(Expensecode)) {
                Reporter.fail("Verifying Expense code in Deal basic information. Expected value: " + Expensecode);
                failCount++;
            }
//        Pricing Options check will be added
//        if (!getElementText(DealBookingPage.pricingoptions).contains(Branch)) {
//            Reporter.fail("Verifying process center in pricing options " + Branch);
//            Reporter.addScreenshot("Deal basic info", "Process center verification in pricing options");
//            failCount++;
//        }
            if (failCount == 0) {
                Reporter.pass("Deal basic information Validations");

            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
        Reporter.addScreenshot("Deal basic information", "Deal basic information");
    }

    /**
     * Author: Raminaidu
     * Modified By: Muzaffar A.
     */
    public void VerifyRelationshipTab() {
        Reporter.info("Relationships tab validations");
        sleep(1000);
        try {
            clickJS(DealBookingPage.RelationshipsTab);
            waitForInVisibile(AngPleaseWait);
            ScrollDown();
            int failCount = 0;
            String CustSG=null;
            String CustName=null;
            String role=null;
            String LIQPrfType=null;
            List<WebElement> RelTabGridRows = this.driver().findElements(By.xpath("//st-grid-box[@fieldname='Servicing_Group_Grid']/table/tbody/tr"));
            for (int i = 0; i < RelTabGridRows.size(); i++) {
                if (i==0) {
                    CustName        =ProcessCenter;
                    role            ="Admin Agent";
                    CustSG          ="SG";
                    LIQPrfType      =role;
                }else{
                    LIQPrfType       =multiValueMap.get("LIQ Profile Type").get(i-1);
                    if (LIQPrfType.equalsIgnoreCase("Guarantor")) {
                        break;
                    }
                    CustName         =multiValueMap.get("Legal Name").get(i-1);
                    role             =multiValueMap.get("Relationship Type").get(i-1);
                    CustSG           =ServiceGroup.get(i-1);

                }
                String PrefRI           ="EXTCC1";
                String CheckedFlag      ="form-control ng-pristine ng-untouched ng-valid ng-not-empty";
                String UncheckedFlag    ="form-control ng-pristine ng-untouched ng-valid ng-empty";
                String CheckBxDefault   =null;

                if (LIQPrfType.equalsIgnoreCase("Borrower")) {
                    CheckBxDefault      =CheckedFlag;
                }else{
                    CheckBxDefault      =UncheckedFlag;
                }


                RelTabGridRows.get(i).click();
                sleep(1000);
                waitForInVisibile(AngPleaseWait);
                sleep(1000);

                if (!getElementText(DealBookingPage.RelTabCustName).equalsIgnoreCase(CustName)) {
                    Reporter.fail("Relationship tab Customer Name Not matched");
                    failCount++;
                }
                if (!getElementText(DealBookingPage.RelTabLocation).equalsIgnoreCase(GetLenderLocationCode())) {
                    Reporter.fail("Relationship tab Location Not matched. Actual: "+ getElementText(DealBookingPage.RelTabLocation));
                    failCount++;
                }
                if (i==0) {
                    if (!bankNumber.equalsIgnoreCase("001")) {
                        if (!getElementText(DealBookingPage.RelTabSG).equalsIgnoreCase(CustSG)) {
                            Reporter.fail("Relationship tab Servicing Group Not matched");
                            failCount++;
                        }
                    }else{
                        clickJS(DealBookingPage.RelTabDD);
                        String SGXpath = "//button[@fieldname='Servicing_Group_btn']/following-sibling::div//td[text() = 'SG']";
                        sleep(1000);
                        clickJS(GetLocator(SGXpath));
                        sleep(1000);
                        waitForInVisibile(AngPleaseWait);

                    }

                }else{
                    if (!getElementText(DealBookingPage.RelTabSG).equalsIgnoreCase(CustSG)) {
                        Reporter.fail("Relationship tab Servicing Group Not matched");
                        failCount++;
                    }
                }

                if (i!=0) {
                    if (!getElementText(DealBookingPage.RelTabPreferredRI).equalsIgnoreCase(PrefRI)) {
                        Reporter.fail("Relationship tab Preferred RI Not matched");
                        failCount++;
                    }
                }
                if (!getElementText(DealBookingPage.RelTabRole).equalsIgnoreCase(role)) {
                    Reporter.fail("Relationship tab Role Not matched");
                    failCount++;
                }
                if (!getElementAttribute(DealBookingPage.RelTabBorrowerIndicator, "class").contains(CheckBxDefault)) {
                    Reporter.fail("Relationship tab Borrower Checkbox default Not matched");
                    failCount++;
                }
                if (!getElementAttribute(DealBookingPage.RelTabDepositorIndicator, "class").contains(CheckBxDefault)) {
                    Reporter.fail("Relationship Depositor Checkbox default Not matched");
                    failCount++;
                }

            }
            if (failCount==0) {
                Reporter.pass("Relationships tab Defaulting rules validations");
            }
            Reporter.addScreenshot("Relationships tab", "Relationships tab");

            click(DealBookingPage.updateRS);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Relationships Update", String.valueOf(timeElapsed) });
            String tablexpath = "//fieldset[@class='Servicing_Group_Grid']//table";
            failCount=0;
            for (int j=1; j<RelTabGridRows.size(); j++) {
                String LIQStatusCol =tablexpath+ "/tbody/tr["+ j +"]/td[count("+ tablexpath +"/thead/tr/th[text()='Message']/preceding-sibling::th)+1]";
                if (!getElementText(GetLocator(LIQStatusCol)).equalsIgnoreCase("Updated to Loan IQ")) {
                    failCount++;
                }
            }
            if ((failCount==0) && (getElementAttribute(DealBookingPage.RelationshipsTabIndicator, "uib-tooltip").equalsIgnoreCase("Complete"))) {
                Reporter.pass("Relationships tab Updating Successful");

            }else{
                Reporter.fail("Relationships tab Updating Successful");
                Assert.fail("Relationships tab Updating Successful");
            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }


    }

    public void VerifyPaymentRules(){
        try {
            Reporter.info("Payment Rules tab validations");
            sleep(1000);
            clickJS(DealBookingPage.PaymentRulesTab);
            start = System.currentTimeMillis();
            waitForInVisibile(AngPleaseWait);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Payment Rules tab Load", String.valueOf(timeElapsed) });
            if (isWebElementVisible(GetLocator("//div[contains(text(),'I,P,E,L,F,R')]"))) {
                Reporter.pass("Payment Rules");
            } else {
                Reporter.fail("Payment Rules");
            }
            Reporter.addScreenshot("Payment Rules", "Payment Rules");
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }
    public void VerifyDealMISAF(){
        try {
            Reporter.info("Deal level MIS/AF tab");
            sleep(1000);
            clickJS(DealBookingPage.DealLvlMISAFTab);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Payment Rules tab Load", String.valueOf(timeElapsed) });
            Reporter.addScreenshot("Deal level MISAF tab", "Deal level MISAF tab");
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Author: Raminaidu
     */
    //Deal primary tab
    public void VerifyPrimaryTab() {
        sleep(1000);
        try {
            ScrollDown();
            sleep(500);
            clickJS(DealBookingPage.primaryTab);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Primary tab Load", String.valueOf(timeElapsed) });
            ScrollDown();
            int failCount = 0;
            String portfolioExpCode = null;
            String lenderCode = GetLenderCode();
            if (!getElementText(DealBookingPage.lender).equalsIgnoreCase(ProcessCenter)) {
                Reporter.fail("Verifying lender field in primary tab. Expected value: " + ProcessCenter);
                failCount++;
            }
            String lenderLocation = GetLenderLocationCode();
            if (!getElementText(DealBookingPage.lenderLocation).equalsIgnoreCase(lenderLocation)) {
                Reporter.fail("Verifying lender location field in primary tab. Expected value: " + lenderLocation);
                failCount++;
            }
            if (failCount == 0) {
                Reporter.pass("Primary tab Validations");


            } else {
                Reporter.fail("Primary tab Validations");
            }

            clickJS(DealBookingPage.riskBookDD);
            //Select riskbook dropdown value
            String Riskbook = GetRiskBook();
            String riskBookXpath = "//button[@fieldname='Risk_Book_btn']/following-sibling::div//td[text() = '"+ Riskbook +"']";
            sleep(500);
            clickJS(GetLocator(riskBookXpath));
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Primary Risk Book Load", String.valueOf(timeElapsed) });
            String expCode = GetPortfolioExpenseCode();
//            if (!BLStk) {
//                portfolioExpCode = "Hold for Investment - Non Taxable - "+ expCode;
//            }else{
//                portfolioExpCode = "Hold for Investment - Taxable - "+ expCode;
//            }
            sleep(1000);
            clickJS(DealBookingPage.portfolioExpenseDD);
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            clickAngularDDElement(expCode);
            click(DealBookingPage.createPrimaryCircle);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            waitForVisible(DealBookingPage.DBSuccessMsgPopup);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Create Primary Circle", String.valueOf(timeElapsed) });
            if (getElementText(DealBookingPage.DBSuccessMsgPopup).equalsIgnoreCase("Primary circle has been created successfully.")) {
                sleep(1000);
                clickJS(AngOKBtn);
                sleep(1000);
                waitForInVisibile(AngPleaseWait);
                Reporter.pass("Primary circle has been created successfully");
            } else {
                Reporter.fail("Primary circle has been created successfully");
            }
            if (!getElementAttribute(DealBookingPage.PrimaryTabIndicator, "uib-tooltip").equalsIgnoreCase("Complete")) {
                Reporter.fail("Verifying Primary tab indicator is complete post-updating");
            }
            Reporter.addScreenshot("Primary tab", "Primary tab verifications");
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }

    }



    /**
     * Author: PadminiB
     */
    public void VerifyFacilityBasicInfo(int i) {

        try {
            String amount = multiValueMap.get("Decision Amount").get(i);
            String primaryBorrower = multiValueMap.get("Full Name").get(0);
            String effectiveDate = multiValueMap.get("Loan Effective Date").get(i);
            String expirationDate = null;
            String maturityDate = null;
            maturityDate = multiValueMap.get("Maturity Date").get(i);
            if (multiValueMap.get("Pricing Type").get(i).equalsIgnoreCase("Fixed") || multiValueMap.get("Pricing Type").get(i).equalsIgnoreCase("Prime")) {
                expirationDate = maturityDate;

            }else{
                expirationDate = multiValueMap.get("Advance Expiration Date").get(i);

            }

            String purpose = multiValueMap.get("Loan Purpose").get(i);
            String ProductType =multiValueMap.get("Product Type").get(i);

            int failCount = 0;
//            if (!getElementText(DealBookingPage.FacId).contains(LoanID.get(i)+ "-" + ProductType)) {
//                Reporter.fail("Verifying Facility Name in Facility Basic Info section. Expected value: " + LoanID.get(i)+ "-" + ProductType);
//                failCount++;
//            }
            if (!getElementText(DealBookingPage.Type).equalsIgnoreCase(GetFacilityType(ProductType))) {
                Reporter.fail("Verifying Facility Type in Facility Basic Info section. Expected value: "+ GetFacilityType(ProductType));
                failCount++;
            }
            if (!getElementText(DealBookingPage.Currency).equalsIgnoreCase("USD")) {
                Reporter.fail("Verifying  Currency in Facility Basic Info section. Expected value: USD");
               failCount++;
            }
            if (!getElementText(DealBookingPage.Amount).equalsIgnoreCase(amount)) {
                Reporter.fail("Verifying  Amount in Facility Basic Info section. Expected value: " + amount);
                failCount++;
            }
            if (!getElementText(DealBookingPage.PrimaryBorrower).equalsIgnoreCase(primaryBorrower)) {
                Reporter.fail("Verifying  Primary Borrower in Facility Basic Info section. Expected value: " + primaryBorrower);
               failCount++;
            }
            if (numOfEnts>1) {
                for (int j=0; j<numOfEnts; j++) {
                    if (j!=0) {

                        if (!multiValueMap.get("Relationship Type").get(j).equalsIgnoreCase("Guarantor")) {
                            String secondaryBorrower = multiValueMap.get("Full Name").get(j);
                            if (!getElementText(DealBookingPage.SecondaruBorrower).contains(secondaryBorrower)) {
                                Reporter.fail("Verifying  Secondary Borrowers in Facility Basic Info section. Expected value: " + secondaryBorrower);
                                failCount++;
                            }
                        }
                    }
                }
            }

            if (!getElementAttribute(DealBookingPage.Effectivedate, "value").equalsIgnoreCase(effectiveDate)) {
                Reporter.fail("Verifying Effective Date in Facility Basic Info section. Expected value: " + effectiveDate);
                failCount++;
            }

            if (!getElementText(DealBookingPage.Expirationdate).equalsIgnoreCase(expirationDate)) {
                Reporter.fail("Verifying Expiration Date in Facility Basic Info section. Expected value: " + expirationDate);
                failCount++;
            }

            if (!getElementText(DealBookingPage.Maturitydate).equalsIgnoreCase(maturityDate)) {
                Reporter.fail("Verifying maturity Date in Facility Basic Info section. Expected value: " + maturityDate);
                failCount++;
            }

            if (!getElementText(DealBookingPage.Purpose).equalsIgnoreCase(purpose)) {
                Reporter.fail("Verifying Purpose in Facility Basic Info section. Expected value: " + purpose);
                failCount++;
            }
            if (failCount != 0) {
                Reporter.fail(failCount + " Basic Information Validation for Facility# " + (i + 1));
            } else {
                Reporter.pass("Basic Information Validation for Facility# " + (i + 1));

            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
        Reporter.addScreenshot("Fac level basic info", "Fac level basic info");

    }

    public void VerifyPricingDetailsGrid(int i){
        Reporter.info("Verifying Pricing Details grid for Facility# "+ (i+1));

    }

    /**
     * Author: PadminiB
     */
    public void VerifyRiskTypesTab(int i) {
        Reporter.info("Risk Types tab validations");
        sleep(1000);
        try {
            clickJS(DealBookingPage.RiskTypesTab);
            waitForInVisibile(AngPleaseWait);
            ScrollDown();
            List<WebElement> RiskTypesGridRows = this.driver().findElements(By.xpath("//st-grid-box[@fieldname='Risk_Type_Grid']/table/tbody/tr"));

            int failCount = 0;
            //Risk Types validations code will be added
            sleep(1000);
            clickJS(DealBookingPage.bookRiskTypes);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Risk Types Book", String.valueOf(timeElapsed) });

            String tablexpath = "//st-grid-box[@fieldname='Risk_Type_Grid']/table";
            for (int k=1; k<=RiskTypesGridRows.size(); k++) {
                String StatusCol =tablexpath+ "/tbody/tr["+ k +"]/td[count("+ tablexpath +"/thead/tr/th[text()='Status']/preceding-sibling::th)+1]";
                if (!getElementText(GetLocator(StatusCol)).equalsIgnoreCase("Booked")) {
                    failCount++;
                }
            }
            if ((failCount==0) && (getElementAttribute(DealBookingPage.RiskTypesTabIndicator, "uib-tooltip").equalsIgnoreCase("Complete"))) {
                Reporter.pass("Risk Types tab Updating Successful");
            }else{
                Reporter.fail("Risk Types tab Updating Successful");
                Assert.fail("Risk Types tab Updating Successful");
            }

            Reporter.addScreenshot("Risk Types tab", "Risk Types tab");
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }

    }

    /**
     * Author: PadminiB
     */
    public void VerifyFacilityGuarantorTab(int i) {
        try {
            sleep(2000);
            clickJS(DealBookingPage.FacilityGuarantorTab);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Facility Guarantors Tab Load", String.valueOf(timeElapsed) });
            int failCount = 0;
            String CustNameXpath = null;
            String RoleXpath = null;
            String GuarTypeXpath = null;
            String GuarProgram = null;
            String LimitPctXpath = null;
            String bookStsXpath = null;
            String tableXpath = "//st-grid-box[@fieldname='Guarantor_Grid']/table";
            ArrayList <String> guars = new ArrayList<String>();
            ArrayList <String> guarType = new ArrayList<String>();
            ArrayList <String> programYN = new ArrayList<String>();
            guars.add(0, Guarantors.get(i));
            guarType.add(0, GuaranteeType.get(i));
            programYN.add(0, GuaranteePrgrmYN.get(i));
            if (GuaranteePrgrmFlg.get(i)) {
                if (multiValueMap.get("Guarantee Program").get(i).equalsIgnoreCase("Yes")) {
                    guars.add(guars.size(), multiValueMap.get("Guarantee Programs").get(i).replace(" Guarantee", ""));
                    guarType.add(guarType.size(), multiValueMap.get("Guarantee Programs").get(i));
                    programYN.add(programYN.size(), "Yes");
                }
            }
            List<WebElement> tRows = this.driver().findElements(By.xpath(tableXpath +"/tbody/tr"));
            for (int r=0; r<tRows.size(); r++){
                tRows.get(r).click();
                waitForInVisibile(AngPleaseWait, 1000);
            }
            Reporter.info("Updating Facility Guarantors");
            clickJS(DealBookingPage.facUpdateGuarantors);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait, 1000);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Facility Guarantors Update", String.valueOf(timeElapsed) });
            Reporter.addScreenshot("Facility Guarantors", "Facility Guarantors");

            for (int r=1; r<=tRows.size(); r++){
                CustNameXpath = tableXpath + "/tbody/tr["+ r +"]/td[count(" + tableXpath + "/thead/tr/th[text()='Customer Name']/preceding-sibling::th)+1]";
                RoleXpath = tableXpath + "/tbody/tr["+ r +"]/td[count(" + tableXpath + "/thead/tr/th[text()='Role']/preceding-sibling::th)+1]";
                GuarTypeXpath = tableXpath + "/tbody/tr["+ r +"]/td[count(" + tableXpath + "/thead/tr/th[text()='Guaranty Type']/preceding-sibling::th)+1]";
                GuarProgram = tableXpath + "/tbody/tr["+ r +"]/td[count(" + tableXpath + "/thead/tr/th[text()='Guaranty Program']/preceding-sibling::th)+1]";
                LimitPctXpath = tableXpath + "/tbody/tr["+ r +"]/td[count(" + tableXpath + "/thead/tr/th[text()='Limit Percent']/preceding-sibling::th)+1]";
                bookStsXpath = tableXpath + "/tbody/tr["+ r +"]/td[count(" + tableXpath + "/thead/tr/th[text()='Status']/preceding-sibling::th)+1]";

                if (!getElementText(GetLocator(CustNameXpath)).equalsIgnoreCase(guars.get(r-1))) {
                    Reporter.fail("Guarantor Name");
                    failCount++;
                }
                if (!getElementText(GetLocator(RoleXpath)).equalsIgnoreCase("Guarantor")) {
                    Reporter.fail("Guarantor Role");
                    failCount++;
                }
                if (!getElementText(GetLocator(GuarProgram)).equalsIgnoreCase(programYN.get(r-1))) {
                    Reporter.fail("Guarantee Program - Yes/No");
                    failCount++;
                }
                if (!getElementText(GetLocator(GuarTypeXpath)).equalsIgnoreCase(guarType.get(r-1))) {
                    Reporter.fail("Guarantee Type");
                    failCount++;
                }
                if (!getElementText(GetLocator(bookStsXpath)).equalsIgnoreCase("Updated to Loan IQ")) {
                    Reporter.fail("Updated to Loan IQ");
                    failCount++;
                }
            }
            if (failCount==0) {
                Reporter.pass("Facility Guarantors");
            }else{
                Reporter.fail("Facility Guarantors");
                Assert.fail("Facility Guarantors");
            }
        } catch (Exception e) {
            Assert.fail("Facility Guarantors: \n"+ e.getMessage());
        }


    }

    /**
     * Author: PadminiB
     * Description: Validating Facility Mis/Additional Fields  values
     */

    public void VerifyFacMisAdditionalFieldsTab(int i) {

        sleep(1000);
        try {
            clickJS(DealBookingPage.facMisAddFields);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "MIS/AF Facility level", String.valueOf(timeElapsed) });
            Reporter.info("Clicking Additional Information tab for Facility# " + (i + 1));
            sleep(1000);
            String beginningFarmer = YbsBeginner.get(i);
            String committed = "Committed";
            String smallFarmer = YbsSmall.get(i);
            String capitalAllocation = "100% Allocation";
            String secured = "Secured";
            String youngFarmer = YbsYoung.get(i);
            String charteredTerritory = "In";
            String collateralDescription = multiValueMap.get("Display Description").get(i);
            String primaryRepayment = "111110-Soybean Farming";
            String ppCalculateType = null;
            String PrePmntOption = multiValueMap.get("Prepayment Option").get(i);
            String pppEffectiveDate = multiValueMap.get("Loan Effective Date").get(i);

            int failCount = 0;
            if (!getElementText(DealBookingPage.BeginningFarmer).contains(beginningFarmer)) {
                Reporter.fail("Verifying Beginning Farmer value in Facility Mis Codes section." + beginningFarmer);
                failCount++;
            }
            if (!getElementText(DealBookingPage.CommittedUncommited).equalsIgnoreCase(committed)) {
                Reporter.fail("Verifying Committed/UnCommitted value in Facility Mis Codes section " + committed);
                failCount++;
            }
            if (!getElementText(DealBookingPage.SmallFarmer).contains(smallFarmer)) {
                Reporter.fail("Verifying Small Farmer value in Facility Mis Codes section " + smallFarmer);
                failCount++;
            }
            if (!getElementText(DealBookingPage.CapitalAllocation).equalsIgnoreCase(capitalAllocation)) {
                Reporter.fail("Verifying Capital Allocation value in Facility Mis Codes section " + capitalAllocation);
                failCount++;
            }
            if (!getElementText(DealBookingPage.Secured).equalsIgnoreCase(secured)) {
                Reporter.fail("Verifying Secured value in Facility Mis Codes section" + secured);
                failCount++;
            }
            if (!getElementText(DealBookingPage.YoungFarmer).contains(youngFarmer)) {
                Reporter.fail("Verifying Young Farmer value in Facility Mis Codes section " + youngFarmer);
                failCount++;
            }
            if (!getElementText(DealBookingPage.CharteredTerritory).equalsIgnoreCase(charteredTerritory)) {
                Reporter.fail("Verifying Chartered Territory value in Facility Mis Codes section " + charteredTerritory);
                failCount++;
            }
            if (numOfCollaterals==1) {
                if (!getElementAttribute(DealBookingPage.CollateralDescription, "value").equalsIgnoreCase(collateralDescription)) {
                    Reporter.fail("Verifying Collateral Description value in Facility Additional Fields section " + collateralDescription);
                    scrollingToElement(DealBookingPage.CollateralDescription);
                    Reporter.addScreenshot("CollateralDescription", "CollateralDescription");
                    failCount++;
                }
            }
            if (!getElementText(DealBookingPage.PrimaryRepayment).equalsIgnoreCase(primaryRepayment)) {
                Reporter.fail("Verifying Primary Repayment value in Facility Additional Fields section " + primaryRepayment);
                failCount++;
            }

            if (PrePmntOption.equalsIgnoreCase("OPO")) {
                ppCalculateType = "Open Prepay";
            }else if (PrePmntOption.equalsIgnoreCase("PLO")) {
                ppCalculateType = "Period Lockout (5-4-3-2-1)";
            }else if (PrePmntOption.equalsIgnoreCase("OLO")) {
                ppCalculateType = "Period Lockout (1 Year)";
            }else if (PrePmntOption.equalsIgnoreCase("FYM")) {
                ppCalculateType = "Full Yield Maintenance";
            }

            if (!getElementText(DealBookingPage.PPCalType).equalsIgnoreCase(ppCalculateType)) {
                Reporter.fail("Verifying Prepayment Penalty Calc Type value in Facility Additional Fields  section " + ppCalculateType);
                failCount++;
            }

            ScrollDown();
            switch (PrePmntOption) {
                case "OPO":
                case "FYM":
                    if (!getElementAttribute(DealBookingPage.PPPEffectiveDate, "value").isEmpty()) {
                        Reporter.fail("Verifying Prepayment Penalty Effective Date value in Facility Additional Fields  section.");
                        failCount++;
                    }

                    break;

                case "OLO":
                case "PLO":
                    if (!getElementAttribute(DealBookingPage.PPPEffectiveDate, "value").equalsIgnoreCase(pppEffectiveDate)) {
                        Reporter.fail("Verifying Prepayment Penalty Effective Date value in Facility Additional Fields  section.");
                        failCount++;
                    }
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + PrePmntOption);
            }

            sleep(1000);
            clickJS(DealBookingPage.BookMISAFButton);
            start = System.currentTimeMillis();
            waitForVisible(DealBookingPage.DBSuccessMsgPopup);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "MIS/AF Facility level Update", String.valueOf(timeElapsed) });
            if (getElementText(DealBookingPage.DBSuccessMsgPopup).equalsIgnoreCase("Update was successful.")) {
                sleep(1000);
                clickJS(AngOKBtn);
                sleep(1000);
                waitForInVisibile(AngPleaseWait);
                sleep(1000);
                waitForInVisibile(AngPleaseWait);
                if (!getElementAttribute(DealBookingPage.FacMISAFTabIndicator, "uib-tooltip").equalsIgnoreCase("Complete")) {
                    Reporter.fail("Verifying MIS/AF tab indicator is complete");
                }else{
                    Reporter.pass("MIS/AF Update was successful");
                }
            }else{
                Reporter.fail("MIS/AF Update was successful");
            }

            if (failCount != 0) {
                Reporter.fail(failCount + " Failed Validations found in MIS/AF tab");
            }
            Reporter.addScreenshot("MISAF tab", "MISAF tab");
        } catch (IllegalStateException e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Author: PadminiB
     */

    public void VerifyAndBookOutstandingTab(int i) {
        if (isWebElementVisible(GetLocator("//pre[contains(text(), 'TypeError: Cannot read property ')]"))) {
            sleep(500);
            clickJS(AngOKBtn);
            waitForInVisibile(AngPleaseWait, 2000);
        }
        Reporter.info("Navigating to Outstanding tab for Facility# " + (i + 1));

        String PricingType = multiValueMap.get("Pricing Type").get(i);
        String FXTM = multiValueMap.get("Fixed To Maturity").get(i);
        String RepriceOption = multiValueMap.get("Reprice Option").get(i);
        String tablexpath = "//st-grid-box[@fieldname='OutstandingsGridBox']/table";
        String ostLiqStatus = tablexpath + "/tbody/tr[1]/td[count(" + tablexpath + "/thead/tr/th[text()='Status']/preceding-sibling::th)+1]";
        boolean OSTFreqDD=false;
        if (PricingType.equalsIgnoreCase("Fixed") && FXTM.equalsIgnoreCase("No")) {
            OSTFreqDD = true;
            String[] ArrRO = RepriceOption.split(" ");
            RepriceOption = ArrRO[0] + ArrRO[1].charAt(0);
        }

        try {
            ScrollDown();
            sleep(1000);
            clickJS(DealBookingPage.outstandingTab);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Outstanding Tab Load", String.valueOf(timeElapsed) });
            ScrollDown();
            sleep(500);
            ScrollDown();
            sleep(500);
            if (OSTFreqDD) {
                clickJS(DealBookingPage.OSTFrequency);
                clickAngularDDElement(RepriceOption);
            }
            clickJS(DealBookingPage.BookOSTButton);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Outstanding Book", String.valueOf(timeElapsed) });

            if (getElementText(GetLocator(ostLiqStatus)).equalsIgnoreCase("Successfully booked to LIQ.")) {
                Reporter.pass("Outstanding Successfully booked to LIQ");
            }else{
                Reporter.fail("Outstanding Successfully booked to LIQ");
                Assert.fail("Outstanding Successfully booked to LIQ");
            }
            Reporter.addScreenshot("OST Booked", "OST Booked");

            sleep(1000);
            waitForInVisibile(AngPleaseWait, 2000);
            if (BookRPSFlg.get(i)) {
                sleep(1000);
                clickJS(DealBookingPage.RepaymentScheduleButton);
                start = System.currentTimeMillis();
                sleep(1000);
                waitForInVisibile(AngPleaseWait);
                finish = System.currentTimeMillis();
                timeElapsed = (finish - start)/1000;
                PerfMetrics.add(new String[] { Activity, "Repayment Schedule Book", String.valueOf(timeElapsed) });
                if (getElementText(GetLocator(ostLiqStatus)).equalsIgnoreCase("Booking of Repayment Schedule Successful")) {
                    Reporter.pass("Booking of Repayment Schedule Successful");
                }else{
                    Reporter.fail("Booking of Repayment Schedule Successful");
                    Assert.fail("Booking of Repayment Schedule Successful");
                }

                Reporter.addScreenshot("RPS Booked", "RPS Booked");
            }

        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Author: PadminiB
     * Modified By: Muzaffar
     */

    public void CloseDeal() {
        try {
            sleep(1000);
            click(DealBookingPage.closeDealBtn);
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            clearInput(DealBookingPage.closeDate);
            sleep(500);
            String closedate = getElementAttribute(DealBookingPage.agreementDate, "value");
            inputText(DealBookingPage.closeDate, closedate);
            sleep(1000);
            click(DealBookingPage.closeDeal);
            start = System.currentTimeMillis();
            sleep(3000);
            waitForInVisibile(AngPleaseWait);
            waitForClickable(GetLocator("//*[text()='Info:']/parent::div/parent::div"));
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Deal Successfully Closed", String.valueOf(timeElapsed) });
            int infoCount = 0;
            int cusipCount = 0;

            while (isWebElementVisible(GetLocator("//*[text()='Info:']/parent::div/parent::div"))) {
                String infoMsg=getElementText(GetLocator("//*[text()='Info:']/parent::div/parent::div"));
                if (infoCount>5) {
                    break;
                }
                if (infoMsg.contains("Deal Personnel Update Success") || infoMsg.contains("Deal Successfully Closed")) {
                    sleep(1000);
                    Reporter.info("Clicking Ok button on popup: "+ infoMsg);
                    clickJS(AngOKBtn);
                    waitForInVisibile(AngPleaseWait);
                    waitForInVisibile(AngPleaseWait);
                }else if (infoMsg.toUpperCase().contains("CUSIP")){
                    if (!(cusipCount>2)) {
                        Reporter.warning(infoMsg);
                        Reporter.addScreenshot("CUSIP", "CUSIP");
                        sleep(1000);
                        Reporter.info("Clicking Ok button in CUSIP info pop up");
                        clickJS(AngOKBtn);
                        waitForInVisibile(AngPleaseWait);
                        sleep(30000);
                        clickJS(DealBookingPage.closeDeal);
                        sleep(5000);
                        waitForInVisibile(AngPleaseWait);

                    } else {
                        Reporter.fail(infoMsg);
                        Assert.fail(infoMsg);
                    }
                    cusipCount++;
                }else if (infoMsg.toUpperCase().contains("ERROR") || (infoMsg.toUpperCase().contains("FAIL"))){
                    Reporter.fail(infoMsg);
                    Assert.fail(infoMsg);
                }else{
                    Reporter.fail(infoMsg);
                    Assert.fail(infoMsg);
                }
                infoCount++;
                waitForInVisibile(AngPleaseWait);
            }
            waitForInVisibile(AngPleaseWait);
            clickJS(DealBookingPage.closeWindow);
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            List<WebElement> DBGridRows = this.driver().findElements(By.xpath("//st-grid-box[@fieldname='LIQ_Deals_PostDocs_Grid']/table/tbody/tr"));
            int failCount = 0;
            for (int i = 1; i <= DBGridRows.size(); i++) {
                String tablexpath = "//st-grid-box[@fieldname='LIQ_Deals_PostDocs_Grid']/table";
                String LIQStatusCol = tablexpath + "/tbody/tr[" + i + "]/td[count(" + tablexpath + "/thead/tr/th[text()='Loan IQ Status']/preceding-sibling::th)+1]";

                if (i == 1) {
                    if (!getElementText(GetLocator(LIQStatusCol)).equalsIgnoreCase("Closed")) {
                        Reporter.fail("LIQ Status Column displays " + getElementText(GetLocator(LIQStatusCol)));
                        failCount++;
                    }
                } else {
                    if (!getElementText(GetLocator(LIQStatusCol)).equalsIgnoreCase("Active")) {
                        Reporter.fail("LIQ Status Column displays " + getElementText(GetLocator(LIQStatusCol)));
                        failCount++;
                    }
                }
            }
            if (failCount != 0) {
                Reporter.fail("Deal Closing Status in the grid");
                Assert.fail("Deal Closing Status in the grid");
            } else {
                Reporter.pass("Deal Closing Status in the grid");
            }
            Reporter.addScreenshot("Deal Closing Status", "Deal Closing Status");
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }
}


