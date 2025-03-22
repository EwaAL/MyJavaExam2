Feature: Create different user accounts

Scenario: Create a user account
  Given I am at https://membership.basketballengland.co.uk/NewSupporterAccount
  When I fill in date of birth
  And I fill in first name
  And I fill in last name
  And I fill in an email address
  And I confirm the Email Address
  And I type a password
  And I retype the password
  And I describe myself as a fan
  And I agree to the terms and conditions
  And I am over 18 years old
  And I agree to the code of ethics and conduct
  And I click the button
  Then an account will be created
