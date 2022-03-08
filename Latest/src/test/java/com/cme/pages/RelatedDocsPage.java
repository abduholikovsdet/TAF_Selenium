package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;
public class RelatedDocsPage {
    public static Locator RelatedDocsScreen = Locator.byXpath("//a[@fieldname='Related Documents']");
    public static Locator StatusField = Locator.byXpath("//div[@fieldname='Entity MS']/input");
    public static Locator UploadFilepath = Locator.byXpath("//input[@type='file']");
    public static Locator AssociateDoc = Locator.byXpath("//div[@fieldname='File Name:']//following::input");
    public static Locator UploadDescription = Locator.byXpath("//*[@class='textField']//following::input[8]");
    public static Locator AssociateDocsDocumentFolder = Locator.byXpath("//div[@fieldname='Document Folder:']//following::input[1]");
    public static Locator AssociateDocsDocumentName = Locator.byXpath("//div[@fieldname='Document Name:']//following::input[1]");
    public static Locator AssociateDocsEntity = Locator.byXpath("//div[@fieldname='Entity: ']//following::input[1]");
    public static Locator AssociateDocsCollateral = Locator.byXpath("//div[@fieldname='Collateral: ']//following::input[1]");
    public static Locator AssociateDocsProduct = Locator.byXpath("//div[@fieldname='Product: ']//following::input[1]");
    public static Locator AssociateDocContinueBtn = Locator.byXpath("//div[@fieldname='Associate Document']//parent::div//a[text()='Continue']");
    public static Locator GridDescriptionColumn = Locator.byXpath("//div[@fieldname='relDocsGridBox']//div[@class='gridBoxColumn'][3]//div[@class='gridBoxCell']");
    public static Locator GridStatusColumn = Locator.byXpath("//div[@fieldname='relDocsGridBox']//div[@class='gridBoxColumn'][9]//div[@class='gridBoxCell']");
    public static Locator GridIDColumn = Locator.byXpath("//div[@fieldname='relDocsGridBox']//div[@class='gridBoxColumn'][1]//div[@class='gridBoxCell']");
    public static Locator ArchiveBtn = Locator.byXpath("//a[@fieldname='Archive']");
    public static Locator HostKeywordsWndw = Locator.byXpath("//div[@fieldname='Host Keywords']");
}

