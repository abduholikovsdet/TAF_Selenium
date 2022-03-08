package com.cme.pagemethods;

import com.cme.pages.AuthorizationPage;
import com.cme.pages.CovenantsPage;
import com.cme.pages.DDTPage;
import com.fcbt.taf.core.reporting.Reporter;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AuthorizationPageMethods extends CmeBasePage{
    /**
     * Author: Muzaffar A.
     * Description: Enters Decision for Products
     */
    public void EnterProductDecision(){
        List<WebElement> prodElms = this.driver().findElements(AuthorizationPage.ProductColumn.getBy());
        for(WebElement el : prodElms) {
            sleep(500);
            el.click();
            sleep(500);
            inputTextUsingJS(AuthorizationPage.ProductDecisionDD, "Approved");
            sleep(500);
            Reporter.info("Entering Decision for Product "+ el.getText());
        }

    }

    /**
     * Author: Muzaffar A.
     * Description: Enters Deal Decision
     */
    public void EnterDealDecision(){
        sleep(1000);
        click(AuthorizationPage.NewDecisionBtn);
        AuthorizeLogin();
        waitForInVisibile(PleaseWait);
        inputTextUsingJS(AuthorizationPage.DealDecisionDD, "Approved");
        Reporter.info("Entering Deal Decision");
        if (bankNumber.equalsIgnoreCase("001") && !complexity.toLowerCase().contains("agfast")) {
            SelectApproversInAuthorization();
        }else if (bankNumber.equalsIgnoreCase("019")) {
            SelectApproversInAuthorization();
        }
    }
    /**
     * Author: Muzaffar A.
     * Description: User performs CDA complete
     */
    public void CDAComplete(){
        Reporter.info("Clicking CDA Complete");
        sleep(500);
        waitForInVisibile(PleaseWait);
        click(AuthorizationPage.CDACompleteBtn);
        start = System.currentTimeMillis();
        sleep(1000);
        waitForInVisibile(PleaseWait);
        waitForInVisibile(PleaseWait);
        finish = System.currentTimeMillis();
        timeElapsed = (finish - start)/1000;
        PerfMetrics.add(new String[] { Activity, "CDA Complete", String.valueOf(timeElapsed) });
        SwitchWindow(1);
        VerifyPDF();
        this.driver().close();
        SwitchWindow(0);
        SwitchToCMEFrame();

    }

}
