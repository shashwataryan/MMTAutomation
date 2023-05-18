package utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class Reports {
	public static ExtentReports extent =new ExtentReports();
	public static ExtentHtmlReporter htmlReporter;

	@BeforeAll
	public static void beforeSuiteSetup() {
		String filepath = System.getProperty("user.dir");
		htmlReporter = new ExtentHtmlReporter(filepath+"/Report.html");     
		extent.attachReporter(htmlReporter);
	}

	@AfterAll
	public static void afterSuite() {
		extent.flush();
	}
}
