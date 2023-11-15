package AvactisRegistration;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;


public class AvactisHomePage1 extends LoadableComponent<AvactisHomePage1>{
		
	@FindBy (xpath = "//a[text()='My Account']")
	private WebElement MyAccount;
	
	private WebDriver driver;
	
	private String pageTitle = "Avactis Demo Store";
	private String url = "http://localhost/avactis/";
	
	public AvactisHomePage1()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();		
		PageFactory.initElements(driver, this);
		get();
	}	
	
	
	public AvactisHomePage1(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		isLoaded();
	}
	
	
	public RegisterButtonPO1 goToMyAccount() {
		MyAccount.click();
		RegisterButtonPO1 register = new RegisterButtonPO1(driver); 	
		return register;

	}	
	
	public void closeBrowser() {
		driver.quit();
	}

	public String getTitleAfterLogin() {
		
		return driver.getTitle();
	}

	
	
	@Override
	protected void isLoaded() throws Error {
		
		assertEquals(driver.getTitle(), pageTitle, "Seems Home Page is not loaded!!");
	}

	@Override
	protected void load() {
		
		driver.get(url);
	}
	

}
