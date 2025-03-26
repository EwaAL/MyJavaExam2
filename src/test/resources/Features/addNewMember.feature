Feature: Create user accounts

  Scenario Outline: Create a successful user account
    Given I am using <browser>
    * I have the user info I need
    * I am at the website "https://membership.basketballengland.co.uk/NewSupporterAccount"
    * I have filled in the form
    When I click the button
    Then an account will be created
    Examples:
      | browser |
      | Chrome  |
      | Firefox |
      | Edge    |

  Scenario Outline: Try to create a user account with missing data
    Given I am using <browser>
    Given I have the user info I need
    And I am at the website "https://membership.basketballengland.co.uk/NewSupporterAccount"
    And I have filled in the form with an error with <Problem>
    When I click the button
    Then a message with the text <Message> for number <Number> will show
    Examples:
      | Number | Problem                   | Message                                                                   | browser |
      | 1      | member_lastname           | Last Name is required                                                     | Chrome  |
      | 2      | signupunlicenced_password | Password did not match                                                    | Chrome  |
      | 3      | Terms and Conditions      | You must confirm that you have read and accepted our Terms and Conditions | Chrome  |
      | 1      | member_lastname           | Last Name is required                                                     | Firefox |
      | 2      | signupunlicenced_password | Password did not match                                                    | Firefox |
      | 3      | Terms and Conditions      | You must confirm that you have read and accepted our Terms and Conditions | Firefox |
      | 1      | member_lastname           | Last Name is required                                                     | Edge    |
      | 2      | signupunlicenced_password | Password did not match                                                    | Edge    |
      | 3      | Terms and Conditions      | You must confirm that you have read and accepted our Terms and Conditions | Edge       |
