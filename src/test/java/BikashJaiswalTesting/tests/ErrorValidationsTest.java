package BikashJaiswalTesting.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import BikashJaiswal.pageobjcts.CartPage;
import BikashJaiswal.pageobjcts.ProductCatalogue;
import BikashJaiswalTesting.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {


	@Test(groups= {"ErrorHandling"})//tests are getting skiiped if adding (, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException{
		
		landingpage.loginApplication("bikoojais51@gmail.com", "Kohlip@5679");
		Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMessage());

	}
	
	@Test
	public void ProductErrorValidations() throws IOException, InterruptedException{
		
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingpage.loginApplication("bikoojais51@gmail.com", "Kohli@5679");
		// since loginapplication method itself creating and returning product catalogue
		// page object

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductsToCart(productName);

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT33");
		Assert.assertFalse(match);

		
		

	}
}
