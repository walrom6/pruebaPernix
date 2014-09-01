
  Feature: Validating the USA Zip Code Information Web Service

    Scenario: Verify it contains the expected operations
    When I ask the service for the supported operations
    Then "get_info_by_area_code" should be supported
    And "get_info_by_city" should be supported
    And "get_info_by_state" should be supported
    And "get_info_by_zip" should be supported

    When /^I ask the service for the supported operations$/ do
      @operations = using(ZipCodeInformationService).operations
    end

    Then /^"([^"]*)" should be supported$/ do |operation|
      @operations.should include operation.to_sym
    end


    Scenario: Getting the zip code information by zip code
    When I ask for the zip code information for "44114"
    Then I should get the following information:
    | city | state | area_code | time_zone |
    | Cleveland | OH | 216 | E |

    When /^I ask for the zip code information for "([^"]*)"$/ do |zip_code|
      using(ZipCodeInformationService).get_info_by_zip('USZip' => zip_code)
    end

    Then /^I should get the following information:$/ do |table|
        expected = table.hashes.first
        using(ZipCodeInformationService) do |service|
        service.response_for(:city).should == expected['city']
        service.response_for(:state).should == expected['state']
        service.response_for(:area_code).should == expected['area_code']
        service.response_for(:time_zone)_zone.should == expected['time_zone']
      end
    end