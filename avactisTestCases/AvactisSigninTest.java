package avactisTestCases;

import org.testng.annotations.Test;
import avactisBase.TestBase;
import AvactisSignin.AvactisHomePage3;
import AvactisSignin.SigninPage1;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import static org.testng.Assert.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Normalizer.Form;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class AvactisSigninTest {
	AvactisHomePage3 homepage;
	SigninPage1 signin;
	
	String filepath = "src/test/java/avactisTestData/Avactis1.xls";
	String sheetName = "avactis3";
	String Marker = "NTStartEnd";
	Logger log;
	
				
	@DataProvider
	public String[][] AvactisTestData3() {
		String data [][]  = readDataFromXLS(filepath, sheetName, Marker);
		return data;
	}

	/*
	TC3
	write a test to login into the store.(positive and negative)
	1. Click on SignIn
	2. Enter the valid credentials
	3. Login Successful
	*/	
		
//@Test(dataProvider = "AvactisTestData3")
public void avactisSignInValidTest(String emailAdd,String pass ) throws IOException {

log.info("Click on Signin Button");

signin = homepage.clickOnSignIn();

log.info("Enter email id and password");
signin.enterCredentials(emailAdd, pass);
//getting input data from xls file	

boolean CheckValue = signin.displayTextForSigninSuccessfull();
Assert.assertTrue(CheckValue, "Sign-in into account got failed");
signin.getScreenshot();

}


String sheetName4 = "avactis4";
		
@DataProvider
public String[][] AvactisTestData4() {
	String data [][]  = readDataFromXLS(filepath, sheetName4, Marker);
	return data;
}

/*
TC4
Negative scenario:
1. Click on SignIn
2. Enter the valid credentials
3. Login Fails     
Creating a Negative test Case by entering wrong password & checking if it fails 
*/


@Test(dataProvider = "AvactisTestData4")
public void avactisSignInFailedTest(String emailAdd,String pass ) throws IOException {

log.info("Click on Signin Button");
signin = homepage.clickOnSignIn();

log.info("Enter email id and password");
log.info("Entering wrong password to fail the testCase in negative Scenario");
signin.enterCredentials(emailAdd, pass);
//getting input data from xls file	

boolean CheckValue = signin.verifyDisplayedTextForSignInFailed();
Assert.assertTrue(CheckValue, "Sign-in into account got failed");
signin.getScreenshot();

}
			
@Parameters("browserName")
 @BeforeMethod
	public void beforeClass() {	
	  DOMConfigurator.configure("src/test/java/avactisTestData/log4j.xml");
	  log = Logger.getLogger(AvactisSigninTest.class.getName());
	  log.info("Opening Url");
	  log.debug("Going to Avactis Home Page");
       homepage = new AvactisHomePage3();
       signin = new SigninPage1();
	}


	@AfterMethod
	public void afterClass() {
		homepage.closeBrowser();
	}

public String[][] readDataFromXLS (String xlFilePath, String sheetName, String marker) {
	String[][] tabArray = null;
	try {

		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
		Sheet sheet = workbook.getSheet(sheetName);
		Cell tableStart = sheet.findCell(marker);

		int StartRow, StartCol, endRow, endCol, ci, cj;

		StartRow = tableStart.getRow();
		StartCol = tableStart.getColumn();

		Cell tableEnd = sheet.findCell(marker, StartRow + 1, StartCol + 1, 100, 64000, false);
		endRow = tableEnd.getRow();
		endCol = tableEnd.getColumn();
		
		System.out.println(
				"startRow=" + StartRow + ",endRow=" + endRow + "," + "StartCol=" + StartCol + ",endCol=" + endCol);

		tabArray = new String[endRow - StartRow - 1][endCol - StartCol - 1];
		ci = 0;
		for (int i = StartRow + 1; i < endRow; i++, ci++) {
			cj = 0;
			for (int j = StartCol + 1; j < endCol; j++, cj++) {
				tabArray[ci][cj] = sheet.getCell(j, i).getContents();

			}
		}	
	} 
	catch (FileNotFoundException e) {
		System.out.println("The file you specified does not exists");
	} 
	catch (Exception e) {
		System.out.println("Please check if file path, sheet name and tag name are correct");
		e.toString();
	}

	return (tabArray);

}       
}
