Feature: Flight Search and Compose an email in Gmail

  @test
  Scenario: Search for flights from Delhi to Mumbai
    Given User is on the MMT website
    When User searches for flights from Delhi to Mumbai
    And User sorts the results by departure
    Then User prints the airline name and price with the 2nd lowest price
    

  @test1
  Scenario: Compose an email and send it
    Given I am logged in to Gmail
    When I compose a new email with subject "Damco"
    




