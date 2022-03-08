Feature: Call api

  @api
  Scenario: Test api
    When Call API https://apibeta.podfoods.co/buyer/buyer_auth/sign_in.json
    Then Check response code = 200
    And Check response body correct
