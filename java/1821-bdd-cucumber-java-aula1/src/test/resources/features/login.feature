Feature: Only registered users can login

  Scenario: Only valid user can login
    Given the valid user
    When log in
    Then redirected to the auction page

  Scenario: An invalid user cannot login
    Given the invalid user
    When try to login
    Then continue on the login page