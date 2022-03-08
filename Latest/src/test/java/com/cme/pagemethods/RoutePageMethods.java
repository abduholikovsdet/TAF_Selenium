package com.cme.pagemethods;

import com.cme.pages.RoutePage;
import com.fcbt.taf.core.reporting.Reporter;
import org.testng.Assert;

import java.io.FileWriter;

public class RoutePageMethods  extends CmeBasePage{
    //Routes deal to next activity
    public void RouteDeal(String newActivity, String userName){
        String newAct = getElementAttribute(RoutePage.routeNewActivity, "title");
        if (newAct.equalsIgnoreCase(newActivity)) {
            Reporter.pass("New Activity defaulted as "+newActivity);
        } else {
            if (newAct.equalsIgnoreCase("Pending - Pre-Closing") || newAct.equalsIgnoreCase("Pending - Post Closing - RE Certification")) {
                inputTextUsingJS(RoutePage.routeNewActivity, newActivity);
                sleep(1000);
            }else {
                Reporter.fail("New Activity defaulted as " + newAct);
                Assert.fail("New Activity defaulted as " + newAct);
            }
        }
        String procCntr = getElementAttribute(RoutePage.routeProcessCenter, "title");

        if (procCntr.equalsIgnoreCase(ProcessCenter)) {
            Reporter.pass("Process Center defaulted as "+ProcessCenter);
        } else {
            inputTextUsingJS(RoutePage.routeProcessCenter, ProcessCenter);
            Reporter.info("Selected Process Center as "+ProcessCenter);
        }
        inputTextUsingJS(RoutePage.routeUser, userName);
        Reporter.addScreenshot("Routing", "Routing");
        sleep(1000);
        click(finishBtn);
        start = System.currentTimeMillis();
        waitForInVisibile(PleaseWait);
        if (newActivity.equalsIgnoreCase("Closing - Fund/Book")) {
            newActivity = "Closing-FundBook";
        }
        try {
            if (!newActivity.equalsIgnoreCase("Complete")) {
                waitForVisible(cancelBtn);
                Reporter.addScreenshot("Routed to "+newActivity, "Routed to "+newActivity);
                sleep(500);
                clickJS(cancelBtn);
                finish = System.currentTimeMillis();
                timeElapsed = (finish - start)/1000;
                PerfMetrics.add(new String[] { Activity, "Routed to "+newActivity, String.valueOf(timeElapsed) });
                WritePerfMetricsToCSVFile();
            }
            Reporter.info("Routing the Application "+ dealAls +" to "+ newActivity);
        } catch (Exception e) {
            Reporter.info("Routing the deal to "+ newActivity+ e.getMessage());
            Assert.fail();
        }




    }
}
