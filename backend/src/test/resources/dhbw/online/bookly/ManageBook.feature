Feature: As a registered User
  I want to manage my friendship book

  Scenario: Delete friendship book cover
    Given I am logged in
    And I navigate to friendship book cover
    When User friendship book cover is loaded
    And User friendship book cover is shown
    When I select delete friendship book cover
    Then The page should be refreshed
    And I am able to create a new cover

  Scenario: Browse through friendship book
    Given I am logged in
    And I navigate to friendship book
    When User friendship book cover is loaded
    And User friendship book cover is shown
    Given Friendship book remaining pages not null
    When I select next page
    Then Next page will be loaded
    And I can see next page

  Scenario: Create new friendship book cover
    Given I am logged in
    And I navigate to profile
    When I select create friendship book cover
    Then The friendship book cover form should be loaded
    And I should be able to edit form data


  Scenario: Edit friendship book cover
    Given I am logged in
    Given I created a friendship book cover
    And I navigate to profile
    When I select edit friendship book cover
    Then The friendship book cover form should be loaded
    And I should be able to see the form with old data
    And I should be able to edit the friendship book cover data
