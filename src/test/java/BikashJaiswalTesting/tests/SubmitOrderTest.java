package BikashJaiswalTesting.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BikashJaiswal.pageobjcts.CartPage;
import BikashJaiswal.pageobjcts.CheckoutPage;
import BikashJaiswal.pageobjcts.ConfirmationPage;
import BikashJaiswal.pageobjcts.OrderPage;
import BikashJaiswal.pageobjcts.ProductCatalogue;
import BikashJaiswalTesting.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input ) throws IOException, InterruptedException{
		
		
		ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"),input.get("password"));
		// since loginapplication method itself creating and returning product catalogue
		// page object

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductsToCart(input.get("product"));

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		CheckoutPage checkoutpage = cartPage.goToCheckout();

		checkoutpage.selectCountry("india");

		ConfirmationPage confirmationPage = checkoutpage.submitOrder();
		String confirmMessage = confirmationPage.verifyConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}
		
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistory()
	{
		ProductCatalogue productCatalogue = landingpage.loginApplication("bikoojais51@gmail.com", "Kohli@5679");
		OrderPage ordersPage= productCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
		//To verify ZAEA COA 3 is displaying in order page
	
	
	
	
	@DataProvider
	public Object[][]  getData() throws IOException
	{
		//second way
		
//		HashMap<String,String> map= new HashMap<String,String>();
//		map.put("email", "bikoojais51@gmail.com");
//		map.put("password", "Kohli@5679");
//		map.put("product","ZARA COAT 3");
//		
//		HashMap<String,String> map1= new HashMap<String,String>();
//		map1.put("email", "bikoojais51@gmail.com");
//		map1.put("password", "Kohli@5679");
//		map1.put("product","ADIDAS ORIGINAL");
//		return new Object[][] {{map},{map1}};
		
		//first way 
		//return new Object[][] {{"bikoojais51@gmail.com","Kohli@5679","ZARA COAT 3"},{"bikoojais51@gmail.com","Kohli@5679","ADIDAS ORIGINAL"}};
	
		//third way
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//BikashJaiswalTesting//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}}; 
	}
}
