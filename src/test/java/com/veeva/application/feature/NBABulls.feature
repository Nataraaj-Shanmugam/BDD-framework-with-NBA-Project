Feature: NBA Warrior

  @smoke @bullsFooterLink
  Scenario Outline: Get NBA footer links
    Given Open '<Browser>' and load 'NBA Bulls' URL
    When Page is Loaded
    Then Navigate to footer
    Then Get all footer links
    Then Export to csv file

    Examples:
      | Browser |
#      | Chrome  |
#      | Random  |
      | Edge    |
#      | Firefox    |
