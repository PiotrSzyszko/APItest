Feature: GitHub API Testing

  Scenario: 01 Creating a Comment
    Given User "PiotrSzyszko" logs in
    When User creates comment for a gist with ID "082b9573943f9e7efdce01dceb1a7e75"
    Then Comment is created


  Scenario: 02 Listing Commenta
    Given User "PiotrSzyszko" logs in
    When User gets list of comments for a gist with ID "082b9573943f9e7efdce01dceb1a7e75"
    Then List is received


  Scenario: 03 Geting a Comment
    Given User "PiotrSzyszko" logs in
    When User gets comment for a gist with ID "082b9573943f9e7efdce01dceb1a7e75"
    Then Comment is received


  Scenario: 04 Updating a Comment
    Given User "PiotrSzyszko" logs in
    When User updates comment for a gist with ID "082b9573943f9e7efdce01dceb1a7e75"
    Then Comment is updated


  Scenario: 05 Deleting a Comment
    Given User "PiotrSzyszko" logs in
    When User deletes comment for a gist with ID "082b9573943f9e7efdce01dceb1a7e75"
    Then Comment is deleted