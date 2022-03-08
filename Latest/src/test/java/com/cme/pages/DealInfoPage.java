package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;

public class DealInfoPage {
		
	public static Locator ApplicationSrn = Locator.byXpath("//a[@fieldname='Application']");
	// Input box
	public static Locator dealinfoLastEdit1 = Locator.byXpath("//div[@fieldname='use_last_edit_dt']/input");
	public static Locator dealinfoLastEdit2 = Locator.byXpath("//div[@fieldname='use_last_initial']/input");
	public static Locator dealinfoOppName = Locator.byXpath("//div[@fieldname='configField51528']/input");
	public static Locator dealinfoBranchDesc = Locator.byXpath("//div[@fieldname='APP_BRANCH_NUM1']/input");
	public static Locator dealinfoRelPD = Locator.byXpath("//div[@fieldname='configField53248']/input");
	public static Locator dealinfoAgFastScore = Locator.byXpath("//div[@fieldname='configField52149']/input");
	public static Locator dealinfoAgFastScoreDt = Locator.byXpath("//div[@fieldname='configField51695']/input");
	public static Locator dealinfoAppDt = Locator.byXpath("//div[@fieldname='app_start_dt']/input");
	public static Locator dealinfoTrgtCloseDt = Locator.byXpath("//div[@fieldname='app_target_closing_dt']/input");
	public static Locator dealinfoAppCompltDt = Locator.byXpath("//div[@fieldname='app_concur_recd_dt']/input");
	public static Locator dealinfoAppNum = Locator.byXpath("//div[@fieldname='app_application_num']/input");
	public static Locator dealinfoCDAType = Locator.byXpath("//div[@fieldname='configField50037']/input");
	public static Locator dealinfoIncmptReason = Locator.byXpath("//div[@fieldname='configField53643']/input");
	public static Locator dealinfoOutofDist = Locator.byXpath("//div[@fieldname='configField54596']/input");
	public static Locator dealinfoLUSExceptions = Locator.byXpath("//div[@fieldname='configField51658']/input");
	public static Locator dealinfoTitlInsurncAttorney = Locator.byXpath("//div[@fieldname='configField54571']/input");
	public static Locator dealinfoTitlInsurncUnderWrtr = Locator.byXpath("//div[@fieldname='configField54572']/input");
	public static Locator dealinfoCreditActionReq = Locator.byXpath("//div[@fieldname='configField51512']/textarea");
	public static Locator dealinfoDueDt = Locator.byXpath("//div[@fieldname='configField53644']/input");
	public static Locator dealinfoMailCode = Locator.byXpath("//div[@fieldname='emp_mail_code']/input");
	public static Locator dealinfoEmpPhone = Locator.byXpath("//div[@fieldname='emp_phone']/input");
	public static Locator dealinfoEmail = Locator.byXpath("//div[@fieldname='emp_email']/input");
	
	//Dropdowns
	public static Locator dealinfoAssociation = Locator.byXpath("//div[@fieldname='APP_BANK_X']/input");
	public static Locator dealinfoRegion = Locator.byXpath("//div[@fieldname='APP_ACCT_HIER2_X']/input");
	public static Locator dealinfoBranchNum = Locator.byXpath("//div[@fieldname='APP_BRANCH_NUM']/input");
	public static Locator dealinfoLUSType = Locator.byXpath("//div[@fieldname='configField51341']/input");
	public static Locator dealinfoTotalLoanAmt = Locator.byXpath("//div[@fieldname='configField57141']/input");
	public static Locator dealinfoDealComplx = Locator.byXpath("//div[@fieldname='app_loan_complex_desc']/input");
	public static Locator dealinfoGovState = Locator.byXpath("//div[@fieldname='app_gov_state_desc']/input");
	public static Locator dealinfoNotaryState = Locator.byXpath("//div[@fieldname='APP_STATE_DESC']/input");
	public static Locator dealinfoAccrualStatus = Locator.byXpath("//div[@fieldname='configField51502']/input");
	public static Locator dealinfoApprovalReq = Locator.byXpath("//div[@fieldname='configField51508']/input");
	public static Locator dealinfoOfficer = Locator.byXpath("//div[@fieldname='officer_num']/input");
	public static Locator dealinfoNMLSOfficer = Locator.byXpath("//div[@fieldname='APP_NMLS_OFFICER_NUM']/input");
	public static Locator dealinfoFinIndstry = Locator.byXpath("//div[@fieldname='APP_FIN_IND_DESC']/input");
	public static Locator dealinfoSubIndstry = Locator.byXpath("//div[@fieldname='APP_SUB_IND_DESC']/input");
	public static Locator dealinfoCBforAgfast = Locator.byXpath("//div[@fieldname='configField51633']/input");
	public static Locator dealinfoAgFastPurpose = Locator.byXpath("//div[@fieldname='configField51694']/input");
	public static Locator dealinfoCommitteelvl = Locator.byXpath("//div[@fieldname='configField51769']/input");
	public static Locator dealinfoIncmpltReasons = Locator.byXpath("//div[@fieldname='APP_INCOMPLETE_REASON_DESC']/input");
	public static Locator dealinfoContactName = Locator.byXpath("//div[@fieldname='emp_name']/input");
	public static Locator dealinfoRole = Locator.byXpath("//div[@fieldname='role_desc']/input");
	public static Locator dealinfoCreditAnalysislvl = Locator.byXpath("//div[@fieldname='configField52123']/input");
	public static Locator dealinfoRoleSearch = Locator.byXpath("//div[@fieldname='role_search']/input");
	public static Locator dealinfoDealTeamBranchNum = Locator.byXpath("//div[@fieldname='rel_emp_branch_num']/input");
	public static Locator dealinfoAssociations = Locator.byXpath("//div[@fieldname='configField53538']/input");
	public static Locator dealinfoUserSearch = Locator.byXpath("//div[@fieldname='user_search']/input");

