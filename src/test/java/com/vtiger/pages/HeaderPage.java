package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.common.CommonFunctions;

public class HeaderPage {
	
public WebDriver driver;
public CommonFunctions cf;
ExtentTest logger;
	
	public HeaderPage( WebDriver driver,ExtentTest logger)
	{
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);
		cf=new CommonFunctions(driver,logger);
	}
	
	@FindBy(linkText="Leads")
	WebElement lnk_leads;
	
	@FindBy(linkText="New Lead")
	WebElement lnk_newlead;
	
	@FindBy(linkText="Logout")
	WebElement lnk_logout;
	
	@FindBy(linkText="New Account")
	WebElement lnk_NewAccount;
	
	
	@FindBy(linkText="Accounts")
	WebElement lnk_Accounts;
	
	
	public void verifyLogout()
	{
		//return lnk_logout.isDisplayed();
		cf.IsElementDisplay(lnk_logout,"Logout link displayed successfully");
	}
	
	public void ClickLogout()
	{
		// lnk_logout.click();
		 cf.ClickElement(lnk_logout,"Logout link clicked successfully");
	}
	
	public void ClickNewLead()
	{
		// lnk_logout.click();
		 cf.ClickElement(lnk_newlead,"New Lead link clicked successfully");
	}
	
	public void ClickNewAccount()
	{
		// lnk_logout.click();
		 cf.ClickElement(lnk_NewAccount,"New Account link clicked successfully");
	}
	
}
