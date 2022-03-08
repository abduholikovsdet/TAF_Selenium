package com.cme.pages;
import com.fcbt.taf.core.driver.ui.Locator;


public class AlternateAddressPage {

	public static Locator EntityAlternateAddress = Locator.byXpath("//a[@fieldname='Entity Alternate Address']");
	public static Locator altAddLastEditUN = Locator.byXpath("//div[@fieldname='use_last_initial']/input");
	public static Locator altAddEntName = Locator.byXpath("//div[@fieldname='ent_name']/input");
	public static Locator altAddDescp = Locator.byXpath("//div[@fieldname='adr_name']/input");
	public static Locator altAddAdd1 = Locator.byXpath("//div[@fieldname='adr_addr1']/input");
	public static Locator altAddAdd2 = Locator.byXpath("//div[@fieldname='adr_addr_sup']/input");
	public static Locator altAddCity = Locator.byXpath("//div[@fieldname='adr_city']/input");
	public static Locator altAddForiegnSt = Locator.byXpath("//div[@fieldname='configField51563']/input");
	public static Locator altAddPostalCode = Locator.byXpath("//div[@fieldname='adr_postal_code']/input");
	public static Locator altAddStDt = Locator.byXpath("//div[@fieldname='adr_start_date']/input");
	public static Locator altAddStpDt = Locator.byXpath("//div[@fieldname='adr_stop_date']/input");
	public static Locator altAddLIQFltr = Locator.byXpath("//div[@fieldname='configField52089']/input");
	public static Locator altAddZipCode = Locator.byXpath("//div[@fieldname='adr_zip']/input");
	public static Locator altAddCustNum = Locator.byXpath("//div[@fieldname='adr_obgr_num']/input");
	public static Locator altAddBankNum = Locator.byXpath("//div[@fieldname='adr_bank_num']/input");
	public static Locator altAddLastEditDt = Locator.byXpath("//div[@fieldname='use_last_edit_dt']/input");
	
	//DropDowns
	public static Locator altAddWhichLoan = Locator.byXpath("//div[@fieldname='loan_id']/input");
	public static Locator altAddtype = Locator.byXpath("//div[@fieldname='ALT_ADR_TYPE']/input");
	public static Locator altAddState = Locator.byXpath("//div[@fieldname='adr_state']/input");
	public static Locator altAddCountry = Locator.byXpath("//div[@fieldname='ADR_COUNTRY_NAME']/input");
	public static Locator altAddCounty = Locator.byXpath("//div[@fieldname='adr_county']/input");
	public static Locator altAddMiltryCity = Locator.byXpath("//div[@fieldname='configField51560']/input");
	public static Locator altAddMiltryPostSrv = Locator.byXpath("//div[@fieldname='ADR_MILITARY_PSC_DESC']/input");
	public static Locator altAddMiltryState = Locator.byXpath("//div[@fieldname='configField51562']/input");
	public static Locator altAddLIQLtn = Locator.byXpath("//div[@fieldname='ADR_LIQ_LOCATION_CODE_DESCRIPTION']/input");
	
	//RadioButtons
	public static Locator altAddTypeForeign = Locator.byXpath("//a[@fieldname='configField51557_FOR']");
	public static Locator altAddTypeMilitary = Locator.byXpath("//a[@fieldname='configField51557_MIL']");
	public static Locator altAddTypeDomestic = Locator.byXpath("//a[@fieldname='configField51557_DOM']");

	
	
	
}
