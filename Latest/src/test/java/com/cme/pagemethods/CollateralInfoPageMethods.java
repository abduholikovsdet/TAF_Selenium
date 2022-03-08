package com.cme.pagemethods;
import com.cme.pages.CollateralInfoPage;
import com.fcbt.taf.core.driver.ui.Locator;
import com.fcbt.taf.core.reporting.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.Arrays;

public class CollateralInfoPageMethods extends CmeBasePage{
    /**
     * Author: Muzaffar A.
     * Description: Selects owners of the collateral
     */
    public void SelectCollatOwnerFirstEntity(int i){
        try {
            int[] owners;
            int numberOfOwners;
            click(CollateralInfoPage.collatOwnerDDbtn);
            sleep(500);
            String ownersOfCollatFlg = multiValueMap.get("Owners of the Collateral").get(i);
            if (ownersOfCollatFlg.contains(",")) {
                owners = Arrays.stream(ownersOfCollatFlg.split(",")).mapToInt(Integer::parseInt).toArray();
                numberOfOwners = owners.length;
                for (int j=0; j<numberOfOwners; j++) {
                    sleep(500);
                    String xpath = "//div[@class='gridBox' and contains(@style, 'display: block')]//div[@class='gridBoxCell' and contains(text(), '"+ CustomerNumber.get(owners[j]-1) + "')]";
                    click(GetLocator(xpath));
                    sleep(500);
                    Actions action = new Actions(this.driver());
                    action.sendKeys(Keys.SPACE).build().perform();
//                    sleep(500);
                }
            } else {
                int owner = Integer.parseInt(ownersOfCollatFlg);
                String xpath = "//div[@class='gridBox' and contains(@style, 'display: block')]//div[@class='gridBoxCell' and contains(text(), '"+ CustomerNumber.get(owner-1) + "')]";
                click(GetLocator(xpath));
                click(GetLocator("//div[@class='gridBoxCell' and contains(text(), '"+ CustomerNumber.get(owner-1)+"')]"));
                sleep(500);
                Actions action = new Actions(this.driver());
                action.sendKeys(Keys.SPACE).build().perform();
                sleep(500);
            }
            sleep(500);
            click(CollateralInfoPage.collatOwnerDDbtn);
            sleep(500);
            pressTabKey(CollateralInfoPage.collatOwnerName);
            Reporter.info("Selecting owner of the collateral for Collateral# "+(i+1));
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Selecting owner of the collateral "+ "\n"+ e.getMessage());
        }
    }
    //Selects Facility secured by this collateral
    public void SelectFacilitsSecuredByThisCollat(int i){
        try {
            int[] facils;
            int numberOfFacils;
            String facilsFlg = multiValueMap.get("Facilities Secured by this Collateral").get(i);
            sleep(500);
            click(CollateralInfoPage.facilitiesSecuredByCollatDDbtn);
            sleep(500);
            if (facilsFlg.contains(",")) {
                facils = Arrays.stream(facilsFlg.split(",")).mapToInt(Integer::parseInt).toArray();
                numberOfFacils = facils.length;
                for (int j=0; j<numberOfFacils; j++) {
                    sleep(500);
                    String xpath = "//div[@class='gridBox' and contains(@style, 'display: block')]//div[@class='gridBoxCell' and contains(text(), '"+ FacilityNumber.get(facils[j] - 1) + "')]";
                    click(GetLocator(xpath));
                    sleep(500);
                    Actions action = new Actions(this.driver());
                    action.sendKeys(Keys.SPACE).build().perform();
//                    sleep(500);
                }
            } else {
                int facil = Integer.parseInt(facilsFlg);
                String xpath = "//div[@class='gridBox' and contains(@style, 'display: block')]//div[@class='gridBoxCell' and contains(text(), '"+ FacilityNumber.get(facil-1) + "')]";
                click(GetLocator(xpath));
                sleep(500);
                Actions action = new Actions(this.driver());
                action.sendKeys(Keys.SPACE).build().perform();
                sleep(500);
            }
            click(CollateralInfoPage.facilitiesSecuredByCollatDDbtn);
            sleep(500);
            pressTabKey(CollateralInfoPage.facilitiesSecuredByCollat);

            Reporter.info("Selecting Facility secured by this collateral# "+(i+1));
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Selecting Facility secured by this collateral"+ "\n"+ e.getMessage());
        }
    }
    //Selects Primary Collateral Yes/No RB
    public void isThisPrimaryCollateral(int i){
        String yesNo = multiValueMap.get("Is this a Primary Collateral").get(i);
        sleep(500);
        int[] facils;
        int numberOfFacils;
        try {
            if (yesNo.equalsIgnoreCase("Yes")) {
                CheckRadioButton(CollateralInfoPage.isThisPrimaryCollatYes);
                sleep(500);
                click(CollateralInfoPage.primaryForWhichFacilityDDbtn);
                sleep(500);
                String primaryForWhichFacFlg = multiValueMap.get("Facilities Secured by this Collateral").get(i);
                if (primaryForWhichFacFlg.contains(",")) {
                    facils = Arrays.stream(primaryForWhichFacFlg.split(",")).mapToInt(Integer::parseInt).toArray();
                    numberOfFacils = facils.length;
                    for (int j = 0; j < numberOfFacils; j++) {
                        CollateralDesc.put(FacilityNumber.get(facils[j]-1), multiValueMap.get("Display Description").get(i));
                        sleep(500);
                        String xpath = "//div[@class='gridBox' and contains(@style, 'display: block')]//div[@class='gridBoxCell' and contains(text(), '"+ FacilityNumber.get(facils[j] - 1) + "')]";
                        click(GetLocator(xpath));
                        sleep(500);
                        Actions action = new Actions(this.driver());
                        action.sendKeys(Keys.SPACE).build().perform();
//                        sleep(500);
                    }
                }else{
                    int facil = Integer.parseInt(primaryForWhichFacFlg);
                    CollateralDesc.put(FacilityNumber.get(facil-1), multiValueMap.get("Display Description").get(i));
                    String xpath = "//div[@class='gridBox' and contains(@style, 'display: block')]//div[@class='gridBoxCell' and contains(text(), '"+ FacilityNumber.get(facil-1) + "')]";
                    click(GetLocator(xpath));
                    sleep(500);
                    Actions action = new Actions(this.driver());
                    action.sendKeys(Keys.SPACE).build().perform();
                    sleep(500);

                }
                click(CollateralInfoPage.primaryForWhichFacilityDDbtn);
                pressTabKey(CollateralInfoPage.primaryForWhichFacility);

            } else {
                CheckRadioButton(CollateralInfoPage.isThisPrimaryCollatNo);
            }
            Reporter.info("Selecting Is this Primary Collateral RB as "+ yesNo);
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Selecting Is this Primary Collateral RB as "+ yesNo+ "\n"+ e.getMessage());
        }
    }
    //Selects Is this RE Collateral Yes/No RB
    public void isThisRealEstateCollateral(int i){
        String yesNo = multiValueMap.get("Is this Real Estate Collateral").get(i);
        String pool = multiValueMap.get("Collateral Poolable").get(i);
        sleep(500);
        try {
            if (yesNo.equalsIgnoreCase("Yes")) {
                CheckRadioButton(CollateralInfoPage.isThisRealEstateCollatYes);

            } else {
                CheckRadioButton(CollateralInfoPage.isThisRealEstateCollatNo);
                isThisCollateralPoolable(pool);

            }
            Reporter.info("Selecting Is this RE Collateral RB as "+ yesNo);
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Selecting Is this RE Collateral RB as "+ yesNo+ "\n"+ e.getMessage());
        }
    }

