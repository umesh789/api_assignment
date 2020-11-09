Feature: Verify Pet Store APIs are up and Running.

  @SmokeTest
  Scenario: Verify Create a new pet API
    Given Create request body with new pet details
      | cat_Name     | name    | tagName | photoUrls | status    |
      | new category | petName | tagName | url       | available |

    When  Run Create a new pet API
    Then Verify Get available pet API response 200.
    And Verify category created successfully and status should be "available"

  @SmokeTest
  Scenario: Verify Get pet API
    When Get available pet API
    Then Verify Get available pet API response 200.
    And Verify category name should be "new category"
    And Verify status should be "available"

  @SmokeTest
  Scenario: Verify update pet API
    Given Create request body with update pet details
      | cat_Name     | name    | tagName | photoUrls | status    |
      | new category | petName | tagName | url       | booked |
    When  Run update pet API
    Then Verify Get available pet API response 200.
    And Verify category created successfully and status should be "booked"

  @SmokeTest
  Scenario: Verify delete pet API
    When run delete pet API where pet id is given
    Then Verify Get available pet API response 200.
