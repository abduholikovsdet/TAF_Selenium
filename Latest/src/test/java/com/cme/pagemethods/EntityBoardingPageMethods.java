package com.cme.pagemethods;

import com.cme.pages.DealBookingPage;
import com.cme.pages.EntityBoardingPage;
import com.fcbt.taf.core.reporting.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.*;

public class EntityBoardingPageMethods extends CmeBasePage {


    ArrayList<String> RICodes = new ArrayList<String>();
    /**
     * Author:PadminiB
     */

    public void FBLEntityBoarding() {

        try {
            Activity = "FBL: Entity Boarding";
            List<WebElement> entBoardingEls = this.driver().findElements(By.xpath("//span[@fieldname='Entity Boarding']"));
            // dataMap=ReadTestDataFromExcel(string, "CME");
            for (int i = 0; i < numOfEnts; i++) {
                sleep(500);
                WebDriverWait wait = new WebDriverWait(this.driver(), 10L);
                wait.until(ExpectedConditions.elementToBeClickable(this.driver().findElement(By.xpath("//span[@fieldname='Borrowing Entities']//following::span[1]/div[" + (i + 1) + "]/img[2]"))));
                this.driver().findElement(By.xpath("//span[@fieldname='Borrowing Entities']//following::span[1]/div[" + (i + 1) + "]/img[2]")).click();
                sleep(500);
                entBoardingEls.get(i).click();
                start = System.currentTimeMillis();
                if (isElementExist(ScreenNotSavedMsg, 2)) {
                    sleep(500);
                    click(NoBtn);
                }
                waitForInVisibile(PleaseWait);
                VerifyScreenName(EntityBoardingPage.EBScreen);
                SwitchToAngularFrame();
                waitForClickable(EntityBoardingPage.ExpandAll);
                finish = System.currentTimeMillis();
                timeElapsed = (finish - start)/1000;
                PerfMetrics.add(new String[] { Activity, "Entity Boarding Screen", String.valueOf(timeElapsed) });
                sleep(1000);
                clickJS(EntityBoardingPage.ExpandAll);
                sleep(1000);
                VerifyEntityBasicInfoBeforeBooking(i);
                click(EntityBoardingPage.BoardEntity);
                start = System.currentTimeMillis();
                waitForInVisibile(AngPleaseWait);
                sleep(1000);
                String SuccessMsg = getElementText(EntityBoardingPage.EBSuccessMsgPopup);
                if (SuccessMsg.equalsIgnoreCase("Entity and all its basic details are created in Loan IQ.")) {
                    finish = System.currentTimeMillis();
                    timeElapsed = (finish - start)/1000;
                    PerfMetrics.add(new String[] { Activity, "Entity Boarded", String.valueOf(timeElapsed) });
                    click(AngOKBtn);
                    waitForInVisibile(AngPleaseWait);
                    Reporter.pass("Entity Boarding is Success for Entity#"+(i+1));
                } else if (SuccessMsg.toLowerCase().contains("error") || SuccessMsg.toLowerCase().contains("fail")) {
                    Reporter.fail("Entity Boarding is Failed");
                    Assert.fail();
                }
                Reporter.addScreenshot("Entity Boarding is Success", "Entity Boarding is Success");
                VerifyEntityBasicInfoAfterBooking(i);
                sleep(1000);
                VerifyProfileLocationsTab(i);
                VerifyContactsTab(i);
                if (!multiValueMap.get("Relationship Type").get(i).equalsIgnoreCase("Guarantor")) {
                    VerifyRemitInstructionTab(i);
                }
                VerifyMisAdditionalFieldsTab(i);
                VerifyServicingGroupsTab(i);
                VerifyCommentsTab(i);
                sleep(500);
                this.driver().switchTo().defaultContent();
                SwitchToCMEFrame();
            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Author:PadminiB
     */
    public void VerifyContactsTab(int i) {
        try {
            sleep(1000);
            click(EntityBoardingPage.ContactTab);
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            sleep(1000);
            click(AngAddBtn);
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            VerifyContactInfo(i);

        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
        String VerifycolorIndicator = VerifyEBDBTabsindicator(EntityBoardingPage.ContactsIndicator);
        if (VerifycolorIndicator.equalsIgnoreCase("Complete")) {
            Reporter.pass("Contacts tab color present in green color");
        } else {
            Reporter.fail("Contacts tab not updated successfully");
        }
    }

    /**
     * Author:PadminiB
     */
    public void VerifyRemitInstructionTab(int i) {
        try {
            sleep(3000);
            click(EntityBoardingPage.RITab);
            start = System.currentTimeMillis();
            sleep(2000);
            waitForInVisibile(AngPleaseWait);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Remit Instructions Loading", String.valueOf(timeElapsed) });
            VerifyRemitInstructionsTab(i);
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
        String VerifycolorIndicator = VerifyEBDBTabsindicator(EntityBoardingPage.ContactsIndicator);
        if (VerifycolorIndicator.equalsIgnoreCase("Complete")) {
            Reporter.pass("Remit Instructions tab color present in green color");
        } else {
            Reporter.fail("Remit Instructions tab not updated successfully");
        }
    }

    /**
     * Author:PadminiB
     */
    public void VerifyMisAdditionalFieldsTab(int i) {
        try {
            sleep(1000);
            click(EntityBoardingPage.MISTab);
            start = System.currentTimeMillis();
            sleep(2000);
            waitForInVisibile(AngPleaseWait);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "MIS/AF Tab", String.valueOf(timeElapsed) });
            VerifyMisAdditionalInformation(i);
            click(EntityBoardingPage.UpdateMIS);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "MIS/AF Update", String.valueOf(timeElapsed) });
            this.driver().switchTo().defaultContent();
            SwitchToCMEFrame();
            List<WebElement> entBoardingEls = this.driver().findElements(By.xpath("//span[@fieldname='Entity Boarding']"));
            entBoardingEls.get(i).click();
            sleep(5000);
            SwitchToAngularFrame();
            waitForInVisibile(AngPleaseWait);

        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }

    }

    /**
     * Author:PadminiB
     */
    public void VerifyServicingGroupsTab(int i) {
        try {
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            sleep(1000);
            waitForClickable(EntityBoardingPage.ServeGroupTab);
            click(EntityBoardingPage.ServeGroupTab);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait, 1000);
            clickJS(EntityBoardingPage.SGAddBtn);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Servicing Group Tab Load", String.valueOf(timeElapsed) });
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            VerifyServicingGroups(i);
            sleep(1000);
            click(EntityBoardingPage.UpdateSG);
            start = System.currentTimeMillis();
            sleep(2000);
            waitForInVisibile(AngPleaseWait);
            String SgSuccessMsg = GetCellValue(0, 5, EntityBoardingPage.SGStatusMsg);
            if (SgSuccessMsg.equalsIgnoreCase("Servicing Group successfully created. Location completed.")) {
                Reporter.pass("Servicing Group successfully created. Location completed.");
                finish = System.currentTimeMillis();
                timeElapsed = (finish - start)/1000;
                PerfMetrics.add(new String[] { Activity, "Servicing Group Update", String.valueOf(timeElapsed) });
            } else {
                Reporter.fail("Servicing Group not created. Location completion failed.");
                Assert.fail();
            }
            String ServingGrpXpsth = "//fieldset[@class='servicing_group_grid']//table//tbody//tr//td[1]//span[@class='font-data']";
            String sgalias = getElementText(GetLocator(ServingGrpXpsth));
            ServiceGroup.add(i, sgalias);
            String StandardRemitInstxpath = "//fieldset[@class='servicing_group_grid']//table//tbody//tr//td[5]//span[@class='font-data']";
            String StdPreferedRI = getElementText(GetLocator(StandardRemitInstxpath));
            PreferedRemitInstructions.add(i, StdPreferedRI);

        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
        String VerifycolorIndicator = VerifyEBDBTabsindicator(EntityBoardingPage.SGIndicator);
        if (VerifycolorIndicator.equalsIgnoreCase("Complete")) {
            Reporter.pass("Servicing Group tab color present in green color");
        } else {
            Reporter.fail("Servicing Group tab not updated successfully");
        }
    }

    /**
     * Author:PadminiB
     */
    public void VerifyCommentsTab(int i) {
        try {
            sleep(1000);
            click(EntityBoardingPage.CommentsTab);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            sleep(500);
            clickJS(EntityBoardingPage.CommentAdd);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "CommentsTab Tab Load", String.valueOf(timeElapsed) });
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            inputText(EntityBoardingPage.ConSub, " Test Comment");
            sleep(1000);
            inputText(EntityBoardingPage.ConComment, "Entity Booked to LIQ");
            click(EntityBoardingPage.UpdateComment);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
            waitForInVisibile(AngPleaseWait);
            String CommentsMessageXpath = "//fieldset[@class='Comments_Grid']//table//tbody/tr[1]/td[4]//span[@class='font-data']";
            if (getElementText(GetLocator(CommentsMessageXpath)).equalsIgnoreCase("Comment added successfully")) {
                Reporter.pass("Verify Comments Message in Comments tab present as Comment added successfully for the borrower " + (i + 1));
                finish = System.currentTimeMillis();
                timeElapsed = (finish - start)/1000;
                PerfMetrics.add(new String[] { Activity, "Comments Update", String.valueOf(timeElapsed) });

            } else {
                Reporter.fail("Comments were not added successfully for the entity " + (i + 1));
            }
            String CommetnstabIndicator = VerifyEBDBTabsindicator(EntityBoardingPage.commentsTabIndicator);
            if (CommetnstabIndicator.equalsIgnoreCase("Complete")) {
                Reporter.pass("Comments tab color turned to green");
            } else {
                Reporter.fail("Updating Comments tab is not successful");
            }
            Reporter.addScreenshot("Comments tab", "Comments tab");

        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }


