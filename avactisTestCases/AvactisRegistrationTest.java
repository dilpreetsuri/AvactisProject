package avactisTestCases;

import org.testng.annotations.Test;
import avactisBase.TestBase;
import AvactisRegistration.AvactisHomePage1;
import AvactisRegistration.AvactisRegistrationForm1;
import AvactisRegistration.RegisterButtonPO1;
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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class AvactisRegistrationTest {
	AvactisHomePage1 homepage;
	AvactisRegistrationForm1 form;
//	TestUtil testutil;

	String filepath = "src/test/java/avactisTestData/Avactis1.xls";
	String sheetName = "avactis1";
	String Marker = "NTStartEnd";
	Logger log;
	
	@DataProvider
	public String[][] AvactisTestData() {
		String data [][]  = readDataFromXLS(filepath, sheetName, Marker);
		return data;
	}	
	
/*TC1
Positive scenario Steps are
1. Click on "My Account" Link
2. Click on "Register" Button
3. Enter the valid data
4. Click on Register Button
5. And Registration should be successful	
*/		
	
//@Test(dataProvider = "AvactisTestData")
public void avactisRegistrationValidTest(String pass, String repass, String ftName, String ltName, String countryName, 
String stateSelect,String zpcode, String City, String add1, String add2, String phnum ) throws IOException {
	
String title =  homepage.getTitleAfterLogin();
Assert.assertEquals(title, "Avactis Demo Store");

log.info("Click on My Account");

log.info("Click on Register Button");

form = homepage.goToMyAccount().clickOnRegister();
log.info("Fill up the Registration form");

//form.emailAddress();
log.info("Enter your email Address");
//getting input data from xls file	

form.fillRegistrationDetails(pass, repass, ftName, ltName, countryName, stateSelect, zpcode, City, add1, add2, phnum);
log.info("Enter All the Registration details required to Create an Account");

boolean CheckValue = form.accountCreatedIsDisplayedSuccess();
Assert.assertTrue(CheckValue, "Account Registration got failed");
form.getScreenshot();
}

String filepath2 = "src/test/java/avactisTestData/Avactis1.xls";
String sheetName2 = "avactis2";
String Marker2 = "NTStartEnd";

	
@DataProvider
public String[][] AvactisTestData2() {
	String data [][]  = readDataFromXLS(filepath2, sheetName2, Marker2);
	return data;
}

/*TC2
And negative scenarios Steps
1. Click on "My Account" Link
2. Click on "Register" Button
3. Enter the same data as you did for positive scenario
4. Click on Register Button
5. And registration should fail
*/
// testing to validate failed test by trying to register without entering FirstName

@Test(dataProvider = "AvactisTestData2")
public void avactisRegistrationFailTest(String pass, String repass, String ftName, String ltName, String countryName, 
String stateSelect,String zpcode, String City, String add1, String add2, String phnum ) throws IOException {

String title =  homepage.getTitleAfterLogin();
Assert.assertEquals(title, "Avactis Demo Store");

log.info("Click on My Account");

log.info("Click on Register Button");

form = homepage.goToMyAccount().clickOnRegister();
log.info("Fill up the Registration form");
//getting input data from xls file	

form.fillRegistrationDetails(pass, repass, ftName, ltName, countryName, stateSelect, zpcode, City, add1, add2, phnum);
log.info("Enter All the Registration details required to Create an Account");

boolean CheckValue = form.firstNameMissingIsDisplayed();
Assert.assertTrue(CheckValue, "Account Registration got failed");

form.getScreenshot();
}

 @BeforeMethod
	public void beforeClass() {	
	  DOMConfigurator.configure("src/test/java/avactisTestData/log4j.xml");
	  log = Logger.getLogger(AvactisRegistrationTest.class.getName());
	  log.info("Opening Url");
	  log.trace("Maximized window");
	  log.debug("Going to Avactis Home Page");
//	  initialization();
	  homepage = new AvactisHomePage1();
	  form = new AvactisRegistrationForm1();	 
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