Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if place is added successfully using API request
    Given Add place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "POST" request
    Then API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"
    
Examples: 
      |name    | language   |address           |
      |AAhouse | English    |world cross center|
#     |BBhouse | Spanish    |sea cross center  |
 
@DeletePlace
Scenario: 
    Given Delete Place payload
    When User calls "deletePlaceAPI" with "POST" request
    Then API call is success with status code 200
    And "status" in response body is "OK"