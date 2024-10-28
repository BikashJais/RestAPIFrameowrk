package BikashJaiswalTesting.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import BikashJaiswal.pageobjcts.CartPage;
import BikashJaiswal.pageobjcts.CheckoutPage;
import BikashJaiswal.pageobjcts.ConfirmationPage;
import BikashJaiswal.pageobjcts.LandingPage;
import BikashJaiswal.pageobjcts.ProductCatalogue;
import BikashJaiswalTesting.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	
	@Given("I landed on Ecommerce page")	
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage=launchApplication();
	}
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		 productCatalogue = landingPage.loginApplication(username,password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductsToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartPage.goToCheckout();
		checkoutpage.selectCountry("india");
		confirmationPage = checkoutpage.submitOrder();
	}
	
	
	@Then("{string} message is displayed on confirmationPage")
	public void message_is_displayed_confirmationpage(String string)
	{
		String confirmMessage = confirmationPage.verifyConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void something_message_is_displayed(String string) throws Throwable
	{
		Assert.assertEquals(string,landingpage.getErrorMessage());
		driver.close();
	}
}
