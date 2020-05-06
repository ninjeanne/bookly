Feature: As a registered User
  I want to manage my friendship book

  Scenario: successfully logged in and getting user data
    When I login with "test-user""resu"
    Then  The response is the test user

  Scenario: Delete friendship book cover
    When I login with "test-user""resu"
    And I navigate to friendship book
    When User friendship book cover is loaded
    Then I delete friendship book cover

  Scenario: Browse through friendship book
    When I login with "test-user""resu"
    And I navigate to friendship book
    When User friendship book cover is loaded
    Given User pages are loaded

  Scenario: Create new friendship book cover
    When I login with "test-user""resu"
    And I navigate to profile
    When I can create a friendship book cover

  Scenario: Edit friendship book cover
    When I login with "test-user""resu"
    Given I can create a friendship book cover
    And I navigate to profile
    When I can create a friendship book cover

