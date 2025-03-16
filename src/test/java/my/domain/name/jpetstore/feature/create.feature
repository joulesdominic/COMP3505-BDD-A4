#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Test Pet Store Account Registration 
 
  Scenario Outline: Test valid Pet Store Account Registration
    Given I open Chrome browser
    	And I go to Pet Store Create an Account Page
    When I enter a valid "<userID>", "<password>", "<firstname>", "<lastname>", "<email>", "<phone>", "<address1>", "<city>", "<state>", "<zip>", and "<country>" combination and press Save Account Information
    Then My account is made with the appropriate information assigned to it

    Examples: 
      | userID| password    | firstname | lastname | email | phone | address1 | city | state | zip | country | 
			| user123 | Pass@123  | John      | Doe      | john.doe@example.com  | 4031234567  | 123 Main St    | Calgary | Alberta | T2P1B1 | Canada  |
			
	Scenario Outline: Test invalid Pet Store Account Registration 
    Given I open Chrome browser
    	And I go to Pet Store Create an Account Page
    When I enter an invalid "<userID>", "<password>", "<firstname>", "<lastname>", "<email>", "<phone>", "<address1>", "<city>", "<state>", "<zip>", and "<country>" combination and press Save Account Information
    Then I should see an error message indicating invalid input

  Examples:
    | userID  | password  | firstname | lastname | email              | phone      | address1    | city    | state   | zip     | country  |
    | google  | Pass@123  |           | Doe      | john.doe@example.com | 4031234567 | 123 Main St | Calgary | Alberta | T2P1B1  | Canada   | # Missing first name