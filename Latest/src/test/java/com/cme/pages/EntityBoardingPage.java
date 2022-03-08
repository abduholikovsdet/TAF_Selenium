package com.cme.pages;

import com.fcbt.taf.core.driver.ui.Locator;

public class EntityBoardingPage {
    public static Locator EBScreen = Locator.byXpath("//a[@fieldname='Entity Boarding']");
    public static Locator EntityBoard = Locator.byXpath("//span[@fieldname='Entity Boarding']");
    public static Locator BoardEntity = Locator.byXpath("//button[@fieldname='BookingEntityAction']");
    public static Locator EBSuccessMsgPopup = Locator.byXpath("//div[@class='modal-body custom-modal-body']/p");
    public static Locator UpdateEntity = Locator.byXpath("//button[@fieldname='UpdateButton']");
    public static Locator ExpandAll = Locator.byXpath("//button[@fieldname='Expand_All_Button']");
    public static Locator CollapseAll = Locator.byXpath("//button[@fieldname='Collapse All']");
    public static Locator EntityCreate = Locator.byXpath("//p[text()='Entity and all its basic details are created in Loan IQ.']");
    public static Locator OkButton = Locator.byXpath("//button[contains(text(),'OK')]");
    public static Locator entityBoardingAlert = Locator.byXpath("//p[contains(text(),'All Entity Basic details has successfully updated in Loan IQ.')]");




    // Basic Info Section
    public static Locator shortName = Locator.byXpath("//input[@fieldname='shortname']");
    public static Locator expenseCode = Locator.byXpath("//button[@fieldname='Expense_btn']//div[@ng-if='options.displayField']");
    public static Locator legalName = Locator.byXpath("//fieldset[@class='fullname']/label/span[2]/p");
    public static Locator branch = Locator.byXpath("//fieldset[@class='Branch']/label/span[2]");
    public static Locator department = Locator.byXpath("//fieldset[@class='Department']/label/div");
    public static Locator classification = Locator.byXpath("//fieldset[@class='classification']/label/div");
    public static Locator entityLiqId= Locator.byXpath("//fieldset[@class='EntityLIQCid']/label/span[2]/p");
    public static Locator entityStatus = Locator.byXpath("//fieldset[@class='EntityStatus']/label/span[2]/p");
    //Legal Address
    public static Locator ebAddLine1 = Locator.byXpath("//fieldset[@class='AddressLine1']/label/span[2]/p");
    public static Locator ebCity = Locator.byXpath("//fieldset[@class='City']/label/span[2]/p");
    public static Locator ebZip = Locator.byXpath("//fieldset[@class='Zip']/label/span[2]/p");
    public static Locator ebPhone = Locator.byXpath("//fieldset[@class='Phone']/label/span[2]/p");
    public static Locator ebState = Locator.byXpath("//fieldset[@class='State']/label/span[2]/p");
    public static Locator ebCountry = Locator.byXpath("//fieldset[@class='Country']/label/span[2]/p");
    //Industry Classification
    public static Locator ebPrimarySic = Locator.byXpath("//fieldset[@class='PrimarySIC']/label/span[2]/p");
    public static Locator ebSicCountry= Locator.byXpath("//fieldset[@class='Primary_SIC_Country']/label/span[2]/p");

    //Profiles/Locations
    public static Locator ProLocationTab = Locator.byXpath("//span[text()='Profiles/Locations']");
    public static Locator UpdatePL= Locator.byXpath("//button[text()='Update Profiles/Locations']");
    public static Locator PlMsgTable = Locator.byXpath("//st-grid-box[@fieldname='ProfileSSN']/table");//st-grid-box[@fieldname='ProfileSSN']/table/tbody/tr[1]/td[10]
    public static Locator profileLocationsIndicator =Locator.byXpath("//span[text()='Profiles/Locations']/preceding-sibling::span[@ng-show='tab.showStatusIndicator']");


    //Contacts Tab
    public static Locator ContactTab = Locator.byXpath("//span[text()='Contacts']");
    public static Locator ContactFname = Locator.byXpath("//input[@fieldname='FName']");
    public static Locator ContactMname = Locator.byXpath("//input[@fieldname='MName']");
    public static Locator ContactLname = Locator.byXpath("//input[@fieldname='LName']");
    public static Locator ContactPhNum = Locator.byXpath("//input[@fieldname='PrimaryPhone']");
    public static Locator UpdateContacts = Locator.byXpath("//button[text()='Update Contacts']");
    public static Locator ContactProfilocations = Locator.byXpath("//button[@fieldname='profileLocation_btn']");
    public static Locator ContactPreferredLang = Locator.byXpath("//fieldset[@class='PreferredLanguage']/label/div");
    public static Locator ContactPrimContactPurpose = Locator.byXpath("//button[@fieldname='PContactPurpose_btn']");
    public static Locator ContactOtherContactPurpose = Locator.byXpath("//button[@fieldname='OtherPurpose_btn']");
    public static Locator ContactNotificationMethods = Locator.byXpath("//fieldset[@class='NotificationMethod']/label/div");
    public static Locator ContactPreferNotificationMethods = Locator.byXpath("//fieldset[@class='PreferredNotification']/label/div");
    public static Locator ContactsIndicator = Locator.byXpath("//span[text()='Contacts']/preceding-sibling::span[@ng-show='tab.showStatusIndicator']");