    public void VerifyEntityBasicInfoBeforeBooking(int i) {
        String entityLegalName = multiValueMap.get("Legal Name").get(i);
        String naicsCode = multiValueMap.get("NAICS Code 1").get(i);
        String address1 = multiValueMap.get("Address 1").get(i);
        String city = multiValueMap.get("City").get(i);
        String state = multiValueMap.get("State").get(i);
        String zip = multiValueMap.get("Zip Code").get(i);
        String phoneNum = multiValueMap.get("Primary Phone Number").get(i);
        //String country = multiValueMap.get("County").get(i);
        String bank = dataMap.get("Bank");
        String Fname = multiValueMap.get("First Name").get(i);
        String Lname = multiValueMap.get("Last Name").get(i);
        String ssn = multiValueMap.get("SSN").get(i);
        String CustId = getElementText(EntityBoardingPage.entityLiqId);
//        String shortName = CustId + "," + ssn.substring(7, 11) + "," + Lname;
        int failCount = 0;
        try {
            if (!getElementText(EntityBoardingPage.legalName).equalsIgnoreCase(entityLegalName)) {
                Reporter.fail("Verifying Legal Name in Basic Info Section. Expected value: " + entityLegalName);
                failCount++;
            }
//            if (!getElementAttribute(EntityBoardingPage.shortName, "value").equalsIgnoreCase(shortName)) {
//                Reporter.fail("Verifying Short Name in Basic Info Section. Expected value: " + shortName);
//                failCount++;
//            }
            if (!getElementText(EntityBoardingPage.branch).equalsIgnoreCase(GetFBLBranch())) {
                Reporter.fail("Verifying Branch in Basic Info Section. Expected value: " + GetFBLBranch());
                failCount++;
            }

            if (!getElementText(EntityBoardingPage.department).contains(GetFBLDepartment())) {
                Reporter.fail("Verifying Department in Basic Info Section. Expected value: " + GetFBLDepartment());
                failCount++;
            }

            if (!getElementText(EntityBoardingPage.expenseCode).equalsIgnoreCase(GetFBLExpenseCode())) {
                Reporter.fail("Verifying Expense Code in Basic Info Section. Expected value: " + GetFBLExpenseCode());
                failCount++;
            }
            if (!getElementText(EntityBoardingPage.classification).equalsIgnoreCase(GetBusinessClassification(i))) {
                Reporter.fail("Verifying classification in Basic Info Section. Expected value: " + GetBusinessClassification(i));
                failCount++;
            }

            if (getElementText(EntityBoardingPage.entityLiqId).length() == 0) {
                Reporter.fail("Verifying Loan IQ ID in Basic Info Section. Field is empty");
                failCount++;
            }

            if (!getElementText(EntityBoardingPage.ebAddLine1).equalsIgnoreCase(address1)) {
                Reporter.fail("Verifying Address Line1 in Legal Address section . Expected value: " + address1);
                failCount++;
            }
            if (!getElementText(EntityBoardingPage.ebCity).equalsIgnoreCase(city)) {
                Reporter.fail("Verifying City in Legal Address section. Expected value: " + city);
                failCount++;
            }
            if (!getElementText(EntityBoardingPage.ebZip).equalsIgnoreCase(zip)) {
                Reporter.fail("Verifying Zipcode in Legal Address section. Expected value: " + zip);
                failCount++;
            }
            if (!getElementText(EntityBoardingPage.ebState).equalsIgnoreCase(state)) {
                Reporter.fail("Verifying State in Legal Address section. Expected value: " + state);
                failCount++;
            }

            if (!getElementText(EntityBoardingPage.ebPhone).equalsIgnoreCase(phoneNum)) {
                Reporter.fail("Verifying Primary Phone Number in Legal Address section. Expected value: " + phoneNum);
                failCount++;
            }
            if (!getElementText(EntityBoardingPage.ebPrimarySic).contains(naicsCode)) {
                Reporter.fail("Verifying NAICS Code in Industry Classification section. Expected value: " + naicsCode);
                failCount++;
            }
            if (getElementText(EntityBoardingPage.ebSicCountry).length() == 0) {
                Reporter.fail("Verifying NAICS Country in Industry Classification section. Expected value: ");
                failCount++;
            }
            if (failCount == 0) {
                Reporter.pass("Entity Basic Information Validations are Passed");
                Reporter.addScreenshot("Entity Summary", "Entity Summary");
            } else {
                Reporter.fail("Entity Basic Information Validations");
//                Assert.fail();
            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    public void VerifyEntityBasicInfoAfterBooking(int i) {
        try {
//            String bank = dataMap.get("Bank");
//            String Fname = multiValueMap.get("First Name").get(i);
//            String Mname = multiValueMap.get("Middle Name").get(i);
//            String Lname = multiValueMap.get("Last Name").get(i);
//            String ssn = multiValueMap.get("SSN").get(i);
//            String shortname = Lname + "," + Fname + " " + Mname + " "+ bank + "-" + ssn.substring(ssn.length()-4);

//            if (!getElementAttribute(EntityBoardingPage.shortName, "value").equalsIgnoreCase(shortname)) {
//                Reporter.fail("Verifying Short Name in Basic Info Section After booking. Expected: "+ shortname);
//
//            }
            if (!getElementText(EntityBoardingPage.entityStatus).equalsIgnoreCase("Skeletal")) {
                Reporter.fail("Verifying LIQ Status in Basic Info Section. Expected value: Skeletal");

            }

            Reporter.info("Verifying Entity Boarding Tabs indicators pre-updating to LIQ");

//            if (!getElementAttribute(EntityBoardingPage.profileLocationsIndicator, "uib-tooltip").equalsIgnoreCase("Complete")) {
//                Reporter.fail("Verifying Profile Locations tab indicator is complete pre-updating");
//            }
            if (!getElementAttribute(EntityBoardingPage.ContactsIndicator, "uib-tooltip").equalsIgnoreCase("Incomplete")) {
                Reporter.fail("Verifying Contacts tab indicator is incomplete");
            }
            if (!getElementAttribute(EntityBoardingPage.RemitInstrTabIndicator, "uib-tooltip").equalsIgnoreCase("Incomplete")) {
                Reporter.fail("Verifying Remit Instructions tab indicator is incomplete");
            }
            if (!getElementAttribute(EntityBoardingPage.SGIndicator, "uib-tooltip").equalsIgnoreCase("Incomplete")) {
                Reporter.fail("Verifying SG tab indicator is incomplete pre-updating");

            }
            if (!getElementAttribute(EntityBoardingPage.MISIndicator, "uib-tooltip").equalsIgnoreCase("Incomplete")) {
                Reporter.fail("Verifying MIS/AF tab indicator is incomplete");
            }
            if (!getElementAttribute(EntityBoardingPage.MISIndicator, "uib-tooltip").equalsIgnoreCase("Incomplete")) {
                Reporter.fail("Verifying MIS/AF tab indicator is incomplete");
            }

        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    public void VerifyProfileLocationsTab(int i) {
        Reporter.info("Verify profile locations tab for Entity# " + (i + 1));
        click(EntityBoardingPage.UpdatePL);
        start = System.currentTimeMillis();
        sleep(1000);
        waitForInVisibile(AngPleaseWait);
        int rowNum = 1;
        String tablexpath = null;

        if (multiValueMap.get("Entity Type").get(i).equalsIgnoreCase("Company")) {
            tablexpath = "//fieldset[@class='Profile']//table//tbody";
        } else {
            tablexpath = "//fieldset[@class='ProfileSSN']//table//tbody";
        }

        String ProfilemessageXpath = tablexpath + "/tr[" + rowNum + "]/td[10]//span[@class='font-data']";
        //   String plmsg = GetCellValue(1, 10, EntityBoardingPage.PlMsgTable);
        if (getElementText(GetLocator(ProfilemessageXpath)).equalsIgnoreCase("Profile Created.") || getElementText(GetLocator(ProfilemessageXpath)).equalsIgnoreCase("Profile Updated.")) {
            Reporter.pass("Profile/Locations are updated to Loan IQ Successfully");
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Profile/Locations Update", String.valueOf(timeElapsed) });
        } else {
            Reporter.fail("Profile/Locations are not updated to Loan IQ");
            Assert.fail();
        }

        waitForInVisibile(AngPleaseWait);
        VerifyprofilelocationtabInfo(i);
    }

    public void VerifyprofilelocationtabInfo(int i) {
        sleep(1000);
        //Inputs from test data
        String Profile = multiValueMap.get("LIQ Profile Type").get(i);
        String SendtoLIQ = "Yes";
        //String DefaultLocationDesc = BranchDescription + "," + " "+ multiValueMap.get("State").get(i)+" "+ "United States";
        String DefaultLocationDesc = GetLenderLocationCode();

        int rowNum = 1;
        int failCount = 0;
        String tablexpath = "//fieldset[@class='ProfileSSN']//table//tbody";
        if (multiValueMap.get("Entity Type").get(i).equalsIgnoreCase("Company")) {
            tablexpath = "//fieldset[@class='Profile']//table//tbody";
        }
        String ProfileXpath = tablexpath + "/tr[" + rowNum + "]/td[5]//span[@class='font-data']";
        String SendToLIQXpath = tablexpath + "/tr[" + rowNum + "]/td[8]//span[@class='font-data']";
        String DefaultLocationDescXpath = tablexpath + "/tr[" + rowNum + "]/td[13]//span[@class='font-data']";

        if (!getElementText(GetLocator(ProfileXpath)).equalsIgnoreCase(Profile)) {
            Reporter.fail("Verifying profile as " + Profile);
            Reporter.addScreenshot("Profile tab", "Profile tab");
            failCount++;
        }

        if (!getElementText(GetLocator(SendToLIQXpath)).equalsIgnoreCase(SendtoLIQ)) {
            Reporter.fail("Verifying Send to LIQ as " + SendtoLIQ);
            Reporter.addScreenshot("Profile tab", "Profile tab");
            failCount++;
        }
        if (!getElementText(GetLocator(DefaultLocationDescXpath)).equalsIgnoreCase(DefaultLocationDesc)) {
            Reporter.fail("Verifying Default location description" + DefaultLocationDesc);
            Reporter.addScreenshot("Profile tab", "Default location descriptionProfile tab");
            failCount++;
        }
        if (failCount != 0) {
            Reporter.fail(failCount + " Failed Validations found in Profile/Locations tab");
//            Assert.fail();
        } else {
            Reporter.pass("Profile/locations tab Validations for borrower# " + (i + 1));

        }
        Reporter.addScreenshot("Profile Locations tab", "profile locations tab");
    }


    public void VerifyContactInfo(int i) {

        String relationshipType = multiValueMap.get("LIQ Profile Type").get(i); //borrower
        String entityFName = multiValueMap.get("First Name").get(i);
        String entityMName = multiValueMap.get("Middle Name").get(i);
        String entityLName = multiValueMap.get("Last Name").get(i);
        String phoneNum = multiValueMap.get("Primary Phone Number").get(i);
        String address1 = multiValueMap.get("Address 1").get(i);
        String address2 = multiValueMap.get("Address 2").get(i);
        String city = multiValueMap.get("City").get(i);
        String state = multiValueMap.get("State").get(i);
        String zip = multiValueMap.get("Zip Code").get(i);
        String county = multiValueMap.get("County").get(i);
        String addr = null;
        if (address2==null) {
            addr = address1 + "," + " " + city + "," + " " + state + "," + " " + zip + "," + " " + county;
        } else {
            addr = address1 + "," + address2+", " + city + "," + " " + state + "," + " " + zip + "," + " " + county;
        }

        String ProfLocation=null;
        if (multiValueMap.get("Entity Type").get(i).equalsIgnoreCase("Company")) {
            entityFName = CompanyContactFName.get(CustomerNumber.get(i));
            entityMName = CompanyContactMName.get(CustomerNumber.get(i));
            entityLName = CompanyContactLName.get(CustomerNumber.get(i));
            CompanyContactSG.put(CustomerNumber.get(i), entityFName.substring(0, 1) + entityMName.substring(0, 1) + entityLName.substring(0, 1));
            CompanyContact = entityFName +" "+ entityLName;
            phoneNum = CompanyContactPhone.get(CustomerNumber.get(i));
            addr = CompanyContactAddress.get(CustomerNumber.get(i));
        }

        int failCount = 0;
        try {

            String Fname = getElementAttribute(EntityBoardingPage.ContactFname, "value");
            String Mname = getElementAttribute(EntityBoardingPage.ContactMname, "value");
            String Lname = getElementAttribute(EntityBoardingPage.ContactLname, "value");
            if (!Fname.equalsIgnoreCase(entityFName)) {
                Reporter.fail("Verifying Contact First Name in Contact Details section. Expected value: " + entityFName);
                failCount++;
            }
            if (!Mname.equalsIgnoreCase(entityMName)) {
                Reporter.fail("Verifying Middle Name in Contact Details section. Expected value: " + entityFName);
                failCount++;
            }
            if (!Lname.equalsIgnoreCase(entityLName)) {
                Reporter.fail("Verifying Last Name in Contact Details section. Expected value: " + entityLName);
                failCount++;
            }
            if (!getElementAttribute(EntityBoardingPage.ContactPhNum, "value").equalsIgnoreCase(phoneNum)) {
                Reporter.fail("Verifying Primary Contact Number in Contact Details section. Expected value: " + phoneNum);
                failCount++;
            }

            switch (relationshipType) {
                case "Borrower":
                    ProfLocation = "Borrower/"+ GetProfileLocations();
                    if (!getElementText(EntityBoardingPage.ContactProfilocations).equalsIgnoreCase(ProfLocation)) {
                        Reporter.fail("Verifying Profile/Locations in Contact Details section");
                        failCount++;
                    }
                    break;
                case "Guarantor":
                    ProfLocation = "Guarantor/"+ GetProfileLocations();
                    if (!getElementText(EntityBoardingPage.ContactProfilocations).equalsIgnoreCase(ProfLocation)) {
                        Reporter.fail("Verifying Profile/Locations in Contact Details section.");
                        failCount++;
                    }
                    break;
            }
            ContactsProfileLocations.add(i, ProfLocation);

            if (!getElementText(EntityBoardingPage.ContactPreferredLang).equalsIgnoreCase("English")) {
                Reporter.fail("Verifying Preferred Language in Contact Details section. Expected value: " + "English");
                failCount++;
            }
            if (!getElementText(EntityBoardingPage.ContactPrimContactPurpose).equalsIgnoreCase("Billing")) {
                Reporter.fail("Verifying Primary Contact Purpose in Contact Details section. Expected value: " + "Billing");
                failCount++;
            }
            if (!getElementText(EntityBoardingPage.ContactOtherContactPurpose).equalsIgnoreCase("Servicing, Statement")) {
                Reporter.fail("Verifying Other Contact Purpose in Contact Details section. Expected value: " + "Borrower/Bryan, TX United States");
                failCount++;
            }
            if (!getElementText(EntityBoardingPage.ContactNotificationMethods).equalsIgnoreCase("Mail")) {
                Reporter.fail("Verifying Notification Method in Contact Details section. Expected value: " + "Mail");
                failCount++;
            }
            if (!getElementText(EntityBoardingPage.ContactPreferNotificationMethods).equalsIgnoreCase("Mail")) {
                Reporter.fail("Verifying Preferred Notification Method in Contact Details section. Expected value: " + "Mail");
                failCount++;
            }
            if (!getElementText(EntityBoardingPage.NotificationAddress).equalsIgnoreCase("Legal Address")) {
                Reporter.fail("Verifying Preferred Notification Method in Contact Details section. Expected value: " + "Legal Address");
                failCount++;
            }

            if (!getElementText(EntityBoardingPage.NotificationAddressDesc).equalsIgnoreCase(addr)) {
                Reporter.fail("Verifying Preferred Notification Method in Contact Details section. Expected value: " + addr);
                failCount++;
            }
            if (failCount == 0) {
                Reporter.pass("Contacts tab Validations");

            } else {
                Reporter.fail("Contacts tab Validations");
//                Assert.fail();
            }
            Reporter.addScreenshot("Contacts tab", "Contacts tab");
            sleep(1000);
            click(EntityBoardingPage.UpdateContacts);
            start = System.currentTimeMillis();
            sleep(1000);
            waitForInVisibile(AngPleaseWait);
//            waitForInVisibile(AngPleaseWait);
            String tableXpath = "//st-grid-box[@fieldname='ContactSummary']/table";
            String ContactsMessageXpath = tableXpath + "/tbody/tr[1]/td[count(" + tableXpath + "/thead/tr/th[text()='Message']/preceding-sibling::th)+1]";
            String ContactsMSG = getElementText(GetLocator(ContactsMessageXpath));

            if (ContactsMSG.equalsIgnoreCase("Language: attribute is Required")){
                click(EntityBoardingPage.UpdateContacts);
                sleep(1000);
                waitForInVisibile(AngPleaseWait);
            }
            ContactsMSG = getElementText(GetLocator(ContactsMessageXpath));
            if (ContactsMSG.equalsIgnoreCase("Contact successfully booked to Loan IQ")) {
                Reporter.pass("Contacts are Booked to Loan IQ Successfully");
                finish = System.currentTimeMillis();
                timeElapsed = (finish - start)/1000;
                PerfMetrics.add(new String[] { Activity, "Contacts Update", String.valueOf(timeElapsed) });
            }else{
                Reporter.fail("Contacts are not Booked to Loan IQ");
//                Assert.fail();
            }


        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());

        }
    }

    public int getRelationshipTypeValue(String relationshipType, String ProfilesandLocationsXpath) {
        int failcount = 0;
        switch (relationshipType) {
            case "Borrower":
                if (!getElementText((GetLocator(ProfilesandLocationsXpath))).equalsIgnoreCase("Borrower/"+ GetProfileLocations())) {
                    Reporter.fail("Verifying Profile/Locations in Contact Details section. Expected value: " + "Borrower/Bryan, TX United States");
                    failcount++;
                }
                break;
            case "Guarantor":
                if (!getElementText((GetLocator(ProfilesandLocationsXpath))).equalsIgnoreCase("Guarantor/"+ GetProfileLocations())) {
                    Reporter.fail("Verifying Profile/Locations in Contact Details section. Expected value: " + "Guarantor/Bryan, TX United States");
                    failcount++;
                }
                break;
        }
        return failcount;
    }

    public void VerifyRemitInstructionsTab(int i) {
        Reporter.info("Clicking Remittance Instructions tab for Entity# " + (i + 1));
        sleep(6000);
        //this.driver().findElement(By.linkText("Remittance Instructions")).click();
        // sleep(1000);
        // waitForVisible(GetLocator("//span[@class='badge badge-primary' and contains(text(), 'Remittance Instructions for "+CustomerNumber.get(i)+"')]"));
        String relationshipType = multiValueMap.get("LIQ Profile Type").get(i);
        int failCount = 0;
        int rowNum;
        RICodes.clear();


        if (!multiValueMap.get("Relationship Type").get(i).equalsIgnoreCase("Guarantor")) {
            RICodes.add(RICodes.size(), "AGRLN1");
        }
        RICodes.add(RICodes.size(),"LCKBX1");
        RICodes.add(RICodes.size(),"EXTCC1");
        RICodes.add(RICodes.size(),"LNFH1");
        RICodes.add(RICodes.size(),"RDC1");
        RICodes.add(RICodes.size(),"ABOL1");
        RICodes.add(RICodes.size(),"REVSL1");
        RICodes.add(RICodes.size(),"MRDC1");
        RICodes.add(RICodes.size(),"TXWTH1");

       /* for (int j=0; j<numOfLoans; j++) {
                if (multiValueMap.get("Product Type").get(j).contains("Revolver") && !multiValueMap.get("Relationship Type").get(i).equalsIgnoreCase("Guarantor")) {
                    RICodes.add(RICodes.size(),"AGRLN1");
                    break;
                }
            }*/

        String tableXpath = "//st-grid-box[@fieldname='ri_grid']/table";
        String DescriptionXpath = null;
        String FBLRICode          =null;
        int matchCount =0;

        List<WebElement> tRows = this.driver().findElements(By.xpath("//st-grid-box[@fieldname='ri_grid']/table/tbody/tr"));
        int pageCount = 1;
        if (RICodes.size()>5){
            pageCount = 2;
        }
        for (int p=0; p<pageCount; p++) {
            if (p>0) {
                sleep(500);
                clickJS(GetLocator("//st-grid-box[@fieldname='ri_grid']//following-sibling::li[2]/a"));
                sleep(2000);
                tRows = this.driver().findElements(By.xpath("//st-grid-box[@fieldname='ri_grid']/table/tbody/tr"));
            }
            for (int r=1; r<=tRows.size(); r++) {
                DescriptionXpath    =tableXpath + "/tbody/tr[" + r + "]/td[2]";
                FBLRICode           =getElementText(GetLocator(DescriptionXpath));
                if (!RICodes.contains(FBLRICode)) {
                    Reporter.fail("Verifying Payment Method defaulted: "+ FBLRICode);
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

    public void VerifyMisAdditionalInformation(int i) {
        try {
            Reporter.info("Clicking Additional Information tab for Entity# " + (i + 1));
            sleep(1000);
            // this.driver().findElement(By.linkText("Additional Information")).click();
            // sleep(1000);
            // waitForVisible(GetLocator("//span[@class='badge badge-primary' and contains(text(), 'MIS Codes for "+multiValueMap.get("Legal Name").get(i)+"')]"));
            int failCount = 0;
            int rowNum = 0;

            String tableXpath = "//table[@class='table table-sm' and contains(.,'MIS TYPE')]";
            String FIPSCountyCode = multiValueMap.get("County").get(i) + " " + multiValueMap.get("State").get(i);
            String FIPSStateCode = multiValueMap.get("State").get(i);
            String SimilarEntity = "No";
            String OfficailLoanType = "Not Official";

            if (!getElementText(EntityBoardingPage.FipsStateCode).contains(FIPSStateCode)) {
                Reporter.fail("Verifying Fips State Code in MIS Codes section. Expected value: " + FIPSStateCode);
                failCount++;
            }
            if (!getElementText(EntityBoardingPage.FipsCountyCode).equalsIgnoreCase(FIPSCountyCode.toUpperCase(Locale.ROOT))) {
                Reporter.fail("Verifying Fips County Code in MIS Codes section. Expected value: " + FIPSCountyCode);
                failCount++;
            }
            if (!getElementText(EntityBoardingPage.SimilarEntity).equalsIgnoreCase(SimilarEntity)) {
                Reporter.fail("Verifying Similar Entity in MIS Codes section. Expected value: " + SimilarEntity);
                failCount++;
            }
            if (!getElementText(EntityBoardingPage.OfficialEntity).equalsIgnoreCase(OfficailLoanType)) {
                Reporter.fail("Verifying Official Entity in Additional fields section. Expected value: " + OfficailLoanType);
                failCount++;
            }
//
            if (failCount != 0) {
                Reporter.fail(failCount + " Failed Validations found in MIS/Additional Fields tab");
            } else {
                Reporter.pass("Remit Instructions tab Validations for Entity# " + (i + 1));

            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());

        }
        Reporter.addScreenshot("MIS Additional tab", "MIS Additional tab");
    }

    public void VerifyServicingGroups(int i) {
        try {
            Reporter.info("Clicking Servicing Groups tab for Entity# " + (i + 1));
            sleep(1000);
            String Fname = null;
            String Mname = null;
            String Lname = null;
            String sgalias = null;
            String sgname = null;
            if (!multiValueMap.get("Entity Type").get(i).equalsIgnoreCase("Company")) {
                Fname = multiValueMap.get("First Name").get(i);
                Mname = multiValueMap.get("Middle Name").get(i);
                Lname = multiValueMap.get("Last Name").get(i);
                sgalias = Fname.substring(0, 1) + Mname.substring(0, 1) + Lname.substring(0, 1);
                sgname = Fname +" "+ Lname;
            }else{
                sgalias = CompanyContactSG.get(CustomerNumber.get(i));
                sgname = CompanyContact;
            }



            sleep(2000);
            waitForInVisibile(AngPleaseWait);
            int failCount = 0;

            if (!getElementText(EntityBoardingPage.SGPl).equalsIgnoreCase(ContactsProfileLocations.get(i))) {
                Reporter.fail("Verifying Profile/Locations in Contact Details section. Expected value: " + ContactsProfileLocations.get(i));
                failCount++;
            }
            if (!getElementText(EntityBoardingPage.SGContacts).equalsIgnoreCase(sgname)) {
                Reporter.fail("Verifying Contacts in Servicing group details Section. Expected value: " + sgname);
                failCount++;
            }
            if (!getElementText(EntityBoardingPage.SGPrimaryContact).equalsIgnoreCase(sgname)) {
                Reporter.fail("Verifying Primary contact in Servicing group details Section. Expected value: " + sgname);
                failCount++;
            }
            sleep(1000);
           WebElement selectElement = null;
            if (!multiValueMap.get("LIQ Profile Type").get(i).equalsIgnoreCase("Guarantor")) {
                ArrayList<String> SGRIdefaulted = new ArrayList<String>();
                ArrayList<String> SGRIExpected = new ArrayList<String>();
                String[] SGRI;
                SGRI = getElementText(EntityBoardingPage.SGRemitInstr).replace(" ", "").split(",");
                String tempStr = "ABOL1,AGRLN1,EXTCC1,LCKBX1,LNFH1,MRDC1,RDC1,REVSL1,TXWTH1";
                String[] ExpectedSGRI = tempStr.split(",");
                SGRIdefaulted.addAll(Arrays.asList(SGRI));
                SGRIExpected.addAll(Arrays.asList(ExpectedSGRI));
                Collections.sort(SGRIdefaulted);
                Collections.sort(SGRIExpected);
                if (!SGRIExpected.equals(SGRIdefaulted)) {
                    Reporter.fail("Verifying defaulted RI's in Servicing group details Section. Expected value: " + SGRIExpected + " VS " + SGRIdefaulted);

                }
                if (!getElementText(EntityBoardingPage.SGStandardRI).equalsIgnoreCase("EXTCC1")) {
                    Reporter.fail("Verifying defaulted Standard RI in Servicing group details Section. Expected value: " + sgname);
                    failCount++;
                }
            }
            selectElement = this.driver().findElement(By.xpath("//input[@fieldname='standard_checkbox']"));
            if (!selectElement.isSelected()) {
                Reporter.fail("Standard checkbox not checked");
                sleep(1000);
                selectElement.click();
            }
            if (!getElementAttribute(EntityBoardingPage.SGAlias, "value").equalsIgnoreCase(sgalias)) {
                Reporter.fail("Verifying Alias in Servicing group details Section. Expected value: " + sgalias);
                failCount++;
            }
//            if (!getElementAttribute(EntityBoardingPage.SGName, "value").equalsIgnoreCase(Lname)) {
//                Reporter.fail("Verifying Name in Servicing group details Section. Expected value: " + Lname);
//                failCount++;
//            }
            if (failCount == 0) {
                Reporter.pass("Remit Instructions tab Validations for Entity# " + (i + 1));
            }



            Reporter.addScreenshot("Servicing Groups tab", "Servicing Groups tab");
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());

        }
    }

}






