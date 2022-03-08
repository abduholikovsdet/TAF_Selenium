package com.cme.pagemethods;

import com.cme.pages.DDTPage;
import com.cme.pages.RelatedDocsPage;
import com.fcbt.taf.core.reporting.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

//import utilities.ExcelDataUtil;
//import utilities.GlobalUtil;
//import utilities.KeywordUtil;

public class RelatedDocsMethods extends CmeBasePage {
    boolean archBool = false; //used for loading time data
    public void AddDocumentECM(int i) {
        String path             =multiValueMap.get("File Path").get(i);
        String docDescription   =multiValueMap.get("Document Description").get(i);
        String docFolder        =multiValueMap.get("Document Folder").get(i);
        String docName          =multiValueMap.get("Document Name").get(i);
        int entityNum           =Integer.parseInt(multiValueMap.get("Associate Entity").get(i));
        int collatNum           =Integer.parseInt(multiValueMap.get("Associate Collateral").get(i));
        int prodctNum           =Integer.parseInt(multiValueMap.get("Associate Product").get(i));
        String entity           =multiValueMap.get("Legal Name").get(entityNum-1);

        Reporter.info("Uploading and Archiving "+ docDescription);
        try {
            LocalFileDetector detector = new LocalFileDetector();
            File file = detector.getLocalFile(path);
            String absPath = file.getAbsolutePath();
            sleep(1000);
            click(addBtn);
            waitForInVisibile(PleaseWait);
            SwitchToECM();
            RemoteWebElement input = (RemoteWebElement) this.driver().findElement(By.xpath("//input[@type='file']"));
            input.setFileDetector(detector);
            input.sendKeys(absPath);

            this.driver().switchTo().defaultContent();
            SwitchToCMEFrame();
            sleep(1000);
            click(RelatedDocsPage.UploadDescription);
            inputText(RelatedDocsPage.UploadDescription,docDescription);
            sleep(1000);
            click(OKBtn);
            waitForClickable(RelatedDocsPage.AssociateDocsDocumentFolder);
            inputTextUsingJS(RelatedDocsPage.AssociateDocsDocumentFolder,docFolder);
            inputTextUsingJS(RelatedDocsPage.AssociateDocsDocumentName,docName);
            inputTextUsingJS(RelatedDocsPage.AssociateDocsEntity, entity);
            click(RelatedDocsPage.AssociateDocsCollateral);
            sleep(1000);
            for (int j=0; j<collatNum; j++) {
                Actions action = new Actions(this.driver());
                action.sendKeys(Keys.DOWN).build().perform();
                sleep(500);
            }
            pressSpaceKey(RelatedDocsPage.AssociateDocsCollateral);
            sleep(500);
            pressTabKey(RelatedDocsPage.AssociateDocsCollateral);
//            sleep(500);
            click(RelatedDocsPage.AssociateDocsProduct);
            sleep(500);
            for (int j=0; j<prodctNum; j++) {
                Actions action = new Actions(this.driver());
                action.sendKeys(Keys.DOWN).build().perform();
                sleep(500);
            }
            pressSpaceKey(RelatedDocsPage.AssociateDocsProduct);
            sleep(500);
            pressTabKey(RelatedDocsPage.AssociateDocsProduct);
            click(RelatedDocsPage.AssociateDocContinueBtn);
            sleep(300);
            start = System.currentTimeMillis();
            waitForInVisibile(PleaseWait);
            if (i==0) {
                finish = System.currentTimeMillis();
                timeElapsed = (finish - start)/1000;
                PerfMetrics.add(new String[] { Activity, "ECM Upload Document", String.valueOf(timeElapsed) });
                archBool = true;
            }
            ArchiveDoc(docDescription);
        } catch (Exception e) {
            Reporter.fail(e.getMessage());
            Assert.fail(e.getMessage());
        }

    }

    public void ArchiveDoc(String docDescription) {
        List<WebElement> DescriptionElms = this.driver().findElements(RelatedDocsPage.GridDescriptionColumn.getBy());
        for (int i = 0; i < DescriptionElms.size(); i++) {
            if (DescriptionElms.get(i).getText().equalsIgnoreCase(docDescription)) {
                sleep(500);
                DescriptionElms.get(i).click();
                sleep(500);
                Actions action = new Actions(this.driver());
                action.sendKeys(Keys.SPACE).build().perform();
                sleep(500);
                click(RelatedDocsPage.ArchiveBtn);
                try {
                    waitForVisible(RelatedDocsPage.HostKeywordsWndw);
                    Reporter.addScreenshot(docDescription, docDescription);
                    sleep(500);
                    click(OKBtn);
                } catch (Exception e) {
                    Reporter.fail(e.getMessage());
                    Assert.fail();
                }
                start = System.currentTimeMillis();
                waitForInVisibile(PleaseWait);
                sleep(1000);
                List<WebElement> StatusElms = this.driver().findElements(RelatedDocsPage.GridStatusColumn.getBy());
                if (StatusElms.get(i).getText().equalsIgnoreCase("Archived")) {
                    Reporter.pass(docDescription+ " Archived");
                    if (archBool) {
                        finish = System.currentTimeMillis();
                        timeElapsed = (finish - start)/1000;
                        PerfMetrics.add(new String[] { Activity, "ECM Archive Document", String.valueOf(timeElapsed) });
                    }
                    archBool = false;

                } else {
                    Reporter.fail(docDescription+ " Archived");
                    Reporter.addScreenshot(docDescription,docDescription);
                }

            }
        }

    }
}