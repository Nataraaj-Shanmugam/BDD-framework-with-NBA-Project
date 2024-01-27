Feature: NBA Warrior

  @smoke
  Scenario: NBA get product details
    Given Load 'NBA Warrior' URL
    When Page is Loaded
    Then Navigate to '<category>' Shop Now
    Then Navigate to newly opened window
    Then Select '<productType>' product
    Then Collect data of each product

