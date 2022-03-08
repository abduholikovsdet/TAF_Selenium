package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;

public class RoutePage {

	public static Locator routeScreen = Locator.byXpath("//a[@fieldname='Route']");
	public static Locator routeDealComplexity = Locator.byXpath("//div[@fieldname='app_loan_complex_desc']/input");
	public static Locator routeProducts = Locator.byXpath("//div[@fieldname='WRK_DPRODS']/textarea");
	
	//Previous Activity Section
	
	public static Locator routeCurrentActivity = Locator.byXpath("//div[@fieldname='activity_desc']/input");
	public static Locator routeActivityCompletedYes = Locator.byXpath("//a[@fieldname='PHASECOMPL_Y']");
	public static Locator routeActivityCompletedNo = Locator.byXpath("//a[@fieldname='PHASECOMPL_N']");
	public static Locator routePrevDisposition = Locator.byXpath("//div[@fieldname='DISPOSITION']/input");
	public static Locator routeAssignedUser = Locator.byXpath("//div[@fieldname='PREVUSER']/input");
	public static Locator routeExpectedDueDate = Locator.byXpath("//div[@fieldname='queue_due_dt']/input");
	public static Locator routeTime = Locator.byXpath("//div[@fieldname='queue_due_time']/input");
	public static Locator routecurrentActivityNote = Locator.byXpath("//div[@fieldname='queue_note']/textarea");
	
	//New activity section
	
	public static Locator routeRouteToNewActivityYes = Locator.byXpath("//a[@fieldname='NEWPHASE_Y']");
	public static Locator routeRouteToNewActivityNo = Locator.byXpath("//a[@fieldname='NEWPHASE_N']");
	public static Locator routeNewActivity = Locator.byXpath("//div[@fieldname='NEXTPHASE']/input");
	public static Locator routeDeposition = Locator.byXpath("//div[@fieldname='NEXTDISPOSITION']/input");
	public static Locator routeProcessCenter = Locator.byXpath("//div[@fieldname='NEXTPROCCTR']/input");
	public static Locator routeUser = Locator.byXpath("//div[@fieldname='NEXTUSER']/input");
	public static Locator routeDefaultNoteChkBx = Locator.byXpath("//a[@fieldname='DEFAULT_NOTE']/div");
	public static Locator routeNewactivityNote = Locator.byXpath("//div[@fieldname='NEXT_NOTE']/textarea");	
	
	//Buttons
	
	public static Locator routeFinishBtn = Locator.byXpath("//a[@fieldname='Finish']");
	public static Locator routeBackBtn = Locator.byXpath("//a[@fieldname='Back']");
	public static Locator routeContinueBtn = Locator.byXpath("//a[@fieldname='Continue']");
}
