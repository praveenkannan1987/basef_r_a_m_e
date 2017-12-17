package wrappers;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import utils.DataInputProvider;

public class TestNGWrappers extends GenericWrappers {
	
	public String browserName;
	public String dataSheetName;
	public int testCaseInc;
	protected Logger logger= Logger.getLogger(TestNGWrappers.class.getName());
	

	@BeforeSuite
	public void beforeSuite(){
		suppressLogs();
		logger.info("----------------------POM Started----------------------");
		startResult(folderCreation());
	}

	@BeforeTest
	public void beforeTest(){
		loadObjects();
		logger.info("----------------------Load Objects---------------------");
	}
	
	@BeforeMethod
	public void beforeMethod(){
		test = startTestCase(testCaseName, testDescription);
		test.assignCategory(category);
		test.assignAuthor(authors);
		invokeApp(browserName,testCaseName+"."+testCaseInc);
		System.out.println(testCaseInc);
	}
		
	@AfterSuite
	public void afterSuite(){
		endResult();
	}

	@AfterTest
	public void afterTest(){
		unloadObjects();
	}
	
	@AfterMethod
	public void afterMethod(){
		endTestcase();
		testCaseInc=testCaseInc+1;
		System.out.println(testCaseInc);
		quitBrowser();
		
	}
	
	@DataProvider(name="fetchData")
	public Object[][] getData(){
		return DataInputProvider.getSheet(dataSheetName);		
	}	
	
	
}






