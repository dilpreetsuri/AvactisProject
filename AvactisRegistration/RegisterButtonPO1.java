package AvactisRegistration;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class RegisterButtonPO1 extends LoadableComponent<RegisterButtonPO1> {
	
	@FindBy(xpath = "//button[@class='btn btn-default']")
	@CacheLookup
	private WebElement register;

	private WebDriver driver;  
	
	private String pageTitle = "Avactis Demo Store";

	public RegisterButtonPO1(WebDriver driver) {   
		this.driver = driver;              
		PageFactory.initElements(driver, this);
		
	}

	public AvactisRegistrationForm1 clickOnRegister() {

		register.getText();
		register.click();
	
		AvactisRegistrationForm1 registationform = new AvactisRegistrationForm1(driver);
		return registationform;
	}


	@Override
	protected void isLoaded() throws Error {
		
		assertEquals(driver.getTitle(), pageTitle, "Seems My Account Page is not Loaded!!");
	}

	@Override
	protected void load() {

		// driver.get();
	}

}

























