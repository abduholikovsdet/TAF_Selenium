package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;


public class DocumentsPage {

	public static Locator DocumentsScrn = Locator.byXpath("//a[@fieldname='Documents']");
	public static Locator DocumentNameColumn =  Locator.byXpath("//div[@fieldname='DocumentGrid']//div[@class='gridBoxColumn'][2]//div[@class='gridBoxCell']");
	public static Locator RelatedToColumn =  Locator.byXpath("//div[@fieldname='DocumentGrid']//div[@class='gridBoxColumn'][4]//div[@class='gridBoxCell']");
	public static Locator DocNumColChkBx =  Locator.byXpath("//div[text()='Doc Num']/div");
	public static Locator FinalRB = Locator.byXpath("//a[@fieldname='Final RB']");
	public static Locator GenerateDocsBtn = Locator.byXpath("//a[@fieldname='Generate Documents']");
	public static Locator DocumentsInView =  Locator.byXpath("//div[@fieldname='curDocTypeMode']/input");
}
