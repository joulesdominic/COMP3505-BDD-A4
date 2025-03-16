Feature: Test Change Password Feature

  Scenario Outline: Change password successfully
    Given I open Chrome browser
    And I go to Pet Store Login page
    When I enter valid "<username>" and "<password>" combination
    And I navigate to the change password page
    When I enter "<newpassword>" in the password field
    And I confirm "<newpassword>" in the repeat password field
    And I submit the password change form
    Then I should be directed back to main page
    #And I should be able to login with "<username>" and "<newpassword>"

    Examples: 
      | username  | password | newpassword |
      | bubs      | love  | newlove    |
