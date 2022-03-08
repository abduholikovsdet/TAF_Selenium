package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;


public class CovenantsPage {

	public static Locator covenantScreen = Locator.byXpath("//a[contains(text(),' Covenant Details')]");
	public static Locator MonitoredTab = Locator.byXpath("//a[@fieldname = 'Monitored']");
	public static Locator covenantLastEditDate = Locator.byXpath("//div[@fieldname='use_last_edit_dt']");
	public static Locator covenantLastEditUsername = Locator.byXpath("//div[@fieldname='use_last_initial']");
	public static Locator covenantGridBox = Locator.byXpath("//div[@fieldname='gbCovs']");
	public static Locator selectCovenantsWndw = Locator.byXpath("//div[@class='label' and contains(text(), 'Select Covenants')]");
	public static Locator ProductsDDBtn = Locator.byXpath("//div[@fieldname='CRW_PRODS']/div[2]/div");
	public static Locator CollateralsDDBtn = Locator.byXpath("//div[@fieldname='CRW_COLLS']/div[2]/div");
	public static Locator AccountingPeriodType = Locator.byXpath("//div[@fieldname='configField52157']/input");
	public static Locator AnnualFinancialReporting = Locator.byXpath("//a[@fieldname='crw_fin_rep_annual_yn_N']");

	//Covenant Details Section
	
	public static Locator covenantProductsDrpDwn = Locator.byXpath("//div[@fieldname='CRW_PRODS']/input");
	public static Locator covenantCollateralDrpDwn = Locator.byXpath("//div[@fieldname='CRW_COLLS']/input");
	public static Locator covenantTextarea = Locator.byXpath("//div[@fieldname='crwt_text']/textarea");	
	public static Locator covenantMonitoredChkBx = Locator.byXpath("//a[@fieldname='crw_monitored_ind']");
	public static Locator covenantSeasonalChkBx = Locator.byXpath("//a[@fieldname='crw_abl_seasonal_ind']");
	public static Locator covenantReportingPeriodDrpDwn = Locator.byXpath("//div[@fieldname='configField52154']/input");

	//Monitored Section
	
	public static Locator covenantFirstDueDate = Locator.byXpath("//div[@fieldname='crw_start_dt']/input");
	public static Locator covenantGracePeriod = Locator.byXpath("//div[@fieldname='crw_grace_period']/input");
	public static Locator covenantCovenantTarget = Locator.byXpath("//div[@fieldname='crw_target']/textarea");
	public static Locator covenantItemDescription = Locator.byXpath("//div[@fieldname='crw_item_desc']/input");
	public static Locator covenantFrequency = Locator.byXpath("//div[@fieldname='CRW_FREQ_DESC']/input");
	public static Locator covenantExpirationDate = Locator.byXpath("//div[@fieldname='crw_expiration_dt']/input");
	public static Locator covenantPrimaryOfficer = Locator.byXpath("//div[@fieldname='crw_officer']/input");
	public static Locator covenantBranch = Locator.byXpath("//div[@fieldname='CRW_BRANCH_NUM']/input");
	public static Locator covenantOfficer1 = Locator.byXpath("//div[@fieldname='CRW_OFFICER1_NUM']/input");
	public static Locator covenantPriorActual = Locator.byXpath("//div[@fieldname='crw_prior_act']/input");
	public static Locator covenantPriorStmtDate = Locator.byXpath("//div[@fieldname='crw_prior_stmt_dt']/input");
	public static Locator covenantCovenantActual = Locator.byXpath("//div[@fieldname='crw_cov_act']/input");
	public static Locator covenantStmtDate = Locator.byXpath("//div[@fieldname='crw_stmt_dt']/input");
	public static Locator covenantBreachedChkBx = Locator.byXpath("//a[@fieldname='crw_breached']");
	public static Locator covenantNonStandardChkBx = Locator.byXpath("//a[@fieldname='crw_non_standard']");
	public static Locator covenantModelName = Locator.byXpath("//div[@fieldname='crw_model_name']/input");
	public static Locator covenantAccount = Locator.byXpath("//div[@fieldname='crw_link_acct_desc']/input");
	public static Locator covenantBreachedWhen = Locator.byXpath("//div[@fieldname='crw_breached_when_desc']/input");
	public static Locator covenantBreachedValue = Locator.byXpath("//div[@fieldname='crw_breached_val']/input");
	public static Locator covenantNote = Locator.byXpath("//div[@fieldname='crw_cov_note']/textarea");

	//Buttons
	
	public static Locator covenantAddBtn = Locator.byXpath("//a[@fieldname='Add']");
	public static Locator covenantDeleteBtn = Locator.byXpath("//a[@fieldname='Delete']");
	public static Locator covenantRefreshBtn = Locator.byXpath("//a[@fieldname='Refresh']");
	public static Locator covenantContinueBtn = Locator.byXpath("//a[@fieldname='Continue']");
	public static Locator covenantSaveBtn = Locator.byXpath("//a[@fieldname='Save']");
	public static Locator covenantBackBtn = Locator.byXpath("//a[@fieldname='Back']");
	public static Locator covenantRelDocsBtn = Locator.byXpath("//a[@fieldname='Rel Docs']");
	public static Locator covenantEditBtn = Locator.byXpath("//a[@fieldname='Edit']");
	public static Locator covenantOKBtn = Locator.byXpath("//a[@fieldname='OK']");
}
