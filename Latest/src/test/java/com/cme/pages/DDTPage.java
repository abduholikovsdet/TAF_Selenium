package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;

public class DDTPage {
    public static Locator DDTScreen = Locator.byXpath("//a[@fieldname='Due Diligence Tracking']");
    public static Locator DDTGrid =  Locator.byXpath("//div[@fieldname='Results GridBox']");
    public static Locator DDTGridStatusColumn =  Locator.byXpath("//div[@fieldname='Results GridBox']//div[@class='gridBoxColumn'][6]//div[@class='gridBoxCell']");
    public static Locator DDTGridAssignedToColumn =  Locator.byXpath("//div[@fieldname='Results GridBox']//div[@class='gridBoxColumn'][10]//div[@class='gridBoxCell']");
    public static Locator AssignedToDD = Locator.byXpath("//div[@fieldname='Assigned To DD']/input");
    public static Locator StatusDD = Locator.byXpath("//div[@fieldname='Status DD']/input");
}