    //Selects Collateral Category, Collateral type and enters collateral description
    public void SelectCollatCategoryTypeDisplayDesc(int i){
        String collatCateg  =multiValueMap.get("Collateral Category").get(i);
        String collatType   =multiValueMap.get("Collateral Type").get(i);
        String displayDesc  =multiValueMap.get("Display Description").get(i);
        try {
            sleep(500);
            inputTextUsingJS(CollateralInfoPage.collatCategory, collatCateg);
            sleep(1000);
            inputTextUsingJS(CollateralInfoPage.collatType, collatType);
            inputText(CollateralInfoPage.collatDispalyDesc, displayDesc);
            sleep(500);
            pressTabKey(CollateralInfoPage.collatDispalyDesc);
            Reporter.info("Selecting Collateral Category as "+ collatCateg);
            sleep(3000);

        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Selecting Collateral Category as "+ collatCateg+ "\n"+ e.getMessage());
        }
    }

    //Enters RE Collateral value
    public void EnterRECollateralValue(int i){

        String collatMrktValue          =multiValueMap.get("Collateral Market Value").get(i);
        String optiCollat               =multiValueMap.get("Optimist Collateral").get(i);
        String collatValuationType      =multiValueMap.get("Valuation Type").get(i);
        String collatAppraiserEvaluator =Appraiser;
        String collatValuationDate      =multiValueMap.get("Loan Effective Date").get(0);

        try {
            if (!multiValueMap.get("Collateral Poolable").get(i).equalsIgnoreCase("Yes")) {
                inputText(CollateralInfoPage.collatMarketVal, collatMrktValue);
            }
            if (bankNumber.equalsIgnoreCase("001")&& complexity.equalsIgnoreCase("Traditional") ) {
                sleep(1000);
                inputTextUsingJS(CollateralInfoPage.optiCollatType, optiCollat);
            }

            inputTextUsingJS(CollateralInfoPage.collatValuationType, collatValuationType);

            if (!collatAppraiserEvaluator.isEmpty()) {
                inputTextUsingJS(CollateralInfoPage.collatAppraiserEvaluator, collatAppraiserEvaluator);
            }else{
                click(CollateralInfoPage.collatAppraiserEvaluator);
                sleep(500);
                Actions action = new Actions(this.driver());
                action.sendKeys(Keys.ARROW_DOWN).build().perform();
                sleep(500);
                action.sendKeys(Keys.ENTER).build().perform();
                sleep(500);

            }
            inputTextUsingJS(CollateralInfoPage.ValuationSubType, "UAAR");
            if (isWebElementVisible(CollateralInfoPage.ValueSource)) {
                inputTextUsingJS(CollateralInfoPage.ValueSource, "Combination");
            }
            inputText(CollateralInfoPage.collatValuationDate, collatValuationDate);
            Reporter.info("Entering  Collateral value fields");
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Entering  Collateral value fields"+ "\n"+ e.getMessage());
        }
    }
    //Enters Collateral Location
    public void EnterCollateralLocation(int i){
        String collatAddrType           =multiValueMap.get("Collateral Address Type").get(i);
        String collatAddr1              =multiValueMap.get("Collateral Address").get(i);
        String collatCity               =multiValueMap.get("Collateral City").get(i);
        String collatState              =multiValueMap.get("Collateral State").get(i);
        String collatZip                =multiValueMap.get("Collateral Zip Code").get(i);
        String collatCounty             =multiValueMap.get("Collateral County").get(i);
        String latitude                 ="41.403380";
        String longitude                ="2.174030";
        String collatLegalDescription   =multiValueMap.get("Legal Description").get(i);
        try {
            if (!multiValueMap.get("Is this a Primary Collateral").get(i).equalsIgnoreCase("No") && !multiValueMap.get("Is this Real Estate Collateral").get(i).equalsIgnoreCase("No")) {
                inputTextUsingJS(CollateralInfoPage.collatAddressType, collatAddrType);

            }
            inputText(CollateralInfoPage.collatAddress1, collatAddr1);
            inputText(CollateralInfoPage.collatCity, collatCity);
            inputTextUsingJS(CollateralInfoPage.collatState, collatState);
            inputText(CollateralInfoPage.collatZipcode, collatZip);
            pressTabKey(CollateralInfoPage.collatZipcode);
            sleep(500);
            if (getElementAttribute(CollateralInfoPage.collatCounty, "title").isEmpty()) {
//                sleep(500);
//                click(CollateralInfoPage.collatCounty);
//                sleep(500);
//                clickGridBoxCellElement(collatCounty);
//                sleep(500);
                inputTextUsingJS(CollateralInfoPage.collatCounty, collatCounty);
                sleep(500);
                pressTabKey(CollateralInfoPage.collatCounty);
            }

            if (multiValueMap.get("Is this Real Estate Collateral").get(i).equalsIgnoreCase("Yes")) {
                inputText(CollateralInfoPage.collatLatitude, latitude);
                inputText(CollateralInfoPage.collatLongitude, longitude);
                inputText(CollateralInfoPage.collatLegalDesc, collatLegalDescription);
                Reporter.info("Entering Collateral Location details for Collateral# "+(i+1));
            }
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Entering Collateral Location details "+ "\n"+ e.getMessage());
        }
    }
    //Selects Is this Collateral Poolable Yes/No RB
    // PB
    public void isThisCollateralPoolable(String yesNo){
        try {
            if (yesNo.equalsIgnoreCase("Yes")) {
                CheckRadioButton(CollateralInfoPage.isThisCollateralPoolableYes);

            } else {
                CheckRadioButton(CollateralInfoPage.isThisCollateralPoolableYNo);
            }
            Reporter.info("Selecting Is this Collateral Poolable RB as "+ yesNo);
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Selecting Is this Collateral Poolable RB as "+ yesNo + "\n"+ e.getMessage());
        }
    }
    //Enters Collateral Location for NRE
    //PB
    public void EnterNRECollateralLocation (String collatState, String collatZip, String collatCounty) {
        try {
            inputTextUsingJS(CollateralInfoPage.collatState, collatState);

            inputText(CollateralInfoPage.collatZipcode, collatZip);

            inputTextUsingJS(CollateralInfoPage.collatCounty, collatCounty);
            Reporter.info("Entering Collateral Location details for NRE collateral");
        }catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Entering Collateral Location details for NRE collateral"+ "\n"+ e.getMessage());
        }

    }

    //Request Flood report RB
    public void RequestFloodReportRB(int i){
        String YesNo = multiValueMap.get("Flood Report").get(i);
        try {
            if (YesNo.equalsIgnoreCase("Yes")) {
                CheckRadioButton(CollateralInfoPage.collatFloodReportYes);
            } else {
                CheckRadioButton(CollateralInfoPage.collatFloodReportNo);
            }
            Reporter.info("Checking Request Flood Report as "+ YesNo);
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Checking Request Flood Report as "+ YesNo+ "\n"+ e.getMessage());
        }
    }
    //Enter Acreage Information
    public void EnterAcreageInfo(int i){
        String totalLandVal = multiValueMap.get("Collateral Market Value").get(i);
        try {
            if (multiValueMap.get("Environmental Agreement").get(i).equalsIgnoreCase("Yes")) {
                CheckRadioButton(CollateralInfoPage.collatEnvironmentalAggrementYes);
            }
            inputText(CollateralInfoPage.collatTotalLandValue, totalLandVal);
            inputText(CollateralInfoPage.collatTotalDeedAcres, "340");
            inputText(CollateralInfoPage.collatTimberAcres, "56");
            inputText(CollateralInfoPage.collatCultivatedAcres, "75");
            inputText(CollateralInfoPage.collatIrrigatedAcres, "124");
            CheckRadioButton(CollateralInfoPage.ImprovementsLandNo);

            if (isWebElementVisible(CollateralInfoPage.ProceedsUsedToPurchaseNo)) {
                CheckRadioButton(CollateralInfoPage.ProceedsUsedToPurchaseNo);
            }
            if (isWebElementVisible(CollateralInfoPage.collatHomestedNo)) {
                CheckRadioButton(CollateralInfoPage.collatHomestedNo);
            }
            inputText(CollateralInfoPage.collatGF, "GF-"+GetRandomStringWithNumbers(7));
            Reporter.info("Entering Acreage Information for Collateral# "+(i+1));
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Entering Acreage Information"+ "\n"+ e.getMessage());
        }
    }

    public void EnterOwnershipRecordingInfo(int i){
        try {
            CheckRadioButton(CollateralInfoPage.collatRentedLeasedNo);
            CheckRadioButton(CollateralInfoPage.collatOwnerOccupiedNo);
            CheckRadioButton(CollateralInfoPage.collatRecordingInfoNo);
            if (isWebElementVisible(CollateralInfoPage.IndexingInstructions)) {
                inputText(CollateralInfoPage.IndexingInstructions, "Automated Test");
            }

            Reporter.info("Entering Ownership and Recording Information for Collateral# "+(i+1));
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Entering Ownership and Recording Information for Collateral# "+(i+1)+ "\n"+ e.getMessage());
        }
    }

    public void EnterInsuranceUCCInfo(int i){
        try {
            CheckRadioButton(CollateralInfoPage.InsuranceRequiredNo);
            CheckRadioButton(CollateralInfoPage.EnterUCCInformationNo);
            Reporter.info("Entering Insurance and UCC Information for Collateral# "+(i+1));
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail("Entering Insurance and UCC Information Information for Collateral# "+(i+1)+ "\n"+ e.getMessage());
        }
    }

    public void EnterCollateralPool(int i){
        try {
            String PoolType     =multiValueMap.get("Collateral Pool-Collateral Type").get(i);
            String PoolDesc     =multiValueMap.get("Collateral Pool-Collateral Description").get(i);
            String NumOfUnits   =multiValueMap.get("Collateral Pool-# of Units").get(i);
            String UnitPrice    =multiValueMap.get("Collateral Pool-Unit Price").get(i);

            sleep(500);
            click(addBtn);
            waitForInVisibile(PleaseWait);
            inputText(CollateralInfoPage.collatPoolType, PoolType);
            inputText(CollateralInfoPage.collatPoolDesc, PoolDesc);
            inputText(CollateralInfoPage.collatPoolNumOfUnits, NumOfUnits);
            inputText(CollateralInfoPage.collatPoolUnitPrice, UnitPrice);
            sleep(500);
            click(saveBtn);
            waitForInVisibile(PleaseWait);
            Reporter.info("Entering Collateral Pool details for Collateral# "+(i+1));
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }

    }

}
