package com.cme.pagemethods;

import com.cme.pages.FeesPage;
import com.fcbt.taf.core.reporting.Reporter;
import org.testng.Assert;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.text.ParseException;

public class FeesPageMethods extends CmeBasePage{
    //Verifies Stock fee defaulted
    public void VerifyStockFee(int i){
        String xpath = "//div[@fieldname='GRID_FEE']//div[@class='gridBoxCell' and text()='Stock']";
        String stkFeeAmt;
        String feetypeVal;
        try {
            if (isWebElementVisible(GetLocator(xpath))) {
                click(GetLocator(xpath));
                sleep(500);
                feetypeVal = getElementAttribute(FeesPage.feesFeeTypeDesc, "title");
                stkFeeAmt = getElementAttribute(FeesPage.feesFeeAmount, "title");
                if (stkFeeAmt.equalsIgnoreCase("1,000.00")) {
                    Reporter.pass("Verifying Stock Fee Amount defaulted as 1,000.00 for Facility#"+(i+1));
                } else {
                    Reporter.fail("Verifying Stock Fee Amount defaulted as 1,000.00 for Facility#"+(i+1));
                    Reporter.addScreenshot("Stock Fee Amount","Stock Fee Amount");
                }
            }else {
                Reporter.fail("Verifying Stock Fee defaulted");
                Assert.fail("Verifying Stock Fee defaulted");
            }
        } catch (Exception e) {
            Reporter.fail("Verifying Stock Fee defaulted");
            Assert.fail("Verifying Stock Fee defaulted"+ "\n"+ e.getMessage());
        }


    }

    //Adds new fee
    public void AddFees(int i){
        String feeType              =multiValueMap.get("Fee Type").get(i);
        String borrAmtPaid          =multiValueMap.get("Fee Amount").get(i);
        String financedYesNo        =multiValueMap.get("Fee Financed").get(i);

        if (feeType.contains("/")) {
            String[] feeTypes = feeType.split("/");
            String[] feeAmounts = borrAmtPaid.split("/");
            String[] feeFinanced = financedYesNo.split("/");

            for (int j=0; j<feeTypes.length; j++) {
                String xpath = "//div[@fieldname='GRID_FEE']//div[@class='gridBoxCell' and text()='"+ feeTypes[j]+"']";
                if (!isWebElementVisible(GetLocator(xpath))) {
                    sleep(500);
                    click(newBtn);
                    waitForInVisibile(PleaseWait);
                    sleep(500);
                    inputText(FeesPage.feesFeeType, feeTypes[j]);
                    pressTabKey(FeesPage.feesFeeType);
                    sleep(500);
                    if (!getElementAttribute(FeesPage.feesFeeType, "title").equalsIgnoreCase(feeTypes[j])) {
                        sleep(500);
                        inputText(FeesPage.feesFeeType, feeTypes[j]);
                        pressTabKey(FeesPage.feesFeeType);
                        sleep(500);
                    }
                    inputText(FeesPage.feesBorrAmountpaid, feeAmounts[j]);
                    if (feeFinanced[j].equalsIgnoreCase("Yes")) {
                        CheckRadioButton(FeesPage.feesFeeFinancedYes);
                    } else {
                        CheckRadioButton(FeesPage.feesFeeFinancedNo);
                    }
                    if (feeTypes[j].equalsIgnoreCase("FEE18")) {
                        CheckRadioButton(FeesPage.fees1098ReportablePointsYes);

                    }
                    Reporter.info("Adding new Fee type: "+ feeTypes[j]);
                } else {
                    click(GetLocator(xpath));
                    sleep(1000);
                    if (!getElementAttribute(FeesPage.feesBorrAmountpaid, "title").equalsIgnoreCase(feeAmounts[j])) {
                        Reporter.fail("Defaulted Amount for "+ feeTypes[j]);
                        clearInput(FeesPage.feesBorrAmountpaid);
                        inputText(FeesPage.feesBorrAmountpaid, feeAmounts[j]);
                    }
                    if (feeFinanced[j].equalsIgnoreCase("Yes")) {
                        CheckRadioButton(FeesPage.feesFeeFinancedYes);
                    } else {
                        CheckRadioButton(FeesPage.feesFeeFinancedNo);
                    }
                    if (feeTypes[j].equalsIgnoreCase("FEE18")) {
                        CheckRadioButton(FeesPage.fees1098ReportablePointsYes);

                    }
                }

            }
        } else {
            if (!getElementAttribute(FeesPage.feesFeeType, "title").equalsIgnoreCase(feeType)) {
                click(newBtn);
                waitForInVisibile(PleaseWait);
                sleep(1000);
                clearInput(FeesPage.feesFeeType);
                sleep(500);
                inputTextUsingJS(FeesPage.feesFeeType, feeType);
                sleep(500);
                pressTabKey(FeesPage.feesFeeType);
                sleep(500);
                clearInput(FeesPage.feesBorrAmountpaid);
                inputText(FeesPage.feesBorrAmountpaid, borrAmtPaid);
            }
            if (financedYesNo.equalsIgnoreCase("Yes")) {
                CheckRadioButton(FeesPage.feesFeeFinancedYes);
            } else {
                CheckRadioButton(FeesPage.feesFeeFinancedNo);
            }
            if (feeType.equalsIgnoreCase("FEE18")) {
                CheckRadioButton(FeesPage.fees1098ReportablePointsYes);
            }
            Reporter.info("Adding new Fee type: "+ feeType);

        }

    }

