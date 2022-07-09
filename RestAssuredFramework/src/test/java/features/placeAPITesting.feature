Feature: Test the place API

@AddPlace
  Scenario Outline: To test the add place API
    Given request payload for add place API with "<name>" "<language>" "<address>"
    When user calls the "AddPlaceAPI" with "POST" method
    Then success response is received with status code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"
    Then verify place_id created is equal to "<name>" using "getPlaceAPI"
    
    
    Examples: 
      | name       | language | address              |
      | JohnnyDeep | English  | Hollywood California |
 #    | JohnnyDeep2 | English2  | Hollywood California2 |
 
 
 @DeletePlace
 Scenario:
 
 Given DeletePlaceAPI payload
 When user calls the "deletePlaceAPI" with "POST" method
 Then success response is received with status code 200
 And "status" in response is "OK"