package pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import wrappers.TestNGWrappers;

public class LoginPage extends TestNGWrappers{

	public LoginPage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
		if(!verifyTitle("Gmail")){
			reportStep("This is not Login Page", "FAIL");
		}		
	}
	
	public LoginPage enterUserName(String data){
		enterById(prop.getProperty("Login.UserName.Id"), data);
		return this;
	}
	
	
	public LoginPage enterPassword(String data) {		
		enterByName(prop.getProperty("Login.Password.Id"), data);	
		return this;
	}
	
	
	public LoginPage verifyMsg(String text){
		verifyTextContainsById("errorDiv", text);
		return this;
	}
	public LoginPage threadWait(long millis){
		waitTime(millis);
		return this;
	}

}