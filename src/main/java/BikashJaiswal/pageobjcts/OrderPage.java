package BikashJaiswal.pageobjcts;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import BikashJaiswal.abstractcomponent.abstractcomponents;

public class OrderPage extends abstractcomponents {
	
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	


	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	
	
	public Boolean VerifyOrderDisplay(String productName)
	{
		Boolean match= productNames.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
}
