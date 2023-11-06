Feature: Verify the price of a book and its addition to the cart and check the URL
  Scenario: add a book to a cart and verify
    Given I am at the official website of ebay "https://www.ebay.com"
    When I select the "Book" category
    And Search "Python in easy steps"
    And Select the book "Python in easy steps by Mike McGrath 1840785969 The Fast Free Shipping"
    And Handle the page switching
    And Add the book to the cart
    Then Verify the URL of the page "https://cart.payments.ebay.com/"
    And The number of products in the cart is "1"
    And Verify the price is "US $9.37"



