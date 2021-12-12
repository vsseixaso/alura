Feature: Proposing Bids to Auction

  Scenario: Proposing a single valid bid
    Given a valid bid
    When propose to auction
    Then bid is accepted

  Scenario: Proposing multiple valid bids
    Given a valid bid of 10.0 BRL from the user "fulano"
      And a valid bid of 15.0 BRL from the user "beltrano"
    When propose multiple bids to auction
    Then bids are accepted

  Scenario Outline: Proposing a invalid bid
    Given a invalid bid of <value> BRL from the user '<username>'
    When propose to auction
    Then bid is denied

    Examples:
      | value | username |
      |     0 | beltrano |
      |    -1 | ciclano  |

  Scenario: Proposing a sequence of bids
    Given two bids
      | value | username |
      |  10.0 | beltrano |
      |  15.0 | beltrano  |
    When propose multiple bids to auction
    Then the second bid is denied