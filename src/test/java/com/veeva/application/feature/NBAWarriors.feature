@smoke @warrior
Feature: NBA Warrior

  @productDetails
  Scenario Outline: NBA get product details
    Given Open '<Browser>' and load 'NBA Warrior' URL
    When Page is Loaded
    Then Navigate to '<category>' shop now
    Then Navigate to newly opened window from "Warrior"
    Then Select '<productType>' product
    Then Collect data for '<category>' '<productType>' in '<Browser>'

    Examples:
      | Browser | category | productType |
      | Chrome  | Men\'s   | Jackets     |
      | Firefox | Men\'s   | Jackets     |
#      | Chrome  | Men\'s   | Hats        |
#      | Firefox | Men\'s   | Hats        |

  @news
  Scenario Outline: NBA get news details
    Given Open '<Browser>' and load 'NBA Warrior' URL
    When Page is Loaded
    Then Navigate to menu and to 'News and Features'
    Then Count overAll videos
    Then Count videos '>=3' days

    Examples:
      | Browser |
#      | Chrome  |
      | Random  |
#      | Edge    |
