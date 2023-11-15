package AvactisSignin;

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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class SigninPage1 {
    
	@FindBy (id = "account_sign_in_form_email_id")
	private WebElement email;
	
	@FindBy (id = "account_sign_in_form_passwd_id")
	private WebElement Password;
	
	@FindBy (xpath = "//input[@value='Sign In']")
	private WebElement SigninButton;

	@FindBy (xpath = "//div[text()='Your cart is recovered.']")
	private WebElement displayVerifyText;
	
	@FindBy (xpath = "//div[text()='Account and password could not be identified. Try again or create an account.']")
	private WebElement VerifyText;
	
	private WebDriver driver;
	private WebDriverWait wait;
	TakesScreenshot ts;
	
	public SigninPage1(WebDriver driver) {   
		this.driver = driver;              
		PageFactory.initElements(driver, this);	
		wait = new WebDriverWait(driver, Duration.ofSeconds(7));
	}	
	
	public SigninPage1() {   
		this.driver = driver;              
		PageFactory.initElements(driver, this);			
	}	
	
	
	public void enterCredentials(String emailid, String pass) {
		email.sendKeys(emailid);
		Password.sendKeys(pass);
		SigninButton.click();
	}
		
	public boolean displayTextForSigninSuccessfull() {		
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Your cart is recovered.']")));
	return displayVerifyText.isDisplayed();		
	
	}
	
	public boolean verifyDisplayedTextForSignInFailed() {		
		
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Account and password could not be identified. Try again or create an account.']")));
	return VerifyText.isDisplayed();		
	
	}
	
	
	public void getScreenshot() throws IOException {
		
		try{
			ts = (TakesScreenshot) driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile, new File("c:\\temp\\avactis_success.png") );
			assertTrue(true,"Test Case passed ");

			}
			catch(Exception e){
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile, new File ("c:\\temp\\avactis_failure.png"));
			fail("Test Case Failed ");

			}		
	}
}
