package com.cme.pagemethods;

import com.cme.pages.DDTPage;
import com.fcbt.taf.core.reporting.Reporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class DDTPageMethods extends CmeBasePage{
    //Assigns DDT items to bank official
    public void AssignDDTItems(String assigneeTitle){
        List<WebElement> assignToElms = this.driver().findElements(DDTPage.DDTGridAssignedToColumn.getBy());
        for(WebElement el : assignToElms) {
            int loopCount = 0;
            while (el.getText().isEmpty()) {
                if (loopCount == 3) {
                    break;
                }
                sleep(500);
                el.click();
                sleep(500);
                inputTextUsingJS(DDTPage.AssignedToDD, assigneeTitle); //Selecting official in Assigned To drop down
                sleep(500);
                pressTabKey(DDTPage.AssignedToDD);
                sleep(500);
                loopCount++;
            }
        }
        Reporter.info("Assigning DDT items to bank official "+ assigneeTitle);
    }
    //Changes status for DDT items
    public void ChangeDDTItemStatus(String sts){
        List<WebElement> stsElms = this.driver().findElements(DDTPage.DDTGridStatusColumn.getBy());
        List<WebElement> StatusDDElms = this.driver().findElements(DDTPage.StatusDD.getBy());
        WebElement StatusDD = StatusDDElms.get(1);
        for(WebElement el : stsElms) {
            int loopCount = 0;
            while (!el.getText().equalsIgnoreCase(sts)) {
                if (loopCount == 5) {
                    break;
                }
                sleep(500);
                el.click();
                sleep(500);
                ((JavascriptExecutor)this.driver()).executeScript("arguments[0].value = arguments[1]", StatusDD, sts);
                sleep(200);
                Actions action = new Actions(this.driver());
                action.doubleClick(StatusDD);
                action.build().perform();
                sleep(500);
                loopCount++;
                sleep(1000);


            }
        }
        Reporter.info("Status for DDT Items changed to "+ sts);
    }
}
