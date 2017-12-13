package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.TestNGWrappers;

public class LoginPage extends TestNGWrappers{

	public LoginPage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
		if(!verifyTitle("Opentaps Open Source ERP + CRM")){
			reportStep("This is not Login Page", "FAIL");
		}		
	}
	
	/*@FindBy(name="uid")
    WebElement userName;

    

    @FindBy(name="password")
    WebElement password99Guru;*/


	
	public LoginPage enterUserName(String data){
		enterById("username", data);
		return this;
	}
	
	
	public LoginPage enterPassword(String data) {		
		enterByName("PASSWORD", data);	
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