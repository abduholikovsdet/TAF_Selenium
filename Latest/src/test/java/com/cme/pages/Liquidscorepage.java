package com.cme.pages;

import com.fcbt.taf.core.driver.ui.Locator;

public class Liquidscorepage {
    //Scored financial screen
    public static Locator scoredFinancial = Locator.byXpath("//a[@fieldname='Scored Financial']");
    public static Locator creditBureauAgFast = Locator.byXpath("//div[@fieldname='configField53034']/input");
    public static Locator agFastPurpose = Locator.byXpath("//div[@fieldname='configField51691']/input");
    public static Locator ddaBalance = Locator.byXpath("//div[@fieldname='fin_cash_in_bank']/input");
    public static Locator otherCashEquivalent = Locator.byXpath("//div[@fieldname='configField52155']/input");
    public static Locator totalAssets = Locator.byXpath("//div[@fieldname='fin_total_assets']/input");
    public static Locator totalLiabilities = Locator.byXpath("//div[@fieldname='fin_total_liab']/input");
    public static Locator AnnualNetProfitBeforeTaxes = Locator.byXpath("//div[@fieldname='fin_net_profit']/input");
    public static Locator AnnualNetInterestExpense = Locator.byXpath("//div[@fieldname='fin_interest']/input");
    public static Locator liquidCreditDealTree = Locator.byXpath("//span[@fieldname='Liquid Credit']");
    //Liquid credit score
    public static Locator liquidCreditScreen = Locator.byXpath("//a[@fieldname='Liquid Credit']");
    //public static Locator creditScore = Locator.byXpath("//div[@fieldname='SSLISTBOX_INDIV']/div[@class='gridBoxColumn'][2]/div']");
    public static Locator creditScore = Locator.byXpath("//div[@class='gridBox' and @fieldname='SSLISTBOX_INDIV']//div[@class='gridBoxBody']//div[@class='gridBoxContent']//div[@class='gridBoxColumn'][2]//div");
    public static Locator agFastScore = Locator.byXpath("//div[@class='gridBox' and @fieldname='SSLISTBOX_INDIV']//div[@class='gridBoxBody']//div[@class='gridBoxContent']//div[@class='gridBoxColumn'][3]//div");
    public static Locator totalScore = Locator.byXpath("//div[@fieldname='ent_sbss_score']/input");

//buttons
    public static Locator obtainDecision = Locator.byXpath("//a[@fieldname='Obtain Decision']");
}
