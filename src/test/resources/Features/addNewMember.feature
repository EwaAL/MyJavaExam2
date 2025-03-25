Feature: Create user accounts

  Scenario: Create a successful user account
    Given I have the user info I need
    And I am at the website "https://membership.basketballengland.co.uk/NewSupporterAccount"
    And I have filled in the form
    When I click the button
    Then an account will be created

  Scenario Outline: Try to create a user account with missing data
    Given I have the user info I need
    And I am at the website "https://membership.basketballengland.co.uk/NewSupporterAccount"
    And I have filled in the form with an error with <Problem>
    When I click the button
    Then a message with the text <Message> for number <Number> will show
    Examples:
      | Number | Problem                   | Message                                                                   |
      | 1      | member_lastname           | Last Name is required                                                     |
      | 2      | signupunlicenced_password | Password did not match                                                    |
      | 3      | Terms and Conditions      | You must confirm that you have read and accepted our Terms and Conditions |
