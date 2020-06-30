Feature: GitHub API Testing

  Scenario: Creating a Comment
    Given User "PiotrSzyszko" logs in
    When User creates comment for a gist with ID "082b9573943f9e7efdce01dceb1a7e75"
    Then Comment is created


  Scenario: Listing Commenta
    Given User "PiotrSzyszko" logs in
    When User gets list of comments for a gist with ID "082b9573943f9e7efdce01dceb1a7e75"
    Then List is received


  Scenario: Geting a Comment
    Given User "PiotrSzyszko" logs in
    When User gets comment with ID "3358791" for a gist with ID "082b9573943f9e7efdce01dceb1a7e75"
    Then Comment is received


  Scenario: Updating a Comment
    Given User "PiotrSzyszko" logs in
    When User updates comment with ID "3358791" for a gist with ID "082b9573943f9e7efdce01dceb1a7e75"
    Then Comment is updated


  Scenario: Deleting a Comment
    Given User "PiotrSzyszko" logs in
    When User deletes comment with ID "3358791" for a gist with ID "082b9573943f9e7efdce01dceb1a7e75"
    Then Comment is deleted