Feature: As a registered User
  I want to manage my own Page

  Scenario: Delete Page
    Given I am logged in
    And I navigate to profile
    When User pages are loaded
    And User pages are shown
    When I select delete page
    Then The page should be refreshed
    And I am not able to see the deleted page

  Scenario: Browse through Pages
    Given I am logged in
    And I navigate to profile
    When User pages are loaded
    And User pages are shown
    When I select friendship book
    Then I can browse through the pages

  Scenario: Share link to create new Page
    Given I am logged in
    And I navigate to profile
    When User pages are loaded
    And User pages are shown
    When I select add entry
    Then I can copy the generated link and share it with someone

  Scenario: Edit Page
    Given I am logged in
    And I navigate to profile
    When User pages are loaded
    And User pages are shown
    When I select edit page
    Then I can edit the page

