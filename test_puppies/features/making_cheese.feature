Feature: Making Cheese

  As a cheese maker
  I want to make cheese
  So I can share my cheesiness

  Scenario: Using the cheese machine
    Given I have no cheese
    When I press the make cheese button
    Then I should have 1 piece of cheese


  You can implement step definitions for undefined steps with these snippets:

  Given /^I have no cheese$/ do
    puts "I am so sad"
  end

  Given /^I press the make cheese button$/do
    puts "There is hope"
  end

  Then /^I should have (\d+) piece of cheese$/ do |num_pieces|
    puts "Rejoice! We have #{num_pieces} pieces of cheese"
  end

