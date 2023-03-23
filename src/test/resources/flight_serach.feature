Feature: Flight Search
   
   @flightsearch
  Scenario: Search for flights from Delhi to Mumbai
    Given User is on the MMT website
    When User searches for flights from Delhi to Mumbai
    And User sorts the results by departure
    Then User prints the airline name and price with the 2nd lowest price
    
    