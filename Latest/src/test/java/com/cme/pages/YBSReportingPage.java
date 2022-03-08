package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;

public class YBSReportingPage {
    public static Locator YBSScrn = Locator.byXpath("//a[@fieldname='YBS Reporting']");
    public static Locator QualYNYoung = Locator.byXpath("//input[@name='QualYNYoung']");
    public static Locator QualYNBegin = Locator.byXpath("//input[@name='QualYNBegin']");
    public static Locator QualYNSmall = Locator.byXpath("//input[@name='QualYNSmall']");
    public static Locator SmallSum = Locator.byXpath("//input[@name='SmallSum']");
    public static Locator RiskTypesGrid = Locator.byXpath("//st-grid-box[@fieldname='QualTblGrid']/table");

}