	//RadioButtons
	public static Locator dealinfoTrtyCncrncReqYes = Locator.byXpath("//a[@fieldname='app_territorial_concur_yn_Y']");
	public static Locator dealinfoTrtyCncrncReqNo = Locator.byXpath("//a[@fieldname='app_territorial_concur_yn_N']");
	public static Locator dealinfoNotificationYes = Locator.byXpath("//a[@fieldname='configField53588_Y']");
	public static Locator dealinfoNotificationNo = Locator.byXpath("//a[@fieldname='configField53588_N']");
	public static Locator AgfastPlusYes = Locator.byXpath("//a[@fieldname='configField54801_Y']");
	public static Locator AgfastPlusNo = Locator.byXpath("//a[@fieldname='configField54801_N']");
	public static Locator dealinfoWithinHoldYes = Locator.byXpath("//a[@fieldname='configField51509_Y']");
	public static Locator dealinfoWithinHoldNo = Locator.byXpath("//a[@fieldname='configField51509_N']");
	public static Locator dealinfoWithinUnderWrtYes = Locator.byXpath("//a[@fieldname='configField51510_Y']");
	public static Locator dealinfoWithinUnderWrtNo = Locator.byXpath("//a[@fieldname='configField51510_N']");
	public static Locator dealinfoLoansComplianceYes = Locator.byXpath("//a[@fieldname='configField51511_Y']");
	public static Locator dealinfoLoansComplianceNo = Locator.byXpath("//a[@fieldname='configField51511_N']");
	public static Locator dealinfoNewRelationShip = Locator.byXpath("//a[@fieldname='configField51673_Y']");
	public static Locator dealinfoNewOprtLnYes = Locator.byXpath("//a[@fieldname='configField51675_Y']");
	public static Locator dealinfoNewOprtLnNo = Locator.byXpath("//a[@fieldname='configField51675_N']");
	public static Locator dealinfoAnnlRvwYes = Locator.byXpath("//a[@fieldname='configField51714_Y']");
	public static Locator dealinfoAnnlRvwNo = Locator.byXpath("//a[@fieldname='configField51714_N']");
	public static Locator dealinfoAnnlRvwNA = Locator.byXpath("//a[@fieldname='configField51714_X']");
	public static Locator dealinfoFCBTApvlReqYes = Locator.byXpath("//a[@fieldname='configField51712_Y']");
	public static Locator dealinfoFCBTApvlReqNo = Locator.byXpath("//a[@fieldname='configField51712_N']");
	public static Locator dealinfoCompltInfoYes = Locator.byXpath("//a[@fieldname='app_incomplete_yn_Y']");
	public static Locator dealinfoCompltInfoNo = Locator.byXpath("//a[@fieldname='app_incomplete_yn_N']");
	public static Locator dealinfoOutofDistYes = Locator.byXpath("//a[@fieldname='configField54595_Y']");
	public static Locator dealinfoOutofDistNo = Locator.byXpath("//a[@fieldname='configField54595_N']");
	public static Locator dealinfoSrchbyRole = Locator.byXpath("//a[@fieldname='app_search_by_R']");
	public static Locator dealinfoSrchbyContact = Locator.byXpath("//a[@fieldname='app_search_by_C']");
	public static Locator dealinfoRoleInternal = Locator.byXpath("//a[@fieldname='app_role_type_I']");
	public static Locator dealinfoRoleExternal = Locator.byXpath("//a[@fieldname='app_role_type_E']");

	//Buttons
	public static Locator dealinfoNewBtn =Locator.byXpath("//a[@fieldname='New']");
	public static Locator dealinfoRemove =Locator.byXpath("//a[@fieldname='Remove from List']");
	
	
}
