@Loginfeature
 Feature: Login functionality
 
 
 Background:
  Given user should be on login page
 
 Scenario Outline: TC01_InvalidLogin
 When user enters invalid user id as "<userid>" and password as "<password>" and click on login button
 Then user can see the error message
 Examples:
 | userid | password  |
 |ad1111  | pass123   |
 |ad2222  | pass222   |
 |ad333   | pass333   |
 
 Scenario: TC02_validLogin
 When user enters valid userid and password and click on login button
 Then user should be navigated to home page
 And logout link should be appear on right corner on the home page
 
 