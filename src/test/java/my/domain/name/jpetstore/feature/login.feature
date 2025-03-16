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
Feature: Test Petstore Login
 
  Scenario Outline: Test Valid Pet Store login
    Given I open Chrome browser
    And I go to Pet Store Login page
    	When I enter valid "<username>" and "<password>" combination
    Then I should be able to login successfully

    Examples: 
      | username  |password|
      | j2ee      |j2ee|
      | quality1  |12345678|
      | quality2  |12345678|

