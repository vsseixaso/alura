Feature: Proposing Bids

  Scenario: Proposing a single valid bid
    Given a valid bid
    When propose the bid
    Then bid is accepted