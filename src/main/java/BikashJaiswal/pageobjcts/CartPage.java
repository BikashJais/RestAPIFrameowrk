package BikashJaiswal.pageobjcts;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import BikashJaiswal.abstractcomponent.abstractcomponents;

public class CartPage extends abstractcomponents {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	


	@FindBy(css=".cartSection h3")
	List<WebElement> productTiltles;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutele;
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match= productTiltles.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutele.click();
		return new CheckoutPage(driver);
	}

}
