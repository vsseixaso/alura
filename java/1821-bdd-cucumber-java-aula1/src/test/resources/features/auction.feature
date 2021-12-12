@auction
Feature: Registering an auction
  Background:
    Given a logged in user

  Scenario: A logged in user can register an auction
    When access the new auction page
      And fill the form with valid data
    Then go back to the auctions page
      And the new auction appears in the table