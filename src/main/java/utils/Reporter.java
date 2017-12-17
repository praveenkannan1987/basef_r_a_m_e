package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import wrappers.GenericWrappers;

public abstract class Reporter {
	public ExtentTest test;
	public static ExtentReports extent;
	public String testCaseName, testDescription, category, authors;
	protected Logger logger= Logger.getLogger(Reporter.class.getName());


	public void reportStep(String desc,String formattedDate,String testCasefolder, String status) {
		String snapNumber = "";

		try {
			snapNumber=takeSnap(formattedDate,testCasefolder);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Write if it is successful or failure or information
		if(status.toUpperCase().equals("PASS")){
			test.log(LogStatus.PASS, desc+test.addScreenCapture("./../reports/"+formattedDate+"/"+snapNumber+".jpg"));
		}else if(status.toUpperCase().equals("FAIL")){
			test.log(LogStatus.FAIL, desc+test.addScreenCapture("./../reports/"+formattedDate+"/"+snapNumber+".jpg"));
			throw new RuntimeException("FAILED");
		}else if(status.toUpperCase().equals("INFO")){
			test.log(LogStatus.INFO, desc);
		}else if(status.toUpperCase().equals("WARN")){
			test.log(LogStatus.WARNING, desc+test.addScreenCapture("./../reports/"+formattedDate+"/"+snapNumber+".jpg"));
		}
	}
	public void reportStep(String desc, String status) {
		String snapNumber = "";

		try {
			snapNumber=takeSnap();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Write if it is successful or failure or information
		if(status.toUpperCase().equals("PASS")){
			test.log(LogStatus.PASS, desc+test.addScreenCapture("./../reports/images/"+snapNumber+".jpg"));
		}else if(status.toUpperCase().equals("FAIL")){
			test.log(LogStatus.FAIL, desc+test.addScreenCapture("./../reports/images/"+snapNumber+".jpg"));
			throw new RuntimeException("FAILED");
		}else if(status.toUpperCase().equals("INFO")){
			test.log(LogStatus.INFO, desc);
		}else if(status.toUpperCase().equals("WARN")){
			test.log(LogStatus.WARNING, desc+test.addScreenCapture("./../reports/images/"+snapNumber+".jpg"));
		}
	}

	public abstract String takeSnap(String formattedDate,String testCasefolder);
	public abstract String takeSnap();

	public ExtentReports startResult(String formattedDate){
		extent = new ExtentReports("./reports/"+formattedDate+"/result.html", false);
		extent.loadConfig(new File("./src/main/resources/extent-config.xml"));
		return extent;
	}

	public ExtentTest startTestCase(String testCaseName, String testDescription){
		test = extent.startTest(testCaseName, testDescription);
		return test;
	}

	public void endResult(){		
		extent.flush();
	}

	public void endTestcase(){
		extent.endTest(test);
	}
}