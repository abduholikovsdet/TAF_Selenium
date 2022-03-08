package com.cme.pagemethods;

import com.cme.pages.DocumentsPage;
import com.fcbt.taf.core.reporting.Reporter;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DocumentsPageMethods extends CmeBasePage{
    /**
     * Author: Muzaffar A.
     * Description: Verifies CSi documents against given CSi test data
     */
    public void VerifyCsiDocuments(){

        List<String> CSiDocsValues  =new ArrayList<String>();
        int count                   =0;
        String docTypeMode          =getElementAttribute(DocumentsPage.DocumentsInView, "title").trim();

        List<WebElement> ents = this.driver().findElements(EntitiesTreeRow.getBy());
        List<String> EntNames = new ArrayList<String>();
//        Getting entity names from deal tree
        for (int i = 0; i < ents.size(); i++) {
            String[] splArr = ents.get(i).getText().split(" Info");
            EntNames.add(i, splArr[0]);
        }

        if (docTypeMode.equalsIgnoreCase("Pre-Closing")) {
            CSiDocsValues.add(0, "Approved Action Summary");
            for(int f=0; f<numOfLoans; f++){
                CSiDocsValues.add(CSiDocsValues.size(), "Approval Notice");
                CSiDocsValues.add(CSiDocsValues.size(), "Farm Credit Application Disclosure");
                for(int e=0; e<numOfEnts; e++){
                    if (multiValueMap.get("Relationship Type").get(e).toLowerCase().contains("borrower")) {
                        CSiDocsValues.add(CSiDocsValues.size(), "Borrowers Certification And Authorization-"+ multiValueMap.get("Full Name").get(e));
                    }
                }
            }
            for(int c=0; c<numOfCollaterals; c++){
                if (multiValueMap.get("Is this Real Estate Collateral").get(c).equalsIgnoreCase("Yes")) {
                    CSiDocsValues.add(CSiDocsValues.size(), "Environmental Questionnaire");
                }
            }

        } else if (docTypeMode.equalsIgnoreCase("Closing")){
            CSiDocsValues.add(0, "Business Loan Agreement");
            for(int f=0; f<numOfLoans; f++){
                CSiDocsValues.add(CSiDocsValues.size(), "Commercial Promissory Note");
                CSiDocsValues.add(CSiDocsValues.size(), "Effective Int Rate Disclosure");
                CSiDocsValues.add(CSiDocsValues.size(), "Error And Omissions Compliance Agreement");
                CSiDocsValues.add(CSiDocsValues.size(), "Farm Credit Application Disclosure");
                CSiDocsValues.add(CSiDocsValues.size(), "Loan Closing Statement and Receipt for Stock");
                CSiDocsValues.add(CSiDocsValues.size(), "Receipt for Copies");
                CSiDocsValues.add(CSiDocsValues.size(), "Loan Application");
                if (bankState.equalsIgnoreCase("TX")) {
                    double decAmt = Double.parseDouble(multiValueMap.get("Decision Amount").get(f).replace(",", ""));
                    if (decAmt > 50000.00) {
                        CSiDocsValues.add(CSiDocsValues.size(), "Oral Agreement Disclaimer");
                    }
                }
                if (multiValueMap.get("Pricing Type").get(f).toUpperCase().contains("FCSI")) {
                    CSiDocsValues.add(CSiDocsValues.size(), "FCSI Disclosure");
                }
                for(int e=0; e<numOfEnts; e++){
                    CSiDocsValues.add(CSiDocsValues.size(), "Request For TIN And Certification W 9-"+ multiValueMap.get("Full Name").get(e));
                    if (multiValueMap.get("Relationship Type").get(e).equalsIgnoreCase("Guarantor")) {
                        CSiDocsValues.add(CSiDocsValues.size(), "Guaranty-"+ multiValueMap.get("Full Name").get(e));
                        System.out.println("Guaranty added");
                    }
                }
            }
            if (bankNumber.equalsIgnoreCase("019")) {
                CSiDocsValues.add(CSiDocsValues.size(), "Effective Financing Statement-EFS-1-"+ multiValueMap.get("Full Name").get(0));
            }else if (bankNumber.equalsIgnoreCase("017")) {
                CSiDocsValues.add(CSiDocsValues.size(), "Attorney Title Certificate Letter - MS");
            }
            CSiDocsValues.add(CSiDocsValues.size(), "Loan Closing Instructions");



            CSiDocsValues.add(CSiDocsValues.size(), "Voter Designation");

            for(int c=0; c<numOfCollaterals; c++){
                if (multiValueMap.get("Is this Real Estate Collateral").get(c).equalsIgnoreCase("Yes")) {
                    CSiDocsValues.add(CSiDocsValues.size(), "Attorney Title Letter - "+ bankState);
                    if (multiValueMap.get("Owners of the Collateral").get(c).contains("1")) {
                        CSiDocsValues.add(CSiDocsValues.size(), "Commercial Real Estate Security Instrument-"+ multiValueMap.get("Full Name").get(0));
                        if (multiValueMap.get("Environmental Agreement").get(c).equalsIgnoreCase("Yes")) {
                            CSiDocsValues.add(CSiDocsValues.size(), "Environmental Agreement-"+ multiValueMap.get("Full Name").get(0));
                        }

                    }else{
                        CSiDocsValues.add(CSiDocsValues.size(), "Commercial Real Estate Security Instrument-");
                        CSiDocsValues.add(CSiDocsValues.size(), "Environmental Agreement-");
                    }

                }else {
                    CSiDocsValues.add(CSiDocsValues.size(), "Commercial Security Agreement-"+ multiValueMap.get("Full Name").get(0));

                }
                if (bankNumber.equalsIgnoreCase("019")) {
                   CSiDocsValues.add(CSiDocsValues.size(), "Collateral Schedule");
                }
            }

        }
//        System.out.println("CSi Docs: ");
//        for(String doc:CSiDocsValues){
//            System.out.println(doc);
//        }

        List<WebElement> DocNamesElms = this.driver().findElements(DocumentsPage.DocumentNameColumn.getBy());
        List<WebElement> RelatedToElms = this.driver().findElements(DocumentsPage.RelatedToColumn.getBy());
        int mismatchCount = 0;
        List<String> availableDocs = new ArrayList<String>();
        if (DocNamesElms.size() > 1) {
            for (int i = 0; i < DocNamesElms.size(); i++) {
                String docName;
                if (!RelatedToElms.get(i).getText().equalsIgnoreCase("-")) {
                    docName = DocNamesElms.get(i).getText() + "-" + RelatedToElms.get(i).getText();
                } else {
                    docName = DocNamesElms.get(i).getText();
                }
                availableDocs.add(docName);
                if (!CSiDocsValues.contains(docName)) {
                    Reporter.fail(docName + " Not expected");
                    mismatchCount++;
                }
            }
            for( String expectedDoc: CSiDocsValues) {
                if (!availableDocs.contains(expectedDoc)) {
                    Reporter.fail(expectedDoc + " is Missing in Documents grid");
                    mismatchCount++;
                }
            }

            if (mismatchCount==0) {
               Reporter.pass("All Expected CSi documents appear in the Documents grid");
            }
            sleep(1000);
            click(DocumentsPage.DocNumColChkBx);
            sleep(500);
            CheckRadioButton(DocumentsPage.FinalRB);
            sleep(500);
            click(DocumentsPage.GenerateDocsBtn);
            start = System.currentTimeMillis();
            waitForClickable(clickHereToViewDocument);
            finish = System.currentTimeMillis();
            timeElapsed = (finish - start)/1000;
            PerfMetrics.add(new String[] { Activity, "Generate CSi Documents", String.valueOf(timeElapsed) });
            Reporter.info("Generating CSi documents");
            if (isElementExist(clickHereToViewDocument, 120)) {
                click(clickHereToViewDocument);
            } else {
                Reporter.fail("Document is Ready");
                Assert.fail();
            }
            SwitchWindow(1);
            VerifyPDF();
            this.driver().close();
            SwitchWindow(0);
            sleep(1000);
            SwitchToCMEFrame();
            sleep(500);
            clickJS(DoneBtn);
        } else {
            Reporter.fail("CSi Documents are missing in Documents grid");
            Assert.fail("CSi Documents are missing in Documents grid");
        }
    }
}
