package com.cme.pagemethods;

import com.cme.pages.Liquidscorepage;
import com.fcbt.taf.core.reporting.Reporter;
import org.testng.Assert;

public class LiquidCreditMethods extends CmeBasePage{
  //fill scored financial values
    public void FillScoredFinancialscreen(){

        String creditBureauForAgFast        =dataMap.get("Credit Bureau for AgFast");
        String agFastPurpose                =dataMap.get("AgFast Purpose");
        String ddaBalance                   =dataMap.get("DDA Balance");
        String otherCashEquivalent          =dataMap.get("Other Cash Equivelant");
        String totalAssets                  =dataMap.get("Total Assets");
        String totalLiabilities             =dataMap.get("Total Liabilities");
        String annualNetProfitBeforeTaxes   =dataMap.get("Annual NetProfit beofre Taxes");
        String AnnualNetInterestExpense     =dataMap.get("Annual NetInterest Expense");
        String bank                         =dataMap.get("Bank");


        try {
            inputTextUsingJS(Liquidscorepage.creditBureauAgFast, creditBureauForAgFast);
            inputTextUsingJS(Liquidscorepage.agFastPurpose, agFastPurpose);
            inputText(Liquidscorepage.ddaBalance,ddaBalance);
            if (!complexity.equalsIgnoreCase("Agfast")) {
                inputText(Liquidscorepage.otherCashEquivalent,otherCashEquivalent);
                inputText(Liquidscorepage.totalAssets,totalAssets);
                inputText(Liquidscorepage.totalLiabilities,totalLiabilities);
                if (!bank.contentEquals("001")) {
                    inputText(Liquidscorepage.AnnualNetProfitBeforeTaxes,annualNetProfitBeforeTaxes);
                    inputText(Liquidscorepage.AnnualNetInterestExpense,AnnualNetInterestExpense);
                }
            }


        }
        catch (Exception e) {
            Reporter.fail(e.getMessage());
            Reporter.addScreenshot("Exception", "Fill scored financial fields for AgFast deal");
            Assert.fail(e.getMessage());
        }
    }
  //Verify credit score and AgFast score fields numeric validations


}
