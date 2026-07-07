package MyFrameworkDesign.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import MyFrameworkDeisgn.pageobjects.CartPage;
import MyFrameworkDeisgn.pageobjects.CheckoutPage;
import MyFrameworkDeisgn.pageobjects.ConfirmationPage;
import MyFrameworkDeisgn.pageobjects.LandingPage;
import MyFrameworkDeisgn.pageobjects.ProductCatalogue;
import MyFrameworkDesign.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImp extends BaseTest{
	public LandingPage landingpage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	public CheckoutPage checkoutpage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingpage =launchApplication();
	}
	
	@Given("^Logged in with valid username(.+) and password (.+)$")
	public void Logged_in_with_valid_username_and_password(String username, String password)
	{
		 productCatalogue = landingPage.loginApplication(username, password);
	}
  
	@When("^I add product(.+) to the cart$")
	public void I_add_product_to_the_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	 @When("^checkout(.+) and submit the order$")
	 public void checkout_and_submit_the_order(String productName)
	 {
			CartPage cartPage = productCatalogue.goToCartPage();

			Boolean match = cartPage.VerifyProductDisplay(productName);
			Assert.assertTrue(match);
		    checkoutpage = cartPage.goToCheckout();
			checkoutpage.selectCountry("india");
			confirmationPage = checkoutpage.submitOrder();
			
	 }
	 
	 @Then("{string} message is displayed on ConfirmationPage")
	 public void message_is_displayed_on_confirmationPage(String string)
	 {
	        String confirmMessage = confirmationPage.getConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
			driver.close();
	 } 
	
	 
	 @Then("^\"([^\"]*)\" message is displayed$")
	 public void something_message_is_displayed(String str1)throws Throwable {
		 Assert.assertEquals(str1, landingPage.getErrorMessage());
		 driver.close();
	 }
}