    public  static Locator NotificationAddress = Locator.byXpath("//st-drop-down[@fieldname='Address']");
    public  static Locator NotificationAddressDesc = Locator.byXpath("//fieldset[@class='AddressDesc']/label/span[2]/p");

    //Remit Instructions
    public static Locator RITab = Locator.byXpath("//span[text()='Remit Instructions']");
    public static Locator RemitInstrTabIndicator = Locator.byXpath("//span[text()='Remit Instructions']/preceding-sibling::span[@ng-show='tab.showStatusIndicator']");
    //Servicing Groups
    public static Locator ServeGroupTab = Locator.byXpath("//span[text()='Servicing Groups']");
    public static Locator SGAddBtn = Locator.byXpath("//button[text()='Add']");
    public static Locator SGPl = Locator.byXpath("//button[@fieldname='profile_locations_dropdown_btn']");//st-drop-down[@fieldname='profile_locations_dropdown']
    public static Locator SGContacts = Locator.byXpath("//span[text()='Contacts:']//following::div[1]");
    public static Locator SGPrimaryContact = Locator.byXpath("//span[text()='Primary Contact:']//following::div[1]");
    public static Locator SGRemitInstr = Locator.byXpath("//button[@fieldname='remittance_instructions_dropdown_btn']/div/div[@ng-if='options.displayField']");
    public static Locator SGStandardRI = Locator.byXpath("//button[@fieldname='standard_remittance_instructions_dropdown_btn']/div/div[@ng-if='options.displayField']");
    public static Locator SGStCheckbox = Locator.byXpath("//input[@fieldname='standard_checkbox']");
    public static Locator SGAlias = Locator.byXpath("//input[@fieldname='alias_text_field']");
    public static Locator SGName = Locator.byXpath("//input[@fieldname='name_text_field']");
    public static Locator SRIMethod = Locator.byXpath("//div[@fieldname='standard_remittance_instructions_dropdown_menu']/table/tbody/tr[2]/td[1]");
    public static Locator UpdateSG = Locator.byXpath("//button[text()='Update Servicing Groups']");
    public static Locator SGStatusMsg = Locator.byXpath("//st-grid-box[@fieldname='servicing_group_grid']/table");//st-grid-box[@fieldname='servicing_group_grid']/table/tbody/tr[1]/td[6]
    public static Locator SGIndicator = Locator.byXpath("//span[text()='Servicing Groups']/preceding-sibling::span[@ng-show='tab.showStatusIndicator']");
    public static Locator SGStandardChkbx = Locator.byXpath("//input[@fieldname='standard_checkbox']");

    //Mis/Additional Fields
    public static Locator MISTab = Locator.byXpath("//span[text()='MIS/Additional Fields']");
    public static Locator FipsStateCode = Locator.byXpath("//span[text()='FIPS State Code']//following::div[1]");
    public static Locator FipsCountyCode = Locator.byXpath("//span[text()='FIPS County Code']//following::div[1]");
    public static Locator SimilarEntity = Locator.byXpath("//span[text()='Similar Entity']//following::div[1]");
    public static Locator OfficialEntity = Locator.byXpath("//span[text()='Official Loan Type']//following::div[1]");
    public static Locator UpdateRI = Locator.byXpath("//button[text()='Update Remittance Instructions']");
    public static Locator UpdateMIS = Locator.byXpath("//button[text()='Book MIS/Additional Fields']");
    public static Locator MISIndicator = Locator.byXpath("//span[text()='MIS/Additional Fields']/preceding-sibling::span[@ng-show='tab.showStatusIndicator']");

    //Comments Tab
    public static Locator CommentsTab = Locator.byXpath("//span[text()='Comments']");
    public static Locator UpdateComment = Locator.byXpath("//button[text()='Update Comments']");
    public static Locator CommentAdd = Locator.byXpath("//st-grid-box[@fieldname='Comments_Grid']/span[1]/button");
    public static Locator ConSub = Locator.byXpath("//input[@fieldname='subject']");
    public static Locator ConComment = Locator.byXpath("//span[text()='Comment']//following::textarea[1]");
    public static Locator ContactMsg = Locator.byXpath("//span[text()='Contact successfully booked to Loan IQ']");
    public static Locator commentsTabIndicator =Locator.byXpath("//span[text()='Comments']/preceding-sibling::span[@ng-show='tab.showStatusIndicator']");
}
