package com.cme.pagemethods;

import com.cme.pages.CovenantsPage;

public class CovenantPageMethods extends CmeBasePage{
    /**
     * Author: Muzaffar A.
     * Description: Adds Covenants
     */
    public void AddCovenants(int i){

        if (!ExistingCustomerFlag) {
            String covenants = multiValueMap.get("Covenants").get(i);
            if (!covenants.isEmpty()) {
                click(addBtn);
                waitForVisible(CovenantsPage.selectCovenantsWndw);
                String[] arrCovs = covenants.split(",");
                //Selecting covenants in Select Covenants window
                for (String cov : arrCovs) {
                    sleep(500);
                    clickGridBoxCellElement(cov);
                }
                sleep(500);
                click(OKBtn);
                sleep(2000);
                waitForInVisibile(PleaseWait);
                //relating added covenants to Facility and collateral
                for (String cov : arrCovs) {
                    sleep(1000);
                    clickGridBoxCellElement(cov);
                    sleep(2000);
                    click(CovenantsPage.ProductsDDBtn);
                    sleep(1000);
                    pressSpaceKey(CovenantsPage.covenantProductsDrpDwn);
                    pressTabKey(CovenantsPage.covenantProductsDrpDwn);
                    sleep(1000);
                    click(CovenantsPage.CollateralsDDBtn);
                    sleep(1000);
                    pressSpaceKey(CovenantsPage.covenantCollateralDrpDwn);
                    pressTabKey(CovenantsPage.covenantCollateralDrpDwn);
                    //Enter Monitored info when covenant is Financial Information
                    if (cov.equalsIgnoreCase("Financial Information")) {
                        sleep(500);
                        clickGridBoxCellElement(cov);
                        sleep(500);
                        click(CovenantsPage.covenantMonitoredChkBx);
                        inputTextUsingJS(CovenantsPage.AccountingPeriodType, "Fiscal");
                        CheckRadioButton(CovenantsPage.AnnualFinancialReporting);
                        sleep(500);
                        click(CovenantsPage.MonitoredTab);
                        inputText(CovenantsPage.covenantFirstDueDate, "12/01/2021");
                        inputText(CovenantsPage.covenantGracePeriod, "120");
                        inputTextUsingJS(CovenantsPage.covenantFrequency, "Annually");

                    }

                }
            }
        }
    }

}
