package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;

public class StockholderPage {
    public static Locator StockholderScrnFacLevel = Locator.byXpath("//a[@fieldname='Stockholder - Facility Level']");
    public static Locator WhichProdForAssessment = Locator.byXpath("//button[@fieldname='WhichProduct_btn']");
    public static Locator StockholderVoter = Locator.byXpath("//button[@fieldname='Voter_btn']");
    public static Locator Required = Locator.byXpath("//input[@name='Required']");
    public static Locator AltVoter  = Locator.byXpath("//input[@name = 'AltVoter']");
    public static Locator StockholderScrnBorLevel = Locator.byXpath("//a[@fieldname='Stockholder - Borrower Level']");
}
