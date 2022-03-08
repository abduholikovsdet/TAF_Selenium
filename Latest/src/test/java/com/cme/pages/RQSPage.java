package com.cme.pages;

import com.fcbt.taf.core.driver.ui.Locator;
public class RQSPage {
    //Loan Details
    public static Locator newLoan = Locator.byXpath("//label[@name='New Loan']");
    public static Locator conversionLoan = Locator.byXpath("//label[@name='Conversion']");
    public static Locator fixedBtn = Locator.byXpath("//label[@name='Fixed']");
    public static Locator oneMonthLiborBtn = Locator.byXpath("//label[@name='1 Month Libor']");
    public static Locator oneMonthLiborRevolverBtn = Locator.byXpath("//label[@name='1 Month Libor Revolver']");
    public static Locator oneMonthLiborVarCostBtn = Locator.byXpath("//label[@name='1 Month Libor Variable Cost']");
    public static Locator fcsiBtn = Locator.byXpath("//label[@name='FCSI']");
    public static Locator fcsiRevolverBtn = Locator.byXpath("//label[@name='FCSI Revolver']");
    public static Locator primeBtn = Locator.byXpath("//label[@name='Prime']");
    public static Locator loanAmt = Locator.byXpath("//label[text()='Loan Amount']//following::input[1]");
    public static Locator singlePayYes = Locator.byXpath("//label[@name='Yes']");
    public static Locator singlePayNo = Locator.byXpath("//label[@name='No']");
    public static Locator amortPandIBtn = Locator.byXpath("//label[@name='Principal & Interest']");
    public static Locator amortPplusIBtn = Locator.byXpath("//label[@name='Principal + Interest']");
    public static Locator AmortTerm = Locator.byXpath("//input[@id='amortizationTermYear']");
    public static Locator rateTerm = Locator.byXpath("//label[text()='Rate Term']//following::input[1]");
    public static Locator ppyMonthly = Locator.byXpath("//label[@name='12']/input[1]");
    public static Locator ppyQuarter = Locator.byXpath("//label[@name='4']");
    public static Locator ppySemiAnul = Locator.byXpath("//label[@name='2']");
    public static Locator ppyAnnual = Locator.byXpath("//label[@name='1']");
    public static Locator IntOnlyPayments = Locator.byXpath("//*[@id='interestOnlyPayments']");
    //Rate Details
    public static Locator oppBtn = Locator.byXpath("//label[@name='Open Prepay']");
    public static Locator oneyearLockout = Locator.byXpath("//label[@name='One Year Lockout']");
    public static Locator PeriodLockout = Locator.byXpath("//label[@name='Period Lockout 5-4-3-2-1%']");
    public static Locator fymBtn = Locator.byXpath("//label[@name='Full Yield Maintance']");
    public static Locator rateLTBtn = Locator.byXpath("//label[@name='30 Day Lock']");
    public static Locator Spot = Locator.byXpath("//label[@name='SPOT']");
    public static Locator RLTBtn = Locator.byXpath("//label[@name='60 Day Lock']");
    public static Locator ratelockBtn = Locator.byXpath("//label[@name='90 Day Lock']");
    public static Locator assocation = Locator.byXpath("//select[@id='associations']");
    public static Locator AssociaSpread = Locator.byXpath("//input[@id='associationSpread']");
    public static Locator LoanNumber = Locator.byXpath("//input[@id='loanNumber']");
    public static Locator BorrowerName = Locator.byXpath("//input[@id='borrowerName']");
    public static Locator Notes = Locator.byXpath("//textarea[@id='comments']");
    public static Locator LockRateBtn = Locator.byXpath("//button[@id='lockRateButton']");
    public static Locator printIcon = Locator.byXpath("//span[@id='rateLockQuoteDetailsPrint']");
    public static Locator QuoteDetails = Locator.byXpath("//span[text()='Quote Details']");
    public static Locator RQSLoading = Locator.byXpath("//*[@id='loadingGIF' and contains(@style, 'display: inline')]");
}
