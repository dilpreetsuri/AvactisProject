package AvactisRegistration;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import java.util.Random;

public class AvactisRegistrationForm1 {
	
	
	
	@FindBy(name="customer_info[Customer][Email]")
	private WebElement email;

	@FindBy(name="customer_info[Customer][Password]")
	private WebElement password;
		
	@FindBy(name="customer_info[Customer][RePassword]")
	private WebElement repassword;
	
	@FindBy(name="customer_info[Customer][FirstName]")
	private WebElement FirstName;
	
	@FindBy(name="customer_info[Customer][LastName]")
	private WebElement LastName;
	
	@FindBy(name="customer_info[Customer][Country]")
	private WebElement Country;
	
	@FindBy(name="customer_info[Customer][State]")
	private WebElement State;
	
	@FindBy(name="customer_info[Customer][ZipCode]")
	private WebElement Zipcode;
	
	@FindBy(name="customer_info[Customer][City]")
	private WebElement city;
		
	@FindBy(name="customer_info[Customer][Streetline1]")
	private WebElement Address1;
	
	@FindBy(name="customer_info[Customer][Streetline2]")
	private WebElement Address2;
	
	@FindBy(name="customer_info[Customer][Phone]")
	private WebElement PhoneNumber;
	
	@FindBy(xpath="//input[@type='submit' and @value='Register']")
	private WebElement RegisterButton;
	
	@FindBy (xpath = "//div[text()='Account created successfully. You are now registered.']")
	private WebElement accountCreatedSuccess;
	
	@FindBy (xpath = "//div[@class='registration_form']/div[@class='note note-danger']")
	private WebElement FirstNameMissingIsDisplay;

    private WebDriver driver;
    private WebDriverWait wait;
    TakesScreenshot ts;
	
    private String pageTitle = "Avactis Demo Store";
	private String url = "http://localhost/Avactis/register.php";
	
	
		
	public AvactisRegistrationForm1(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}
	
	public AvactisRegistrationForm1() {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	



	public void closeBrowser() {
		driver.quit();
		
	}

	
	public String getTitleAfterLogin() {  
		
		return driver.getTitle();
	}

	

		
	  public static String generateRandomEmail() {
	      String[] domains = {"gmail.com", "yahoo.com","hotmail.com"};
	      Random random = new Random();
	      String username = "dilpreet" + random.nextInt(5000); // You can change the range or format as needed
	      String domain = domains[random.nextInt(domains.length)];

	      return username + "@" + domain;
	  } 
	
	  String randomEmail = generateRandomEmail(); // call to random email method	
	
	  
	  public void emailAddress() {
		  email.sendKeys(randomEmail);
	  }
	  
	  
	  
	public void fillRegistrationDetails(String pass,String repass,String ftName,String ltName,String countryName,
	String stateSelect,	String zpcode, String City, String add1, String add2, String phnum ) {
		email.sendKeys(randomEmail);
		password.sendKeys(pass);
		repassword.sendKeys(repass);
		FirstName.sendKeys(ftName);
		LastName.sendKeys(ltName);
		
		Select obj1 = new Select(Country);
		obj1.selectByVisibleText(countryName);
		
		Select obj2 = new Select(State);
		obj2.selectByVisibleText(stateSelect);
		
		Zipcode.sendKeys(zpcode);
		city.sendKeys(City);
		Address1.sendKeys(add1);
		Address2.sendKeys(add2);
		PhoneNumber.sendKeys(phnum);
		RegisterButton.click();		
	}
	
	
	
	public boolean accountCreatedIsDisplayedSuccess() {
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Account created successfully. You are now registered.']")));
		
	return accountCreatedSuccess.isDisplayed();
		
	}
	
	
	public boolean firstNameMissingIsDisplayed() {
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='registration_form']/div[@class='note note-danger']")));	
		return FirstNameMissingIsDisplay.isDisplayed();
	}
	
	
	
	
	public void getScreenshot() throws IOException {
		
		try{
			ts = (TakesScreenshot) driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile, new File("c:\\temp\\avactis_success1.png") );
			assertTrue(true,"Test Case passed ");

			}
			catch(Exception e){
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile, new File ("c:\\temp\\avactis_failure1.png"));
			fail("Test Case Failed ");

			}		
	}	
	
}

