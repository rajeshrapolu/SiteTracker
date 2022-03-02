Feature: Test project

  Scenario Outline: Updating the data in datatable and verifying
    Given <URL> launch Chrome browser
    When I switch to Tab <tabname> and search for <searchstring> in quick find
    And I click on <searchstring> on left side panel and select datable from inline edit from drop down
    And I click run and update third row data by clicking on pencil icon with fields <label> <Website> <PhoneNumber> <Balance>
    Then I verify fields are updated correctly <label> <Website> <PhoneNumber> <Balance>

    Examples:
      | URL                                                                               | tabname             | searchstring | label      | Website            | PhoneNumber    | Balance  |
      | https://developer.salesforce.com/docs/component-library/documentation/en/48.0/lwc | Component Reference |  datatable   | Larry Page | https://google.com | (555)-755-6575 | 770.54   |
