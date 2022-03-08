package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;

public class BorrowerRiskTypesPage {
    public static Locator BorrowerRiskTypesScrn = Locator.byXpath("//a[@fieldname='Borrower Risk Type Gridbox']");
    public static Locator Borrower = Locator.byXpath("//button[@fieldname='fields.ent_id_btn']");
    public static Locator RiskTypes = Locator.byXpath("//button[@fieldname='fields.risk_type_code_btn']");
    public static Locator Limit = Locator.byXpath("//input[@name='fields.rt_global_limit']");
    public static Locator Float = Locator.byXpath("//input[@fieldname='fields.rt_float_yn']");
    public static Locator RiskTypesGrid = Locator.byXpath("//st-grid-box[@fieldname='Borrower_Risk_Type_Grid']/table");

}
