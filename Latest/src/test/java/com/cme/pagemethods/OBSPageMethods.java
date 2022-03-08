package com.cme.pagemethods;

import com.aventstack.extentreports.model.Report;
import com.cme.pages.FacilityInfoPage;
import com.cme.pages.OBSPage;
import com.fcbt.taf.core.driver.ui.Locator;
import com.fcbt.taf.core.reporting.Reporter;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.apache.velocity.runtime.directive.Parse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class OBSPageMethods extends CmeBasePage{
    ArrayList<String> RICodes = new ArrayList<String>();

    public void SignInToOBS(String usrnm, String pwd){

        if (isElementExist(OBSPage.SignInLabel, 5)) {
            try {
                inputText(OBSPage.UserName, usrnm);
                inputText(OBSPage.Password, pwd);
                sleep(500);
                click(OBSPage.SignInBtn);

            } catch (Exception e) {
                Reporter.fail(e.getMessage());
            }
        }
    }

    public void VerifyNoRedErrorInOBSCards(){

        try {
            if (isWebElementVisible(OBSPage.CustomerCard) && isWebElementVisible(OBSPage.DealCard) && isWebElementVisible(OBSPage.FacilityCard) && isWebElementVisible(OBSPage.AccountCard)) {
                Reporter.pass("Verifying Customer, Deal, Facility and Account cards are present in the page");
            } else {
                Reporter.fail("Verifying Customer, Deal, Facility and Account cards are present in the page");
                Assert.fail();
            }
            List<WebElement> cardLinks = this.driver().findElements(OBSPage.CardLinks.getBy());
            int errorCount = 0;
            for(WebElement el : cardLinks) {
                if (el.getCssValue("background-color").toString().equalsIgnoreCase("#dc3545")) {
                    errorCount++;
                }
            }
            if (errorCount !=0) {
                Reporter.fail(errorCount +" Errors found in OBS Cards");
                Assert.fail();
            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());

        }
        Reporter.addScreenshot("OBS Page", "OBS Page");
    }


    public void BookAndVerifyOBS(){
        try {
            waitForVisible(OBSPage.BookBtn);
            Reporter.info("Clicking Book button in OBS");
            waitForClickable(OBSPage.BookBtn);
            clickJS(OBSPage.BookBtn);
            start = System.currentTimeMillis();
            boolean progressFlg = true;
            boolean greenFlg = false;
            boolean redFlg = false;
            do {
                sleep(1000);
                WebElement element = this.driver().findElement(OBSPage.ProgressBar.getBy());
                String progressBarColor = element.getCssValue("background-color");
                greenFlg = progressBarColor.equalsIgnoreCase("rgba(118, 206, 96, 1)" ); //green color
//                redFlg = progressBarColor.toString().equalsIgnoreCase("rgb(220, 79, 99, 1)" );
                if (!progressBarColor.equalsIgnoreCase("rgba(104, 169, 239, 1)")) //blue color
                {
                    progressFlg = false;
                }
            } while (progressFlg);

            if (greenFlg) {
                Reporter.pass("Booking Status: "+ getElementText(OBSPage.ProgressBarMessage));
                finish = System.currentTimeMillis();
                timeElapsed = (finish - start)/1000;
                PerfMetrics.add(new String[] { Activity, "OBS Booking", String.valueOf(timeElapsed) });
                Reporter.addScreenshot("OBS", "Booked");
            } else {
                if (isWebElementVisible(OBSPage.ErrorMessage)) {
                    Reporter.fail("Booking Status - "+ getElementText(OBSPage.ProgressBarMessage)+ "\n" + getElementText(OBSPage.ErrorMessage));

                }else{
                    Reporter.fail("Booking Status - "+ getElementText(OBSPage.ProgressBarMessage));
                }
                this.driver().switchTo().defaultContent();
                SwitchToCMEFrame();
                GetOBSLog();
                Assert.fail();
            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail();
        }
    }
    public void VerifyCustomerCard(){
        Reporter.info("Opening Customer card");
        sleep(500);
        this.driver().findElement(By.partialLinkText("Customer Summary")).click();
        sleep(500);
        waitForVisible(GetLocator("//li[text()='Customer Summary']"));
        for (int i=0; i<numOfEnts; i++) {
            VerifyCustomerSummaryTab(i);
            VerifyAddressesTab(i);
            VerifyContactsTab(i);
            VerifyRemitInstructionsTab(i);
            VerifyServicingGroupsTab(i);
            VerifyProfilesTab(i);
            VerifyCustomerAdditionalInformation(i);
        }
        this.driver().findElement(By.linkText("Request")).click();
        waitForVisible(OBSPage.BookBtn);

    }
    public void VerifyDealCard(){

        sleep(500);
        Reporter.info("Opening Deal card");
        this.driver().findElement(By.partialLinkText("Deal Summary")).click();
        waitForVisible(GetLocator("//li[text()='Deal Summary']"));
        VerifyDealSummaryTab();
//        VerifyDealFeesTab();
        VerifyDealPricingRulesTab();
        VerifyDealPaymentRulesTab();
        VerifyDealRelatedPartiesTab();
        VerifyDealRiskTab();
        this.driver().findElement(By.linkText("Request")).click();
    }
    public void VerifyFacilityCard(){
        Reporter.info("Opening Facility card");
        sleep(500);
        this.driver().findElement(By.partialLinkText("Facility Summary")).click();
        sleep(500);
        waitForVisible(GetLocator("//li[text()='Facility Summary']"));
        if (BLStk) {
            VerifyBorrowerLvlStock();
        }
        for (int i=0; i<numOfLoans; i++) {
            ScrollUp();
            this.driver().findElement(By.linkText(FacilityNumber.get(i))).click();
            VerifyFacilitySummaryTab(i);
            VerifyFacilityLimitsTab(i);
            VerifyFacilityPricingDetailsTab(i);
            VerifyFacilityRelatedPartiesTab(i);
            VerifyFacilityRiskTab(i);
            VerifyFacilityAdditionalInformationTab(i);

        }
        ScrollUp();
        sleep(1000);
        this.driver().findElement(By.linkText("Request")).click();

    }
    public void VerifyAccountsCard(){
        VerifyAccountsSummaryTab();
        VerifyAccountsAdjustedPortfolioSharesTab();
        VerifyAccountsRepaymentSchedulesTab();
        VerifyAccountsAdditionalInformationTab();
//        this.driver().findElement(By.linkText("Request")).click();

    }
    public void VerifyCustomerSummaryTab(int i){
        String entityLegalName  =multiValueMap.get("Legal Name").get(i);
        String custNumber       =CustomerNumber.get(i);
        String custFName        =multiValueMap.get("First Name").get(i);
        String custMName        =multiValueMap.get("Middle Name").get(i);
        String custLName        =multiValueMap.get("Last Name").get(i);
        String naicsCode        =multiValueMap.get("NAICS Code 1").get(i);

        try {
            Reporter.info("Validating Customer Summary data for "+ entityLegalName);
            waitForVisible(OBSPage.EntitiesGrid);
            sleep(1000);
            //Customer Summary tab validations
            ScrollUp();
            this.driver().findElement(By.linkText(entityLegalName)).click();
            sleep(2000);
            this.driver().findElement(By.linkText("Customer Summary")).click();
            sleep(2000);
            waitForVisible(GetLocator("//span[@class='badge badge-primary' and contains(text(), 'Summary of "+custNumber+"')]"));
            ScrollDown();
            int failCount = 0;
            if (multiValueMap.get("Entity Type").get(i).equalsIgnoreCase("Individual")) {
                if (!getElementAttribute(OBSPage.FirstName, "value").equalsIgnoreCase(custFName)) {
                    Reporter.fail("Verifying First Name in Customer Summary. Expected value: "+ custFName+ " vs Actual: "+ getElementAttribute(OBSPage.FirstName, "value"));
                    failCount++;
                }
                if (!getElementAttribute(OBSPage.MiddleName, "value").equalsIgnoreCase(custMName)) {
                    Reporter.fail("Verifying Middle Name in Customer Summary. Expected value: "+ custMName + " vs Actual: "+ getElementAttribute(OBSPage.MiddleName, "value"));
                    failCount++;
                }
                if (!getElementAttribute(OBSPage.LastName, "value").equalsIgnoreCase(custLName)) {
                    Reporter.fail("Verifying Last Name in Customer Summary. Expected value: "+ custLName);
                    failCount++;
                }
            }
            if (!getElementAttribute(OBSPage.LegalName, "value").equalsIgnoreCase(entityLegalName)) {
                Reporter.fail("Verifying Legal Name in Customer Summary. Expected value: "+ entityLegalName);
                failCount++;
            }
            if (getElementAttribute(OBSPage.ShortName, "value").isEmpty()) {
                Reporter.fail("Verifying Short Name in Customer Summary. Field is empty");
                failCount++;
            }
            if (!getElementAttribute(OBSPage.CustomerNumber, "value").equalsIgnoreCase(custNumber)) {
                Reporter.fail("Verifying Customer Number in Customer Summary. Actual value: "+ getElementAttribute(OBSPage.CustomerNumber, "value"));
                failCount++;
            }
            if (!verifyDropdownSelectedValue(OBSPage.LIQStatus, "Pending")) {
                Reporter.fail("Verifying LIQ Status in Customer Summary. Expected value: Pending");
                failCount++;
            }
            if (!getElementAttribute(OBSPage.IndustryCode, "title").contains(naicsCode)) {
                Reporter.fail("Verifying Industry Code in Customer Summary. Expected: "+ naicsCode + "vs Actual: "+ getElementAttribute(OBSPage.IndustryCode, "title"));
                failCount++;
            }
            if (!verifyDropdownSelectedValue(OBSPage.Branch, GetBranch())) {
                Reporter.fail("Verifying Branch in Customer Summary. Expected value: "+ GetBranch());
                failCount++;
            }
            if (!getElementAttribute(OBSPage.ExpenseCode, "title").equalsIgnoreCase(GetExpenseCode())) {
                Reporter.fail("Verifying Expense Code in Customer Summary. Expected value: "+ GetExpenseCode());
                failCount++;

            }
            if (!verifyDropdownSelectedValue(OBSPage.Department, GetBranch())) {
                Reporter.fail("Verifying Department in Customer Summary. Expected value: "+ GetBranch()+ "vs Actual: "+ verifyDropdownSelectedValue(OBSPage.Department, GetBranch()));
                failCount++;
            }
            if (!verifyDropdownSelectedValue(OBSPage.BusinessClassification, GetBusinessClassification(i))) {
                Reporter.fail("Verifying Department in Customer Summary. Expected value: "+ GetBusinessClassification(i));
                failCount++;
            }
            if (failCount!=0) {
                Reporter.fail("Customer Summary Validations");

            }else{
                Reporter.pass("Customer Summary Validations");
//                Assert.fail();
            }
            Reporter.addScreenshot("Customer Summary", "Customer Summary");

        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());

        }

    }

    public void VerifyBorrowerLvlStock(){
        String tableXpath = "//table[@id='DataTables_Table_0']";
        int rowNum = GetRowNumberWithCellText(tableXpath, ObsStk);
        int failCount = 0;
        String CurrCommitmentXpath = tableXpath + "/tbody/tr[" + rowNum + "]/td[count(" + tableXpath + "/thead/tr/th[text()='CURRENT COMMITMENT']/preceding-sibling::th)+1]";
        String MaturDateXpath = tableXpath + "/tbody/tr[" + rowNum + "]/td[count(" + tableXpath + "/thead/tr/th[text()='MATURITY DATE']/preceding-sibling::th)+1]";

        if (!getElementText(GetLocator(CurrCommitmentXpath)).equalsIgnoreCase("1.00")) {
            Reporter.fail("Verifying Borrower Level Stock Current Commitment defaulted as 1.00");
            failCount++;
        }
        if (!getElementText(GetLocator(MaturDateXpath)).equalsIgnoreCase("01/01/2100")) {
            Reporter.fail("Verifying Borrower Level Stock Maturity Date defaulted as 01/01/2100");
            failCount++;
        }
        Reporter.addScreenshot("Borrower Level Stock added", "Borrower Level Stock added");
        if (failCount==0) {
            Reporter.pass("Verifying Borrower Level Stock added");
        }

    }
    public void VerifyAddressesTab(int i){
        Reporter.info("Clicking Addresses tab for Entity# "+ (i+1));
        sleep(1000);
        this.driver().findElement(By.linkText("Addresses")).click();
        sleep(1000);
        waitForVisible(GetLocator("//span[@class='badge badge-primary' and contains(text(), 'Addresses of "+CustomerNumber.get(i)+"')]"));

        String AltAddress       =multiValueMap.get("Alternate Address").get(i);
        String address          =multiValueMap.get("Address 1").get(i)+ " " + multiValueMap.get("Address 2").get(i)+ ", " + multiValueMap.get("City").get(i)+ ", " + multiValueMap.get("State").get(i)+ ", US: United States, " + multiValueMap.get("Zip Code").get(i);
        String phoneNum         =multiValueMap.get("Primary Phone Number").get(i);
        String addressType;
        if (AltAddress.equalsIgnoreCase("Yes")) {
            addressType = "Mailing Address";
        } else {
            addressType = "Legal Address";
        }
        int rowNum      =1;
        int failCount   =0;
        String tableXpath = "//table[@id='address-dataTable']";
        String AddressTypeXpath =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='ADDRESS TYPE']/preceding-sibling::th)+1]";
        String AddressXpath     =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='ADDRESS']/preceding-sibling::th)+1]";
        String PhoneNumberXpath      =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='DEFAULT PHONE #']/preceding-sibling::th)+1]";
        if (!getElementText(GetLocator(AddressTypeXpath)).equalsIgnoreCase(addressType)) {
            Reporter.fail("Verifying Address Type as "+ addressType);
            Reporter.addScreenshot("Addresses tab", "Addresses tab");
            failCount++;
        }
        if (!getElementText(GetLocator(AddressXpath)).toLowerCase().contains(address.toLowerCase())) {
            Reporter.fail("Verifying Address as "+ address);
            Reporter.addScreenshot("Addresses tab", "Addresses tab");
            failCount++;
        }
        if (!getElementText(GetLocator(PhoneNumberXpath)).equalsIgnoreCase(phoneNum)) {
            Reporter.fail("Verifying DEFAULT PHONE # as "+ phoneNum);
            Reporter.addScreenshot("Addresses tab", "Addresses tab");
            failCount++;
        }
        if (failCount != 0) {
            Reporter.fail(failCount + " Failed Validations found in Addresses tab");
//            Assert.fail();
        }else{
            Reporter.pass("Addresses tab Validations for Entity# "+ (i+1));
            Reporter.addScreenshot("Addresses tab", "Addresses tab");
        }

    }

    public void VerifyContactsTab(int i){
        Reporter.info("Clicking Contacts tab for Entity# "+ (i+1));
        sleep(1000);
        this.driver().findElement(By.linkText("Contacts")).click();
        sleep(1000);
        waitForVisible(GetLocator("//span[@class='badge badge-primary' and contains(text(), 'Contacts for "+CustomerNumber.get(i)+"')]"));

        if (multiValueMap.get("Entity Type").get(i).equalsIgnoreCase("Company")) {
            String phoneNum         =null;
            String addr             =null;
            String entityFName      =null;
            String entityMName      =null;
            String entityLName      =null;

            if (multiValueMap.get("Entity Type").get(i).equalsIgnoreCase("Company")) {
                entityFName = CompanyContactFName.get(CustomerNumber.get(i));
                entityMName = CompanyContactMName.get(CustomerNumber.get(i));
                entityLName = CompanyContactLName.get(CustomerNumber.get(i));
                CompanyContactSG.put(CustomerNumber.get(i), entityFName.substring(0, 1) + entityMName.substring(0, 1) + entityLName.substring(0, 1));
                CompanyContact = entityFName +" "+entityMName+ " " +entityLName;
                phoneNum = CompanyContactPhone.get(CustomerNumber.get(i));
                addr = CompanyContactAddress.get(CustomerNumber.get(i));
            }
        }


        String entityLegalName;
        if (multiValueMap.get("Entity Type").get(i).equalsIgnoreCase("Company")) {
            entityLegalName = CompanyContact;
        } else {
            entityLegalName = multiValueMap.get("Legal Name").get(i);
        }
        int rowNum      =1;
        int failCount   =0;
        String tableXpath                   ="//table[@id='contact-dataTable']";
        String NameXpath                    =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='NAME']/preceding-sibling::th)+1]";
        String PreferredContactMethodXpath  =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='PREFERRED CONTACT METHOD']/preceding-sibling::th)+1]";
        String PrimaryPurposeXpath          =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='PRIMARY PURPOSE']/preceding-sibling::th)+1]";
        String SecondaryPurposesXpath       =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='SECONDARY PURPOSES']/preceding-sibling::th)+1]";

        if (!getElementText(GetLocator(NameXpath)).equalsIgnoreCase(entityLegalName)) {
            Reporter.fail("Verifying Contact Name as "+ entityLegalName);
            Reporter.addScreenshot("Contacts tab", "Contacts tab");
            failCount++;
        }
        if (!getElementText(GetLocator(PreferredContactMethodXpath)).equalsIgnoreCase("Mail")) {
            Reporter.fail("Verifying Preferred Contact Method as Mail");
            Reporter.addScreenshot("Contacts tab", "Contacts tab");
            failCount++;
        }
        if (!getElementText(GetLocator(PrimaryPurposeXpath)).equalsIgnoreCase("Billing")) {
            Reporter.fail("Verifying PRIMARY PURPOSE as Billing");
            Reporter.addScreenshot("Contacts tab", "Contacts tab");
            failCount++;
        }
        if (!getElementText(GetLocator(SecondaryPurposesXpath)).equalsIgnoreCase("Servicing, Statement")) {
            Reporter.fail("Verifying SECONDARY PURPOSES as Servicing, Statement");
            Reporter.addScreenshot("Contacts tab", "Contacts tab");
            failCount++;
        }
        if (failCount != 0) {
            Reporter.fail(failCount + " Failed Validations found in Contacts tab");
//            Assert.fail();
        }else{
            Reporter.pass("Contacts tab Validations for Entity# "+ (i+1));
            Reporter.addScreenshot("Contacts tab", "Contacts tab");
        }

    }

    public void VerifyRemitInstructionsTab(int i){
        Reporter.info("Clicking Remittance Instructions tab for Entity# "+ (i+1));
        sleep(1000);
        this.driver().findElement(By.linkText("Remittance Instructions")).click();
        sleep(1000);
        waitForVisible(GetLocator("//span[@class='badge badge-primary' and contains(text(), 'Remittance Instructions for "+CustomerNumber.get(i)+"')]"));
        int failCount=0;
        int rowNum;
//        ArrayList<String> RICodes = new ArrayList<String>();
        RICodes.clear();
        RICodes.add(0,"LCKBX1");
        RICodes.add(RICodes.size(),"EXTCC1");
        RICodes.add(RICodes.size(),"LNFH1");
        RICodes.add(RICodes.size(),"RDC1");
        if (!multiValueMap.get("LIQ Profile Type").get(i).equalsIgnoreCase("Guarantor")) {
            RICodes.add(RICodes.size(),"ABOL1");
            RICodes.add(RICodes.size(),"MRDC1");
            RICodes.add(RICodes.size(),"TXWTH1");
        }

        if (!multiValueMap.get("Relationship Type").get(i).equalsIgnoreCase("Guarantor")) {
            RICodes.add(RICodes.size(),"REVSL1");
        }

        for (int j=0; j<numOfLoans; j++) {
            if (multiValueMap.get("Product Type").get(j).contains("Revolver") && !multiValueMap.get("Relationship Type").get(i).equalsIgnoreCase("Guarantor")) {
                RICodes.add(RICodes.size(),"AGRLN1");
                break;
            }
        }

        String tableXpath = "//table[@id='instruction-dataTable']";

//        String PaymentMethodXpath =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='PAYMENT METHOD']/preceding-sibling::th)+1]";
        String DescriptionXpath   =null;
//        String DirectionXpath     =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='DIRECTION']/preceding-sibling::th)+1]";
        String OBSRICode          =null;
        int matchCount =0;

        List<WebElement> tRows = this.driver().findElements(By.xpath("//table[@id='instruction-dataTable']/tbody/tr"));
        int pageCount = 1;
        if (RICodes.size()>5){
            pageCount = 2;
        }
        for (int p=0; p<pageCount; p++) {
            if (p>0) {
                sleep(500);
                if (isWebElementVisible(GetLocator("//div[@id='instruction-table']//a[@class='page-link' and text()='2']"))) {
                    clickJS(GetLocator("//div[@id='instruction-table']//a[@class='page-link' and text()='2']"));
                }else{
                    Reporter.fail("Remit Instructions count didn't match");
                    Reporter.addScreenshot("RI", "RI");
                    break;
                }
                sleep(2000);
                tRows = this.driver().findElements(By.xpath("//table[@id='instruction-dataTable']/tbody/tr"));
            }
            for (int r=1; r<=tRows.size(); r++) {
                DescriptionXpath   =tableXpath+ "/tbody/tr["+ r +"]/td[count("+ tableXpath +"/thead/tr/th[text()='DESCRIPTION']/preceding-sibling::th)+1]";
                OBSRICode = getElementText(GetLocator(DescriptionXpath));
                if (!RICodes.contains(OBSRICode)) {
                    Reporter.fail("Verifying Payment Method defaulted: "+ OBSRICode);
                }else {
                    matchCount++;
                }

            }
        }

        if (matchCount!=RICodes.size()) {
            Reporter.fail("Remit Instructions tab Validations for Entity# "+ (i+1)+"\n"+"Numer of RI's defaulted - "+ matchCount + " VS Expected number of RI's - "+ RICodes.size());
        }else{
            Reporter.pass("Remit Instructions tab Validations for Entity# "+ (i+1));
        }
        Reporter.addScreenshot("Remit Instructions tab", "Remit Instructions tab");

    }

    public void VerifyServicingGroupsTab(int i){
        Reporter.info("Clicking Servicing Groups tab for Entity# "+ (i+1));
        sleep(1000);
        this.driver().findElement(By.linkText("Servicing Groups")).click();
        sleep(1000);
        waitForVisible(GetLocator("//span[@class='badge badge-primary' and contains(text(), 'Servicing Groups for "+CustomerNumber.get(i)+"')]"));

        String entityLegalName;
        if (multiValueMap.get("Entity Type").get(i).equalsIgnoreCase("Company")) {
            entityLegalName = CompanyContact;
        } else {
            entityLegalName = multiValueMap.get("Legal Name").get(i);
        }
        String Alias            =multiValueMap.get("Relationship Type").get(i);
        if (Alias.equalsIgnoreCase("Borrower") || Alias.equalsIgnoreCase("Co-Borrower")) {
            Alias = "BORR1";
        } else if (Alias.equalsIgnoreCase("Guarantor")) {
            Alias = "GUAR1";
        }
        int rowNum      =1;
        int failCount   =0;
        String tableXpath = "//table[@id='sg-dataTable']";
        String AliasXpath       =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='ALIAS']/preceding-sibling::th)+1]";
        String GroupdNameXpath  =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='GROUP NAME']/preceding-sibling::th)+1]";
        String MembersXpath     =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='MEMBERS']/preceding-sibling::th)+1]";

        if (!getElementText(GetLocator(AliasXpath)).equalsIgnoreCase(Alias)) {
            Reporter.fail("Verifying Alias as "+Alias);
            Reporter.addScreenshot("Servicing Groups", "Servicing Groups");
            failCount++;
        }
        if (!getElementText(GetLocator(GroupdNameXpath)).equalsIgnoreCase(Alias)) {
            Reporter.fail("Verifying Group Name as "+Alias);
            Reporter.addScreenshot("Servicing Groups", "Servicing Groups");
            failCount++;
        }
        if (!getElementText(GetLocator(MembersXpath)).equalsIgnoreCase(entityLegalName)) {
            Reporter.fail("Verifying Group Name as "+entityLegalName);
            Reporter.addScreenshot("Servicing Groups", "Servicing Groups");
            failCount++;
        }

        if (failCount != 0) {
            Reporter.fail(failCount + " Failed Validations found in Servicing Groups tab");
//            Assert.fail();
        }else{
            Reporter.pass("Servicing Groups tab Validations for Entity# "+ (i+1));
            Reporter.addScreenshot("Servicing Groups", "Servicing Groups");
        }
    }

    public void VerifyProfilesTab(int i){
        Reporter.info("Clicking Profiles tab for Entity# "+ (i+1));
        sleep(1000);
        this.driver().findElement(By.linkText("Profiles")).click();
        sleep(1000);
        waitForVisible(GetLocator("//span[@class='badge badge-primary' and contains(text(), 'Roles for "+CustomerNumber.get(i)+"')]"));
        int failCount=0;
        int rowNum;
        String Role         ="";
        String LocationCode =locationCode;
        String Alias        =multiValueMap.get("Relationship Type").get(i);
        if (Alias.equalsIgnoreCase("Borrower") || Alias.equalsIgnoreCase("Co-Borrower")) {
            Alias   ="BORR1";
            Role    ="Borrower";
        } else if (Alias.equalsIgnoreCase("Guarantor")) {
            Alias ="GUAR1";
            Role  ="Guarantor";
        }

        for (int j=0; j<numOfLoans; j++) {
            if (multiValueMap.get("Product Type").get(j).contains("Revolver") && !multiValueMap.get("Relationship Type").get(i).equalsIgnoreCase("Guarantor")) {
                RICodes.add(RICodes.size(),"AGRLN1");
                break;
            }
        }

        String tableXpath = "//table[@id='role-dataTable']";
        int ProfPages;
        if (RICodes.size()>5) {
            ProfPages = 2;
        } else {
            ProfPages = 1;
        }
        for (int k=0; k<ProfPages; k++){
            if (k==1) {
                sleep(500);
                clickJS(GetLocator("//div[@id='role-dataTable_paginate']//a[@class='page-link' and text()='Next']"));
                sleep(2000);
            }
            int row = this.driver().findElements(By.xpath("//table[@id='role-dataTable']/tbody/tr")).size();
            for(int l=1;l<=row; l++){
                rowNum = l; //GetRowNumberWithCellText(tableXpath, code);
                String RoleXpath         =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='ROLE']/preceding-sibling::th)+1]";
                String LocationCodeXpath =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='LOCATION CODE']/preceding-sibling::th)+1]";
                String SGAliasXpath      =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='SERVICING GROUP ALIAS']/preceding-sibling::th)+1]";
                String RemInstXpath      =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='REMITTANCE INSTRUCTIONS']/preceding-sibling::th)+1]";
                String ProfRICode        =getElementText(GetLocator(RemInstXpath));

                switch(ProfRICode) {
                    case "LCKBX1":
                    case "REVSL1":
                    case "EXTCC1":
                    case "LNFH1":
                    case "RDC1":
                    case "AGRLN1":
                    case "MRDC1":
                    case "TXWTH1":
                    case "ABOL1":
                        if (!getElementText(GetLocator(RoleXpath)).equalsIgnoreCase(Role)) {
                            Reporter.fail("Verifying Role as: "+ Role + " for "+ ProfRICode);
                            Reporter.addScreenshot("Profiles", "Profiles");
                            failCount++;
                        }
                        if (!getElementText(GetLocator(LocationCodeXpath)).equalsIgnoreCase(LocationCode)) {
                            Reporter.fail("Verifying Location Code as: "+LocationCode+" for "+ ProfRICode);
                            Reporter.addScreenshot("Profiles", "Profiles");
                            failCount++;
                        }
                        if (!getElementText(GetLocator(SGAliasXpath)).equalsIgnoreCase(Alias)) {
                            Reporter.fail("Verifying SG Alias as: "+Alias+" for "+ ProfRICode);
                            Reporter.addScreenshot("Profiles", "Profiles");
                            failCount++;
                        }
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + ProfRICode);
                }


            }
            Reporter.addScreenshot("Profiles", "Profiles");
        }


        if (failCount!=0) {
            Reporter.fail(failCount+ " Failed Validations found in Profiles tab");
        }else{
            Reporter.pass("Profiles tab Validations for Entity# "+ (i+1));

        }

    }

    public void VerifyCustomerAdditionalInformation(int i){
        Reporter.info("Clicking Additional Information tab for Entity# "+ (i+1));
        sleep(1000);
        this.driver().findElement(By.linkText("Additional Information")).click();
        sleep(1000);
        waitForVisible(GetLocator("//span[@class='badge badge-primary' and contains(text(), 'MIS Codes for "+multiValueMap.get("Legal Name").get(i)+"')]"));

        int failCount           =0;
        int rowNum              =0;
        String tableXpath       ="//table[@class='table table-sm' and contains(.,'MIS TYPE')]";
        String FIPSCountyCode   =multiValueMap.get("County").get(i) + " " + multiValueMap.get("State").get(i);
        String FIPSStateCode    =multiValueMap.get("State").get(i);
        String SimilarEntity    ="No";

        ArrayList<String> MISCodes = new ArrayList<String>();
        MISCodes.add(0,"FIPS County Code");
        MISCodes.add(1,"FIPS State Code");
        MISCodes.add(2,"Similar Entity");

        for (int j=0; j<MISCodes.size(); j++) {
            rowNum = GetRowNumberWithCellText(tableXpath, MISCodes.get(j));
            String MISCodeValue = tableXpath + "/tbody/tr[" + rowNum + "]/td[count(" + tableXpath + "/thead/tr/th[text()='MIS CODE VALUE']/preceding-sibling::th)+1]";

            if (MISCodes.get(j).equalsIgnoreCase("FIPS County Code")) {
                if (!getElementText(GetLocator(MISCodeValue)).equalsIgnoreCase(FIPSCountyCode)) {
                    Reporter.fail("Verifying FIPS County Code as: "+ FIPSCountyCode);
                    Reporter.addScreenshot("Additional Information tab", "Additional Information tab");
                    failCount++;
                }
            } else if (MISCodes.get(j).equalsIgnoreCase("FIPS State Code")) {
                if (!getElementText(GetLocator(MISCodeValue)).contains(FIPSStateCode)) {
                    Reporter.fail("Verifying FIPS State Code as: "+ FIPSStateCode);
                    Reporter.addScreenshot("Additional Information tab", "Additional Information tab");
                    failCount++;
                }
            }else if (MISCodes.get(j).equalsIgnoreCase("Similar Entity")) {
                if (!getElementText(GetLocator(MISCodeValue)).contains(SimilarEntity)) {
                    Reporter.fail("Verifying Silar Entity as: "+ SimilarEntity);
                    Reporter.addScreenshot("Additional Information tab", "Additional Information tab");
                    failCount++;
                }
            }
        }

        tableXpath          ="//table[@class='table table-sm' and contains(.,'ADDITIONAL FIELD NAME')]";
        String officialLoan =multiValueMap.get("Official Loan").get(i);
        String SCRA         ="N";
        if (officialLoan.equalsIgnoreCase("No")) {
            officialLoan = "Not Official";
        } else {
            officialLoan = "Official";
        }
        ArrayList<String> AFValues = new ArrayList<String>();
        AFValues.add(0,"OFFICIAL TYPE");
        AFValues.add(1,"SCRA");

        for (int j=0; j<AFValues.size(); j++) {
            rowNum = GetRowNumberWithCellText(tableXpath, AFValues.get(j));
            String AFValue = tableXpath + "/tbody/tr[" + rowNum + "]/td[count(" + tableXpath + "/thead/tr/th[text()='ADDITIONAL FIELD VALUE']/preceding-sibling::th)+1]";


            if (AFValues.get(j).equalsIgnoreCase("OFFICIAL TYPE")) {
                if (!getElementText(GetLocator(AFValue)).equalsIgnoreCase(officialLoan)) {
                    Reporter.fail("Verifying OFFICIAL TYPE as: " + officialLoan);
                    Reporter.addScreenshot("Additional Information tab", "Additional Information tab");
                    failCount++;
                }
            } else if (AFValues.get(j).equalsIgnoreCase("SCRA")) {
                if (!getElementText(GetLocator(AFValue)).contains(SCRA)) {
                    Reporter.fail("Verifying SCRA as: " + SCRA);
                    Reporter.addScreenshot("Additional Information tab", "Additional Information tab");
                    failCount++;
                }

            }

        }

        if (failCount!=0) {
            Reporter.fail(failCount+ " Failed Validations found in Additional Information tab");
        }else{
            Reporter.pass("Additional Information tab Validations for Entity# "+ (i+1));
            Reporter.addScreenshot("Additional Information tab", "Additional Information tab");
        }

    }

    public void VerifyDealSummaryTab(){
        Reporter.info("Clicking on Deal Summary Tab ");
        String dealName                 =dealAls;
        String dealClassification       ="General Corp Purpose";
        String dealProposedCommitment   =null;
        String dealAgreementDate        =multiValueMap.get("Loan Effective Date").get(0);
        String dealClosingDate          =multiValueMap.get("Loan Effective Date").get(0);
        String dealStatus               ="Pending";
        String dealPersonnel            =dataMap.get("Primary Loan Officer");
        int numOfDealName               =1;
        String row                      =null;

        double dealProposedCommitmentVal = 0.00;
        for (int i = 0; i < numOfLoans; i++) {
            String val = multiValueMap.get("Decision Amount").get(i);
            val = val.replace(",", "");
            double decAmnt = Double.parseDouble(val);
            dealProposedCommitmentVal = dealProposedCommitmentVal + decAmnt;
        }
//        dealProposedCommitment = String.format("%.2f", dealProposedCommitmentVal);

        if (BLStk) {
            numOfDealName = 2;
        }

        try {
            for (int i=0; i<numOfDealName; i++) {
                if (i==0) {
                    row = dealAls;
                    Reporter.info("Validating Deal level Summary data");
                } else {
                    row                         =FacilityNumber.get(0)+ "_STOCK";
                    dealProposedCommitmentVal   =1.00;
                    dealClassification          ="Borrower Level Stock";
                    dealName                    =row;
                    Reporter.info("Validating Stock level Summary data");

                }
//                ScrollUp();
//                sleep(1000);

//                WebElement link = this.driver().findElement(By.partialLinkText(row));
//                WebDriverWait wait = new WebDriverWait(this.driver(), 2);
//                wait.until(ExpectedConditions.elementToBeClickable(link));
                ClickLinkByPartialText(row);
                //Deal Summary tab validations
                this.driver().findElement(By.partialLinkText("Deal Summary")).click();
                String summaryXpath = "//span[text()='Summary of "+ row + "']";
                waitForVisible(GetLocator(summaryXpath));
                int failCount = 0;
                double actualCommAmnt = Double.parseDouble(getElementAttribute(OBSPage.Deal_ProposedCommitment, "value").replace(",", ""));

                if (!getElementAttribute(OBSPage.DealName, "value").contains(dealName)) {
                    Reporter.fail("Verifying Deal Name in Deal Summary. Expected value: " + dealName);
                    failCount++;
                }
                if (getElementAttribute(OBSPage.Deal_Classification, "value").equalsIgnoreCase(dealClassification)) {
                    Reporter.fail("Verifying Deal Classification in Deal Summary. Expected value :" + dealClassification);
                    failCount++;
                }
                if (actualCommAmnt != dealProposedCommitmentVal) {
                    Reporter.fail("Verifying Deal Proposed Commitment in Deal Summary. Expected value : " + dealProposedCommitmentVal);
                    failCount++;
                }
                if (!getElementAttribute(OBSPage.Deal_AgreementDate, "value").equalsIgnoreCase(dealAgreementDate)) {
                    Reporter.fail("Verifying Deal Agreement Date in Deal Summary. Expected value : " + dealAgreementDate);
                    failCount++;
                }
                if (!verifyDropdownSelectedValue(OBSPage.Deal_Department, GetBranch())) {
                    Reporter.fail("Verifying Department in Customer Summary. Expected value: " + GetBranch() + "vs Actual: " + verifyDropdownSelectedValue(OBSPage.Department, GetBranch()));
                    failCount++;
                }
                if (!getElementAttribute(OBSPage.Deal_CloseDate, "value").equalsIgnoreCase(dealClosingDate)) {
                    Reporter.fail("Verifying Deal Agreement Date in Deal Summary. Expected value :" + dealClosingDate);
                    failCount++;
                }
                if (!verifyDropdownSelectedValue(OBSPage.Deal_CostCenter, GetExpenseCode())) {
                    Reporter.fail("Verifying Deal Cost Center in Deal Summary. Expected value :" + GetExpenseCode());
                    failCount++;
                }
                if (!verifyDropdownSelectedValue(OBSPage.Deal_Status, dealStatus)) {
                    Reporter.fail("Verifying Deal Status in Deal Summary. Expected value: Pending");
                    failCount++;
                }
                if (!verifyDropdownSelectedValue(OBSPage.Branch, GetBranch())) {
                    Reporter.fail("Verifying Branch in Deal Summary. Expected value: " + GetBranch());
                    failCount++;
                }
                if (isWebElementVisible(OBSPage.Deal_Personnel)) {
                    if (!getElementAttribute(OBSPage.Deal_Personnel, "title").equalsIgnoreCase(dealPersonnel)) {
                         Reporter.fail("Verifying Deal Personnel  in Deal Summary. Expected value :" + dealPersonnel);
                         failCount++;
                     }
                }

                List<WebElement> borrowerOptions = this.driver().findElements(By.xpath("//label[text()='Borrowers']//following::select[1]/option"));

                for (int j = 0; j < numOfEnts; j++) {
                    if (multiValueMap.get("LIQ Profile Type").get(j).equalsIgnoreCase("Borrower")) {
                        String borrName = multiValueMap.get("Legal Name").get(j);
                        String borrNameXpath = "//option[contains(text(),'" + borrName + "')]";
                        if (!isWebElementVisible(GetLocator(borrNameXpath))) {
                            Reporter.fail("Verifying Deal Borrower names  in Deal Summary.");
                            failCount++;
                        }
                    }
                }

                if (failCount == 0) {
                    Reporter.pass("Deal Summary Validations");
                    Reporter.addScreenshot("Deal Summary", "Deal Summary");
                } else {
                    Reporter.fail("Deal Summary Validations");
    //               Assert.fail();
                }
            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }
    public void VerifyDealFeesTab(){
        Reporter.info("Loading Fees tab");
        sleep(500);
        try {
            this.driver().findElement(By.linkText("Fees")).click();
            sleep(500);
            int numOfDealName   =1;
            String row          =null;
            for(int i=0; i<numOfLoans; i++){
                if (!multiValueMap.get("Prepayment Option").get(i).equalsIgnoreCase("OPO")) {

                }
            }

            if (BLStk) {
                numOfDealName = 2;
            }

            for (int i=0; i<numOfDealName; i++) {
                if (i==0) {
                    row =dealAls;
                    Reporter.info("Validating Deal level Fees");
                } else {
                    row =FacilityNumber.get(0)+ "_STOCK";
                    Reporter.info("Validating Stock level Fees");

                }

            }

            this.driver().findElement(By.partialLinkText(row)).click();
            waitForVisible(GetLocator("//span[text()='Fees for "+ row + "']"));

            int rowNum      =1;
            int failCount   =0;
            String tableXpath = "//table[@id='address-dataTable']";
            String AddressTypeXpath =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='ADDRESS TYPE']/preceding-sibling::th)+1]";
            String AddressXpath     =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='ADDRESS']/preceding-sibling::th)+1]";
            String PhoneNumberXpath      =tableXpath+ "/tbody/tr["+ rowNum +"]/td[count("+ tableXpath +"/thead/tr/th[text()='DEFAULT PHONE #']/preceding-sibling::th)+1]";
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void VerifyDealPricingRulesTab(){

        Reporter.info("Clicking Pricing Rules tab ");
        sleep(500);
        try {
            this.driver().findElement(By.partialLinkText("Pricing Rules")).click();
            sleep(1000);

            List<String> pricingOptions = new ArrayList<>();  //pricingOptions Object initialized
            for (int i = 0; i < numOfLoans; i++) {
                String productType = multiValueMap.get("Product Type").get(i); //Institution type > Product Type > Pricing type> Pricing Option
                String pricingType = multiValueMap.get("Pricing Type").get(i);
                String loanTerm = multiValueMap.get("Loan Term Months").get(i);
                int lnTerm = Integer.parseInt(loanTerm);
                // String [] pricingoptions={"FCSI Revolver","Prime","1 Month Libor Variable Cost"}; //Storing Pricing Types in an array
                String FixedToMaturity = multiValueMap.get("Fixed To Maturity").get(i);
                switch (pricingType) {
                    case "Fixed":
                        if (FixedToMaturity.equalsIgnoreCase("Yes")) {
                            pricingOptions.add(pricingOptions.size(), "FIXTM");
                        } else {
                            pricingOptions.add(pricingOptions.size(), "VP037");
                            pricingOptions.add(pricingOptions.size(), "FIXED");
                        }
                        break;
                    case "1 Month Libor":
                        if (lnTerm <= 120) {
                            pricingOptions.add(pricingOptions.size(), "VL120");
                        } else {
                            pricingOptions.add(pricingOptions.size(), "VL121");
                        }
                        break;
                    case "1 Month Libor Revolver":
                        if (lnTerm <= 36) {
                            pricingOptions.add(pricingOptions.size(), "VL036");
                        }
                        if (lnTerm <= 120) {
                            pricingOptions.add(pricingOptions.size(), "VL120");
                        } else {
                            pricingOptions.add(pricingOptions.size(), "VL121");
                        }
                        break;
                    case "1 Month Libor Variable Cost":
                        pricingOptions.add(pricingOptions.size(), "VL002");
                        break;
                    case "FCSI":
                        if (lnTerm <= 120) {
                            pricingOptions.add(pricingOptions.size(), "FC120");
                        } else {
                            pricingOptions.add(pricingOptions.size(), "FC121");
                        }
                        break;
                    case "Prime":
                        if (lnTerm <= 15) {
                            pricingOptions.add(pricingOptions.size(), "VP015");
                        } else if (lnTerm <= 36) {
                            pricingOptions.add(pricingOptions.size(), "VP036");
                        } else if (lnTerm > 36) {
                            pricingOptions.add(pricingOptions.size(), "VP037");
                        }
                        break;
                    case "FCSI Revolver":
                        pricingOptions.add(pricingOptions.size(), "FC001");
                        break;
                    //}
                }
            }
            pricingOptions.add(pricingOptions.size(), "FHPRI");
            pricingOptions.add(pricingOptions.size(), "FHPRZ");
            pricingOptions.add(pricingOptions.size(), "STOCK");

            ArrayList<String> pricingRulesValues = new ArrayList<>();     //Creating another Object pricingRulesValues for storing vvalues from Table(UI) in a arrayList

            String tableXpath = "//table[@id='DataTables_Table_2']";
            String poTableXpath;
            int numOfDealName=1;
            if (BLStk) {
                numOfDealName = 2;
            }
            for (int i=0; i<numOfDealName; i++){
                if (i==0) {
                    this.driver().findElement(By.partialLinkText(dealAls)).click();
                    Reporter.info("Verifying Deal level Pricing Options");
                    String summaryXpath = "//span[text()='Pricing options for "+ dealAls + "']";
                    waitForVisible(GetLocator(summaryXpath));
                    for (int j = 1; j <= pricingOptions.size(); j++) {
                        if (j == 6) {
                            sleep(500);
                            clickJS(GetLocator("//th[contains(text(),'PRICING OPTION')]//following::li[3]/a[text()='Next']"));
                            sleep(2000);
                            poTableXpath = tableXpath + "/tbody/tr[" + (j - 5) + "]/td[count(" + tableXpath + "/thead/tr/th[text()='PRICING OPTION']/preceding-sibling::th)+1]";
                            pricingRulesValues.add(getElementText(GetLocator(poTableXpath)));  //Adding values from row into arrylist
                            break;
                        }
                        poTableXpath = tableXpath + "/tbody/tr[" + j + "]/td[count(" + tableXpath + "/thead/tr/th[text()='PRICING OPTION']/preceding-sibling::th)+1]";
                        pricingRulesValues.add(getElementText(GetLocator(poTableXpath)));  //Adding values from row into arrylist

                    }
                    Collections.sort(pricingOptions);  //Sorting pricingOptions ArrayList
                    Collections.sort(pricingRulesValues); //Sorting pricingRulesValues ArrayList

                    if (!pricingOptions.equals(pricingRulesValues)) {  //Comparing pricingOptions(Object-Arraylist) to pricingRulesValues(Object-ArrayList)
                        Reporter.fail("Verifying Pricing Options in Deal level. Expected value:"+ pricingOptions);
        //          Assert.fail();

                    } else {
                        Reporter.pass("Deal level Pricing Rules tab Validations");
                    }
                    Reporter.addScreenshot("Pricing Rules", "Pricing Rules");
                }else{
                    Reporter.info("Verifying Stock level Pricing Options");
                    this.driver().findElement(By.partialLinkText(FacilityNumber.get(0)+ "_STOCK")).click();
                    String summaryXpath = "//span[text()='Pricing options for "+ FacilityNumber.get(0)+ "_STOCK']";
                    waitForVisible(GetLocator(summaryXpath));
                    poTableXpath = tableXpath + "/tbody/tr[1]/td[count(" + tableXpath + "/thead/tr/th[text()='PRICING OPTION']/preceding-sibling::th)+1]";

                    if (!getElementText(GetLocator(poTableXpath)).equalsIgnoreCase("STOCK")) {
                        Reporter.fail("Verifying Stock level Pricing Options. Expected value: STOCK");;  //Adding values from row into arrylist
                    }else {
                        Reporter.pass("Verifying Stock level Pricing Options");
                        Reporter.addScreenshot("Pricing Rules", "Pricing Rules");
                    }

                }
            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }

    }


    public void VerifyDealPaymentRulesTab(){
        Reporter.info("Clicking Payment Rules tab ");
        sleep(2000);

        try {
            this.driver().findElement(By.partialLinkText("Payment Rules")).click();
            sleep(2000);
            int failCount = 0;
            String payRules = null;
            List<WebElement> pr = driver().findElements(By.xpath("//ul[@id='sortable']/li"));
            int s = pr.size();
            List<String> payruleval = new ArrayList<>();
            payruleval.add(0, "INTEREST");
            payruleval.add(1, "PRINCIPAL");
            payruleval.add(2, "ESCROW");
            payruleval.add(3, "LATECHARGES");
            payruleval.add(4, "FEES");
            payruleval.add(5, "REIMBURSABLE EXPENSE FEES");

            for (int i = 0; i < s; i++) {
                payRules = pr.get(i).getText();
                if (!payRules.equalsIgnoreCase(payruleval.get(i))) {
                    Reporter.fail("Verifying Payment Rules in Deal Card. Expected value :" + payRules);
                    // Assert.fail();
                }
            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
        Reporter.addScreenshot("Payment Rules", "Payment Rules");
    }
    public void VerifyDealRelatedPartiesTab(){
        try {
            ClickLinkByPartialText(dealAls);
            Reporter.info("Clicking on Related Parties tab in Deal Card");
            String strSumm = null;
            sleep(500);
            this.driver().findElement(By.partialLinkText("Related Parties")).click();
            strSumm = "Related Parties for "+ dealAls;
            waitForVisible(GetLocator("//span[contains(text(),'" + strSumm + "')]"));
            Reporter.addScreenshot("Related Parties tab", "Related Parties tab");
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }

    }
    public void VerifyDealRiskTab(){
        Reporter.info("Clicking on Risk tab in Deal Card");
        sleep(500);
        this.driver().findElement(By.partialLinkText("Risk")).click();
        String strSumm = "Risks for "+ dealAls;
        waitForVisible(GetLocator("//span[contains(text(),'" + strSumm + "')]"));
        Reporter.addScreenshot("Risks tab", "Risks tab");
    }

    public void VerifyFacilitySummaryTab(int i){
        try {
            Reporter.info("Clicking on Facility Summary Tab ");
            sleep(500);
            this.driver().findElement(By.partialLinkText("Facility Summary")).click();
            String strSumm = "Summary of "+ FacilityNumber.get(i);
            waitForVisible(GetLocator("//span[text()='"+strSumm +"']"));

            String productType = multiValueMap.get("Product Type").get(i);
            String facClosingCommitment = multiValueMap.get("Decision Amount").get(i);
            double facClosingCommitmentVal = 0;
            facClosingCommitmentVal = Double.parseDouble(facClosingCommitment.replaceAll(",", ""));
            facClosingCommitment = String.format("%.2f", facClosingCommitmentVal);
            String loanPurpose = multiValueMap.get("Loan Purpose").get(i);
            if (loanPurpose.equalsIgnoreCase("Production Ag - Prod and Intermediate Term")) {
                loanPurpose = "Production Ag-Prod and Intermediate Term";
            }
            String facEffectiveDate = multiValueMap.get("Loan Effective Date").get(i);
            String facAgreementDate = multiValueMap.get("Loan Effective Date").get(i);
            String facExpirationDate = multiValueMap.get("Maturity Date").get(i);
            String facMaturityDate = multiValueMap.get("Maturity Date").get(i);
            String naicsCode = multiValueMap.get("Loan NAICS Code").get(i);
            int failCount = 0;

            if (!getElementAttribute(OBSPage.FacName, "value").equalsIgnoreCase(FacilityNumber.get(i))) {
                Reporter.fail("Verifying Facility Name in Facility Summary. Expected value: " + FacilityNumber.get(i));
                failCount++;
            }
            if (!verifyDropdownSelectedValue(OBSPage.FacType, GetOBSFacilityType(productType))) {
                Reporter.fail("Verifying Department in Facility Summary. Expected value: " + GetOBSFacilityType(productType));
                failCount++;
            }
            if (!getElementAttribute(OBSPage.FacClosingCommitment, "value").replaceAll(",", "").equalsIgnoreCase(facClosingCommitment)) {
                Reporter.fail("Verifying Facility Closing Commitment in Facility Summary. Expected value : " + facClosingCommitment);
                failCount++;
            }

            if (!getElementAttribute(OBSPage.FacLoanPurpose, "title").equalsIgnoreCase(loanPurpose)) {
                Reporter.fail("Verifying Facility Loan Purpose in Facility Summary. Expected value: " + loanPurpose);
                failCount++;
            }
            if (!getElementAttribute(OBSPage.FacEffectiveDate, "value").equalsIgnoreCase(facEffectiveDate)) {
                Reporter.fail("Verifying Facility Effective Date in Facility Summary. Expected value :" + facEffectiveDate);
                failCount++;
            }
            if (!getElementAttribute(OBSPage.FacAgreementDate, "value").equalsIgnoreCase(facAgreementDate)) {
                Reporter.fail("Verifying Facility Agreement Date in Facility Summary. Expected value : " + facAgreementDate);
                failCount++;
            }
            if (!getElementAttribute(OBSPage.FacExpirationDate, "value").equalsIgnoreCase(facExpirationDate)) {
                Reporter.fail("Verifying Facility Expiration Date in Facility Summary. Expected value : " + facExpirationDate);
                failCount++;
            }
            if (!getElementAttribute(OBSPage.FacMaturityDate, "value").equalsIgnoreCase(facMaturityDate)) {
                Reporter.fail("Verifying Facility Agreement Date in Facility Summary. Expected value : " + facMaturityDate);
                failCount++;
            }
            if (!getElementAttribute(OBSPage.FacControlNumber, "value").equalsIgnoreCase(FacilityNumber.get(i))) {
                Reporter.fail("Verifying Facility Control Number in Facility Summary. Expected value : " + FacilityNumber.get(i));
                failCount++;
            }
            if (!getElementAttribute(OBSPage.IndustryCode, "title").contains(naicsCode)) {
                Reporter.fail("Verifying Industry Code in Customer Summary. Expected: " + naicsCode);
                failCount++;
            }
            Reporter.addScreenshot("Facility Summary", "Facility Summary");

            if (failCount == 0) {
                Reporter.pass("Facility Summary Validations");
            } else {
                Reporter.fail("Facility Summary Validations");
                //Assert.fail();
            }



        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());

        }


    }
    public void VerifyFacilityLimitsTab(int i){
        try {
            Reporter.info("Clicking on Limits tab ");
            sleep(500);
            this.driver().findElement(By.partialLinkText("Limits")).click();
            String strSumm = "Limits for "+ FacilityNumber.get(i);
            waitForVisible(GetLocator("//span[text()='"+strSumm +"']"));
            List<String> limits = new ArrayList<>();
            limits.add(0, "LOANS");
            if (!BLStk) {
                limits.add(limits.size(), "STSTK");
            }
            limits.add(limits.size(), "FH30");
            limits.add(limits.size(), "FHACP");
            limits.add(limits.size(), "FHCCL");
            limits.add(limits.size(), "FHCON");

            String tableXpath = "//table[@id='DataTables_Table_1']";

            String DescriptionXpath   =null;
            int matchCount =0;

            List<WebElement> tRows = this.driver().findElements(By.xpath(tableXpath +"/tbody/tr"));
            int pageCount = 1;
            if (limits.size()>5){
                pageCount = 2;
            }
            for (int p=0; p<pageCount; p++) {
                if (p>0) {
                    sleep(500);
                    if (isWebElementVisible(GetLocator("//div[@id='DataTables_Table_1_paginate']//a[@class='page-link' and text()='2']"))) {
                        clickJS(GetLocator("//div[@id='DataTables_Table_1_paginate']//a[@class='page-link' and text()='2']"));
                    }else{
                        Reporter.fail("Limits count didn't match");
                        Reporter.addScreenshot("Limits", "Limits");
                        break;
                    }
                    sleep(2000);
                    tRows = this.driver().findElements(By.xpath(tableXpath +"/tbody/tr"));
                }
                for (int r=1; r<=tRows.size(); r++) {
                    DescriptionXpath   =tableXpath+ "/tbody/tr["+ r +"]/td[count("+ tableXpath +"/thead/tr/th[text()='LIMIT']/preceding-sibling::th)+1]";
                    String Lmt = getElementText(GetLocator(DescriptionXpath));
                    if (!limits.contains(Lmt)) {
                        Reporter.fail("Verifying Limit defaulted: "+ Lmt);
                    }else {
                        matchCount++;
                    }

                }
                Reporter.addScreenshot("Limits tab", "Limits tab");
            }

            if (matchCount!=limits.size()) {
                Reporter.fail("Limits tab Validations for Facility# "+ (i+1)+"\n"+"Numer of Limits defaulted - "+ matchCount + " VS Expected number of Limits - "+ limits.size());
            }else{
                Reporter.pass("Limits tab Validations for Facility# "+ (i+1));
            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }


    }
    public void VerifyFacilityPricingDetailsTab(int i) {
        try {
            Reporter.info("Clicking on Pricing Details tab ");
            sleep(500);
            this.driver().findElement(By.partialLinkText("Pricing Details")).click();
            String strSumm = "Pricing for " + FacilityNumber.get(i);
            waitForVisible(GetLocator("//span[text()='" + strSumm + "']"));
            String dealAlias = dealAls;
            String expOptionName = null;
            ArrayList<String> pricingOps = new ArrayList<String>();
            String instituteType = multiValueMap.get("Institution Type").get(i); //Institution type > Product Type > Pricing type> Pricing Option
            String pricingType = multiValueMap.get("Pricing Type").get(i);
            String loanTerm = multiValueMap.get("Loan Term Months").get(i);
            int lnTerm = Integer.parseInt(loanTerm);
            String FixedToMaturity = multiValueMap.get("Fixed To Maturity").get(i);
            pricingOps.add(0, dealAlias + ": " + "FHPRI");
            pricingOps.add(pricingOps.size(), dealAlias + ": " + "FHPRZ");
            pricingOps.add(pricingOps.size(), dealAlias + ": " + "STOCK");
            // validating first column in Pricing details tab
            switch (pricingType) {
                case "Fixed":
                    if (FixedToMaturity.equalsIgnoreCase("Yes")) {
                        pricingOps.add(pricingOps.size(), dealAlias + ": " + "FIXTM");
                    } else {
                        pricingOps.add(pricingOps.size(), dealAlias + ": " + "VP037");
                        pricingOps.add(pricingOps.size(), dealAlias + ": " + "FIXED");

                    }
                    break;
                case "1 Month Libor":
                    if (lnTerm <= 120) {
                        pricingOps.add(pricingOps.size(), dealAlias + ": " + "VL120");
                    } else {
                        pricingOps.add(pricingOps.size(), dealAlias + ": " + "VL121");
                    }
                    break;
                case "1 Month Libor Revolver":
                    if (lnTerm <= 36) {
                        pricingOps.add(pricingOps.size(), dealAlias + ": " + "VL036");
                    }else if (lnTerm <= 120) {
                        pricingOps.add(pricingOps.size(), dealAlias + ": " + "VL120");
                    } else {
                        pricingOps.add(pricingOps.size(), dealAlias + ": " + "VL121");
                    }
                    break;
                case "1 Month Libor Variable Cost":
                    pricingOps.add(pricingOps.size(), dealAlias + ": " + "VL002");
                    break;
                case "FCSI":
                    if (lnTerm <= 120) {
                        pricingOps.add(pricingOps.size(), dealAlias + ": " + "FC120");
                    } else {
                        pricingOps.add(pricingOps.size(), dealAlias + ": " + "FC121");
                    }
                    break;
                case "Prime":
                    if (lnTerm <= 15) {
                        pricingOps.add(pricingOps.size(), dealAlias + ": " + "VP015");
                    } else {
                        pricingOps.add(pricingOps.size(), dealAlias + ": " + "VP037");
                    }
                    break;
                case "FCSI Revolver":
                    pricingOps.add(pricingOps.size(), dealAlias + ": " + "FC001");
                    break;

            }
            String tableXpath = "//table[@id='DataTables_Table_2']";

            String OptionNameXpath      =null;
            String BorrowerSprdXpath    =null;
            int matchCount =0;

            List<WebElement> tRows = this.driver().findElements(By.xpath(tableXpath +"/tbody/tr"));
            int pageCount = 1;
            if (pricingOps.size()>5){
                pageCount = 2;
            }
            for (int p=0; p<pageCount; p++) {
                if (p>0) {
                    sleep(500);
                    if (isWebElementVisible(GetLocator("//div[@id='DataTables_Table_2_paginate']//a[@class='page-link' and text()='2']"))) {
                        clickJS(GetLocator("//div[@id='DataTables_Table_2_paginate']//a[@class='page-link' and text()='2']"));
                    }else{
                        Reporter.fail("Limits count didn't match");
                        Reporter.addScreenshot("Limits", "Limits");
                        break;
                    }
                    sleep(2000);
                    tRows = this.driver().findElements(By.xpath(tableXpath +"/tbody/tr"));
                }
                for (int r=1; r<=tRows.size(); r++) {
                    OptionNameXpath     =tableXpath+ "/tbody/tr["+ r +"]/td[count("+ tableXpath +"/thead/tr/th[text()='OPTION NAME']/preceding-sibling::th)+1]";
                    BorrowerSprdXpath   =tableXpath+ "/tbody/tr["+ r +"]/td[count("+ tableXpath +"/thead/tr/th[text()='BORROWER SPREAD']/preceding-sibling::th)+1]";
                    String opt = getElementText(GetLocator(OptionNameXpath));
                    String sprd = getElementText(GetLocator(BorrowerSprdXpath));
                    String expBorrSprd = null;
                    if (opt.toUpperCase().contains("FIXED") || opt.toUpperCase().contains("FIXTM") || opt.toUpperCase().contains("FHPRI") || opt.toUpperCase().contains("FHPRZ") || opt.toUpperCase().contains("STOCK")) {
                        expBorrSprd = "0.000000";
                    }else {
                        if (opt.toUpperCase().contains("VP037")) {
                            expBorrSprd = "3.000000";
                        }else{
                            expBorrSprd = BorrSpread.get(i);
                        }
                    }
                    if (!sprd.equalsIgnoreCase(expBorrSprd)) {
                        Reporter.fail("Verifying Borrower Spread for "+ opt+ " defaulted: "+ sprd);
                    }
                    if (!pricingOps.contains(opt)) {
                        Reporter.fail("Verifying Option defaulted: "+ opt);
                    }else {
                        matchCount++;
                    }


                }
                Reporter.addScreenshot("Pricing Details tab", "Pricing Details tab");
            }

            if (matchCount!=pricingOps.size()) {
                Reporter.fail("Pricing Details tab Validations for Facility# "+ (i+1)+"\n"+"Number of Options defaulted - "+ matchCount + " VS Expected number of Options - "+ pricingOps.size());
            }else{
                Reporter.pass("Pricing Details tab Validations for Facility# "+ (i+1));
            }
        } catch (NumberFormatException e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }


    }

    public void VerifyFacilityRelatedPartiesTab(int i){
        try {
            Reporter.info("Clicking on Related Parties tab ");
            sleep(500);
            this.driver().findElement(By.partialLinkText("Related Parties")).click();
            String strSumm = "Related Parties for "+ dealAls +":" + FacilityNumber.get(i);
            waitForVisible(GetLocator("//span[contains(text(),'" + strSumm + "')]"));

            String tableXpath = "//table[@id='DataTables_Table_3']";
            String RelatedEntXpath =null;
            String EntRoleXpath    =null;
            String DetailsXpath    =null;
            List<String> RelatedParties = new ArrayList<>();
            List<String> EntRole = new ArrayList<>();
            List<String> detailsArr = new ArrayList<>();
            int matchCount =0;

            for (int k=0; k<numOfEnts; k++){
                RelatedParties.add(k, multiValueMap.get("Legal Name").get(k));
                EntRole.add(k, multiValueMap.get("LIQ Profile Type").get(k));
                if (!multiValueMap.get("LIQ Profile Type").get(k).equalsIgnoreCase("Guarantor")) {
                    if (BLStk) {
                        detailsArr.add(k, "Depositor: Yes\nLimits: LOANS, FH30, FHACP, FHCCL, FHCON");
                    }else{
                        detailsArr.add(k, "Depositor: Yes\nLimits: LOANS, STSTK, FH30, FHACP, FHCCL, FHCON");
                    }
                }else{
                    detailsArr.add(k, "Amount: None\nProgram: No");
                }
            }
            if (GuaranteePrgrmFlg.get(i)) {
                RelatedParties.add(RelatedParties.size(), multiValueMap.get("Guarantee Programs").get(i).replace(" Guarantee", ""));
                EntRole.add(EntRole.size(), "Guarantor");
                detailsArr.add(detailsArr.size(), "Amount: None\nProgram: Yes");
            }
            List<WebElement> tRows = this.driver().findElements(By.xpath(tableXpath +"/tbody/tr"));
            int pageCount = 1;
            if (numOfEnts > 5){
                pageCount = 2;
            }
            for (int p=0; p<pageCount; p++) {
                if (p>0) {
                    sleep(500);
                    if (isWebElementVisible(GetLocator("//div[@id='DataTables_Table_3_paginate']//a[@class='page-link' and text()='2']"))) {
                        clickJS(GetLocator("//div[@id='DataTables_Table_3_paginate']//a[@class='page-link' and text()='2']"));
                    }else{
                        Reporter.fail("Limits count didn't match");
                        Reporter.addScreenshot("Limits", "Limits");
                        break;
                    }
                    sleep(2000);
                    tRows = this.driver().findElements(By.xpath(tableXpath +"/tbody/tr"));
                }
                for (int r=1; r<=tRows.size(); r++) {

                    RelatedEntXpath     =tableXpath+ "/tbody/tr["+ r +"]/td[count("+ tableXpath +"/thead/tr/th[text()='RELATED ENTITY']/preceding-sibling::th)+1]";
                    EntRoleXpath   =tableXpath+ "/tbody/tr["+ r +"]/td[count("+ tableXpath +"/thead/tr/th[text()='ENTITY ROLE']/preceding-sibling::th)+1]";
                    DetailsXpath   =tableXpath+ "/tbody/tr["+ r +"]/td[count("+ tableXpath +"/thead/tr/th[text()='DETAILS']/preceding-sibling::th)+1]";
                    String RelEnt = getElementText(GetLocator(RelatedEntXpath));
                    String strRole = getElementText(GetLocator(EntRoleXpath));
                    String details = getElementText(GetLocator(DetailsXpath));
                    if (!RelEnt.contains(RelatedParties.get(r-1))) {
                        Reporter.fail("Verifying Legal Name defaulted: "+ RelEnt);
                    }else {
                        matchCount++;
                    }
                    if (!strRole.contains(EntRole.get(r-1))) {
                        Reporter.fail("Verifying Entity Role defaulted: "+ RelEnt);
                    }
                    if (!details.equalsIgnoreCase(detailsArr.get(r-1))) {
                        Reporter.fail("Verifying Details defaulted: "+ RelEnt);
                    }

                }
                Reporter.addScreenshot("Related Parties tab", "Related Parties tab");
            }

            if (matchCount!=RelatedParties.size()) {
                Reporter.fail("Related Parties tab Validations for Facility# "+ (i+1)+"\n"+"Number of Related Parties defaulted - "+ matchCount + " VS Expected number of Related Parties - "+ numOfEnts);
            }else{
                Reporter.pass("Related Parties tab Validations for Facility# "+ (i+1));
            }

        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }


    }
    public void VerifyFacilityRiskTab(int i){
        Reporter.info("Clicking on Risk tab ");
        sleep(500);
        this.driver().findElement(By.partialLinkText("Risk")).click();
        String strSumm = "Risks for "+ FacilityNumber.get(i);
        waitForVisible(GetLocator("//span[contains(text(),'" + strSumm + "')]"));

        String tableXpath = "//table[@id='DataTables_Table_4']";
        String RiskTypeXpath =null;
        String RiskDescXpath =null;
        int failCount =0;
        RiskTypeXpath =tableXpath+ "/tbody/tr[1]/td[count("+ tableXpath +"/thead/tr/th[text()='RISK TYPE']/preceding-sibling::th)+1]";
        RiskDescXpath =tableXpath+ "/tbody/tr[1]/td[count("+ tableXpath +"/thead/tr/th[text()='RISK DESCRIPTION']/preceding-sibling::th)+1]";

        String pdScore = getElementText(GetLocator(RiskTypeXpath)).replace("PD", "");
        String riskDesc = getElementText(GetLocator(RiskDescXpath));
        if (!pdScore.equalsIgnoreCase(RelPD)) {
            Reporter.fail("Verifying PD score defaulted: "+ RelPD);
            failCount++;
        }
        if (!riskDesc.equalsIgnoreCase(PDDescription(pdScore))) {
            Reporter.fail("Verifying PD Description defaulted: "+ PDDescription(pdScore));
            failCount++;
        }

        RiskTypeXpath =tableXpath+ "/tbody/tr[2]/td[count("+ tableXpath +"/thead/tr/th[text()='RISK TYPE']/preceding-sibling::th)+1]";
        RiskDescXpath =tableXpath+ "/tbody/tr[2]/td[count("+ tableXpath +"/thead/tr/th[text()='RISK DESCRIPTION']/preceding-sibling::th)+1]";

        String lgdStr = getElementText(GetLocator(RiskTypeXpath)).replace("LGD", "");
        riskDesc = getElementText(GetLocator(RiskDescXpath));
        if (!lgdStr.equalsIgnoreCase(LgdRating.get(i))) {
            Reporter.fail("Verifying PD score defaulted: "+ LgdRating.get(i));
            failCount++;
        }
        if (!riskDesc.equalsIgnoreCase("LGD "+ LgdRating.get(i))) {
            Reporter.fail("Verifying LGD Description defaulted: "+ LgdRating.get(i));
            failCount++;
        }
        Reporter.addScreenshot("Risk tab", "Risk tab");
        if (failCount==0) {
            Reporter.pass("Related Risk tab Validations for Facility# "+ (i+1));
        }


    }



    public void VerifyFacilityAdditionalInformationTab(int i){
        Reporter.info("Clicking on Additional Information");
        sleep(500);
        this.driver().findElement(By.partialLinkText("Additional Information")).click();
        String strSumm = "MIS Codes for "+ FacilityNumber.get(i);
        waitForVisible(GetLocator("//span[contains(text(),'" + strSumm + "')]"));

        int failCount = 0;
        int rowNum = 0;
        String row = null;
        String tableXpath = "//table[@class='table table-sm' and contains(.,'MIS TYPE')]";
        String CapitalAllocation = "100% Allocation";
        String CharteredTerritory = "In";
        String Committed = "Committed";
        String BeginningFarmer = YbsBeginner.get(i);
        String YoungFarmer = YbsYoung.get(i);
        String SmallFarmer = YbsSmall.get(i);
        String Secured = null;
        if (multiValueMap.get("Secured?").get(i).equalsIgnoreCase("Yes")) {
            Secured = "Secured";
        } else {
            Secured = "Unsecured";
        }
        ArrayList<String> MISCodes = new ArrayList<String>();
        MISCodes.add(0, "Beginning Farmer");
        MISCodes.add(1, "Capital Allocation");
        MISCodes.add(2, "Chartered Territory");
        MISCodes.add(3, "Committed/Uncommitted");
        MISCodes.add(4, "Secured/Unsecured");
        MISCodes.add(5, "Small Farmer");
        MISCodes.add(6, "Young Farmer");

        for (int j = 0; j < MISCodes.size(); j++) {
            rowNum = GetRowNumberWithCellText(tableXpath, MISCodes.get(j));
            String MISCodeValue = tableXpath + "/tbody/tr[" + rowNum + "]/td[count(" + tableXpath + "/thead/tr/th[text()='MIS CODE VALUE']/preceding-sibling::th)+1]";

            if (MISCodes.get(j).equalsIgnoreCase("Beginning Farmer")) {
                if (!getElementText(GetLocator(MISCodeValue)).equalsIgnoreCase(BeginningFarmer)) {
                    Reporter.fail("Verifying Beginning Farmer as: " + BeginningFarmer);
                    failCount++;
                }
            } else if (MISCodes.get(j).equalsIgnoreCase("Capital Allocation")) {
                if (!getElementText(GetLocator(MISCodeValue)).contains(CapitalAllocation)) {
                    Reporter.fail("Verifying Capital Allocation as: " + CapitalAllocation);
                    failCount++;
                }
            } else if (MISCodes.get(j).equalsIgnoreCase("Chartered Territory")) {
                if (!getElementText(GetLocator(MISCodeValue)).contains(CharteredTerritory)) {
                    Reporter.fail("Verifying Chartered Territory as: " + CharteredTerritory);
                    failCount++;
                }
            } else if (MISCodes.get(j).equalsIgnoreCase("Committed/Uncommitted")) {
                if (!getElementText(GetLocator(MISCodeValue)).contains(Committed)) {
                    Reporter.fail("Verifying Committed/Uncommitted: " + Committed);
                    failCount++;
                }
            } else if (MISCodes.get(j).equalsIgnoreCase("Secured/Unsecured")) {
                if (!getElementText(GetLocator(MISCodeValue)).contains(Secured)) {
                    Reporter.fail("Verifying Secured/Unsecured: " + Secured);
                    failCount++;
                }
            } else if (MISCodes.get(j).equalsIgnoreCase("Small Farmer")) {
                if (!getElementText(GetLocator(MISCodeValue)).contains(SmallFarmer)) {
                    Reporter.fail("Verifying Small Farmer: " + SmallFarmer);
                    failCount++;
                }
            } else if (MISCodes.get(j).equalsIgnoreCase("Young Farmer")) {
                if (!getElementText(GetLocator(MISCodeValue)).contains(YoungFarmer)) {
                    Reporter.fail("Verifying Young Farmer: " + YoungFarmer);
                    failCount++;
                }
            }
        }
        Reporter.addScreenshot("Additional Information tab", "MIS Type");
        ScrollDown();
        tableXpath = "//table[@class='table table-sm' and contains(.,'ADDITIONAL FIELD NAME')]";
        String CollatDesc = CollateralDesc.get(FacilityNumber.get(i));
        String EncompassOriginated = null;
        if (multiValueMap.get("Consumer Loan").get(i).equalsIgnoreCase("No")) {
            EncompassOriginated = "N";
        }else{
            EncompassOriginated = "Y";
        }
        String PrePaymentPCT = multiValueMap.get("Prepayment Option").get(i);
        String PrimaryRepayment = multiValueMap.get("Loan NAICS Code").get(i);
//        String Report1098 = Report1098Int.get(i);
        String SecuredbyDwelling = "N";

        ArrayList<String> AFValues = new ArrayList<String>();
        AFValues.add(0, "COLLATERAL DESCRIPTION");
        AFValues.add(1, "ENCOMPASS ORIGINATED");
        AFValues.add(2, "PREPAYMENT PENALTY CALC TYPE");
        AFValues.add(3, "PREPAYMENT PENALTY EFFECTIVE DATE");
        AFValues.add(4, "PRIMARY REPAYMENT");
        AFValues.add(5, "REPORT 1098 INTEREST");
        AFValues.add(6, "SECURED BY DWELLING");

        for (int j = 0; j < AFValues.size(); j++) {
            rowNum = GetRowNumberWithCellText(tableXpath, AFValues.get(j));
            String AFValue = tableXpath + "/tbody/tr[" + rowNum + "]/td[count(" + tableXpath + "/thead/tr/th[text()='ADDITIONAL FIELD VALUE']/preceding-sibling::th)+1]";


            if (AFValues.get(j).equalsIgnoreCase("COLLATERAL DESCRIPTION")) {
                if (!getElementText(GetLocator(AFValue)).equalsIgnoreCase(CollatDesc)) {
                    Reporter.fail("Verifying COLLATERAL DESCRIPTION as: " + CollatDesc);
                    failCount++;
                }
            } else if (AFValues.get(j).equalsIgnoreCase("ENCOMPASS ORIGINATED")) {
                if (!getElementText(GetLocator(AFValue)).contains(EncompassOriginated)) {
                    Reporter.fail("Verifying ENCOMPASS ORIGINATED as: " + EncompassOriginated);
                    failCount++;
                }
            } else if (AFValues.get(j).equalsIgnoreCase("PREPAYMENT PENALTY CALC TYPE")) {
                if (!getElementText(GetLocator(AFValue)).contains(GetPPPCalcType(PrePaymentPCT))) {
                    Reporter.fail("Verifying PREPAYMENT PENALTY CALC TYPE as: " + PrePaymentPCT);
                    Reporter.addScreenshot("Additional Information tab", "Additional Information tab");
                    failCount++;
                }

            } else if (AFValues.get(j).equalsIgnoreCase("PRIMARY REPAYMENT")) {
                if (!getElementText(GetLocator(AFValue)).contains(PrimaryRepayment)) {
                    Reporter.fail("Verifying PRIMARY REPAYMENT as: " + PrimaryRepayment);
                    Reporter.addScreenshot("Additional Information tab", "Additional Information tab");
                    failCount++;
                }
//            } else if (AFValues.get(j).equalsIgnoreCase("REPORT 1098 INTEREST")) {
//                if (!getElementText(GetLocator(AFValue)).contains(Report1098)) {
//                    Reporter.fail("Verifying REPORT 1098 INTEREST as: " + Report1098);
//                    Reporter.addScreenshot("Additional Information tab", "Additional Information tab");
//                    failCount++;
//                }
            } else if (AFValues.get(j).equalsIgnoreCase("SECURED BY DWELLING")) {
                if (!getElementText(GetLocator(AFValue)).contains(SecuredbyDwelling)) {
                    Reporter.fail("Verifying SECURED BY DWELLING as: " + SecuredbyDwelling);
                    Reporter.addScreenshot("Additional Information tab", "Additional Information tab");
                    failCount++;
                }

            }

        }


    }

    public void VerifyAccountsSummaryTab(){

    }
    public void VerifyAccountsAdjustedPortfolioSharesTab(){

    }
    public void VerifyAccountsRepaymentSchedulesTab(){

    }
    public void VerifyAccountsAdditionalInformationTab(){

    }
}
