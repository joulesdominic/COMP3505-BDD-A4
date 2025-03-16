 Feature: Test Pet Store Account Registration 
 
  Scenario Outline: Test valid Pet Store Account Registration
    Given I open Chrome browser
    And I go to Pet Store Create an Account Page
    When I enter a valid "<userID>", "<password>", "<firstname>", "<lastname>", "<email>", "<phone>", "<address1>", "<city>", "<state>", "<zip>", and "<country>" combination and press Save Account Information
    Then My account is made with the appropriate information assigned to it

    Examples: 
      | userID| password    | firstname | lastname | email | phone | address1 | city | state | zip | country | 
| user123 | Pass@123  | John      | Doe      | john.doe@example.com  | 4031234567  | 123 Main St    | Calgary | Alberta | T2P1B1 | Canada  |
    | user456 | Secure456| Alice     | Smith    | alice.smith@email.com | 4037654321  | 456 Elm St     | Calgary | Alberta | T3K3P3 | Canada  |

  Scenario Outline: Test invalid Pet Store Account Registration 
    Given I open Chrome browser
    And I go to Pet Store Create an Account Page
    When I enter an invalid "<userID>", "<password>", "<firstname>", "<lastname>", "<email>", "<phone>", "<address1>", "<city>", "<state>", "<zip>", and "<country>" combination and press Save Account Information
    Then I should see an error message indicating invalid input

  Examples:
    | userID  | password  | firstname | lastname | email              | phone      | address1    | city    | state   | zip     | country  |
    |         | Pass@123  | John      | Doe      | john.doe@example.com | 4031234567 | 123 Main St | Calgary | Alberta | T2P1B1  | Canada   | # Missing userID  
    | user123 |          | John      | Doe      | john.doe@example.com | 4031234567 | 123 Main St | Calgary | Alberta | T2P1B1  | Canada   | # Missing password  
    | user456 | short    | Alice     | Smith    | alice.smith@email.com | 4037654321 | 456 Elm St  | Calgary | Alberta | T3K3P3  | Canada   | # Weak password  
    | user789 | Pass@123 |           | Doe      | john.doe@example.com | 4031234567 | 123 Main St | Calgary | Alberta | T2P1B1  | Canada   | # Missing firstname  
    | user321 | Pass@123 | John      |          | john.doe@example.com | 4031234567 | 123 Main St | Calgary | Alberta | T2P1B1  | Canada   | # Missing lastname  
    | user654 | Pass@123 | John      | Doe      | invalid-email        | 4031234567 | 123 Main St | Calgary | Alberta | T2P1B1  | Canada   | # Invalid email  
    | user987 | Pass@123 | John      | Doe      | john.doe@example.com | abcdefghij | 123 Main St | Calgary | Alberta | T2P1B1  | Canada   | # Invalid phone  
    | user852 | Pass@123 | John      | Doe      | john.doe@example.com | 4031234567 |             | Calgary | Alberta | T2P1B1  | Canada   | # Missing address  
    | user258 | Pass@123 | John      | Doe      | john.doe@example.com | 4031234567 | 123 Main St |         | Alberta | T2P1B1  | Canada   | # Missing city  
    | user147 | Pass@123 | John      | Doe      | john.doe@example.com | 4031234567 | 123 Main St | Calgary |         | T2P1B1  | Canada   | # Missing state  
    | user369 | Pass@123 | John      | Doe      | john.doe@example.com | 4031234567 | 123 Main St | Calgary | Alberta |         | Canada   | # Missing zip  
    | user369 | Pass@123 | John      | Doe      | john.doe@example.com | 4031234567 | 123 Main St | Calgary | Alberta | T2P1B1  | XYZ      | # Invalid country  
 