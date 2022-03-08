package com.cme.pagemethods;

import com.cme.pages.DealInfoPage;
import com.cme.pages.EntityInfoPage;
import com.cme.pages.FacilityInfoPage;
import com.cme.pages.RQSPage;
import com.fcbt.taf.core.reporting.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.server.handler.GetElementAttribute;
import org.testng.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class FacilityPageMethods extends CmeBasePage{

    //Enters fields in Facility Request section: Institution, Product, Loan Purpose Type, Deal Alias
    public void EnterFacilityRequest(int i){

        String institutionType  =multiValueMap.get("Institution Type").get(i);
        String prodType         =multiValueMap.get("Product Type").get(i);
        String loanPurpose      =multiValueMap.get("Loan Purpose").get(i);
        String consumerLoan     =multiValueMap.get("Consumer Loan").get(i);

        try {
            inputTextUsingJS(FacilityInfoPage.facInstType, institutionType); //Selecting Institution Type
            inputTextUsingJS(FacilityInfoPage.facProdType, prodType); //Selecting Product Type
            sleep(5000);
            String dealAls = getApplicationNumber();
            if (i>0) {
                inputTextUsingJS(FacilityInfoPage.facDeal, dealAls);
            }
            if (!getElementAttribute(FacilityInfoPage.facDealAlias, "title").equalsIgnoreCase(dealAls)) {
                inputText(FacilityInfoPage.facDealAlias, dealAls);  //Fetching Application number from left top panel
            } else {
                System.out.println("Deal Alias defaulted");
            }
            inputTextUsingJS(FacilityInfoPage.facLoanPurp, loanPurpose); //Selecting Loan Purpose
            Reporter.info("Entering fields in Facility Request section");
            if (bankNumber.equalsIgnoreCase("001") && complexity.equalsIgnoreCase("Traditional") ) {
                inputTextUsingJS(FacilityInfoPage.facOptimistProd, multiValueMap.get("Optimist Product").get(i));
            }
            if (consumerLoan.equalsIgnoreCase("Yes")) {
                CheckRadioButton(FacilityInfoPage.facConsumerY);
            } else {
                CheckRadioButton(FacilityInfoPage.facConsumerN);
            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Entering Facility Request section"+ "\n" + e.getMessage());
        }

    }

    public void EnterGuaranteeProgram(int i){
        try {
            if (multiValueMap.get("Guarantee Program")!=null) {
                if (multiValueMap.get("Guarantee Program").get(i).equalsIgnoreCase("Yes")) {
                    CheckRadioButton(FacilityInfoPage.facGuaranteePgmY);
                    sleep(500);
                    inputTextUsingJS(FacilityInfoPage.facGuaranteePrgms, multiValueMap.get("Guarantee Programs").get(i));
                    inputText(FacilityInfoPage.facGuaranteePercent, multiValueMap.get("Percent of Guarantee").get(i));
                    pressTabKey(FacilityInfoPage.facGuaranteePercent);
                    GuaranteePrgrmFlg.add(i, true);

                }else{
                    CheckRadioButton(FacilityInfoPage.facGuaranteePgmN);
                }
            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Entering Guaranteed Program section"+ "\n" + e.getMessage());
        }
    }

    //Enters fields in Facility Amount section: Requested Amount, Decision Amount, Non-AG Income dependent, Non-Farm Income dependent
    public void EnterFacilityAmount(int i) {

        String requestedAmount  =multiValueMap.get("Requested Amount").get(i);
        String decisionAmount   =multiValueMap.get("Decision Amount").get(i);
        try {
            inputText(FacilityInfoPage.facReqAmt, requestedAmount); //Entering Requested Amount
            inputText(FacilityInfoPage.facDecisionAmt, decisionAmount); //Entering Decision Amount
            sleep(500);

            if (multiValueMap.get("Secured by Real Estate").get(i).equalsIgnoreCase("Yes")) {
                CheckRadioButton(FacilityInfoPage.facSecRealEstY); //Clicking Yes to Secured by Real Estate
            } else {
                CheckRadioButton(FacilityInfoPage.facSecRealEstN); //Clicking No to Secured by Real Estate
                if (multiValueMap.get("Secured?").get(i).equalsIgnoreCase("Yes")) {
                    CheckRadioButton(FacilityInfoPage.facSecuredY);
                }else{
                    CheckRadioButton(FacilityInfoPage.facSecuredN);
                }

            }
            sleep(500);
            CheckRadioButton(FacilityInfoPage.facNonAgIncDepN); //Clicking No to Non-AG Income dependent
            sleep(500);
            CheckRadioButton(FacilityInfoPage.facNonFarmIncDepN); //Clicking No to Non-Farm Income dependent
            Reporter.info("Entering fields in Facility Amount section");
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Entering Facility Amount section");
        }
    }
    //Verifies Facility Number
    public void VerifyFacilityNumberCreated(int i){
        try {
            waitForInVisibile(PleaseWait);
            String facNumber = getElementAttribute(FacilityInfoPage.facFacilityNo, "title");
            if (!facNumber.isEmpty()) {
                FacilityNumber.add(i,facNumber);
                Reporter.pass("Facility Number: "+facNumber+" Generated for Loan "+ (i+1));
                Reporter.addScreenshot("Facility Number Generated", "Facility Number Generated");
            }else {
                Reporter.fail("Facility Number Not Generated");
                Assert.fail("Facility Number Not Generated");
            }

        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Verifying Facility number populated");
        }
    }

    //Enters Loan NAICS and Repayment Source Codes
    public void EnterNAICSCodes(int i){
        String loanNAICS    =multiValueMap.get("Loan NAICS Code").get(i);
        String repaymtNAICS =multiValueMap.get("Repayment Source Code").get(i);
        try {
            inputText(FacilityInfoPage.facLoanNAICS, loanNAICS);
            inputText(FacilityInfoPage.facNAICSRepayCd,repaymtNAICS);
            Reporter.info("Entering Loan NAICS Code="+loanNAICS+" and Repayment NAICS Code="+repaymtNAICS);
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Entering Loan and Repayment NAICS Codes");
        }
    }

    //Enters Product Information section
    public void EnterProductInformation(int i){

        String productType      =multiValueMap.get("Product Type").get(i);
        String pricingType      =multiValueMap.get("Pricing Type").get(i);
        String fxtmYesNo        =multiValueMap.get("Fixed To Maturity").get(i);
        String repriceOption    =multiValueMap.get("Reprice Option").get(i);
        String advExpireDate    =multiValueMap.get("Advance Expiration Date").get(i);
        String accrualMethod    =multiValueMap.get("Accrual Method").get(i);
        String repaymtType      =multiValueMap.get("Repayment Type").get(i);
        String paymtFreq        =multiValueMap.get("Payment Frequency").get(i);
        String loanTerm         =multiValueMap.get("Loan Term Months").get(i);
        String numOfIO          =multiValueMap.get("No. of Interest Only Pmnts").get(i);
        String loanEffDate      =multiValueMap.get("Loan Effective Date").get(i);
        String pmtStrtDate      =multiValueMap.get("Payment Start Date").get(i);
        String prePmtOption      =multiValueMap.get("Prepayment Option").get(i);

        try {
            inputTextUsingJS(FacilityInfoPage.facPricingTyp, pricingType); //Selecting Pricing type
            if (pricingType.equalsIgnoreCase("Fixed")) {
                if (fxtmYesNo.trim().equalsIgnoreCase("Yes")) {
                    CheckRadioButton(FacilityInfoPage.facFixdToMatY); //Selecting FXTM RB as Yes

                } else if (fxtmYesNo.trim().equalsIgnoreCase("No")) {
                        CheckRadioButton(FacilityInfoPage.facFixdToMatN); //Selecting FXTM RB as No
                        inputTextUsingJS(FacilityInfoPage.facRepriceOption, repriceOption);
                }
            }

            inputTextUsingJS(FacilityInfoPage.facAccrualMethod, accrualMethod); //Entering Accrual Method
            inputTextUsingJS(FacilityInfoPage.facRepaymentTyp, repaymtType); //Selecting Repayment Type
            pressTabKey(FacilityInfoPage.facRepaymentTyp); //Selecting Repayment Type
            if(!repaymtType.equalsIgnoreCase("SP")) {
                click(FacilityInfoPage.paymentFreqDDbtn);//Selecting Payment Frequency  (Not For SP)
                sleep(1000);
                clickGridBoxCellElement(paymtFreq);//Selecting Payment Frequency
            }
            inputText(FacilityInfoPage.facLoanEffDate, loanEffDate); //Entering Loan Eff Date
            inputText(FacilityInfoPage.facLoanTermMon, loanTerm); //Entering Loan Term Months
            inputText(FacilityInfoPage.facPaymntStrtDt, pmtStrtDate); //Entering Payment Start Date
            pressTabKey(FacilityInfoPage.facPaymntStrtDt);
            sleep(500);
            if(!repaymtType.equalsIgnoreCase("IO"))
                if (!repaymtType.equalsIgnoreCase("SP")) {
                    clearInput(FacilityInfoPage.facNumOfIOPmts);
                    inputText(FacilityInfoPage.facNumOfIOPmts, numOfIO); //Entering Number of IO payments
                }



            if (!getElementAttribute(FacilityInfoPage.facPrepaymentOpt, "title").equalsIgnoreCase(prePmtOption)) {
                inputTextUsingJS(FacilityInfoPage.facPrepaymentOpt, prePmtOption);
            }
            if (isWebElementVisible(FacilityInfoPage.facNumOfIOPmts)) {
                pressTabKey(FacilityInfoPage.facNumOfIOPmts);
            }
            sleep(500);
            if(productType.equalsIgnoreCase("Revolver To Term"))  {
                String principlePmtStrtDte = getElementAttribute(FacilityInfoPage.principlePmtStrtDte, "title");
                System.out.println("Principal Pmt Start date: "+ principlePmtStrtDte);
                advExpireDate = AddMonthsToDateSetFirstDayOfMonth(principlePmtStrtDte, -1);
                System.out.println("Advance Exp Date: "+ advExpireDate);
                inputText(FacilityInfoPage.facAdvExpireDate, advExpireDate );
            }

        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
            Assert.fail("Entering Product Information");
         }
    }



    public void EnterConstructionDetails(int i){
        String constructionStatDate ="";
        String constructionType     ="";
        String constructionEndDate  ="";
        inputText(FacilityInfoPage.facConstStrtDt, constructionStatDate);
        inputTextUsingJS(FacilityInfoPage.facConstTyp, constructionType);
        inputText(FacilityInfoPage.facConstEndDt, constructionEndDate);
    }
    /**
     * Author: Muzaffar A.
     * Description: Clicks Get Rate button and locks rate after rate populated in Facility screen
     */
    public void GetRateCalculatePayment(int i){
        String rateLcktype  =multiValueMap.get("Rate Lock Type").get(i);
        String AssocSpread  =multiValueMap.get("Association Spread").get(i);

        if (!getElementAttribute(FacilityInfoPage.facRateLockTyp, "title").equalsIgnoreCase(rateLcktype)) {
            inputTextUsingJS(FacilityInfoPage.facRateLockTyp, rateLcktype);
        }
        //Get Rate
        Reporter.info("Clicking Get Rate button for Facility #"+(i+1));
        sleep(500);
        clickJS(FacilityInfoPage.facGetRateBtn);
        start = System.currentTimeMillis();
        waitForInVisibile(PleaseWait, 1000);
//        waitForInVisibile(PleaseWait);

        if (Double.parseDouble(getElementAttribute(FacilityInfoPage.facBaseRate, "title"))>0) {
            Reporter.pass("Rate Populated");
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Get Rate", String.valueOf(timeElapsed) });
        } else {
            Reporter.fail("Rate Populated");
            Assert.fail("Rate Populated");
        }

        //Calculate Payment
        Reporter.info("Entering Association spread and clicking Calculate Payment button for Facility #"+(i+1));
        if (!bankNumber.equalsIgnoreCase("001")) {
            clearInput(FacilityInfoPage.facAssociationSpread);
        }
        inputText(FacilityInfoPage.facAssociationSpread, AssocSpread);
        pressTabKey(FacilityInfoPage.facAssociationSpread);
        sleep(1000);
        clickJS(FacilityInfoPage.facCalcPmtBtn);
        start = System.currentTimeMillis();
        waitForInVisibile(PleaseWait);
        if (isWebElementVisible(FacilityInfoPage.facSuccessCalulationMsg)) {
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Successful Calculation Message", String.valueOf(timeElapsed) });
            Reporter.pass("Successful Calculation");
            sleep(1000);
            click(OKBtn);
            finish = System.currentTimeMillis();
            sleep(500);
            waitForInVisibile(PleaseWait);
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Successful Calculation Message - Ok", String.valueOf(timeElapsed) });
        } else {
            Reporter.fail("Successful Calculation");
            Assert.fail("Successful Calculation");
        }
        //Lock Rate
        if(!bankNumber.contentEquals("001")) {
            Reporter.info("Selecting Lock Rate type and locking rate for Facility #" + (i + 1));
            sleep(1000);
            clickJS(FacilityInfoPage.facLockRateBtn);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(PleaseWait);
            waitForInVisibile(LoadCircleGIF);
            if (!getElementAttribute(FacilityInfoPage.facLockInDt, "title").isEmpty()) {
                Reporter.pass("Rate locked");
                finish = System.currentTimeMillis();
                timeElapsed = (finish - start)/1000;
                PerfMetrics.add(new String[] { Activity, "Lock Rate", String.valueOf(timeElapsed) });
            } else {
                Reporter.fail("Rate locked");
                Assert.fail("Rate locked");
            }
        }

        Reporter.addScreenshot("WRT, Sherman", "WRT, Sherman");
        if (isWebElementVisible(FacilityInfoPage.facBorrSpreadToIndxRate)) {
            BorrSpread.add(i,getElementAttribute(FacilityInfoPage.facBorrSpreadToIndxRate, "title"));
        }
    }
        //Verifies Amortization schedule
    public void VerifyAmortizationSchedule(int i){
        try {
            LTVScore = GetLTVinFacilityScreen();
            sleep(1000);
            Reporter.info("Opening Amortization schedule for Facility#"+(i+1));
            click(FacilityInfoPage.facAmortizationBtn);
            start = System.currentTimeMillis();
            waitForInVisibile(PleaseWait);
            click(clickHereToViewDocument);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Amortization Schedule", String.valueOf(timeElapsed) });
            SwitchWindow(1);
            VerifyPDF();
            this.driver().close();
            SwitchWindow(0);
            SwitchToCMEFrame();
            sleep(1000);
            click(DoneBtn);
            sleep(1000);
            GetShermanXML(i);

        } catch (Exception e) {
            Reporter.info(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    public void GetShermanXML(int i) {
        try {
            Reporter.info("Opening Sherman XML for Facility#"+(i+1));
            click(GetLocator("//div[@fieldname='Tools']"));
            sleep(500);
            click(GetLocator("//div[@fieldname='Advanced']"));
            sleep(500);
            click(GetLocator("//div[@fieldname='View Sherman XML']"));
            click(clickHereToViewDocument);
            SwitchWindow(1);
            String shermXML = this.driver().findElement(By.xpath("/html/body/pre")).getText();
            File file = new File("target/html-report/ShermanXML_" + FacilityNumber.get(i) +".txt");
            FileWriter fw = new FileWriter(file, true);
            fw.write(shermXML);
            fw.flush();
            fw.close();
            Reporter.addScreenshot("ShermanXML", "ShermanXML");
            this.driver().close();
            SwitchWindow(0);
            SwitchToCMEFrame();
            sleep(1000);
            click(DoneBtn);
            sleep(1000);
        } catch (IOException e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }


    public void GetRatesfromRateQuoteSystem(int i) {

        String pricType			=multiValueMap.get("Pricing Type").get(i);
        String intOnlyPayment	=multiValueMap.get("No. of Interest Only Pmnts").get(i);
        String assocSpread		=multiValueMap.get("Association Spread").get(i);
        String LoanTermMonths	=multiValueMap.get("Loan Term Months").get(i);
        String RepaymentType	=multiValueMap.get("Repayment Type").get(i);
        String PmtFrequency		=multiValueMap.get("Payment Frequency").get(i);
        String PrepmtFreqcy		=multiValueMap.get("Prepayment Option").get(i);
        String RateLockType		=multiValueMap.get("Rate Lock Type").get(i);
        String PaymentsPerYear	=null;
        String DecisionAmt		=getElementAttribute(FacilityInfoPage.facDecisionAmt, "title");
        DecisionAmt             =DecisionAmt.replace(".00", "");

        String XpathRQSPrepayOption ="//input[@class='rateOptionParameter' and @value='"+PrepmtFreqcy+"']";
        String XpathRateLockType 	="//label[@name='"+RateLockType+"']";
        if (PmtFrequency.equalsIgnoreCase("MON")) {
            PaymentsPerYear = "12";
        }else if (PmtFrequency.equalsIgnoreCase("ANN")) {
            PaymentsPerYear = "1";
        }else if (PmtFrequency.equalsIgnoreCase("SEM")) {
            PaymentsPerYear = "2";
        }else if (PmtFrequency.equalsIgnoreCase("QUA")) {
            PaymentsPerYear = "4";
        }

        try {

            String LoanTermYrs = Integer.toString(Integer.parseInt(LoanTermMonths)/12);
            String parent = this.driver().getWindowHandle();
            ((JavascriptExecutor)driver()).executeScript("window.open('about:blank','_blank');");
            String subWindowHandler=null;
            Set<String> handles = driver().getWindowHandles();
            Iterator<String> iterator =handles.iterator();
            while(iterator.hasNext()){
                subWindowHandler=iterator.next();
            }
            driver().switchTo().window(subWindowHandler);
            this.driver().get(RqsURL);
            //sleep(3000);
            waitForPageLoaded();
            waitForClickable(GetLocator("//label[@name='" + pricType + "']"));
            this.driver().findElement(By.xpath("//label[@name='" + pricType + "']")).click();
            waitForInVisibile(RQSPage.RQSLoading);
            inputText(RQSPage.loanAmt, DecisionAmt);
            if(pricType.equalsIgnoreCase("Fixed")) {
                if (RepaymentType.equalsIgnoreCase("P&I")) {
                    clickJS(RQSPage.amortPandIBtn);
                }else if (RepaymentType.equalsIgnoreCase("P+I")) {
                    clickJS(RQSPage.amortPplusIBtn);
                }
                inputText(RQSPage.AmortTerm, LoanTermYrs);
                waitForInVisibile(RQSPage.RQSLoading);
                inputText(RQSPage.rateTerm, LoanTermYrs);
                waitForInVisibile(RQSPage.RQSLoading);
                String XpathPPY = "//input[@id = 'paymentsPerYear."+PaymentsPerYear+"' ]";
                clickJS(GetLocator(XpathPPY));
                waitForInVisibile(RQSPage.RQSLoading);
                this.driver().findElement(RQSPage.IntOnlyPayments.getBy()).clear();
                inputText(RQSPage.IntOnlyPayments, intOnlyPayment);
                pressTabKey(RQSPage.IntOnlyPayments);
                waitForInVisibile(RQSPage.RQSLoading);

            }else{
                if (!RepaymentType.equalsIgnoreCase("SP")) {
                    clickJS(RQSPage.singlePayNo);
                    waitForInVisibile(RQSPage.RQSLoading);
                    inputText(RQSPage.AmortTerm, LoanTermYrs);
                    waitForInVisibile(RQSPage.RQSLoading);
                    String XpathPPY = "//input[@id = 'paymentsPerYear."+PaymentsPerYear+"' ]";
                    clickJS(GetLocator(XpathPPY));
                    waitForInVisibile(RQSPage.RQSLoading);
                    this.driver().findElement(RQSPage.IntOnlyPayments.getBy()).clear();
                    inputText(RQSPage.IntOnlyPayments, intOnlyPayment);
                }else{
                    clickJS(RQSPage.singlePayYes);
                    waitForInVisibile(RQSPage.RQSLoading);
                    inputText(RQSPage.AmortTerm, LoanTermYrs);
                    waitForInVisibile(RQSPage.RQSLoading);
                }

            }

            waitForInVisibile(RQSPage.RQSLoading);
            selectByVisibleText(RQSPage.assocation, "Capital Farm Credit");
            waitForInVisibile(RQSPage.RQSLoading);
            inputText(RQSPage.AssociaSpread, assocSpread);
            inputText(RQSPage.LoanNumber, FacilityNumber.get(i));
            waitForInVisibile(RQSPage.RQSLoading);
            inputText(RQSPage.BorrowerName, prmBorrFullName1);
            waitForInVisibile(RQSPage.RQSLoading);
            if (pricType.equalsIgnoreCase("Fixed")) {
                clickJS(GetLocator(XpathRQSPrepayOption)); //Selecting Prepayment Option
                waitForInVisibile(RQSPage.RQSLoading);
                clickJS(GetLocator(XpathRateLockType));
                waitForInVisibile(RQSPage.RQSLoading);
            }
            Reporter.info("Locking the rate in RQS");
            Reporter.addScreenshot("RQS","Rate Quote Page");
            click(RQSPage.LockRateBtn);
            sleep(1000);
            waitForPageLoaded();
            if (isElementExist(RQSPage.QuoteDetails, 30)) {
                Reporter.pass("RQS - Rate Lock Approved");

            } else {
                Reporter.fail("RQS - Rate Lock Approved");
            }
            Reporter.addScreenshot("RQS", "Rate Quote Details");
            this.driver().close();
            this.driver().switchTo().window(parent);
        }catch (Exception e) {
            Reporter.fail(e.getMessage());
            Reporter.addScreenshot("Loan Amount Not Entered", "Entering values in RQS");
            Assert.fail(e.getMessage()+ "\n"+ "Failing to generate Rates");
        }

    }
}
