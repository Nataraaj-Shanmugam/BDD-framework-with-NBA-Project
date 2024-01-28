Feature: NBA Sixers

  @smoke @sixers
  Scenario Outline: Get NBA sixers team
    Given Open '<Browser>' and load 'NBA Sixers' URL
    When Page is Loaded
    Then Collect total number of slides
    Then Validate title '<Title>' for team '<TeamName>'
    Then Click WatchReplay
    Then Navigate to newly opened window from "Sixer"
    Then Click Game Recap
    Then Wait till AD gets over
    Then Validate video is playing

    Examples:
      | Browser | Title | TeamName|
      | Chrome  |  Mon. Jan 22, 7:00 PM EST\nFinal   |  SPURS vs 76ERS       |
#      | Chrome  |  a   |  b      |
