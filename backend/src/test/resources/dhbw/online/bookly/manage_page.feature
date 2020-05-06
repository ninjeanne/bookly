Feature: As a registered User
  I want to manage my own Page

  Scenario: Delete Page
    Given I login with "test-user""resu"
    And I navigate to profile
    When I select delete page
    Then The page should be refreshed
    And I am not able to see the deleted page

  Scenario: Browse through Pages
    Given I login with "test-user""resu"
    And I navigate to friendship book
    When User pages are loaded
    Then I can browse through the pages

  Scenario: Share link to create new Page
    Given I login with "test-user""resu"
    And I navigate to profile
    When I select add entry
    Then I can copy the uuid for sharing the page

  Scenario: Edit Page
    When I go to the public page
    Then I can edit the public page

