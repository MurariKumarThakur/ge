package report;

import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utility.ScreenShot;

public class ExtentReport {

	public static ExtentReports er;
	public static ExtentTest et;
	public static String reportPath;

	static {

		reportPath = "./extendsReportFolder/report.html";
		er = new ExtentReports(reportPath);
	}

	public static void initilizeExtendTest(ITestResult result) {

		et = er.startTest(result.getName());
	//	et.log(LogStatus.INFO, result.getName() + " * Method Execution start *");

	}

	public static void getFailureTestCaseImage(String imageName) {

		String imagePath = ScreenShot.getScreenShotPath(imageName);

		String reportPath = et.addScreenCapture(imagePath);

		et.log(LogStatus.FAIL, reportPath);

	}

	public static void getTestCaseStatus(ITestResult result) {
		initilizeExtendTest(result);

		if (result.getStatus() == ITestResult.SUCCESS) {

			et.log(LogStatus.PASS, result.getName() + "");

		} else if (result.getStatus() == ITestResult.SKIP) {
			et.log(LogStatus.SKIP, result.getName() + " * Method skipped from Execution *" + result.getThrowable());

		} else if (result.getStatus() == ITestResult.FAILURE) {
			getFailureTestCaseImage(result.getName());
			et.log(LogStatus.FAIL, result.getName() + " * Method failed from Execution *" + result.getThrowable());

		}

	}

	public static void endReport() {

		er.endTest(et);
		er.flush();

	}
	
	public static void  logStepInformation(String enterStepInfo){
		et.log(LogStatus.INFO, enterStepInfo);
	}

}
