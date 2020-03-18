package customlisterns;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import basePack.testbase;

public class Listerns implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println("Starting test execution for test :" + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Passed test :" + result.getName());

	}

	public void onTestFailure(ITestResult result) {

		Reporter.log("<a href=\"C:\\Users\\anilkumar06\\workspace\\Nagarro\\error.png\">Link to Screen shot</a>");
		System.out.println("Capturing screen shot on failure of this test:" + result.getName());
		testbase.captureScreeshot();

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

	}

}
