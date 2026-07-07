@tag
Feature: Purchase the order from Ecommerce website

Background:
Given I landed on Ecommerce page

@Regression
Scenario Outline: Positive test of purchasing the order
         Given Logged in with valid username<name> and password <password>
         When I add product<productName> to the cart
         And checkout<productName> and submit the order
         Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
         
         
         Examples:
         |name              | password  | productName |
         |chinnu00@gmail.com| Chinnu@97 | ZARA COAT 3|