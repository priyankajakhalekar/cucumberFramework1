package com.vtiger.stepdefinations;

import com.vtiger.pages.HomePage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepdefination extends BaseStapDefinitions {
	  HomePage hp;
	  LoginPage lp;
	
	
	@Before
	public void before(Scenario scenario) {
	    this.scenario = scenario;
	}
	@After
	public void after()
	{
		extent.flush();
		driver.quit();
	}
	
	@Given("user should be on login page")
	public void user_should_be_on_login_page()
	{
		
		init();
		logger=extent.createTest(scenario.getName());
	  TCName=scenario.getName();
	 lp=new LoginPage(driver,logger);
	 hp=new HomePage(driver,logger);
	}
	@When("user enters invalid user id and password and click on login button")
	public void user_enters_invalid_user_id_and_password_and_click_on_login_button() 
	{
		lp.login(TestData.get(TCName).get("UserId"), TestData.get(TCName).get("Password"));
	}
	@Then("user can see the error message")
	public void user_can_see_the_error_message()
	{
		//driver.findElement(By.xpath("//*[contains(text(),'You must specify a valid username and password.')]")).isDisplayed();
		lp.VerifyErrorMsg();
	}


	@When("user enters invalid user id as {string} and password as {string} and click on login button")
	public void user_enters_invalid_user_id_as_ad1111_and_password_as_and_click_on_login_button(String userid,String password) {
		lp.login(userid, password);
	}


	
	@When("user enters valid userid and password and click on login button")
	public void user_enters_valid_userid_and_password_and_click_on_login_button()
	{
	   lp.login(TestData.get(TCName).get("UserId"), TestData.get(TCName).get("Password"));
	}
	@Then("user should be navigated to home page")
	public void user_should_be_navigated_to_home_page() 
	{
	   //driver.findElement(By.linkText("Logout")).isDisplayed();
	   hp.verifyLogout();
	}
	@Then("logout link should be appear on right corner on the home page")
	public void logout_link_should_be_appear_on_right_corner_on_the_home_page() 
	{
		//driver.findElement(By.linkText("Logout")).click();
		hp.ClickLogout();
	}

	
	

}
