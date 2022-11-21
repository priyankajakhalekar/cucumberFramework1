package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class HomePage extends HeaderPage {
	
	
//private WebDriver driver;
	
	public HomePage( WebDriver driver,ExtentTest logger)
	{
		super(driver,logger);
		//this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	@FindBy(name="pipeline")
	WebElement map_pipeline;
	
	public void verifypipelineMap()
	{
		 
		 cf.IsElementDisplay(map_pipeline,"Pipeline graph is displayed successfully");
	}
	

}
