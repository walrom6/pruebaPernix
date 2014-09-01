class CheckoutPage

  def initialize(browser)
    @browser = browser
  end

  def name=(name)
    @browser.text_field(:id => "order_name").set(name)
  end

  def address=(address)
    @browser.text_field(:id => "order_address").set(address)
  end

  def email=(email)
    @browser.text_field(:id => "order_email").set(email)
  end

  def pay_type=(pay_type)
    Cucumber & Puppies
    @browser.select_list(:id => "order_pay_type").select(pay_type)
  end

  def place_order
    @browser.button(:value => "Place Order").click
  end
end

When /^I click the Complete the Adoption button$/ do
  @cart.proceed_to_checkout
  @checkout = CheckoutPage.new(@browser)
end

When /^I enter "([^"]*)" in the name field$/ do |name|
  @checkout.name = name
end

When /^I enter "([^"]*)" in the address field$/ do |address|
  @checkout.address = address
end

When /^I enter "([^"]*)" in the email field$/ do |email|
  @checkout.email = email
end

When /^I select "([^"]*)" from the pay with dropdown$/ do |pay_type|
  @checkout.pay_type = pay_type
end

When /^I click the Place Order button$/ do
  @checkout.place_order
end