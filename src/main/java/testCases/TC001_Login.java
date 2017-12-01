package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.TestNGWrappers;

public class TC001_Login extends TestNGWrappers {
	
	@BeforeClass
	public void setValues(){
		browserName = "chrome";
		dataSheetName = "TC001";
		testCaseName = "Login";
		testDescription = "Login to LeafTaps and LogOut";
		category = "smoke";
		authors = "Praveen";		
	}
	
	@Test(dataProvider = "fetchData")
	public void loginLogOut(String uName, String pwd){
		new LoginPage(driver, test)
		.enterUserName(uName);
	}
	
	
}










