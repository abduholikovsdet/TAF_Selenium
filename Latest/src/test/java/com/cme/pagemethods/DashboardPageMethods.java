package com.cme.pagemethods;

import com.cme.pages.EntityInfoPage;
import com.fcbt.taf.core.driver.ui.Locator;
import org.openqa.selenium.By;
import org.testng.Assert;
import com.cme.pages.CustomerSearchPage;
import com.cme.pages.DashboardPage;
import com.fcbt.taf.core.reporting.Reporter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DashboardPageMethods extends CmeBasePage {

	public void launchCMEInstance(String cmeEnvName, String usrAcct){
		String cmeURL;
		String cmeURL_LB = null;
		String cmeURL_NLB = null;
		try {
			switch(cmeEnvName) {
				case "FBLINTDEV":
					cmeURL_NLB	="fblcme-intdev.farmcreditbank.com/CME/";
					cmeURL_LB 	="fv-intdev.farmcreditbank.com";
					RqsURL 		="wholesalerates-inttst.farmcreditbank.com/";
					break;
				case "FBLRSTG":
					cmeURL_NLB	="fblrstgcmefe1.develop.fcbt:8080/CME/";
					cmeURL_LB 	="fblrstgrapi.develop.fcbt:3002";
					RqsURL 		="wholesalerates-inttst.farmcreditbank.com/";
					break;
				case "FBLINTTST":
					cmeURL_NLB	="fblcme-inttst.farmcreditbank.com/CME/";
					cmeURL_LB	="fv-inttst.farmcreditbank.com";
					RqsURL 		="wholesalerates-inttst.farmcreditbank.com/";
					break;
				case "FBLOBSDEV":
					cmeURL_NLB	="fblobsdevcmefe1.develop.fcbt:8080/CME/";
					cmeURL_LB	="fblobsdevuxp.develop.fcbt:3002/#/?workspace=CME&board=Onboarding";
					RqsURL 		="wholesalerates-inttst.farmcreditbank.com/";
					break;
				case "FBLDATO":
					cmeURL_NLB	="fblcme-dato.farmcreditbank.com/CME/";
					cmeURL_LB	="fv-dato.farmcreditbank.com";
					RqsURL 		="wholesalerates-dato.farmcreditbank.com/";
					break;
				case "FBLQA":
					cmeURL_NLB	="fblcme-qa.farmcreditbank.com/CME/";
					cmeURL_LB 	="fv-qa.farmcreditbank.com";
					RqsURL 		="wholesalerates-inttst.farmcreditbank.com/";
					break;
				case "FBLDATV":
					cmeURL_NLB	="fblcme-datv.farmcreditbank.com/CME/";
					cmeURL_LB 	="fv-datv.farmcreditbank.com";
					RqsURL 		="wholesalerates-datv.farmcreditbank.com/";
					break;
				case "FBLDATN":
					cmeURL_NLB	="fblcme-datn.farmcreditbank.com/CME/"; //invalid url
					cmeURL_LB 	="fbldatnuxp.develop.fcbt:3002";//fbldatnuxp.develop.fcbt:3002
					break;
				case "FBBUILD":
					cmeURL_NLB	="fblcme-build.farmcreditbank.com/CME/";
					cmeURL_LB 	="fv-build.farmcreditbank.com";
					RqsURL 		="wholesalerates-build.farmcreditbank.com/";
					break;
				case "FBLTRAIN":
					cmeURL_NLB	="fblcme-train.farmcreditbank.com/CME/";
					cmeURL_LB 	="fv-train.farmcreditbank.com";
					RqsURL 		="wholesalerates-trn.farmcreditbank.com/";
					break;
				case "FBLLEARN":
					cmeURL_NLB	="fblcme-learn.farmcreditbank.com/CME/";
					cmeURL_LB 	="fv-learn.farmcreditbank.com/#/?workspace=CME&board=Onboarding";
					RqsURL 		="wholesalerates-trn.farmcreditbank.com/";
					break;
				case "FBLASSN":
					cmeURL_NLB	="fblassncmefe1.nterprise.net:8080/CME/";
					cmeURL_LB 	="fblassnuxp.nterprise.net:3002/#/?workspace=CME&board=Onboarding";
					RqsURL = "wholesalerates-assn.farmcreditbank.com/";
					break;
				case "FBLDATI":
					cmeURL_NLB	="fblcme-dati.farmcreditbank.com/CME/";
					cmeURL_LB 	="fv-dati.farmcreditbank.com";
					RqsURL = "wholesalerates-dati.farmcreditbank.com/";
					break;
				case "FBLDEV":
					cmeURL_NLB	="fblcme-dev.farmcreditbank.com/CME/";
					cmeURL_LB 	="fv-dev.farmcreditbank.com";
				case "FBLDATF":
					cmeURL_NLB	="fbldatfcmefe1.develop.fcbt:8080/CME/";
					cmeURL_LB 	="fv-datf.farmcreditbank.com";
					break;
				case "FBLDATJ":
					cmeURL_NLB	="fbldatjcmefe1.develop.fcbt:8080/CME/";
					cmeURL_LB 	="fbldatjuxp.develop.fcbt:3002/#/?workspace=CME&board=Onboarding";
					break;
				case "FBLSDK2":
					cmeURL_NLB	="fblcme-sdk2.farmcreditbank.com/CME/";
					cmeURL_LB 	="fblSDK2rapi.develop.fcbt:3002";
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + cmeEnvName);
			}

			UserAccount		=usrAcct;
			UserAccountPwd	=GetPassword(usrAcct);
			String cmeURL1 	="http://NTERPRISE%5C"+ UserAccount +":"+ UserAccountPwd +"@" + cmeURL_NLB;
			String cmeURL2	="http://NTERPRISE%5C"+ UserAccount +":"+ UserAccountPwd +"@" + cmeURL_LB;
			if (!cmeEnvName.equalsIgnoreCase("FBLDATI")) {
				RqsURL 			="https://NTERPRISE%5C"+ UserAccount + ":" + UserAccountPwd+ "@" + RqsURL;
			}else {
				RqsURL 			="https://DEVELOP%5C"+ UserAccount + ":" + UserAccountPwd+ "@" + RqsURL;
			}

			if (UserAccountPwd.contains("%40")) {
				UserAccountPwd	= UserAccountPwd.replace("%40", "@");
			}
			Reporter.info("User Launches CME URL: " + "http://" + cmeURL_LB + " with User Account: "+ usrAcct);
			this.driver().manage().deleteAllCookies();
			navigateToUrl(cmeURL1);
			waitForPageLoaded();
			sleep(3000);
			navigateToUrl(cmeURL2);
			sleep(3000);

			try {
				if (isWebElementVisible(GetLocator("//h2[text()='Welcome to UXP']"))) {
					System.out.println("Welcome to UXP Page displayed");
	//				this.driver().switchTo().defaultContent();
					if (isWebElementVisible(CMESignOutIcon)) {
						clickJS(CMESignOutIcon);
						sleep(1000);
						clickJS(CMELogOutBtn);
						sleep(2000);
						this.driver().findElement(By.linkText("here")).click();
//						this.driver().manage().deleteAllCookies();
						navigateToUrl(cmeURL1);
						waitForPageLoaded();
						Thread.sleep(3000);
						navigateToUrl(cmeURL2);
						Thread.sleep(3000);
						waitForInVisibile(PleaseWait);
						this.driver().navigate().refresh();
						start = System.currentTimeMillis();

					}
				}
			} catch (InterruptedException e) {
				Assert.fail(e.getMessage());
			}
			SwitchToCMEFrame();
			Thread.sleep(1000);
			waitForInVisibile(PleaseWait);
			if (isElementExist(DashboardPage.dashboard, 60)) {
				waitForInVisibile(PleaseWait);
				Reporter.pass("CME Dashboard Loaded");
				GetReleaseNumber();
//				Reporter.addScreenshot("Launch CME", "Dashboard Loaded");

			} else {
				Assert.fail("CME Dashboard Loaded");
			}
		} catch (Throwable e) {
			Reporter.fail(e.getMessage());
			Assert.fail(e.getMessage());

		}
	}
	
	//Starts New Deal Creation wizard for new customer
	public void newDealCreation(String arg0, String bankNumber, String custName)
	{
		try {
			click(DashboardPage.tasks);	//Clicking Tasks from Menu Bar
			sleep(300);
			if (arg0.equalsIgnoreCase("New Deal Creation")) {
				click(DashboardPage.newDealCreation); //Clicking New Deal Creation under Tasks
			} else if (arg0.equalsIgnoreCase("AgFast Application")) {
				click(DashboardPage.AgFastApplication); //Clicking New Deal Creation under Tasks
			}
			start = System.currentTimeMillis();
			Reporter.info("Clicking "+ arg0+ " from Tasks menu");
			sleep(1000);
			waitForInVisibile(PleaseWait);
			VerifyScreenName(CustomerSearchPage.custSearchSrn);// Verifying Customer Search Screen
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "New Deal Creation - Customer Search Screen", String.valueOf(timeElapsed) });
			inputTextUsingJS(CustomerSearchPage.bankNumber, bankNumber); //Selecting Bank number
			clearInput(CustomerSearchPage.nameSearchFld);
			sleep(500);
			inputText(CustomerSearchPage.nameSearchFld, custName); //Entering Random Text
			sleep(1000);
			click(CustomerSearchPage.searchBtn); //Clicking Search Button
			sleep(1000);
			waitForInVisibile(PleaseWait);
			grdBxCellElem = Locator.byXpath("//div[@class='gridBoxCell' and text() = '"+custName + "']");
			if (isWebElementVisible(grdBxCellElem)) {
				click(CustomerSearchPage.continueBtn); //Clicking Continue Button
				System.out.println("Clicking Continue Button");
				ExistingCustomerFlag = true;
				waitForVisible(EntityActiveInOtherDeals);
				sleep(1000);
				click(yesBtn);
			} else {
				click(CustomerSearchPage.newBtn); //Clicking New Button
			}
			start = System.currentTimeMillis();
			waitForInVisibile(PleaseWait);
			Reporter.info("Clicking New button to add a new customer");
			VerifyScreenName(EntityInfoPage.entInfoSrn);
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start)/1000;
			PerfMetrics.add(new String[] { Activity, "Entity Info Screen", String.valueOf(timeElapsed) });

		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail("New Deal Creation");
		}
		
	}
	
	//Starts New Deal Creation wizard for existing customer
	public void newDealCreationExistingCustomer(String bankNumber, String custNumber) throws InterruptedException 
	{
		try {
			click(DashboardPage.tasks);	//Clicking Tasks from Menu Bar
			Thread.sleep(300);
			click(DashboardPage.newDealCreation); //Clicking New Deal Creation under Tasks
			Thread.sleep(1000);
			Reporter.info("Clicking New Deal Creation from Tasks menu");
			waitForInVisibile(DashboardPage.PleaseWait);
			waitForVisible(CustomerSearchPage.custSearchSrn);// Verifying Customer Search Screen
			Reporter.info("Customer Search Screen Displayed");
			inputTextUsingJS(CustomerSearchPage.bankNumber, bankNumber); //Selecting Bank number 
			inputText(CustomerSearchPage.nameSearchFld, custNumber); //Entering customer number of the customer
			Thread.sleep(1000);
			click(searchBtn); //Clicking Search Button
			Thread.sleep(1000);
			waitForInVisibile(PleaseWait);
			click(continueBtn); //Clicking Continue Button
			waitForInVisibile(PleaseWait);
			Reporter.info("Clicking Continue button to add a new customer");
		} catch (Exception e) {
			isWebElementVisible(DashboardPage.dashboard);
			Assert.fail("New Deal Creation");
		}
		
	}

	public void GetReleaseNumber(){
		String CmeRelVersion = null;
		String SqlRelVersion = null;
		String CmePrmVersion = null;
		String strEnv = dataMap.get("Environment");

		try {
			click(HelpMenu);
			sleep(500);
			click(AboutMenu);
			waitForVisible(ReleaseNumberWindw);
			sleep(500);
			CmeRelVersion = getElementText(GetLocator("//div[@fieldname='Credit Management Enterprise']/following-sibling::div[1]")).replace("Release ", "");
			SqlRelVersion = getElementText(GetLocator("//div[@fieldname='Credit Management Enterprise']/following-sibling::div[2]")).replace("SQL ", "");
			CmePrmVersion = getElementText(GetLocator("//div[@fieldname='Credit Management Enterprise']/following-sibling::div[3]")).replace("Promotion ", "");
			String[] strArr = {timeStampForPerf, strEnv, CmeRelVersion, SqlRelVersion, CmePrmVersion};
			Reporter.addScreenshot("Release Details", "Release Details");
			click(OKBtn);
			waitForInVisibile(PleaseWait);
			if (ReleaseNumberToGrafana) {
//				insert release details to grafana table
				String qry	="INSERT INTO [PERF_TEST_RESULTS].[dbo].[LoadMetrics] ([testID], [testEnvironment], [CMEReleaseVersion], [SQLReleaseVersion], [CMEPromoVersion])"
						.concat("VALUES ('" + String.join("','", strArr) +"')");
				try {
					String connectionUrl = "jdbc:sqlserver://QASTIMPRDFA04:1433;databaseName=PERF_TEST_RESULTS;user=SVCCMETESTUSER01;password=This1is2a3sqllogin"; //integratedSecurity=true";

					Connection con = DriverManager.getConnection(connectionUrl);
					PreparedStatement stmt = con.prepareStatement(qry);
					stmt.executeUpdate();
					con.close();
				} catch (SQLException e) {
					Reporter.fail(e.getMessage());
					Assert.fail(e.getMessage());
				}
				ReleaseNumberToGrafana = false;
			}

		} catch (Exception e) {
			Reporter.fail(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}
	
}
