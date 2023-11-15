package AvactisSignin;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

/*
TC3
write a test to login into the store.(positive and negative)
1. Click on SignIn
2. Enter the valid credentials
3. Login Successful
*/

public class AvactisHomePage3 extends LoadableComponent<AvactisHomePage3>{
		
	@FindBy (xpath = "//a[text()='Sign In']")
	private WebElement Signin;
	
	private WebDriver driver;
	
	private String pageTitle = "Avactis Demo Store";
	private String url = "http://localhost/avactis/";
	
	public AvactisHomePage3()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();		
		PageFactory.initElements(driver, this);
		get();
	}	
	
	public AvactisHomePage3(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		isLoaded();
	}
	
	
	public SigninPage1 clickOnSignIn() {
		Signin.click();
		SigninPage1 signin = new SigninPage1(driver); 	
		return signin;

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
