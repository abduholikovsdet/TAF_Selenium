package com.cme.pagemethods;

import com.cme.pages.BorrowerRiskTypesPage;
import com.fcbt.taf.core.driver.ui.Locator;
import com.fcbt.taf.core.reporting.Reporter;
import org.testng.Assert;

public class BorrowerRiskTypesMethods extends CmeBasePage{
    /**
     * Author:      Muzaffar A.
     * Description: Adds desired risk type to a given borrower. After adding validates added risk type in the last row for Risk Types column in the grid
     */
    public void AddRiskType(int i){
        String borrName;
        String risktype = "Loans";

        for (int j=0; j<numOfEnts; j++) {

            if (multiValueMap.get("Relationship Type").get(j).equalsIgnoreCase("Guarantor")) {
                continue;
            }
            borrName = multiValueMap.get("Legal Name").get(j);
            sleep(1000);
            click(AngAddBtn);
            waitForInVisibile(AngPleaseWait);
            clickJS(BorrowerRiskTypesPage.Borrower);
            sleep(500);
            clickAngularDDElement(borrName);
            clickJS(BorrowerRiskTypesPage.RiskTypes);
            sleep(500);
            clickAngularDDElement(risktype);
            sleep(500);
            clickJS(BorrowerRiskTypesPage.Float);
            sleep(500);
            clickJS(AngSaveBtn);
            waitForInVisibile(AngPleaseWait);
            String rowNum = GetNumberOfRowsOfTable(BorrowerRiskTypesPage.RiskTypesGrid);
            String RiskTypesColXpath = "//st-grid-box[@fieldname='Borrower_Risk_Type_Grid']/table/tbody/tr["+ rowNum +"]/td[count(//st-grid-box[@fieldname='Borrower_Risk_Type_Grid']/table/thead/tr/th[text()='Risk Type Code']/preceding-sibling::th)+1]";
            String BorrNameColXpath = "//st-grid-box[@fieldname='Borrower_Risk_Type_Grid']/table/tbody/tr["+ rowNum +"]/td[count(//st-grid-box[@fieldname='Borrower_Risk_Type_Grid']/table/thead/tr/th[text()='Borrower Name']/preceding-sibling::th)+1]";
            tempLocator =  Locator.byXpath(RiskTypesColXpath);
            String addedRiskTypeVal = getElementText(tempLocator);
            if (!addedRiskTypeVal.equalsIgnoreCase(risktype)) {
                Reporter.fail("Added Risk Type-"+ risktype +" for "+ borrName);
            }
            Reporter.info("Adding Risk Type for Facility#"+ (i+1));
        }

    }





}
