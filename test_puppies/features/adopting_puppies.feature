

Feature: Adopting puppies

  Scenario: Adopting one puppy
    Given I am on the puppy adoption site
    When I click the View Details button
    And I click the Adopt Me button
    And I click the Complete the Adoption button
    And I enter "Cheezy" in the name field
    And I enter "123 Main Street" in the address field
    And I enter "cheezy@example.com" in the email field
    And I select "Credit card" from the pay with dropdown
    And I click the Place Order button
    Then I should see "Thank you for adopting a puppy!"

  Given /^I am on the puppy adoption site$/ do
    @browser.goto "http://puppies.herokuapp.com"
  end

  Then /^I should see "([^\"]*)"$/ do |expected|
    @browser.text.should include expected
  end

  Given /^I am on the puppy adoption site$/ do
    @browser.goto "http://puppies.herokuapp.com"
  end

  When /^I click the View Details button$/ do
    @browser.button(:value => "View Details").click
  end

  When /^I click the Adopt Me button$/ do
    @browser.button(:value => "Adopt Me!").click
  end

  When /^I click the Complete the Adoption button$/ do
    @browser.button(:value => "Complete the Adoption").click
  end

  When /^I enter "([^\"]*)" in the name field$/ do |name|
    @browser.text_field(:id => "order_name").set(name)
  end

  When /^I enter "([^\"]*)" in the address field$/ do |address|
    @browser.text_field(:id => "order_address").set(address)
  end

  When /^I enter "([^\"]*)" in the email field$/ do |email|
    @browser.text_field(:id => "order_email").set(email)
  end

  When /^I select "([^\"]*)" from the pay with dropdown$/ do |pay_type|
    @browser.select_list(:id => "order_pay_type").select(pay_type)
  end

  When /^I click the Place Order button$/ do
    @browser.button(:value => "Place Order").click
  end

  Then /^I should see "([^\"]*)"$/ do |expected|
    @browser.text.should include expected
  end