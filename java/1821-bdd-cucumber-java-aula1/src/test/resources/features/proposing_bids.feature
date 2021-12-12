Feature: Proposing Bids to Auction

  Scenario: Proposing a single valid bid
    Given a valid bid
    When propose to auction
    Then bid is accepted

  Scenario: Proposing multiple valid bids
    Given a valid bid of 10.0 reais from the user "fulano"
      And a valid bid of 15.0 reais from the user "beltrano"
    When propose multiple bids to auction
    Then bids are accepted