    public String calculateFeeAmt(String OrgFee) throws ParseException {
         String st2 = OrgFee.replaceAll(",", "");
         System.out.println(st2);
         NumberFormat format = NumberFormat.getInstance();
         Number number = format.parse(OrgFee.substring(0, OrgFee.length() - 3));
         System.out.println(number);
         double ln = number.doubleValue()/100;
         String org1 = Double.toString(ln);
         return org1; //origin fee
    }
    public void verifyDefaultFeesforCFC(String institutionType, String loanAmt) throws ParseException { // CFC Institution Type is PCA and Secured by Real Estate radio button is set to No

        if (institutionType.equalsIgnoreCase("PCA")) {
            String feetypeVal = getElementAttribute(FeesPage.feesFeeTypeDesc, "title");
            String processFeeAmt = getElementAttribute(FeesPage.feesFeeAmount, "title");
            if (feetypeVal.equalsIgnoreCase("Processing Fee") && processFeeAmt.equalsIgnoreCase("250.00")) {
                CheckRadioButton(FeesPage.feesFeeFinancedNo);
                Reporter.pass("Processing Fee is defaulted with FeeAmt is $250.00");
            } else {
                Reporter.fail("Processing Fee is not defaulted");
            }
            clickGridBoxCellElement("Stock");
            waitForInVisibile(PleaseWait);
            String feetypeValStk = getElementAttribute(FeesPage.feesFeeTypeDesc, "title");
            String stkFeeAmt = getElementAttribute(FeesPage.feesFeeAmount, "title");
            if (feetypeValStk.equalsIgnoreCase("Stock") && stkFeeAmt.equalsIgnoreCase("1,000.00")) {
                Reporter.pass("Verifying Stock Fee defaulted and FeeAmt is $1000.00");
            } else {
                Reporter.fail("Verifying Stock Fee defaulted");
                Assert.fail("Verifying Stock Fee defaulted");
            }
        } else if (institutionType.equalsIgnoreCase("FLCA")) {
            sleep(1000);
            System.out.println("---" + loanAmt);
            String  org1=  calculateFeeAmt(loanAmt);
            System.out.println(org1);
            waitForInVisibile(PleaseWait);
            String feetypeValOrigin = getElementAttribute(FeesPage.feesFeeTypeDesc, "title");
            String originFeeAmt = getElementAttribute(FeesPage.feesFeeAmount, "title");
            System.out.println("--->"+originFeeAmt.replaceAll(",",""));
            if(Integer.parseInt(org1)<=500){
                Reporter.fail( "Origination fee should not be Less than $500");
            }

            if (feetypeValOrigin.equalsIgnoreCase("Origination Fee") && originFeeAmt.replaceAll(",","").equalsIgnoreCase(org1) && (Integer.parseInt(org1)>=500)) {
                sleep(1000);
                CheckRadioButton(FeesPage.feesFeeFinancedYes);
                sleep(1000);
                CheckRadioButton(FeesPage.fees1098ReportablePointsYes);
                waitForInVisibile(PleaseWait);
                Reporter.pass("Verifying Origination Fee defaulted and FeeAmt is 1% on Decision amount");
            } else {
                Reporter.fail("Origination Fee is not defaulted or Accurate Fee is not Calculate");
                Assert.fail("Verifying Origination Fee defaulted");
            }

            clickGridBoxCellElement("Appraisal/Evaluation Fee - RE");
            waitForInVisibile(PleaseWait);
            String feetypeValAppraise = getElementAttribute(FeesPage.feesFeeTypeDesc, "title");
            String appraislFeeAmt = getElementAttribute(FeesPage.feesFeeAmount, "title");
            if (feetypeValAppraise.equalsIgnoreCase("Appraisal/Evaluation Fee - RE") && appraislFeeAmt.equalsIgnoreCase("500.00")) {
                CheckRadioButton(FeesPage.feesFeeFinancedNo);
                Reporter.pass("Verifying Appraisal/Evaluation Fee - RE Fee defaulted and FeeAmt is $500.00");
            } else {
                Reporter.fail("Appraisal/Evaluation Fee - RE is not defaulted");
                Assert.fail("Verifying Appraisal/Evaluation Fee - RE defaulted");
            }

            clickGridBoxCellElement("Processing Fee");
            waitForInVisibile(PleaseWait);
            String feetypeValProcessing = getElementAttribute(FeesPage.feesFeeTypeDesc, "title");
            String processFeeAmt = getElementAttribute(FeesPage.feesFeeAmount, "title");
            if (feetypeValProcessing.equalsIgnoreCase("Processing Fee") && processFeeAmt.equalsIgnoreCase("350.00")) {
                CheckRadioButton(FeesPage.feesFeeFinancedNo);
                Reporter.pass("Verifying Processing Fee defaulted and FeeAmt is $350.00");
            } else {
                Reporter.fail("Verifying Processing Fee is not defaulted");
                Assert.fail("Verifying Processing Fee defaulted");
            }
            clickGridBoxCellElement("Stock");
            waitForInVisibile(PleaseWait);
            String feetypeValStk = getElementAttribute(FeesPage.feesFeeTypeDesc, "title");
            String stkFeeAmt = getElementAttribute(FeesPage.feesFeeAmount, "title");
            if (feetypeValStk.equalsIgnoreCase("Stock") && stkFeeAmt.equalsIgnoreCase("1,000.00")) {
                Reporter.pass("Verifying Stock Fee defaulted");
            } else {
                Reporter.fail("Verifying Stock Fee defaulted");
                Assert.fail("Verifying Stock Fee defaulted");
            }

        }

    }
}