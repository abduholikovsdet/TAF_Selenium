package com.cme.pagemethods;

import com.cme.pages.AuthorizationPage;
import com.cme.pages.DisbursementsPage;
import com.fcbt.taf.core.reporting.Reporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class DisbursementsPageMethods extends CmeBasePage{
    /**
     * Author: Muzaffar A.
     * Description: Adds new Disbursement
     */
    public void AddNewDisbursement(int i){
        String disbType         =multiValueMap.get("Disbursement Type").get(i);
        String disbAmount       =multiValueMap.get("Amount").get(i);
        String disbursalType    =multiValueMap.get("Disbursal Type").get(i);
        String decisionAmnt     =multiValueMap.get("Decision Amount").get(i);
        sleep(500);
        try {
            start = System.currentTimeMillis();
            VerifyScreenName(DisbursementsPage.disbursementsScreen);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Disbursements Screen", String.valueOf(timeElapsed) });
            click(newBtn);
            waitForInVisibile(PleaseWait);
            sleep(500);

            click(DisbursementsPage.DisbursementType);
            sleep(500);
            inputTextUsingJS(DisbursementsPage.DisbursementType, disbType);
            sleep(500);


            String availAmt = getElementAttribute(DisbursementsPage.AmountAvailable, "title");
            int percent = 0;
            int amtToEnter = 0;
            int avilAmnt = 0;
            String finalAmt = null;
            if (disbAmount.equalsIgnoreCase("100%")) {
                finalAmt = availAmt;
            }else{
                availAmt = decisionAmnt;
                disbAmount = disbAmount.replace("%", "");
                percent = Integer.parseInt(disbAmount);
                availAmt = availAmt.replace(",", "");
                avilAmnt = Integer.parseInt(availAmt.replace(".00", ""));
                amtToEnter = (avilAmnt * percent)/100;
                finalAmt = String.valueOf(amtToEnter);

            }


            if (multiValueMap.get("Disbursement Type").get(i).equalsIgnoreCase("Payable to Others")) {
                inputTextUsingJS(DisbursementsPage.DisbursalType, disbursalType);
                inputText(DisbursementsPage.Payee, TitleCompany); //
            } else if (multiValueMap.get("Disbursement Type").get(i).equalsIgnoreCase("Funds Held")){
                inputTextUsingJS(DisbursementsPage.FundsHeldType, disbursalType);
            }
            inputTextUsingJS(DisbursementsPage.Amount, finalAmt);

            sleep(500);
            click(saveBtn);
            sleep(1000);
            waitForInVisibile(PleaseWait);
            Reporter.info("Adding Disbursement type: "+ disbType+ "for Facility# "+ i);
        } catch (Exception e) {
            Reporter.fail("Adding Disbursement");
            Assert.fail("Adding Disbursement"+ "\n"+ e.getMessage());
        }



    }



}
