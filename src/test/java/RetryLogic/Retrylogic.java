package RetryLogic;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retrylogic implements IRetryAnalyzer {

	private int retrycount = 0;
	private static final int maxRetryCount = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (retrycount < maxRetryCount) {
			retrycount++;
			return true;
		}
		return false;
	}

}
