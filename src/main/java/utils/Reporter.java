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

	public String formattedDate; 
	int inc=1;

	public void reportStep(String desc, String status) {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File("./src/main/resources/folder.properties")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		formattedDate = props.getProperty("foldername");
		String snapNumber = "";

		try {
			snapNumber=takeSnap(formattedDate,inc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Write if it is successful or failure or information
		if(status.toUpperCase().equals("PASS")){
			test.log(LogStatus.PASS, desc+test.addScreenCapture("./../reports/images/"+formattedDate+"/"+snapNumber+".jpg"));
			inc=inc+1;
		}else if(status.toUpperCase().equals("FAIL")){
			test.log(LogStatus.FAIL, desc+test.addScreenCapture("./../reports/images/"+formattedDate+"/"+snapNumber+".jpg"));
			inc=inc+1;
			throw new RuntimeException("FAILED");
		}else if(status.toUpperCase().equals("INFO")){
			test.log(LogStatus.INFO, desc);
		}else if(status.toUpperCase().equals("WARN")){
			test.log(LogStatus.WARNING, desc+test.addScreenCapture("./../reports/images/"+formattedDate+"/"+snapNumber+".jpg"));
			inc=inc+1;
		}
	}

	public abstract String takeSnap(String formattedDate1,int increament);


	public ExtentReports startResult(){
		formattedDate="";
		Date newDate=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		formattedDate= formatter.format(newDate);
		try {
			Properties props = new Properties();
			props.load(new FileInputStream(new File("./src/main/resources/folder.properties")));
			props.setProperty("foldername", formattedDate);
			FileOutputStream output = new FileOutputStream("./src/main/resources/folder.properties");
			props.store(new FileOutputStream(new File("./src/main/resources/folder.properties")), "");
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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