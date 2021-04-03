package com.sample.pages;

import org.openqa.selenium.By;

public class CustomerSearchPage {

	public static By custSearchSrn = By.xpath("//a[@fieldname='Customer Search']");
	public static By nameSearchFld = By.xpath("//div[@fieldname='WRK_NAME_SEARCH']/input");
	public static By searchBtn = By.xpath("//a[@fieldname='Search']");
	public static By newBtn = By.xpath("//a[@fieldname='New']");
	public static By continueBtn = By.xpath("//a[@fieldname='Continue']");
	public static By PleaseWait = By.xpath("//div[@fieldname='Please Wait...']");
		
}
