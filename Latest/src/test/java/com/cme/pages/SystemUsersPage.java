package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;


public class SystemUsersPage {

	public static Locator MainProcessCenter = Locator.byXpath("//div[@fieldname='proc_num']/input");
	public static Locator ProcessCenters = Locator.byXpath("//div[@fieldname='emp_proc_nums']/input");
	public static Locator Affiliates = Locator.byXpath("//div[@fieldname='emp_affiliate_nums']/input");
	public static Locator PrmAffiliate = Locator.byXpath("//div[@fieldname='emp_lgl_book_num']/input");
	public static Locator ApprovalLevel = Locator.byXpath("//div[@fieldname='appr_level']/input");
	public static Locator NetworkLogonID = Locator.byXpath("//div[@fieldname='emp_login_nm']/input");
	public static Locator SecurityGrp = Locator.byXpath("//div[@fieldname='emp_sec_grp_desc']/input");
	public static Locator ProcessGrp = Locator.byXpath("//div[@fieldname='emp_proc_grp_desc']/input");


}
