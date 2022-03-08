package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;


public class AuthorizationPage {

	public static Locator AuthorizationScrn = Locator.byXpath("//a[@fieldname='Authorization Screen']");
	public static Locator ProductColumn =  Locator.byXpath("//div[@fieldname='PLISTBOX_FIELD']//div[@class='gridBoxColumn'][1]//div[@class='gridBoxCell']");
	public static Locator ProductDecisionDD = Locator.byXpath("//div[@fieldname='auth_prod_decision']/input");
	public static Locator DealDecisionNameDD = Locator.byXpath("//div[@fieldname='auth_name']/input");
	public static Locator DealDecisionDD = Locator.byXpath("//div[@fieldname='auth_rec_decision']/input");
	public static Locator NewDecisionBtn = Locator.byXpath("//a[@fieldname='New Decision']");
	public static Locator CDACompleteBtn = Locator.byXpath("//a[@fieldname='CDA Complete']");
	public static Locator messageWndw = Locator.byXpath("//div[@fieldname='Message']");


}
